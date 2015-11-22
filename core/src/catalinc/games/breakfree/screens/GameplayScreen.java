package catalinc.games.breakfree.screens;

import catalinc.games.breakfree.BreakFreeGame;
import catalinc.games.breakfree.commands.MoveLeftCommand;
import catalinc.games.breakfree.commands.MoveRightCommand;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class GameplayScreen extends GameScreen {
  public GameplayScreen(BreakFreeGame game) {
    super(game);
  }

  @Override
  public void render(float delta) {
    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      world.addCommand(new MoveLeftCommand(world, delta, world.getPaddle()));
    }

    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      world.addCommand(new MoveRightCommand(world, delta, world.getPaddle()));
    }

    world.update(delta);
    renderer.render();
  }
}
