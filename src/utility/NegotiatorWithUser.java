package utility;

import java.util.Objects;
import java.util.Scanner;
import exceptions.*;
import data.*;

/**
 * Класс NegotiatorWithUser предназначеный для того, чтобы задавать пользователю вопросы
 */
public class NegotiatorWithUser {
    /** поле сканнер */
    private Scanner userScanner;
    /** поле режим файла */
    private boolean fileMode;
    private ExceptionValidator eValidator;

    /**
     * Конструктор создающий объект переговорщик с пользователем
     * @param userScanner сканнер
     */
    public NegotiatorWithUser(Scanner userScanner, ExceptionValidator eValidator){
        this.userScanner = userScanner;
        fileMode = false;
        this.eValidator = eValidator;
    }

    /**
     * функция устанавливает сканнер, котрый считывает введённые пользователем данные
     * @param userScanner возвращает сканнер
     */
    public void setUserScanner(Scanner userScanner){
        this.userScanner = userScanner;
    }

    /**
     * функция получения пользовательского сканнера
     * @return возвращает сканнер
     */
    public Scanner getUserScanner(){
        return userScanner;
    }

    /**
     * функция устанавливает режим файла, котрый будет работать с файлом в зависимости от установленнного значения
     */
    public void setFileMode(){
        fileMode = true;
    }

    /**
     * функция устанавливает режим, в котором сможет работать пользователь
     */
    public void setUserMode(){
        fileMode = false;
    }

    /**
     * функция спрашивает у пользователя название музыкальной группы
     * @return возвращает имя объекта
     */
    public String askName(){
        String name;
        while (true) {
            try {
                System.out.println("Введите имя:");
                name = userScanner.nextLine().trim();
                if (fileMode) System.out.println(name);
                eValidator.emptyString(name);
                break;
            }catch (NumberFormatException exception){
                System.out.println("Данные введены неверно!");
            }catch (MustBeNotEmptyException exception){
                System.out.println("Значение обязательно должно быть введено!");
            }catch (NullPointerException e){
                System.out.println("Не должно быть null");
            }
        }
        return name;
    }

    /**
     * функция спрашивает у пользователя id группы
     * @return возвращает id объекта
     */
    public Integer askId(){
        int id;
        while (true){
            try {
                System.out.println("ВВедите ID:");
                id = Integer.parseInt(userScanner.nextLine());
                if (fileMode) System.out.println(id);
                eValidator.zeroAndLower(id);
                break;
            }catch (NumberFormatException e){
                System.out.println("Данные введены неверно!");
            }catch (NullPointerException e){
                System.out.println("Значение обязательно должно быть введено!");
            }catch (LessThanZeroException e){
                System.out.println("Значение должно быть больше нуля!");
            }
        }
        return id;
    }

    /**
     * функция спрашивает у пользователя координату x
     * @return возвращает координату x
     */
    public Float askCoordinateX(){
        Float x;
        while (true){
            try {
                System.out.println("Введите координату x:");
                x = Float.valueOf(userScanner.nextLine());
                if (fileMode) System.out.println(x);
                break;
            }catch (NumberFormatException e){
                System.out.println("Данные введены неверно!");
            }catch (NullPointerException e){
                System.out.println("Значение обязательно должно быть введено!");
            }
        }
        return x;
    }

    /**
     * функция спрашивает у пользователя координату y
     * @return возвращает координату y
     */
    public Integer askCoordinateY(){
        Integer y;
        while (true){
            try {
                System.out.println("Введите координату y:");
                y = Integer.valueOf(userScanner.nextLine());
                if (fileMode) System.out.println(y);
                break;
            }catch (NumberFormatException e){
                System.out.println("Данные введены неверно!");
            }catch (NullPointerException e){
                System.out.println("Значение обязательно должно быть введено!");
            }
        }
        return y;
    }

    /**
     * функция спрашивает у пользователя координаты объекта
     * @return возвращает координаты
     */
    public Coordinates askCoordinates(){
        Float x = this.askCoordinateX();
        Integer y = this.askCoordinateY();
        return new Coordinates(x, y);
    }

    /**
     * функция спрашивает количество участников музыкальной группы
     * @return возвращает количество участников
     */
    public int askNumberOfParticipants(){
        int numberOP;
        while (true){
            try {
                System.out.println("Введите количество участников:");
                numberOP = Integer.parseInt(userScanner.nextLine());
                if (fileMode) System.out.println(numberOP);
                eValidator.zeroAndLower(numberOP);
                break;
            }catch (NumberFormatException e){
                System.out.println("Данные введены неверно!");
            }catch (LessThanZeroException e){
                System.out.println("Значение должно быть больше нуля!");
            }
        }
        return numberOP;
    }

