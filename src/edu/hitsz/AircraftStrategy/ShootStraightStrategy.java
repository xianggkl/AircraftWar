package edu.hitsz.AircraftStrategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class ShootStraightStrategy implements AircraftStrategy{

    @Override
    public List<BaseBullet> aircraftShoot(AbstractAircraft aircraft) {
        List<BaseBullet> res = new LinkedList<>();
        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY() + aircraft.direction*2;
        int speedX = 0;
        int speedY = aircraft.getSpeedY() + aircraft.direction*5;
        BaseBullet abstractBullet;
        for(int i=0; i<aircraft.shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            if (aircraft.getClass().equals(HeroAircraft.class))
            {
                abstractBullet = new HeroBullet(x + (i*2 - aircraft.shootNum + 1)*10, y, speedX, speedY, aircraft.power);
            }
            else{
                abstractBullet = new EnemyBullet(x + (i*2 - aircraft.shootNum + 1)*10, y, speedX, speedY, aircraft.power);
            }
            res.add(abstractBullet);
        }
        return res;
    }
}
