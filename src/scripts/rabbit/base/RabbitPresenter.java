package scripts.rabbit.base;

import com.sun.media.jfxmediaimpl.MediaDisposer;
import javafx.application.Platform;
import scripts.base.presenter.SpritePresenter;
import scripts.behaviour.interfaces.IBehaviour;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import scripts.rabbit.RabbitModel;

public abstract class RabbitPresenter extends SpritePresenter implements MediaDisposer.Disposable {
    protected IBehaviour behaviour;
    protected RabbitModel model;

    public RabbitPresenter(Pane layer, Image image, RabbitModel model) {
        super(layer, image, model.position);
        this.model = model;

        model.setOnDisposeAction(new RabbitModel.IOnDisposeAction() {
            @Override
            public void handle() {

                dispose();
            }
        });
        //model.disposeEvent = actionEvent -> dispose();
    }
    public void AttachData(IBehaviour behaviour) {
        this.behaviour = behaviour;
    }

    @Override
    public void dispose() {

        removeFromLayer();

    }
}
