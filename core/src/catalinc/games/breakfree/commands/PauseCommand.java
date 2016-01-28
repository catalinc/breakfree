package catalinc.games.breakfree.commands;

import catalinc.games.breakfree.world.World;

public class PauseCommand implements Command {
    @Override
    public void execute(World world) {
        world.pause();
    }
}
