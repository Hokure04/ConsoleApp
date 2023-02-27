package data;

/**
 * Класс студия имплементирующий класс Comparable
 */
public class Studio implements Comparable<Studio> {
    /** поле имя */
    private String name; //Поле не может быть null

    /**
     * Конструктор для создания объекта студия
     * @param name возвращает название объекиа
     */
    public Studio(String name) {
        this.name = name;
    }

    /**
     * функция получения имени объекта
     * @return возвращает название студии
     */
    public String getName() {
        try {
            return name;
        }catch (NullPointerException e){
            return null;
        }
    }

    /**
     * функция сравнивает два объекта по их именам
     * @param o the object to be compared.
     * @return возвращает объект название которого начинается на меньшую букву
     */
    @Override
    public int compareTo(Studio o){
        return this.getName().compareTo(o.getName());
    }
}
