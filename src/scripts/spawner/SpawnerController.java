package scripts.spawner;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import scripts.base.disposable.IDisposable;
import scripts.base.observer.IObserver;
import scripts.base.observer.TimerObservable;
import scripts.behaviour.data.BehaviourType;
import scripts.behaviour.factories.BehaviourFactory;
import com.sun.javafx.geom.Vec2f;
import scripts.rabbit.RabbitModel;
import scripts.rabbit.base.RabbitPresenter;
import scripts.rabbit.config.RabbitSpawnConfig;
import scripts.rabbit.data.RabbitType;
import scripts.rabbit.factories.RabbitModelFactory;
import scripts.rabbit.factories.RabbitPresenterFactory;
import scripts.rabbit.storage.RabbitStorage;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpawnerController implements IObserver
{
    private final RabbitPresenterFactory rabbitPresenterFactory;
    private final RabbitModelFactory rabbitModelFactory;
    private final SpawnerModel model;

    private final TimerObservable timerObservable;
    //private final List<RabbitModel> rabbitStorage;
    //private final List<RabbitPresenter> presenterDisposableStorage;
    private final RabbitStorage rabbitStorage;

    private final Random randomGenerator;
    private final Stage primaryStage;

    private int prevSec = 0;


    public SpawnerController(Stage stage, Pane layer, SpawnerModel model, RabbitStorage storage,
                             TimerObservable timerObservable){
        this.model = model;
        this.timerObservable = timerObservable;

        rabbitStorage = storage;
        primaryStage = stage;

        randomGenerator = new Random();
        rabbitModelFactory = new RabbitModelFactory();
        //presenterDisposableStorage = new ArrayList<>();
        rabbitPresenterFactory = new RabbitPresenterFactory(layer, new BehaviourFactory());
    }

    @Override
    public void handleEvent(long value) {
        var seconds = (int)(value/1000000000);

        if (seconds == prevSec)
            return;

        prevSec = seconds;

        TrySpawnCommonRabbit(seconds);
        TrySpawnAlbinoRabbit(seconds);
    }

    private void TrySpawnCommonRabbit(int time){
        if (time % model.getCommonRabbitSpawnTime() == 0) {
            var coin = randomGenerator.nextFloat(1f);
            if (coin >= model.getCommonRabbitRate()) {
                var position = new Vec2f(randomGenerator.nextFloat((float) primaryStage.getWidth()),
                        randomGenerator.nextFloat((float) primaryStage.getHeight()));
                var commonModel = rabbitModelFactory.Create(RabbitType.Common, position);
                var presenter = rabbitPresenterFactory.Create(BehaviourType.Calm, commonModel);
                rabbitStorage.addRabbit(commonModel);
                //presenterDisposableStorage.add(presenter);
            }
        }
    }

    private void TrySpawnAlbinoRabbit(int time){
        if (time % model.getAlbinoRabbitSpawnTime() == 0) {
            var albinoRabbits = rabbitStorage.getCounter(RabbitType.Albino);
            var overallRabbits = rabbitStorage.getCounter(RabbitType.Common);

            /*for (RabbitModel rabbitModel : rabbitStorage) {
                albinoRabbits += rabbitModel.rabbitType == RabbitType.Albino ? 1 : 0;
                overallRabbits += rabbitModel.rabbitType == RabbitType.Common ? 1 : 0;
            }*/

            if (albinoRabbits <= overallRabbits * RabbitSpawnConfig.AlbinoRabbitBelowOverallAmount){
                var position = new Vec2f(randomGenerator.nextFloat((float) primaryStage.getWidth()),
                        randomGenerator.nextFloat((float) primaryStage.getHeight()));
                var albinoModel = rabbitModelFactory.Create(RabbitType.Albino, position);
                var presenter = rabbitPresenterFactory.Create(BehaviourType.Calm, albinoModel);
                rabbitStorage.addRabbit(albinoModel);
                //presenterDisposableStorage.add(presenter);
            }
        }
    }
}
