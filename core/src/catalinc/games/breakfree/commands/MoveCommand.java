package catalinc.games.breakfree.commands;

import catalinc.games.breakfree.entities.GameObject;
import catalinc.games.breakfree.world.World;

public abstract class MoveCommand implements Command {
  protected final World world;
  protected final float delta;
  protected final GameObject actor;

  public MoveCommand(World world, float delta, GameObject actor) {
    this.world = world;
    this.delta = delta;
    this.actor = actor;
  }
}
