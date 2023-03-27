package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Класс Utility.Console, которая осуществляет взаимодействие пользователя и вводимых им команд
 */
public class Console {
    /** поле мэнеджер команд */
    private static Invoker invoker;
    /** поле сканнер */
    private Scanner userScanner;
    /** поле переговорщик с пользователем */
    private NegotiatorWithUser negotiator;
    /** поле список того, что прочитано в скрипте */
    private List<String> script = new ArrayList<>();


    /**
     * Конструктор создающий консоль
     * @param invoker команд мэнеджер
     * @param userScanner сканнер
     * @param negotiator переговорщик спользователем
     */
    public Console(Invoker invoker, Scanner userScanner, NegotiatorWithUser negotiator){
        this.invoker = invoker;
        this.userScanner = userScanner;
        this.negotiator = negotiator;

    }

    /**
     * функция, котрая запускает рабочее поле для пользователя и проверяет введённые пользователем данные
     */
    public void start(){
        String[] userCommand = {"",""};
        int commandStat;
        try {
            do {
                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                invoker.addToHistory(userCommand[0]);
                commandStat = launchCommand(userCommand);
            }while (commandStat != 2);
        }catch (NoSuchElementException e){
            System.out.println("Вы ничего не ввели!");
        }catch (IllegalStateException e){
            System.out.println("Ошибка не распознана");
        }
    }

    /**
     * функция, которая осуществляет считывание данных из скрипта и проверка, верные ли данные находятся в скрипте
     * @param argument ссылка на файл в котором находится скрипт
     */
    public int scripting(String argument) {
        String[] userCommand = {"",""};
        int commandStatus;
        script.add(argument);
        Scanner scriptScan = null;
        try {
            File file = new File(argument);
            scriptScan = new Scanner(file);
            if(!scriptScan.hasNext()) throw new NoSuchElementException();
            Scanner secondScanner = negotiator.getUserScanner();
            negotiator.setUserScanner(scriptScan);
            negotiator.setFileMode();
            do{
                userCommand = (scriptScan.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while (scriptScan.hasNextLine() && userCommand[0].isEmpty()){
                    userCommand = (scriptScan.nextLine().trim() + " ").split(" ",2);
                    userCommand[1] = userCommand[1].trim();
                }
                System.out.println(String.join(" ", userCommand));
                commandStatus = launchCommand(userCommand);
            }while (commandStatus == 0 && scriptScan.hasNextLine());
            negotiator.setUserScanner(secondScanner);
            negotiator.setUserMode();
            if (commandStatus == 1 && !(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty()))
                System.out.println("Приложение не может исполнить данный скрипт!");
            return commandStatus;
        }catch (FileNotFoundException e){
            System.out.println("Файл не найден!");
        }catch (NoSuchElementException e){
            System.out.println("Скрипт пуст!");
        }catch (IllegalStateException e){
            System.out.println("Неизвестная ошибка!");
            System.exit(0);
        }finally {
            script.remove(script.size()-1);
        }
        return 1;
    }

    /**
     * проверка того правильно ли введены команды и их запуск
     * @param userCommand команда введённая пользователем
     */
    private int launchCommand(String[] userCommand){
        if(userCommand[0].equals("exit")){
            if(!invoker.exit(userCommand[1])) return 1;
            else return 2;
        }
        invoker.launch(userCommand[0],userCommand[1]);
        if(userCommand[0].equals("execute_script")){
            if (!invoker.executeScript(userCommand[1])) return 1;
            else return scripting(userCommand[1]);
        }
        return 0;
    }
}
