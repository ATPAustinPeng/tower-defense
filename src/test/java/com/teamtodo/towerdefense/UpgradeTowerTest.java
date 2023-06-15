package com.teamtodo.towerdefense;

import com.teamtodo.towerdefense.components.BulletComponent;
import com.teamtodo.towerdefense.components.EnemyComponent;
import com.teamtodo.towerdefense.components.TowerComponent;
import com.teamtodo.towerdefense.enemy.EnemyType;
import com.teamtodo.towerdefense.tower.TowerManager;
import com.teamtodo.towerdefense.tower.TowerType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Tests the logic behind upgrade and boss functionality
 *
 * @author Alek Jacoby
 * @version 1.5.0
 * @since 1.1
 */
public class UpgradeTowerTest {

    @Test
    public void testUpgradeSmallTowerSubtractMoney() {
        GameVars.setMoney(1000);
        GameVars.setSelectedTower(TowerType.TOWER11);
        GameVars.setDifficulty(0);

        assertThat(TowerManager.IS_SUFFICIENT_MONEY.getAsBoolean(), is(Boolean.TRUE));

        var money = GameVars.getMoney().get();
        TowerManager.PURCHASE.run();

        assertThat(GameVars.getMoney().get(), is(money - TowerType.TOWER11.getPrice()));
    }

    @Test
    public void testUpgradeMediumTowerSubtractMoney() {
        GameVars.setMoney(1000);
        GameVars.setSelectedTower(TowerType.TOWER22);
        GameVars.setDifficulty(0);

        assertThat(TowerManager.IS_SUFFICIENT_MONEY.getAsBoolean(), is(Boolean.TRUE));

        var money = GameVars.getMoney().get();
        TowerManager.PURCHASE.run();

        assertThat(GameVars.getMoney().get(), is(money - TowerType.TOWER22.getPrice()));
    }

    @Test
    public void testUpgradeBigTowerSubtractMoney() {
        GameVars.setMoney(1000);
        GameVars.setSelectedTower(TowerType.TOWER33);
        GameVars.setDifficulty(0);

        assertThat(TowerManager.IS_SUFFICIENT_MONEY.getAsBoolean(), is(Boolean.TRUE));

        var money = GameVars.getMoney().get();
        TowerManager.PURCHASE.run();

        assertThat(GameVars.getMoney().get(), is(money - TowerType.TOWER33.getPrice()));
    }

    @Test
    public void testUpgradeSmallTowerStats() {
        TowerComponent t1 = new TowerComponent(TowerType.TOWER1);
        TowerComponent t2 = new TowerComponent(TowerType.TOWER11);
        assertThat(t1.getType().getDamage() < t2.getType().getDamage(), is(true));
        assertThat(t1.getType().getRange() < t2.getType().getRange(), is(true));
        assertThat(t1.getType().getBulletSpeed() < t2.getType().getBulletSpeed(), is(true));
    }

    @Test
    public void testUpgradeMediumTowerStats() {
        TowerComponent t1 = new TowerComponent(TowerType.TOWER2);
        TowerComponent t2 = new TowerComponent(TowerType.TOWER22);
        assertThat(t1.getType().getDamage() < t2.getType().getDamage(), is(true));
        assertThat(t1.getType().getRange() < t2.getType().getRange(), is(true));
        assertThat(t1.getType().getBulletSpeed() < t2.getType().getBulletSpeed(), is(true));
    }

    @Test
    public void testUpgradeBigTowerStats() {
        TowerComponent t1 = new TowerComponent(TowerType.TOWER3);
        TowerComponent t2 = new TowerComponent(TowerType.TOWER33);
        assertThat(t1.getType().getDamage() < t2.getType().getDamage(), is(true));
        assertThat(t1.getType().getRange() < t2.getType().getRange(), is(true));
        assertThat(t1.getType().getBulletSpeed() < t2.getType().getBulletSpeed(), is(true));
    }

    @Test
    public void testTowerUpgradeImage() {
        TowerComponent t1 = new TowerComponent(TowerType.TOWER1);
        TowerComponent t2 = new TowerComponent(TowerType.TOWER11);
        assertThat(t1.getType().getImagePath() != t2.getType().getImagePath(), is(true));

        t1 = new TowerComponent(TowerType.TOWER2);
        t2 = new TowerComponent(TowerType.TOWER22);
        assertThat(t1.getType().getImagePath() != t2.getType().getImagePath(), is(true));

        t1 = new TowerComponent(TowerType.TOWER3);
        t2 = new TowerComponent(TowerType.TOWER33);
        assertThat(t1.getType().getImagePath() != t2.getType().getImagePath(), is(true));
    }

    @Test
    public void testTowerUpgradeNoMoney() {
        GameVars.setDifficulty(0); // Resets money
        GameVars.setMoney(Integer.MIN_VALUE);

        GameVars.setSelectedTower(TowerType.TOWER11);
        assertThat(TowerManager.IS_SUFFICIENT_MONEY.getAsBoolean(), is(Boolean.FALSE));

        GameVars.setSelectedTower(TowerType.TOWER22);
        assertThat(TowerManager.IS_SUFFICIENT_MONEY.getAsBoolean(), is(Boolean.FALSE));

        GameVars.setSelectedTower(TowerType.TOWER33);
        assertThat(TowerManager.IS_SUFFICIENT_MONEY.getAsBoolean(), is(Boolean.FALSE));
    }

    @Test
    public void testFinalBossTakeDamage() {
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
    public void testFinalBossReachMonument() {
        GameVars.setDifficulty(0);
        var lives = GameVars.getLives().get();
        EnemyComponent e = new EnemyComponent(EnemyType.ENEMY3);
        int d = e.getType().getDamage();
        GameVars.setLives(
                GameVars.getLives().getValue() - e.getType().getDamage()
        );
        assertThat(GameVars.getLives().get(), is(lives - d));
    }
}