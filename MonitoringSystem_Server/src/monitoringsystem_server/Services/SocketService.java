/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitoringsystem_server.Services;

import Models.LogAction;
import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import monitoringsystem_server.ServiceThread;

/**
 *
 * @author nghiadx
 */
public class SocketService {

    public static void Run(JTextPane tpLog, List lClient, ArrayList ClientList,
            ArrayList<LogAction> logList, DefaultTableModel modelTable) throws IOException {
        ServerSocket listener = null;
        DataInputStream ournewDataInputstream;
        DataOutputStream ournewDataOutputstream;

        tpLog.setText("Server is waiting to accept user...");
        int clientNumber = 0;

        // Mở một ServerSocket tại cổng 7777.
        // Chú ý bạn không thể chọn cổng nhỏ hơn 1023 nếu không là người dùng
        // đặc quyền (privileged users (root)).
        try {
            listener = new ServerSocket(8888);

        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }

        try {
            while (true) {

                // Chấp nhận một yêu cầu kết nối từ phía Client.
                // Đồng thời nhận được một đối tượng Socket tại server.
                Socket socketOfServer = listener.accept();

                ServiceThread ser = new ServiceThread(socketOfServer, clientNumber++, logList, modelTable);
                ser.start();

                //update UI
                logList = ser.logList;
                modelTable = ser.modelTable;

                String clientName = "client" + clientNumber++;
                String ip = socketOfServer.getInetAddress() + "/" + socketOfServer.getPort();

                tpLog.setText(tpLog.getText() + "\nNew " + clientName + ": " + ip);
                ClientList.add(ip + ":" + clientName);
                lClient.add(ip + ":" + clientName);
            }
        } finally {
            listener.close();
        }
    }
}
