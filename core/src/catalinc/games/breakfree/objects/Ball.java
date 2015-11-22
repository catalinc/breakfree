package catalinc.games.breakfree.objects;

public class Ball extends GameObject {
  private float damage;

  public float getDamage() {
    return damage;
  }

  public void setDamage(float damage) {
    this.damage = damage;
  }

  public void reverseVelocity() {
    velocity.scl(-1f);
  }

  public void reverseVelocityX() {
    velocity.set(-velocity.x, velocity.y);
  }
}
