package commands;

import utility.*;
import exceptions.*;


/**
 * Класс команда Commands.PrintAscendingCommand наследующийся от абстрактного класса Commands.AbstractCommand
 */
public class PrintAscendingCommand extends AbstractCommand {
    /** поле мэнеджер коллекции */
    private CollectionManager collectionManager;
    private Receiver receiver;

    /**
     * Конструктор - создание команды printAscending
     * @param collectionManager мэнеджер коллекции
     */
    public PrintAscendingCommand(CollectionManager collectionManager, Receiver receiver){
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");
        this.collectionManager = collectionManager;
        this.receiver = receiver;
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса Commands.AbstractCommand
     * метод реализует исполнение команды printAscending
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    public boolean execute(String argument){
        try{
            if(!argument.isEmpty()) throw new IncorrectlyInstalledElement();
            receiver.printAscending();
            return true;
        }catch (IncorrectlyInstalledElement e){
            System.out.println("Установлено неправильное значение элемента!");
        }
        return false;
    }
}

