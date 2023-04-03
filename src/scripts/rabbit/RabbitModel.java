package scripts.rabbit;

import com.sun.javafx.geom.Vec2f;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import scripts.rabbit.data.RabbitType;

public class RabbitModel {

    public RabbitType rabbitType;
    public Vec2f position;

    public EventHandler<ActionEvent> disposeEvent;

    public RabbitModel(RabbitType type, Vec2f position){

        rabbitType = type;
        this.position = position;
    }

    public void Dispose() {
        disposeEvent.notifyAll();
    }
}
