package catalinc.games.breakfree.entities;

public class Player extends GameObject {
    private int lives;
    private int score;

    public Player() {
        this.lives = 0;
        this.score = 0;
    }

    public Player(Player other) {
        this.lives = other.lives;
        this.score = other.score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void looseOneLife() {
        if (this.lives > 0) {
            this.lives--;
        }
    }

    public boolean isDead() {
        return this.lives == 0;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int amount) {
        this.score += amount;
    }
}
