package com.teamtodo.towerdefense.enemy;

/**
 * EnemyType contains the stats of each enemy type.
 *
 * @author Austin Peng
 * @author Richard Luo
 * @version 1.6.0
 * @since 1.5.0
 */
public enum EnemyType {

    ENEMY1(4, 50, 4, 1, 0),
    ENEMY2(8, 100, 8, 2, 0),
    ENEMY3(200, 200, 1600, 3, 1);
  
    private final int reward;
    private final int damage;
    private final int armor;
    private int health;
    private double speed;

    EnemyType(int health, int reward, int damage, double speed, int armor) {
        this.health = health;
        this.reward = reward;
        this.damage = damage;
        this.speed = speed;
        this.armor = armor;
    }

    public int getReward() {
        return reward;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getArmor() {
        return armor;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
