package catalinc.games.breakfree;

import catalinc.games.breakfree.screens.StartScreen;
import catalinc.games.breakfree.world.AudioPlayer;
import catalinc.games.breakfree.world.Renderer;
import catalinc.games.breakfree.world.World;
import com.badlogic.gdx.Game;

public class BreakFreeGame extends Game {
    private World world;
    private Renderer renderer;
    private AudioPlayer audioPlayer;

    @Override
    public void create() {
        world = new World("game.properties");

        renderer = new Renderer(world);
        world.addObserver(renderer);

        audioPlayer = new AudioPlayer("sounds.properties");
        world.addObserver(audioPlayer);

        setScreen(new StartScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        renderer.dispose();
        audioPlayer.dispose();
    }

    public World getWorld() {
        return world;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }
}
