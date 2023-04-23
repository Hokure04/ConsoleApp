package commands;

import utility.*;
import exceptions.*;

/**
 * Класс команда Commands.SaveCommand наследующийся от абстрактного класса Commands.AbstractCommand
 */
public class SaveCommand extends AbstractCommand {
    /** поле мэнеджер коллекции */
    private CollectionManager collectionManager;
    /** поле файл мэнеджер */
    private FileManager fileManager;
    private Receiver receiver;
    private ExceptionValidator eValidator;

    /**
     * Конструктор - создание команды save
     * @param collectionManager мэнеджер коллекции
     * @param fileManager файл мэнеджер
     */
    public SaveCommand(CollectionManager collectionManager, FileManager fileManager, Receiver receiver, ExceptionValidator eValidator){
        super("save","сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
        this.receiver = receiver;
        this.eValidator = eValidator;
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса Commands.AbstractCommand
     * метод реализует исполнение команды save
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    @Override
    public boolean execute(String argument){
        try {
            eValidator.noArgument(argument);
            receiver.saveCollection(fileManager);
            return true;
        }catch (IncorrectlyInstalledElement e){
            System.out.println("Установлено неправильное значение элемента! Вы должны ввести просто команду без каких-либо аргументов");
        }
        return false;
    }
}
