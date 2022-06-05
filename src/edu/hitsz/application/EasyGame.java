package edu.hitsz.application;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.factory.enemyFactory.BossEnemyFactory;
import edu.hitsz.factory.enemyFactory.EliteEnemyFactory;
import edu.hitsz.factory.enemyFactory.MobEnemyFactory;

import java.awt.image.BufferedImage;

public class EasyGame extends Game{
    public EasyGame()
    {
        super();
        //修改敌机产生的最大数量
        this.enemyMaxNumber = 20;
        //修改boss机出现的分数阈值
        this.bossScoreThreshold = 400;
        //修改飞机产生周期
        this.cycleDuration = 1000;
    }

    /**
     * 修改精英与普通敌机的产生概率
     * 修改精英机 boss机 普通机的火力
     * 修改敌机的血量和火力
     * 修改了boss机随时间增加血量增加
     */
    @Override
    public AbstractAircraft getAircraft() {
        AbstractAircraft enemy = null;
        float random = (float)Math.random();
        double airRandom = 0.6;
        Boolean mob = (random <= airRandom && random >= 0);
        Boolean elite =(random > airRandom && random <= 1);
        Boolean boss = (score % bossScoreThreshold <= 10 && score % bossScoreThreshold >=0) && (score >= bossScoreThreshold) && (this.boss == null);
        if (mob)
        {
            MobEnemyFactory mobEnemyFactory = new MobEnemyFactory();
            enemy = mobEnemyFactory.getEnemyAircraft_Easy();
        }
        if (elite)
        {
            EliteEnemyFactory eliteEnemyFactory = new EliteEnemyFactory();
            enemy = eliteEnemyFactory.getEnemyAircraft_Easy();
        }
        if (boss)
        {
            BossEnemyFactory bossEnemyFactory = new BossEnemyFactory();
            enemy = bossEnemyFactory.getEnemyAircraft_Easy();
            this.boss = (BossEnemy) enemy;
            bossThread.setSTOP(false);
        }
        return enemy;
    }

    /**
     * 修改游戏背景图
     */
    @Override
    protected BufferedImage getDesImage() {
        return ImageManager.BACKGROUND_IMAGE_EASY;
    }
}
