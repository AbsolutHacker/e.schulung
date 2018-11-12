package de.esolutions.net;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client extends Thread {
    public void run() {
        try {
            Socket sock = new Socket("localhost", 8000);
            BufferedWriter osw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream(), StandardCharsets.UTF_8));
            BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream(), StandardCharsets.UTF_8));
            osw.write("desd\n");
            osw.flush();
            System.out.println(br.readLine());
            sock.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final int N = 2048;

    public static void main(String[] args)  {
        for (int i = 0; i < N; i++) {
            new Client().start();
        }
    }

}
