/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitoringsystem_client;

/**
 *
 * @author nghiadx
 */
public class LogAction {

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String Action) {
        this.Action = Action;
    }

    public String getExplain() {
        return Explain;
    }

    public void setExplain(String Explain) {
        this.Explain = Explain;
    }
    public String Time;
    public String Action;
    public String Explain;

    public LogAction(String Time, String Action, String Explain) {
        this.Time = Time;
        this.Action = Action;
        this.Explain = Explain;
    }
}
