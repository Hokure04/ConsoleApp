import java.time.LocalDateTime;

/**
 * Класс команда UpdateCommand наследующийся от абстрактного класса AbstractCommand
 */
public class UpdateCommand extends AbstractCommand {
    /** поле мэнеджер коллекции */
    private CollectionManager collectionManager;
    /** поле переговорщик с пользователем */
    private NegotiatorWithUSer nGW;

    /**
     * Конструктор - создание команды update
     * @param collectionManager мэнеджер коллекции
     * @param nGW переговорщик с пользователем
     */
    public UpdateCommand(CollectionManager collectionManager, NegotiatorWithUSer nGW){
        super("update id {element}", "обновить значение элемента коллекции по его id");
        this.collectionManager = collectionManager;
        this.nGW = nGW;
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса AbstractCommand
     * метод реализует исполнение команды update
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    @Override
    public boolean execute(String argument){
        try{
            if(!argument.isEmpty()) throw new IncorrectlyInstalledElement();
            if(collectionManager.learnCollectionSize() == 0) throw new NothingInTheCollectionException();
            int id = nGW.askId();
            MusicBand band = collectionManager.getById(id);
            if(band == null) throw new MusicBandDoesNotExistException();
            String name = band.getName();
            Float coordinateX = band.getCoordinateX();
            Integer coordinateY = band.getCoordinateY();
            int numberOfParticipants = band.getNumberOfParticipants();
            Integer singlesCount = band.getSinglesCount();
            String description = band.getDescription();
            MusicGenre genre = band.getGenre();
            Studio studio = band.getStudio();
            LocalDateTime lDT = band.getCreationDate();
            collectionManager.remove(band);
            if (nGW.askQuestion("Вы действительно хотите изменить название группы?")) name = nGW.askName();
            if (nGW.askQuestion("Вы действительно хотите изменить координату x?")) coordinateX = nGW.askCoordinateX();
            if(nGW.askQuestion("Вы действительно хотите изменить координату y?")) coordinateY = nGW.askCoordinateY();
            if(nGW.askQuestion("Вы действительно хотите изменить количество участников?")) numberOfParticipants  = nGW.askNumberOfParticipants();
            if(nGW.askQuestion("Вы действительно хотите изменить количество синглов?")) singlesCount = nGW.askSinglesCount();
            if(nGW.askQuestion("Вы действительно хотите изменить описание?")) description = nGW.askDescription();
            if(nGW.askQuestion("Вы действительно хотите изменить жанр группы?")) genre = nGW.askGenre();
            if(nGW.askQuestion("Вы действительно хотите изменить студию?")) studio = nGW.askStudio();
            collectionManager.add(new MusicBand(
                    id,
                    name,
                    new Coordinates(coordinateX, coordinateY),
                    numberOfParticipants,
                    singlesCount,
                    description,
                    genre,
                    studio,
                    lDT
            ));
            System.out.println("Данные изменены");
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
