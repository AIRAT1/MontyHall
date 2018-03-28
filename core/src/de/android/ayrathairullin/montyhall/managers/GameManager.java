package de.android.ayrathairullin.montyhall.managers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;

import de.android.ayrathairullin.montyhall.gameobjects.Door;

public class GameManager {
    public static enum Level {
        START, CONFIRM, END
    }
    static Level level;

    private static final float DOOR_RESICE_FACTOR = 2500;
    private static final float DOOR_VERT_POSITION_FACTOR = 3;
    private static final float DOOR1_HORIZ_POSITION_FACTOR = 7.77f;
    private static final float DOOR2_HORIZ_POSITION_FACTOR = 2.57f;
    private static final float DOOR3_HORIZ_POSITION_FACTOR = 1.52f;

    static Array<Door> doors;
    static Texture doorTexture, carTexture, goatTexture;
    static float width, height;
    static Vector3 temp = new Vector3();
    static IntArray goatIndices;
    static boolean hasWon = false;

    public static void initialize(float width, float height) {
        level = Level.START;
        GameManager.width = width;
        GameManager.height = height;
        doorTexture = new Texture(Gdx.files.internal("data/door_close.png"));
        carTexture = new Texture(Gdx.files.internal("data/door_open_car.png"));
        goatTexture = new Texture(Gdx.files.internal("data/door_open_goat.png"));
        goatIndices = new IntArray();
        initDoors();
    }

    private static void initDoors() {
        doors = new Array<Door>();
        for (int i = 0; i < 3; i++) {
            doors.add(new Door());
        }

        doors.get(0).position.set(width / DOOR1_HORIZ_POSITION_FACTOR, height / DOOR_VERT_POSITION_FACTOR);
        doors.get(1).position.set(width / DOOR2_HORIZ_POSITION_FACTOR, height / DOOR_VERT_POSITION_FACTOR);
        doors.get(2).position.set(width / DOOR3_HORIZ_POSITION_FACTOR, height / DOOR_VERT_POSITION_FACTOR);

        for (Door door : doors) {
            door.closeSprite = new Sprite(doorTexture);
            door.width = door.closeSprite.getWidth() * (width / DOOR_RESICE_FACTOR);
            door.height = door.closeSprite.getHeight() * (width / DOOR_RESICE_FACTOR);
            door.closeSprite.setSize(door.width, door.height);
            door.closeSprite.setPosition(door.position.x, door.position.y);

            door.openSprite = new Sprite();
            door.openSprite.setSize(door.width, door.height);
            door.openSprite.setPosition(door.position.x, door.position.y);
        }

        doors.get(0).openSprite.setRegion(goatTexture);
        doors.get(1).openSprite.setRegion(carTexture);
        doors.get(2).openSprite.setRegion(goatTexture);

        doors.get(0).isGoat = true;
        doors.get(1).isGoat = false;
        doors.get(2).isGoat = true;
    }

    public static IntArray getGoatindices(int selectedDoorIndex) {
        goatIndices.clear();
        for (int i = 0; i < doors.size; i++) {
            if (i != selectedDoorIndex && doors.get(i).isGoat) {
                goatIndices.add(i);
            }
        }
        return goatIndices;
    }

    public static void renderGame(SpriteBatch batch) {
        for (Door door : doors) {
            door.render(batch);
        }
    }

    public static void dispose() {
        doorTexture.dispose();
        carTexture.dispose();
        goatTexture.dispose();
    }
}
