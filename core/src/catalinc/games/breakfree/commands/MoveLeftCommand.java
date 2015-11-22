package catalinc.games.breakfree.commands;

import catalinc.games.breakfree.objects.GameObject;
import catalinc.games.breakfree.world.World;

public class MoveLeftCommand extends MoveCommand {
  public MoveLeftCommand(World world, float delta, GameObject actor) {
    super(world, delta, actor);
  }

  @Override
  public void execute() {
    actor.setVelocity(-actor.getSpeed(), 0);
    actor.update(delta);
    if (actor.getX() < 0) {
      actor.setX(0);
    }
  }
}
