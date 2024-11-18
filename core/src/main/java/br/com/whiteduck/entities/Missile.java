package br.com.whiteduck.entities;

import com.badlogic.gdx.graphics.Texture;

public class Missile extends GameObject {
    private static final float SPEED = 5f;
    private boolean active = true;

    public Missile(Texture texture, float x, float y) {
        super(texture);

        float missilWidth = 1/8f;
        float missilHeight = 1/2f;

        sprite.setSize(missilWidth, missilHeight);
        sprite.setRotation(270);
        sprite.setOriginCenter();
        sprite.setPosition(x, y - missilHeight/2);
    }

    @Override
    public void update(float delta) {
        sprite.translateX(SPEED * delta);
        updateBounds();
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }
}
