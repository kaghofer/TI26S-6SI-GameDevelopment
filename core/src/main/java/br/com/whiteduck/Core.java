package br.com.whiteduck;

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
    private Vector2 touchPos;
    private GameManager gameManager;

    @Override
    public void create() {
        backgroundTexture = new Texture("interstellar.jpg");
        spaceShipTexture = new Texture("spaceShips_009.png");
        missileTexture = new Texture("spaceMissiles_009.png");
        rockTexture = new Texture("spaceMeteors_001.png");

        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(8, 5);
        touchPos = new Vector2();

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
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);
            gameManager.setShipY(touchPos.y);
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
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        spriteBatch.dispose();
        backgroundTexture.dispose();
        spaceShipTexture.dispose();
        missileTexture.dispose();
        rockTexture.dispose();
    }
}
