import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class Cube {
	private char[] faces;
	private final static String[] pMoves = {"F", "R", "U", "B", "L", "D", "F'", "R'", "U'", "B'", "L'", "D'" };

	public Cube(char[] cs) {
		if (validInput(cs))
			this.faces = cs;
		else
		{
			System.out.println("your input is incorrect");
			System.out.println("you now have to restart the program");
		}
	}

	public char[] getFaces() {
		return faces;
	}

	/**
	 * checks if the value sent as an input is valid or not
	 * @param cs
	 * @return true if input is valid, else false
	 */
	private boolean validInput(char[] cs) {
		int countW = 0, countR = 0, countG = 0, countY = 0, countO = 0, countB = 0;
		if (cs.length != 24)
			return false;
		for (int i = 0; i < cs.length; i++) {
			switch (cs[i]) {
			case 'W':
				countW++;
				break;
			case 'R':
				countR++;
				break;
			case 'G':
				countG++;
				break;
			case 'Y':
				countY++;
				break;
			case 'O':
				countO++;
				break;
			case 'B':
				countB++;
				break;
			}
		}
		if (countW != 4 || countR != 4 || countG != 4 || countY != 4 || countO != 4 || countB != 4)
			return false;
		else
			return true;
	}

	/**
	 * checks if the state received as an input is a goal state or not
	 * @param faces: representation of the cube
	 * @return true if the state received as an input is considered one of the possible goal states else false
	 */
	private boolean isGoal(char[] faces)

	{

		if (faces[0] == faces[1] && faces[1] == faces[2] && faces[2] == faces[3] && faces[8] == faces[9]
				&& faces[9] == faces[10] && faces[10] == faces[11] && faces[4] == faces[5] && faces[5] == faces[6]
				&& faces[6] == faces[7] && faces[16] == faces[17] && faces[17] == faces[18]
				&& faces[18] == faces[19] && faces[20] == faces[21] && faces[21] == faces[22]
				&& faces[22] == faces[23] && faces[12] == faces[13] && faces[13] == faces[14] && faces[14] == faces[15])
		{
			return true;
		}	
		else
			return false;

	}

	/**
	 * the function applies each of the moves in pMoves and applies them seperatly to the current state
	 * @param state: representation of the cube state
	 * @return a 12*24 matrix of the 12 possible configurations of children states of the current state
	 */
	private char[][] successor(char[] state) {
		char[][] successors = new char[12][24];
		for (int i = 0; i < pMoves.length; i++) {
			char[] newState = applyMove(state, pMoves[i]);
			for (int j = 0; j < newState.length; j++) {
				successors[i][j] = newState[j];
			}
		}
		return successors;
	}

	/**
	 * function applies move param2 on state param1, uses helper function rotate
	 * @param1 state: representation of the cube state 
	 * @param2 move: char represnting type of rotation to be applied
	 * @return
	 */
	private static char[] applyMove(char[] state, String move) {
		char[] temp = new char[24];
		switch (move) {
		case "F": {
			temp = rotate(state, 2, 3, 4, 6, 13, 12, 19, 17, 8, 9, 10, 11);
			break;
		}
		case "R": {
			temp = rotate(state, 3, 1, 20, 22, 15, 13, 11, 9, 4, 5, 6, 7);
			break;
		}
		case "U": {
			temp = rotate(state, 21, 20, 5, 4, 9, 8, 17, 16, 0, 1, 2, 3);
			break;
		}
		case "B": {
			temp = rotate(state, 1, 0, 16, 18, 14, 15, 7, 5, 20, 21, 22, 23);
			break;
		}
		case "L": {
			temp = rotate(state, 0, 2, 8, 10, 12, 14, 23, 21, 16, 17, 18, 19);
			break;
		}
		case "D": {
			temp = rotate(state, 10, 11, 6, 7, 22, 23, 18, 19, 12, 13, 14, 15);
			break;
		}
		case "F'": {
			temp = rotate(state, 3, 2, 17, 19, 12, 13, 6, 4, 9, 8, 11, 10);
			break;
		}
		case "R'": {
			temp = rotate(state, 1, 3, 9, 11, 13, 15, 22, 20, 5, 4, 7, 6);
			break;
		}
		case "U'": {
			temp = rotate(state, 20, 21, 16, 17, 8, 9, 4, 5, 1, 0, 3, 2);
			break;
		}
		case "B'": {
			temp = rotate(state, 0, 1, 5, 7, 15, 14, 18, 16, 21, 20, 23, 22);
			break;
		}
		case "L'": {
			temp = rotate(state, 2, 0, 21, 23, 14, 12, 10, 8, 17, 16, 19, 18);
			break;
		}
		case "D'": {
			temp = rotate(state, 11, 10, 19, 18, 23, 22, 7, 6, 13, 12, 15, 14);
			break;
		}
		}
		return temp;
	}

	/** helper method to applyMove function to implement rotations, user helper method swap
	 * 
	 * @param state
	 * @param i1-i12: indexes of the 12 squares on the cube cube that change per rotation
	
	 * @return a version of the cube where the full rotation has been implemented.
	 */
	private static char[] rotate(char[] state, int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10,
			int i11, int i12) {
		char[] temp = state.clone();
		temp = swap(temp, i7, i1);
		temp = swap(temp, i8, i2);
		temp = swap(temp, i6, i8);
		temp = swap(temp, i5, i7);
		temp = swap(temp, i4, i6);
		temp = swap(temp, i3, i5);
		temp = swap(temp, i9, i10);
		temp = swap(temp, i11, i9);
		temp = swap(temp, i11, i12);
		return temp;
	}

	/**
	 * helper method to rotate method, swaps between 2 values of indexes as part of a rotation
	 * @param faces: state if the cube
	 * @param i1: first index to be swapped
	 * @param i2: second index to be swapped
	 * @return an updated version of the cube with the swap between to indexes implemented
	 */
	private static char[] swap(char[] faces, int i1, int i2) {
		char temp;
		char[] temp2 = faces.clone();
		temp = faces[i1];
		temp2[i1] = temp2[i2];
		temp2[i2] = temp;
		return temp2;
	}
	
	
	public Node iterativeDeepeningSearch(char[] faces ) {
		int maxDepth= 1;
	    for (int depth = 0; depth <= maxDepth; depth++) {
	        Node result = depthLimitedSearch(faces, depth);
	        if (result != null) {
	            return result;
	        }
	        maxDepth++;
	    }
	    return null; // Goal state not found within the depth limit
	}

	private Node depthLimitedSearch(char[] faces, int depthLimit) {
	    Stack<Node> stack = new Stack<>();
	    
	    Node startNode = new Node(faces, null, 0,0,0);
	    stack.push(startNode);
	    
	    while (!stack.isEmpty()) {
	        Node currentNode = stack.pop();
	        char[] currentState = currentNode.getState();

	        if (isGoal(currentState)) {
	            return currentNode;
	        }
	        
	        if (currentNode.getDepth() < depthLimit && !stack.contains(currentNode)) {
	            // Generate successor states
	            for (char[] successor : this.successor(currentState)) {
	                int cost = currentNode.getCost() + 1;
	                Node successorNode = new Node(successor, currentNode, cost, currentNode.getDepth() + 1,0);
	                stack.push(successorNode);
	            }
	        }
	    }
	    return null; // Goal state not found within the depth limit
	}


	// order of operations according to
	// The order listed from left to right - F first and 'D last.
	public Node ucs(char[] faces) {
		PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
		HashSet<char[]> visited = new HashSet<>();

		Node startNode = new Node(faces, null, 0);
		priorityQueue.offer(startNode);

		// run the search
		while (!priorityQueue.isEmpty()) {
			Node currentNode = priorityQueue.poll();

			char[] currentState = currentNode.getState();

			// Check if the current state is the goal state
			if (isGoal(currentState)) {
				// Reconstruct the path from the goal to the start
				return currentNode;
			}

			// Check if the state has been visited
			if (!visited.contains(currentState)) {
				visited.add(currentState);

				// Generate successor states
				for (char[] successor : this.successor(currentState)) {
					int cost = currentNode.getCost() + 1; // Assuming all costs are equal
					Node successorNode = new Node(successor, currentNode, cost);
					priorityQueue.offer(successorNode);
				}
			}
		}

		// Goal state not found
		return null;
	}

	public Node aStar(char[] faces) {
		PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(Node::getTotalCost));
		HashSet<char[]> visited = new HashSet<>();

		Node startNode = new Node(faces, null, 0,0, calculateH(faces));
		priorityQueue.offer(startNode);

		// run the search
		while (!priorityQueue.isEmpty()) {
			Node currentNode = priorityQueue.poll();

			char[] currentState = currentNode.getState();

			// Check if the current state is the goal state
			if (isGoal(currentState)) {
				// Reconstruct the path from the goal to the start
				return currentNode;
			}

			// Check if the state has been visited
			if (!visited.contains(currentState)) {
				visited.add(currentState);

				// Generate successor states
				for (char[] successor : this.successor(currentState)) {
					int cost = currentNode.getCost() + 1; // Assuming all movement costs are equal
					Node successorNode = new Node(successor, currentNode, cost,0, calculateH(successor));
					priorityQueue.offer(successorNode);
				}
			}
		}
		// Goal state not found
		return null;
	}
	
	
	// Helper method to reconstruct the path from the goal to the start
		public static List<String> reconstructPath(Node goalNode) {
			List<String> path = new ArrayList<>();
			Node current = goalNode;

			while (current != null) {
				if(current.parent!=null)
				{
					path.add("Do move: " + findMove(current.parent.getState(), current.getState())+ " to get to here: " 
							+ String.valueOf(current.getState()));
				}
				else
				{
					path.add("your input was: " + String.valueOf(current.getState())+ " the steps to solve are below:");	
				}
				current = current.parent;
			}

			// Reverse the path to get it from start to goal
			Collections.reverse(path);

			// Return the final path
			return path.isEmpty() ? null : path;
		}
		
		
		
		public static String findMove(char[] parent, char[] child)
		{
			boolean check=true;
			for(int i=0; i<pMoves.length; i++)
			{
			char[] temp = applyMove(parent, pMoves[i]);
				for(int j=0;j<=23;j++)
				{
					if(temp[j]!=child[j])
					{
						check = false;
					}
				}
				if(check)
					return pMoves[i];
				check=true;
			}
			return null;
		}

		
		/** 
		 * calculates heurestic valuation of the state sent to it as a paramater 
		 * based on the following equation: 
		 * (number of faces with more than one color /6) * ((sum of: (# of different colors on each face -1))/4)
		 *  
		 * @param faces
		 * @return double valuation of the state sent as an input
		 */	
		public static double calculateH(char[] faces)
		{
			int countFaces = 0;
			double result=0;
			int countGlobalColor = 0;

			for (int i = 0; i <= 23; i += 4) {
				int countLocalColor = 0;
				ArrayList<Character> colors = new ArrayList<Character>();
				for (int j = 0; j <= 3; j++) {
					if (!colors.contains(faces[i + j])) {
						countLocalColor++;
						colors.add(faces[i + j]);
					}
				}
				colors.clear();
				countGlobalColor += (countLocalColor - 1);
				if (countLocalColor ==2) {
					countFaces+=1;
				}
				if (countLocalColor ==3) {
					countFaces+=2;
				}
				if (countLocalColor ==4) {
					countFaces+=3;
				}
			}
			result = (Double.parseDouble(String.valueOf(countFaces)) / 6) * (Double.parseDouble(String.valueOf(countGlobalColor)) / 4);
			
			return result;
		}
		
		
		
		
	
	
	
	

	// Node class to represent states along with their parent and cost
	public static class Node {
		private char[] state;
		private Node parent;
		private int cost;
		private double costH;
		private int depth;

		public Node(char[] state, Node parent, int cost) {
			this.state = state;
			this.parent = parent;
			this.cost = cost;
			costH = 0;
		}
		
		public Node(char[] state, Node parent, int cost, int depth, int costH) {
			this.state = state;
			this.parent = parent;
			this.cost = cost;
			this.depth=depth;
			this.costH = Double.parseDouble(String.valueOf(costH));
		}
		public Node(char[] state, Node parent, int cost, int depth, double costH) {
			this.state = state;
			this.parent = parent;
			this.cost = cost;
			this.depth=depth;
			this.costH = costH;
		}
		
		public void setCostH(int costH)
		{
			this.costH=costH;
		}

	
		public int getDepth()
		{
			return depth;
		}

		public double getTotalCost() {
			return cost + costH;
		}

		public char[] getState() {
			return state;
		}

		public int getCost() {
			return cost;
		}
	}	
}
