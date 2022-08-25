/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author nghiadx
 */
public class Folder {
    public String FolderName;
    public ArrayList<String> FileName;

    public String getFolderName() {
        return FolderName;
    }

    public void setFolderName(String FolderName) {
        this.FolderName = FolderName;
    }

    public ArrayList<String> getFileName() {
        return FileName;
    }

    public void setFileName(ArrayList<String> FileName) {
        this.FileName = FileName;
    }
    
}
