/**
 * Класс команда ShowCommand наследующийся от абстрактного класса AbstractCommand
 */
public class ShowCommand extends AbstractCommand {
    /** поле мэнеджер коллекции */
    private CollectionManager collectionManager;

    /**
     * Конструктор - создание команды removeLower
     * @param collectionManager мэнеджер коллекции
     */
    public ShowCommand(CollectionManager collectionManager){
        super("show","вывести в стандартный поток вывода все элементы коллекции");
        this.collectionManager = collectionManager;
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса AbstractCommand
     * метод реализует исполнение команды show
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new IncorrectlyInstalledElement();
            collectionManager.show();
            return true;
        } catch (IncorrectlyInstalledElement e) {
            System.out.println("Установлено неправильное значение элемента!");
        }
        return false;
    }
}
