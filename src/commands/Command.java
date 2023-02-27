package commands;

/**
 * Интерфейс Commands.Command
 */
public interface Command {
    /** метод получения описаняи */
    String getDescription();

    /** метод получения имени */
    String getName();

    /** метод исполнения комманды */
    boolean execute(String argument);
}