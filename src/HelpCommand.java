/**
 * Класс команда help наследующийся от абстрактного класса AbstractCommand
 */
public class HelpCommand extends AbstractCommand {

    /**
     * Конструктор - создание команды help
     */
    public HelpCommand() {
        super("help", "вывести все доступные команды");
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса AbstractCommand
     * метод реализует исполнение команды help
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
    @Override
    public boolean execute(String argument){
        try{
            if(!argument.isEmpty()) throw new IncorrectlyInstalledElement();
            return true;
        }catch (IncorrectlyInstalledElement e){
            System.out.println("Установлено неправильное значение элемента!");
        }
        return false;
    }

}