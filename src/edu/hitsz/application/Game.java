package edu.hitsz.application;

import edu.hitsz.AircraftStrategy.AircraftStrategy;
import edu.hitsz.AircraftStrategy.ShootSpreadStrategy;
import edu.hitsz.AircraftStrategy.ShootStraightStrategy;
import edu.hitsz.Events.Events;
import edu.hitsz.aircraft.*;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.prop.AbstractProp;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public abstract class Game extends JPanel {

    private int backGroundTop = 0;

    public static int score = 0;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private int timeInterval = 40;

    private final HeroAircraft heroAircraft;
    public static int time = 0;
    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;
    private final List<BaseBullet> heroBullets;
    private final List<AbstractProp> enemyProps;

    protected int enemyMaxNumber;
    protected int bossScoreThreshold;
    protected MusicThread backGroundThread;
    protected MusicThread bossThread;
    protected MusicThread bulletShootThread;
    protected BossEnemy boss = null;
    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    protected int cycleDuration;
    private List<AbstractAircraft> enemyAircrafts;
    private List<BaseBullet> enemyBullets;
    public Game() {
        heroAircraft = HeroAircraft.get_HeroAircraftInstance();
        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        enemyProps = new LinkedList<>();

        //Scheduled 线程池，用于定时任务调度
        executorService = new ScheduledThreadPoolExecutor(1);

        backGroundThread = new MusicThread(VideoManager.BGM_BACKGROUND_WAV);
        bossThread = new MusicThread(VideoManager.BGM_BOSS_WAV);
        bulletShootThread = new MusicThread(VideoManager.BGM_BULLET_SHOOT_WAV);
        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);
    }

    public HeroAircraft getHeroAircraft()
    {
        return this.heroAircraft;
    }

    public List<AbstractAircraft> getEnemyAircrafts()
    {
        return this.enemyAircrafts;
    }
    private int cycleTime = 0;

    public List<BaseBullet> getEnemyBullets()
    {
        return this.enemyBullets;
    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public final void action() {
        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {
            time += timeInterval;

            //音乐
            if (Events.musicType == Events.MusicType.OPEN)
            {
                while(!backGroundThread.isAlive())
                {
                    backGroundThread = new MusicThread(VideoManager.BGM_BACKGROUND_WAV);
                    backGroundThread.start();
                }

                while(boss!=null && !bossThread.isAlive())
                {
                    bossThread = new MusicThread(VideoManager.BGM_BOSS_WAV);
                    bossThread.start();
                }

            }

            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                // 新敌机产生
                if (enemyAircrafts.size() < enemyMaxNumber) {
                    enemyAircrafts.add(getAircraft());
                }
                // 飞机射出子弹
                shootAction();
            }

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            //道具移动
            propsMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            // 游戏结束检查
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                closeAllMusic();
                new MusicThread(VideoManager.BGM_GAME_OVER_WAV).start();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (Events.IS_GAME_STAYING){
                    Events.IS_GAME_STAYING.notify();
                }
                executorService.shutdown();
            }
        };

        /*
          以固定延迟时间进行执行
          本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);
    }

    /**
     * 随机产生一架战机
     * 返回敌机类型
     */
    public abstract AbstractAircraft getAircraft();

    private void closeAllMusic()
    {
        backGroundThread.setSTOP(true);
        bossThread.setSTOP(true);
        bulletShootThread.setSTOP(true);
    }

    //***********************
    //      Action 各部分
    //***********************

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private void shootAction() {
        enemyShoot();
        heroShoot();
    }

    private void enemyShoot()
    {
        // TODO 敌机射击
        for (AbstractAircraft enemyAircraft:enemyAircrafts)
        {
            AircraftStrategy strategyEnemy = new ShootStraightStrategy();
            if (enemyAircraft.getClass().equals(BossEnemy.class))
            {
                strategyEnemy = new ShootSpreadStrategy();
            }
            enemyAircraft.setStrategy(strategyEnemy);
            enemyBullets.addAll(enemyAircraft.shoot());
        }
    }
    private void heroShoot()
    {
        AircraftStrategy strategy;
        if (!heroAircraft.getSpread())
        {
            strategy = new ShootStraightStrategy();
        }
        else{
            strategy = new ShootSpreadStrategy();
        }
        if (Events.musicType.equals(Events.MusicType.OPEN))
        {
            new MusicThread(VideoManager.BGM_BULLET_SHOOT_WAV).start();
        }
        heroAircraft.setStrategy(strategy);
        heroBullets.addAll(heroAircraft.shoot());
    }

    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }

    private void propsMoveAction()
    {
        for (AbstractProp enemyProp : enemyProps){
            enemyProp.forward();
        }
    }


    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // TODO 敌机子弹攻击英雄
        for (BaseBullet bullet : enemyBullets)
        {
            if (bullet.notValid())
            {
                continue;
            }
            if (heroAircraft.notValid())
            {
                continue;
            }
            if (heroAircraft.crash(bullet))
            {
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
            }
        }
        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    if (Events.musicType.equals(Events.MusicType.OPEN))
                    {
                        new MusicThread(VideoManager.BGM_BULLET_HIT_WAV).start();
                    }
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }
        // Todo: 我方获得道具，道具生效
        for (AbstractProp prop:enemyProps)
        {
            if (heroAircraft.crash(prop))
            {
                prop.propActive(this);
                prop.vanish();
                if (Events.musicType.equals(Events.MusicType.OPEN))
                {
                    new MusicThread(VideoManager.BGM_SUPPLY_GET_WAV).start();
                }
            }
        }
        for (AbstractAircraft enemyAircraft:enemyAircrafts)
        {
            if (enemyAircraft.notValid()) {
                //当敌机是Boss机时
                if (enemyAircraft.getClass().equals(BossEnemy.class))
                {
                    this.boss = null;
                    bossThread.setSTOP(true);
                }
                // TODO 获得分数，产生道具补给
                score += 10;
                AbstractProp prop = getProp(enemyAircraft);
                if (prop != null)
                {
                    enemyProps.add(prop);
                }
            }
        }

    }
    /**
     * 产生道具Prop
     */
    private AbstractProp getProp(AbstractAircraft enemyAircraft)
    {
        AbstractProp prop;
        if (enemyAircraft.getClass().equals(MobEnemy.class))
        {
            prop = ((MobEnemy) enemyAircraft).propDrop();
        }
        else if (enemyAircraft.getClass().equals(EliteEnemy.class))
        {
            prop = ((EliteEnemy) enemyAircraft).propDrop();
        }
        else if (enemyAircraft.getClass().equals(BossEnemy.class))
        {
            prop = ((BossEnemy) enemyAircraft).propDrop();
        }
        else{
            prop = null;
        }
        return prop;
    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * 3. 检查英雄机生存
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        enemyProps.removeIf(AbstractFlyingObject::notValid);
    }


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动

        BufferedImage desImage;
        desImage = getDesImage();

        g.drawImage(desImage, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(desImage, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);

        paintImageWithPositionRevised(g, enemyAircrafts);
        paintImageWithPositionRevised(g,enemyProps);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }

    protected abstract BufferedImage getDesImage();

}
