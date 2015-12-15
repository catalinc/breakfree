package catalinc.games.breakfree.commands;

import catalinc.games.breakfree.entities.GameObject;
import catalinc.games.breakfree.world.World;

public class MoveRightCommand extends MoveCommand {
    public MoveRightCommand(World world, float delta, GameObject actor) {
        super(world, delta, actor);
    }

    @Override
    public void execute() {
        actor.setVelocity(actor.getSpeed(), 0);
        actor.update(delta);
        if (actor.getX() + actor.getWidth() > world.getWidth()) {
            actor.setX(world.getWidth() - actor.getWidth());
        }
    }
}
