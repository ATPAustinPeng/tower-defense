package com.teamtodo.towerdefense.components;

import com.almasb.fxgl.entity.component.Component;

/**
 * Bullet Component that contains logic for the tower bullet (e.g. damage, laser).
 *
 * @author Richard Luo
 * @author Akash Chakka
 * @version 1.1.0
 * @since 1.1.0
 */
public class BulletComponent extends Component {

    private int damage;
    private final boolean isLaser;

    public BulletComponent(int damage, boolean isLaser) {
        this.isLaser = isLaser;
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isLaser() {
        return isLaser;
    }
}
