package br.com.whiteduck.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class SpaceShip extends GameObject {
    private float speed = 4f;
    private float worldHeight;

    public SpaceShip(Texture texture, float worldHeight) {
        super(texture);
        this.worldHeight = worldHeight;

        sprite.setSize(1, 1);
        sprite.rotate(90);
        sprite.setOriginCenter();
    }

    @Override
    public void update(float delta) {
        updateBounds();
        // Limita movimento vertical
        sprite.setY(MathUtils.clamp(sprite.getY(), 0, worldHeight - sprite.getWidth()));
    }

    public void moveUp(float delta) {
        sprite.translateY(speed * delta);
    }

    public void moveDown(float delta) {
        sprite.translateY(-speed * delta);
    }

    public void setCenterY(float y) {
        sprite.setCenterY(y);
    }
}
