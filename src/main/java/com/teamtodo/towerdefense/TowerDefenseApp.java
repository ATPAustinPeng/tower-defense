package com.teamtodo.towerdefense;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.GameView;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.logging.Logger;
import com.almasb.fxgl.pathfinding.CellState;
import com.almasb.fxgl.pathfinding.astar.AStarGrid;
import com.teamtodo.towerdefense.collision.BulletEnemyHandler;
import com.teamtodo.towerdefense.collision.EnemyMonumentHandler;
import com.teamtodo.towerdefense.collision.TowerPathHandler;
import com.teamtodo.towerdefense.collision.TowerTowerHandler;
import com.teamtodo.towerdefense.enemy.EnemyWaveFactory;
import com.teamtodo.towerdefense.tower.TowerManager;
import com.teamtodo.towerdefense.ui.TowerDefenseMainMenu;
import com.teamtodo.towerdefense.ui.TowerMenu;
import com.teamtodo.towerdefense.ui.WaveStartButton;
import javafx.beans.binding.Bindings;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.app.scene.FXGLMenu;
import org.jetbrains.annotations.NotNull;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * Main Application for Tower Defense that launches the game.
 *
 * @author Austin Peng
 * @author Richard Luo
 * @author Lucas Yim
 * @author Alek Jacoby
 * @version 1.6.0
 * @since 1.0
 */
public class TowerDefenseApp extends GameApplication {

    private static int screenWidth = 1920;
    private static int screenHeight = 1080;
    private static Rectangle2D worldBounds = new Rectangle2D(0, 0, 1920, 1080);
    private static EnemyWaveFactory waveFactory;

    public static final SceneFactory SCENE_FACTORY = new SceneFactory() {
        @NotNull
        @Override
        public FXGLMenu newMainMenu() {
            System.out.println("Hi");
            return new TowerDefenseMainMenu();
        }
    };

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        //Start in fullscreen mode
        gameSettings.setWidth(screenWidth);
        gameSettings.setHeight(screenHeight);
        gameSettings.setFullScreenFromStart(true);
        gameSettings.setFullScreenAllowed(true);

        gameSettings.setNative(true);

        gameSettings.setTitle("Tower Defense");
        gameSettings.setVersion("1.2");
        gameSettings.setMainMenuEnabled(true);

