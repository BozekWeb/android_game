package pl.bozek.dungeon.config;

import com.badlogic.gdx.Gdx;

public class GameHudConfig {

    private static float HEIGHT = Gdx.graphics.getHeight();
    private static float WIDTH  = Gdx.graphics.getWidth();

    public static final float ASPECT_RATIO = HEIGHT / WIDTH;
    public static final float FONT_RATIO = ASPECT_RATIO;


    public static final float HUD_HEIGHT = GameConfig.HEIGHT * ASPECT_RATIO; // world units
    public static final float HUD_WIDTH = GameConfig.WIDTH * ASPECT_RATIO;

    public static final float HUD_NORMAL_SIZE = HUD_HEIGHT / 16;
    private static final float HUD_REAL_NORMAL_SIZE = HUD_NORMAL_SIZE/2.66f;
    private static final float HUD_SMALL_FRAME_SIZE = HUD_NORMAL_SIZE / 8;


    //HP BAR
        public static final float HUD_HP_BAR_WIDTH = HUD_NORMAL_SIZE ;
        public static final float HUD_HP_BAR_HEIGHT = HUD_NORMAL_SIZE * 4;
        public static final float HUD_HP_BAR_X = HUD_REAL_NORMAL_SIZE;
        public static final float HUD_HP_BAR_Y = HUD_HEIGHT - HUD_HP_BAR_HEIGHT - HUD_REAL_NORMAL_SIZE;

    //EXP BAR
        public static final float HUD_EXP_BAR_GRAY_PLATE_WIDTH = HUD_WIDTH - 2 * HUD_REAL_NORMAL_SIZE - HUD_HP_BAR_WIDTH;
        public static final float HUD_EXP_BAR_GRAY_PLATE_HEIGHT = HUD_NORMAL_SIZE / 3;
        public static final float HUD_EXP_BAR_GRAY_PLATE_X = HUD_HP_BAR_X + HUD_HP_BAR_WIDTH;
        public static final float HUD_EXP_BAR_GRAY_PLATE_Y = HUD_HEIGHT - HUD_EXP_BAR_GRAY_PLATE_HEIGHT - HUD_REAL_NORMAL_SIZE;

        public static final float HUD_EXP_BAR_LIGHT_PLATE_WIDTH = HUD_WIDTH - 2 * HUD_REAL_NORMAL_SIZE - HUD_HP_BAR_WIDTH;
        public static final float HUD_EXP_BAR_LIGHT_PLATE_HEIGHT = HUD_SMALL_FRAME_SIZE;
        public static final float HUD_EXP_BAR_LIGHT_PLATE_X = HUD_HP_BAR_X + HUD_HP_BAR_WIDTH;
        public static final float HUD_EXP_BAR_LIGHT_PLATE_Y = HUD_HEIGHT - HUD_EXP_BAR_GRAY_PLATE_HEIGHT - HUD_REAL_NORMAL_SIZE - HUD_EXP_BAR_LIGHT_PLATE_HEIGHT;

        public static final float HUD_EXP_BAR_DARK_PLATE_WIDTH = HUD_WIDTH - 2 * HUD_REAL_NORMAL_SIZE - HUD_HP_BAR_WIDTH - 2 * HUD_SMALL_FRAME_SIZE;
        public static final float HUD_EXP_BAR_DARK_PLATE_HEIGHT = HUD_SMALL_FRAME_SIZE;
        public static final float HUD_EXP_BAR_DARK_PLATE_X = HUD_HP_BAR_X + HUD_HP_BAR_WIDTH + HUD_SMALL_FRAME_SIZE;
        public static final float HUD_EXP_BAR_DARK_PLATE_Y = HUD_HEIGHT - HUD_EXP_BAR_GRAY_PLATE_HEIGHT/2 - HUD_REAL_NORMAL_SIZE - HUD_EXP_BAR_LIGHT_PLATE_HEIGHT/2;

    //LVL FRAME
        public static final float HUD_LVL_FRAME_GRAY_PLATE_WIDTH = 1f * HUD_NORMAL_SIZE;
        public static final float HUD_LVL_FRAME_GRAY_PLATE_HEIGHT = 1f * HUD_NORMAL_SIZE;
        public static final float HUD_LVL_FRAME_GRAY_PLATE_X = HUD_REAL_NORMAL_SIZE + HUD_HP_BAR_WIDTH;
        public static final float HUD_LVL_FRAME_GRAY_PLATE_Y = HUD_EXP_BAR_LIGHT_PLATE_Y - HUD_LVL_FRAME_GRAY_PLATE_HEIGHT;

        public static final float HUD_LVL_FRAME_LIGHT_PLATE_WIDTH = HUD_LVL_FRAME_GRAY_PLATE_WIDTH + HUD_SMALL_FRAME_SIZE;
        public static final float HUD_LVL_FRAME_LIGHT_PLATE_HEIGHT = HUD_SMALL_FRAME_SIZE;
        public static final float HUD_LVL_FRAME_LIGHT_PLATE_X = HUD_REAL_NORMAL_SIZE + HUD_HP_BAR_WIDTH;
        public static final float HUD_LVL_FRAME_LIGHT_PLATE_Y = HUD_LVL_FRAME_GRAY_PLATE_Y - HUD_SMALL_FRAME_SIZE;

