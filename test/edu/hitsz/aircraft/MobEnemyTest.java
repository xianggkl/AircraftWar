package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MobEnemyTest {

    private MobEnemy mobEnemy;
    @BeforeEach
    void setUp() {
        mobEnemy = new MobEnemy(
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2)*1,
                0,
                5,
                30
        );
    }

    @AfterEach
    void tearDown() {
        mobEnemy = null;
    }

    @ParameterizedTest
    @ValueSource(ints = {10,52,56,88,1})
    void decreaseHp(int hp) {
        int actualHp = mobEnemy.getHp() - hp;
        mobEnemy.decreaseHp(hp);
        if (actualHp <= 0)
        {
            assertTrue(mobEnemy.notValid() == true);
        }
        else{
            assertTrue(mobEnemy.getHp() == actualHp);
        }
    }

    @Test
    void vanish() {
        boolean isValid = !mobEnemy.notValid();
        mobEnemy.vanish();
        assertTrue(isValid == mobEnemy.notValid());
    }

    @Test
    void propDrop() {
        assertNull(mobEnemy.propDrop());
    }
}