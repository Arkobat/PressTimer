package io.github.arkobat.presstimer.model;

import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Status {
    ACTIVE(Color.valueOf("66cc00"), "Active"),
    OFFLINE(Color.RED, "Offline"),
    MAINTENANCE(Color.YELLOW, "Paused"),
    NON_RESPONSIVE(Color.GRAY, "Timeout");

    private final Color color;
    private final String name;
}
