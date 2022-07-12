package pl.bozek.dungeon.config;

public class DungeonConfig {

    public static final int OFFSET_SIZE = 2;

    public static final int DUNGEON_SIZE = 16;

    public static final int MAX_ROOM_SIZE = 7;

    public static final int MIN_ROOM_SIZE = 3;

    public static final int ROOM_SIZE_TOLERANCE = 6;

    /** Iteration amount */
    public static final int ATTEMPTS = 1000;

    /** 0 to 1, 0 - straight corridor, 1 - chaos */
    public static final float WIND_CHANCE = 0.0f;

    /** 0 - 1, 0 - perfect maze , 1 - all cell connected */
    public static final float CONNECT_CHANCE = 0.0f;

    /**  */
    public static final int DEAD_END = 10;


    private DungeonConfig(){}
}
