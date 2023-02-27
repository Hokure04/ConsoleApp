import commands.*;
import utility.*;
import java.io.IOException;
import java.util.Scanner;

/**
 * Класс ConsoleApp является main классом и служит для запуска консольного приложения
 */
class Main {
    public static void main(String args[]) throws IOException {

         class ConsoleApp implements Runnable {
            @Override
            public void run() {
                try (Scanner scan = new Scanner(System.in)) {
                    if (args.length == 0) {
                        System.out.println("This program need an argument");
                    }

                    CollectionManager collectionManager = new CollectionManager();
                    Receiver receiver = new Receiver(collectionManager);
                    FileManager fileManager = new FileManager(args[0], args[1], collectionManager, receiver);
                    fileManager.read();
                    NegotiatorWithUser nGW = new NegotiatorWithUser(scan);

                    Invoker invoker = new Invoker(
                            new HelpCommand(),
                            new InfoCommand(collectionManager),
                            new ShowCommand(collectionManager, receiver),
                            new AddCommand(collectionManager, nGW, receiver),
                            new UpdateCommand(collectionManager, nGW, receiver),
                            new RemoveByIdCommand(collectionManager, nGW, receiver),
                            new ClearCommand(collectionManager, receiver),
                            new SaveCommand(collectionManager, fileManager, receiver),
                            new ExecuteScriptCommand(nGW),
                            new ExitCommand(),
                            new RemoveGreaterCommand(collectionManager, nGW, receiver),
                            new RemoveLowerCommand(collectionManager, nGW, receiver),
                            new HistoryCommand(collectionManager),
                            new RemoveAllByNumberOfParticipantsCommand(collectionManager, nGW, receiver),
                            new CountGreaterThanStudioCommand(collectionManager, nGW, receiver),
                            new PrintAscendingCommand(collectionManager, receiver)
                    );

                    Console console = new Console(invoker, scan, nGW);
                    console.start();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Не введенный значения файлов, для их ввода воспользуйтесь терминалом!");
                }
            }
        }
        Thread thread = new Thread(new ConsoleApp());
        thread.start();
    }
}
//"C:\Users\Asus\Desktop\test.csv"
//"C:\Users\Asus\Desktop\result.csv"