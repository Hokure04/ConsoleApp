/**
 * Класс Coordinate передаётся как одно из полей при создании музыкальной группы
 */
public class Coordinates {
    /** поле координата x */
    private Float x; //Поле не может быть null
    /** поле координата y */
    private Integer y; //Поле не может быть null

    /**
     * Конструктор для создания объекта координаты
     * @param x координата x
     * @param y координата y
     */
    public Coordinates(Float x, Integer y) {
        this.x = x;
        this.y = y;
    }

    /**
     * функция получения координаты x
     * @return возвращает координату x
     */
    public Float getX() {
        return x;
    }

    /**
     * функция получения координаты y
     * @return возвращает координату y
     */
    public Integer getY(){
        return y;
    }
}