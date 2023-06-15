package com.teamtodo.towerdefense.enemy;

import com.almasb.fxgl.dsl.FXGL;
import com.teamtodo.towerdefense.TowerDefenseApp;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * EnemyWave handles spawning for all the enemies within a specific wave. Instantiation requires
 * a list of all enemies and the spawn timings (set as delays from previous enemy) for each enemy.
 * EnemyWave is to only be started and run by EnemyWaveFactory.
 *
 * @author Austin Peng
 * @author Richard Luo
 * @author Akash Chakka
 * @version 1.6.0
 * @since 1.3.1
 */
public class EnemyWave {

    private EnemyType nextEnemyType;
    private int nextDelay;
    private final SimpleBooleanProperty isRunning = new SimpleBooleanProperty(false);

    //These two Lists must be the same length
    private final Queue<EnemyType> enemies = new LinkedList<>();
    private final Queue<Integer> spawnDelay = new LinkedList<>();

    /**
     * Creates an EnemyWave with the list of enemies and a list of spawn delay for each enemy.
     * The way EnemyWave works is it spawns an enemy, waits for the specified spawn delay, and
     * then spawns the next enemy.
     *
     * @param enemies    List of enemy types
     * @param spawnDelay List of spawn delay in seconds after each enemy
     */
    public EnemyWave(@NotNull List<EnemyType> enemies, @NotNull List<Integer> spawnDelay) {
        this.enemies.addAll(enemies);
        this.spawnDelay.addAll(spawnDelay);

        //Sanity check each enemy has its own spawn delay
        if (enemies.size() != spawnDelay.size()) {
            throw new IllegalArgumentException("List of enemies has a different size than the "
                + "number of delays!");
        }
        nextEnemyType = this.enemies.peek();
        nextDelay = this.spawnDelay.peek();
    }

    public void start() {
        isRunning.set(true);
        System.out.println("These are the enemies in this wave: ");
        for (EnemyType temp : enemies) {
            System.out.println(temp.name());
        }
    }

    /**
     * Spawns the next enemy and checks whether the wave has finished
     */
    public void run() {
        if (nextEnemyType != null) {
            spawn();
        }
    }

    /**
     * Ends this current wave
     */
    public void end() {
        isRunning.set(false);
    }

    /**
     * @return Integer specifying the spawn delay for the next enemy
     */
    public int getNextDelay() {
        return nextDelay;
    }

    public SimpleBooleanProperty isRunningProperty() {
        return isRunning;
    }

    private void spawn() {
        String enemy = nextEnemyType.name();
        System.out.println("Spawn " + enemy);

        // Start at middle of spawner TODO
        Point2D position = new Point2D(250, TowerDefenseApp.getScreenHeight() / 2. - 30);

        FXGL.spawn(enemy, position);

        //If there are still enemies, pop from queue
        if (enemies.isEmpty()) {
            System.out.println("Wave is completed");
            end();

            return;
        }

        enemies.remove();
        nextDelay = spawnDelay.remove();

        nextEnemyType = enemies.peek();
    }

    /**
     * @return Integer specifying the number of enemies left in this wave
     */
    public int getEnemiesLeft() {
        return enemies.size();
    }
}
