/**
 * Класс команда RemoveAllByNumberOfParticipants наследующийся от абстрактного класса AbstractCommand
 */
public class RemoveAllByNumberOfParticipantsCommand extends AbstractCommand {
    /** поле мэнеджер коллекции */
    private CollectionManager collectionManager;
    /** поле переговорщик с пользователем */
    private NegotiatorWithUSer nGW;

    /**
     * Конструктор - создание команды printAscending
     * @param collectionManager мэнеджер коллекции
     * @param nGW переговорщик с пользователем
     */
    public RemoveAllByNumberOfParticipantsCommand(CollectionManager collectionManager, NegotiatorWithUSer nGW){
        super("remove_all_by_number_of_participants ", "удалить из коллекции все элементы, значение поля numberOfParticipants которго эквивалентны заданному");
        this.collectionManager = collectionManager;
        this.nGW = nGW;
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса AbstractCommand
     * метод реализует исполнение команды removeAllByNumberOfParticipants
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    public boolean execute(String argument){
        try {
            if (argument.isEmpty()) throw new IncorrectlyInstalledElement();
            if (collectionManager.learnCollectionSize() == 0) throw new NothingInTheCollectionException();
            int numberOfParticipants = Integer.parseInt(argument);
            MusicBand musicBand = collectionManager.getByNumberOfParticipants(numberOfParticipants);
            if (musicBand == null) throw new MusicBandDoesNotExistException();
            collectionManager.removeAllByNumberOfParticipants(numberOfParticipants);
            System.out.println("Музыкальные группы успешно удалены!");
            return true;
        }catch(IncorrectlyInstalledElement e) {
            System.out.println("Установлено неправильное значение элемента!");
        }catch (NothingInTheCollectionException e){
            System.out.println("Данного элемента в коллекции нет!");
        }catch (MusicBandDoesNotExistException e){
            System.out.println("Коллекция пуста!");
        }
        return false;
    }
}

