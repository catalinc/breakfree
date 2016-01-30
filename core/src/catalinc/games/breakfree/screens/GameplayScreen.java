package catalinc.games.breakfree.screens;

import catalinc.games.breakfree.BreakFreeGame;
import catalinc.games.breakfree.world.entities.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Array;

public class GameplayScreen extends GameScreen {
    private Array<Handler> handlers;

    public GameplayScreen(BreakFreeGame game) {
        super(game);

        handlers = new Array<>();
        handlers.add(new Handler(Input.Keys.LEFT, () -> {
            float delta = Gdx.graphics.getDeltaTime();
            Player player = world.getPlayer();
            player.setVelocity(-player.getSpeed(), 0);
            player.update(delta);
            if (player.getX() < 0) {
                player.setX(0);
            }
        }));
        handlers.add(new Handler(Input.Keys.RIGHT, () -> {
            float delta = Gdx.graphics.getDeltaTime();
            Player player = world.getPlayer();
            player.setVelocity(player.getSpeed(), 0);
            player.update(delta);
            if (player.getX() + player.getWidth() > world.getWidth()) {
                player.setX(world.getWidth() - player.getWidth());
            }
        }));
        handlers.add(new Handler(Input.Keys.M, () -> {
            if (audioPlayer.isMuted()) {
                audioPlayer.unmute();
            } else {
                audioPlayer.mute();
            }
        }));
        handlers.add(new Handler(Input.Keys.ESCAPE, world::pause));
        handlers.add(new Handler(Input.Keys.SPACE, world::resume));
    }

    @Override
    public void render(float delta) {
        for (Handler handler : handlers) {
            if (Gdx.input.isKeyPressed(handler.getKey())) {
                handler.getAction().execute();
                break;
            }
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
