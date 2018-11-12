package de.esolutions.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ServerST {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
//        ArrayList<Socket> sockPool = new ArrayList<>(16);
        while (true) {
            new EchoService(serverSocket.accept()).run();
        }
    }


    static class EchoService {

        Socket sock;
        BufferedReader bReader;
        PrintWriter writer;

        //    @Override
        public void run() throws IOException {
            System.out.println("Thread::run() entry");
            if (bReader == null) return;
            try {
//            while (true) {
                String line = bReader.readLine();
                System.out.println(line);
                writer.println(line.toUpperCase());
                writer.flush();
//            }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Thread encountered I/O exception, terminating.");
            } finally {
                writer.close();
                bReader.close();
                sock.close();
            }
        }

        public EchoService(Socket sock) {
            System.out.println("Spawning new EchoService thread..");
            this.sock = sock;
            try {
                bReader = new BufferedReader(new InputStreamReader(this.sock.getInputStream(), StandardCharsets.UTF_8));
                writer = new PrintWriter(new OutputStreamWriter(this.sock.getOutputStream(), StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Thread encountered I/O exception, terminating.");
            }
        }
    }


}
