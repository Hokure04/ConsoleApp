package utility;

import commands.*;
import java.util.Scanner;

public class ConsoleApp {
    public void start(String args1) {
        try (Scanner scan = new Scanner(System.in)) {
            if (args1 == null) {
                System.out.println("Пожалуйста передайте ссылку на файл через командную строку");
            }

            CollectionManager collectionManager = new CollectionManager();
            Receiver receiver = new Receiver(collectionManager);
            FileManager fileManager = new FileManager(args1,collectionManager, receiver);
            fileManager.read(args1);
            ExceptionValidator eValidator = new ExceptionValidator(collectionManager);
            NegotiatorWithUser nGW = new NegotiatorWithUser(scan, eValidator);


            Invoker invoker = new Invoker(
                    new HelpCommand(eValidator),
                    new InfoCommand(collectionManager, eValidator),
                    new ShowCommand(collectionManager, receiver, eValidator),
                    new AddCommand(collectionManager, nGW, receiver, eValidator),
                    new UpdateCommand(collectionManager, nGW, receiver, eValidator),
                    new RemoveByIdCommand(collectionManager, nGW, receiver, eValidator),
                    new ClearCommand(collectionManager, receiver, eValidator),
                    new SaveCommand(collectionManager, fileManager, receiver, eValidator),
                    new ExecuteScriptCommand(nGW, eValidator),
                    new ExitCommand(eValidator),
                    new RemoveGreaterCommand(collectionManager, nGW, receiver, eValidator),
                    new RemoveLowerCommand(collectionManager, nGW, receiver, eValidator),
                    new HistoryCommand(eValidator, collectionManager),
                    new RemoveAllByNumberOfParticipantsCommand(collectionManager, nGW, receiver, eValidator),
                    new CountGreaterThanStudioCommand(collectionManager, nGW, receiver, eValidator),
                    new PrintAscendingCommand(collectionManager, receiver, eValidator)
            );

            Console console = new Console(invoker, scan, nGW);
            console.start();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Не введенный значения файлов, для их ввода воспользуйтесь терминалом!");
        }
    }
}
