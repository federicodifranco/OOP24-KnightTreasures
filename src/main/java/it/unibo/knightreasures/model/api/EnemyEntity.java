package it.unibo.knightreasures.model.api;

public interface EnemyEntity {

    void resetEnemy();
    
    void hurt(int amount);

    boolean isActive();
}
