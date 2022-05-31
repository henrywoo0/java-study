package assignments.prompt;

import java.io.File;
import java.text.SimpleDateFormat;

public class CommandLs extends AbstractCommand {

    public CommandLs(File currentDirectory, String commandLine) {
        super(currentDirectory, commandLine);
    }

    @Override
    public File executeCommand() {

        File dir = currentDirectory;

        if(!dir.exists()) {
            System.out.println("cannot find the path");
            return dir;
        }

        File[] listFiles = dir.listFiles();

        assert listFiles != null;
        for (File file : listFiles) {
            printFileInfo(file);
        }

        return dir;
    }

    private void printFileInfo(File file) {
        System.out.print(getFormattedDate(file.lastModified()) + " ");
        System.out.printf("%-7s", file.isDirectory() ? "<DIR>" : "");
        System.out.printf("%6s ", file.isFile() ? getFormattedSize(file.length()) : "");
        System.out.print(file.getName());
        System.out.println();
    }

    private String getFormattedDate(long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(time);
    }

    private String getFormattedSize(long size) {
        if (size > 1024 * 1024 * 1024) {
            return (size / 1024 / 1024 / 1024) + "G";
        } else if (size > 1024 * 1024) {
            return (size / 1024 / 1024) + "M";
        } else if (size > 1024) {
            return (size / 1024) + "K";
        } else {
            return Long.toString(size);
        }
    }

}