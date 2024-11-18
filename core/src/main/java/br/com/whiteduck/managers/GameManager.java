package br.com.whiteduck.managers;

import br.com.whiteduck.entities.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameManager {
    private SpaceShip spaceShip;
    private Array<Missile> missiles;
    private Array<Drop> drops;
    private FitViewport viewport;
    private float dropTimer;
    private Texture missileTexture;
    private Texture dropTexture;
    private float shootingCooldown; // Timer para controlar cadência
    private static final float SHOOTING_DELAY = 1f; // 1 segundo entre tiros

    public GameManager(FitViewport viewport, Texture shipTexture,
                       Texture missileTexture, Texture dropTexture) {
        this.viewport = viewport;
        this.missileTexture = missileTexture;
        this.dropTexture = dropTexture;

        spaceShip = new SpaceShip(shipTexture, viewport.getWorldHeight());
        missiles = new Array<>();
        drops = new Array<>();
        shootingCooldown = 0;
    }

    public void update(float delta) {
        if (shootingCooldown > 0) {
            shootingCooldown -= delta;
        }
        // Atualiza nave
        spaceShip.update(delta);

        // Atualiza mísseis
        for (int i = missiles.size - 1; i >= 0; i--) {
            Missile missile = missiles.get(i);
            missile.update(delta);

            // Remove mísseis fora da tela
            if (missile.getX() > viewport.getWorldWidth()) {
                missiles.removeIndex(i);
                continue;
            }

            // Verifica colisões com meteoros
            for (int j = drops.size - 1; j >= 0; j--) {
                Drop drop = drops.get(j);
                if (missile.getBounds().overlaps(drop.getBounds())) {
                    missile.deactivate();
                    drop.deactivate();
                    missiles.removeIndex(i);
                    drops.removeIndex(j);
                    break;
                }
            }
        }

        // Atualiza meteoros
        for (int i = drops.size - 1; i >= 0; i--) {
            Drop drop = drops.get(i);
            drop.update(delta);

            if (drop.getY() > viewport.getWorldHeight()) {
                drops.removeIndex(i);
            }
        }

        // Cria novos meteoros
        dropTimer += delta;
        if (dropTimer > 1f) {
            dropTimer = 0;
            createDrop();
        }
    }

    public boolean canShoot() {
        return shootingCooldown <= 0;
    }

    public void draw(SpriteBatch batch) {
        spaceShip.draw(batch);

        for (Missile missile : missiles) {
            missile.draw(batch);
        }

        for (Drop drop : drops) {
            drop.draw(batch);
        }
    }

    public void moveShipUp(float delta) {
        spaceShip.moveUp(delta);
    }

    public void moveShipDown(float delta) {
        spaceShip.moveDown(delta);
    }

    public void setShipY(float y) {
        spaceShip.setCenterY(y);
    }

    public void createMissile() {
        if (canShoot()) {
            Missile missile = new Missile(missileTexture,
                spaceShip.getX() + spaceShip.getWidth(),
                spaceShip.getY() + spaceShip.getHeight() / 2);
            missiles.add(missile);
            shootingCooldown = SHOOTING_DELAY; // Reset do cooldown
        }
    }

    private void createDrop() {
        drops.add(new Drop(dropTexture, viewport.getWorldWidth()));
    }
}
