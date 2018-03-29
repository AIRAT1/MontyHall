package de.android.ayrathairullin.montyhall.managers;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextManager {
    static BitmapFont font ; // we draw the text to the screen using this variable
    // Texts corresponding to different states
    static String start = "Select a door";
    static StringBuffer confirm;
    static String win = "You Win!";
    static String lose = "You Lose!";
    // viewport width and height
    static float width,height;

    public static void initialize(float width,float height){

        TextManager.width = width;
        TextManager.height= height;
        //set the font color to cyan

        font = new BitmapFont();
        font.setColor(Color.CYAN);
        //scale the font size according to screen width
        font.getData().setScale(width/1600f);

        confirm = new StringBuffer( (String) "You selected door no.Do you want to switch or stay?");
    }

    public static void displayMessage(SpriteBatch batch){
        // draw the text based on the game state
        switch(GameManager.level){
            case START:
                // calculations to center the text on the screen
                font.draw(batch, start, (width/2 - new GlyphLayout(font, start).width/2), GameManager.doors.first().closeSprite.getY()/2 + new GlyphLayout(font, start).height/2);
                break;
            case CONFIRM:
                font.draw(batch, confirm, (width/2 - new GlyphLayout(font, confirm).width/2),GameManager.doors.first().closeSprite.getY()/2 + new GlyphLayout(font, confirm).height/2);
                break;
            case END:
                // draw win/lose text based on the status
                if(GameManager.hasWon)
                    font.draw(batch, win,(width/2 - new GlyphLayout(font, win).width/2), GameManager.doors.first().closeSprite.getY()/2 + new GlyphLayout(font, win).height/2);
                else
                    font.draw(batch,lose,(width/2 - new GlyphLayout(font, lose).width/2), GameManager.doors.first().closeSprite.getY()/2 + new GlyphLayout(font, lose).height/2);
                break;
        }

    }

    public static void setSelectedDoor(int doorIndex){
        // insert selected door number into confirm display text
        confirm.insert(confirm.indexOf("door no")+"door no".length(), " "+(doorIndex+1));
    }

}
