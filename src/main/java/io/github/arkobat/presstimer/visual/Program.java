package io.github.arkobat.presstimer.visual;

import io.github.arkobat.presstimer.core.PressHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application {

    public static void load(String[] args) {
        launch(args);
    }

    private SetupGUI setup;
    private TimerGUI timer;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.setup = new SetupGUI();
        this.timer = new TimerGUI(primaryStage);
        PressHandler pressHandler = new PressHandler(this.timer);

        primaryStage.setTitle("Press watcher");
        primaryStage.setScene(timer.getScene());
        primaryStage.setWidth(1400);
        primaryStage.setHeight(900);
        //primaryStage.setOnCloseRequest(aVoid -> Config.updateWordFile());
        primaryStage.show();
    }
}
