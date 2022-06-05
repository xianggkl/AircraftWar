package edu.hitsz.factory.propFactory;

import edu.hitsz.prop.AbstractProp;
import edu.hitsz.prop.HPProp;

public class HPPropFactory implements IPropFactory{
    @Override
    public AbstractProp GetEnemyProp(int x,int y) {
        return new HPProp(
                x,
                y,
                0,
                2
        );
    }
}
