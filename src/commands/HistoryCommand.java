package commands;

import utility.*;
import exceptions.*;


/**
 * Класс команда history наследующийся от абстрактного класса Commands.AbstractCommand
 */
public class HistoryCommand extends AbstractCommand {
    private ExceptionValidator eValidator;
    /**
     * Конструктор - создание команды history
     */
    public HistoryCommand(ExceptionValidator eValidator, CollectionManager collectionManager){
        super("history","вывести последние 9 команд");
        this.eValidator = eValidator;
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса Commands.AbstractCommand
     * метод реализует исполнение команды history
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    @Override
    public boolean execute(String argument){
        try{
            eValidator.emptyHistory(argument);
            return true;
        }catch (HistoryIsEmptyException e){
            System.out.println("История команд пуста!");
        }
        return false;
    }
}