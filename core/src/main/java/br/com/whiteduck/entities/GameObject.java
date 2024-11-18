package br.com.whiteduck.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject {
    protected Sprite sprite;
    protected Rectangle bounds;

    public GameObject(Texture texture) {
        this.sprite = new Sprite(texture);
        this.bounds = new Rectangle();
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public abstract void update(float delta);

    protected void updateBounds() {
        bounds.set(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public float getX() { return sprite.getX(); }
    public float getY() { return sprite.getY(); }
    public float getWidth() { return sprite.getWidth(); }
    public float getHeight() { return sprite.getHeight(); }
}
