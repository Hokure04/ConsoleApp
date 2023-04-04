import utility.*;
import java.io.IOException;

/**
 * Класс ConsoleApp является main классом и служит для запуска консольного приложения
 */
class Main {
    public static void main(String[] args) throws IOException {
        try {
            ConsoleApp consoleApp = new ConsoleApp();
            consoleApp.start(args[0]);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Вам необходимо из командной строки передать ссылку на файл");
        }
    }
}
//"C:\Users\Asus\Desktop\test.csv"
//"C:\Users\Asus\Desktop\result.csv"