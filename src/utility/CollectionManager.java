package utility;

import java.time.LocalDateTime;
import java.util.*;
import data.*;

/**
 * Класс Utility.CollectionManager осуществляющий взаимодействие с коллекцией
 */
public class CollectionManager {
    /** поле коллекция - двусторонняя очередь содержащая объекты музыкальных групп */
    public static ArrayDeque<MusicBand> collection;
    /** поле время использования */
    private LocalDateTime useTime;
    /** поле время последнего сохранения */
    private LocalDateTime lastSaveTime;

    /**
     * Конструктор - создание мэнеджера коллекции
     * @param - коллекция
     * @param - время использования
     * @param - последнее время сохраненеия
     */
    public CollectionManager() {
        collection = new ArrayDeque<>();
        useTime = null;
        lastSaveTime = null;
    }

    /**
     * Функция получения коллекции
     * @return возвращает коллекцию музыкальных групп
     */
    public ArrayDeque<MusicBand> getCollection(){
        return collection;
    }

    /**
     * Функция получения времени использования
     * @return возвращает последнее время пользования
     */
    public LocalDateTime getUseTime(){
        return useTime;
    }

    /**
     * функция получения последнего времени сохранения коллекции
     * @return возвращает последнее время сохранения коллекции
     */
    public LocalDateTime getLastSaveTime(){
        return lastSaveTime;
    }

    /**
     * функция, которая узнаёт тип коллекции
     * @return возвращает тип коллекции
     */
    public String learnCollectionType(){
        String type = collection.getClass().getName();
        return type;
    }

    /**
     * функция, которая узнаёт размер коллекции
     * @return возвращает размер коллекции
     */
    public int learnCollectionSize(){
        int size = collection.size();
        return size;
    }

    /**
     * функция получения первого элемента коллекции
     * @return возвращает музыкальную группу
     */
    public MusicBand getFirst(){
        return collection.getFirst();
    }

    /**
     * функция получения последенего пэлемента коллекции
     * @return возвращает музыкальную группу
     */
    public MusicBand getLast(){
        return collection.getLast();
    }



    /**
     * функция получения объекта по количеству его участников
     * @param numberOfParticipants
     * @return возвращает музыкальную группу, если объект был найден, в противном случае возвращает null
     */
    public MusicBand getByNumberOfParticipants(int numberOfParticipants){
        for(MusicBand mband : collection){
            if(mband.getNumberOfParticipants() == numberOfParticipants) return mband;
        }
        return null;
    }

    /**
     * функция получения объекта по всем параметрам совпадающего с введённым пользователеем
     * @param band музыкальная группа введённая пользователем
     * @return возвращает музыкальную группу, если объект был найден, в противном случае возвращает null
     */
    public MusicBand getByValue(MusicBand band){
        for (MusicBand mband : collection){
            if(mband.getName().equals(band.getName())
                    && mband.getCoordinateX().equals(band.getCoordinateX())
                    && mband.getCoordinateY().equals(band.getCoordinateY())
                    && mband.getNumberOfParticipants() == band.getNumberOfParticipants()
                    && mband.getSinglesCount().equals(band.getSinglesCount())
                    && mband.getDescription().equals(band.getDescription())
                    && mband.getGenre().equals(band.getGenre())
                    && mband.getStudioName().equals(band.getStudioName())
            ){
                return mband;
            }
        }
        return null;
    }

    /**
     * функция получения студии объекта соответствующей введённой пользователем
     * @param studio студия введённая пользователем
     * @return возвращает музыкальную группу, если объект был найден, в противном случае возвращает null
     */
    public Studio getByStudioName(String studio){
        for(MusicBand mband : collection){
            if(mband.getStudioName().equals(studio)) return mband.getStudio();
        }
        return null;
    }

    /**
     * функция которая удаляет музыкальную группу из коллекции
     * @param mband группа, которую необходимо удалить
     */
    public void remove(MusicBand mband){
        collection.remove(mband);
    }

    /**
     * функция, которая создаёт id для нового элемента коллекции
     * @return возвращает новое id для объекта
     */
    public Integer generateNextId(){
        if(collection.isEmpty()) return 1;
        else return collection.getLast().getId()+1;
    }


    /**
     * функция сортирующая коолекцию
     * @return возвращает отсортированную коллекцию
     */
    public ArrayDeque<MusicBand> sortedCollection(){
        MusicBand[] m = collection.toArray(new MusicBand[0]);
        Arrays.sort(m);
        collection.clear();
        for (MusicBand band : m) {
            collection.add(band);
        }
        ArrayList<MusicBand> list = new ArrayList<>(collection);
        collection.clear();
        Collections.reverse(list);
        for (MusicBand mband : list){
            collection.add(mband);
        }
        return collection;
    }
}
