package br.com.whiteduck.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class AssetController {

    public static final String NAVE_TEXTURE = "spaceShips_009.png";
    public static final String METEOROS = "spaceMeteors_001.png";
    public static final String MISSIL = "spaceMissiles_009.png";
    public static final String BACKGROUND = "interstellar.jpg";
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
        manager.load(METEOROS, Texture.class);
        manager.load(MISSIL, Texture.class);
        manager.load(BACKGROUND, Texture.class);

    }

    public void loadGamePlayAssets() {
        // Carrega assets específicos da gameplay
        manager.load(NAVE_TEXTURE, Texture.class);
        manager.load(METEOROS, Texture.class);
        manager.load(MISSIL, Texture.class);
        manager.load(BACKGROUND, Texture.class);
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
