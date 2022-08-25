/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.io.File;
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

    String _path;

    public FolderSharing() {
        _path = "C:\\Users\\nghiadx\\Desktop\\machine 1";
        FolderList = getFolders();
        FolderSelected = getFolderSelected("share_1");
    }
    
    public ArrayList<Folder> getFolders() {
        ArrayList<Folder> result = new ArrayList<Folder>();
        ArrayList<String> folderList = getfolder();
        for (String folderName : folderList) {
            Folder fd = new Folder();
            //get file
            ArrayList<String> files = getFile(_path + "\\" + folderName);
            fd.setFileName(files);
            fd.setFolderName(folderName);

            result.add(fd);
        }
        return result;
    }

    public Folder getFolderSelected(String folderName) {
        Folder f = new Folder();
        ArrayList<String> files = getFile(_path + "\\" + folderName);
        f.setFileName(files);
        f.setFolderName(folderName);
        
        return f;
    }

    public ArrayList<String> getfolder() {
        ArrayList<String> result = new ArrayList<String>();
        File directoryPath = new File(_path);
        //List of all files and directories
        String contents[] = directoryPath.list();
        for (int i = 0; i < contents.length; i++) {
            String[] name = contents[i].split(".");
            if (name.length == 0) {
                result.add(contents[i]);
            }
        }
        return result;
    }

    public ArrayList<String> getFile(String path) {
        ArrayList<String> result = new ArrayList<String>();
        File directoryPath = new File(path);
        //List of all files and directories
        String contents[] = directoryPath.list();
        for (int i = 0; i < contents.length; i++) {
            String[] name = contents[i].split(".");
            if (name.length > 1) {
                result.add(contents[i]);
            }
        }
        return result;
    }
}
