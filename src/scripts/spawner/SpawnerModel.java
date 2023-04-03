package scripts.spawner;

import scripts.rabbit.config.RabbitSpawnConfig;

public class SpawnerModel {

    private int commonRabbitSpawnTime;
    private int albinoRabbitSpawnTime;

    private float commonRabbitRate;
    private float albinoRabbitBelowOverallAmount;

    public SpawnerModel(){
        commonRabbitSpawnTime = RabbitSpawnConfig.CommonRabbitSpawnTime;
        albinoRabbitSpawnTime = RabbitSpawnConfig.AlbinoRabbitSpawnTime;
        commonRabbitRate = RabbitSpawnConfig.CommonRabbitRate;
        albinoRabbitBelowOverallAmount = RabbitSpawnConfig.AlbinoRabbitBelowOverallAmount;
    }

    public SpawnerModel(int commonRabbitSpawnTime, int albinoRabbitSpawnTime,
                        float commonRabbitRate, float albinoRabbitBelowOverallAmount){
        this.commonRabbitSpawnTime = commonRabbitSpawnTime;
        this.albinoRabbitSpawnTime = albinoRabbitSpawnTime;
        this.commonRabbitRate = commonRabbitRate;
        this.albinoRabbitBelowOverallAmount = albinoRabbitBelowOverallAmount;
    }

    //region Getters and Setters

    public int getCommonRabbitSpawnTime(){
        return commonRabbitSpawnTime;
    }

    public int getAlbinoRabbitSpawnTime(){
        return albinoRabbitSpawnTime;
    }

    public float getCommonRabbitRate(){
        return commonRabbitRate;
    }

    public float getAlbinoRabbitBelowOverallAmount(){
        return albinoRabbitBelowOverallAmount;
    }

    public void setCommonRabbitSpawnTime(int value){
        commonRabbitSpawnTime = value;
    }

    public void setAlbinoRabbitSpawnTime(int value){
        albinoRabbitSpawnTime = value;
    }

    public void setCommonRabbitRate(float value){
        commonRabbitRate = value;
    }

    public void setAlbinoRabbitBelowOverallAmount(float value){
        albinoRabbitBelowOverallAmount = value;
    }

    //endregion
}
