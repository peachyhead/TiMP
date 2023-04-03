package scripts.rabbit.factories;

import javafx.stage.Stage;
import scripts.behaviour.data.BehaviourType;
import scripts.behaviour.factories.BehaviourFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import scripts.rabbit.RabbitModel;
import scripts.rabbit.base.RabbitPresenter;
import scripts.rabbit.presenters.AlbinoRabbitPresenter;
import scripts.rabbit.presenters.CommonRabbitPresenter;

public class RabbitPresenterFactory {
    private final Pane layer;
    private final BehaviourFactory behaviourFactory;

    private final Image commonRabbitImage;
    private final Image albinoRabbitImage;

    public RabbitPresenterFactory(Pane layer, BehaviourFactory behaviourFactory){
        this.layer = layer;
        this.behaviourFactory = behaviourFactory;

        commonRabbitImage = new Image("/resources/commonRabbit.jpg");
        albinoRabbitImage = new Image("/resources/albinoRabbit.jpg");
    }

    public RabbitPresenter Create(BehaviourType behaviourType, RabbitModel model){

        RabbitPresenter presenter = null;
        switch (model.rabbitType){
            case Common -> presenter = new CommonRabbitPresenter(layer, commonRabbitImage, model);
            case Albino -> presenter = new AlbinoRabbitPresenter(layer, albinoRabbitImage, model);
        }

        var behaviour = behaviourFactory.Create(behaviourType);
        if (presenter != null)
            presenter.AttachData(behaviour);

        return presenter;
    }
}
