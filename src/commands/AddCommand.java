package commands;

import utility.*;
import exceptions.*;
import java.time.LocalDateTime;
import data.*;

/**
 * Класс команда add наследующийся от абстрактного класса Commands.AbstractCommand
 */
public class AddCommand extends AbstractCommand {
    /** поле мэнеджер коллекции  */
    private CollectionManager collectionManager;
    /** поле переговорщик с пользователем */
    private NegotiatorWithUser nGW;
    private Receiver receiver;
    private ExceptionValidator eValidator;

    /**
     * Конструктор - создание команды Commands.AddCommand
     * super - принимает имя объекта и его описание
     * @param collectionManager
     * @param nGW
     */
    public AddCommand(CollectionManager collectionManager, NegotiatorWithUser nGW, Receiver receiver, ExceptionValidator eValidator){
        super("add {element}", "добавляет элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.nGW = nGW;
        this.receiver = receiver;
        this.eValidator = eValidator;
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса Commands.AbstractCommand
     * метод реализует исполнение команды add
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    @Override
    public boolean execute(String argument){
        try{
            eValidator.noArgument(argument);
            receiver.add(new MusicBand(
                    collectionManager.generateNextId(),
                    nGW.askName(),
                    nGW.askCoordinates(),
                    nGW.askNumberOfParticipants(),
                    nGW.askSinglesCount(),
                    nGW.askDescription(),
                    nGW.askGenre(),
                    nGW.askStudio(),
                    LocalDateTime.now()
            ));
            System.out.println("Музыкальная группа добавлена");
            return true;
        }catch (IncorrectlyInstalledElement e){
            System.out.println("Установлено неправильное значение элемента!");
        }
        return false;
    }
}
