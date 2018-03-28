package de.android.ayrathairullin.montyhall.managers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import de.android.ayrathairullin.montyhall.gameobjects.Door;

public class GameManager {
    private static final float DOOR_RESICE_FACTOR = 2500;
    private static final float DOOR_VERT_POSITION_FACTOR = 3;
    private static final float DOOR1_HORIZ_POSITION_FACTOR = 7.77f;
    private static final float DOOR2_HORIZ_POSITION_FACTOR = 2.57f;
    private static final float DOOR3_HORIZ_POSITION_FACTOR = 1.52f;

    static Array<Door> doors;
    static Texture doorTexture;
    static float width, height;

    public static void initialize(float width, float height) {
        GameManager.width = width;
        GameManager.height = height;
        doorTexture = new Texture(Gdx.files.internal("data/door_close.png"));
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
        }
    }

    public static void renderGame(SpriteBatch batch) {
        for (Door door : doors) {
            door.render(batch);
        }
    }

    public static void dispose() {
        doorTexture.dispose();
    }
}
