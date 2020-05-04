package io.github.arkobat.presstimer.model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Press {

    private final String id;
    @Setter
    private String customer = "Danfoss A/S";
    private Status status = Status.NON_RESPONSIVE;
    private final PressFacade facade;
    @Getter(AccessLevel.NONE)
    private VBox pressBox;
    private VBox view = new VBox();

    private long stoppedTime;

    public Press(String id) {
        this.id = id;
        this.stoppedTime = System.currentTimeMillis();
        this.facade = new PressFacade();
        this.pressBox = facade.getView(customer, status, stoppedTime);

        // Header
        Label idLabel = new Label("Stamp #" + id);
        idLabel.setStyle("-fx-font-size: 32; -fx-font-family: Century Gothic; -fx-wrap-text:true;-fx-text-alignment: center; -fx-alignment: center; -fx-max-width:Infinity; -fx-padding: 5;");
        VBox header = new VBox(idLabel);
        header.setStyle("-fx-background-color: #999999; -fx-background-radius: 10 10 0 0; ");
        header.setMinWidth(200);
        header.setMinSize(200, 50);
        header.setMaxSize(200, 50);

        // Content
        this.pressBox.setStyle("-fx-min-width: 200; -fx-max-width: 200; -fx-min-height: 200; -fx-max-height: 200; " +
                "-fx-label-padding: 5; -fx-background-color: #cccccc; -fx-background-radius: 0 0 10 10; -fx-padding: 5;" +
                "-fx-start-margin: 5;");

        this.view.getChildren().addAll(header, pressBox);

        this.pressBox.setOnMouseClicked(e -> {
            System.out.println(status);
            switch (status) {
                case NON_RESPONSIVE:
                    setStatus(Status.ACTIVE);
                    break;
                case ACTIVE:
                    setStatus(Status.OFFLINE);
                    break;
                case OFFLINE:
                    setStatus(Status.MAINTENANCE);
                    break;
                case MAINTENANCE:
                    setStatus(Status.NON_RESPONSIVE);
                    break;
            }
            this.pressBox.getChildren().removeAll();
            this.pressBox.getChildren().add(facade.getView(customer, status, stoppedTime));
        });
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(250), ev -> {
            this.pressBox.getChildren().removeAll();
            this.pressBox.getChildren().addAll(facade.getView(customer, status, stoppedTime));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public void setStatus(Status status) {
        this.status = status;
        this.stoppedTime = System.currentTimeMillis();
    }


}
