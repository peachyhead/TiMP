package scripts.habitat;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.stage.Stage;
import scripts.base.IResetable;
import scripts.base.observer.TimerObservable;
import javafx.scene.layout.Pane;
import scripts.debug.HabitatStatsPresenter;
import scripts.rabbit.data.RabbitType;
import scripts.rabbit.storage.RabbitStorage;
import scripts.spawner.SpawnerController;
import scripts.spawner.SpawnerModel;
import scripts.utils.Formatter;

import java.util.ArrayList;
import java.util.List;

public class HabitatController {

     private class SimulationTimer extends AnimationTimer{
        private long prevTimestamp;
        private long time;

        public SimulationTimer(){
            time = 0;
        }

        @Override
        public void handle(long l) {
            var deltaTime = l - prevTimestamp;
            time += deltaTime;
            prevTimestamp = l;


            Update(time);


        }

        @Override
        public void start(){
            super.start();
            prevTimestamp = System.nanoTime();

            System.out.println("STARTED");
        }

        @Override
         public void stop(){
            super.stop();

            time = 0;
        }
    }

    private TimerObservable spawnTimerProperty;
    private final SpawnerModel spawnerModel;
    private final SpawnerController spawnerController;
    //private final List<RabbitModel> rabbitStorage;
    private final RabbitStorage rabbitStorage;

    private final SimulationTimer simulationTimer;

    private HabitatStatsPresenter habitatStatsPresenter;




    public HabitatController(Stage stage, Pane layer) {
        rabbitStorage = new RabbitStorage(this);
        spawnTimerProperty = new TimerObservable(this);
        spawnerModel = new SpawnerModel();
        spawnerController = new SpawnerController(stage, layer, spawnerModel, rabbitStorage, spawnTimerProperty);
        simulationTimer = new SimulationTimer();
    }

    public void setHabitatStatsPresenter(HabitatStatsPresenter habitatStatsPresenter) {
        this.habitatStatsPresenter = habitatStatsPresenter;
    }

    public void Start() {
       spawnTimerProperty.addObserver(spawnerController);

       habitatStatsPresenter.hideStats();

       simulationTimer.start();
    }

    private void Update(long time) {
        spawnTimerProperty.updateValue(time);
    }

    /*public List<RabbitModel> getRabbitList() {
        return rabbitStorage.stream().toList();
    }*/

    public TimerObservable getTimerChangeObservable() {
        return spawnTimerProperty;
    }

    public void Stop() {
        simulationTimer.stop();

        spawnTimerProperty.removeObserver(spawnerController);

        var albinoCounter = rabbitStorage.getCounter(RabbitType.Albino);
        var commonCounter = rabbitStorage.getCounter(RabbitType.Common);
        var formattedTime = Formatter.getFormattedTime(spawnTimerProperty.getSimulationTime());

        habitatStatsPresenter.setStats(albinoCounter, commonCounter, formattedTime);

        spawnTimerProperty.handleReset();
        rabbitStorage.handleReset();

        habitatStatsPresenter.showStats();

    }


}
