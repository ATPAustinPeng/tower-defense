package com.teamtodo.towerdefense.collision;

import com.almasb.fxgl.app.scene.GameView;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.teamtodo.towerdefense.GameVars;
import com.teamtodo.towerdefense.TowerDefenseType;
import com.teamtodo.towerdefense.components.BulletComponent;
import com.teamtodo.towerdefense.components.EnemyComponent;
import com.teamtodo.towerdefense.ui.EndGameSubscene;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * BulletEnemyHandler controls the logic for a bullet from a tower hitting an enemy. Logic for
 * bullet collision:
 * <pre>
 *     if bullet is laser: bullet goes through all enemies
 *
 *     if bullet can pierce through enemy armor:
 *          deal damage to enemy
 *
 *          if enemy is dead:
 *              remove enemy from map
 *              update money appropriately
 * </pre>
 *
 * @author Richard Luo
 * @author Austin Peng
 * @author Akash Chakka
 * @since 1.5.0
 * @version 1.3.1
 */
public class BulletEnemyHandler extends CollisionHandler {

    private static final double HITMARKER_LIFE = 0.4;

    public BulletEnemyHandler() {
        super(TowerDefenseType.BULLET, TowerDefenseType.ENEMY);
    }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity enemy) {
        BulletComponent b = bullet.getComponent(BulletComponent.class);
        EnemyComponent e = enemy.getComponent(EnemyComponent.class);

        // Check if bullet is laser
        if (!b.isLaser()) {
            bullet.removeFromWorld();
        }

        // Check if enemy armor is pierced
        if (b.getDamage() > e.getType().getArmor()) {
            //Decrement enemy health according to bullet damage
            e.getType().setHealth(e.getType().getHealth() - b.getDamage());

            //Update GameVars
            GameVars.setDamageDealt(GameVars.getDamageDealt().getValue() + b.getDamage());

            //Draw hitmarker
            displayHitmarker(bullet.getPosition());

            //Enemy killed
            if (e.getType().getHealth() <= 0) {
                enemy.removeFromWorld();

                //Update corresponding game variables
                GameVars.setEnemyCount(GameVars.getEnemyCount().getValue() - 1);
                GameVars.setEnemyKillCount(GameVars.getEnemyKillCount().getValue() + 1);
                GameVars.setMoney(GameVars.getMoney().getValue() + e.getType().getReward());

                if (GameVars.getEnemyCount().getValue() == 0) {
                    System.out.println("Round Completed!");
                }

                if (GameVars.getEnemyCount().getValue() <= 0) {
                    FXGL.getSceneService().pushSubScene(new EndGameSubscene());
                    System.out.println("ggez");
                }
            }
        }
    }

    /**
     * Displays a hitmarker on the current enemy after bullet collision
     *
     * @param position Point2D specifying the X and Y position of the enemy
     */
    private void displayHitmarker(Point2D position) {
        var xMark = FXGL.getUIFactoryService().newText("X", Color.BLACK, 24);
        xMark.setTranslateX(position.getX());
        xMark.setTranslateY(position.getY());

        var gameView = new GameView(xMark, 10001);

        FXGL.getGameScene().addGameView(gameView);

        FXGL.getGameTimer().runOnceAfter(() -> {
            FXGL.getGameScene().removeGameView(gameView);
        }, Duration.seconds(HITMARKER_LIFE));

    }
}
