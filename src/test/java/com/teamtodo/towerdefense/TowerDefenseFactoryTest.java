package com.teamtodo.towerdefense;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.teamtodo.towerdefense.ui.TowerDefenseMainMenu;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Austin Peng
 * @author Lucas Yim
 * @author Richard Luo
 * @author Akash Chakka
 * @author Alek Jacoby
 **/
public class TowerDefenseFactoryTest {

    /*
    @BeforeAll
    public static void initJFX() {
        Thread t = new Thread("JavaFX Init Thread") {
            @Override
            public void run() {
                String[] args = new String[1];
                GameApplication.launch(TowerDefenseApp.class, new String[0]);

            }
        };

        t.setDaemon(true);
        t.start();
    }
     */

    @Test
    public void testNewEnemy() throws FileNotFoundException {
        assertThat(new TowerDefenseFactory().spawnEnemy1(new SpawnData()),
            instanceOf(Entity.class));
    }

    @Test
    public void testDifficultySelection() {
        GameVars.setDifficulty(0);
        assertThat(GameVars.getDifficulty(), is(0));

        GameVars.setDifficulty(1);
        assertThat(GameVars.getDifficulty(), is(1));

        GameVars.setDifficulty(2);
        assertThat(GameVars.getDifficulty(), is(2));
    }

    @Test
    public void testValidName() {
        assertThat(TowerDefenseMainMenu.SANITIZE_NAME.test(" "), is(false));
        assertThat(TowerDefenseMainMenu.SANITIZE_NAME.test(""), is(false));
        assertThat(TowerDefenseMainMenu.SANITIZE_NAME.test("asdfasdf"), is(true));
    }

    @Test
    public void testDifficultySelectionMoney() {
        GameVars.setDifficulty(2);
        assertThat(GameVars.getMoney().getValue(), is(GameVars.getOriginalMoney() - 100 * 2));

        GameVars.setDifficulty(0);
        assertThat(GameVars.getMoney().getValue(), is(GameVars.getOriginalMoney()));

        GameVars.setDifficulty(1);
        assertThat(GameVars.getMoney().getValue(), is(GameVars.getOriginalMoney() - 100));
    }

    @Test
    public void testDifficultySelectionHealth() {

        //Test hard mode
        GameVars.setDifficulty(2);
        assertThat(GameVars.getLives().getValue(), is(GameVars.getOriginalLives() - 10 * 2));

        //Test easy mode
        GameVars.setDifficulty(0);
        assertThat(GameVars.getLives().getValue(), is(GameVars.getOriginalLives() - 10 * 0));

        //Test medium mode
        GameVars.setDifficulty(1);
        assertThat(GameVars.getLives().getValue(), is(GameVars.getOriginalLives() - 10 * 1));
    }

    @Test
    public void testEnterName() {
        GameVars.setName("Awestin");
        assertThat(GameVars.getName(), is("Awestin"));

        GameVars.setName("Lukeeeee");
        assertThat(GameVars.getName(), is("Lukeeeee"));
    }
}
