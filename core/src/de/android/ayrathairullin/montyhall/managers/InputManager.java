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
                    if (handleDoor(door, touchX, touchY, i)) {
                        break;
                    }
                }
            }
        }
    }

    private static boolean handleDoor(Door door, float touchX, float touchY, int doorIndex) {
        if (touchX >= door.position.x && touchX <= door.position.x + door.width
                && touchY >= door.position.y && touchY <= door.position.y + door.height) {
            switch (GameManager.level) {
                case START:
                    GameManager.doors.get(GameManager.getGoatindices(doorIndex).random()).isOpen = true;
                    GameManager.level = GameManager.Level.CONFIRM;
                    break;
                case CONFIRM:
                    door.isOpen = true;
                    GameManager.level = GameManager.Level.END;
                    if (!door.isGoat) {
                        GameManager.hasWon = true;
                    }
                    break;
            }
            return true;
        }
        return false;
    }
}
