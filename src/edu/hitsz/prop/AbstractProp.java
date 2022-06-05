package edu.hitsz.prop;

import edu.hitsz.application.Game;
import edu.hitsz.basic.AbstractFlyingObject;

/**
 * 所有道具的抽象父类
 * 火力道具(FireProp) ,炸弹道具(BombProp),加血道具(HPProp)
 */
public abstract class AbstractProp extends AbstractFlyingObject {
    public static float FIRE_PROP_DROP_CHANCE;
    public static float BOMB_PROP_DROP_CHANCE;
    public static float HP_PROP_DROP_CHANCE;
    static{
        FIRE_PROP_DROP_CHANCE =  0.3f;
        BOMB_PROP_DROP_CHANCE =  0.2f;
        HP_PROP_DROP_CHANCE =  0.5f;
    }
    public AbstractProp(int locationX, int locationY, int speedX, int speedY)
    {
        super(locationX,locationY,speedX,speedY);
    }
    public abstract void propActive(Game game);
}
