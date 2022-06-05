package edu.hitsz.publish;

import edu.hitsz.aircraft.AbstractAircraft;

import java.util.List;

public class BombPublisher extends AbstractPublisher<AbstractAircraft>{

    @Override
    public void addObserver(AbstractAircraft observer) {
        this.observers.add(observer);
    }

    @Override
    public void addObserversList(List<AbstractAircraft> observers) {
        this.observers = observers;
    }

    @Override
    public void removeObserver(AbstractAircraft observer) {
        for (int i=0;i<observers.size();i++)
        {
            if (observers.get(i).equals(observer)) {
                this.observers.remove(observer);
                return;
            }
        }
    }

    @Override
    public void notifyAllObservers() {
        for (AbstractAircraft aircraft:
             observers) {
            aircraft.update();
        }
    }
}
