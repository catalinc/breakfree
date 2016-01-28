package catalinc.games.breakfree.commands;

import catalinc.games.breakfree.world.World;

/**
 * A command to be executed by the game {@link catalinc.games.breakfree.world.World}.
 */
public interface Command {
    void execute(World world);
}
