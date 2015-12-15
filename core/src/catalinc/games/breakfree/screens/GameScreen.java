package catalinc.games.breakfree.screens;

import catalinc.games.breakfree.BreakFreeGame;
import catalinc.games.breakfree.components.Renderer;
import catalinc.games.breakfree.world.World;
import com.badlogic.gdx.ScreenAdapter;

public class GameScreen extends ScreenAdapter {
    protected final BreakFreeGame game;
    protected final World world;
    protected final Renderer renderer;

    public GameScreen(BreakFreeGame game) {
        this.game = game;
        this.world = game.getWorld();
        this.renderer = game.getRenderer();
    }
}
