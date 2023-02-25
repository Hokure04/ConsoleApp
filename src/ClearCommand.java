/**
 * Класс команда clear наследующийся от абстрактного класса AbstractCommand
 */
public class ClearCommand extends AbstractCommand {
    /** поле мэнеджер коллекции  */
    private CollectionManager collectionManager;

    /**
     * Конструктор - создание команды ClearCommand
     * super - принимает имя объекта и его описание
     * @param collectionManager
     */
    public ClearCommand(CollectionManager collectionManager, NegotiatorWithUSer nGW){
        super("clear","команда очищает коллекцию");
        this.collectionManager =collectionManager;
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса AbstractCommand
     * метод реализует исполнение команды clear
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    @Override
    public boolean execute(String argument){
        try{
            if(!argument.isEmpty()) throw new IncorrectlyInstalledElement();
            collectionManager.clear();
            System.out.println("Коллекция полностью очищена");
            return true;
        }catch (IncorrectlyInstalledElement e){
            System.out.println("Установлено неправильное значение элемента!");
        }
        return false;
    }
}
