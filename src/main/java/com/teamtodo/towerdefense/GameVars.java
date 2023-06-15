package com.teamtodo.towerdefense;

import com.teamtodo.towerdefense.enemy.EnemyWaveFactory;
import com.teamtodo.towerdefense.tower.TowerType;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * {@code GameVars} class contains a set of static getters and setters
 * for necessary game variables. The game variables are listed as follows:
 * <pre>
 *     name
 *     difficulty
 *     lives
 *     money
 *     gameStatus
 *     selectedTower
 *
 *     enemy count
 *     enemy kill count
 * </pre>
 *
 * @author Austin Peng
 * @author Richard Luo
 * @author Akash Chakka
 * @version 1.6.0
 * @since 1.0
 */
public final class GameVars {

    /**
     * No initialization of this class
     */
    private GameVars() {
        //This can never be called
    }

    private static String name;

    /* ==== Public Global Constants ==== */
    public static final int ORIGINAL_MONEY = 1000;
    public static final int ORIGINAL_LIVES = 100;
    public static final int INITIAL_ENEMY_COUNT = 8; //TODO link to WaveFactory

    private static final SimpleIntegerProperty DIFFICULTY;
    private static final SimpleIntegerProperty LIVES;
    private static final SimpleIntegerProperty MONEY;
    private static final SimpleIntegerProperty ENEMY_COUNT;

    private static boolean isGameOver;
    private static TowerType selectedTower;
    private static final SimpleStringProperty TOWER_DESCRIPTION;

    /*==== End Game Stats ==== */
    private static final SimpleIntegerProperty ENEMY_KILL_COUNT;
    private static final SimpleIntegerProperty NUM_TOWERS_BOUGHT;
    private static final SimpleIntegerProperty DAMAGE_DEALT;

    static {
        /* =========== Static Initializer =========== */
        DIFFICULTY = new SimpleIntegerProperty(0);
        LIVES = new SimpleIntegerProperty(ORIGINAL_LIVES);
        MONEY = new SimpleIntegerProperty(ORIGINAL_MONEY); // Integer.MAX_VALUE // 10000
        ENEMY_COUNT = new SimpleIntegerProperty(6);
        TOWER_DESCRIPTION = new SimpleStringProperty("");

        isGameOver = false; //False -> game not over, True -> game over
        selectedTower = TowerType.TOWER1;

        ENEMY_KILL_COUNT = new SimpleIntegerProperty(0);
        NUM_TOWERS_BOUGHT = new SimpleIntegerProperty(0);
        DAMAGE_DEALT = new SimpleIntegerProperty(0);
    }

    public static void initVars() {
        setDifficulty(DIFFICULTY.get());
        TowerDefenseApp.setWaveFactory(new EnemyWaveFactory());

        ENEMY_COUNT.set(INITIAL_ENEMY_COUNT);
        isGameOver = false;
        selectedTower = TowerType.TOWER1;
        setTowerDescription();

        //End game stats
        ENEMY_KILL_COUNT.set(0);
        NUM_TOWERS_BOUGHT.set(0);
        DAMAGE_DEALT.set(0);
    }

    /**
     * @return {@link TowerType} representing the current tower type
     * @see TowerType
     */
    public static TowerType getSelectedTower() {
        return selectedTower;
    }

    /**
     * @param selectedTower {@link TowerType} specifying the type of tower selected
     * @see TowerType
     */
    public static void setSelectedTower(TowerType selectedTower) {
        GameVars.selectedTower = selectedTower;
    }

    /**
     * @return String specifying the name of the player
     */
    public static String getName() {
        return name;
    }

    /**
     * @param name String specifying the name of the player to set to
     */
    public static void setName(String name) {
        GameVars.name = name;
    }

    /**
     * @return Integer between 0 and 2 inclusive specifying the game difficulty,
     * where [0, 1, 2] corresponds to [easy, medium, hard] respectively
     */
    public static int getDifficulty() {
        return DIFFICULTY.get();
    }

    /**
     * Sets the game difficulty to easy, medium, or hard and updates lives/money accordingly.
     *
     * @param difficulty Integer where 0 specifies easy mode, 1 is medium mode, 2 is hard mode
     */
    public static void setDifficulty(int difficulty) {
        assert (difficulty <= 2 && difficulty >= 0);
        DIFFICULTY.set(difficulty);
        LIVES.set(ORIGINAL_LIVES - 10 * difficulty);
        MONEY.set(ORIGINAL_MONEY - 100 * difficulty);
    }

    /**
     * @return An observable SimpleIntegerProperty that specifies number of lives left
     */
    public static SimpleIntegerProperty getLives() {
        return LIVES;
    }

    /**
     * @param lives Integer specifying how number of lives left
     */
    public static void setLives(int lives) {
        LIVES.set(lives);
    }

    /**
     * @return An observable SimpleIntegerProperty that specifies how much money the player has
     */
    public static SimpleIntegerProperty getMoney() {
        return MONEY;
    }

    /**
     * @param money Integer specifying the amount of money to set
     */
    public static void setMoney(int money) {
        MONEY.set(money);
    }

    /**
     * Subtracts cost from total amount of money
     *
     * @param cost Integer specifying cost of purchase
     */
    public static void buy(int cost) {
        setMoney(MONEY.get() - cost);
    }

    public static SimpleStringProperty getTowerDescription() {
        return TOWER_DESCRIPTION;
    }

    public static void setTowerDescription() {
        if (selectedTower.equals(TowerType.TOWER1)) {
            TOWER_DESCRIPTION.set(
                "Rifle Tower\n\nDescription: Holds one rifleman with medium range who"
                    + " does medium damage to single targets.");
        } else if (selectedTower.equals(TowerType.TOWER2)) {
            TOWER_DESCRIPTION.set(
                "Sniper Tower\n\nDescription: Holds one sniper with long range who "
                    + "does heavy damage to single targets.");
        } else if (selectedTower.equals(TowerType.TOWER3)) {
            TOWER_DESCRIPTION.set(
                "Laser Tower\n\nDescription: Holds one laser cannon with medium range"
                    + " which does light damage to all targets in a straight line.");
        } else {
            TOWER_DESCRIPTION.set("Select a tower");
        }
    }

    public static boolean getIsGameOver() {
        return isGameOver;
    }

    public static void setIsGameOver(boolean isGameOver) {
        GameVars.isGameOver = isGameOver;
    }

    /**
     * @return An observable SimpleIntegerProperty that specifies number of enemies left
     */
    public static SimpleIntegerProperty getEnemyCount() {
        return ENEMY_COUNT;
    }

    /**
     * @param enemyCount Integer specifying how number of enemies left
     */
    public static void setEnemyCount(int enemyCount) {
        ENEMY_COUNT.set(enemyCount);
    }

    public static int getOriginalMoney() {
        return ORIGINAL_MONEY;
    }

    public static int getOriginalLives() {
        return ORIGINAL_LIVES;
    }

    public static SimpleIntegerProperty getEnemyKillCount() {
        return ENEMY_KILL_COUNT;
    }

    public static void setEnemyKillCount(int enemyKillCount) {
        ENEMY_KILL_COUNT.set(enemyKillCount);
    }

    public static SimpleIntegerProperty getNumTowersBought() {
        return NUM_TOWERS_BOUGHT;
    }

    public static void setNumTowersBought(int numTowersBought) {
        NUM_TOWERS_BOUGHT.set(numTowersBought);
    }

    public static SimpleIntegerProperty getDamageDealt() {
        return DAMAGE_DEALT;
    }

    public static void setDamageDealt(int damageDealt) {
        DAMAGE_DEALT.set(damageDealt);
    }
}
