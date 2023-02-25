/**
 * Класс команда history наследующийся от абстрактного класса AbstractCommand
 */
public class HistoryCommand extends AbstractCommand {

    /**
     * Конструктор - создание команды history
     */
    public HistoryCommand(CollectionManager collectionManager){
        super("history","вывести последние 9 команд");
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса AbstractCommand
     * метод реализует исполнение команды history
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    @Override
    public boolean execute(String argument){
        try{
            if(!argument.isEmpty()) throw new HistoryIsEmptyException();
            return true;
        }catch (HistoryIsEmptyException e){
            System.out.println("История команд пуста!");
        }
        return false;
    }
}