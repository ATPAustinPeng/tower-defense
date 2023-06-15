package com.teamtodo.towerdefense.ui;

import com.almasb.fxgl.dsl.FXGL;
import com.teamtodo.towerdefense.TowerDefenseApp;
import javafx.beans.binding.Bindings;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class WaveStartButton extends StackPane {

    public WaveStartButton() {
        setOnMouseClicked((mouseEvent) -> {
            System.out.println("START BUTTON PRESS");
            TowerDefenseApp.getWaveFactory().startWave();
        });

        var bg = new Rectangle(200, 40);
        bg.setStroke(Color.RED);

        var text = FXGL.getUIFactoryService().newText("Start Wave", Color.WHITE, 18);

        bg.fillProperty().bind(
            Bindings.when(hoverProperty()).then(Color.RED).otherwise(Color.BLACK)
        );

        text.fillProperty().bind(
            Bindings.when(hoverProperty()).then(Color.BLACK).otherwise(Color.RED)
        );

        getChildren().addAll(bg, text);
    }

}
