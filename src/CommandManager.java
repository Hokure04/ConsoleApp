import java.util.ArrayList;
import java.util.List;

/**
 * Класс CommandManager осуществляющий работу с коммандами
 */
public class CommandManager {

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

    /**
     * Конструктор - мэнеджера команд
     * все параметры - это команды
     */
    public CommandManager(
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
    }

    /**
     *  функция получения истории команд
     * @return возвращает все команды, которы вводил пользователь
     */
    public String[] getCommandHistory(){
        return commandHistory;
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
        for (Command command : commands){
            if(command.getName().split(" ")[0].equals(commandChest)){
                for (int i = historySize-1; i>0; i--){
                    commandHistory[i] = commandHistory[i-1];
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
     * @param argument команда help, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean help(String argument){
        if(helpCommand.execute(argument)){
            for (Command command : commands){
                System.out.printf("%-37s%-1s%n", command.getName(), command.getDescription());
            }
            return true;
        }else return false;
    }

    /**
     * функция выполняющая, команду info
     * @param argument команда info, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean info(String argument){
        return infoCommand.execute(argument);
    }

    /**
     * функция выполняющая, команду show
     * @param argument команда show, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean show(String argument){
        return showCommand.execute(argument);
    }

    /**
     * функция выполняющая, команду add
     * @param argument команда add, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean add(String argument){
        return addCommand.execute(argument);
    }

    /**
     * функция выполняющая, команду update
     * @param argument команда update, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean update(String argument){
        return updateIdCommand.execute(argument);
    }

    /**
     * функция выполняющая, команду removeById
     * @param argument команда removeById, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean removeById(String argument){
        return removeByIdCommand.execute(argument);
    }

    /**
     * функция выполняющая, команду clear
     * @param argument команда clear, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean clear(String argument){
        return clearCommand.execute(argument);
    }

    /**
     * функция выполняющая, команду save
     * @param argument команда save, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean save(String argument){
        return saveCommand.execute(argument);
    }

    /**
     * функция выполняющая, команду execute_script
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
     * @param argument команда remove_greater, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean removeGreater(String argument){
        return removeGreaterCommand.execute(argument);
    }

    /**
     * функция выполняющая, команду remove_lower
     * @param argument команда remove_lower, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean removeLower(String argument){
        return removeLowerCommand.execute(argument);
    }

    /**
     * функция выполняющая, команду remove_by_participants
     * @param argument команда remove_by_participants, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean removeAllByNumberOfParticipants(String argument){
        return removeAllByNumberOfParticipantsCommand.execute(argument);
    }

    /**
     * функция выполняющая, команду count_greater_than_studio
     * @param argument команда count_greater_than_studio, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean countGreaterThanStudio(String argument){
        return countGreaterThanStudioCommand.execute(argument);
    }

    /**
     * функция выполняющая, команду print_ascending
     * @param argument команда print_ascending, которую ввёл пользователь
     * @return возвращает true, если команда выполнилась и false в обратном случае
     */
    public boolean printAscending(String argument){
        return printAscendingCommand.execute(argument);
    }

    /**
     * функция выполняющая, команду history
     * @param argument команда history, которую ввёл пользователь
     * @return возвращает историю команд
     */
    public boolean history(String argument){
        if (historyCommand.execute(argument)) {
            try{
                if(commandHistory.length == 0) throw  new HistoryIsEmptyException();
                System.out.println("История использованных команд:");
                for(int i = 0;i < commandHistory.length; i++){
                    if(commandHistory[i] != null) System.out.println(" "+commandHistory[i]);
                }
                return true;
            }catch (HistoryIsEmptyException e){
                System.out.println("История команд пуста!");
            }
        }
        return false;
    }
}
