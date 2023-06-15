package com.teamtodo.towerdefense;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.teamtodo.towerdefense.components.*;
import com.teamtodo.towerdefense.enemy.EnemyType;
import com.teamtodo.towerdefense.tower.TowerType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Tower Defense Factory spawns the entities in Tower Defense (e.g. towers, enemies).
 *
 * @author Austin Peng
 * @author Richard Luo
 * @author Akash Chakka
 * @version 1.6.0
 * @since 1.0
 */
public class TowerDefenseFactory implements EntityFactory {

    @Spawns("Map")
    public Entity spawnMap(SpawnData data) throws FileNotFoundException {
        FileInputStream temp = new FileInputStream("./src/main/resources/assets/textures/map.png");
        ImageView backgroundImage = new ImageView(new Image(temp));
        backgroundImage.preserveRatioProperty();
        backgroundImage.setFitWidth(FXGL.getAppWidth());
        backgroundImage.setFitHeight(FXGL.getAppHeight());

        return FXGL.entityBuilder(data)
            .type(TowerDefenseType.MAP)
            .view(backgroundImage)
            .build();
    }

    @Spawns("ENEMY1")
    public Entity spawnEnemy1(SpawnData data) throws FileNotFoundException {
        FileInputStream temp = new FileInputStream(
            "./src/main/resources/assets/textures/enemy1.png");
        ImageView image = new ImageView(new Image(temp));
        image.preserveRatioProperty();
        image.setFitWidth(100);
        image.setFitHeight(100);

        return FXGL.entityBuilder(data)
            .type(TowerDefenseType.ENEMY)
            .viewWithBBox(image)
            .with(new CollidableComponent(true))
            .with(new EnemyComponent(EnemyType.ENEMY1))
            .build();
    }

    @Spawns("ENEMY2")
    public Entity spawnEnemy2(SpawnData data) throws FileNotFoundException {
        FileInputStream temp = new FileInputStream(
            "./src/main/resources/assets/textures/enemy2.png");
        ImageView image = new ImageView(new Image(temp));
        image.preserveRatioProperty();
        image.setFitWidth(100);
        image.setFitHeight(100);

        return FXGL.entityBuilder(data)
            .type(TowerDefenseType.ENEMY)
            .viewWithBBox(image)
            .with(new CollidableComponent(true))
            .with(new EnemyComponent(EnemyType.ENEMY2))
            .build();
    }

    @Spawns("ENEMY3")
    public Entity spawnEnemy3(SpawnData data) throws FileNotFoundException {
        FileInputStream temp = new FileInputStream(
            "./src/main/resources/assets/textures/enemy3.png");
        ImageView image = new ImageView(new Image(temp));
        image.preserveRatioProperty();
        image.setFitWidth(100);
        image.setFitHeight(100);

        return FXGL.entityBuilder(data)
            .type(TowerDefenseType.ENEMY)
            .viewWithBBox(image)
            .with(new CollidableComponent(true))
            .with(new EnemyComponent(EnemyType.ENEMY3))
            .build();
    }

    @Spawns("TOWER1")
    public Entity spawnTower1(SpawnData data) throws FileNotFoundException {
        ImageView image =
            new ImageView(new Image(new FileInputStream(TowerType.TOWER1.getImagePath())));
        image.preserveRatioProperty();
        image.setFitWidth(75);
        image.setFitHeight(75);

        return FXGL.entityBuilder(data)
            .type(TowerDefenseType.TOWER)
            .viewWithBBox(image)
            .with(new CollidableComponent(true))
            .with(new TowerComponent(TowerType.TOWER1))
            .build();
    }

    @Spawns("TOWER11")
    public Entity spawnTower11(SpawnData data) throws FileNotFoundException {
        ImageView image =
            new ImageView(new Image(new FileInputStream(TowerType.TOWER11.getImagePath())));
        image.preserveRatioProperty();
        image.setFitWidth(75);
        image.setFitHeight(75);

        return FXGL.entityBuilder(data)
            .type(TowerDefenseType.TOWER)
            .viewWithBBox(image)
            .with(new CollidableComponent(true))
            .with(new TowerComponent(TowerType.TOWER11))
            .build();
    }

    @Spawns("TOWER2")
    public Entity spawnTower2(SpawnData data) throws FileNotFoundException {
        ImageView image =
            new ImageView(new Image(new FileInputStream(TowerType.TOWER2.getImagePath())));
        image.preserveRatioProperty();
        image.setFitWidth(75);
        image.setFitHeight(75);

        return FXGL.entityBuilder(data)
            .type(TowerDefenseType.TOWER)
            .viewWithBBox(image)
            .with(new CollidableComponent(true))
            .with(new TowerComponent(TowerType.TOWER2))
            .build();
    }

    @Spawns("TOWER22")
    public Entity spawnTower22(SpawnData data) throws FileNotFoundException {
        ImageView image =
            new ImageView(new Image(new FileInputStream(TowerType.TOWER22.getImagePath())));
        image.preserveRatioProperty();
        image.setFitWidth(75);
        image.setFitHeight(75);

        return FXGL.entityBuilder(data)
            .type(TowerDefenseType.TOWER)
            .viewWithBBox(image)
            .with(new CollidableComponent(true))
            .with(new TowerComponent(TowerType.TOWER22))
            .build();
    }

