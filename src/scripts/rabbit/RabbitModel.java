package scripts.rabbit;

import com.sun.javafx.geom.Vec2f;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import scripts.rabbit.data.RabbitType;

public class RabbitModel {
    @FunctionalInterface
    public interface IOnDisposeAction{
        void handle();
    }

    public RabbitType rabbitType;
    public Vec2f position;

    //public EventHandler<ActionEvent> disposeEvent;
    private IOnDisposeAction onDisposeAction;

    public RabbitModel(RabbitType type, Vec2f position){

        rabbitType = type;
        this.position = position;
    }

    public void setOnDisposeAction(IOnDisposeAction onDisposeAction) {
        this.onDisposeAction = onDisposeAction;
    }

    public void Dispose() {
        onDisposeAction.handle();
    }
}
