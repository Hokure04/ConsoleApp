/**
 * Класс команда exit наследующийся от абстрактного класса AbstractCommand
 */
public class ExitCommand extends AbstractCommand {
    /**
     * Конструктор - создание команды exit
     */
    public ExitCommand(){
        super("exit", "выйти из программы");
    }

    /**
     * метод execute должен быть переопределён для всех наследников класса AbstractCommand
     * метод реализует исполнение команды executeScript
     * @param argument - строка введённая пользователем в консоли
     * @return возвращает true если выполнение кода прошло успешно, и false если были найдены ошибки
     */
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