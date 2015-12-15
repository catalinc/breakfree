package catalinc.games.breakfree.world;

import com.badlogic.gdx.Gdx;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class Level {
    public static final int MAX_LEVEL = 3;
    public static final int MAX_PLAYER_LIVES = 3;

    private Properties properties;
    private String name;
    private int index;
    private int brickScorePoints;

    public Level(String filename) {
        this.properties = new Properties();
        try {
            try (Reader reader = Gdx.files.internal(filename).reader()) {
                this.properties.load(reader);
            }
        } catch (IOException e) {
            throw new RuntimeException("unable to load configuration from " + filename);
        }
        this.name = getString("level.name");
        this.index = getInt("level.index");
        this.brickScorePoints = getInt("brick.score.points");
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public int getBrickScorePoints() {
        return brickScorePoints;
    }

    public int getInt(String key) {
        return Integer.valueOf(this.properties.getProperty(key));
    }

    public float getFloat(String key) {
        return Float.valueOf(this.properties.getProperty(key));
    }

    public String getString(String key) {
        return this.properties.getProperty(key);
    }
}
