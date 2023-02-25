import java.time.LocalDateTime;
/**
 * Класс команда RemoveGreaterCommand наследующийся от абстрактного класса AbstractCommand
 */
public class RemoveGreaterCommand extends AbstractCommand {
    /** поле мэнеджер коллекции */
    private CollectionManager collectionManager;
    /** поле переговорщик с пользователем */
    private NegotiatorWithUSer nGW;

    /**
     * Конструктор - создание команды removeGreater
     * @param collectionManager мэнеджер коллекции
     * @param nGW переговорщик с пользователем
     */
    public RemoveGreaterCommand(CollectionManager collectionManager, NegotiatorWithUSer nGW){
        super("remove_greater", "удалить из коллекции все элементы превышающие заданный");
        this.collectionManager = collectionManager;
        this.nGW = nGW;
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса AbstractCommand
     * метод реализует исполнение команды removeGreater
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    @Override
    public boolean execute(String argument){
        try{
            if(!argument.isEmpty()) throw new IncorrectlyInstalledElement();
            if(collectionManager.learnCollectionSize() == 0) throw new NothingInTheCollectionException();
            MusicBand musicBand = new MusicBand(
                    collectionManager.generateNextId(),
                    nGW.askName(),
                    nGW.askCoordinates(),
                    nGW.askNumberOfParticipants(),
                    nGW.askSinglesCount(),
                    nGW.askDescription(),
                    nGW.askGenre(),
                    nGW.askStudio(),
                    LocalDateTime.now()
            );
            MusicBand collectionBand = collectionManager.getByValue(musicBand);
            if(collectionBand == null) throw new MusicBandDoesNotExistException();
            collectionManager.removeGreater(collectionBand);
            System.out.println("Группы больше заданной удалены!");
            return true;
        }catch (IncorrectlyInstalledElement e){
            System.out.println("Установлено неправильное значение элемента!");
        }catch (NothingInTheCollectionException e){
            System.out.println("Коллекция пуста!");
        }catch (MusicBandDoesNotExistException e){
            System.out.println("Данного элемента в коллекции нет!");
        }
        return false;
    }
}
