package pl.bozek.dungeon.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class AssetPacker {
    private static final boolean DRAW_DEBUG_OUTLINE = false;

    private static final String RAW_ASSETS_PATH = "desktop/raw";

    private static final String ASSETS_PATH = "android/assets";

    public static void main(String[] args) {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.debug = DRAW_DEBUG_OUTLINE;
        settings.flattenPaths = true;
        settings.combineSubdirectories = true;
        settings.maxHeight = 4096;
        settings.maxWidth = 4096;


        TexturePacker.process(settings,
                RAW_ASSETS_PATH + "/hud",
                ASSETS_PATH + "/hud",
                "hud");
    }
}
