package commands;

import exceptions.*;
import utility.ExceptionValidator;


/**
 * Класс команда help наследующийся от абстрактного класса Commands.AbstractCommand
 */
public class HelpCommand extends AbstractCommand {
    private ExceptionValidator eValidator;
    /**
     * Конструктор - создание команды help
     */
    public HelpCommand(ExceptionValidator eValidator) {
        super("help", "вывести все доступные команды");
        this.eValidator = eValidator;
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса Commands.AbstractCommand
     * метод реализует исполнение команды help
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    @Override
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