package akeen.pathfinding;

import java.util.ArrayList;

public class Pathfinder {

	
	/**
	 * Algorithm can be found at:
	 * https://medium.com/@nicholas.w.swift/easy-a-star-pathfinding-7e6689c7f7b2
	 */
	public static Pathable[] findPath(Pathable startNode, Pathable endNode)
	{
		Pathfinder pathfinder = new Pathfinder(startNode, endNode);
		return pathfinder.pathList;
	}
	
	PathfindingNode startNode;
	PathfindingNode endNode;
	ArrayList<PathfindingNode> openList;
	ArrayList<PathfindingNode> closedList;
	Pathable[] pathList;
	
	private Pathfinder(Pathable pathableStartNode, Pathable pathableEndNode)
	{
		this.startNode = new PathfindingNode(pathableStartNode);
		this.endNode = new PathfindingNode(pathableEndNode);
		
		// Initialize both open and closed list
		openList = new ArrayList<PathfindingNode>();
		closedList = new ArrayList<PathfindingNode>();
		
		// Add the start node
		openList.add(startNode);
		
		// Loop until you find the end
		while(!openList.isEmpty())
		{
			// Get the current node
			PathfindingNode currentNode = findNodeFromOpenListWithMinF();
			openList.remove(currentNode);
			closedList.add(currentNode);
			
			// Found the goal
		    if(currentNode.node == endNode.node)
		    {
		    	backtrackToGetPath(currentNode);
		    	break;
			}
		    else
		    {
		    	// Generate children
		    	Pathable[] pathableArray = currentNode.node.getNeighbors();
		    	
		    	// for each child in the children
		    	for(Pathable pathableChild: pathableArray)
		    	{
		    		// if the node is already closed, skip it
		    		if(this.findPathfindingNodeInList(closedList, pathableChild) != null)
		            {
		            	continue;
		            }
		    		// if the node is not traversible, close it
		    		else if(!pathableChild.isTraversible())
		    		{
		    			closedList.add(new PathfindingNode(pathableChild));
		    		}
		    		else
		    		{
		    			// find the node in the open list
			    		PathfindingNode pathableChildNode = this.findPathfindingNodeInList(openList, pathableChild);
			            if(pathableChildNode != null)
			            {
			            	// we found the node
			            	float newG = currentNode.g + pathableChildNode.node.getPathCost(currentNode.node);
			            	
			            	if(newG < pathableChildNode.g)
			            	{
			            		pathableChildNode.parentNode = currentNode;
			            		pathableChildNode.g = newG;
			            		pathableChildNode.calculuateF();
			            	}
			            }
			            else
			            {
			            	// we didn't find the node, we'll make one
			            	pathableChildNode = new PathfindingNode(pathableChild);
			            	pathableChildNode.parentNode = currentNode;
			            	// Create the f, g, and h values
			            	pathableChildNode.g = currentNode.g + pathableChildNode.node.getPathCost(currentNode.node);
			            	pathableChildNode.h = pathableChildNode.node.getPathCost(this.endNode.node);
			            	pathableChildNode.calculuateF();
				            
			            	// Add the child to the openList
			            	openList.add(pathableChildNode);
			            }
			    	}
		    	}
		    }
		}
	}
	
	/**
	 * Go through each element in the list and check each pathfindingnode's node-component
	 * for a match to Pathable
	 * 
	 * @param list the ArrayList within which we are looking
	 * @param p the Pathable object that we are looking for
	 * @return null if not found, otherwise the first node that matches
	 */
	PathfindingNode findPathfindingNodeInList(ArrayList<PathfindingNode> list, Pathable p)
	{
		for(PathfindingNode pn: list)
		{
			if(pn.node.equals(p))
			{
				return pn;
			}
		}
		return null;
	}
	
	/**
	 * Iterate backwards through the list starting at the endNode and using
	 * the parent element within the PathfindingNode to trace back to the startNode.
	 * 
	 * @param endNode an Array of Pathable objects representing a path
	 */
	private void backtrackToGetPath(PathfindingNode endNode)
	{
		ArrayList<Pathable> pathableList = new ArrayList<Pathable>();
		PathfindingNode currentNode = endNode;
		while(currentNode != startNode)
		{
			pathableList.add(currentNode.node);
			currentNode = currentNode.parentNode;
		}
		pathableList.add(currentNode.node);
		this.pathList = new Pathable[pathableList.size()];
		this.pathList = pathableList.toArray(this.pathList);
	}
	
	/**
	 * Find the minimum F value with our OpenList
	 * 
	 * @return null if empty, or the PathfindingNode with the minimum F value
	 */
	private PathfindingNode findNodeFromOpenListWithMinF()
	{
		PathfindingNode returnNode = null;
		for(PathfindingNode node: this.openList)
		{
			if(returnNode == null)
			{
				returnNode = node;
			}
			else if(node.f < returnNode.f) 
			{
				returnNode = node;
			}
		}
		return returnNode;
	}
}
