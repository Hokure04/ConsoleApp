import java.io.IOException;
import java.util.Scanner;

/**
 * Класс ConsoleApp является main классом и служит для запуска консольного приложения
 */
class ConsoleApp {
    public static void main(String args[]) throws IOException {
        try (Scanner scan = new Scanner(System.in)) {

            if (args.length == 0) {
                System.out.println("This program need an argument");
            }

            CollectionManager collectionManager = new CollectionManager();
            FileManager fileManager = new FileManager(args[0], args[1], collectionManager);
            fileManager.read();
            NegotiatorWithUSer nGW = new NegotiatorWithUSer(scan);
            CommandManager commandManager = new CommandManager(
                    (Command) new HelpCommand(),
                    new InfoCommand(collectionManager),
                    new ShowCommand(collectionManager),
                    new AddCommand(collectionManager, nGW),
                    new UpdateCommand(collectionManager, nGW),
                    new RemoveByIdCommand(collectionManager, nGW),
                    new ClearCommand(collectionManager, nGW),
                    new SaveCommand(collectionManager, fileManager),
                    new ExecuteScriptCommand(nGW),
                    new ExitCommand(),
                    new RemoveGreaterCommand(collectionManager, nGW),
                    new RemoveLowerCommand(collectionManager, nGW),
                    new HistoryCommand(collectionManager),
                    new RemoveAllByNumberOfParticipantsCommand(collectionManager, nGW),
                    new CountGreaterThanStudioCommand(collectionManager, nGW),
                    new PrintAscendingCommand(collectionManager)
            );
            Console console = new Console(commandManager, scan, nGW);
            console.start();
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Не введенный значения файлов, для их ввода воспользуйтесь терминалом!");
        }
    }
}
//"C:\Users\Asus\Desktop\test.csv"
//"C:\Users\Asus\Desktop\result.csv"