package utility;

import data.MusicBand;
import data.Studio;
import exceptions.NoAccessException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Receiver {
    private CollectionManager collectionManager;
    /** поле время последнего сохранения */
    private LocalDateTime lastSaveTime;
    private LocalDateTime lastUseTime;

    public Receiver(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }


    /**
     * функция, которая добавляет музыкальную группу в коллецию
     * @param mBand музыкальная группа, которую необходимо добавить
     */
    public void add(MusicBand mBand) {
        collectionManager.getCollection().add(mBand);
    }

    /**
     * функция отображающая все элементы коллекции
     */

    public void show(){
        try {
            if (FileManager.accessibility == 1) throw new NoAccessException();
            System.out.printf("%1s %16s %15s %17s %23s %16s %25s %11s %16s", "id", "Music Band Name", "Coordinate x", "Coordinate y", "Number of participants", "Single Count", "Description", "Genre", "Studio");
            System.out.println();
            for (MusicBand mband : CollectionManager.collection) {
                System.out.printf("%1s %16s %15s %17s %15s %25s %24s %11s %16s", mband.getId(), mband.getName(), mband.getCoordinateX(), mband.getCoordinateY(), mband.getNumberOfParticipants(), mband.getSinglesCount(), mband.getDescription(), mband.getGenre(), mband.getStudioName());
                System.out.println();
            }
        }catch (NoAccessException e){
            System.out.println("Невозможно прочитать содержимое файла, так как данный файл недоступен");
        }
    }

    /**
     * функция, которая очищает коллекцию
     */
    public void clear(){
        collectionManager.getCollection().clear();
    }

    /**
     * функция удаляющая все объекты больше заданного
     * @param bandComparing музыкальная группа с которой идёт сравнение
     */
    public void removeGreater(MusicBand bandComparing){
        collectionManager.getCollection().removeIf(musicBand -> musicBand.compareTo(bandComparing) > 0);
    }

    /**
     * функция удаляющая все объекты меньше заданого
     * @param bandComparing музыкальная группа с которой идёт сравнение
     */
    public void removeLower(MusicBand bandComparing){
        collectionManager.getCollection().removeIf(musicBand -> bandComparing.compareTo(musicBand) > 0);
    }

    /**
     * функция удаляющая все объекты, количество участников котрых совпадает с тем что ввёд пользователь
     * @param i - количество участников, которое вводит пользователь
     */
    public void removeAllByNumberOfParticipants(int i){
        collectionManager.getCollection().removeIf(collection -> collection.getNumberOfParticipants() == i);
    }

    /**
     * функция считающая количество групп, у которых значение поля studio болше заданного
     * @param studio студия, которую вводит пользователь
     */
    public void countGreaterThanStudio(Studio studio){
            int i = 0;
            try {
                for (MusicBand mband : CollectionManager.collection) {
                    if (mband.getStudioName().compareTo(studio.getName()) > studio.getName().compareTo(mband.getStudioName())) {
                        if (studio.getName() == null) {
                            System.out.println("Невозможно осуществить сравнение с null элементом");
                        } else {
                            i += 1;
                        }
                    }
                }
            }catch (NullPointerException e){
                System.out.println("Нельзя сравнивать с null значением");
            }
            System.out.println(i);
    }

    /**
     * функция которая печатает элементы коллекции в порядке возрастания
     */
    public void printAscending(){
        this.collectionManager.sortedCollection();
        System.out.printf("%1s %16s %15s %17s %23s %16s %25s %11s %16s", "id", "Music Band Name", "Coordinate x", "Coordinate y", "Number of participants", "Single Count", "Description", "Genre", "Studio");
        System.out.println();
        for(MusicBand mband : collectionManager.collection){
            System.out.printf("%1s %16s %15s %17s %15s %25s %24s %11s %16s", mband.getId(), mband.getName(), mband.getCoordinateX(), mband.getCoordinateY(), mband.getNumberOfParticipants(), mband.getSinglesCount(), mband.getDescription(), mband.getGenre(), mband.getStudioName());
            System.out.println();
        }
    }

    /**
     * функция сохраняющая коллекцию в файл
     * @param fileManager мэнеджер файлов
     */
    public void saveCollection(FileManager fileManager){
        MusicBand[] m = collectionManager.getCollection().toArray(new MusicBand[0]);
        Arrays.sort(m);
        collectionManager.getCollection().clear();
        for (MusicBand band : m) {
            collectionManager.getCollection().add(band);
        }
        ArrayList<MusicBand> list = new ArrayList<>(collectionManager.getCollection());
        collectionManager.getCollection().clear();
        Collections.reverse(list);
        for (MusicBand mband : list){
            collectionManager.getCollection().add(mband);
        }
        lastUseTime = LocalDateTime.now();
        try {
            fileManager.save(collectionManager.getCollection());
            lastSaveTime = LocalDateTime.now();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * функция получения объекта по его id
     * @param id
     * @return возвращает музыкальную группу, если объект был найден, в противном случае возвращает null
     */
    public MusicBand getById(int id){
        for (MusicBand mband : collectionManager.collection){
            if (mband.getId() == id) return mband;
        }
        return null;
    }
}
