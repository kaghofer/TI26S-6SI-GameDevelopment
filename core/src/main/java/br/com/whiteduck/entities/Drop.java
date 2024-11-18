package br.com.whiteduck.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Drop extends GameObject {
    private static final float SPEED = 2f;
    private boolean active = true;

    public Drop(Texture texture, float worldWidth) {
        super(texture);

        sprite.setSize(1, 1);
        sprite.setRotation(180);
        sprite.setOriginCenter();

        float x = MathUtils.random(worldWidth / 2, worldWidth - sprite.getWidth());
        sprite.setPosition(x, 0);
    }

    @Override
    public void update(float delta) {
        sprite.translateY(SPEED * delta);
        updateBounds();
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }
}
