package catalinc.games.breakfree.world;

import com.badlogic.gdx.Gdx;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class Level {
  private Properties properties;

  public Level(String filename) {
    properties = new Properties();
    try {
      try (Reader reader = Gdx.files.internal(filename).reader()) {
        properties.load(reader);
      }
    } catch (IOException e) {
      throw new RuntimeException("unable to load configuration from " + filename);
    }
  }

  public int getInt(String key) {
    return Integer.valueOf(properties.getProperty(key));
  }

  public float getFloat(String key) {
    return Float.valueOf(properties.getProperty(key));
  }
}
