package guiController;

import gui.playPanel.PlayPanel;
import logicController.LogicRequest;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class GuiController extends Thread{
    private Deque<GuiRequest> requests;
    private PlayPanel playPanel;
    private Deque<LogicRequest> responses;
    private boolean needTarget;
    private boolean needSummon;

    public GuiController(Deque<GuiRequest> requests, Deque<LogicRequest> responses){
        this.requests = requests;
        this.responses = responses;
        this.needTarget = false;
    }

    public void setPlayPanel(PlayPanel playPanel) {
        this.playPanel = playPanel;
    }

    public void sendRequest(LogicRequest logicRequest) {
        responses.add(logicRequest);
    }


    public void run(){
        while(true){
            while (requests.isEmpty()) {
                sleep();
            }
            executeAll();
        }
    }

    private void executeAll(){
        try {
            GuiRequest request = requests.pollFirst();
            request.execute(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public PlayPanel getPlayPanel() {
        return playPanel;
    }

    private void sleep(){
        try {
            Thread.sleep(1000 / 60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isNeedTarget() {
        return needTarget;
    }

    public void setNeedTarget(boolean needTarget) {
        this.needTarget = needTarget;
    }

    public boolean isNeedSummon() {
        return needSummon;
    }

    public void setNeedSummon(boolean needSummon) {
        this.needSummon = needSummon;
    }
}
