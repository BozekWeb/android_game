package pl.bozek.dungeon.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

public class GdxUtils {

    public static void clearScreen() {
        clearScreen(Color.BLACK);
    }

    public static void clearScreen(Color color) {
        Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }


    public static Color getColor(float i){

        if(i == 1.0f){
            return new Color(Color.RED);
        } else if(i == 0.0) {
            return new Color(Color.BLUE);
        } else if(i == 3) {
            return new Color(Color.WHITE);
        } else if(i == 0.5f) {
            return new Color(Color.GREEN);
        } else {
            return new Color(Color.YELLOW);
        }

    }


    private GdxUtils() {}
}
