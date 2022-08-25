/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitoringsystem_client;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nghiadx
 */
public class MonitorFolder extends Thread {

    String pathTemplate = "C:\\Users\\nghiadx\\Desktop\\machine 1\\";
    WatchService watchService;
    String _pathFile;
    Path path;
    ClientSocket _client;

    public MonitorFolder(ClientSocket client, String folderName) throws IOException {
        watchService = FileSystems.getDefault().newWatchService();
        _pathFile = pathTemplate + folderName;
        path = Paths.get(_pathFile);
        path.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        this._client = client;
    }

    @Override
    public void run() {
        MonitorFolder monitor = null;
        try {
            monitor = new MonitorFolder(_client, "share_1");
        } catch (IOException ex) {
            Logger.getLogger(MonitoringSystem_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {
            WatchKey key;
            try {
                key = monitor.watchService.take();
            } catch (InterruptedException ex) {
                return;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                @SuppressWarnings("unchecked")
                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path fileName = ev.context();

                String log = kind.name() + ":" + fileName;
                _client.sendMessage("Log==" + log);
            }

            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
    }
}
