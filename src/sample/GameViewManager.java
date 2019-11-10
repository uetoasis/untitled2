package sample;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.ViewManager.ViewManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameViewManager {

    private AnchorPane gamePane;
    private Pane menuPane, InformationPane;
    private Scene gameScene, menuScene, InformationScene;
    private Stage gameStage, menuStage, InformationStage;

    private static final int GAME_WIDTH = 860;
    private static final int GAME_HEIGTH = 640;
    private static final int MENUGAME_WIDTH = 200;
    private static final int MENUGAME_HEIGTH = 410;
    private Stage stage;

    GraphicsContext gc;
    List<Enemy> enemy = new ArrayList<>();

    public GameViewManager()  {
        initializeStage();
    }

    private void initializeStage() {
        Canvas canvas = new Canvas(860, 640);
        gc = canvas.getGraphicsContext2D();
        gameStage = new Stage();
        gamePane = new AnchorPane();
        gamePane.getChildren().add(canvas);
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGTH);
        gameStage.setScene(gameScene);
        Map();
        createMenuTower();

        MenuPlay();
    }

    public void createNewGame(Stage menuStage){
        this.stage = menuStage;
        this.stage.hide();
        gameStage.show();
    }

    public void Map(){
        Image image = new Image("image.jpg");
        gc.drawImage(image, 0, 0,640,640);
    }

    public void createMenuTower() {
        TowerCanon1();
        TowerCanon2();
        TowerCanon3();
    }

    public void addMenuTower(Button button,int x, int y, Image image){
        button.setMaxHeight(64);
        button.setMaxWidth(64);
        ImageView iv = new ImageView(image);
        button.setGraphic(iv);
        button.setLayoutX(x);
        button.setLayoutY(y);
        gamePane.getChildren().add(button);
    }

    public void TowerCanon1(){
        Image image = new Image("Tower\\1.1.png");
        Button button1 = new Button();
        addMenuTower(button1, 665, 30, image);
        MouseMove(button1);
    }

    public void TowerCanon2(){
        Image image = new Image("Tower\\2.1.png");
        Button button2 = new Button();
        addMenuTower(button2, 765, 30, image);
    }

    public void TowerCanon3(){
        Image image = new Image("Tower\\3.1.png");
        Button button2 = new Button();
        addMenuTower(button2, 665, 130, image);
    }

    public void MouseMove(Button button){
        button.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
    }

    public void MenuPlay(){
        menuStage = new Stage();
        menuPane = new Pane();
        menuPane.setLayoutX(650);
        menuPane.setLayoutY(215);
        menuScene = new Scene(menuPane, MENUGAME_WIDTH, MENUGAME_HEIGTH);
        gamePane.getChildren().add(menuPane);
        createButton();
        createLable();
    }

    public void InformationTower(){
        InformationStage = new Stage();
        InformationPane = new Pane();
        InformationPane.setLayoutX(650);
        InformationPane.setLayoutY(215);
        InformationScene = new Scene(InformationPane, MENUGAME_WIDTH, MENUGAME_HEIGTH);
        gamePane.getChildren().add(InformationPane);
    }


    public void createLable(){
        createHP();
        createCost();
        creatLevel();
    }

    public void setLable(String s, int x, int y){
        Label label = new Label(s);
        label.setLayoutX(x);
        label.setLayoutY(y);
        Font font = new Font("System", 16);
        label.setFont(font);
        menuPane.getChildren().add(label);
    }

    public void createHP(){
        setLable("HP:" , 60, 130);
    }
    public void createCost(){
        setLable("COST: ", 60, 200);
    }
    private void creatLevel(){
        setLable("LEVEL: ", 60, 270);
    }

    public void createButton(){
        createStart();
        createMainMenu();
        createQuit();
    }

    public void addMenuButton(Button button,int x, int y,String s, int w, int h){
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setStyle(s);
        button.setPrefWidth(w);
        button.setPrefHeight(h);
        menuPane.getChildren().add(button);
    }

    public void createStart(){
        Button button = new Button("START");
        addMenuButton(button, 20, 14, "-fx-font:32 arial;", 160, 64);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AnimationTimer animationTimer = new AnimationTimer() {
                    @Override
                    public void handle(long l) {
                        render();
                        update();
                    }
                };
                animationTimer.start();

                enemy.add(createminion());

            }
        });

    }

    public void createMainMenu(){
        Button button = new Button("MAIN MENU");
        addMenuButton(button, 15, 330, "-fx-font:11 arial;", 80, 45);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ViewManager Manager = new ViewManager();
                Manager.createMainMenuGame(gameStage);
            }
        });
    }

    public void createQuit(){
        Button button = new Button("QUIT");
        addMenuButton(button, 105, 330, "-fx-font:11 arial;", 80, 45);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStage.close();
            }
        });
    }

    //double x = 100, y =100;
    public Enemy createminion() {
        Enemy minion = new Enemy();
        minion.x = 0;
        minion.y = 90;
         minion.image = new Image("Enemy\\1.1.png", 40, 40, false, true);
        return minion;
    }

    public void render(){
        Map();
        enemy.forEach( g -> g.render(gc));
    }

    public void update(){
        enemy.forEach(Enemy ::update);
    }
}
