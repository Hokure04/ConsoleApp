package commands;

import utility.*;
import exceptions.*;
import data.*;

/**
 * Класс команда CountGreaterThanStudio наследующийся от абстрактного класса Commands.AbstractCommand
 */
public class CountGreaterThanStudioCommand extends AbstractCommand {
    /** поле мэнеджер коллекции */
    private CollectionManager collectionManager;
    /** поле переговорщик с пользователем */
    private NegotiatorWithUser nGW;
    private Receiver receiver;
    private ExceptionValidator eValidator;

    /**
     * Конструктор - создание команды CountGreaterThanStudio
     * @param collectionManager мэнеджер коллекции
     * @param nGW переговорщик с пользователем
     */
    public CountGreaterThanStudioCommand(CollectionManager collectionManager, NegotiatorWithUser nGW, Receiver receiver, ExceptionValidator eValidator){
        super("count_greater_than_studio studio", "вывести количество элементов, значение поля studio которых больше заданного");
        this.collectionManager = collectionManager;
        this.nGW = nGW;
        this.receiver = receiver;
        this.eValidator = eValidator;
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса Commands.AbstractCommand
     * метод реализует исполнение команды countGreaterThanStudio
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    @Override
    public boolean execute(String argument){
        try {
            eValidator.argument(argument);
            eValidator.nullCollection(collectionManager);
            Studio collectionStudio = new Studio(argument);
            eValidator.studioDoesntExist(collectionStudio);
            receiver.countGreaterThanStudio(collectionStudio);
            return true;
        }catch (IncorrectlyInstalledElement e){
            System.out.println("Установлено неправильное значение элемента! Вы должны ввести команду и назвние студии");
        }catch (NothingInTheCollectionException e){
            System.out.println("Коллекция пуста! Веедите в неё данные и повторите попытку");
        }catch (StudioDoesNotExistException e){
            System.out.println("Элемента с такой студией в коллекции нет!");
        }
        return false;
    }
}
