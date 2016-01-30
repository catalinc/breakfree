package catalinc.games.breakfree.screens;

import catalinc.games.breakfree.BreakFreeGame;
import catalinc.games.breakfree.world.AudioPlayer;
import catalinc.games.breakfree.world.Renderer;
import catalinc.games.breakfree.world.World;
import com.badlogic.gdx.ScreenAdapter;

public class GameScreen extends ScreenAdapter {
    protected final BreakFreeGame game;
    protected final World world;
    protected final Renderer renderer;
    protected final AudioPlayer audioPlayer;

    public GameScreen(BreakFreeGame game) {
        this.game = game;
        this.world = game.getWorld();
        this.renderer = game.getRenderer();
        this.audioPlayer = game.getAudioPlayer();
    }

    public World getWorld() {
        return world;
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    public void execute(Action action) {
        action.execute();
    }
}
