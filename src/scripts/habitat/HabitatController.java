package scripts.habitat;

import javafx.stage.Stage;
import scripts.base.observer.TimerObservable;
import javafx.scene.layout.Pane;
import scripts.rabbit.RabbitModel;
import scripts.spawner.SpawnerController;
import scripts.spawner.SpawnerModel;

import java.util.ArrayList;
import java.util.List;

public class HabitatController {
    private TimerObservable spawnTimerProperty;
    private final SpawnerModel spawnerModel;
    private final SpawnerController spawnerController;
    private final List<RabbitModel> rabbitStorage;

    public HabitatController(Stage stage, Pane layer) {
        rabbitStorage = new ArrayList<>();
        spawnTimerProperty = new TimerObservable();
        spawnerModel = new SpawnerModel();
        spawnerController = new SpawnerController(stage, layer, spawnerModel, rabbitStorage, spawnTimerProperty);
    }

    public void Start() {
       spawnTimerProperty.addObserver(spawnerController);
    }

    public void Update(long time) {
        spawnTimerProperty.updateValue(time);
    }

    public List<RabbitModel> getRabbitList() {
        return rabbitStorage.stream().toList();
    }

    public TimerObservable getTimerChangeObservable() {
        return spawnTimerProperty;
    }

    public void Stop() {
        spawnTimerProperty.removeObserver(spawnerController);
    }
}
