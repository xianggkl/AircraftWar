package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {

    /**
     * 使用单例模式创建英雄机
     */
    private volatile static HeroAircraft _HeroAircraftInstance;
    private boolean isSpread = false;

    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        shootNum = 5;
        power = 100;
        direction = -1;
    }

    public static HeroAircraft get_HeroAircraftInstance()
    {
        if (_HeroAircraftInstance == null)
        {
            synchronized (HeroAircraft.class){
                if (_HeroAircraftInstance == null)
                {
                    _HeroAircraftInstance = new HeroAircraft(
                            Main.WINDOW_WIDTH / 2,
                            Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
                            0,
                            -10,
                            10000
                    );
                }
            }
        }
        return _HeroAircraftInstance;
    }

    @Override
    public void update() {
        //do nothing
    }

    public boolean getSpread()
    {
        return isSpread;
    }
    public void setSpread(boolean b)
    {
        isSpread = b;
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }
}
