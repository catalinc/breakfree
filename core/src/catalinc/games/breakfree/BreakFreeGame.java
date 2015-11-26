package catalinc.games.breakfree;

import catalinc.games.breakfree.screens.StartScreen;
import catalinc.games.breakfree.world.World;
import catalinc.games.breakfree.world.AudioPlayer;
import catalinc.games.breakfree.world.Renderer;
import com.badlogic.gdx.Game;

public class BreakFreeGame extends Game {
  private World world;
  private Renderer renderer;
  private AudioPlayer audioPlayer;

  @Override
  public void create() {
    world = new World();

    renderer = new Renderer(world);
    world.addObserver(renderer);

    audioPlayer = new AudioPlayer();
    world.addObserver(audioPlayer);

    world.loadLevel("level1.properties");

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
}
