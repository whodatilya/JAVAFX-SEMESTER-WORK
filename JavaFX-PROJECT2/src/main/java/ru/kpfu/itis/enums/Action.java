package ru.kpfu.itis.enums;

import javafx.scene.input.KeyCode;

public enum Action {

    DRAW(0, "right", "PlAYER DRAW", KeyCode.RIGHT),
    ERASE(1, "left", "PLAYER ERASE", KeyCode.LEFT);
    private final int code;
    private final String title;
    private final String description;
    private final KeyCode keyCode;

    Action(int code, String title, String description, KeyCode keyCode) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.keyCode = keyCode;
    }

    public int getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static Action typeOf(String title){
        for (Action action: Action.values()) {
            if (action.getTitle().equalsIgnoreCase(title)) {
                return action;
            }
        }
        return null;
    }

}
