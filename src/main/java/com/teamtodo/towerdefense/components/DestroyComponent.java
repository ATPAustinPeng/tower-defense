package com.teamtodo.towerdefense.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.time.LocalTimer;
import javafx.util.Duration;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.Entity;

/**
 * Destroy Component which handles an enemy dying.
 *
 * @author Richard Luo
 * @author Akash Chakka
 * @version 1.1
 * @since 1.1
 */
public class DestroyComponent extends Component {

    private LocalTimer shootTimer;
    private Entity entity;
    private double timeAlive;

    public DestroyComponent(Entity entity, double timeAlive) {
        this.entity = entity;
        this.timeAlive = timeAlive;
    }

    @Override
    public void onAdded() {
        shootTimer = FXGL.newLocalTimer();
        shootTimer.capture();
    }

    @Override
    public void onUpdate(double tpf) {
        if (shootTimer.elapsed(Duration.seconds(timeAlive))) {
            entity.removeFromWorld();
        }
    }
}
