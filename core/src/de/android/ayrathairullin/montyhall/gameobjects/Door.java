package de.android.ayrathairullin.montyhall.gameobjects;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Door {
    public Sprite openSprite; //Represents sprite to display when the door is open
    public Sprite closeSprite; //Represents sprite to display when the door is open
    public boolean isOpen = false; //Whether the door is open or not
    public boolean isGoat = false; // indicates whether a goat is behind the door

    public Vector2 position = new Vector2(); // position of the door
    //door dimensions
    public float height;
    public float width;

    public void render(SpriteBatch batch){

        if(isOpen){
            openSprite.draw(batch);
        }
        else{
            closeSprite.draw(batch);
        }

    }
}
