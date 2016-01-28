package catalinc.games.breakfree.screens;

import catalinc.games.breakfree.BreakFreeGame;
import catalinc.games.breakfree.commands.MoveLeftCommand;
import catalinc.games.breakfree.commands.MoveRightCommand;
import catalinc.games.breakfree.commands.PauseCommand;
import catalinc.games.breakfree.commands.ResumeCommand;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class GameplayScreen extends GameScreen {
    public GameplayScreen(BreakFreeGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            world.addCommand(new MoveLeftCommand(delta, world.getPlayer()));
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            world.addCommand(new MoveRightCommand(delta, world.getPlayer()));
        } else if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            world.addCommand(new PauseCommand());
        } else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            world.addCommand(new ResumeCommand());
        }

        world.update(delta);
        renderer.render();

        if (world.playerLost()) {
            game.setScreen(new PlayerLostScreen(game));
        } else if (world.playerWon()) {
            game.setScreen(new PlayerWonScreen(game));
        } else if (world.levelCompleted()) {
            game.setScreen(new LevelCompletedScreen(game));
        }
    }
}
