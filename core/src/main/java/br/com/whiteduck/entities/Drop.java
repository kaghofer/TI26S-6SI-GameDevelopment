package br.com.whiteduck.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Drop extends GameObject {
    private static final float SPEED = 2f;
    private boolean active = true;

    public Drop(Texture texture, float worldHeight) {
        super(texture);

        sprite.setSize(0.5f, 0.5f);

        float y = MathUtils.random(0,worldHeight - sprite.getWidth());
        System.out.println(y);
        sprite.setPosition(8, y);
    }

    @Override
    public void update(float delta) {
        sprite.translateX(-SPEED * delta);
        updateBounds();
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }
}
