package com.teamtodo.towerdefense;

import com.teamtodo.towerdefense.tower.TowerManager;
import com.teamtodo.towerdefense.tower.TowerType;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the logic behind TowerManager class
 *
 * @author Richard Luo
 * @version 1.1
 * @since 1.1
 */
public class TowerManagerTest {

    @Test
    public void testTowerBuyNoMoney() {
        GameVars.setSelectedTower(TowerType.TOWER1);
        GameVars.setDifficulty(0); // Resets money
        GameVars.setMoney(Integer.MIN_VALUE);

        assertThat(TowerManager.IS_SUFFICIENT_MONEY.getAsBoolean(), is(Boolean.FALSE));
    }

    @Test
    public void testTowerBuyMoney() {
        GameVars.setMoney(1000);
        GameVars.setSelectedTower(TowerType.TOWER1);
        GameVars.setDifficulty(0);

        assertThat(TowerManager.IS_SUFFICIENT_MONEY.getAsBoolean(), is(Boolean.TRUE));

        var money = GameVars.getMoney().get();
        TowerManager.PURCHASE.run();

        assertThat(GameVars.getMoney().get(), is(money - TowerType.TOWER1.getPrice()));
    }
}
