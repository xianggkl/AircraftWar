package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.prop.AbstractProp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

class EliteEnemyTest {

    private EliteEnemy eliteEnemy;
    private LinkedList<String> lprops;
    @BeforeEach
    void setUp() {
        eliteEnemy = new EliteEnemy(
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2)*1,
                0,
                8,
                100,
                2,
                40
        );
        lprops = new LinkedList<>();
        lprops.add("class edu.hitsz.prop.HPProp");
        lprops.add("class edu.hitsz.prop.FireProp");
        lprops.add("class edu.hitsz.prop.BombProp");
    }

    @AfterEach
    void tearDown() {
        eliteEnemy = null;
        lprops = null;
    }

    @Test
    void propDrop() {
        AbstractProp absProp = eliteEnemy.propDrop();
        assumeFalse(absProp == null);
        System.out.println(absProp.getClass().toString());
        assertTrue(lprops.contains(absProp.getClass().toString()));
    }

    @Test
    void shoot() {
        assertNotNull(eliteEnemy.shoot());
    }
}