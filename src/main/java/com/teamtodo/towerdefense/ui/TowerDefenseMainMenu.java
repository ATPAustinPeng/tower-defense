package com.teamtodo.towerdefense.ui;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.teamtodo.towerdefense.GameVars;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.function.Predicate;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameController;

/**
 * Configuration menu that allows user to input name and select difficulty.
 *
 * @author Austin Peng
 * @author Lucas Yim
 * @author Richard Luo
 * @version 1.0
 * @since 1.0
 */
public class TowerDefenseMainMenu extends FXGLMenu {

    public TowerDefenseMainMenu() {
        super(MenuType.MAIN_MENU);
        startMainMenu();
    }

    public void startMainMenu() {
        getContentRoot().getChildren().setAll(new Pane());
        TowerDefenseButton button = new TowerDefenseButton("Play Game", this::configMenu);
        button.setTranslateX(FXGL.getAppWidth() / 2. - 100);
        button.setTranslateY(FXGL.getAppHeight() / 2. - 20);

        var welcomeText = FXGL.getUIFactoryService().newText("Welcome!", Color.BLACK, 30);
        welcomeText.setTranslateX(FXGL.getAppWidth() / 2. - 70);
        welcomeText.setTranslateY(FXGL.getAppHeight() / 2. - 100);

        getContentRoot().getChildren().add(button);
        getContentRoot().getChildren().add(welcomeText);
    }

    public static final Predicate<String> SANITIZE_NAME = (str) -> str.matches("\\S+");

    public void configMenu() {
        getContentRoot().getChildren().setAll(new Pane());

        //Input username
        FXGL.getDialogService().showInputBox("Enter your name", SANITIZE_NAME, name -> {
            GameVars.setName(name);
            getGameController().gotoMainMenu();
        });

        var startGameButton = new TowerDefenseButton("Start Game", () -> {
            GameVars.initVars();
            fireNewGame();
        });

        var startMainMenu = new TowerDefenseButton("Back To Main Menu", this::startMainMenu);

        //Create difficulty toggles
        ToggleGroup gameDifficultyGroup = new ToggleGroup();

        var easy = new RadioButton("easy");
        easy.setToggleGroup(gameDifficultyGroup);
        easy.setSelected(true);
        easy.setOnAction((ActionEvent action) -> GameVars.setDifficulty(0));

        RadioButton medium = new RadioButton("medium");
        medium.setToggleGroup(gameDifficultyGroup);
        medium.setOnAction((ActionEvent action) -> GameVars.setDifficulty(1));

        RadioButton hard = new RadioButton("hard");
        hard.setToggleGroup(gameDifficultyGroup);
        hard.setOnAction((ActionEvent action) -> GameVars.setDifficulty(2));

        HBox gameDifficultyButtons = new HBox();
        gameDifficultyButtons.getChildren().addAll(easy, medium, hard);

        VBox vbox = new VBox(50);
        vbox.getChildren().addAll(startGameButton, startMainMenu, gameDifficultyButtons);
        vbox.setTranslateX(FXGL.getAppWidth() / 2. - 100);
        vbox.setTranslateY(FXGL.getAppHeight() / 2. - 20);

        getContentRoot().getChildren().add(vbox);
    }

    private static class TowerDefenseButton extends StackPane {

        public TowerDefenseButton(String name, Runnable action) {

            var bg = new Rectangle(200, 40);
            bg.setStroke(Color.RED);

            var text = FXGL.getUIFactoryService().newText(name, Color.WHITE, 18);

            bg.fillProperty().bind(
                Bindings.when(hoverProperty()).then(Color.RED).otherwise(Color.BLACK)
            );

            text.fillProperty().bind(
                Bindings.when(hoverProperty()).then(Color.BLACK).otherwise(Color.RED)
            );

            setOnMouseClicked(e -> action.run());

            getChildren().addAll(bg, text);
        }
    }
}
