package scripts.debug;

import javafx.scene.Group;
import scripts.base.observer.TimerObservable;
import scripts.habitat.HabitatController;

public class HabitatStatsPresenter {

    private final HabitatController habitatController;
    private final TimerObservable timerObservable;

    private boolean timerVisibility;
    private final TimerPresenter timerPresenter;

    public HabitatStatsPresenter(Group root, HabitatController habitatController, TimerObservable observable) {
        this.habitatController = habitatController;
        timerObservable = observable;

        timerPresenter = new TimerPresenter(root, observable);
    }

    public void Initialize() {
        ToggleTimerVisibility();
    }

    public void ToggleTimerVisibility() {
        timerVisibility = !timerVisibility;

        if (timerVisibility) {
            timerObservable.addObserver(timerPresenter);
            timerPresenter.Show();
        }
        else {
            timerPresenter.Hide();
            timerObservable.removeObserver(timerPresenter);
        }
    }
}
