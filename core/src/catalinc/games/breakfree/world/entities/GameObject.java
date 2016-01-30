package catalinc.games.breakfree.world.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameObject {
    protected Rectangle region;
    protected Vector2 velocity;
    protected float speed;

    public GameObject() {
        this.region = new Rectangle();
    }

    public GameObject(float x, float y, float width, float height) {
        this.region = new Rectangle(x, y, width, height);
    }

    public void setPosition(float x, float y) {
        this.region.setPosition(x, y);
    }

    public void setSize(float width, float height) {
        this.region.setSize(width, height);
    }

    public float getX() {
        return region.getX();
    }

    public float getY() {
        return region.getY();
    }

    public void setX(float x) {
        region.setX(x);
    }

    public float getWidth() {
        return region.getWidth();
    }

    public float getHeight() {
        return region.getHeight();
    }

    public void setVelocity(float x, float y) {
        this.velocity = new Vector2(x, y);
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public boolean collides(GameObject other) {
        return region.overlaps(other.region);
    }

    public void update(float delta) {
        float x = region.getX() + velocity.x * delta;
        float y = region.getY() + velocity.y * delta;
        region.setPosition(x, y);
    }
}
