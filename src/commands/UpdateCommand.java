package commands;

import data.Coordinates;
import data.MusicBand;
import data.MusicGenre;
import data.Studio;
import exceptions.IncorrectlyInstalledElement;
import exceptions.MusicBandDoesNotExistException;
import exceptions.NothingInTheCollectionException;
import utility.CollectionManager;
import utility.ExceptionValidator;
import utility.NegotiatorWithUser;
import utility.Receiver;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

/**
 * Класс команда Commands.UpdateCommand наследующийся от абстрактного класса Commands.AbstractCommand
 */
public class UpdateCommand extends AbstractCommand {
    /** поле мэнеджер коллекции */
    private CollectionManager collectionManager;
    /** поле переговорщик с пользователем */
    private NegotiatorWithUser nGW;
    private Receiver receiver;
    private ExceptionValidator eValidator;

    /**
     * Конструктор - создание команды update
     * @param collectionManager мэнеджер коллекции
     * @param nGW переговорщик с пользователем
     */
    public UpdateCommand(CollectionManager collectionManager, NegotiatorWithUser nGW, Receiver receiver, ExceptionValidator eValidator){
        super("update id {element}", "обновить значение элемента коллекции по его id");
        this.collectionManager = collectionManager;
        this.nGW = nGW;
        this.receiver = receiver;
        this.eValidator = eValidator;
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса Commands.AbstractCommand
     * метод реализует исполнение команды update
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    @Override
    public boolean execute(String argument){
        try{
            eValidator.argument(argument);
            eValidator.nullCollection(collectionManager);
            int id = Integer.parseInt(argument);
            MusicBand band = receiver.getById(id);
            eValidator.doesntExist(band);
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
            receiver.add(new MusicBand(
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
            System.out.println("Установлено неправильное значение элемента! Пожалуйста добавьте аргумент id элмемента, который должен быть обновлён");
        }catch (NothingInTheCollectionException e){
            System.out.println("Коллекция пуста! Веедите в неё данные и повторите попытку");
        }catch (MusicBandDoesNotExistException e){
            System.out.println("Данного элемента в коллекции нет! Введите правильное значение элемента");
        }catch (NumberFormatException e) {
            System.out.println("Неверно введённое значение id!");
        }catch (NoSuchElementException e){
            System.out.println("Введённые данные не являются корректными программа не может их исполнить");
        }
        return false;
    }
}
