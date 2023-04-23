package commands;

import utility.*;
import exceptions.*;
import data.*;

/**
 * Класс команда RemoveAllByNumberOfParticipants наследующийся от абстрактного класса Commands.AbstractCommand
 */
public class RemoveAllByNumberOfParticipantsCommand extends AbstractCommand {
    /** поле мэнеджер коллекции */
    private CollectionManager collectionManager;
    /** поле переговорщик с пользователем */
    private NegotiatorWithUser nGW;
    private Receiver receiver;
    private ExceptionValidator eValidator;

    /**
     * Конструктор - создание команды printAscending
     * @param collectionManager мэнеджер коллекции
     * @param nGW переговорщик с пользователем
     */
    public RemoveAllByNumberOfParticipantsCommand(CollectionManager collectionManager, NegotiatorWithUser nGW, Receiver receiver, ExceptionValidator eValidator){
        super("remove_all_by_number_of_participants ", "удалить из коллекции все элементы, значение поля numberOfParticipants которго эквивалентны заданному");
        this.collectionManager = collectionManager;
        this.nGW = nGW;
        this.receiver = receiver;
        this.eValidator = eValidator;
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса Commands.AbstractCommand
     * метод реализует исполнение команды removeAllByNumberOfParticipants
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    public boolean execute(String argument){
        try {
            eValidator.argument(argument);
            eValidator.nullCollection(collectionManager);
            int numberOfParticipants = Integer.parseInt(argument);
            MusicBand musicBand = collectionManager.getByNumberOfParticipants(numberOfParticipants);
            eValidator.doesntExist(musicBand);
            receiver.removeAllByNumberOfParticipants(numberOfParticipants);
            System.out.println("Музыкальные группы успешно удалены!");
            return true;
        }catch(IncorrectlyInstalledElement e) {
            System.out.println("Установлено неправильное значение элемента! Пожалуйста введите количество участников по которому должны быть удалены элементы");
        }catch (NothingInTheCollectionException e){
            System.out.println("Данного элемента в коллекции нет!");
        }catch (MusicBandDoesNotExistException e){
            System.out.println("Музыкальной группы с таким количеством участников нет в данной коллекции!");
        }catch (NumberFormatException e){
            System.out.println("Неверно введённое количество участников!");
        }
        return false;
    }
}

