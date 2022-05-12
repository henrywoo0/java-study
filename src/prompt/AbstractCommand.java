package prompt;

import java.io.File;

public abstract class AbstractCommand {

    protected final File currentDirectory;

    protected final String commandLine;

    public AbstractCommand(File currentDirectory, String commandLine) {
        this.currentDirectory = currentDirectory;
        this.commandLine = commandLine;
    }

    public boolean isExitCondition() {
        return false;
    }

    public abstract File executeCommand();

    public static AbstractCommand build(File currentDirectory, String line)
            throws UnknownCommandException {
        if (line.startsWith("cd")) {
            return new CommandCd(currentDirectory, line);
        } else if (line.startsWith("ls")) {
            return new CommandLs(currentDirectory, line);
        } else if (line.startsWith("quit")) {
            return new CommandExit(currentDirectory, line);
        } else {
            throw new UnknownCommandException();
        }
    }

    public static void main(String[] args) {
        SimpleCommander commander = new SimpleCommander();
        commander.execute();
    }

}
