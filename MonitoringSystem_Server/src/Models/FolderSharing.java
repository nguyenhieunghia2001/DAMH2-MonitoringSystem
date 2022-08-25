/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.ArrayList;

/**
 *
 * @author nghiadx
 */
public class FolderSharing {

    public Folder getFolderSelected() {
        return FolderSelected;
    }

    public void setFolderSelected(Folder FolderSelected) {
        this.FolderSelected = FolderSelected;
    }
    public Folder FolderSelected;

    public ArrayList<Folder> getFolderList() {
        return FolderList;
    }

    public void setFolderList(ArrayList<Folder> FolderList) {
        this.FolderList = FolderList;
    }
    public ArrayList<Folder> FolderList;
}
