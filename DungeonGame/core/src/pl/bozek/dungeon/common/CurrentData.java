package pl.bozek.dungeon.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;

public class CurrentData {

    public static boolean PLAYER_DEAD = false;

    public static int PLAYER_END_ROOM;
    public static int PLAYER_SPAWN_ROOM;

    public static int PLAYER_FINAL_NODE_X;
    public static int PLAYER_FINAL_NODE_Y;

    public static boolean PLAYER_TURN;
    public static boolean ENEMY_TURN;

    public static boolean ENEMY_TURN_START = false;

    public static boolean PLAYER_CAN_CLICK = true;

    public static boolean FREE_MOVEMENT = true;
    public static boolean BREAK_FREE_MOVEMENT = false;

    public static Array<Entity> ENEMY_ACTION_ARRAY = new Array<>(  );

    public static boolean PLAYER_CAN_ATTACK = false;
    public static boolean PLAYER_STRONG_ATTACK = false;
    public static int PLAYER_ATTACK_TYPE = 1;

    public static boolean PLAYER_TURN_START = false;
    public static Entity CLICKED_ENEMY = null;


    public static boolean ENEMY_CAN_MOVE = false;


    public static boolean PLAYER_CAN_MOVE = false;

    public static int CURRENT_GAME_DEEP;


    public static boolean ENEMY_FIND_PATH = false;



    public static String DATABASE_ERROR;


















}
