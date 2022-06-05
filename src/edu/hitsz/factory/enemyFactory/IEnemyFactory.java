package edu.hitsz.factory.enemyFactory;

import edu.hitsz.aircraft.AbstractAircraft;

public interface IEnemyFactory {
    public abstract AbstractAircraft getEnemyAircraft_Easy();
    public abstract AbstractAircraft getEnemyAircraft_Common();
    public abstract AbstractAircraft getEnemyAircraft_Hard();
}
