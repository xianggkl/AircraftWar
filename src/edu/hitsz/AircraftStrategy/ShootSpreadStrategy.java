package edu.hitsz.AircraftStrategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class ShootSpreadStrategy implements AircraftStrategy{

    @Override
    public List<BaseBullet> aircraftShoot(AbstractAircraft aircraft) {
        List<BaseBullet> res = new LinkedList<>();
        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY() + aircraft.direction*2;
        int speedY = aircraft.getSpeedY() + aircraft.direction*5;
        int speedX = 0;
        //当子弹数目是偶数
        if (aircraft.shootNum %2 == 0)
        {
            for (int i=0;i<aircraft.shootNum;i++)
            {
                speedX = i -aircraft.shootNum/2 + 1;
                if (aircraft.getClass().equals(HeroAircraft.class))
                {
                    res.add(new HeroBullet(x + (i*2 - aircraft.shootNum + 1)*10,y,speedX,speedY,aircraft.power));
                }
                else
                {
                    res.add(new EnemyBullet(x + (i*2 - aircraft.shootNum + 1)*10,y,speedX,speedY,aircraft.power));
                }
            }
        }
        //当子弹数目是奇数
        else {
            for (int i=0;i<aircraft.shootNum;i++)
            {
                speedX = i - aircraft.shootNum/2;
                if (aircraft.getClass().equals(HeroAircraft.class))
                {
                    res.add(new HeroBullet(x + (i*2 - aircraft.shootNum + 1)*10,y,speedX,speedY,aircraft.power));
                }
                else
                {
                    res.add(new EnemyBullet(x + (i*2 - aircraft.shootNum + 1)*10,y,speedX,speedY,aircraft.power));
                }
            }
        }
        return res;
    }
}
