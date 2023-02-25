import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Класс CollectionManager осуществляющий взаимодействие с коллекцией
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
    public MusicBand getLat(){
        return collection.getLast();
    }

    /**
     * функция получения объекта по его id
     * @param id
     * @return возвращает музыкальную группу, если объект был найден, в противном случае возвращает null
     */
    public MusicBand getById(int id){
        for (MusicBand mband : collection){
            if (mband.getId() == id) return mband;
        }
        return null;
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
     * функция удаляющая все объекты больше заданного
     * @param bandComparing музыкальная группа с которой идёт сравнение
     */
    public void removeGreater(MusicBand bandComparing){
        collection.removeIf(musicBand -> musicBand.compareTo(bandComparing) > 0);
    }

    /**
     * функция удаляющая все объекты меньше заданого
     * @param bandComparing музыкальная группа с которой идёт сравнение
     */
    public void removeLower(MusicBand bandComparing){
        collection.removeIf(musicBand -> bandComparing.compareTo(musicBand) > 0);
    }

    /**
     * функция удаляющая все объекты, количество участников котрых совпадает с тем что ввёд пользователь
     * @param i - количество участников, которое вводит пользователь
     */
    public void removeAllByNumberOfParticipants(int i){
        collection.removeIf(collection -> collection.getNumberOfParticipants() == i);
    }

    /**
     * функция считающая количество групп, у которых значение поля studio болше заданного
     * @param studio студия, которую вводит пользователь
     */
    public void countGreaterThanStudio(Studio studio){
        int i = 0;
        for(MusicBand mband : collection){
            if(mband.getStudioName().compareTo(studio.getName()) > studio.getName().compareTo(mband.getStudioName())){
                i += 1;
            }
        }
        System.out.println(i);
    }

    /**
     * функция которая печатает элементы коллекции в порядке возрастания
     */
    public void printAscending(){
        this.sortedCollection();
        for(MusicBand mband : collection){
            System.out.println(mband.getId() + " " + mband.getName() + " " + mband.getCoordinateX() + " " + mband.getCoordinateY() + " " + mband.getNumberOfParticipants() + " " + mband.getSinglesCount() + " " + mband.getDescription() + " " + mband.getGenre() + " " + mband.getStudioName());
        }
    }

    /**
     * функция, которая добавляет музыкальную группу в коллецию
     * @param mBand музыкальная группа, которую необходимо добавить
     */
    public void add(MusicBand mBand) {
        collection.add(mBand);
    }

    /**
     * функция которая удаляет музыкальную группу из коллекции
     * @param mband группа, которую необходимо удалить
     */
    public void remove(MusicBand mband){
        collection.remove(mband);
    }

    /**
     * функция, которая очищает коллекцию
     */
    public void clear(){
        collection.clear();
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
     * функция сохраняющая коллекцию в файл
     * @param fileManager мэнеджер файлов
     */
    public void saveCollection(FileManager fileManager){
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
        try {
            fileManager.save(collection);
            lastSaveTime = LocalDateTime.now();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * функция отображающая все элементы коллекции
     */
    public void show(){
        for (MusicBand mband : collection) {
            System.out.println(mband.getId() + " " + mband.getName() + " " + mband.getCoordinateX() + " " + mband.getCoordinateY() + " " + mband.getNumberOfParticipants() + " " + mband.getSinglesCount() + " " + mband.getDescription() + " " + mband.getGenre() + " " + mband.getStudioName());
        }
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
