package pl.bozek.dungeon.config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import pl.bozek.dungeon.util.GdxUtils;

public class GameConfig {
    // == WORLD UNITS ==
    // == World unit based on real device width and height ==
    // == World unit scale with all device to keep proportions ==
    // == Some device will have white space on bottom and top ==
    // == or right and left to keep  proportions ==

    // == DEBUG MODE ==
    // == Active debug mode ==
    public static final boolean DEBUG = true;

    // == Default cell size in debug grid ==
    public static final float DEFAULT_CELL_SIZE = 1f; // in world units

    // == APPLICATION SETTING ==
    // == Default device size on PC in pixels ==
    public static final int PC_WIDTH = 600; // in pixels
    public static final int PC_HEIGHT = 900; // in pixels

    // == Default size on android in world units ==
    public static final float HEIGHT = 640; // world units
    public static final float WIDTH = 336;


    public static final float RATIO_FOR_MAP = 0.99f;

    public static final float MAP_DIMENSION = WIDTH / (WIDTH / 3.5f);
   //public static final float MAP_DIMENSION = 1;
    public static final float MAP_DIMENSION_FINAL = MAP_DIMENSION * RATIO_FOR_MAP;





    private GameConfig(){}

}
