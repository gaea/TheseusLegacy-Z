import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import com.sun.j3d.utils.universe.*; 
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.event.*;
import java.awt.EventQueue;
import java.awt.*;
import java.net.URL;

public class Animacion extends JFrame 
{
    public Animacion() 
    {
        initComponents();
    }

    private void initComponents() 
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Theseus Legacy");
        setMinimumSize(new Dimension(1000, 600));
        setResizable(false);

	screen = new JApplet();
	screen.setLayout(new BorderLayout());
	GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
	Canvas3D canvas3D = new Canvas3D(config);

	SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
	simpleU.getViewingPlatform().setNominalViewingTransform();

	View view = simpleU.getViewer().getView();
	view.setTransparencySortingPolicy(View.TRANSPARENCY_SORT_GEOMETRY);

	scene = new Scene(simpleU);
	simpleU.addBranchGraph(scene);
	screen.add("Center", canvas3D);
	
	add(screen);
	pack();
	setVisible(true);
    }

    private JApplet screen;
    private Scene scene;
}
