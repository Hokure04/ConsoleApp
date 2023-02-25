/**
 * Абстрактный класс AbstractCommand для всех команд приложения
 * имплементирующий интерфейс Command
 */
public abstract class AbstractCommand implements Command {
    /** поле имя */
    private String name;
    /** поле описание */
    private String description;

    /**
     * Конструктор - создание нового объекта с определёнными парамтрами
     * @param name - имя
     * @param description - описание
     */
    public AbstractCommand(String name, String description){
        this.name = name;
        this.description = description;
    }

    /**
     * функция получения значения поля {@link AbstractCommand#name}
     * @return возвращает название команды
     */
    public String getName(){
        return name;
    }

    /**
     * функция получения значения поля {@link AbstractCommand#description}
     * @return возвращает описание команды
     */
    public String getDescription(){
        return description;
    }
}
