/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitoringsystem_server;

import Models.LogAction;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import monitoringsystem_server.Services.FileDAO;
import monitoringsystem_server.Services.TableDAO;

/**
 *
 * @author nghiadx
 */
public class ServiceThread extends Thread {

    private int clientNumber;
    private Socket socketOfServer;
    private FileDAO fileDAO;
    private String FILENAME_LOG = "LogAction.txt";
    DateFormat dateFormat;
    public ArrayList<LogAction> logList;
    public DefaultTableModel modelTable;
    public TableDAO tableDAO;

    public ServiceThread(Socket socketOfServer, int clientNumber, ArrayList<LogAction> logList, DefaultTableModel modelTable) {
        this.clientNumber = clientNumber;
        this.socketOfServer = socketOfServer;
        this.fileDAO = new FileDAO();
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        this.logList = logList;
        this.modelTable = modelTable;
        tableDAO = new TableDAO();
    }

    @Override
    public void run() {
        try {
            // Mở luồng vào ra trên Socket tại Server.
            BufferedReader is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));

            String clientName = "client" + clientNumber++;
            String port = Integer.toString(socketOfServer.getPort());
            String fullAddress = socketOfServer.getInetAddress() + ":" + socketOfServer.getPort();
            String fileName = port;

            while (true) {
                System.out.println("New connection with client# " + this.clientNumber + " at " + socketOfServer);
                // Đọc dữ liệu tới server (Do client gửi tới).
                String line = is.readLine();

                String[] splitCheckNew = line.split("==");
                if (splitCheckNew.length > 1) {
                    String key = splitCheckNew[0];
                    String value = splitCheckNew[1];
                    if ("New".equals(key)) {
                        //TODO: save file
//                        fileDAO.write(value, fileName + ".txt");
                    } else if ("Log".equals(splitCheckNew[0])) {
                        //TODO: save log here
                        // time, action, ip, explain
                        //format message: Time:xx;action:xx;ip:xx;explain:xx
                        Date date = new Date();
                        String[] splitActionLog = value.split(":");
                        String type = splitActionLog[0];
                        String actionValue = splitActionLog[1];

                        String explain = fileDAO.convertMessage(type, actionValue);
                        String lineTemplate = dateFormat.format(date) + ";" + type + ";" + fullAddress + ";" + explain;

                        fileDAO.writeNotReset(lineTemplate, FILENAME_LOG);
                        //update UI
                        String t = dateFormat.format(date);

                        Object[] ob = tableDAO.newRow(logList.size() + 1, dateFormat.format(date), type, fullAddress, explain);
                        LogAction ac = new LogAction(dateFormat.format(date), type, fullAddress, explain);
                        this.logList.add(ac);
                        this.modelTable.addRow(ob);
                    }
                }
                // Ghi vào luồng đầu ra của Socket tại Server.
                // (Nghĩa là gửi tới Client).
//                os.write(">> " + line);
//                // Kết thúc dòng
//                os.newLine();
//                // Đẩy dữ liệu đi
//                os.flush();
                // Nếu người dùng gửi tới QUIT (Muốn kết thúc trò chuyện).
                if (line.equals("QUIT")) {
                    os.write(">> OK");
                    os.newLine();
                    os.flush();
                    break;
                }
            }
            socketOfServer.close();

        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
