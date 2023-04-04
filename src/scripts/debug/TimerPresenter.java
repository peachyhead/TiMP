package scripts.debug;


import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import scripts.base.observer.IObserver;
import scripts.base.observer.TimerObservable;
import scripts.utils.Formatter;

import java.time.LocalDateTime;


public class TimerPresenter implements IObserver {

    public final Label timeLabel;

    private final TimerObservable timerObservable;

    private final Pane root;
    private final Pane layer;
    //private final StackPane layer;
    //private long seconds = 0;
    //private long minutes = 0;
    //private long hours = 0;

    //private String formattedLabel = String.format("%02d:%02d:%02d", hours, minutes, seconds);

    public TimerPresenter(Pane root, TimerObservable observable) {
        this.root = root;
        layer = new StackPane();
        timeLabel = new Label();

        timerObservable = observable;
        timeLabel.setText("00:00:000");

        layer.getChildren().add(timeLabel);
        root.getChildren().add(layer);
    }
    @Override
    public void handleEvent(long value) {
        if (timeLabel == null) return;


        timeLabel.setText(Formatter.getFormattedTime(value));
    }

    public void Show() {
        //root.getChildren().add(layer);
        layer.setVisible(true);
    }

    public void Hide(){
        //root.getChildren().remove(layer);
        layer.setVisible(false);
    }
}
