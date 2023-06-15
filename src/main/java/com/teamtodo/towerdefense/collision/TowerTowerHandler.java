package com.teamtodo.towerdefense.collision;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.teamtodo.towerdefense.GameVars;
import com.teamtodo.towerdefense.TowerDefenseType;
import com.teamtodo.towerdefense.components.TowerComponent;
import com.teamtodo.towerdefense.tower.TowerType;
import javafx.geometry.Point2D;

/**
 * Collision Handler for Tower-Tower collisions. Mainly checks for upgrading towers
 *
 * @author Akash Chakka
 * @author Richard Luo
 * @version 1.6.0
 * @since 1.6.0
 */
public class TowerTowerHandler extends CollisionHandler {

    public TowerTowerHandler() {
        super(TowerDefenseType.TOWER, TowerDefenseType.TOWER);
    }

    @Override
    protected void onCollisionBegin(Entity tower1, Entity tower2) {
        TowerComponent t1 = tower1.getComponent(TowerComponent.class);
        TowerComponent t2 = tower2.getComponent(TowerComponent.class);

        //Reset money and remove extra tower
        GameVars.setMoney(GameVars.getMoney().get() + GameVars.getSelectedTower().getPrice());
        tower2.removeFromWorld();

        if (t1.getType() == t2.getType()) {
            GameVars.setMoney(GameVars.getMoney().get() - GameVars.getSelectedTower().getPrice());

            Point2D pos = tower1.getPosition();
            if (t1.getType() == TowerType.TOWER1) {
                FXGL.spawn(TowerType.TOWER11.name(), pos);
            } else if (t1.getType() == TowerType.TOWER2) {
                FXGL.spawn(TowerType.TOWER22.name(), pos);
            } else {
                FXGL.spawn(TowerType.TOWER33.name(), pos);
            }
            tower1.removeFromWorld();
        }
    }
}
