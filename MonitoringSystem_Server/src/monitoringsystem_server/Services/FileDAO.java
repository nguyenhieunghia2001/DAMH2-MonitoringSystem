/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitoringsystem_server.Services;

import Models.LogAction;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nghiadx
 */
public class FileDAO {

    public void write(String json, String fileName) throws IOException {
        BufferedWriter writer = null;
        try {
            File f = new File(fileName);
            f.delete();
            writer = new BufferedWriter(new FileWriter(f, true));
            writer.append(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(writer);
        }
    }

    public void writeNotReset(String json, String fileName) throws IOException {
        BufferedWriter writer = null;
        try {
            File f = new File(fileName);
            writer = new BufferedWriter(new FileWriter(f, true));
            writer.append(json);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(writer);
        }
    }

    public ArrayList<LogAction> readLogList(String fileName) {
        ArrayList<LogAction> logList = new ArrayList<LogAction>();
        BufferedReader b = null;
        try {
            File f = new File(fileName);
            b = new BufferedReader(new FileReader(f));
            String readLine = "";

            while ((readLine = b.readLine()) != null) {
                String[] keyValue = readLine.split(";");
                if (keyValue.length > 1) {
                    LogAction ac = new LogAction(keyValue[0], keyValue[1], keyValue[2], keyValue[3]);
                    logList.add(ac);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(b);
        }
        return logList;
    }

    private void closeStream(BufferedReader is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeStream(BufferedWriter is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String convertMessage(String type, String message) {
        if ("ENTRY_CREATE".equals(type)) {
            return "Create file " + message;
        } else if ("ENTRY_DELETE".equals(type)) {
            return "Delete file " + message;
        } else {
            return "Modify file " + message;
        }
    }
}