        public static final float HUD_LVL_FRAME_LIGHT_PLATE_WIDTH_1 = HUD_SMALL_FRAME_SIZE;
        public static final float HUD_LVL_FRAME_LIGHT_PLATE_HEIGHT_1 = 1f * HUD_NORMAL_SIZE;
        public static final float HUD_LVL_FRAME_LIGHT_PLATE_X_1 = HUD_REAL_NORMAL_SIZE + HUD_HP_BAR_WIDTH + HUD_LVL_FRAME_GRAY_PLATE_WIDTH;
        public static final float HUD_LVL_FRAME_LIGHT_PLATE_Y_1 = HUD_EXP_BAR_LIGHT_PLATE_Y - HUD_LVL_FRAME_GRAY_PLATE_HEIGHT;

        public static final float HUD_LVL_FRAME_TEXT_X = HUD_LVL_FRAME_GRAY_PLATE_X + HUD_LVL_FRAME_GRAY_PLATE_WIDTH / 2.7f;
        public static final float HUD_LVL_FRAME_TEXT_Y = HUD_LVL_FRAME_GRAY_PLATE_Y + HUD_LVL_FRAME_GRAY_PLATE_HEIGHT / 1.35f;
        public static final float HUD_LVL_FRAME_TEXT_X_10 = HUD_LVL_FRAME_GRAY_PLATE_X + HUD_LVL_FRAME_GRAY_PLATE_WIDTH / 5f;
        public static final float HUD_LVL_FRAME_TEXT_Y_10 = HUD_LVL_FRAME_GRAY_PLATE_Y + HUD_LVL_FRAME_GRAY_PLATE_HEIGHT / 1.35f;
        public static final float HUD_LVL_FRAME_TEXT_X_20 = HUD_LVL_FRAME_GRAY_PLATE_X + HUD_LVL_FRAME_GRAY_PLATE_WIDTH / 5.6f;
        public static final float HUD_LVL_FRAME_TEXT_Y_20 = HUD_LVL_FRAME_GRAY_PLATE_Y + HUD_LVL_FRAME_GRAY_PLATE_HEIGHT / 1.35f;


    //LVL FRAME
        public static final float HUD_AP_FRAME_GRAY_PLATE_WIDTH = 1.5f * HUD_NORMAL_SIZE;
        public static final float HUD_AP_FRAME_GRAY_PLATE_HEIGHT = 1.5f * HUD_NORMAL_SIZE;
        public static final float HUD_AP_FRAME_GRAY_PLATE_X = HUD_WIDTH - HUD_AP_FRAME_GRAY_PLATE_WIDTH - HUD_REAL_NORMAL_SIZE;
        public static final float HUD_AP_FRAME_GRAY_PLATE_Y = HUD_EXP_BAR_LIGHT_PLATE_Y - HUD_AP_FRAME_GRAY_PLATE_HEIGHT;

        public static final float HUD_AP_FRAME_LIGHT_PLATE_WIDTH = HUD_AP_FRAME_GRAY_PLATE_WIDTH + HUD_SMALL_FRAME_SIZE;
        public static final float HUD_AP_FRAME_LIGHT_PLATE_HEIGHT = HUD_SMALL_FRAME_SIZE;
        public static final float HUD_AP_FRAME_LIGHT_PLATE_X = HUD_WIDTH - HUD_AP_FRAME_LIGHT_PLATE_WIDTH - HUD_REAL_NORMAL_SIZE;
        public static final float HUD_AP_FRAME_LIGHT_PLATE_Y = HUD_EXP_BAR_LIGHT_PLATE_Y - HUD_AP_FRAME_GRAY_PLATE_HEIGHT - HUD_AP_FRAME_LIGHT_PLATE_HEIGHT;

        public static final float HUD_AP_FRAME_LIGHT_PLATE_WIDTH_1 = HUD_SMALL_FRAME_SIZE;
        public static final float HUD_AP_FRAME_LIGHT_PLATE_HEIGHT_1 =  HUD_AP_FRAME_GRAY_PLATE_HEIGHT;
        public static final float HUD_AP_FRAME_LIGHT_PLATE_X_1 = HUD_WIDTH - HUD_AP_FRAME_GRAY_PLATE_WIDTH - HUD_REAL_NORMAL_SIZE - HUD_AP_FRAME_LIGHT_PLATE_WIDTH_1;
        public static final float HUD_AP_FRAME_LIGHT_PLATE_Y_1 = HUD_AP_FRAME_GRAY_PLATE_Y;

        public static final float HUD_AP_FRAME_TEXT_X = HUD_AP_FRAME_GRAY_PLATE_X + HUD_AP_FRAME_GRAY_PLATE_WIDTH / 12f;
        public static final float HUD_AP_FRAME_TEXT_Y = HUD_AP_FRAME_GRAY_PLATE_Y + HUD_AP_FRAME_GRAY_PLATE_HEIGHT / 1.09f;

        public static final float HUD_AP_FRAME_TEXT_X_2 = HUD_AP_FRAME_GRAY_PLATE_X + HUD_AP_FRAME_GRAY_PLATE_WIDTH / 2f;
        public static final float HUD_AP_FRAME_TEXT_Y_2 = HUD_AP_FRAME_GRAY_PLATE_Y + HUD_AP_FRAME_GRAY_PLATE_HEIGHT / 2.3f;


    private GameHudConfig(){}
}
