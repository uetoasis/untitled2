package sample.ViewManager;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.Enemy;
import sample.GameViewManager;

public class ViewManager {

    private static final int WIDTH = 860;
    private static final int HEIGHT = 640;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private Stage stage;

    GraphicsContext gc;

    public ViewManager(){
        MainMenu();
    }

    public void MainMenu() {
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);

        createBackground();
        createButton();
        createLabel();
    }

    private void createBackground() {
        Image image = new Image("download.jpg");
        ImageView iv = new ImageView(image);
        mainPane.getChildren().add(iv);
    }

    private void createButton() {
        createPlayButton();
        createContinueButton();
        createExitButton();
    }

    private void addMenuButton(Button button,int x, int y){
        button.setLayoutX(x);
        button.setLayoutY(y);
        setFornButton(button);
        mainPane.getChildren().add(button);
    }
    private void createPlayButton(){
        Button button1 = new Button("PLAY");
        addMenuButton(button1,350, 200);
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                GameViewManager gameManager = new GameViewManager();
                gameManager.createNewGame(mainStage);
            }
        });
    }

    private void createContinueButton(){
        Button button2  = new Button("CONTINUE");
        addMenuButton(button2,350, 300);
    }

    private void createExitButton(){
        Button button3 = new Button("EXIT");
        addMenuButton(button3,350, 400);
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.close();
            }
        });
    }

    private void setFornButton(Button button){
        button.setPrefHeight(50);
        button.setPrefWidth(160);
        button.setStyle("-fx-font:22 arial; -fx-base: #b6e7c9;");
    }

    private void createLabel(){
        LOGO();
        GROUP1();
    }

    private void LOGO(){
        Label Logo = new Label("TOWER DEFENSE");
        Logo.setLayoutX(208);
        Logo.setLayoutY(50);
        Logo.setFont(new Font("Kristen ITC",47 ));
        mainPane.getChildren().add(Logo);
    }

    private void GROUP1(){
        Label Group1 = new Label("MAKE BY GROUP 1");
        Group1.setLayoutX(295);
        Group1.setLayoutY(505);
        Group1.setFont(new Font("System", 32 ));
        mainPane.getChildren().add(Group1);
    }

    public void createMainMenuGame(Stage menuStage){
        this.stage = menuStage;
        this.stage.hide();
        mainStage.show();
    }

    public Stage getMainStage(){
        return mainStage;
    }
}
