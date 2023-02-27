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

    /**
     * Конструктор - создание команды Commands.ClearCommand
     * super - принимает имя объекта и его описание
     * @param collectionManager
     */
    public ClearCommand(CollectionManager collectionManager, Receiver receiver){
        super("clear","команда очищает коллекцию");
        this.collectionManager =collectionManager;
        this.receiver = receiver;
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
            if(!argument.isEmpty()) throw new IncorrectlyInstalledElement();
            receiver.clear();
            System.out.println("Коллекция полностью очищена");
            return true;
        }catch (IncorrectlyInstalledElement e){
            System.out.println("Установлено неправильное значение элемента!");
        }
        return false;
    }
}
