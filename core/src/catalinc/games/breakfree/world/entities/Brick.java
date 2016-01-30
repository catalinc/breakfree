package catalinc.games.breakfree.world.entities;

public class Brick extends GameObject {
    private float strength;

    public Brick(float x, float y, float width, float height, float strength) {
        super(x, y, width, height);
        this.strength = strength;
    }

    public float getStrength() {
        return strength;
    }

    public void takeDamage(Ball ball) {
        strength -= ball.getDamage();
    }

    public boolean isDestroyed() {
        return strength <= 0;
    }
}
