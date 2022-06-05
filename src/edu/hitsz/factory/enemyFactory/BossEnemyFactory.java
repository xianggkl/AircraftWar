package edu.hitsz.factory.enemyFactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.application.Game;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class BossEnemyFactory implements IEnemyFactory{
    @Override
    public AbstractAircraft getEnemyAircraft_Easy() {
        AbstractAircraft enemyAir;
        enemyAir = new BossEnemy(
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2)*1,
                1,
                0,
                1000,
                9,
                100
        );
        return enemyAir;
    }

    @Override
    public AbstractAircraft getEnemyAircraft_Common() {
        AbstractAircraft enemyAir;
        enemyAir = new BossEnemy(
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2)*1,
                1,
                0,
                1500 + Game.time / 100,
                12,
                200 + Game.time/300
        );
        return enemyAir;
    }

    @Override
    public AbstractAircraft getEnemyAircraft_Hard() {
        AbstractAircraft enemyAir;
        enemyAir = new BossEnemy(
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2)*1,
                1,
                0,
                2000 + Game.time / 80,
                13,
                200 + Game.time/200
        );
        return enemyAir;
    }
}
