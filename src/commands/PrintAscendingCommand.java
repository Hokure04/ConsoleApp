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
    private ExceptionValidator eValidator;

    /**
     * Конструктор - создание команды printAscending
     * @param collectionManager мэнеджер коллекции
     */
    public PrintAscendingCommand(CollectionManager collectionManager, Receiver receiver, ExceptionValidator eValidator){
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");
        this.collectionManager = collectionManager;
        this.receiver = receiver;
        this.eValidator = eValidator;
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса Commands.AbstractCommand
     * метод реализует исполнение команды printAscending
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    public boolean execute(String argument){
        try{
            eValidator.noArgument(argument);
            receiver.printAscending();
            return true;
        }catch (IncorrectlyInstalledElement e){
            System.out.println("Установлено неправильное значение элемента! Вы должны ввести просто команду без каких-либо аргументов");
        }
        return false;
    }
}

