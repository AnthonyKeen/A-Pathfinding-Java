package akeen.TerrainGrid;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import akeen.pathfinding.Pathable;

public class TerrainNode implements Pathable {
	
	public static final int DIAGONAL_COST = 14;
	public static final int HORIZONTAL_COST = 10;
	
	
	Color terrainColor; // this probably shouldn't be here
	float walkCost;
	boolean isTraversible = true;
	String terrainTypeName; // we should use this to look up the terrainColor
	String note = null;
	Point location;
	ArrayList<TerrainNode> neighborList = new ArrayList<TerrainNode>();
	
	public TerrainNode()
	{
		terrainTypeName = "Grass";
		terrainColor = Color.GREEN;
		walkCost = 1f;
	}
	
	public TerrainNode(String terrainTypeName, Color terrainColor, float walkCostMultiplier)
	{
		this.terrainTypeName = terrainTypeName;
		this.terrainColor = terrainColor;
		this.walkCost = walkCostMultiplier;
	}
	
	public TerrainNode(TerrainNode node)
	{
		this.terrainTypeName = node.terrainTypeName;
		this.terrainColor = node.terrainColor;
		this.walkCost = node.walkCost;
		this.isTraversible = node.isTraversible;
	}
	
	public void setLocation(Point location)
	{
		this.location = location;
	}
	
	public Point getLocation()
	{
		return this.location;
	}
	
	public Color getColor()
	{
		return this.terrainColor;
	}
	
	public String getNote()
	{
		return this.note;
	}
	
	public String getTypeName()
	{
		return this.terrainTypeName;
	}
	
	public void setTraversible(boolean value)
	{
		this.isTraversible = value;
	}

	@Override
	public boolean isTraversible() {
		return this.isTraversible;
	}

	@Override
	public float getPathCost(Pathable nodeA) 
	{		
		int deltaX = Math.abs(((TerrainNode)(nodeA)).location.x - this.location.x);
		int deltaY = Math.abs(((TerrainNode)(nodeA)).location.y - this.location.y);
		int horizontalCount = Math.abs(deltaX - deltaY);
		int diagonalCount = Math.min(deltaX,  deltaY);
		
		return horizontalCount * HORIZONTAL_COST + diagonalCount * DIAGONAL_COST;
	
	}

	public void addNeighbor(TerrainNode node)
	{
		this.neighborList.add(node);
	}
	
	@Override
	public Pathable[] getNeighbors() {
		Pathable[] pathableList = new Pathable[this.neighborList.size()];
		return this.neighborList.toArray(pathableList);
	}
	
	@Override
	public String toString()
	{
		String str = new String(this.location.toString());
		return str;
	}
}
