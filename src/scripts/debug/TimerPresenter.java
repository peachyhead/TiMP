package scripts.debug;


import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import scripts.base.observer.IObserver;
import scripts.base.observer.TimerObservable;

import java.time.LocalDateTime;


public class TimerPresenter implements IObserver {

    public final Label timeLabel;

    private final TimerObservable timerObservable;

    private final Group root;
    private final StackPane layer;
    private int seconds = 0;
    private int minutes = 0;
    private int hours = 0;

    private String formattedLabel = String.format("%02d:%02d:%02d", hours, minutes, seconds);

    public TimerPresenter(Group root, TimerObservable observable) {
        this.root = root;
        layer = new StackPane();
        timeLabel = new Label();

        timerObservable = observable;
        timeLabel.setText(formattedLabel);

        layer.getChildren().add(timeLabel);
    }
    @Override
    public void handleEvent(LocalDateTime value) {
        if (timeLabel == null) return;

        hours = value.getHour();
        minutes = value.getMinute();
        seconds = value.getSecond();

        formattedLabel = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timeLabel.setText(formattedLabel);
    }

    public void Show() {
        root.getChildren().add(layer);
    }

    public void Hide(){
        root.getChildren().remove(layer);
    }
}
