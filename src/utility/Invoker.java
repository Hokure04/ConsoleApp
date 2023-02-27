package utility;

import commands.Command;
import exceptions.*;

import java.util.*;

/**
 * Класс Utility.CommandManager осуществляющий работу с коммандами
 */
public class Invoker {

    /** поле размер истории команд */
    private final int historySize = 9;
    /** поле массив истории команд */
    private String[] commandHistory = new String[historySize];
    /** поле коллекция - лист, содержащий команды */
    private List<Command> commands = new ArrayList<>();
    /** поля команды */
    private Command helpCommand;
    private Command infoCommand;
    private Command showCommand;
    private Command addCommand;
    private Command updateIdCommand;
    private Command removeByIdCommand;
    private Command clearCommand;
    private Command saveCommand;
    private Command executeScriptCommand;
    private Command exitCommand;
    private Command removeGreaterCommand;
    private Command removeLowerCommand;
    private Command historyCommand;
    private Command removeAllByNumberOfParticipantsCommand;
    private Command countGreaterThanStudioCommand;
    private Command printAscendingCommand;
    private Map<String, Command> map = new HashMap<String, Command>();


    /**
     * Конструктор - мэнеджера команд
     * все параметры - это команды
     */
    public Invoker(
            Command helpCommand,
            Command infoCommand,
            Command showCommand,
            Command addCommand,
            Command updateIdCommand,
            Command removeByIdCommand,
            Command clearCommand,
            Command saveCommand,
            Command executeScriptCommand,
            Command exitCommand,
            Command removeGreaterCommand,
            Command removeLowerCommand,
            Command historyCommand,
            Command removeAllByNumberOfParticipantsCommand,
            Command countGreaterThanStudioCommand,
            Command printAscendingCommand
    ){
        this.helpCommand = helpCommand;
        this.infoCommand = infoCommand;
        this.showCommand = showCommand;
        this.addCommand = addCommand;
        this.updateIdCommand = updateIdCommand;
        this.removeByIdCommand = removeByIdCommand;
        this.clearCommand = clearCommand;
        this.saveCommand = saveCommand;
        this.executeScriptCommand = executeScriptCommand;
        this.exitCommand = exitCommand;
        this.removeGreaterCommand = removeGreaterCommand;
        this.removeLowerCommand = removeLowerCommand;
        this.historyCommand = historyCommand;
        this.removeAllByNumberOfParticipantsCommand = removeAllByNumberOfParticipantsCommand;
        this.countGreaterThanStudioCommand = countGreaterThanStudioCommand;
        this.printAscendingCommand = printAscendingCommand;

        /**
         * добавление всех команд в список
         */
        commands.add(helpCommand);
        commands.add(infoCommand);
        commands.add(showCommand);
        commands.add(updateIdCommand);
        commands.add(removeByIdCommand);
        commands.add(clearCommand);
        commands.add(saveCommand);
        commands.add(executeScriptCommand);
        commands.add(exitCommand);
        commands.add(removeGreaterCommand);
        commands.add(removeLowerCommand);
        commands.add(historyCommand);
        commands.add(removeAllByNumberOfParticipantsCommand);
        commands.add(countGreaterThanStudioCommand);
        commands.add(printAscendingCommand);

        map.put("info",infoCommand);
        map.put("show", showCommand);
        map.put("add", addCommand);
        map.put("update",updateIdCommand);
        map.put("remove_by_id",removeByIdCommand);
        map.put("clear", clearCommand);
        map.put("save", saveCommand);
        map.put("remove_greater",removeGreaterCommand);
        map.put("remove_lower",removeLowerCommand);
        map.put("remove_all_by_number_of_participants", removeAllByNumberOfParticipantsCommand);
        map.put("count_greater_than_studio", countGreaterThanStudioCommand);
        map.put("print_ascending", printAscendingCommand);
        map.put("history",historyCommand);
        map.put("help", helpCommand);
    }


    /**
     *  функция получения истории команд
     * @return возвращает все команды, которы вводил пользователь
     */
    public String[] getCommandHistory(){
        return commandHistory;
    }

    public void launch(String argument1, String argument2) {
        int i = 0 ;
        for (String key : map.keySet()) {
            if (argument1.equals(key)) {
                i += 1;
                this.map.get(key).execute(argument2);
            }
        }
        if (argument1.equals("help")) this.help(argument1, argument2);
        if (argument1.equals("history")) this.history(argument1, argument2);
        if (i == 0 && !argument1.equals("exit") && !argument1.equals("execute_script")) this.noSuchCommand(argument1);
    }