        //Load configuration menu
        gameSettings.setSceneFactory(SCENE_FACTORY);
    }

    @Override
    protected void initGame() {
        //Game logic
        FXGL.getGameWorld().addEntityFactory(new TowerDefenseFactory());

        //Load in tilemap
        //var level = FXGL.setLevelFromMap("tilemap.tmx");
        AStarGrid grid =
            AStarGrid.fromWorld(getGameWorld(), screenWidth, screenHeight, 40, 40, (type) -> {
                //Do something about this
                if (Math.random() > 0.5) {
                    return CellState.NOT_WALKABLE;
                }

                return CellState.WALKABLE;
            });

        var log = Logger.get(GameApplication.class);
        log.debug("Level is loaded");

        FXGL.spawn("Path", 250, screenHeight / 2.);
        GameVars.setTowerDescription();

        var uiFactory = FXGL.getUIFactoryService();

        //Label name
        var name = uiFactory.newText(GameVars.getName(), Color.BLACK, 18);

        //Note: we don't need to bind name because GameVars.getName() will be populated before init
        name.setTranslateX(screenWidth - 200);
        name.setTranslateY(screenHeight - 200);
        FXGL.getGameScene().addGameView(new GameView(name, 1000));

        var description = uiFactory.newText(Bindings.createStringBinding(
            () -> GameVars.getTowerDescription().get(), GameVars.getTowerDescription()));

        description.setTranslateX(screenWidth / 2. - 300);
        description.setTranslateY(screenHeight - 100);
        description.setFill(Color.BLACK);

        FXGL.getGameScene().addGameView(new GameView(description, 1000));

        //Spawn first monument
        FXGL.spawn("Monument", screenWidth - 150, screenHeight / 2. - 30);

        //Label lives on top of monuments
        FXGL.getGameWorld().getEntitiesByType(TowerDefenseType.MONUMENT).forEach((var monument) -> {
            var lives = uiFactory.newText(Bindings.createStringBinding(
                () -> "Lives: " + GameVars.getLives().get(), GameVars.getLives()));

            lives.setFill(Color.BLACK);
            lives.setTranslateX(monument.getX() + 10);
            lives.setTranslateY(monument.getY() + 150);
            FXGL.getGameScene().addGameView(new GameView(lives, 1000));
        });

        //Label money
        var money = uiFactory.
            newText(Bindings.createStringBinding(() -> "Money: " + GameVars.getMoney().get(),
                GameVars.getMoney()));

        money.setFill(Color.BLACK);
        money.setTranslateX(25);
        money.setTranslateY(screenHeight - 200);
        FXGL.getGameScene().addGameView(new GameView(money, 1000));

        //Display TowerMenuUI
        //createTowerMenuUI();

        System.out.println(Companion.getAppHeight());

        FXGL.getGameScene().addGameView(new GameView(new TowerMenu(screenWidth,
            screenHeight), 0));

        //Display wave start button
        var waveButton = new WaveStartButton();
        waveButton.setTranslateX(Companion.getAppWidth() / 2f);
        waveButton.setTranslateY(Companion.getAppHeight() / 1.6f);
        FXGL.getGameScene().addGameView(new GameView(waveButton, 0));
    }

    public static boolean checkButton(Point2D mousePos) {
        System.out.println((Companion.getAppWidth() / 2f));
        return mousePos.getY() < (Companion.getAppHeight() / 1.6f) + 40
            && mousePos.getY() > (Companion.getAppHeight() / 1.6f) - 20
            && mousePos.getX() > (Companion.getAppWidth() / 2f)
            && mousePos.getX() < (Companion.getAppWidth() / 2f) + 200;
    }

    public static boolean checkPath(Point2D mousePos) {
        return mousePos.getY() < screenHeight / 2. - 35
            || mousePos.getY() > screenHeight / 2. + 85;
    }


    @Override
    protected void initInput() {
        Input input = FXGL.getInput();
        TowerManager towerManager = TowerManager.INSTANCE;

        input.addAction(new UserAction("Place Tower") {
            @Override
            protected void onActionBegin() {
                var mousePos = input.getMousePositionWorld();

                // y = [730, 866]
                // x = 0 - 240

                System.out.println(mousePos);
                System.out.println(checkButton(mousePos));
                System.out.println("^^^^^^^^^");

                if (worldBounds.contains(mousePos) && checkPath(mousePos)
                    && !checkButton(mousePos)) { // checkPath uneccessary
                    var status = towerManager.placeTower(mousePos);
                    System.out.println(status);
                }
            }
        }, MouseButton.PRIMARY);

        input.addAction(new UserAction("Money Hack") {
            @Override
            protected void onAction() {
                GameVars.setMoney(0xfffff);
            }
        }, KeyCode.BACK_SPACE);
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new BulletEnemyHandler());
        getPhysicsWorld().addCollisionHandler(new EnemyMonumentHandler());
        getPhysicsWorld().addCollisionHandler(new TowerTowerHandler());
        getPhysicsWorld().addCollisionHandler(new TowerPathHandler());
    }

    public static Rectangle2D getWorldBounds() {
        return worldBounds;
    }

    public static EnemyWaveFactory getWaveFactory() {
        return waveFactory;
    }

    public static void setWaveFactory(EnemyWaveFactory waveFactory) {
        TowerDefenseApp.waveFactory = waveFactory;
    }

    public static void main(String[] args) {
        //Get screen dimensions for fullscreen
        var screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        worldBounds = new Rectangle2D(0, 0, screenWidth, screenHeight - 100 - 40);

        launch(args); // Milestone 3
    }
}
