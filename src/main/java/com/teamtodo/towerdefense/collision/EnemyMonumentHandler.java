package com.teamtodo.towerdefense.collision;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.teamtodo.towerdefense.GameVars;
import com.teamtodo.towerdefense.TowerDefenseType;
import com.almasb.fxgl.physics.CollisionHandler;
import com.teamtodo.towerdefense.components.EnemyComponent;
import com.teamtodo.towerdefense.ui.EndGameSubscene;

/**
 * @author Richard Luo
 * @author Austin Peng
 * @author Akash Chakka
 * @version 1.5.0
 * @since 1.3.1
 */
public class EnemyMonumentHandler extends CollisionHandler {

    public EnemyMonumentHandler() {
        super(TowerDefenseType.ENEMY, TowerDefenseType.MONUMENT);
    }

    @Override
    public void onCollisionBegin(Entity enemy, Entity monument) {
        enemy.removeFromWorld();
        GameVars.setEnemyCount(GameVars.getEnemyCount().getValue() - 1);

        if (GameVars.getEnemyCount().getValue() == 0) {
            System.out.println("Round Completed!");
        }

        GameVars.setLives(
            GameVars.getLives().getValue()
                - enemy.getComponent(EnemyComponent.class).getType().getDamage()
        );

        if (GameVars.getLives().getValue() <= 0) {
            FXGL.getSceneService().pushSubScene(new EndGameSubscene());
            System.out.println("You lost noob lol");
        }
    }
}
