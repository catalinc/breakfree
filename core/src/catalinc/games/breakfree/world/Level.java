package catalinc.games.breakfree.world;

import com.badlogic.gdx.Gdx;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class Level {
    private Properties props;
    private int index;
    private int brickScorePoints;

    public Level(String filename) {
        this.props = new Properties();
        try {
            try (Reader reader = Gdx.files.internal(filename).reader()) {
                this.props.load(reader);
            }
        } catch (IOException e) {
            throw new RuntimeException("unable to load level data from " + filename + ": " + e.getMessage());
        }
        this.index = getInt("index");
        this.brickScorePoints = getInt("brick.score.points");
    }

    public int getIndex() {
        return index;
    }

    public boolean isFirst() {
        return index == 1;
    }

    public int getBrickScorePoints() {
        return brickScorePoints;
    }

    public int getInt(String key) {
        return Integer.valueOf(this.props.getProperty(key));
    }

    public float getFloat(String key) {
        return Float.valueOf(this.props.getProperty(key));
    }
}
