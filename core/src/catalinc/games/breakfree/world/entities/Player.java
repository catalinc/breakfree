package catalinc.games.breakfree.world.entities;

/** The paddle. */
public class Player extends GameObject {
    private int lives;
    private int score;

    public Player() {
        this.lives = 0;
        this.score = 0;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void loseOneLife() {
        if (this.lives > 0) {
            this.lives--;
        }
    }

    public boolean isDead() {
        return this.lives == 0;
    }

    public boolean isAlive() {
        return !isDead();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void increaseScore(int amount) {
        this.score += amount;
    }
}
