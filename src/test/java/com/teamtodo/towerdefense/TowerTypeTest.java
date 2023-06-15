package com.teamtodo.towerdefense;

import com.teamtodo.towerdefense.tower.TowerType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Tests the logic behind TowerType class
 *
 * @author Austin Peng
 * @author Akash Chakka
 * @version 1.5.0
 * @since 1.1
 */
public class TowerTypeTest {

    @Test
    public void testTowerPriceEasy() {
        GameVars.setDifficulty(0);
        assertThat(
                TowerType.TOWER1.getPrice(),
                is(TowerType.TOWER1.getOriginalPrice()));

        assertThat(
                TowerType.TOWER2.getPrice(),
                is(TowerType.TOWER2.getOriginalPrice()));

        assertThat(
                TowerType.TOWER3.getPrice(),
                is(TowerType.TOWER3.getOriginalPrice()));
    }

    @Test
    public void testTowerPriceMedium() {
        GameVars.setDifficulty(1);
        int changeInPrice = 50 * GameVars.getDifficulty();
        assertThat(
                TowerType.TOWER1.getPrice(),
                is(TowerType.TOWER1.getOriginalPrice() + changeInPrice));

        assertThat(
                TowerType.TOWER2.getPrice(),
                is(TowerType.TOWER2.getOriginalPrice() + changeInPrice));

        assertThat(
                TowerType.TOWER3.getPrice(),
                is(TowerType.TOWER3.getOriginalPrice() + changeInPrice));

    }

    @Test
    public void testTowerPriceHard() {
        GameVars.setDifficulty(2);
        int changeInPrice = 50 * GameVars.getDifficulty();
        assertThat(
                TowerType.TOWER1.getPrice(),
                is(TowerType.TOWER1.getOriginalPrice() + changeInPrice));

        assertThat(
                TowerType.TOWER2.getPrice(),
                is(TowerType.TOWER2.getOriginalPrice() + changeInPrice));

        assertThat(
                TowerType.TOWER3.getPrice(),
                is(TowerType.TOWER3.getOriginalPrice() + changeInPrice));
    }

    @Test
    public void testTowerDescriptionRifleTower() {
        GameVars.setSelectedTower(TowerType.TOWER1);
        GameVars.setTowerDescription();

        assertThat(
                GameVars.getTowerDescription().getValue(),
                containsString("Rifle Tower"));
    }

    @Test
    public void testTowerDescriptionSniperTower() {
        GameVars.setSelectedTower(TowerType.TOWER2);
        GameVars.setTowerDescription();

        assertThat(
                GameVars.getTowerDescription().getValue(),
                containsString("Sniper Tower"));
    }

    @Test
    public void testTowerDescriptionLaserTower() {
        GameVars.setSelectedTower(TowerType.TOWER3);
        GameVars.setTowerDescription();

        assertThat(
                GameVars.getTowerDescription().getValue(),
                containsString("Laser Tower"));
    }

    @Test
    public void testRifleTowerRange() {
        assertThat(TowerType.TOWER1.canHit(TowerType.TOWER1.getRange() + 1), is(false));
        assertThat(TowerType.TOWER1.canHit(TowerType.TOWER1.getRange() - 1), is(true));
    }

    @Test
    public void testSniperTowerRange() {
        assertThat(TowerType.TOWER1.canHit(TowerType.TOWER1.getRange() + 1), is(false));
        assertThat(TowerType.TOWER1.canHit(TowerType.TOWER1.getRange() - 1), is(true));
    }

    @Test
    public void testLaserTowerRange() {
        assertThat(TowerType.TOWER1.canHit(TowerType.TOWER1.getRange() + 1), is(false));
        assertThat(TowerType.TOWER1.canHit(TowerType.TOWER1.getRange() - 1), is(true));
    }
}
