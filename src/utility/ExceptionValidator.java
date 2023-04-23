package utility;

import data.MusicBand;
import data.Studio;
import exceptions.*;


public class ExceptionValidator {
    public CollectionManager collectionManager;

    public ExceptionValidator(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void nullCollection(CollectionManager collectionManager) throws NothingInTheCollectionException {
        if (collectionManager.learnCollectionSize() == 0) throw new NothingInTheCollectionException();
    }

    public void zeroAndLower(int i) throws LessThanZeroException {
        if (i <= 0) throw new LessThanZeroException();
    }

    public void twoFourSevenAndGreater(float i) throws ExceedingTheFormatException {
        if(i>247) throw new ExceedingTheFormatException();
    }

    public void exceed(String number) throws ExceedingTheFormatException{
        if(number.matches(".*\\d.*")){
            if(number.length()>9) throw new ExceedingTheFormatException();
        }
    }


    public void shieldSignException(String text) throws ShieldSignException{
        if(text.contains(";")) throw new  ShieldSignException();
    }

    public void emptyString(String isEmpty) throws MustBeNotEmptyException {
        if (isEmpty.equals("")) throw new MustBeNotEmptyException();
    }

    public void doesntExist(MusicBand musicBand) throws MusicBandDoesNotExistException {
        if (musicBand == null) throw new MusicBandDoesNotExistException();
    }

    public void studioDoesntExist(Studio studio) throws StudioDoesNotExistException {
        if (studio == null) throw new StudioDoesNotExistException();
    }

    public void emptyHistory(String name) throws HistoryIsEmptyException {
        if (!name.isEmpty()) throw new HistoryIsEmptyException();
    }

    public void emptyHistory(Integer length) throws HistoryIsEmptyException {
        if (length == 0) throw new HistoryIsEmptyException();
    }

    public void noArgument(String argument) throws IncorrectlyInstalledElement {
        if (!argument.isEmpty()) throw new IncorrectlyInstalledElement();
    }

    public void argument(String argument) throws IncorrectlyInstalledElement {
        if (argument.isEmpty()) throw new IncorrectlyInstalledElement();
    }

    public void findDuplicate() throws DuplicateIdException{
        if(!collectionManager.areAllDistinct()) throw new DuplicateIdException();
    }

    public void rightAnswer(String argument) throws OnlyNoOrYesException{
        if(!(argument.equals("yes")||argument.equals("no"))) throw new OnlyNoOrYesException();
    }
}
