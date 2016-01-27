package catalinc.games.breakfree.screens;

import catalinc.games.breakfree.BreakFreeGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class PlayerLostScreen extends GameScreen {
    public PlayerLostScreen(BreakFreeGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        SpriteBatch batch = renderer.getBatch();
        BitmapFont font = renderer.getFont();

        batch.begin();
        font.draw(batch, "You lost. Sorry :-(. Press 'R' to restart or 'Q' to exit.", 0,
                world.getHeight() / 2, world.getWidth(), Align.center, false);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            world.loadLevel(1);
            game.setScreen(new GameplayScreen(game));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            Gdx.app.exit();
        }
    }

}
