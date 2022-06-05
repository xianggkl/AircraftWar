package edu.hitsz.factory.propFactory;

import edu.hitsz.prop.AbstractProp;
import edu.hitsz.prop.FireProp;

public class FirePropFactory implements IPropFactory{
    @Override
    public AbstractProp GetEnemyProp(int x,int y) {
        return new FireProp(
                x,
                y,
                0,
                2
        );
    }
}
