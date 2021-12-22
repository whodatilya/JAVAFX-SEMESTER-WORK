package ru.kpfu.itis.protocol;

public enum MessageType {

    CONNECT("connect", "Player connected"),
    STOP("stop", "Game stopped"),
    ACTION("action", "Player action"),
    CHAT( "chat", "Message");

    private final String title;
    private final String description;

    MessageType(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static MessageType typeOf(String title){
        for (MessageType messageType: MessageType.values()) {
            if (messageType.getTitle().equals(title)) {
                return messageType;
            }
        }
        return null;
    }
    @Override
    public String toString() {
        return "MessageType{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
