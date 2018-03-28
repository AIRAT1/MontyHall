package de.android.ayrathairullin.montyhall.managers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

import de.android.ayrathairullin.montyhall.gameobjects.Door;

public class InputManager {
    public static void handleInput(OrthographicCamera camera) {
        if (Gdx.input.justTouched()) {
            GameManager.temp.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(GameManager.temp);
            float touchX = GameManager.temp.x;
            float touchY = GameManager.temp.y;
            for (int i = 0; i < GameManager.doors.size; i++) {
                Door door = GameManager.doors.get(i);
                if (!door.isOpen) {
                    if (handleDoor(door, touchX, touchY)) {
                        break;
                    }
                }
            }
        }
    }

    private static boolean handleDoor(Door door, float touchX, float touchY) {
        if (touchX >= door.position.x && touchX <= door.position.x + door.width
                && touchY >= door.position.y && touchY <= door.position.y + door.height) {
            door.isOpen = true;
            return true;
        }
        return false;
    }
}
