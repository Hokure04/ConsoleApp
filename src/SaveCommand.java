/**
 * Класс команда SaveCommand наследующийся от абстрактного класса AbstractCommand
 */
public class SaveCommand extends AbstractCommand {
    /** поле мэнеджер коллекции */
    private CollectionManager collectionManager;
    /** поле файл мэнеджер */
    private FileManager fileManager;

    /**
     * Конструктор - создание команды save
     * @param collectionManager мэнеджер коллекции
     * @param fileManager файл мэнеджер
     */
    public SaveCommand(CollectionManager collectionManager, FileManager fileManager){
        super("save","сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса AbstractCommand
     * метод реализует исполнение команды save
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    @Override
    public boolean execute(String argument){
        try {
            if (!argument.isEmpty()) throw new IncorrectlyInstalledElement();
            collectionManager.saveCollection(fileManager);
            System.out.println("Коллекция успешна сохранена");
            return true;
        }catch (IncorrectlyInstalledElement e){
            System.out.println("Установлено неправильное значение элемента!");
        }
        return false;
    }
}
