package scripts.rabbit.storage;

import scripts.base.IResetable;
import scripts.habitat.HabitatController;
import scripts.rabbit.RabbitModel;
import scripts.rabbit.data.RabbitType;

import java.util.ArrayList;
import java.util.List;

public class RabbitStorage implements IResetable {
    private final List<RabbitModel> rabbitModels;
    private final int[] rabbitTypesCounter;

    public RabbitStorage(HabitatController habitatController){
        rabbitModels = new ArrayList();
        rabbitTypesCounter = new int[RabbitType.values().length];
    }

    public void addRabbit(RabbitModel rabbitModel){
        rabbitModels.add(rabbitModel);
        rabbitTypesCounter[rabbitModel.rabbitType.getValue()]++;
    }

    @Override
    public void handleReset() {
        clearRabbitStorage();
        resetCounters();
    }

    private void clearRabbitStorage(){
        for (var rabbit : rabbitModels){
            rabbit.Dispose();
        }
        rabbitModels.clear();
    }

    private void resetCounters(){
        for (var counter : rabbitTypesCounter){
            counter = 0;
        }
    }

    public int getCounter(RabbitType rabbitType){
        return rabbitTypesCounter[rabbitType.getValue()];
    }
}
