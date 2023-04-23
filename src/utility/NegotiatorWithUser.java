package utility;

import data.Coordinates;
import data.MusicGenre;
import data.Studio;
import exceptions.*;

import java.util.Scanner;

import static data.MusicGenre.NULL;

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
                eValidator.shieldSignException(name);
                break;
            }catch (NumberFormatException exception){
                System.out.println("Данные введены неверно! Введите приемлимое имя");
            }catch (MustBeNotEmptyException exception){
                System.out.println("Значение обязательно должно быть введено!");
            }catch (NullPointerException e){
                System.out.println("Не должно быть null. Пожалуйста, введите имя");
            }catch (ShieldSignException e){
                System.out.println("Нельзя использовать ; в названии музыкальной группы");
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
                System.out.println("Данные введены неверно! id обязательно должен быть числом");
            }catch (NullPointerException e){
                System.out.println("Значение обязательно должно быть введено! Пожалуйста введите id");
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
                eValidator.twoFourSevenAndGreater(x);
                break;
            }catch (NumberFormatException e){
                System.out.println("Данные введены неверно! Координата x обязательно должна быть числом");
            }catch (NullPointerException e){
                System.out.println("Значение обязательно должно быть введено! Пожалуйста, введите координату");
            }catch (ExceedingTheFormatException e){
                System.out.println("Значение превышает допустимое. Максимальное значение 247");
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
        String yText;
        while (true){
            try {
                System.out.println("Введите координату y:");
                yText = userScanner.nextLine();
                eValidator.exceed(yText);
                y = Integer.valueOf(yText);
                if (fileMode) System.out.println(y);
                break;
            }catch (NumberFormatException e){
                System.out.println("Данные введены неверно! Координата y обязательно должна являться целым числом");
            }catch (NullPointerException e){
                System.out.println("Значение обязательно должно быть введено! Пожалуйста введите координату");
            }catch (ExceedingTheFormatException e){
                System.out.println("Координата y не должна являться настолько длинным числом!");
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
        String numberOPText;
        while (true){
            try {
                System.out.println("Введите количество участников:");
                numberOPText = userScanner.nextLine();
                eValidator.exceed(numberOPText);
                numberOP = Integer.parseInt(numberOPText);
                if (fileMode) System.out.println(numberOP);
                eValidator.zeroAndLower(numberOP);
                break;
            }catch (NumberFormatException e){
                System.out.println("Данные введены неверно! Количество участников должно быть введено целым числом");
            }catch (LessThanZeroException e){
                System.out.println("Значение должно быть больше нуля!");
            } catch (ExceedingTheFormatException e) {
                System.out.println("Количество участников не должно быть настолько длинным числом!");
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
                eValidator.exceed(singlesCountText);
                if (fileMode) System.out.println(singlesCountText);
                singlesCount = Integer.valueOf(singlesCountText);
                eValidator.zeroAndLower(singlesCount);
                break;
            }catch (NumberFormatException e){
                System.out.println("Данные введены неверно! Количество синглов должно быть введено целым числом");
            }catch (LessThanZeroException e){
                System.out.println("Значение должно быть больше нуля!");
            }catch (ExceedingTheFormatException e){
                System.out.println("Количество участников не должно являться настолько длинным числом!");
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
                eValidator.shieldSignException(description);
                if (fileMode) System.out.println(description);
                if(description.equals("")){
                    return "there is no description";
                } else if(!description.equals("")){
                    return description;
                }
                break;
            }catch (NumberFormatException e){
                System.out.println("Данные введены неверно! пожалуйста, введите приемлимое описание");
            }catch (ShieldSignException e){
                System.out.println("Нельзя использовать ; в описании группы");
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
                    genre = NULL;
                }if(!genreText.equals("")){
                    genre = MusicGenre.valueOf(genreText);
                }
                break;
            }catch (NumberFormatException e){
                System.out.println("Данные введены неверно! Пожалуйста введите жанр из списка");
            }catch (IllegalArgumentException e){
                System.out.println("Данного жанра не существует! Пожалуйста введите жанр и списка");
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
                eValidator.emptyString(studioName);
                eValidator.shieldSignException(studioName);
                if (fileMode) System.out.println(studioName);
                break;
            }catch (NumberFormatException e){
                System.out.println("Данные введены неверно! Пожалуйста введите приемлимое название студии");
            }catch (NullPointerException e){
                System.out.println("Недопустимое значение");
            }catch (MustBeNotEmptyException e){
                return "no studio name";
            }catch (ShieldSignException e){
                System.out.println("Нельзя использовать ; в названии студии");
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
        Studio stud;
        while (true) {
            try {
                System.out.println();
                String studioName = this.askStudioName();
                stud = new Studio(studioName);
                break;
            }catch (NullPointerException e){
                System.out.println("Значение обязательно должно быть введено! Пожалуйста выберите один из вариантов");
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
                eValidator.rightAnswer(answer);
                break;
            }catch (NumberFormatException e){
                System.out.println("Данные введены неверно! Вы должны ответить (yes/no)");
            }catch (MustBeNotEmptyException e){
                System.out.println("Значение обязательно должно быть введено!");
            }catch (OnlyNoOrYesException e){
                System.out.println("Ответ на вопрос должен быть только yes или no!");
            }
        }
        return answer.equals("yes") ? true:false;
    }
}
