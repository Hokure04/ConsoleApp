import commands.*;
import utility.*;
import java.io.IOException;
import java.util.Scanner;

/**
 * Класс ConsoleApp является main классом и служит для запуска консольного приложения
 */
class Main {
    public static void main(String[] args) throws IOException {
        try {
            ConsoleApp consoleApp = new ConsoleApp();
            consoleApp.start(args[0], args[1]);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Программе нужны аргументы");
        }
    }
}
//"C:\Users\Asus\Desktop\test.csv"
//"C:\Users\Asus\Desktop\result.csv"