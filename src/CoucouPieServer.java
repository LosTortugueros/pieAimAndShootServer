import jline.ConsoleReader;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by root on 05/12/14.
 */
public class CoucouPieServer {
    private static DatagramSocket socket;
    private static boolean alive = true;
    private static DatagramPacket packet;
    private static Scanner reader;
    private InputThread thread;

    public CoucouPieServer(){
        try {
            socket = new DatagramSocket();
            socket.setBroadcast(true);
            packet = new DatagramPacket("tarace".getBytes(), 6,InetAddress.getByName("255.255.255.255"), 44242);
            thread = new InputThread(this);
            thread.start();



        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void send() {
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] params){
        CoucouPieServer server = new CoucouPieServer();
        DataInputStream dis = new DataInputStream(System.in);
        char toto = 'a';
        ConsoleReader consoleReader = null;
        try {
            consoleReader = new ConsoleReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(alive){
                //System.out.println(server.getNextChar());
            int c = 0;
            try {
                c = consoleReader.readVirtualKey();
            } catch (IOException e) {
                e.printStackTrace();
            }
            send();
            System.out.println((char)c + " omg");

/* String s = dis.readLine();
 * DataInputStream.readLine() has been deprecated
 * to get String value you need to use BufferedReader class
 */

        }
        socket.close();

    }

    public void print(String s) {
        System.out.println(s);
    }
}
