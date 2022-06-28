package assignments.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientMain {

    public static void main(String[] args) throws IOException {

        InetAddress inetAddress= InetAddress.getByName("255.255.255.255");

        DatagramSocket ds = new DatagramSocket();

        for(int i = 0; i <= 1000; i++) {
            String str = Integer.toString(i);
            DatagramPacket sendPacket = new DatagramPacket(str.getBytes(), str.getBytes().length, inetAddress, 9999);
            ds.send(sendPacket);
        }
    }
}
