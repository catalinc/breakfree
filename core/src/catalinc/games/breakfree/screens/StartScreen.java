package catalinc.games.breakfree.screens;

import catalinc.games.breakfree.BreakFreeGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class StartScreen extends GameScreen {
    public StartScreen(BreakFreeGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        SpriteBatch batch = renderer.getBatch();
        BitmapFont font = renderer.getFont();

        batch.begin();
        font.draw(batch, "Press any key to start", 0,
                Gdx.graphics.getHeight() / 2,
                Gdx.graphics.getWidth(), Align.center, false);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            world.loadLevel(1);
            game.setScreen(new GameplayScreen(game));
        }
    }
}