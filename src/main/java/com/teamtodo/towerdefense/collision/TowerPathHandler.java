package com.teamtodo.towerdefense.collision;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.teamtodo.towerdefense.GameVars;
import com.teamtodo.towerdefense.TowerDefenseType;
import com.teamtodo.towerdefense.components.TowerComponent;

/**
 * Collision Handler for Tower-Path collisions. Removes a tower if it is placed onto the path.
 *
 * @author Akash Chakka
 * @author Lucas Yim
 * @version 1.2
 * @since 1.2
 */
public class TowerPathHandler extends CollisionHandler {

    public TowerPathHandler() {
        super(TowerDefenseType.TOWER, TowerDefenseType.PATH);
    }

    @Override
    protected void onCollisionBegin(Entity tower, Entity path) {
        path.getWorld()
            .getSingleton(TowerDefenseType.TOWER)
            .getComponent(TowerComponent.class);

        GameVars.setMoney(GameVars.getMoney().get() + GameVars.getSelectedTower().getPrice());
        tower.removeFromWorld();
    }
}
