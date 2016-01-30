package catalinc.games.breakfree.screens;

public class Handler {
    private final int key;
    private final Action action;

    public Handler(int key, Action action) {
        this.key = key;
        this.action = action;
    }

    public int getKey() {
        return key;
    }

    public Action getAction() {
        return action;
    }
}
