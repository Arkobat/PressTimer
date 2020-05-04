package io.github.arkobat.presstimer.visual;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.AccessLevel;
import lombok.Getter;

public class TimerGUI {

    @Getter(AccessLevel.PACKAGE)
    private final Scene scene;
    private final TilePane tilePane = new TilePane();

    TimerGUI(Stage stage) {
        tilePane.setPadding(new Insets(10,10,10,10));
        tilePane.setHgap(15);
        tilePane.setVgap(15);

        VBox header = new VBox(), content = new VBox();
        header.setStyle("-fx-background-color: #cccccc; -fx-text-alignment: center");
        content.setStyle("-fx-background-color: #ffffff");
        content.setStyle("-fx-padding: 25px 5px 5px 5px;");
        header.setMinHeight(110);
        header.setMaxHeight(110);

        content.getChildren().add(tilePane);

        Label label = new Label("Stamp Stop Monitor");
        label.setStyle("-fx-font-size: 82; -fx-font-weight: bold; -fx-wrap-text:true;"
                + "-fx-text-alignment: center; -fx-alignment: center; -fx-max-width:Infinity; -fx-padding: 5;");

        //label.setFont(new Font(82));
        header.getChildren().add(label);

        this.scene = new Scene(new VBox(header, content), 800, 600);
    }

    public void addPress(VBox vBox) {
        this.tilePane.getChildren().add(vBox);
    }

}
