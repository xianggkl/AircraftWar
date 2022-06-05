package edu.hitsz.factory.propFactory;

import edu.hitsz.prop.AbstractProp;

public interface IPropFactory {
    public abstract AbstractProp GetEnemyProp(int x,int y);
}
