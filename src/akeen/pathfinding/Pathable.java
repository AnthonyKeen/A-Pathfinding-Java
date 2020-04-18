package akeen.pathfinding;

public interface Pathable {
	public boolean isTraversible();
	public float getPathCost(Pathable nodeA);
	public Pathable[] getNeighbors();
}
