package scripts.base.observer;

import scripts.base.IResetable;
import scripts.habitat.HabitatController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimerObservable implements IObservable, IResetable {
    //private Timestamp timestamp;
    private long simulationTime;

    private final List<IObserver> observers = new ArrayList<>();

    public TimerObservable(HabitatController habitatController) {
        simulationTime = 0;

    }

    public void updateValue(long value) {
        simulationTime = value;
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
            observer.handleEvent(simulationTime);
        }
    }

    //public LocalDateTime getTime() { return timestamp.toLocalDateTime(); }
    public long getSimulationTime() { return simulationTime; }

    @Override
    public void handleReset() {
        simulationTime = 0;
    }
}
