package catalinc.games.breakfree;

import catalinc.games.breakfree.components.Audio;
import catalinc.games.breakfree.components.Renderer;
import catalinc.games.breakfree.screens.StartScreen;
import catalinc.games.breakfree.world.World;
import com.badlogic.gdx.Game;

public class BreakFreeGame extends Game {
    private World world;
    private Renderer renderer;
    private Audio audio;

    @Override
    public void create() {
        world = new World();

        renderer = new Renderer(world);
        world.addObserver(renderer);

        audio = new Audio();
        world.addObserver(audio);

        setScreen(new StartScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        renderer.dispose();
        audio.dispose();
    }

    public World getWorld() {
        return world;
    }

    public Renderer getRenderer() {
        return renderer;
    }
}
