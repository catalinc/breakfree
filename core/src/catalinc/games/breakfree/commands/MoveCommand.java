package catalinc.games.breakfree.commands;

import catalinc.games.breakfree.entities.GameObject;

public abstract class MoveCommand implements Command {
    protected final float delta;
    protected final GameObject actor;

    public MoveCommand(float delta, GameObject actor) {
        this.delta = delta;
        this.actor = actor;
    }
}
