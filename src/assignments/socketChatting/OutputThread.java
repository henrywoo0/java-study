package assignments.socketChatting;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class OutputThread extends Thread {

    private final Socket sc;
    private final Scanner scanner;

    public OutputThread(Socket sc) {
        this.sc = sc;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        try {
            OutputStream os = sc.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            while(true) {
                String msg = scanner.nextLine();
                pw.println(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.scanner.close();
        }
    }

}
