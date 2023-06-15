package com.teamtodo.towerdefense;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import com.teamtodo.towerdefense.components.BulletComponent;
import com.teamtodo.towerdefense.components.EnemyComponent;
import com.teamtodo.towerdefense.enemy.EnemyType;
import com.teamtodo.towerdefense.tower.TowerType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Austin Peng
 * @author Alek Jacoby
 * @author Lucas Yim
 * @author Richard Luo
 * @version 1.5.0
 * @since 1.3.2
 */
public class EnemyTest {

    @Test
    public void testWeakEnemyReachMonument() {
        GameVars.setDifficulty(0);

        var lives = GameVars.getLives().get();
        EnemyComponent e = new EnemyComponent(EnemyType.ENEMY1);

        int d1 = e.getType().getDamage();
        GameVars.setLives(GameVars.getLives().getValue() - e.getType().getDamage());

        assertThat(GameVars.getLives().get(), is(lives - d1));
    }

    @Test
    public void testNormalEnemyReachMonument() {
        GameVars.setDifficulty(1);
        var lives = GameVars.getLives().get();

        EnemyComponent e = new EnemyComponent(EnemyType.ENEMY2);

        int d = e.getType().getDamage();
        GameVars.setLives(GameVars.getLives().getValue() - e.getType().getDamage());

        assertThat(GameVars.getLives().get(), is(lives - d));
    }

    @Test
    public void testStrongEnemyReachMonument() {
        GameVars.setDifficulty(2);
        var lives = GameVars.getLives().get();
        EnemyComponent e = new EnemyComponent(EnemyType.ENEMY3);
        int d = e.getType().getDamage();
        GameVars.setLives(
            GameVars.getLives().getValue() - e.getType().getDamage()
        );
        assertThat(GameVars.getLives().get(), is(lives - d));
    }

    @Test
    public void testWeakEnemyDeathAwardsMoney() {
        GameVars.setDifficulty(0);
        var money = GameVars.getMoney().get();
        EnemyComponent e = new EnemyComponent(EnemyType.ENEMY1);
        int r = e.getType().getReward();
        GameVars.setMoney(
            GameVars.getMoney().getValue() + e.getType().getReward()
        );
        assertThat(GameVars.getMoney().get(), is(money + r));
    }

    @Test
    public void testNormalEnemyDeathAwardsMoney() {
        GameVars.setDifficulty(1);
        var money = GameVars.getMoney().get();
        EnemyComponent e = new EnemyComponent(EnemyType.ENEMY2);
        int r = e.getType().getReward();
        GameVars.setMoney(
            GameVars.getMoney().getValue() + e.getType().getReward()
        );
        assertThat(GameVars.getMoney().get(), is(money + r));
    }

    @Test
    public void testStrongEnemyDeathAwardsMoney() {
        GameVars.setDifficulty(2);
        var money = GameVars.getMoney().get();
        EnemyComponent e = new EnemyComponent(EnemyType.ENEMY3);
        int r = e.getType().getReward();
        GameVars.setMoney(
            GameVars.getMoney().getValue() + e.getType().getReward()
        );
        assertThat(GameVars.getMoney().get(), is(money + r));
    }

    @Test
    public void testRifleTowerDamageOnEnemy() {
        EnemyComponent e = new EnemyComponent(EnemyType.ENEMY3);
        int h = e.getType().getHealth();
        BulletComponent b = new BulletComponent(TowerType.TOWER1.getDamage(), false);
        int d = b.getDamage();
        e.getType().setHealth(
            e.getType().getHealth() - b.getDamage()
        );
        assertThat(e.getType().getHealth(), is(h - d));
    }

    @Test
    public void testSniperTowerDamageOnEnemy() {
        EnemyComponent e = new EnemyComponent(EnemyType.ENEMY3);

        int h = e.getType().getHealth();
        BulletComponent b = new BulletComponent(TowerType.TOWER2.getDamage(), false);

        int d = b.getDamage();
        e.getType().setHealth(e.getType().getHealth() - b.getDamage());

        assertThat(e.getType().getHealth(), is(h - d));
    }

    @Test
    public void testLaserTowerDamageOnEnemy() {
        EnemyComponent e = new EnemyComponent(EnemyType.ENEMY3);
        int h = e.getType().getHealth();
        BulletComponent b = new BulletComponent(TowerType.TOWER3.getDamage(), true);

        int d = b.getDamage();
        e.getType().setHealth(e.getType().getHealth() - b.getDamage());

        assertThat(e.getType().getHealth(), is(h - d));
    }

    @Test
    public void testRestartGame() {
        GameVars.setDifficulty(0);
        GameVars.initVars();

        assertThat(GameVars.getMoney().get(), is(GameVars.getOriginalMoney()));
        assertThat(GameVars.getLives().get(), is(GameVars.getOriginalLives()));
    }

    @Test
    public void testWeakEnemyTakeDamage() {
        List<TowerType> towerTypes = new ArrayList<>(Arrays.asList(TowerType.values()));

        for (TowerType t : towerTypes) {
            EnemyComponent e = new EnemyComponent(EnemyType.ENEMY1);
            int h = e.getType().getHealth();
            BulletComponent b = new BulletComponent(t.getDamage(), t.getBulletType() == 3);
            int d = b.getDamage();
            e.getType().setHealth(e.getType().getHealth() - b.getDamage());

            assertThat(e.getType().getHealth(), is(h - d));
        }
    }

