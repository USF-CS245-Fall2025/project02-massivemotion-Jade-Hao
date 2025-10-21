import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/***
 *  * @author jadehao
 * The @code CelestialObjects represents individual objects in the MassiveMotion code( star or comet)
 * Each celestial object has a position, velocity, color, size and type. Updated based on its velocity
 * during each frame
 */

public class CelestialObjects {
    public double x, y; //x and y cooridnate (horizontal and vertical) position
    public int size;
    public double velX, velY; //velX= horizontal velocity velY = vertical velocity
    public Color color;
    public String kind;

    /***
     *
     * @param x the initial x-coord of object
     * @param y the initial y-coord
     * @param size the diameter of object
     * @param velX the horizontal velocity
     * @param velY the vertical velocity
     * @param color color used
     * @param kind // string describing the type of object (star or comet)
     */
    public CelestialObjects(double x, double y, int size, double velX, double velY, Color color, String kind){
        this.x=x;
        this.y=y;
        this.size = Math.max(1,size); //double check to show the minimum visible size
        this.velX = velX;
        this.velY = velY;
        this.color = color;
        this.kind = kind;
    }

    /***
     * Update object position based on current velocity
     * Called once per fram
     */
    public void velocityUpdate(){
        x += velX;
        y += velY;
    }

    /***
     *
     * @param width the width of the visible area in pixels
     * @param height the height of the visible area
     * @return true if the object is outside the visible bounds;
     * false otherwise
     */
    public boolean offScreen(int width, int height){
        if(x + size <0){
            return true;
        }
        if(y + size <0){
            return true;
        }
        if(x> width){
            return true;
        }
        if(y > height){
            return true;
        }
        return false;
    }
}
