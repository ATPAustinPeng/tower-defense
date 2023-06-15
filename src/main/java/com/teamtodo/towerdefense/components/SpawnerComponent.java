package com.teamtodo.towerdefense.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.LocalTimer;
import javafx.geometry.Point2D;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Akash Chakka
 * @version 1.3.0
 * @deprecated As of 1.3.1 update, replaced by EnemyWaveFactory
 */
public class SpawnerComponent extends Component {

    private LocalTimer spawnTimer;

    private final Queue<List<Integer>> waves = new LinkedList<>();

    //These two Lists must be the same length
    private final Queue<Integer> enemies = new LinkedList<>();
    private final Queue<Integer> spawnDelay = new LinkedList<>();

    private int nextEnemy;

    /*
    TO-DO Get list of enemies
    public SpawnerComponent(int[] enemies) {
        this.enemies = enemies;
    }
     */

    @Override
    public void onAdded() {
        spawnTimer = FXGL.newLocalTimer();
        spawnTimer.capture();

        Collections.addAll(
            waves,
            Arrays.asList(1, 1, 1, 1, 1, 2),
            Arrays.asList(1, 1, 2, 2, 2, 3),
            Arrays.asList(3, 2, 2, 2, 2, 2)
        );


        enemies.addAll(waves.remove());
        nextEnemy = enemies.remove();
    }

    @Override
    public void onUpdate(double tpf) {
        if (spawnTimer.elapsed(Duration.seconds(2))) {
            if (nextEnemy > 0) {
                spawn();
                spawnTimer.capture();
            } else if (nextEnemy == 0) {
                nextEnemy = enemies.remove();
                spawnTimer.capture();
            }
        }
    }

    private void spawn() {
        String enemy = "Enemy" + nextEnemy;

        Point2D position = getEntity().getPosition();
        Point2D startOffset = new Point2D(50, 50); // Start at middle of spawner

        FXGL.spawn(enemy, position);

        //If there are still enemies, pop from queue
        enemies.stream().findFirst().ifPresentOrElse(
            next -> {
                nextEnemy = next;
                enemies.remove();
            },
            //Else wave is over
            () -> {
                System.out.println("Wave Completed lambda");
                waves.stream().findAny().ifPresentOrElse((arg) -> nextWave(), () -> {
                    System.out.println("Combat Over lambda");
                    nextEnemy = -1;
                });
            });

        /* Equivalent code
        if (!enemies.isEmpty()) {
            nextEnemy = enemies.remove();
        } else {
            // Next Wave
            System.out.println("Wave Completed");
            if (!waves.isEmpty()) {
                nextWave(); // Remove Later
            } else {
                System.out.println("Combat Over");
                nextEnemy = -1;
            }
        }
         */
    }

    public void nextWave() {
        if (!waves.isEmpty()) {
            enemies.addAll(waves.remove());
            nextEnemy = enemies.remove();
        }
    }
}
