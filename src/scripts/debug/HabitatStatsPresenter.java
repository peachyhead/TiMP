package scripts.debug;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import scripts.base.observer.TimerObservable;
import scripts.habitat.HabitatController;

public class HabitatStatsPresenter {

    private final HabitatController habitatController;
    private final TimerObservable timerObservable;

    private boolean timerVisibility;
    private final TimerPresenter timerPresenter;

    private final Pane root;
    private VBox statLayer;

    private final Label commonRabbitLabel;
    private final Label albinoRabbitLabel;
    private final Label simTimeLabel;

    public HabitatStatsPresenter(Pane root, HabitatController habitatController, TimerObservable observable) {
        this.habitatController = habitatController;
        this.root = root;
        timerObservable = observable;

        timerPresenter = new TimerPresenter(root, observable);
        commonRabbitLabel = new Label();
        albinoRabbitLabel = new Label();
        simTimeLabel = new Label();
    }

    public void Initialize() {

        statLayer = new VBox();
        statLayer.setFillWidth(false);

        root.getChildren().add(statLayer);

        hideStats();

        var titleStatLabel = new Label();
        titleStatLabel.setText("SimulationStats:");
        titleStatLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        titleStatLabel.setTextFill(Color.RED);
        statLayer.getChildren().add(titleStatLabel);

        commonRabbitLabel.setText("Common rabbits spawned - ");
        commonRabbitLabel.setFont(Font.font("Lucida Sans Unicode", FontWeight.NORMAL, FontPosture.REGULAR, 14));
        commonRabbitLabel.setTextFill(Color.BLUE);
        statLayer.getChildren().add(commonRabbitLabel);

        albinoRabbitLabel.setText("Albino rabbits spawned - ");
        albinoRabbitLabel.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
        albinoRabbitLabel.setTextFill(Color.GREEN);
        statLayer.getChildren().add(albinoRabbitLabel);

        simTimeLabel.setText("Simulation time - ");
        simTimeLabel.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, FontPosture.REGULAR, 18));
        simTimeLabel.setTextFill(Color.PURPLE);
        statLayer.getChildren().add(simTimeLabel);


        ToggleTimerVisibility();
    }

    public void showStats(){
        statLayer.setVisible(true);
    }

    public void hideStats(){
        statLayer.setVisible(false);
    }

    public void setStats(int albinoCounter, int commonCounter, String formattedTime){
        albinoRabbitLabel.setText("Albino rabbits spawned - " + albinoCounter);
        commonRabbitLabel.setText("Common rabbits spawned - " + commonCounter);
        simTimeLabel.setText("Simulation time - " + formattedTime);

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
