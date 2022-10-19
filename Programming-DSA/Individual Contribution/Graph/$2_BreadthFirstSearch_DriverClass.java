public class $2_BreadthFirstSearch_DriverClass {
    public static void main(String[] args) {
        int[][] arr={
            {0,1,1,0,1,0,0},
            {1,0,0,1,0,0,0},
            {1,0,0,1,0,0,0},
            {0,1,1,0,1,1,1},
            {1,0,0,1,0,0,0},
            {0,0,0,1,0,0,0},
            {0,0,0,1,0,0,0}
        };
        $1_Graph graph=new $1_Graph(arr);

        System.out.println("Breadth First Search: ");
        graph.breadthFirstSearch(1);
        graph.breadthFirstSearch(2);
        graph.breadthFirstSearch(3);
        graph.breadthFirstSearch(4);
        graph.breadthFirstSearch(5);
        graph.breadthFirstSearch(6);
        graph.breadthFirstSearch(7);
    }
}
