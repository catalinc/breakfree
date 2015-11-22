package catalinc.games.breakfree.screens;

import catalinc.games.breakfree.BreakFreeGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
    font.draw(batch, "Welcome to BreakFree!!! ", world.getWidth() / 2 - 50, world.getHeight() / 2 + 50);
    font.draw(batch, "Press any key to start", world.getWidth() / 2 - 50, world.getHeight() / 2);
    batch.end();

    if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
      game.setScreen(new GameplayScreen(game));
    }
  }
}