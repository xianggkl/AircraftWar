package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.EasyGame;
import edu.hitsz.application.Game;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HPPropTest {

    private HPProp hpProp;
    private Game game;
    @BeforeEach
    void setUp() {
        hpProp = new HPProp(
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2)*1,
                1,
                2
        );
        game = new EasyGame();
    }

    @AfterEach
    void tearDown() {
        hpProp = null;
        game = null;
    }

    @Test
    void forward() {
        int locX = hpProp.getLocationX();
        int locY = hpProp.getLocationY();
        hpProp.forward();
        assertTrue(Math.abs(hpProp.getLocationX() - locX) == hpProp.getSpeedX());
        assertTrue(Math.abs(hpProp.getLocationY() - locY) == hpProp.getSpeedY());
    }

    @Test
    void propActive() {
        HeroAircraft heroAircraft = game.getHeroAircraft();
        int actualHP = heroAircraft.getHp() + HPProp.Hp;
        hpProp.propActive(game);
        assertTrue(actualHP == heroAircraft.getHp());
    }
}