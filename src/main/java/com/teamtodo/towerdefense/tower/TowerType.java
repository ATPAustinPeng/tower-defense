package com.teamtodo.towerdefense.tower;

import com.teamtodo.towerdefense.GameVars;

/**
 * TowerType contains the stats of each tower type.
 *
 * @author Austin Peng
 * @author Richard Luo
 * @version 1.5.0
 * @since 1.5.0
 */
public enum TowerType {

    TOWER1(10 * 60, 300, 2, 1, 1, 200, "./src/main/resources/assets/textures/tower1.png"),
    TOWER11(12 * 60, 600, 3, 1, 1, 200, "./src/main/resources/assets/textures/tower11.png"),
    TOWER2(15 * 60, 600, 4, 2, 2, 500, "./src/main/resources/assets/textures/tower2.png"),
    TOWER22(18 * 60, 1200, 5, 2, 2, 500, "./src/main/resources/assets/textures/tower22.png"),
    TOWER3(11 * 60, 200, 6, 1, 3, 800, "./src/main/resources/assets/textures/tower3.png"),
    TOWER33(13 * 60, 400, 8, 1, 3, 800, "./src/main/resources/assets/textures/tower33.png");

    private final int bulletType;
    private final int bulletSpeed;
    private final int range;
    private final int price;
    private final double attackDelay;
    private final int damage;
    private final String imagePath;

    private static final int DIFFICULTY_SCALING = 50;

    TowerType(int bulletSpeed, int range, int damage, double attackDelay, int bulletType,
              int price, String imagePath) {
        this.range = range;
        this.bulletSpeed = bulletSpeed;
        this.damage = damage;
        this.attackDelay = attackDelay;
        this.bulletType = bulletType;
        this.price = price;
        this.imagePath = imagePath;
    }

    public int getBulletType() {
        return bulletType;
    }

    public int getBulletSpeed() {
        return bulletSpeed;
    }

    public int getRange() {
        return range;
    }

    public double getAttackDelay() {
        return attackDelay;
    }

    public int getDamage() {
        return damage;
    }

    public int getPrice() {
        return price + DIFFICULTY_SCALING * GameVars.getDifficulty();
    }

    public int getOriginalPrice() {
        return price;
    }

    public boolean canHit(int distance) {
        return getRange() > distance;
    }

    public String getImagePath() {
        return imagePath;
    }
}
