package assignments.socketChatting;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    public static void main(String[] args) {

        try {
            ServerSocket ss = new ServerSocket(6000);

            Socket sc = ss.accept();

            Thread inputThread = new InputThread(sc);
            Thread outputThread = new OutputThread(sc);

            inputThread.start();
            outputThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
