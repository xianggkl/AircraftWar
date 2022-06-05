package edu.hitsz.factory.propFactory;

import edu.hitsz.prop.AbstractProp;
import edu.hitsz.prop.BombProp;

public class BombPropFactory implements IPropFactory{
    @Override
    public AbstractProp GetEnemyProp(int x,int y) {
        return new BombProp(
                x,
                y,
                0,
                2
        );
    }
}
