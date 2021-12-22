package ru.kpfu.itis.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ru.kpfu.itis.enums.Action;
import ru.kpfu.itis.protocol.Message;
import ru.kpfu.itis.protocol.MessageType;
import ru.kpfu.itis.sockets.ClientSocket;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private ClientSocket clientSocket;

    private static final String PLAYER_ICON = "src/main/resources/img/img.png";

    @FXML
    private Button sendMessageButton;

    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private TextField brushSize;
    @FXML
    private CheckBox eraser;

    @FXML
    private TextField messageText;

    @FXML
    private VBox messages;

    @FXML
    private TextField name;

    @FXML
    private Button connectButton;

    @FXML
    private Label helloLabel;

    @FXML
    private ImageView player;

    @FXML
    private ImageView enemy;

    @FXML
    private AnchorPane gameArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GraphicsContext g = canvas.getGraphicsContext2D();

        canvas.setOnMouseDragged(e -> {
            double size =  Double.parseDouble(brushSize.getText());
            double x = e.getX() - size/2;
            double y = e.getY() - size/2;
            if (eraser.isSelected()){
                g.clearRect(x,y,size, size);
            }else{
                g.setFill(colorPicker.getValue());
                g.fillRect(x,y,size,size);
            }
        });
        try {
            Image playerIcon = new Image(new FileInputStream(PLAYER_ICON));

            player.setImage(playerIcon);
            player.setRotate(180);

            enemy.setImage(playerIcon);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Platform.runLater( () -> gameArea.requestFocus() );
        connectButton.setOnMouseClicked(event -> {
            String nickname = name.getText();
            name.setEditable(false);
            helloLabel.setText("Привет, " + nickname + "!");
            clientSocket = new ClientSocket();
            clientSocket.connect(this, nickname);
            clientSocket.start();
        });
        sendMessageButton.setOnMouseClicked(event -> {
            sendMessage();
        });

    }

    private Message createActionMessage(Action action) {
        Message message = new Message();
        message.setType(MessageType.ACTION);
        message.setBody(action.getTitle());
        return message;
    }

    private Message createChatMessage(String text) {
        Message message = new Message();
        message.setType(MessageType.CHAT);
        message.setBody(text);

        return message;
    }

    private void sendMessage() {
        System.out.println(messageText.getText());
        Label messageLabel = new Label();
        messageLabel.setText("Я: " + messageText.getText());
        messages.getChildren().add(messageLabel);

        clientSocket.sendMessage(createChatMessage(messageText.getText()));

        messageText.clear();
    }



    public VBox getMessages() {
        return messages;
    }

    public ImageView getPlayer() {
        return player;
    }

    public ImageView getEnemy() {
        return enemy;
    }

    public AnchorPane getGameArea() {
        return gameArea;
    }
}
