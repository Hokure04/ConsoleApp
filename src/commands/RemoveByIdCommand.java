package commands;

import utility.*;
import exceptions.*;
import data.*;

/**
 * Класс команда RemoveById наследующийся от абстрактного класса Commands.AbstractCommand
 */
public class RemoveByIdCommand extends AbstractCommand {
    /** поле мэнеджер коллекции */
    private CollectionManager collectionManager;
    /** поле переговорщик с пользователем */
    private NegotiatorWithUser nGW;
    private Receiver receiver;

    /**
     * Конструктор - создание команды removeById
     * @param collectionManager мэнеджер коллекции
     * @param nGW переговорщик с пользователем
     */
    public RemoveByIdCommand(CollectionManager collectionManager, NegotiatorWithUser nGW, Receiver receiver){
        super("remove_by_id","удаление элемента коллекции по его id");
        this.collectionManager = collectionManager;
        this.nGW = nGW;
        this.receiver = receiver;
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса Commands.AbstractCommand
     * метод реализует исполнение команды removeById
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    public boolean execute(String argument){
        try{
            if(argument.isEmpty()) throw new IncorrectlyInstalledElement();
            if(collectionManager.learnCollectionSize() == 0) throw new NothingInTheCollectionException();
            int id = Integer.parseInt(argument);
            MusicBand musicBand = receiver.getById(id);
            if(musicBand == null) throw new MusicBandDoesNotExistException();
            collectionManager.remove(musicBand);
            System.out.println("Музыкальная группа удалена");
            return true;
        }catch (IncorrectlyInstalledElement e){
            System.out.println("Установлено неправильное значение элемента!");
        }catch (MusicBandDoesNotExistException e){
            System.out.println("Данного элемента в коллекции нет!");
        }catch (NothingInTheCollectionException e){
            System.out.println("Коллекция пуста!");
        }catch (NumberFormatException e){
            System.out.println("Неверно введённое значение id!");
        }
        return false;
    }
}

