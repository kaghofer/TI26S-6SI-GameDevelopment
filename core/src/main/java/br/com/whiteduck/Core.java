package br.com.whiteduck;

import br.com.whiteduck.managers.AssetController;
import br.com.whiteduck.managers.GameManager;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Core implements ApplicationListener {
    private Texture backgroundTexture;
    private Texture spaceShipTexture;
    private Texture rockTexture;
    private Texture missileTexture;
    private SpriteBatch spriteBatch;
    private FitViewport viewport;
    private GameManager gameManager;
    private AssetController assets;

    @Override
    public void create() {
        // Inicializa o controlador de assets
        assets = AssetController.getInstance();

        assets.loadAllAssets();
        assets.finishLoading();

        backgroundTexture = assets.get(AssetController.BACKGROUND);
        spaceShipTexture = assets.get(AssetController.NAVE_TEXTURE);
        missileTexture = assets.get(AssetController.MISSIL);
        rockTexture = assets.get(AssetController.METEOROS);

        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(8, 5);

        gameManager = new GameManager(viewport, spaceShipTexture,
            missileTexture, rockTexture);
    }

    @Override
    public void render() {
        handleInput();
        update();
        draw();
    }

    private void handleInput() {
        float delta = Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            gameManager.moveShipUp(delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            gameManager.moveShipDown(delta);
        }

        // Versão básica - sem feedback visual
        if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            gameManager.createMissile();
        }
    }

    private void update() {
        gameManager.update(Gdx.graphics.getDeltaTime());
    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();

        spriteBatch.draw(backgroundTexture, 0, 0,
            viewport.getWorldWidth(),
            viewport.getWorldHeight());

        gameManager.draw(spriteBatch);

        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        backgroundTexture.dispose();
        spaceShipTexture.dispose();
        missileTexture.dispose();
        rockTexture.dispose();
    }
}
