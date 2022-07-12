package pl.bozek.dungeon.customType;

public class PlayerStatistics {

    private int lvl;
    private int deep;

    private int currentExpPoint;

    private int maxHitPoint;
    private int currentHitPoint;

    private int maxActionPoint;
    private int currentActionPoint;

    private int minDamage;
    private int maxDamage;

    private int armor;

    private int skillPoint;


    public PlayerStatistics(int lvl, int deep, int currentExpPoint, int skillPoint, int maxHitPoint, int currentHitPoint, int maxActionPoint, int currentActionPoint, int minDamage, int maxDamage, int armor) {
        this.lvl = lvl;
        this.deep = deep;
        this.currentExpPoint = currentExpPoint;
        this.skillPoint = skillPoint;

        this.armor = armor;
        this.maxHitPoint = maxHitPoint;
        this.currentHitPoint = currentHitPoint;

        this.maxActionPoint = maxActionPoint;
        this.currentActionPoint = currentActionPoint;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public void incDeep(int x){
        deep += x;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getDeep() {
        return deep;
    }

    public void setDeep(int deep) {
        this.deep = deep;
    }

    public int getCurrentExpPoint() {
        return currentExpPoint;
    }

    public void setCurrentExpPoint(int currentExpPoint) {
        this.currentExpPoint = currentExpPoint;
    }

    public int getSkillPoint() {
        return skillPoint;
    }

    public void setSkillPoint(int skillPoint) {
        this.skillPoint = skillPoint;
    }

    public int getMaxHitPoint() {
        return maxHitPoint;
    }

    public void setMaxHitPoint(int maxHitPoint) {
        this.maxHitPoint = maxHitPoint;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    public int getCurrentHitPoint() {
        return currentHitPoint;
    }

    public void setCurrentHitPoint(int currentHitPoint) {
        this.currentHitPoint = currentHitPoint;
    }

    public int getMaxActionPoint() {
        return maxActionPoint;
    }

    public void setMaxActionPoint(int maxActionPoint) {
        this.maxActionPoint = maxActionPoint;
    }

    public int getCurrentActionPoint() {
        return currentActionPoint;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setCurrentActionPoint(int currentActionPoint) {
        this.currentActionPoint = currentActionPoint;
    }
}
