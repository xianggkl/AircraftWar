package edu.hitsz.factory.enemyFactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.application.Game;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class EliteEnemyFactory implements IEnemyFactory{
    @Override
    public AbstractAircraft getEnemyAircraft_Easy() {
        int random = (int)(Math.random()*3) - 1;
        AbstractAircraft enemyAir = null;
        enemyAir = new EliteEnemy(
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2)*1,
                4 * random,
                5,
                100,
                2,
                50
        );
        return enemyAir;
    }

    @Override
    public AbstractAircraft getEnemyAircraft_Common() {
        int random = (int)(Math.random()*3) - 1;
        AbstractAircraft enemyAir = null;
        enemyAir = new EliteEnemy(
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2)*1,
                5*random,
                6,
                150,
                3,
                50 + Game.time/300
        );
        return enemyAir;
    }

    @Override
    public AbstractAircraft getEnemyAircraft_Hard() {
        int random = (int)(Math.random()*3) - 1;
        AbstractAircraft enemyAir = null;
        enemyAir = new EliteEnemy(
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2)*1,
                5*random,
                7,
                200,
                3,
                80 + Game.time/200
        );
        return enemyAir;
    }
}