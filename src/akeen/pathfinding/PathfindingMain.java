package akeen.pathfinding;

import akeen.TerrainGrid.TerrainGrid;
import akeen.pathfinding.graphics.GridFrame;

/**
 * This class is only used for testing the Logical TerrainGrid with the Graphical GridFrame
 * using A* Pathfinding. 
 * 
 * @author Anthony Keen
 *
 */
public class PathfindingMain {

	public static void main(String[] args) {
		// Creating a new GridFrame object with our 2-D grid of TerrainNode objects
		new GridFrame("A* Pathfinding", new TerrainGrid(14, 10));
	}

}