    @Spawns("TOWER3")
    public Entity spawnTower3(SpawnData data) throws FileNotFoundException {
        ImageView image =
            new ImageView(new Image(new FileInputStream(TowerType.TOWER3.getImagePath())));
        image.preserveRatioProperty();
        image.setFitWidth(75);
        image.setFitHeight(75);

        return FXGL.entityBuilder(data)
            .type(TowerDefenseType.TOWER)
            .viewWithBBox(image)
            .with(new CollidableComponent(true))
            .with(new TowerComponent(TowerType.TOWER3))
            .build();
    }

    @Spawns("TOWER33")
    public Entity spawnTower33(SpawnData data) throws FileNotFoundException {
        ImageView image =
            new ImageView(new Image(new FileInputStream(TowerType.TOWER33.getImagePath())));
        image.preserveRatioProperty();
        image.setFitWidth(75);
        image.setFitHeight(75);

        return FXGL.entityBuilder(data)
            .type(TowerDefenseType.TOWER)
            .viewWithBBox(image)
            .with(new CollidableComponent(true))
            .with(new TowerComponent(TowerType.TOWER33))
            .build();
    }

    @Spawns("Bullet1") // Hit 1 enemy
    public Entity spawnBullet1(SpawnData data) throws FileNotFoundException {
        FileInputStream temp = new FileInputStream(
            "./src/main/resources/assets/textures/bullet1.png");
        ImageView image = new ImageView(new Image(temp));
        image.preserveRatioProperty();
        image.setFitWidth(15);
        image.setFitHeight(5);

        return FXGL.entityBuilder(data)
            .type(TowerDefenseType.BULLET)
            .with(new BulletComponent(TowerType.TOWER1.getDamage(), false))
            .viewWithBBox(image)
            .with(new CollidableComponent(true))
            .with(new OffscreenCleanComponent())
            .build();
    }

    @Spawns("Bullet2") // Hit 1 enemy
    public Entity spawnBullet2(SpawnData data) throws FileNotFoundException {
        FileInputStream temp = new FileInputStream(
            "./src/main/resources/assets/textures/bullet2.png");
        ImageView image = new ImageView(new Image(temp));
        image.preserveRatioProperty();
        image.setFitWidth(20);
        image.setFitHeight(5);

        return FXGL.entityBuilder(data)
            .type(TowerDefenseType.BULLET)
            .with(new BulletComponent(TowerType.TOWER2.getDamage(), false))
            .viewWithBBox(image)
            .with(new CollidableComponent(true))
            .with(new OffscreenCleanComponent())
            .build();
    }

    @Spawns("Bullet3") // Pierce Enemies
    public Entity spawnBullet3(SpawnData data) throws FileNotFoundException {
        FileInputStream temp = new FileInputStream(
            "./src/main/resources/assets/textures/bullet3.png");
        ImageView image = new ImageView(new Image(temp));
        image.preserveRatioProperty();
        image.setFitWidth(30);
        image.setFitHeight(30);

        return FXGL.entityBuilder(data)
            .type(TowerDefenseType.BULLET)
            .with(new BulletComponent(TowerType.TOWER3.getDamage(), true))
            .viewWithBBox(image)
            .with(new CollidableComponent(true))
            .with(new OffscreenCleanComponent())
            .build();
    }

    @Spawns("Monument")
    public Entity spawnMonument(SpawnData data) throws FileNotFoundException {
        FileInputStream temp = new FileInputStream(
            "./src/main/resources/assets/textures/monument.png");
        ImageView image = new ImageView(new Image(temp));
        image.preserveRatioProperty();
        image.setFitWidth(100);
        image.setFitHeight(100);

        return FXGL.entityBuilder(data)
            .type(TowerDefenseType.MONUMENT)
            .viewWithBBox(image)
            .with(new CollidableComponent(true))
            //.with(new MonumentComponent(100)) // Lives handled separately
            .build();
    }

    @Spawns("Spawner")
    @Deprecated
    public Entity spawnSpawner(SpawnData data) throws FileNotFoundException {
        FileInputStream temp = new FileInputStream(
            "./src/main/resources/assets/textures/spawner.png");
        ImageView image = new ImageView(new Image(temp));
        image.preserveRatioProperty();
        image.setFitWidth(100);
        image.setFitHeight(100);

        return FXGL.entityBuilder(data)
            .type(TowerDefenseType.SPAWNER)
            .with(new SpawnerComponent())
            .viewWithBBox(image)
            //.with(new MonumentComponent(80)) // Lives handled separately
            .build();
    }

    //TODO - replace with tilemap
    @Spawns("Path")
    public Entity spawnPath(SpawnData data) {
        return FXGL.entityBuilder(data)
            .type(TowerDefenseType.PATH)
            .viewWithBBox(
                new Rectangle(TowerDefenseApp.getScreenWidth() - 450, 40, Color.AQUAMARINE))
            .with(new CollidableComponent(true))
            .build();
    }
}
