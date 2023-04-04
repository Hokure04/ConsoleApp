package utility;

import data.Coordinates;
import data.MusicBand;
import data.MusicGenre;
import data.Studio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;


/**
 * Класс Utility.FileManager осуществляющий работу с файлом
 */
public class FileManager {
    /** поле имя первого файла */
    private String filename;
    /** поле мэнеджер коллекции */
    private final CollectionManager collectionManager;
    private Receiver receiver;



    /**
     * Конструктор создающий объект Utility.FileManager
     * @param filename имя первого файла
     * @param coolM мэнеджер коллекции
     */
    public FileManager(String filename,CollectionManager coolM,Receiver receiver){
        this.filename = filename;
        this.collectionManager = coolM;
        this.receiver = receiver;
    }

    /**
     * функция считывает данные и файла в csv формате и конвертирует их в обычный формат представления данных
     */
    public void read(String filename) {
        try {
            Path path = Paths.get(filename);
            List<List<String>> data = new ArrayList<>();
            Scanner scanner = new Scanner(path);
            while (scanner.hasNextLine()){
                List<String> lineData = Arrays.asList(scanner.nextLine().split(","));
                data.add(lineData);
            }
            for(List<String> list : data){
                objectCreator(list);
            }
            scanner.close();
        }catch (NullPointerException e){
            System.out.println("В файле находиться недопустимое значение");
        } catch (IOException e) {
            System.out.println("Неизвестная ошибка");
        }
    }

    /**
     * функция собирает все данные полученные после выполнения метода read() и обирает из них объект с типом Data.MusicBand
     * @param list список в котором хранятся все данные, полученные при выполнении метода read()
     */
    private  void objectCreator(List<String> list){
        try{
            Integer id = Integer.valueOf(list.get(0));
            String name = list.get(1);
            Float coordinateX = Float.valueOf(list.get(2));
            Integer coordinateY = Integer.valueOf(list.get(3));
            Coordinates coordinates = new Coordinates(coordinateX, coordinateY);
            int participants = Integer.parseInt(list.get(4));
            Integer singlesCount = Integer.valueOf(list.get(5));
            String description = list.get(6);
            MusicGenre genre = MusicGenre.valueOf(list.get(7));
            String studioName = list.get(8);
            Studio studio = new Studio(studioName);
            String[] birthday = list.get(9).split("-");
            LocalDateTime localDateTime = LocalDateTime.of(
                    Integer.parseInt(birthday[0]), Integer.parseInt(birthday[1]),Integer.parseInt(birthday[2]),0,0
            );
            MusicBand mband = new MusicBand(id, name, coordinates, participants,singlesCount,description,genre, studio,localDateTime);
            receiver.add(mband);
            collectionManager.sortedCollection();
        } catch (NumberFormatException e) {
            System.out.println("В файле находиться недопустимое значение");
        }
    }

    /**
     * функция сохраняет коллекцию в файл
     * @param collection колекция из объектов Data.MusicBand
     * @throws IOException
     */
    public void save(ArrayDeque<MusicBand> collection) throws IOException {
        try(OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(filename))){
            for(MusicBand i : collection){
                Integer id = i.getId();
                String name = i.getName();
                Float x = i.getCoordinateX();
                Integer y = i.getCoordinateY();
                int participants = i.getNumberOfParticipants();
                Integer singlesCount = i.getSinglesCount();
                String description = i.getDescription();
                MusicGenre genre = i.getGenre();
                String studio = i.getStudioName();
                LocalDateTime ldT = i.getCreationDate();
                String stringDate = ldT.toString().substring(0,10);
                String line = String.valueOf(id)+","+name+","+String.valueOf(x)+","+String.valueOf(y)+","+String.valueOf(participants)+","+String.valueOf(singlesCount)+","+description+","+String.valueOf(genre)+","+studio+","+String.valueOf(stringDate)+"\n";
                outputStreamWriter.write(line);
            }
        }catch (FileNotFoundException e){
            System.out.println("Файл не найден, создайте файл или если вы открыли файл, закройте его для того, чтобы программа могла с ним работать:)");
        }
    }

}
