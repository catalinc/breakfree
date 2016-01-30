package catalinc.games.breakfree.world.entities;

public class Ball extends GameObject {
    private float damage;

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void reverseVelocityX() {
        velocity.set(-velocity.x, velocity.y);
    }

    public void reverseVelocityY() {
        velocity.set(velocity.x, -velocity.y);
    }
}
