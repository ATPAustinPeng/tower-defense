package com.teamtodo.towerdefense.ui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class TowerButton extends Button {

    public TowerButton(String imagePath) {
        ImageView iv = new ImageView();
        iv.setImage(new Image(new File(imagePath).toURI().toString()));
        iv.setPreserveRatio(true);

        if (iv.getFitWidth() < iv.getFitHeight()) {
            iv.setFitHeight(50);
        } else {
            iv.setFitWidth(50);
        }

        setGraphic(iv);
        setPrefSize(110, 110);
        setStyle("-fx-background-color: transparent;");
    }

}
