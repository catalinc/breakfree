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
            world.addCommand(new MoveLeftCommand(world, delta, world.getPlayer()));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            world.addCommand(new MoveRightCommand(world, delta, world.getPlayer()));
        }

        world.update(delta);
        renderer.render();

        if (world.playerLost()) {
            game.setScreen(new PlayerLostScreen(game));
        }
        if (world.playerWon()) {
            game.setScreen(new PlayerWonScreen(game));
        }
        if (world.levelCompleted()) {
            game.setScreen(new LevelCompletedScreen(game));
        }
    }
}
