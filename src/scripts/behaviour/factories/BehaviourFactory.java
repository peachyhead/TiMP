package scripts.behaviour.factories;

import scripts.behaviour.data.BehaviourType;
import scripts.behaviour.interfaces.IBehaviour;
import scripts.behaviour.models.CalmBehaviour;

import java.util.Objects;

public class BehaviourFactory
{
    public IBehaviour Create(BehaviourType type)
    {
        if (Objects.requireNonNull(type) == BehaviourType.Calm) {
            return new CalmBehaviour();
        }
        return null;
    }
}
