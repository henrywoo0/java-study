package socketChatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class InputThread extends Thread {

    private final Socket sc;

    public InputThread(Socket sc) {
        this.sc = sc;
    }

    public void run() {
        try {
            InputStream is = sc.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String msg = null;
            while(true) {
                msg = br.readLine();
                System.out.println(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
