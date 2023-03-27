package commands;

import exceptions.*;
import utility.ExceptionValidator;


/**
 * Класс команда exit наследующийся от абстрактного класса Commands.AbstractCommand
 */
public class ExitCommand extends AbstractCommand {
    private ExceptionValidator eValidator;

    /**
     * Конструктор - создание команды exit
     */
    public ExitCommand(ExceptionValidator eValidator){
        super("exit", "выйти из программы");
        this.eValidator = eValidator;
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса Commands.AbstractCommand
     * метод реализует исполнение команды executeScript
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    public boolean execute(String argument){
        try{
            eValidator.noArgument(argument);
            return true;
        }catch (IncorrectlyInstalledElement e){
            System.out.println("Установлено неправильное значение элемента!");
        }
        return false;
    }
}