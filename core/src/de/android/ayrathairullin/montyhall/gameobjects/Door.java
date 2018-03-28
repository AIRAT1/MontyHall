package de.android.ayrathairullin.montyhall.gameobjects;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Door {
    public Sprite openSprite, closeSprite;
    public boolean isOpen = false, isGoat = false;
    public Vector2 position = new Vector2();
    public float height, width;

    public void render(SpriteBatch batch) {
        if (isOpen) {
            openSprite.draw(batch);
        }else {
            closeSprite.draw(batch);
        }
    }
}
