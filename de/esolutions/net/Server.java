package de.esolutions.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;

public class Server {

    private ServerSocket listenSocket;
//    private ExecutorService threadPool;

    public Server(int port) throws IOException {
        listenSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        while (true) {
            new EchoService(listenSocket.accept()).start();
        }
    }

    public static void main(String[] args) throws IOException {
        new Server(8000).start();
    }

}

class EchoService extends Thread {

    Socket sock;
    BufferedReader bReader;
    PrintWriter writer;

    @Override
    public void run() {
        if (bReader == null) return;
//        System.out.println("Enter worker thread" + getName());
        try {
            String line = bReader.readLine();
            if (!(line == null || line.isEmpty())) {
//                System.out.println("<< " + line);
                line = line.toUpperCase();
//                System.out.println(">> " + line);
                writer.println(line.toUpperCase());
                writer.flush();
            }
            writer.close();
            bReader.close();
            sock.close();
//            System.out.println("Exit worker thread" + getName());
        } catch (IOException ioExc) {
            ioExc.printStackTrace();
            System.out.println("Thread encountered I/O exception, terminating.");
        }
    }

    public EchoService(Socket sock) {
//        System.out.println("New worker thread" + getName());
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

