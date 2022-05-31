package assignments.prompt;

import java.io.File;

public class CommandCd extends AbstractCommand {

    public CommandCd(File currentDirectory, String commandLine) {
        super(currentDirectory, commandLine);
    }

    @Override
    public File executeCommand() {
        String currentPath = this.currentDirectory.getAbsolutePath();
        String changingPath = this.commandLine.substring(3);

        if (changingPath.equals("..")) {
            for(int i = currentPath.length() - 1; i >= 0; i--) {
                char ch = currentPath.charAt(i);
                if(ch == '/') {
                    currentPath = currentPath.substring(0, i);
                    break;
                }
            }
        } else if (changingPath.startsWith("./")) {
            currentPath += changingPath.substring(1);
        } else {
            currentPath += "/" + changingPath;
        }

        if(!checkExists(currentPath)) {
            System.out.println("cannot find path");
            currentPath = this.currentDirectory.getAbsolutePath();
        }

        return new File(currentPath);
    }

    private boolean checkExists(String path) {
        File file = new File(path);
        return file.exists();
    }


}
