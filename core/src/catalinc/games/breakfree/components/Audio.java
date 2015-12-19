package catalinc.games.breakfree.components;

import catalinc.games.breakfree.world.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Map;

public class Audio implements World.Observer {
    private final Map<World.Event, Sound> soundForEvent;
    private boolean muted;

    public Audio() {
        soundForEvent = new HashMap<>();
        soundForEvent.put(World.Event.BALL_DROP,
                Gdx.audio.newSound(Gdx.files.internal("sounds/ball_drop.mp3")));
        soundForEvent.put(World.Event.BRICK_HIT,
                Gdx.audio.newSound(Gdx.files.internal("sounds/brick_hit.mp3")));
        soundForEvent.put(World.Event.BRICK_DESTROYED,
                Gdx.audio.newSound(Gdx.files.internal("sounds/brick_destroyed.mp3")));
        soundForEvent.put(World.Event.PADDLE_HIT,
                Gdx.audio.newSound(Gdx.files.internal("sounds/brick_hit.mp3")));
    }

    public void onNotify(World.Event event) {
        if (muted) return;

        Sound sound = soundForEvent.get(event);
        if (sound != null) {
            sound.play();
        }
    }

    public void dispose() {
        soundForEvent.values().forEach(Sound::dispose);
    }

    public void mute() {
        muted = true;
    }

    public void unmute() {
        muted = false;
    }
}
