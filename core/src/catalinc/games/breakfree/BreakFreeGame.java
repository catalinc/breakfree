package catalinc.games.breakfree;

import catalinc.games.breakfree.screens.StartScreen;
import catalinc.games.breakfree.world.World;
import catalinc.games.breakfree.world.WorldObserver;
import catalinc.games.breakfree.world.WorldRenderer;
import com.badlogic.gdx.Game;

public class BreakFreeGame extends Game {
  private World world;
  private WorldRenderer renderer;
  private WorldObserver observer;

  @Override
  public void create() {
    world = new World();

    renderer = new WorldRenderer(world);
    world.addObserver(renderer);

    observer = new WorldObserver();
    world.addObserver(observer);

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
    observer.dispose();
  }

  public World getWorld() {
    return world;
  }

  public WorldRenderer getRenderer() {
    return renderer;
  }
}
