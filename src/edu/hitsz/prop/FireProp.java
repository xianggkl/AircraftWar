package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game;
import edu.hitsz.application.Main;

public class FireProp extends AbstractProp{
    public FireProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void propActive(Game game) {
        System.out.println("火力道具生效!");
        AbstractAircraft temp = game.getHeroAircraft();
        Runnable ra = new Runnable() {
            @Override
            public void run() {
                if (temp.getClass().equals(HeroAircraft.class))
                {
                    temp.shootNum += 3;
                    ((HeroAircraft) temp).setSpread(true);
                }
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (temp.getClass().equals(HeroAircraft.class))
                {
                    temp.shootNum -= 3;
                    ((HeroAircraft) temp).setSpread(false);
                }
            }
        };
        new Thread(ra).start();
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
