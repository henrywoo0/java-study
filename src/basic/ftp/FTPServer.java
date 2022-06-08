package basic.ftp;

import java.io.*;
import java.net.Socket;

public class FTPServer extends Thread {

    private Socket socket;
    private DataInputStream is;
    private DataOutputStream os;

    private static final String filefolder = "/Users/whitebear/Documents/server/";
    private static final int READING_SIZE = 20000;

    public FTPServer(Socket socket) {
        this.socket = socket;
        OutputStream os = null;
        BufferedOutputStream bos = null;
        InputStream is = null;
        BufferedInputStream bis = null;

        try {
            os = socket.getOutputStream();
            bos = new BufferedOutputStream(os);
            this.os = new DataOutputStream(bos);

            is = socket.getInputStream();
            bis = new BufferedInputStream(is);
            this.is = new DataInputStream(bis);

            System.out.println(socket.getLocalAddress() + ":" + socket.getLocalPort() + " 클라이언트 연결됨");
        } catch (Exception e) {
            System.out.println("Socket/stream 연결 실패");
            close();
        }
    }

    private void close() {
        try {
            is.close();
            os.close();
            socket.close();
        } catch (Exception e2) {
            System.out.println("close 실패");
        }
    }

    private void logClient(String message) {
        System.out.println(socket.getLocalAddress() + ":" + socket.getLocalPort() + " " + message);
    }

    private void sendMessage(String message) throws Exception {
        byte[] bytes = message.getBytes();

        os.writeLong(bytes.length);

        os.write(bytes);
        os.flush();
    }

    private String receiveMessage() throws Exception {
        int size = (int) is.readLong();

        byte[] bytes = new byte[size];
        is.read(bytes);

        return new String(bytes, 0, size).trim();
    }

    private boolean checkUser(String id, String pass) {
        return id.equals("admin") && pass.equals("1234");
    }

    private boolean login() throws LoginFailedException {
        try {
            String id = receiveMessage();
            String pass = receiveMessage();

            if(!checkUser(id, pass)) {
                sendMessage("-1");
                return false;
            } else {
                sendMessage("1");
                return true;
            }

        } catch (Exception e) {
            throw new LoginFailedException();
        }
    }

    private void sendFileList() throws FileTransferFailedException {
        try {
            File dir = new File(filefolder);
            File[] fileList = dir.listFiles();

            sendMessage(Integer.toString(fileList.length));

            for (File file : fileList) {
                sendMessage(file.getName());
                sendMessage((long)(file.length() / 1024.0) + "kb");
            }
        } catch (Exception e) {
            throw new FileTransferFailedException();
        }
    }

    private void receiveFile() throws FileTransferFailedException {
        try {
            if (receiveMessage() == "-1") {
                return;
            }

            String fileName = receiveMessage();
            File file = new File(filefolder + fileName);
            sendMessage(file.exists() ? "-1" : "1");
            String response = receiveMessage();

            if (response.equals("-1")) {
                return;
            }

            long size = Long.parseLong(receiveMessage());

            FileOutputStream fos = new FileOutputStream(filefolder + fileName);

            long totalBytes = 0;
            int readingSize = 0;
            byte[] bytes = new byte[READING_SIZE];

            while(totalBytes < size) {
                readingSize = is.read(bytes);
                fos.write(bytes, 0, readingSize);
                totalBytes += readingSize;
            }

            if(totalBytes != size) {
                File savedFile = new File(filefolder + fileName);
                savedFile.delete();
            }
        } catch (Exception e) {
            throw new FileTransferFailedException();
        }
    }

    private void sendFile() throws FileTransferFailedException {
        try {
            String fileName = receiveMessage();
            File file = new File(filefolder + fileName);
            if(!file.exists()) {
                sendMessage("-1");
                return;
            } else {
                sendMessage("1");
            }

            sendMessage(Long.toString(file.length()));

            FileInputStream fis = new FileInputStream(file);

            int readingSize = 0;
            byte[] bytes = new byte[READING_SIZE];

            while ((readingSize = fis.read(bytes)) != -1) {
                os.write(bytes, 0, readingSize);
            }
            os.flush();

        } catch (Exception e) {
            throw new FileTransferFailedException();
        }
    }

    @Override
    public void run() {
        try {
            while(true) {
                if(login()) {
                    break;
                }
            }

            logClient("로그인 성공");

            while(true) {
                int command = Integer.parseInt(receiveMessage());
                logClient(Integer.toString(command) + " 명령어 실행");
                if (command == 1) {
                    sendFileList();
                } else if (command == 2) {
                    receiveFile();
                } else if (command == 3) {
                    sendFile();
                } else if (command == 0) {
                    logClient("연결 종료");
                    break;
                }
            }

        } catch (LoginFailedException e) {
            System.out.println("클라이언트 로그인 처리 중 오류 발생");
        } catch (FileTransferFailedException e2) {
            System.out.println("파일 관련 처리 중 오류 발생");
        } catch (Exception e3) {
            System.out.println("오류 뱔생으로 인해 연결 끊김");
        } finally {
            close();
        }
    }
}
