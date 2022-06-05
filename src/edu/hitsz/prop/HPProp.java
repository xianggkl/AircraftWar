package edu.hitsz.prop;

import edu.hitsz.application.Game;
import edu.hitsz.application.Main;

public class HPProp extends AbstractProp{
    public static final int Hp;
    static
    {
        Hp = 100;
    }
    public HPProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void propActive(Game game) {
        System.out.println("血量道具生效,英雄机血量提高");
        game.getHeroAircraft().increaseHp(Hp);
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
