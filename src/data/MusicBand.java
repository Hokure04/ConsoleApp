package data;

import java.time.LocalDateTime;

/**
 * Класс Data.MusicBand является объектом, с коллекцией которого работает приложение
 */
public class MusicBand implements Comparable<MusicBand>{
    /** поле id */
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    /** поле название музыкальной группы */
    private String name; //Поле не может быть null, Строка не может быть пустой
    /** поле координаты музыкальной группы */
    private Coordinates coordinates; //Поле не может быть null
    /** поле время добавления или создания элемента в коллекции */
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    /** поле количество участников музыкальной группы */
    private int numberOfParticipants;
    /** поле количество синглов */
    private Integer singlesCount; //Поле может быть null, Значение поля должно быть больше 0
    /** поле описание */
    private String description; //Поле может быть null
    /** поле жанр группы */
    private MusicGenre genre; //Поле может быть null
    /** поле студия */
    private Studio studio; //Поле может быть null

    /**
     * Конструктор, создающий обЪект музыкальная группа
     * @param id id объекта
     * @param name название
     * @param coord координаты
     * @param numberOfParticipants количество участников
     * @param singlesCount количество синглов
     * @param description описание
     * @param genre жанр
     * @param studio студия
     * @param creationDate дата добавления, создания
     */
    public MusicBand(Integer id, String name, Coordinates coord, int numberOfParticipants, Integer singlesCount, String description, MusicGenre genre, Studio studio, LocalDateTime creationDate) {
        this.id = id;
        this.name = name;
        this.coordinates = coord;
        this.numberOfParticipants = numberOfParticipants;
        this.singlesCount = singlesCount;
        this.description = description;
        this.genre = genre;
        this.studio = studio;
        this.creationDate = creationDate;
    }

    /**
     * функция получения id объекта
     * @return возвращает id
     */
    public Integer getId() {
        try {
            return id;
        }catch (NullPointerException e){
            System.out.println("Непредвиденная ошибка!");
        }
        return 1;
    }

    /**
     * функция получения имени объекта
     * @return возвращает название музыкальной группы
     */
    public String getName() {
        try {
            return name;
        }catch (NullPointerException e){
            return "Непредвиденная ошибка!";
        }
    }

    /**
     * функция получения координаты x
     * @return возвращает координату x
     */
    public Float getCoordinateX() {
        try {
            return coordinates.getX();
        }catch (NullPointerException e){
            System.out.println("Непридведенная ошибка!");
        }
        return null;
    }

    /**
     * функция получения координаты y
     * @return возвращает координату y
     */
    public Integer getCoordinateY() {
        try {
            return coordinates.getY();
        }catch (NullPointerException e){
            System.out.println("Непредвиденная ошибка!");
        }
        return 0;
    }

    /**
     * функция получения количества участников объекта
     * @return возвращает количество участников
     */
    public int getNumberOfParticipants() {
        try {
            return numberOfParticipants;
        }catch (NullPointerException e){
            System.out.println("Непредвиденная ошибка!");
        }
        return 1;
    }

    /**
     * функция получения количества синглов объекта
     * @return возвращает количество синглов
     */
    public Integer getSinglesCount() {
        try {
            return singlesCount;
        }catch (NullPointerException e){
            return null;
        }
    }

    /**
     * функция получения описания объекта
     * @return возвращает описание музыкальной группы
     */
    public String getDescription() {
        try {
            return this.description;
        }catch (NullPointerException e){
            return null;
        }
    }

    /**
     * функция получения жанра объекта
     * @return возвращает жанр музыкальной группы
     */
    public MusicGenre getGenre() {
        try {
            return genre;
        }catch (NullPointerException e){
            return null;
        }
    }

    /**
     * функция получения имени студии
     * @return возвращает название студии
     */
    public String getStudioName() {
        try {
            return studio.getName();
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * функция получения студии объекта
     * @return возвращает студию
     */
    public Studio getStudio(){
        try {
            return studio;
        }catch (NullPointerException e){
            return null;
        }
    }

    /**
     * функция получения времени созлания, обновления
     * @return возвращает локальной время
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public  int compare(MusicBand band){
        if(this.toString().compareTo(band.toString()) == 0){
            return this.id - band.id;
        }else {
            return this.toString().compareTo(band.toString());
        }
    }


    /**
     * функция сравнивает два объекта по их  id
     * @param o the object to be compared.
     * @return возвращает результат сравнения
     */
    @Override
    public int compareTo(MusicBand o) {
        if(this.getId()<o.getId())return 1;
        if(this.getId()>o.getId())return -1;
        return 0;
    }
}