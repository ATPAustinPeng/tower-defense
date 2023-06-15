package com.teamtodo.towerdefense.enemy;

import com.almasb.fxgl.dsl.FXGL;
import com.teamtodo.towerdefense.GameVars;
import javafx.util.Duration;

import java.util.*;

/**
 * EnemyWaveFactory spawns in Enemy Waves, which contains a list of enemies and the timings to
 * spawn them. Once instantiated, start a wave through {@link #startWave()}, which will
 * automatically handle all the logic for spawning that wave. This class is meant to be triggered
 * by a start wave button.
 *
 * @author Austin Peng
 * @author Richard Luo
 * @author Akash Chakka
 * @version 1.3.1
 * @since 1.3.1
 */
public class EnemyWaveFactory {

    private final Queue<EnemyWave> waves = new LinkedList<>();
    private EnemyWave currentWave;

    /**
     * Instantiates EnemyWaveFactory with 3 waves of enemies and a uniform spawn delay of 2 sec
     */
    public EnemyWaveFactory() {
        Collections.addAll(waves,
            new EnemyWave(Arrays.asList(EnemyType.ENEMY1, EnemyType.ENEMY1, EnemyType.ENEMY1),
                Collections.nCopies(3, 2)),
            new EnemyWave(Arrays.asList(EnemyType.ENEMY1, EnemyType.ENEMY1, EnemyType.ENEMY2),
                Collections.nCopies(3, 2)),
            new EnemyWave(Arrays.asList(EnemyType.ENEMY2, EnemyType.ENEMY3),
                Collections.nCopies(2, 4))
        );

        currentWave = waves.remove();
    }

    /**
     * Starts the next wave by initiating a Timer that calls EnemyWave.run() according to the
     * specified spawn delay
     */
    public void startWave() {
        System.out.println("Next wave");
        currentWave.start();

        FXGL.getGameTimer().runAtIntervalWhile(() -> {
            if (GameVars.getIsGameOver()) {
                finishWave();
                return;
            }
            currentWave.run();
            if (currentWave.getEnemiesLeft() <= 0) {
                finishWave();
            }
        }, Duration.seconds(currentWave.getNextDelay()), currentWave.isRunningProperty());
    }

    /**
     * Ends the current wave regardless of game state
     */
    public void finishWave() {
        currentWave.end();

        if (waves.isEmpty()) {
            GameVars.setIsGameOver(true);
            System.out.println("Game Over!!");
        } else {
            currentWave = waves.remove();
        }
        System.out.println("Wave Ended");
    }

    public int getEnemiesLeft() {
        if (waves.size() == 0) {
            return 0;
        }

        return waves.peek().getEnemiesLeft();
    }
}
