package it.unibo.knightreasures.model.api;

public interface EnemyEntity {

    void resetEnemy();
    
    void hurt(int amount);

    boolean isActive();

    int getEnemyState();

    boolean getFirstupdate();

    int getAniIndex();

    boolean isAttackChecked();

    void setAttackChecked(final boolean attackChecked);

    int getWalkDir();
}
