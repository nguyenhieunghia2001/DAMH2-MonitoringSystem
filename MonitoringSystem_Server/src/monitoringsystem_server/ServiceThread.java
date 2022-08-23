/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitoringsystem_server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author nghiadx
 */
public class ServiceThread extends Thread {

    private int clientNumber;
    private Socket socketOfServer;

    public ServiceThread(Socket socketOfServer, int clientNumber) {
        this.clientNumber = clientNumber;
        this.socketOfServer = socketOfServer;

        // Log
        System.out.println("New connection with client# " + this.clientNumber + " at " + socketOfServer);
    }

    @Override
    public void run() {
        try {
            // Mở luồng vào ra trên Socket tại Server.
            BufferedReader is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));

            while (true) {
                // Đọc dữ liệu tới server (Do client gửi tới).
                String line = is.readLine();

                // Ghi vào luồng đầu ra của Socket tại Server.
                // (Nghĩa là gửi tới Client).
                os.write(">> " + line);
                // Kết thúc dòng
                os.newLine();
                // Đẩy dữ liệu đi
                os.flush();

                // Nếu người dùng gửi tới QUIT (Muốn kết thúc trò chuyện).
                if (line.equals("QUIT")) {
                    os.write(">> OK");
                    os.newLine();
                    os.flush();
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
