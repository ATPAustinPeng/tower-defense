package com.teamtodo.towerdefense.tower;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.teamtodo.towerdefense.GameVars;
import javafx.geometry.Point2D;

import java.util.function.BooleanSupplier;

/**
 * TowerManager is a Singleton class that handles the logic behind internal stats
 * for each tower and also controls the buying logic for each of the towers. Instance is accessed
 * through {@code TowerManager.INSTANCE}.
 *
 * @author Richard Luo
 * @version 1.1
 * @since 1.1
 */
public enum TowerManager {

    INSTANCE; //Singleton instance

    //Returns whether player's money is sufficient to buy selected tower
    public static final BooleanSupplier IS_SUFFICIENT_MONEY =
        () -> GameVars.getMoney().get() >= GameVars.getSelectedTower().getPrice();

    //Queries GameVars to subtract appropriate amount of money
    public static final Runnable PURCHASE =
        () -> GameVars.buy(GameVars.getSelectedTower().getPrice());

    /**
     * Places down a tower at the specified position. If there is enough money to buy the tower,
     * the selected tower is placed down at the location, and money is subtracted from the player's
     * total budget. However, if there is insufficient money, no tower will be placed and false will
     * be returned.
     *
     * @param mousePos A 2D point (x, y) representing the desired location of the tower
     * @return True if the tower is placed successfully, false otherwise
     */
    public boolean placeTower(Point2D mousePos) {
        //Handle money logic
        if (!IS_SUFFICIENT_MONEY.getAsBoolean()) {
            return false;
        }

        FXGL.spawn(GameVars.getSelectedTower().name(),
            new SpawnData(mousePos.getX() - 40, mousePos.getY() - 40)
        );

        //Subtract money from bank
        PURCHASE.run();

        //Update Game Vars
        GameVars.setNumTowersBought(GameVars.getNumTowersBought().getValue() + 1);

        return true;
    }
}