    /**
     * функция получения списка команд
     * @return возвращает весь список команд
     */
    public List<Command> getCommands(){
        return commands;
    }

    /**
     * функция, которая добавляет команду в историю
     * @param commandChest возвращает команды, которые находятся в массиве
     */
    public void addToHistory(String commandChest){
        for (Command command : commands) {
            if (command.getName().split(" ")[0].equals(commandChest)) {
                for (int i = historySize - 1; i > 0; i--) {
                    commandHistory[i] = commandHistory[i - 1];
                }
                commandHistory[0] = commandChest;
            }
        }
    }

    /**
     * функция, которая проверяет существует ли данная команда
     * @param command команда, которую ввёл в консоль пользователь
     * @return возвращает false, если команды не существует
     */
    public boolean noSuchCommand(String command){
        System.out.println("Данная команда не найдена. Введите 'help' для большей информации");
        return false;
    }

    /**
     * функция, которая выводит список команд и их описание
     * @param argument1 команда save, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean help(String argument1, String argument2){
            if (argument1.equals("help")) {
                if (map.get(argument1).execute(argument2)) {
                    for (Command command : commands) {
                        System.out.printf("%-37s%-1s%n", command.getName(), command.getDescription());
                    }
                } return true;
            }
            else return false;
    }

    /**
     * функция выполняющая, команду info
     * @param argument1 команда info, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean info(String argument1,String argument2){
        return map.get(argument1).execute(argument2);
    }

    /**
     * функция выполняющая, команду show
     * @param argument1 команда save, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean show(String argument1,String argument2){
        return map.get(argument1).execute(argument2);
    }

    /**
     * функция выполняющая, команду add
     * @param argument1 команда add, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean add(String argument1, String argument2){
        return map.get(argument1).execute(argument2);
    }

    /**
     * функция выполняющая, команду update
     * @param argument1 команда update, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean update(String argument1, String argument2){
        return map.get(argument1).execute(argument2);
    }

    /**
     * функция выполняющая, команду removeById
     * @param argument1 команда removeById, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean removeById(String argument1, String argument2){
        return map.get(argument1).execute(argument2);
    }

    /**
     * функция выполняющая, команду clear
     * @param argument1 команда clear, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean clear(String argument1,String argument2){
        return map.get(argument1).execute(argument2);
    }

    /**
     * функция выполняющая, команду save
     * @param argument1 команда save, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean save(String argument1,String argument2){
        return map.get(argument1).execute(argument2);
    }

    /**
     * функция выполняющая, команду execute_script
     *
     * @param argument команда execute_script, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean executeScript(String argument){
        return executeScriptCommand.execute(argument);
    }

    /**
     * функция выполняющая, команду exit
     * @param argument команда exit, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean exit(String argument){
        return exitCommand.execute(argument);
    }

    /**
     * функция выполняющая, команду remove_greater
     * @param argument1 команда remove_greater, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean removeGreater(String argument1, String argument2){
        return map.get(argument1).execute(argument2);
    }

    /**
     * функция выполняющая, команду remove_lower
     * @param argument1 команда remove_lower, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean removeLower(String argument1,String argument2){
        return map.get(argument1).execute(argument2);
    }

    /**
     * функция выполняющая, команду remove_by_participants
     * @param argument1 команда remove_by_participants, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean removeAllByNumberOfParticipants(String argument1, String argument2){
        return map.get(argument1).execute(argument2);
    }

    /**
     * функция выполняющая, команду count_greater_than_studio
     * @param argument1 команда count_greater_than_studio, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean countGreaterThanStudio(String argument1, String argument2){
        return map.get(argument1).execute(argument2);
    }

    /**
     * функция выполняющая, команду print_ascending
     * @param argument1 команда print_ascending, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean printAscending(String argument1, String argument2){
        return map.get(argument1).execute(argument2);
    }

    /**
     * функция выполняющая, команду history
     * @param argument1 команда history, которую ввёл пользователь
     * @return возвращает историю команд
     */
    public boolean history(String argument1,String argument2){
        if (map.get(argument1).execute(argument2)) {
            try {
                if (commandHistory.length == 0) throw new HistoryIsEmptyException();
                System.out.println("История использованных команд:");
                for (int i = 0; i < commandHistory.length; i++) {
                    if (commandHistory[i] != null) System.out.println(" " + commandHistory[i]);
                }
                return true;
            } catch (HistoryIsEmptyException e) {
                System.out.println("История команд пуста!");
            }
        }
        return false;
    }
}
