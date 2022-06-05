package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.factory.propFactory.BombPropFactory;
import edu.hitsz.factory.propFactory.FirePropFactory;
import edu.hitsz.factory.propFactory.HPPropFactory;
import edu.hitsz.prop.AbstractProp;
public class BossEnemy extends AbstractAircraft{
    private boolean isExist = false;
    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp,int shootnum,int power) {
        super(locationX, locationY, speedX, speedY, hp);
        this.shootNum = shootnum;
        this.power = power;
        this.direction = 1;
    }

    @Override
    public void update() {
        //do nothing
    }

    public boolean getExist()
    {
        return isExist;
    }

    public void setExist(boolean b)
    {
        isExist = b;
    }

    /**
     * 较低机率得到随机一个道具
     * 得到则返回道具,否则返回null
     */
    public AbstractProp propDrop()
    {
        if (Math.random() <= 1)
        {
            AbstractProp prop;
            float chance = (float)Math.random();
            Boolean fire = (chance <= AbstractProp.FIRE_PROP_DROP_CHANCE && chance >=0 );
            Boolean bomb = (chance <= (AbstractProp.FIRE_PROP_DROP_CHANCE + AbstractProp.BOMB_PROP_DROP_CHANCE) && chance > AbstractProp.FIRE_PROP_DROP_CHANCE);
            Boolean hp = (chance <= (AbstractProp.FIRE_PROP_DROP_CHANCE + AbstractProp.BOMB_PROP_DROP_CHANCE + AbstractProp.HP_PROP_DROP_CHANCE) && chance > (AbstractProp.FIRE_PROP_DROP_CHANCE + AbstractProp.BOMB_PROP_DROP_CHANCE));
            if (fire)
            {
                FirePropFactory firePropFactory = new FirePropFactory();
                prop = firePropFactory.GetEnemyProp(this.locationX,this.locationY);
            }
            else if (bomb)
            {
                BombPropFactory bombPropFactory = new BombPropFactory();
                prop = bombPropFactory.GetEnemyProp(this.locationX,this.locationY);
            }
            else if (hp)
            {
                HPPropFactory hpPropFactory = new HPPropFactory();
                prop = hpPropFactory.GetEnemyProp(this.locationX,this.locationY);
            }
            else {prop = null;}
            return prop;
        }
        else{ return null;}
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }
}
