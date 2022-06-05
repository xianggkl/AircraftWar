package edu.hitsz.factory.enemyFactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class MobEnemyFactory implements IEnemyFactory{
    @Override
    public AbstractAircraft getEnemyAircraft_Easy() {
        AbstractAircraft enemyAir = null;
        enemyAir = new MobEnemy(
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2)*1,
                0,
                5,
                30
        );
        return enemyAir;
    }

    @Override
    public AbstractAircraft getEnemyAircraft_Common() {
        AbstractAircraft enemyAir = null;
        enemyAir = new MobEnemy(
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2)*1,
                0,
                6,
                60
        );
        return enemyAir;
    }

    @Override
    public AbstractAircraft getEnemyAircraft_Hard() {
        AbstractAircraft enemyAir = null;
        enemyAir = new MobEnemy(
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2)*1,
                0,
                7,
                100
        );
        return enemyAir;
    }
}
