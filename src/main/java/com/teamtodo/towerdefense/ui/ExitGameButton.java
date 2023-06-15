package com.teamtodo.towerdefense.ui;

import com.almasb.fxgl.dsl.FXGL;
import javafx.beans.binding.Bindings;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Button ends the game when clicked.
 * @author Austin Peng
 * @version 1.3.1
 * @since 1.3.1
 */
public class ExitGameButton extends StackPane {

    public ExitGameButton() {
        setOnMouseClicked((mouseEvent) -> {
            System.out.println("EXIT GAME");
            FXGL.getGameController().exit();
        });
        var bg = new Rectangle(200, 40);
        var text = FXGL.getUIFactoryService().newText("Exit Game", Color.WHITE, 18);

        bg.fillProperty()
                .bind(Bindings.when(hoverProperty()).then(Color.RED).otherwise(Color.BLACK));

        text.fillProperty().bind(
                Bindings.when(hoverProperty()).then(Color.BLACK).otherwise(Color.RED)
        );

        getChildren().addAll(bg, text);
    }

}
