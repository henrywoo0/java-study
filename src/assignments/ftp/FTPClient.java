package assignments.ftp;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class FTPClient {

    private Socket socket;
    private DataOutputStream os;
    private DataInputStream is;
    private Scanner scanner;

    private static final String HOST_IP = "127.0.0.1";
    private static final int HOST_PORT = 6000;
    private static final String DIRECTORY = "/Users/whitebear/Documents/client/";
    private static final int READING_SIZE = 20000;

    public FTPClient() {

        OutputStream os = null;
        BufferedOutputStream bos = null;
        InputStream is = null;
        BufferedInputStream bis = null;

        try {
            this.socket = new Socket(HOST_IP, HOST_PORT);
            this.socket.setSoTimeout(Integer.MAX_VALUE);

            os = socket.getOutputStream();
            bos = new BufferedOutputStream(os);
            this.os = new DataOutputStream(bos);

            is = socket.getInputStream();
            bis = new BufferedInputStream(is);
            this.is = new DataInputStream(bis);

            this.scanner = new Scanner(System.in);

            System.out.println("** 서버에 접속하였습니다. **");
        } catch (IOException e) {
            System.out.println("연결에 실패했습니다.");

            try {
                socket.close();
                scanner.close();
            } catch (Exception e2) {
                System.out.println("close에 실패했습니다.");
            }
        }
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

    private boolean login() throws LoginFailedException {
        try {
            System.out.print("ID: ");
            sendMessage(scanner.nextLine());
            System.out.print("PASS: ");
            sendMessage(scanner.nextLine());

            String result = receiveMessage();
            if(result.equals("1")) {
                System.out.println("** FTP 서버에 접속하였습니다. **");
                return true;
            }

            System.out.println("** ID 또는 PASS가 틀렸습니다.! **");
            return false;
        } catch (Exception e) {
            throw new LoginFailedException();
        }
    }

    private void uploadFile(String command) throws FileTransferFailedException {
        try {
            String path = command.substring(5);
            String changingFileName = null;

            for(int i = 0; i < path.length(); i++) {
                if(path.charAt(i) == ' ') {
                    changingFileName = path.substring(i + 1);
                    path = path.substring(0, i);
                    break;
                }
            }

            File file = new File(path);

            // 클라이언트 측 디렉토리에 해당 파일이 존재하지 않을 경우
            if(!file.exists()) {
                sendMessage("-1");
                System.out.println("** 해당하는 파일이 존재하지 않습니다. **");
                return;
            }
            sendMessage("1");

            String fileName = file.getName();

            if(changingFileName == null) {
                changingFileName = file.getName();
            }

            FileInputStream fis = new FileInputStream(file);

            sendMessage(changingFileName);

            String response = receiveMessage();

            // 파일명 중복일 경우
            if(response.equals("-1")) {
                System.out.print("파일이 이미 있습니다. 덮어쓰기 하실건가요??(Yes: 덮어쓰기 / No: 업로드 취소):");
                String input = scanner.nextLine();
                if(input.equals("Yes")) {
                    sendMessage("1");
                } else if (input.equals("No")) {
                    System.out.println("명령어를 다시 입력해주세요.");
                    sendMessage("-1");
                    return;
                } else {
                    System.out.println("잘못된 응답입니다. 덮어쓰기를 취소합니다.");
                    sendMessage("-1");
                    return;
                }
            } else {
                sendMessage("1");
            }

            sendMessage(Long.toString(file.length()));

            int readingSize = 0;
            byte[] bytes = new byte[READING_SIZE];

            while((readingSize = fis.read(bytes)) != -1) {
                os.write(bytes, 0, readingSize);
            }
            os.flush();

            System.out.println("** " + changingFileName + " 파일을 업로드하였습니다. **");

        } catch (Exception e) {
            throw new FileTransferFailedException();
        }
    }

    private void getFileList() throws FileTransferFailedException {
        try {
            int length = Integer.parseInt(receiveMessage());
            System.out.println("** File List **");
            for(int i = 0; i < length; i++) {
                System.out.print("** " + receiveMessage() + " ");
                System.out.println(receiveMessage() + " **");
            }
            System.out.println("** " + length + "개 파일 **");
        } catch(Exception e) {
            throw new FileTransferFailedException();
        }
    }

    private void downloadFile(String command) throws FileTransferFailedException {
        try {
            String fileName = command.substring(6);
            sendMessage(fileName);
            String result = receiveMessage();

            if(result.equals("-1")) {
                System.out.println("** 파일이 존재하지 않습니다. **");
                return;
            }

            long size = Long.parseLong(receiveMessage());

            FileOutputStream fos = new FileOutputStream(DIRECTORY + fileName);

            long totalBytes = 0;
            int readingSize = 0;
            byte[] bytes = new byte[READING_SIZE];

            while(totalBytes < size) {
                readingSize = is.read(bytes);
                fos.write(bytes, 0, readingSize);
                totalBytes += readingSize;
            }

            if(totalBytes != size) {
                File savedFile = new File(DIRECTORY + fileName);
                savedFile.delete();
                throw new FileTransferFailedException();
            } else {
                System.out.println("** " + fileName + "을 " + DIRECTORY + "로 다운로드 하였습니다. **");
            }

        } catch (Exception e) {
            throw new FileTransferFailedException();
        }
    }

    public void execute() {

        try {
            while(true) {
                if(login()) {
                    break;
                }
            }

            while(true) {
                String command = scanner.nextLine();
                if(command.startsWith("/파일목록")) {
                    sendMessage("1");
                    getFileList();
                } else if (command.startsWith("/업로드")) {
                    sendMessage("2");
                    uploadFile(command);
                } else if (command.startsWith("/다운로드")) {
                    sendMessage("3");
                    downloadFile(command);
                } else if (command.startsWith("/접속종료")) {
                    sendMessage("0");
                    System.out.println("** 접속을 종료합니다. **");
                    break;
                } else {
                    System.out.println("** 명령어를 다시 입력해주세요. **");
                }
            }

        } catch (LoginFailedException e) {
            System.out.println("로그인 시도 중 오류 발생");
        } catch (FileTransferFailedException e2) {
            System.out.println("파일 관련 처리 중 오류 발생");
        } catch (Exception e3) {
            System.out.println("오류 발생");
        } finally {
            try {
                socket.close();
                scanner.close();
            } catch (Exception e) {
                System.out.println("close 작업 중 오류 발생");
            }
        }
    }

    public static void main(String[] args) throws Exception {

        FTPClient ftpClient = new FTPClient();
        ftpClient.execute();
    }
}
