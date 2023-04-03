package scripts.rabbit.factories;

import com.sun.javafx.geom.Vec2f;
import scripts.rabbit.RabbitModel;
import scripts.rabbit.data.RabbitType;

public class RabbitModelFactory {

    public RabbitModel Create(RabbitType rabbitType, Vec2f position){
        return new RabbitModel(rabbitType, position);
    }
}
