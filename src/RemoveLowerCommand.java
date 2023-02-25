import java.time.LocalDateTime;
/**
 * Класс команда RemoveLowerCommand наследующийся от абстрактного класса AbstractCommand
 */
public class RemoveLowerCommand extends AbstractCommand {
    /** поле мэнеджер коллекции */
    private CollectionManager collectionManager;
    /** поле переговорщик с пользователем */
    private NegotiatorWithUSer nGW;

    /**
     * Конструктор - создание команды removeLower
     * @param collectionManager мэнеджер коллекции
     * @param nGW переговорщик с пользователем
     */
    public RemoveLowerCommand(CollectionManager collectionManager, NegotiatorWithUSer nGW){
        super("remove_lower {element}", "удалить из коллекции все элементы, меньше чем заданный");
        this.collectionManager = collectionManager;
        this.nGW = nGW;
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса AbstractCommand
     * метод реализует исполнение команды removeLower
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
            collectionManager.removeLower(collectionBand);
            System.out.println("Группы меньше заданной удалены!");
            return true;
        }catch (IncorrectlyInstalledElement e){
            System.out.println("Установлено неправильное значение элемента!");
        }catch (MusicBandDoesNotExistException e){
            System.out.println("Данного элемента в коллекции нет!");
        }catch (NothingInTheCollectionException e){
            System.out.println("Коллекция пуста!");
        }
        return false;
    }
}
