package akeen.pathfinding.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import akeen.TerrainGrid.TerrainGrid;
import akeen.TerrainGrid.TerrainNode;
import akeen.pathfinding.Pathable;
import akeen.pathfinding.Pathfinder;

@SuppressWarnings("serial")
public class GridPanel extends JPanel {

	int gridCellSize = 60; // default length and width of each cell in the displayed grid
	TerrainNode[][] terrain; // grid of TerrainNodes to display
	Point startingCell = new Point(1,3);
	Point endingCell = null;
	Pathable[] path = null;
	
	/**
	 * Initializes the terrain grid based on the parameter and sets the Preferred Size.
	 * @param terrain
	 */
	public GridPanel(TerrainGrid terrainGrid)
	{
		this.terrain = terrainGrid.terrain;
		this.setPreferredSize(new Dimension(terrain.length * gridCellSize, terrain[0].length * gridCellSize));
		this.addMouseListener(new MouseAdapter(){
	         public void mouseClicked(MouseEvent e) {
	        	 endingCell = new Point(e.getX()/gridCellSize, e.getY()/gridCellSize);
	        	 // do the pathfinding to the ending cell
	        	 path = Pathfinder.findPath(terrain[startingCell.x][startingCell.y], terrain[endingCell.x][endingCell.y]);
	        	 if(path == null)
	        	 {
	        		 System.out.println("Cannot travel from " + startingCell + " to: " + endingCell);
	        	 }
	        	 else
	        	 {
	        		 System.out.println("Path drawn.");
	        	 }
	             repaint();
	         }                
	      });
	}
	
	/*
	 * Draws each cell of the terrain 2-D array including a DARK GRAY border and prints the x,y coordinates 
	 * to the screen within the cell.
	 */
	public void paint(Graphics g)
	{
		//super.paint(g);
		if(terrain != null)
		{
			// draw the terrain grid
			for(int x=0; x<terrain.length; x++) // for each column
			{
				for(int y=0; y<terrain[x].length; y++) // for each row
				{
					TerrainNode terrainNode = terrain[x][y];
					g.setColor(terrainNode.getColor());
					g.fillRect(x * gridCellSize, y * gridCellSize, gridCellSize, gridCellSize);
					g.setColor(Color.DARK_GRAY);
					g.drawRect(x * gridCellSize, y * gridCellSize, gridCellSize, gridCellSize);
					g.drawString(x + "," + y, x * gridCellSize + 2, y * gridCellSize + 12);
					String note = terrainNode.getNote();
					if(note != null)
					{
						g.drawString(note, x*gridCellSize + 2, y*gridCellSize + gridCellSize - 2);
					}
					
				}
			}
			// draw the starting cell location
			g.setColor(Color.MAGENTA);
			g.fillOval(this.startingCell.x * gridCellSize + gridCellSize/2-5, this.startingCell.y * gridCellSize + gridCellSize/2-5, 10, 10);
			
			// draw the ending cell location
			if(this.endingCell != null)
			{
				g.setColor(Color.RED);
				g.fillOval(this.endingCell.x * gridCellSize + gridCellSize/2-5, this.endingCell.y * gridCellSize + gridCellSize/2-5, 10, 10);
			}
			
			// draw the path
			if(this.path != null)
			{
				g.setColor(Color.RED);
				for(int i=0; i<this.path.length-1; i++)
				{
					TerrainNode tn = (TerrainNode)this.path[i];
					TerrainNode tn2 = (TerrainNode)this.path[i+1];
					g.drawLine(tn.getLocation().x * gridCellSize + gridCellSize/2, 
							tn.getLocation().y * gridCellSize + gridCellSize/2, 
							tn2.getLocation().x * gridCellSize + gridCellSize/2, 
							tn2.getLocation().y * gridCellSize + gridCellSize/2);
				}
			}
		}
	}
}