    @Test
    public void testNormalEnemyTakeDamage() {
        List<TowerType> towerTypes = new ArrayList<>(Arrays.asList(TowerType.values()));

        for (TowerType t : towerTypes) {
            EnemyComponent e = new EnemyComponent(EnemyType.ENEMY2);
            int h = e.getType().getHealth();
            BulletComponent b = new BulletComponent(t.getDamage(), t.getBulletType() == 3);
            int d = b.getDamage();
            e.getType().setHealth(e.getType().getHealth() - b.getDamage());

            assertThat(e.getType().getHealth(), is(h - d));
        }
    }

    @Test
    public void testStrongEnemyTakeDamage() {
        List<TowerType> towerTypes = new ArrayList<>(Arrays.asList(TowerType.values()));

        for (TowerType t : towerTypes) {
            EnemyComponent e = new EnemyComponent(EnemyType.ENEMY3);
            int h = e.getType().getHealth();
            BulletComponent b = new BulletComponent(t.getDamage(), t.getBulletType() == 3);
            int d = b.getDamage();
            e.getType().setHealth(e.getType().getHealth() - b.getDamage());

            assertThat(e.getType().getHealth(), is(h - d));
        }
    }

    @Test
    public void testWeakEnemyMoneyOnDeath() {
        EnemyComponent e = new EnemyComponent(EnemyType.ENEMY1);
        int beforeEnemyDeathMoney = GameVars.getMoney().getValue();
        int afterEnemyDeathMoney = GameVars.getMoney().getValue() + e.getType().getReward();
        assertThat(afterEnemyDeathMoney - beforeEnemyDeathMoney, is(e.getType().getReward()));
    }

    @Test
    public void testNormalEnemyMoneyOnDeath() {
        EnemyComponent e = new EnemyComponent(EnemyType.ENEMY2);
        int beforeEnemyDeathMoney = GameVars.getMoney().getValue();
        int afterEnemyDeathMoney = GameVars.getMoney().getValue() + e.getType().getReward();
        assertThat(afterEnemyDeathMoney - beforeEnemyDeathMoney, is(e.getType().getReward()));
    }

    @Test
    public void testStrongEnemyMoneyOnDeath() {
        EnemyComponent e = new EnemyComponent(EnemyType.ENEMY3);
        int beforeEnemyDeathMoney = GameVars.getMoney().getValue();
        int afterEnemyDeathMoney = GameVars.getMoney().getValue() + e.getType().getReward();
        assertThat(afterEnemyDeathMoney - beforeEnemyDeathMoney, is(e.getType().getReward()));
    }

    @Test
    public void testEnemyTypesHaveDifferentTravelSpeed() {
        List<EnemyType> enemyTypes = new ArrayList<>(Arrays.asList(EnemyType.values()));

        boolean enemiesHaveDifferentSpeeds = true;

        for (int i = 0; i < enemyTypes.size(); i++) {
            for (int j = i + 1; j < enemyTypes.size(); j++) {
                if (enemyTypes.get(i).getSpeed() == enemyTypes.get(j).getSpeed()) {
                    enemiesHaveDifferentSpeeds = false;
                    break;
                }
            }
        }
        assertThat(enemiesHaveDifferentSpeeds, is(true));
    }

    @Test
    public void testEnemyTypesHaveDifferentHealth() {
        List<EnemyType> enemyTypes = new ArrayList<>(Arrays.asList(EnemyType.values()));

        boolean enemiesHaveDifferentHealth = true;

        for (int i = 0; i < enemyTypes.size(); i++) {
            for (int j = i + 1; j < enemyTypes.size(); j++) {
                if (enemyTypes.get(i).getHealth() == enemyTypes.get(j).getHealth()) {
                    enemiesHaveDifferentHealth = false;
                    break;
                }
            }
        }
        assertThat(enemiesHaveDifferentHealth, is(true));
    }

    @Test
    public void testEnemyTypesHaveDifferentDamageDealtToMonument() {
        List<EnemyType> enemyTypes = new ArrayList<>(Arrays.asList(EnemyType.values()));

        boolean enemiesHaveDifferentDamageDealtToMonument = true;

        for (int i = 0; i < enemyTypes.size(); i++) {
            for (int j = i + 1; j < enemyTypes.size(); j++) {
                if (enemyTypes.get(i).getDamage() == enemyTypes.get(j).getDamage()) {
                    enemiesHaveDifferentDamageDealtToMonument = false;
                    break;
                }
            }
        }
        assertThat(enemiesHaveDifferentDamageDealtToMonument, is(true));
    }

    @Test
    public void testEnemyTypesHaveDifferentReward() {
        List<EnemyType> enemyTypes = new ArrayList<>(Arrays.asList(EnemyType.values()));

        boolean enemiesHaveDifferentReward = true;

        for (int i = 0; i < enemyTypes.size(); i++) {
            for (int j = i + 1; j < enemyTypes.size(); j++) {
                if (enemyTypes.get(i).getReward() == enemyTypes.get(j).getReward()) {
                    enemiesHaveDifferentReward = false;
                    break;
                }
            }
        }
        assertThat(enemiesHaveDifferentReward, is(true));
    }
}
