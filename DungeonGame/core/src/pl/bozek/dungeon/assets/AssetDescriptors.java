package pl.bozek.dungeon.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetDescriptors {

    public static final AssetDescriptor<BitmapFont> BASIC_FONT =
            new AssetDescriptor<BitmapFont>(AssetPaths.BASIC_FONT, BitmapFont.class);

    public static final AssetDescriptor<TextureAtlas> MAP_TILESET =
            new AssetDescriptor<TextureAtlas>(AssetPaths.MAP_TILESET, TextureAtlas.class);


    public static final AssetDescriptor<TextureAtlas> ENTITY_TILESET =
            new AssetDescriptor<TextureAtlas>(AssetPaths.ENTITY_TILESET, TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> HUD_TILE =
            new AssetDescriptor<TextureAtlas>(AssetPaths.HUD_TILE, TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> UI_SKIN =
            new AssetDescriptor<>(AssetPaths.UI_SKIN, TextureAtlas.class);

    private AssetDescriptors() {}
}
