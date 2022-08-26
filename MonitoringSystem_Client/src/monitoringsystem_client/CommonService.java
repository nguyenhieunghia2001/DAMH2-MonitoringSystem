/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitoringsystem_client;

/**
 *
 * @author nghiadx
 */
public class CommonService {

    public String convertMessage(String type, String message) {
        if ("ENTRY_CREATE".equals(type)) {
            return "Create file " + message;
        } else if ("ENTRY_DELETE".equals(type)) {
            return "Delete file " + message;
        } else {
            return "Modify file " + message;
        }
    }

    public Object[] newRow(int no, String time, String action, String explain) {
        Object[] row = new Object[4];
        row[0] = no;
        row[1] = time;
        row[2] = action;
        row[3] = explain;
        return row;
    }
}
