package io.github.arkobat.presstimer.core;

import io.github.arkobat.presstimer.model.Press;
import io.github.arkobat.presstimer.model.Status;
import io.github.arkobat.presstimer.visual.TimerGUI;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PressHandler {

    private final TimerGUI timerGUI;
    private List<Press> presses = new ArrayList<>();

    public PressHandler(TimerGUI timerGUI) {
        this.timerGUI = timerGUI;
        this.presses.add(new Press("A13"));
        this.presses.add(new Press("A24"));
        this.presses.add(new Press("A84"));
        this.presses.add(new Press("A14"));
        this.presses.add(new Press("A52"));
        this.presses.add(new Press("A16"));
        this.presses.add(new Press("B11"));
        this.presses.add(new Press("B52"));
        this.presses.add(new Press("B53"));
        this.presses.add(new Press("B44"));
        this.presses.add(new Press("B51"));
        this.presses.add(new Press("B26"));

        presses.forEach(press -> {
            VBox vBox = press.getView();

            vBox.setId(String.valueOf(press.getId()));
            timerGUI.addPress(vBox);
        });
    }

    public void updatePress(String pressId, Status status, String costumer) {
        presses.stream().filter(p -> p.getId().equalsIgnoreCase(pressId)).forEach(p -> {
            if (status != null) {
                p.setStatus(status);
            }
            if (costumer != null) {
                p.setCustomer(costumer);
            }
        });
    }

    public void getPressInfo(int id) {

    }

    private CompletableFuture<Status> test(int id) {
        return null;
    }


}
