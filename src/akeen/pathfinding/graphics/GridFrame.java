package akeen.pathfinding.graphics;

import java.awt.BorderLayout;
import javax.swing.JFrame;

import akeen.TerrainGrid.TerrainGrid;

@SuppressWarnings("serial")
public class GridFrame extends JFrame {

	/**
	 * Creates a JFrame object that contains a single JPanel object initialized with the 
	 * passed in terrain 2-D array of TerrainNodes as a passthrough.
	 * 
	 * @param name The name added to the frame.
	 * @param terrain 2-D array of TerrainNodes
	 */
	public GridFrame(String name, TerrainGrid terrainGrid)
	{
		super(name);
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(new GridPanel(terrainGrid),BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
	}
}
