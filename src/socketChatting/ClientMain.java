package socketChatting;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain {

    public static void main(String[] args) {

        try {
            Socket sc = new Socket("10.80.162.5", 6000);

            Thread inputThread = new InputThread(sc);
            Thread outputThread = new OutputThread(sc);

            inputThread.start();
            outputThread.start();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
