package com.teamtodo.towerdefense.ui;

import com.teamtodo.towerdefense.GameVars;
import com.teamtodo.towerdefense.tower.TowerType;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class TowerMenu extends StackPane {

    private final int width;
    private final int height;

    public TowerMenu(int width, int height) {
        this.width = width;
        this.height = height;

        createMenuUI();
    }

    public void createMenuUI() {
        var towerMenuOutline = new Rectangle(0, 0, width / 8f, height / 2f);
        towerMenuOutline.setFill(Color.BLACK);

        Rectangle towerMenuBkg = new Rectangle(5, 5,
            width / 8f - 10f, height / 2f - 10f);
        towerMenuBkg.setFill(Color.LIGHTSTEELBLUE);

        Label towerMenuLabel = new Label("Tower Menu");
        towerMenuLabel.setFont(new Font(24));


        VBox towersVBox = new VBox(20);

        String basePath = "./src/main/resources/assets/textures/";

        var towerButton1 = new TowerButton(basePath.concat("tower1.png"));
        var towerButton2 = new TowerButton(basePath.concat("tower2.png"));
        var towerButton3 = new TowerButton(basePath.concat("tower3.png"));

        towerButton1.setStyle("-fx-border: 3px solid; -fx-border-color: black; ");

        StackPane towerSP1 = new StackPane();
        StackPane towerSP2 = new StackPane();
        StackPane towerSP3 = new StackPane();

        Label towerPrice1 = new Label("Price: " + TowerType.TOWER1.getPrice());
        StackPane.setAlignment(towerPrice1, Pos.BOTTOM_CENTER);
        towerSP1.getChildren().addAll(towerButton1, towerPrice1);
        towerSP1.setAlignment(Pos.CENTER);

        Label towerPrice2 = new Label("Price: " + TowerType.TOWER2.getPrice());
        StackPane.setAlignment(towerPrice2, Pos.BOTTOM_CENTER);
        towerSP2.getChildren().addAll(towerButton2, towerPrice2);
        towerSP2.setAlignment(Pos.CENTER);

        Label towerPrice3 = new Label("Price: " + TowerType.TOWER3.getPrice());
        StackPane.setAlignment(towerPrice3, Pos.BOTTOM_CENTER);
        towerSP3.getChildren().addAll(towerButton3, towerPrice3);
        towerSP3.setAlignment(Pos.CENTER);

        //Set button click on action
        towerButton1.setOnAction((ActionEvent event) -> {
            towerButton2.setStyle("-fx-background-color: transparent; ");
            towerButton3.setStyle("-fx-background-color: transparent; ");
            towerButton1.setStyle("-fx-border: 3px solid; -fx-border-color: black; ");

            GameVars.setSelectedTower(TowerType.TOWER1);
            GameVars.setTowerDescription();
        });

        towerButton2.setOnAction((ActionEvent event) -> {
            towerButton1.setStyle("-fx-background-color: transparent; ");
            towerButton3.setStyle("-fx-background-color: transparent; ");
            towerButton2.setStyle("-fx-border: 3px solid; -fx-border-color: black; ");
            GameVars.setSelectedTower(TowerType.TOWER2);
            GameVars.setTowerDescription();
        });

        towerButton3.setOnAction((ActionEvent event) -> {
            towerButton1.setStyle("-fx-background-color: transparent; ");
            towerButton2.setStyle("-fx-background-color: transparent; ");
            towerButton3.setStyle("-fx-border: 3px solid; -fx-border-color: black; ");
            GameVars.setSelectedTower(TowerType.TOWER3);
            GameVars.setTowerDescription();
        });

        towersVBox.getChildren().addAll(towerSP1, towerSP2, towerSP3);

        BorderPane towerMenuBP = new BorderPane();
        BorderPane.setAlignment(towerMenuLabel, Pos.CENTER);
        BorderPane.setMargin(towerMenuLabel, new Insets(10, 10, 10, 10));
        towerMenuBP.setTop(towerMenuLabel);

        BorderPane.setAlignment(towersVBox, Pos.CENTER);
        BorderPane.setMargin(towersVBox, new Insets(10, 10, 10, 10));
        towerMenuBP.setCenter(towersVBox);

        this.getChildren().addAll(towerMenuOutline, towerMenuBkg, towerMenuBP);
    }
}
