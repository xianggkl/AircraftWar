package edu.hitsz.aircraft;

import edu.hitsz.AircraftStrategy.AircraftStrategy;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

/**
 * 所有种类飞机的抽象父类：
 * 敌机（BOSS, ELITE, MOB），英雄飞机
 *
 * @author hitsz
 */
public abstract class AbstractAircraft extends AbstractFlyingObject {
    public int shootNum;//子弹一次发射数量
    protected int hp;
    public int power;   //子弹伤害
    public int direction;//子弹射击方向 (向上发射：1，向下发射：-1
    protected int maxHp;
    protected AircraftStrategy strategy;

    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
    }

    public void setStrategy(AircraftStrategy strategy)
    {
        this.strategy = strategy;
    }

    public void decreaseHp(int decrease){
        hp -= decrease;
        if(hp <= 0){
            hp=0;
            vanish();
        }
    }

    public void increaseHp(int increase){
        if (this.isValid == true && this.hp < maxHp)
        {
            hp += increase;
        }
        if(hp <= 0){
            hp=0;
            vanish();
        }
    }

    public int getHp() {
        return hp;
    }

    /**
     * 飞机射击方法，可射击对象必须实现
     * @return
     *  可射击对象需实现，返回子弹
     *  非可射击对象空实现，返回null
     */
    public List<BaseBullet> shoot()
    {
        return this.strategy.aircraftShoot(this);
    }

    public abstract void update();
}


