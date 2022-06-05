package edu.hitsz.application;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.factory.enemyFactory.BossEnemyFactory;
import edu.hitsz.factory.enemyFactory.EliteEnemyFactory;
import edu.hitsz.factory.enemyFactory.MobEnemyFactory;

import java.awt.image.BufferedImage;

public class HardGame extends Game{
    public HardGame()
    {
        super();
        //修改敌机产生的最大数量
        this.enemyMaxNumber = 20;
        //修改boss机出现的分数阈值
        this.bossScoreThreshold = 100;
        //修改飞机产生周期
        this.cycleDuration = 600;
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
        int time = Game.time;
        double airRandom = 0.2;
        double temp = ((double) time / 100000);
        if (Game.score % 50 == 0)
        {
            if (airRandom > 0)
            {
                airRandom = 0.2 - temp;
            }
            if (this.cycleDuration >= 400)
            {
                this.cycleDuration -= temp*1000;
            }
        }

        Boolean mob = (random <= airRandom && random >= 0);
        Boolean elite =(random > airRandom && random <= 1);
        Boolean boss = (score % bossScoreThreshold <= 10 && score % bossScoreThreshold >=0) && (score >= bossScoreThreshold) && (this.boss == null);
        if (mob)
        {
            MobEnemyFactory mobEnemyFactory = new MobEnemyFactory();
            enemy = mobEnemyFactory.getEnemyAircraft_Hard();
        }
        if (elite)
        {
            EliteEnemyFactory eliteEnemyFactory = new EliteEnemyFactory();
            enemy = eliteEnemyFactory.getEnemyAircraft_Hard();
            System.out.println("精英机血量达到:" + enemy.getHp());
            System.out.println("精英机攻击力达到:" + enemy.power);
        }
        if (boss)
        {
            BossEnemyFactory bossEnemyFactory = new BossEnemyFactory();
            enemy = bossEnemyFactory.getEnemyAircraft_Hard();
            this.boss = (BossEnemy) enemy;
            bossThread.setSTOP(false);
            System.out.println("Boss机产生!!!");
            System.out.println("Boss机血量达到:" + enemy.getHp());
            System.out.println("Boss机攻击力达到:" + enemy.power);
        }
        System.out.println("精英机产生概率提高到:" + (0.8 + temp));
        System.out.println("敌机周期降低为:" + this.cycleDuration);
        System.out.println();
        return enemy;
    }

    /**
     * 修改游戏背景图
     */
    @Override
    protected BufferedImage getDesImage() {
        return ImageManager.BACKGROUND_IMAGE_HARD;
    }
}
