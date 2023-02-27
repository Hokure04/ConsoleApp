package utility;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import data.*;

/**
 * Класс Utility.FileManager осуществляющий работу с файлом
 */
public class FileManager {
    /** поле имя первого файла */
    private String filename1;
    /** поле имя второго файла */
    private String filename2;
    /** поле мэнеджер коллекции */
    private final CollectionManager collectionManager;
    private Receiver receiver;
    public static int j = 0;


    /**
     * Конструктор создающий объект Utility.FileManager
     * @param filename1 имя первого файла
     * @param filename2 имя второго файла
     * @param coolM мэнеджер коллекции
     */
    public FileManager(String filename1, String filename2, CollectionManager coolM,Receiver receiver){
        this.filename1 = filename1;
        this.filename2 = filename2;
        this.collectionManager = coolM;
        this.receiver = receiver;
    }

    /**
     * функция считывает данные и файла в csv формате и конвертирует их в обычный формат представления данных
     */
    public void read() {

        try {
            Path path = Paths.get(filename1);
            Scanner sc = new Scanner(path);
            sc.useDelimiter("\\r\\n");
            while (sc.hasNext()) {
                Scanner scanner = new Scanner(sc.next());
                scanner.useDelimiter(",");
                String str;
                List<String> listOfStrings = new ArrayList<String>();
                while (scanner.hasNext()) {
                    str = scanner.next();
                    listOfStrings.add(str);
                }
                scanner.close();
                String[] array = listOfStrings.toArray(new String[0]);
                List<String> list = new ArrayList<>();
                for (int i = 0; i < array.length; i++) {
                    list.add(array[i]);
                }
                objectCreator(list);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * функция собирает все данные полученные после выполнения метода read() и обирает из них объект с типом Data.MusicBand
     * @param list список в котором хранятся все данные, полученные при выполнении метода read()
     */
    private  void objectCreator(List<String> list){
        j++;
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
                    Integer.parseInt(birthday[2]), Integer.parseInt(birthday[1]),Integer.parseInt(birthday[0]),0,0
            );
            MusicBand mband = new MusicBand(id, name, coordinates, participants,singlesCount,description,genre, studio,localDateTime);
            receiver.add(mband);
            collectionManager.sortedCollection();
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * функция сохраняет коллекцию в файл
     * @param collection колекция из объектов Data.MusicBand
     * @throws IOException
     */
    public void save(ArrayDeque<MusicBand> collection) throws IOException {
        try(OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(filename2))){
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
                String line = String.valueOf(id)+","+name+","+String.valueOf(x)+","+String.valueOf(y)+","+String.valueOf(participants)+","+String.valueOf(singlesCount)+","+description+","+String.valueOf(genre)+","+studio+","+String.valueOf(ldT)+"\n";
                outputStreamWriter.write(line);
            }
        }catch (FileNotFoundException e){
            System.out.println("Файл не найден, исправьте эту ошибку и возвращайтесь :)");
        }
    }
}
