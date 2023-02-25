import java.time.LocalDateTime;

/**
 * Класс команда info наследующийся от абстрактного класса AbstractCommand
 */
public class InfoCommand extends AbstractCommand {
    /** поле мэнеджер коллекции */
    private CollectionManager collectionManager;

    /**
     * Конструктор - создание команды info
     * @param collectionManager мэнеджер коллекции
     */
    public InfoCommand(CollectionManager collectionManager) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции");
        this.collectionManager = collectionManager;
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса AbstractCommand
     * метод реализует исполнение команды info
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    @Override
    public boolean execute(String argument){
        try{
            System.out.println("Информация о коллекции:");
            if(!argument.isEmpty()) throw new IncorrectlyInstalledElement();
            LocalDateTime lDT = collectionManager.getUseTime();
            String lastUseTime = (lDT == null) ? "дата создания ещё не устанавливалась":
                    lDT.toLocalDate().toString()+lDT.toLocalTime().toString();

            LocalDateTime lST = collectionManager.getLastSaveTime();
            String lastSaveTime = (lST == null) ? "дата сохранения ещё не устанавливалась":
                    lST.toLocalDate().toString()+lST.toLocalTime().toString();

            System.out.println("Тип коллекции: " + collectionManager.learnCollectionType());
            System.out.println("Количество элементов коллекции: " + collectionManager.learnCollectionSize());
            System.out.println("Последний раз использовалась: " + lastUseTime);
            System.out.println("Последнее сохранение: " + lastSaveTime);
            return true;
        }catch (IncorrectlyInstalledElement e){
            System.out.println("Установлено неправильное значение элемента!");
        }
        return false;
    }
}
