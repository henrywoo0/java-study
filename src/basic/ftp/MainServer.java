package basic.ftp;

import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

    private static final int PORT = 6000;

    public static void main(String[] args) throws Exception {

        ServerSocket ss = null;

        try {
            ss = new ServerSocket(PORT);
            System.out.println("서버 시작됨");

            while (true) {
                Socket sc = ss.accept();
                Thread ftpServer = new FTPServer(sc);
                ftpServer.start();
            }
        } catch (Exception e) {
            System.out.println("ServerSocket 연결 실패");
            try {
                ss.close();
            } catch (Exception e2) {
                System.out.println("close 실패");
            }
        }
    }
}
