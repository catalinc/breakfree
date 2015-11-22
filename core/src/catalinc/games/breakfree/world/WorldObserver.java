package catalinc.games.breakfree.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Map;

public class WorldObserver implements World.Observer {
  private final Map<World.Event, Sound> soundForEvent;

  public WorldObserver() {
    soundForEvent = new HashMap<>();
    soundForEvent.put(World.Event.BALL_DROP,
        Gdx.audio.newSound(Gdx.files.internal("ball_drop.mp3")));
    soundForEvent.put(World.Event.BRICK_HIT,
        Gdx.audio.newSound(Gdx.files.internal("brick_hit.mp3")));
    soundForEvent.put(World.Event.BRICK_DESTROYED,
        Gdx.audio.newSound(Gdx.files.internal("brick_destroyed.mp3")));
    soundForEvent.put(World.Event.PADDLE_HIT,
        Gdx.audio.newSound(Gdx.files.internal("brick_hit.mp3")));
  }

  public void onNotify(World.Event event) {
    Sound sound = soundForEvent.get(event);
    if (sound != null) {
      sound.play();
    }
    if (event == World.Event.BALL_DROP) {
    }
  }

  public void dispose() {
    soundForEvent.values().forEach(Sound::dispose);
  }
}
