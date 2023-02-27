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

    /**
     * Конструктор - создание команды removeLower
     * @param collectionManager мэнеджер коллекции
     */
    public ShowCommand(CollectionManager collectionManager,Receiver receiver){
        super("show","вывести в стандартный поток вывода все элементы коллекции");
        this.collectionManager = collectionManager;
        this.receiver = receiver;
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
            if (!argument.isEmpty()) throw new IncorrectlyInstalledElement();
            receiver.show();
            return true;
        } catch (IncorrectlyInstalledElement e) {
            System.out.println("Установлено неправильное значение элемента!");
        }
        return false;
    }
}
