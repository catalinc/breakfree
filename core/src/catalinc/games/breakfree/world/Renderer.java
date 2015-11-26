package catalinc.games.breakfree.world;

import catalinc.games.breakfree.objects.Ball;
import catalinc.games.breakfree.objects.Brick;
import catalinc.games.breakfree.objects.Paddle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Renderer implements World.Observer {
  private final World world;

  private final SpriteBatch batch;
  private final OrthographicCamera camera;
  private final BitmapFont font;
  private final Texture texture;

  private Sprite paddleSprite;
  private Sprite ballSprite;
  private Sprite noDamageBrickSprite;
  private Sprite mediumDamageBrickSprite;
  private Sprite highDamageBrickSprite;

  private int maxBrickStrength;

  public Renderer(World world) {
    this.world = world;

    camera = new OrthographicCamera();
    batch = new SpriteBatch();

    font = new BitmapFont();
    texture = new Texture(Gdx.files.internal("sprite_sheet.png"));

    Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
  }

  public void render() {
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.setProjectionMatrix(camera.combined);
    batch.begin();

    paddleSprite.setPosition(world.getPaddle().getX(), world.getPaddle().getY());
    paddleSprite.draw(batch);

    ballSprite.setPosition(world.getBall().getX(), world.getBall().getY());
    ballSprite.draw(batch);

    for (int i = 0; i < world.getBricks().size; i++) {
      Brick brick = world.getBricks().get(i);
      Sprite brickSprite;
      if (brick.getStrength() > maxBrickStrength * 0.66) {
        brickSprite = noDamageBrickSprite;
      } else if (brick.getStrength() >= maxBrickStrength * 0.33) {
        brickSprite = mediumDamageBrickSprite;
      } else {
        brickSprite = highDamageBrickSprite;
      }
      brickSprite.setPosition(brick.getX(), brick.getY());
      brickSprite.draw(batch);
    }

    batch.end();
  }

  public SpriteBatch getBatch() {
    return batch;
  }

  public BitmapFont getFont() {
    return font;
  }

  public void dispose() {
    texture.dispose();
    batch.dispose();
  }

  @Override
  public void onNotify(World.Event event) {
    if (event == World.Event.LEVEL_LOADED) {
      camera.setToOrtho(false, world.getWidth(), world.getHeight());
      camera.update();
      batch.setProjectionMatrix(camera.combined);

      int x, y;
      Level level = world.getLevel();

      Paddle paddle = world.getPaddle();
      int paddleWidth = (int) paddle.getWidth();
      int paddleHeight = (int) paddle.getHeight();

      x = level.getInt("sprite.paddle.x");
      y = level.getInt("sprite.paddle.y");
      paddleSprite = new Sprite(texture, x, y, paddleWidth, paddleHeight);

      Ball ball = world.getBall();
      int ballWidth = (int) ball.getWidth();
      int ballHeight = (int) ball.getHeight();

      x = level.getInt("sprite.ball.x");
      y = level.getInt("sprite.ball.y");
      ballSprite = new Sprite(texture, x, y, ballWidth, ballHeight);

      Brick brick = world.getBricks().get(0);
      int brickWidth = (int) brick.getWidth();
      int brickHeight = (int) brick.getHeight();

      x = level.getInt("sprite.brick.damage.none.x");
      y = level.getInt("sprite.brick.damage.none.y");
      noDamageBrickSprite = new Sprite(texture, x, y, brickWidth, brickHeight);

      x = level.getInt("sprite.brick.damage.medium.x");
      y = level.getInt("sprite.brick.damage.medium.y");
      mediumDamageBrickSprite = new Sprite(texture, x, y, brickWidth, brickHeight);

      x = level.getInt("sprite.brick.damage.high.x");
      y = level.getInt("sprite.brick.damage.high.y");
      highDamageBrickSprite = new Sprite(texture, x, y, brickWidth, brickHeight);

      maxBrickStrength = level.getInt("brick.strength");
    }
  }
}
