package catalinc.games.breakfree.commands;

import catalinc.games.breakfree.world.World;

public class ResumeCommand implements Command {
    @Override
    public void execute(World world) {
        world.resume();
    }
}
