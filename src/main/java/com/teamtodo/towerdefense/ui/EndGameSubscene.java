package com.teamtodo.towerdefense.ui;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.scene.Scene;
import com.almasb.fxgl.scene.SubScene;
import com.teamtodo.towerdefense.GameVars;
import com.teamtodo.towerdefense.TowerDefenseApp;
import javafx.beans.binding.Bindings;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

import static com.almasb.fxgl.dsl.FXGLForKtKt.animationBuilder;

/**
 * Endgame Subscene for restarting the game or ending the game.
 *
 * @author Austin Peng
 * @author Richard Luo
 * @version 1.6.0
 * @since 1.6.0
 */
public class EndGameSubscene extends SubScene {

    public EndGameSubscene() {
        FXGL.getGameScene().clearGameViews();
        FXGL.getGameScene().clearUINodes();
        FXGL.getGameScene().clearCSS();
        FXGL.getGameScene().clearEffect();

        var text = FXGL.getUIFactoryService()
            .newText(GameVars.getLives().get() > 0 ? "GGEZ" : "LOL UR A LOSER", Color.GOLD, 28);
        text.setTranslateX(TowerDefenseApp.getScreenWidth() / 2f - 200);
        text.setTranslateY(TowerDefenseApp.getScreenHeight() / 2.5);

        var killCount = FXGL.getUIFactoryService().newText(Bindings.createStringBinding(
            () -> "Enemies Killed: " + GameVars.getEnemyKillCount().get(),
            GameVars.getEnemyKillCount()));
        killCount.setFill(Color.BLACK);
        killCount.setTranslateX(TowerDefenseApp.getScreenWidth() / 2f + 100);
        killCount.setTranslateY(TowerDefenseApp.getScreenHeight() / 2.5);

        var towerBought = FXGL.getUIFactoryService().newText(Bindings.createStringBinding(
            () -> "Towers Bought: " + GameVars.getNumTowersBought().get(),
            GameVars.getNumTowersBought()));
        towerBought.setFill(Color.BLACK);
        towerBought.setTranslateX(TowerDefenseApp.getScreenWidth() / 2f + 300);
        towerBought.setTranslateY(TowerDefenseApp.getScreenHeight() / 2.5);

        var totalDamage = FXGL.getUIFactoryService().newText(Bindings.createStringBinding(
            () -> "Total Damage: " + GameVars.getDamageDealt().get(),
            GameVars.getDamageDealt()));
        totalDamage.setFill(Color.BLACK);
        totalDamage.setTranslateX(TowerDefenseApp.getScreenWidth() / 2f + 500);
        totalDamage.setTranslateY(TowerDefenseApp.getScreenHeight() / 2.5);

        var resetBtn = new ResetGameButton();
        resetBtn.setTranslateX(TowerDefenseApp.getScreenWidth() / 3f);
        resetBtn.setTranslateY(TowerDefenseApp.getScreenHeight() / 2f);

        var exitBtn = new ExitGameButton();
        exitBtn.setTranslateX(TowerDefenseApp.getScreenWidth() / 3f * 2);
        exitBtn.setTranslateY(TowerDefenseApp.getScreenHeight() / 2f);

        getContentRoot().getChildren()
            .addAll(text, killCount, towerBought, totalDamage, resetBtn, exitBtn);
    }

    @Override
    public void onEnteredFrom(@NotNull Scene prevState) {
        animationBuilder()
            .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
            .translate(getContentRoot())
            .from(new Point2D(-350, 250))
            .to(new Point2D(50, 250))
            .build().start();
    }

}
