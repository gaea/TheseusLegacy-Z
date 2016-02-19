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
import java.util.*;

public class Animacion extends JFrame
{
    public Animacion( Vector movimientos, Ambiente ambiente) 
    {
	
        //setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Theseus Legacy");
        setMinimumSize(new Dimension(1000, 800));
	setVisible(true);
        setResizable(false);

	screen = new JApplet();
	screen.setLayout(new BorderLayout());
	GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
	Canvas3D canvas3D = new Canvas3D(config);

	SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
	simpleU.getViewingPlatform().setNominalViewingTransform();

	View view = simpleU.getViewer().getView();
	//view.setTransparencySortingPolicy(View.TRANSPARENCY_SORT_GEOMETRY);

	scene = new Scene(simpleU, movimientos, ambiente, canvas3D);
	simpleU.addBranchGraph(scene);
	screen.add("Center", canvas3D);
	
	add(screen);
	pack();
	
    }

    private JApplet screen;
    private Scene scene;
    private Ambiente ambiente;
    private Vector movimientos;
}
