package commands;

import utility.*;
import exceptions.*;

/**
 * Класс команда Commands.ShowCommand наследующийся от абстрактного класса Commands.AbstractCommand
 */
public class ShowCommand extends AbstractCommand {
    /** поле мэнеджер коллекции */
    private CollectionManager collectionManager;
    private Receiver receiver;
    private ExceptionValidator eValidator;

    /**
     * Конструктор - создание команды removeLower
     * @param collectionManager мэнеджер коллекции
     */
    public ShowCommand(CollectionManager collectionManager,Receiver receiver, ExceptionValidator eValidator){
        super("show","вывести в стандартный поток вывода все элементы коллекции");
        this.collectionManager = collectionManager;
        this.receiver = receiver;
        this.eValidator = eValidator;
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса Commands.AbstractCommand
     * метод реализует исполнение команды show
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    @Override
    public boolean execute(String argument) {
        try {
            eValidator.noArgument(argument);
            receiver.show();
            return true;
        } catch (IncorrectlyInstalledElement e) {
            System.out.println("Установлено неправильное значение элемента! Вы должны ввести просто команду без каких-либо аргументов");
        }
        return false;
    }
}
