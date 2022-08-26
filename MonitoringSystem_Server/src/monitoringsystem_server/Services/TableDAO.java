/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitoringsystem_server.Services;

/**
 *
 * @author nghiadx
 */
public class TableDAO {

    public Object[] newRow(int no, String time, String action, String Ip, String explain) {
        Object[] row = new Object[5];
        row[0] = no;
        row[1] = time;
        row[2] = action;
        row[3] = Ip;
        row[4] = explain;
        return row;
    }
}
