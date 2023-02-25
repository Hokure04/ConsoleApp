/**
 * Класс команда PrintAscendingCommand наследующийся от абстрактного класса AbstractCommand
 */
public class PrintAscendingCommand extends AbstractCommand {
    /** поле мэнеджер коллекции */
    private CollectionManager collectionManager;

    /**
     * Конструктор - создание команды printAscending
     * @param collectionManager мэнеджер коллекции
     */
    public PrintAscendingCommand(CollectionManager collectionManager){
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");
        this.collectionManager = collectionManager;
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса AbstractCommand
     * метод реализует исполнение команды printAscending
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    public boolean execute(String argument){
        try{
            if(!argument.isEmpty()) throw new IncorrectlyInstalledElement();
            collectionManager.printAscending();
            return true;
        }catch (IncorrectlyInstalledElement e){
            System.out.println("Установлено неправильное значение элемента!");
        }
        return false;
    }
}

