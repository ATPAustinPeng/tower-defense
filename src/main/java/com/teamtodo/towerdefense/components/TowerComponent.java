package com.teamtodo.towerdefense.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.AutoRotationComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.LocalTimer;
import com.teamtodo.towerdefense.TowerDefenseType;
import com.teamtodo.towerdefense.tower.TowerType;
import javafx.util.Duration;

import javafx.geometry.Point2D;

/**
 * Tower Component contains the base logic that a tower class should have, including shoot logic
 *
 * @author Richard Luo
 * @author Akash Chakka
 * @version 1.6.0
 * @since 1.0
 */
public class TowerComponent extends Component {

    private LocalTimer shootTimer;
    private TowerType type;

    public TowerComponent(TowerType type) {
        this.type = type;
    }

    @Override
    public void onAdded() {
        shootTimer = FXGL.newLocalTimer();
        shootTimer.capture();
    }

    @Override
    public void onUpdate(double tpf) {
        if (shootTimer.elapsed(Duration.seconds(type.getAttackDelay()))) {
            FXGL.getGameWorld()
                .getClosestEntity(entity, e -> e.isType(TowerDefenseType.ENEMY))
                .ifPresent(nearestEnemy -> {
                    shoot(nearestEnemy);
                    shootTimer.capture();
                });
        }
    }

    private void shoot(Entity enemy) {
        //Handle shoot logic here
        Point2D position = getEntity().getPosition();
        Point2D startOffset = new Point2D(25, 0); // Start at top of tower
        Point2D endOffset = new Point2D(50, 50); // Aim for center of enemy
        Point2D direction = (enemy.getPosition().subtract(position)).add(endOffset);

        String bulletType = "Bullet" + type.getBulletType();
        double range = type.getRange();
        double bulletSpeed = type.getBulletSpeed();
        double bulletTimeAlive = range / bulletSpeed;

        double distance = enemy.getPosition().distance(position);

        //Spawn bullet when entity is in range
        if (distance < range) {
            Entity bullet = FXGL.spawn(bulletType, position.add(startOffset));

            bullet.addComponent(new ProjectileComponent(direction, bulletSpeed));
            this.getEntity().rotateToVector(enemy.getPosition());
            bullet.addComponent(new DestroyComponent(bullet, bulletTimeAlive));
        }
    }

    public TowerType getType() {
        return this.type;
    }

    public void setType(TowerType type) {
        this.type = type;
    }
}
