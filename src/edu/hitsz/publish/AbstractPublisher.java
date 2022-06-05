package edu.hitsz.publish;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractPublisher<T> {
    List<T> observers = new LinkedList<T>();
    public abstract void addObserver(T observer);
    public abstract void addObserversList(List<T> observers);
    public abstract void removeObserver(T observer);
    public abstract void notifyAllObservers();
}
