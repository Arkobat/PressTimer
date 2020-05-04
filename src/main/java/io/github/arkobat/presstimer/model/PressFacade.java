package io.github.arkobat.presstimer.model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public final class PressFacade {

    private final Circle circle = new Circle(50, Color.GRAY);

    private final Label costumerLabel = new Label();
    private final Label statusLabel = new Label();
    private final Label timeLabel = new Label();

    PressFacade() {
        Font font = new Font("Century Gothic", 16);
        Insets insets = new Insets(5, 0, 5, 0);
        this.costumerLabel.setFont(font);
        this.costumerLabel.setPadding(insets);
        this.statusLabel.setFont(font);
        this.timeLabel.setFont(font);
        this.timeLabel.setPadding(insets);
    }

    private String formatTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat(System.currentTimeMillis()  - time >= 3600000 ? "HH:mm:ss" : "mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        return format.format(new java.util.Date(System.currentTimeMillis() - time));
    }

    VBox getView(String costumer, Status status, long time) {
        costumerLabel.setText("Customer: " + costumer);
        statusLabel.setText("Status: " + status.getName());
        timeLabel.setText("Since: " + formatTime(time));
        circle.setFill(status.getColor());
        circle.setStyle("-fx-background-color: " + status.getColor().getRed() + status.getColor().getGreen() + status.getColor().getBlue() + "; -fx-border-color: black");
        StackPane stackPane = new StackPane(circle);
        stackPane.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(costumerLabel, statusLabel, timeLabel, stackPane);
        return vBox;
    }

}

