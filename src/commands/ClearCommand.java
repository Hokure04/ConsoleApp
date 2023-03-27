package commands;

import utility.*;
import exceptions.*;

/**
 * Класс команда clear наследующийся от абстрактного класса Commands.AbstractCommand
 */
public class ClearCommand extends AbstractCommand {
    /** поле мэнеджер коллекции  */
    private CollectionManager collectionManager;
    private Receiver receiver;
    private ExceptionValidator eValidator;

    /**
     * Конструктор - создание команды Commands.ClearCommand
     * super - принимает имя объекта и его описание
     * @param collectionManager
     */
    public ClearCommand(CollectionManager collectionManager, Receiver receiver, ExceptionValidator eValidator){
        super("clear","команда очищает коллекцию");
        this.collectionManager =collectionManager;
        this.receiver = receiver;
        this.eValidator = eValidator;
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса Commands.AbstractCommand
     * метод реализует исполнение команды clear
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    @Override
    public boolean execute(String argument){
        try{
            eValidator.noArgument(argument);
            receiver.clear();
            System.out.println("Коллекция полностью очищена");
            return true;
        }catch (IncorrectlyInstalledElement e){
            System.out.println("Установлено неправильное значение элемента!");
        }
        return false;
    }
}
