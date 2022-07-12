package pl.bozek.dungeon.system.enemy;

public class EnemyStatistics {

    private int maxHitPoint;
    private int currentHitPoint;
    private int maxDamage;
    private int minDamage;

    private float dodgeChance;
    private int maxActionPoint;
    private int currentActionPoint;

    private int armor;



    public EnemyStatistics(float enemyType, int dungeonDeep) {



        //Glass Canon Enemy - Red Enemy
        if(enemyType == 0.0){
            maxHitPoint = 1;
            currentHitPoint = 1;
            armor = 0;
            dodgeChance = 0.0f;
            minDamage = 2 * dungeonDeep;
            maxDamage = 2 * dungeonDeep;
            maxActionPoint = 4;
            currentActionPoint = 4;

        // Tank /
        } else if(enemyType == 1.0){
            armor = 4;
            dodgeChance = 0;
            maxHitPoint = 10;
            currentHitPoint = 10;
            minDamage = dungeonDeep;
            maxDamage = dungeonDeep;
            maxActionPoint = 2;
            currentActionPoint = 2;

         // Dodge
        } else if(enemyType == 2.0){
            armor = 0;
            dodgeChance = 95.0f;
            maxActionPoint = 6;
            currentActionPoint = 6;
            maxHitPoint = 1;
            currentHitPoint = 1;
            minDamage = 1;
            maxDamage = 1;
        }




    }


    public int getArmor() {
        return armor;
    }

    public float getDodgeChance() {
        return dodgeChance;
    }

    public int getMaxActionPoint() {
        return maxActionPoint;
    }

    int getCurrentActionPoint() {
        return currentActionPoint;
    }

    public void setCurrentActionPoint(int currentActionPoint) {
        this.currentActionPoint = currentActionPoint;
    }

    public int getMaxHitPoint() {
        return maxHitPoint;
    }

    public int getCurrentHitPoint() {
        return currentHitPoint;
    }

    public void setCurrentHitPoint(int currentHitPoint) {
        this.currentHitPoint = currentHitPoint;
    }

    int getMaxDamage() {
        return maxDamage;
    }

    int getMinDamage() {
        return minDamage;
    }
}
