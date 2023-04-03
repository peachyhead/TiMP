import config.SimulationConfig;
import javafx.animation.AnimationTimer;
import scripts.debug.HabitatStatsPresenter;
import scripts.habitat.HabitatController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    private Group root;
    private Pane simulationLayer;
    private HabitatController habitatController;
    private HabitatStatsPresenter habitatStatsPresenter;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        PrepareEnvironment(primaryStage);
        Initialize(primaryStage);
    }

    private void PrepareEnvironment(Stage primaryStage){
        primaryStage.setTitle("Rabbit farm");

        root = new Group();
        var timerLayer = new Pane();
        simulationLayer = new Pane();

        root.getChildren().add(simulationLayer);
        root.getChildren().add(timerLayer);

        var scene = new Scene(root, 680, 440);
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setOnKeyPressed(keyEvent -> {
            var code = keyEvent.getCode();
            if (habitatController == null) return;

            if (code.equals(SimulationConfig.startSimulationKey))
                habitatController.Start();
            if (code.equals(SimulationConfig.stopSimulationKey))
                habitatController.Stop();
            if (code.equals(SimulationConfig.handleTimeVisibleKey))
                HandleTimerVisible();
        });
    }

    private void Initialize(Stage stage) {
        habitatController = new HabitatController(stage, simulationLayer);
        habitatStatsPresenter = new HabitatStatsPresenter(root, habitatController,
                habitatController.getTimerChangeObservable());
        habitatStatsPresenter.Initialize();

        var animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                habitatController.Update(l);
            }
        };

        animationTimer.start();
    }

    private void HandleTimerVisible(){
        if (habitatStatsPresenter != null) {
            habitatStatsPresenter.ToggleTimerVisibility();
        }
    }
}
