import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;

/***
 *  * @author jadehao
 * The @code MassiveMotion class creates a 2D animation mirroring "celestial" objects
 * (star = red circle, comet = black dots) that move across the allocated window. We get the
 * parameters from a configuration file, this includes, window size, velocity, probability of comet
 * generation and the object type.
 *We use a time to update the position of objects and repaint the window to showcase motion
 *
 * In this code there are supported list implementations such as, ArrayList, LinkList (single),
 * DoublyLinkedList and a DummyHead linked list
 * The goal of this code was to showcase my understanding of how to List data structures and it's
 * variants

 */
public class MassiveMotion extends JPanel implements ActionListener {

    protected Timer tm; //control the movement of the comets
    protected List<CelestialObjects> starBodies; //renders star + comets

    int timer_delay; //in milliseconds
    String listType;
    int window_size_x; // width of window (pixel)
    int window_size_y; // height of window

    double gen_x; // probability that a new comet appears on the x-axis (either top or bottom)
    double gen_y; //probability that a new comet appears on the y-axis (left or right)
    int body_size;
    int body_velocity;

    int star_position_x; //x-axis position of star
    int star_position_y; //y-axis position of star
    int star_size;
    int star_velocity_x; //horizontal velocity of star
    int star_velocity_y; //vertical velocity of star

    Random rand = new Random();

    /***
     * @param args- command line arguments; when 1st argument is on commandline,
     *            it is used for the configuration properties file
     */
    public MassiveMotion(String[] args) {
        Properties configFiles = new Properties(); //properties object to load config from file
        boolean loaded = false;
        if (args != null && args.length > 0) { // check if argument are provided
            try (FileInputStream fileInput = new FileInputStream(args[0])) {
                configFiles.load(fileInput);
                loaded = true;
            } catch (Exception e) {
                System.out.println("ERROR: Could not read file");
            }
        }
        //in-case file fails-set defaults
        timer_delay = Integer.parseInt(configFiles.getProperty("time_delay", "75"));
        listType = configFiles.getProperty("listType", "arraylist");
        window_size_x = Integer.parseInt(configFiles.getProperty("window_size_x", "1024"));
        window_size_y = Integer.parseInt(configFiles.getProperty("window_size_y", "768"));
        gen_x = Double.parseDouble(configFiles.getProperty("gen_x", "0.06"));
        gen_y = Double.parseDouble(configFiles.getProperty("gen_y", "0.06"));
        body_size = Integer.parseInt(configFiles.getProperty("body_size", "10"));
        body_velocity = Integer.parseInt(configFiles.getProperty("body_velocity", "3"));
        star_position_x = Integer.parseInt(configFiles.getProperty("star_position_x", "512"));
        star_position_y = Integer.parseInt(configFiles.getProperty("star_position_y", "384"));
        star_size = Integer.parseInt(configFiles.getProperty("star_size", "30"));
        star_velocity_x = Integer.parseInt(configFiles.getProperty("star_velocity_x", "0"));
        star_velocity_y = Integer.parseInt(configFiles.getProperty("star_velocity_y", "0"));

        //make list based on the type
        switch (listType.toLowerCase()) {
            case "arrayList":
                starBodies = new ArrayList<>();
            case "single":
                starBodies = new LinkedList<>();
                break;
            case "double":
                starBodies = new DoublyLinkedList<>();
                break;
            case "dummyhead":
                starBodies = new DummyHead<>();
                break;
            default:
                starBodies = new ArrayList<>();
        }
        //create a star object to list of celestial objects
        CelestialObjects star = new CelestialObjects(star_position_x, star_position_y, star_size,
                star_velocity_x, star_velocity_y, Color.RED, "star");
        starBodies.add(star);

        tm = new Timer(timer_delay, this); //timer calls actionPerformed
    }

    public void paintComponent(Graphics g) { // draw object on the screen
        super.paintComponent(g); // Probably best you leave this as is.

        g.setColor(Color.WHITE);
        g.fillRect(0,0,window_size_x, window_size_y);

        for(int i=0; i< starBodies.size(); i++){ //loop through celestial objects in the list and draw
            CelestialObjects object = starBodies.get(i);
            g.setColor(object.color); //set color of the object
            g.fillOval((int)object.x, (int) object.y, object.size, object.size);
        }
        tm.start();
    }

    /***
     *
     * @param actionEvent the event is triggered by the timer
     */
    public void actionPerformed(ActionEvent actionEvent) {
        for(int i=0; i<starBodies.size(); i++){ //update velocity of each object
            CelestialObjects object = starBodies.get(i);
            object.velocityUpdate();
        }
        for (int i=0; i<starBodies.size(); i++){ //check if any object is off-screen - if it is remove it
            CelestialObjects object= starBodies.get(i);
            if(object.offScreen(window_size_x, window_size_y) && !object.kind.equals("star")){
                starBodies.remove(i);
                i--; // decreases index to avoid skipping elements after removing them
            }
        }
        if(rand.nextDouble() < gen_x){ //randomly generate new comet based on gen_x and gen_y
            boolean fromTop = rand.nextBoolean(); // if comet comes from top or bottom
            int x= rand.nextInt(window_size_x); //random x position
            int y = fromTop ? 0 : window_size_y; //y based on direction
            double vx= randomVelocity(); //random velocity in X direction
            double vy = randomVelocity(); //random velocity in Y direction
            if(fromTop && vy < 0){ //adjust y based on direction
                vy = -vy;
            }
            if(!fromTop && vy > 0){
                vy = -vy;
            } //create new comet object and add it to the list
            CelestialObjects comet = new CelestialObjects(x, y, body_size, vx, vy, Color.BLACK, "comet");
            starBodies.add(comet);
        }

        if(rand.nextDouble() < gen_y) { //Same logic as above
            boolean fromLeft = rand.nextBoolean();
            int x = fromLeft ? 0 : window_size_x;
            int y = rand.nextInt(window_size_y);
            double vx = randomVelocity();
            if (fromLeft && vx < 0) {
                vx = -vx;
            }
            if (!fromLeft && vx > 0) {
                vx = -vx;
            }
            double vy = randomVelocity(); //create new comet object and add to list
            CelestialObjects comet = new CelestialObjects(x, y, body_size, vx, vy, Color.WHITE, "comet");
            starBodies.add(comet);
        }
        repaint();
    }

    /***
     * @return velocity (never zero)
     */
    private double randomVelocity (){
        double velocity = 0;
        while(velocity ==0){ //ensure velocity isn't zero
            velocity = rand.nextInt(body_velocity * 2 + 1) - body_velocity;
        }
        return velocity;
    }

    public static void main(String[] args) {

        System.out.println("Massive Motion starting...");
        MassiveMotion mm = new MassiveMotion(args); //create instance of MassiveMotion with commandline arg

        JFrame jf = new JFrame();
        jf.setTitle("Massive Motion");
        jf.setSize(mm.window_size_x, mm.window_size_y); // TODO: Replace with the size from configuration!
        jf.add(mm);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
