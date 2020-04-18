package akeen.TerrainGrid;

import java.awt.Color;
import java.awt.Point;

public class TerrainGrid {

	int width;
	int height;
	
	public static TerrainNode[] templateNodes;
	public TerrainNode[][] terrain;
	public TerrainNode startNode;
	
	/**
	 * This sets up the default templateNodes. This will be called automatically
	 * by the constructor if the templates have not already been created.
	 */
	public static void buildDefaultTemplateNodes()
	{
		templateNodes = new TerrainNode[4];
		//String terrainTypeName, Color terrainColor, float walkCostMultiplier
		templateNodes[0] = new TerrainNode("Grass", Color.GREEN, 1f);
		templateNodes[1] = new TerrainNode("Water", Color.BLUE, 0f); 
		templateNodes[1].setTraversible(false);
		templateNodes[2] = new TerrainNode("Mud", new Color(0.7f, 0.6f, 0.5f), 2f);
		templateNodes[3] = new TerrainNode("Desert", Color.ORANGE, 1f);
	}
	
	/**
	 * This is used to set the templateNodes BEFORE creating an instance of this class
	 * @param templateNodes
	 */
	public static void setTemplateNodes(TerrainNode[] templateNodes)
	{
		TerrainGrid.templateNodes = templateNodes;
	}
	
	/**
	 * Builds a Terrain Grid from the templateNodes at random.
	 * @param width
	 * @param height
	 */
	public TerrainGrid(int width, int height)
	{
		if(templateNodes == null)
		{
			TerrainGrid.buildDefaultTemplateNodes();
		}
		
		this.width = width;
		this.height = height;
		
		// Create a 2-D grid of TerrainNode objects and initializing them all to the default node
		terrain = new TerrainNode[width][height];
		for(int y=0; y<height; y++)
		{
			for(int x=0; x<width; x++)
			{
				terrain[x][y] = new TerrainNode(templateNodes[(int)(Math.random()*4)]);
				terrain[x][y].setLocation(new Point(x, y));
				this.buildNeighborList(terrain[x][y].location);
			}
		}
	}
	
	/**
	 * Build up the Neighbor List at this point - check for valid neighbors to the left, up-left, up, and up-right
	 * @param p the point indicating which node to look at
	 */
	private void buildNeighborList(Point p)
	{
		int x = p.x;
		int y = p.y;
		
		if(y == 0)
		{
			if(x > 0)
			{
				// connect left
				terrain[x][y].addNeighbor(terrain[x-1][y]);
				terrain[x-1][y].addNeighbor(terrain[x][y]);
			}
		}
		else
		{
			// connect up
			terrain[x][y].addNeighbor(terrain[x][y-1]);
			terrain[x][y-1].addNeighbor(terrain[x][y]);
			if(x > 0)
			{
				// connect left
				terrain[x][y].addNeighbor(terrain[x-1][y]);
				terrain[x-1][y].addNeighbor(terrain[x][y]);
				// connect upLeft
				terrain[x][y].addNeighbor(terrain[x-1][y-1]);
				terrain[x-1][y-1].addNeighbor(terrain[x][y]);
			}
			
			if(x < width - 1)
			{
				// connect upRight
				terrain[x][y].addNeighbor(terrain[x+1][y-1]);
				terrain[x+1][y-1].addNeighbor(terrain[x][y]);
			}
		}
	}
}
