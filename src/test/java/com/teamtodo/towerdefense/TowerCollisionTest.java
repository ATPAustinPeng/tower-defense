package com.teamtodo.towerdefense;


import javafx.geometry.Point2D;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the logic behind TowerCollision class
 *
 * @author Alek Jacoby
 * @author Richard Luo
 * @version 1.1
 * @since 1.1
 */

public class TowerCollisionTest {

    @Test
    public void testTowerPlaceOutOfMap() throws InterruptedException {
        assertThat(
                TowerDefenseApp.getWorldBounds().contains(new Point2D(-1, -1)),
                is(false));

        assertThat(
                TowerDefenseApp.getWorldBounds().contains(new Point2D(Integer.MAX_VALUE, 1)),
                is(false));

        assertThat(
                TowerDefenseApp.getWorldBounds().contains(new Point2D(1, Integer.MAX_VALUE)),
                is(false));

        assertThat(
                TowerDefenseApp.getWorldBounds().contains(new Point2D(Integer.MIN_VALUE, 1)),
                is(false));
    }

    @Test
    public void testTowerPlaceOnPath() {
        assertThat(TowerDefenseApp.checkPath(new Point2D(10, 510)), is(false));
        assertThat(TowerDefenseApp.checkPath(new Point2D(10, 500)), is(true));
    }

    @Test
    public void testTowerPlaceOnEmptyMap() {
        assertThat(TowerDefenseApp.getWorldBounds().contains(new Point2D(469, 69)), is(true));
        assertThat(TowerDefenseApp.getWorldBounds().contains(new Point2D(0, 0)), is(true));
    }
}
