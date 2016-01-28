package catalinc.games.breakfree.commands;

import catalinc.games.breakfree.entities.GameObject;
import catalinc.games.breakfree.world.World;

public class MoveLeftCommand extends MoveCommand {
    public MoveLeftCommand(float delta, GameObject actor) {
        super(delta, actor);
    }

    @Override
    public void execute(World world) {
        if (world.isPaused()) return;
        actor.setVelocity(-actor.getSpeed(), 0);
        actor.update(delta);
        if (actor.getX() < 0) {
            actor.setX(0);
        }
    }
}
