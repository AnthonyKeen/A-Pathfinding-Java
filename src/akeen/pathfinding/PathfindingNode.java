package akeen.pathfinding;

public class PathfindingNode {
	
	Pathable node;
	PathfindingNode parentNode;
	
	float f; // sum of g and h
	float g; // how far from the start node
	float h; // how far from the end node
	
	PathfindingNode(Pathable node)
	{
		this.node = node;
	}
	
	void calculuateF()
	{
		f = g + h;
	}
	
	@Override
	public String toString()
	{
		return node.toString();
	}

}
