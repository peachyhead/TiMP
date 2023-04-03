package scripts.base.observer;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimerObservable implements IObservable {
    private Timestamp timestamp;
    private final List<IObserver> observers = new ArrayList<>();

    public TimerObservable() {
        timestamp = new Timestamp(0);
    }

    public void updateValue(long value) {
        timestamp.setTime(value);
        notifyObservers();
    }

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (var observer : observers) {
            observer.handleEvent(timestamp.toLocalDateTime());
        }
    }

    public LocalDateTime getTime() { return timestamp.toLocalDateTime(); }
}
