import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс Console, которая осуществляет взаимодействие пользователя и вводимых им команд
 */
public class Console {
    /** поле мэнеджер команд */
    private CommandManager cManager;
    /** поле сканнер */
    private Scanner userScanner;
    /** поле переговорщик с пользователем */
    private NegotiatorWithUSer negotiator;
    /** поле список того, что прочитано в скрипте */
    private List<String> script = new ArrayList<>();

    /**
     * Конструктор создающий консоль
     * @param cManager команд мэнеджер
     * @param userScanner сканнер
     * @param negotiator переговорщик спользователем
     */
    public Console(CommandManager cManager, Scanner userScanner, NegotiatorWithUSer negotiator){
        this.cManager = cManager;
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
                cManager.addToHistory(userCommand[0]);
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
            System.out.println(argument);
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
        switch (userCommand[0]){
            case "":
                break;
            case "help":
                if (!cManager.help(userCommand[1])) return 1;
                break;
            case "info":
                if (!cManager.info(userCommand[1])) return 1;
                break;
            case "show":
                if (!cManager.show(userCommand[1])) return 1;
                break;
            case "add":
                if(!cManager.add(userCommand[1])) return 1;
                break;
            case "update":
                if (!cManager.update(userCommand[1])) return 1;
                break;
            case "remove_by_id":
                if (!cManager.removeById(userCommand[1])) return 1;
                break;
            case "clear":
                if (!cManager.clear(userCommand[1])) return 1;
                break;
            case "save":
                if (!cManager.save(userCommand[1])) return 1;
                break;
            case "execute_script":
                if (!cManager.executeScript(userCommand[1])) return 1;
                else return scripting(userCommand[1]);
            case "remove_greater":
                if (!cManager.removeGreater(userCommand[1])) return 1;
                break;
            case "remove_lower":
                if (!cManager.removeLower(userCommand[1])) return 1;
                break;
            case "history":
                if (!cManager.history(userCommand[1])) return 1;
                break;
            case "remove_all_by_number_of_participants":
                if (!cManager.removeAllByNumberOfParticipants(userCommand[1])) return 1;
                break;
            case "count_greater_than_studio":
                if (!cManager.countGreaterThanStudio(userCommand[1])) return 1;
                break;
            case "print_ascending":
                if (!cManager.printAscending(userCommand[1])) return 1;
                break;
            case "exit":
                if(!cManager.exit(userCommand[1])) return 1;
                else return 2;
            default:
                if(!cManager.noSuchCommand(userCommand[0])) return 1;
        }
        return 0;
    }
}
