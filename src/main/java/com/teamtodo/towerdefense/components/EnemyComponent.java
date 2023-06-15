package com.teamtodo.towerdefense.components;

import com.almasb.fxgl.entity.component.Component;
import com.teamtodo.towerdefense.TowerDefenseApp;
import com.teamtodo.towerdefense.enemy.EnemyType;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Enemy Component which contains the base logic for enemies, including movement, speed ... etc.
 *
 * @author Austin Peng
 * @author Richard Luo
 * @author Akash Chakka
 * @version 1.5.0
 * @since 1.0
 */
public class EnemyComponent extends Component {

    private List<Point2D> waypoints;
    private Point2D nextWaypoint;

    private final EnemyType type;

    public EnemyComponent(EnemyType type) {
        this.type = type;
    }

    @Override
    public void onAdded() {
        waypoints = new ArrayList<>();
        waypoints.addAll(Arrays.asList(
            new Point2D(TowerDefenseApp.getScreenWidth() - 150,
                TowerDefenseApp.getScreenHeight() / 2. - 20)
        ));

        nextWaypoint = waypoints.remove(0);
    }

    @Override
    public void onUpdate(double tpf) {
        double speed = tpf * 60 * this.getType().getSpeed();

        Point2D velocity = nextWaypoint.subtract(entity.getPosition())
            .normalize()
            .multiply(speed);

        entity.translate(velocity);

        if (nextWaypoint.distance(entity.getPosition()) < speed) {
            entity.setPosition(nextWaypoint);

            if (!waypoints.isEmpty()) {
                nextWaypoint = waypoints.remove(0);
            }
        }
    }

    public EnemyType getType() {
        return type;
    }
}
