package catalinc.games.breakfree.world;

import catalinc.games.breakfree.commands.Command;
import catalinc.games.breakfree.objects.Ball;
import catalinc.games.breakfree.objects.Brick;
import catalinc.games.breakfree.objects.Paddle;
import com.badlogic.gdx.utils.Array;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class World {
  private static final float MAX_BOUNCE_ANGLE = (float) (5 * Math.PI / 12); // 75 degrees in rad

  private Level level;

  private int width;
  private int height;

  private Paddle paddle;
  private Ball ball;
  private Array<Brick> bricks;

  private final List<Observer> observers;
  private final Queue<Command> commands;

  public enum Event {
    BALL_DROP,
    PADDLE_HIT,
    BRICK_HIT,
    BRICK_DESTROYED,
    ALL_BRICKS_DESTROYED,
    LEVEL_LOADED
  }

  public interface Observer {
    void onNotify(Event event);
  }

  public World() {
    observers = new LinkedList<>();
    commands = new LinkedList<>();
  }

  public void loadLevel(String filename) {
    level = new Level(filename);

    width = level.getInt("world.width");
    height = level.getInt("world.height");

    createPaddle();
    createBall();
    createBricks();

    notifyEvent(Event.LEVEL_LOADED);
  }

  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  public void addCommand(Command command) {
    commands.add(command);
  }

  public void update(float delta) {
    while (!commands.isEmpty()) {
      Command command = commands.poll();
      command.execute();
    }

    ball.update(delta);

    for (int i = 0; i < bricks.size; i++) {
      Brick brick = bricks.get(i);
      if (brick.collides(ball)) {
        ball.reverseVelocity();
        brick.takeDamage(ball);
        if (brick.isDestroyed()) {
          notifyEvent(Event.BRICK_DESTROYED);
          bricks.removeIndex(i);
        } else {
          notifyEvent(Event.BRICK_HIT);
        }
        if (bricks.size == 0) {
          notifyEvent(Event.ALL_BRICKS_DESTROYED);
        }
        break;
      }
    }

    if (ball.getY() < 0) {
      notifyEvent(Event.BALL_DROP);
      resetPaddleAndBall();
      return;
    }

    if (ball.getX() < 0) {
      ball.setX(0);
      ball.reverseVelocityX();
    }

    if (ball.getX() + ball.getWidth() > width) {
      ball.setX(width - ball.getWidth());
      ball.reverseVelocityX();
    }

    if (ball.getY() + ball.getHeight() > height) {
      ball.reverseVelocity();
    }

    if (paddle.collides(ball)) {
      notifyEvent(Event.PADDLE_HIT);

      float half = paddle.getWidth() / 2;
      float relativeIntersectX = paddle.getX() + half - (ball.getX() + ball.getWidth() / 2);
      float normalizedRelativeIntersectX = relativeIntersectX / half;
      float bounceAngle = normalizedRelativeIntersectX * MAX_BOUNCE_ANGLE;

      float x = (float) -(ball.getSpeed() * Math.sin(bounceAngle));
      float y = (float) (ball.getSpeed() * Math.cos(bounceAngle));
      ball.setVelocity(x, y);
    }
  }

  public Level getLevel() {
    return level;
  }

  public Paddle getPaddle() {
    return paddle;
  }

  public Ball getBall() {
    return ball;
  }

  public Array<Brick> getBricks() {
    return bricks;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  private void resetPaddleAndBall() {
    createPaddle();
    createBall();
  }

  private void createPaddle() {
    int paddleWidth = level.getInt("paddle.width");
    int paddleHeight = level.getInt("paddle.height");
    float paddleSpeed = level.getInt("paddle.speed");

    paddle = new Paddle();
    paddle.setSize(paddleWidth, paddleHeight);
    paddle.setSpeed(paddleSpeed);
    paddle.setVelocity(0, 0);
    paddle.setPosition((this.width - paddleWidth) / 2, paddleHeight);
  }

  private void createBall() {
    int ballWidth = level.getInt("ball.width");
    int ballHeight = level.getInt("ball.height");
    float ballSpeed = level.getFloat("ball.speed");
    float ballDamage = level.getFloat("ball.damage");

    ball = new Ball();
    ball.setSize(ballWidth, ballHeight);
    ball.setDamage(ballDamage);
    ball.setSpeed(ballSpeed);
    ball.setVelocity(0, ballSpeed);
    ball.setPosition(paddle.getX() + (paddle.getWidth() - ballWidth) / 2, paddle.getHeight() + ballHeight);
  }

  private void createBricks() {
    int brickWidth = level.getInt("brick.width");
    int brickHeight = level.getInt("brick.height");
    float brickStrength = level.getFloat("brick.strength");
    int brickWalls = level.getInt("brick.walls");

    bricks = new Array<>();
    int bricksPerLine = width / brickWidth;
    for (int i = 1; i <= brickWalls; i++) {
      for (int j = 0; j < bricksPerLine; j++) {
        Brick brick = new Brick(
            j * brickWidth,
            height - i * brickHeight,
            brickWidth,
            brickHeight,
            brickStrength);
        bricks.add(brick);
      }
    }
  }

  private void notifyEvent(Event event) {
    for (Observer observer: observers) {
      observer.onNotify(event);
    }
  }
}
