package catalinc.games.breakfree.commands;

import catalinc.games.breakfree.entities.GameObject;
import catalinc.games.breakfree.world.World;

public class MoveRightCommand extends MoveCommand {
    public MoveRightCommand(float delta, GameObject actor) {
        super(delta, actor);
    }

    @Override
    public void execute(World world) {
        if (world.isPaused()) return;
        actor.setVelocity(actor.getSpeed(), 0);
        actor.update(delta);
        if (actor.getX() + actor.getWidth() > world.getWidth()) {
            actor.setX(world.getWidth() - actor.getWidth());
        }
    }
}
