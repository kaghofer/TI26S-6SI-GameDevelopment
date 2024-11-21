package br.com.whiteduck.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class AssetController {

    public static final String NAVE_TEXTURE = "spaceShips_009.png";
    public static final String METEOROS_TEXTURE = "spaceMeteors_001.png";
    public static final String MISSIL_TEXTURE = "spaceMissiles_009.png";
    public static final String BACKGROUND_TEXTURE = "interstellar.jpg";
    private static AssetController instance;
    private AssetManager manager;

    private AssetController() {
        manager = new AssetManager();
    }

    public static AssetController getInstance() {
        if (instance == null) {
            instance = new AssetController();
        }
        return instance;
    }

    public void loadAllAssets() {
        // Texturas
        manager.load(NAVE_TEXTURE, Texture.class);
        manager.load(METEOROS_TEXTURE, Texture.class);
        manager.load(MISSIL_TEXTURE, Texture.class);
        manager.load(BACKGROUND_TEXTURE, Texture.class);

    }

    public void loadGamePlayAssets() {
        // Carrega assets específicos da gameplay
        manager.load(NAVE_TEXTURE, Texture.class);
        manager.load(METEOROS_TEXTURE, Texture.class);
        manager.load(MISSIL_TEXTURE, Texture.class);
        manager.load(BACKGROUND_TEXTURE, Texture.class);
    }

    public boolean isLoaded(String fileName) {
        return manager.isLoaded(fileName);
    }

    public float getProgress() {
        return manager.getProgress();
    }

    public boolean update() {
        return manager.update();
    }

    public void finishLoading() {
        manager.finishLoading();
    }

    public <T> T get(String fileName) {
        return manager.get(fileName);
    }

    public void dispose() {
        manager.dispose();
        instance = null;
    }
}
