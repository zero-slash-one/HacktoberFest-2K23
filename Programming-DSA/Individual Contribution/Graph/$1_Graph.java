import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class $1_Graph {
	private int no_of_nodes;
	private int[][] adjacency_matrix;

	$1_Graph() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter no. of nodes::");
		no_of_nodes = scanner.nextInt();

		adjacency_matrix = new int[no_of_nodes + 1][no_of_nodes + 1];

		System.out.println("Enter Adjacency Matrix::");
		for (int i = 1; i <= no_of_nodes; i++) {
			for (int j = 1; j <= no_of_nodes; j++) {
				adjacency_matrix[i][j] = scanner.nextInt();
			}
		}
		scanner.close();
	}

	$1_Graph(int[][] arr) {
		no_of_nodes = arr.length;
		adjacency_matrix = new int[arr.length + 1][arr[0].length + 1];
		for (int i = 1; i <= arr.length; i++) {
			for (int j = 1; j <= arr[i - 1].length; j++) {
				adjacency_matrix[i][j] = arr[i - 1][j - 1];
			}
		}
	}

	void breadthFirstSearch(int startingVertex) {
		Queue<Integer> queue = new LinkedList<>();
		int[] visitedNodes = new int[no_of_nodes + 1];

		System.out.print("Starting Vertex::" + startingVertex + "->\t");

		// Adding starting vertex in queue and mark it as visited
		queue.add(startingVertex);
		visitedNodes[startingVertex] = 1;

		while (!queue.isEmpty()) {
			int i = queue.remove();
			System.out.print(i + " ");

			for (int j = 1; j < adjacency_matrix[i].length; j++) { // j < matrix.columnLength

				// Adding adjacent vertex to queue,which are not visited till now
				if (adjacency_matrix[i][j] == 1 && visitedNodes[j] == 0) {
					queue.add(j);
					visitedNodes[j] = 1;
				}
			}
		}
		System.out.println();
	}

	void depthFirstSearch(int startingVertex) {
		Stack<Integer> stack = new Stack<>();
		int visitedNodes[] = new int[no_of_nodes + 1];

		System.out.print("Starting Vertex::" + startingVertex + "->\t");
		depthFirstSearchRecursive(startingVertex, stack, visitedNodes);
		System.out.println();
	}

	private void depthFirstSearchRecursive(int startingVertex, Stack<Integer> stack, int[] visitedNodes) {
		System.out.print(startingVertex + " ");
		visitedNodes[startingVertex] = 1;

		for (int j = 1; j <= no_of_nodes; j++) {
			if (adjacency_matrix[startingVertex][j] == 1 && visitedNodes[j] == 0) {
				depthFirstSearchRecursive(j, stack, visitedNodes);
			}
		}
	}
}