    /**
     * функция спрашивает у пользователя количество синглов
     * @return возвращает количество синглов
     */
    public Integer askSinglesCount(){
        Integer singlesCount = null;
        String singlesCountText;
        while (true){
            try {
                System.out.println("Введите количество синглов:");
                singlesCountText = userScanner.nextLine();
                if (fileMode) System.out.println(singlesCountText);
                if (singlesCountText.equals("")){
                    singlesCount = null;
                }else if (!singlesCountText.equals("")){
                    singlesCount = Integer.valueOf(singlesCountText);
                    eValidator.zeroAndLower(singlesCount);
                }
                break;
            }catch (NumberFormatException e){
                System.out.println("Данные введены неверно!");
            }catch (LessThanZeroException e){
                System.out.println("Значение должно быть больше нуля!");
            }
        }
        return singlesCount;
    }

    /**
     * функция спрашивает у пользователя описание музыкальной группы
     * @return возвращает описание объекта
     */
    public String askDescription(){
        String description;
        while (true){
            try {
                System.out.println("Введите описание группы:");
                description = userScanner.nextLine().trim();
                if (fileMode) System.out.println(description);
                if(description.equals("")){
                    return null;
                } else if(!description.equals("")){
                    return description;
                }
                break;
            }catch (NumberFormatException e){
                System.out.println("Данные введены неверно!");
            }
        }
        return description;
    }

    /**
     * функция спрашивает у пользователя жанр музыкальной группы
     * @return возвращает жанр
     */
    public MusicGenre askGenre(){
        String genreText;
        MusicGenre genre = null;
        while (true){
            try {
                System.out.println("Список доступных жанров: \r\n RAP \r\n BRIT_POP \r\n SOUL \r\n POST_PUNK");
                System.out.println("Введите жанр группы:");
                genreText = userScanner.nextLine();
                if (fileMode) System.out.println(genreText);
                if(genreText.equals("")){
                    genre = null;
                }if(!genreText.equals("")){
                    genre = MusicGenre.valueOf(genreText);
                }
                break;
            }catch (NumberFormatException e){
                System.out.println("Данные введены неверно!");
            }catch (IllegalArgumentException e){
                System.out.println("Данного жанра не существует!");
            }
        }
        return genre;
    }

    /**
     * функция спрашивает у пользователя название студии
     * @return возвращает название студии
     */
    public String askStudioName(){
        String studioName;
        while (true){
            try {
                System.out.println("Введите название студии:");
                studioName = userScanner.nextLine();
                if (fileMode) System.out.println(studioName);
                break;
            }catch (NumberFormatException e){
                System.out.println("Данные введены неверно!");
            }catch (NullPointerException e){
                return " ";
            }
        }
        return studioName;
    }

    /**
     * функция спрашивает у пользователя есть у музыкальной группы студия
     * @return возвращает студию
     */
    public Studio askStudio(){
        String text;
        Studio stud = null;
        while (true) {
            try {
                System.out.println("Есть ли у данной группы студия? (yes/no)");
                text = userScanner.nextLine();
                if (Objects.equals(text, "yes")) {
                    eValidator.emptyString(text);
                    String studioName = this.askStudioName();
                    stud = new Studio(studioName);
                } else if (Objects.equals(text, "no")) {
                    stud = null;
                }
                if(fileMode) System.out.println(text);
                break;
            } catch (MustBeNotEmptyException e) {
                System.out.println("Значение обязательно должно быть введено!");
            }catch (NullPointerException e){
                return null;
            }
        }return stud;
    }

    /**
     * функция спрашивает у пользователя уверен ли он в своих действиях
     * @return возвращает true, если да, и false, если нет
     */
    public boolean askQuestion(String question){
        String nextQuestion = question+(" yes/no ");
        String answer;
        while (true){
            try{
                System.out.println(nextQuestion);
                answer = userScanner.nextLine().trim();
                if (fileMode) System.out.println(answer);
                eValidator.emptyString(answer);
                break;
            }catch (NumberFormatException e){
                System.out.println("Данные введены неверно!");
            }catch (MustBeNotEmptyException e){
                System.out.println("Значение обязательно должно быть введено!");
            }
        }
        return answer.equals("yes") ? true:false;
    }
}
