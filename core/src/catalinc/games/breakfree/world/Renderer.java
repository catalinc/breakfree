package catalinc.games.breakfree.world;

import catalinc.games.breakfree.world.entities.Ball;
import catalinc.games.breakfree.world.entities.Brick;
import catalinc.games.breakfree.world.entities.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Align;

/**
 * {@link World} renderer.
 */
public class Renderer implements World.Observer {
    private final World world;

    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final BitmapFont font;
    private final Texture texture;

    private Sprite playerSprite;
    private Sprite ballSprite;
    private Sprite noDamageBrickSprite;
    private Sprite mediumDamageBrickSprite;
    private Sprite highDamageBrickSprite;

    private int maxBrickStrength;

    public Renderer(World world) {
        this.world = world;

        camera = new OrthographicCamera(world.getWidth(), world.getHeight());
        batch = new SpriteBatch();

        texture = new Texture(Gdx.files.internal(world.getSpriteSheetPath()));

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(world.getFontPath()));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 22;
        parameter.color = Color.GREEN;
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.Linear;
        font = generator.generateFont(parameter);
        generator.dispose();

        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
    }

    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        Player player = world.getPlayer();
        playerSprite.setPosition(player.getX(), player.getY());
        playerSprite.draw(batch);

        Ball ball = world.getBall();
        ballSprite.setPosition(ball.getX(), ball.getY());
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

        Level level = world.getLevel();
        String header = String.format("Level %d | Score: %d | Lives: %d",
                level.getIndex(), player.getScore(), player.getLives());
        font.draw(batch, header, 1, world.getHeight() - 1, world.getWidth(), Align.center, false);

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
    public void onNotify(Event event) {
        if (event == Event.LEVEL_LOADED) {
            camera.setToOrtho(false, world.getWidth(), world.getHeight());
            camera.update();
            batch.setProjectionMatrix(camera.combined);

            int x, y;
            Level level = world.getLevel();

            Player player = world.getPlayer();
            int playerWidth = (int) player.getWidth();
            int playerHeight = (int) player.getHeight();

            x = level.getInt("sprite.player.x");
            y = level.getInt("sprite.player.y");
            playerSprite = new Sprite(texture, x, y, playerWidth, playerHeight);

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
