public class $3_DepthFirstSearch_DriverClass {
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

        System.out.println("Depth First Search: ");
        graph.depthFirstSearch(1);
        graph.depthFirstSearch(2);
        graph.depthFirstSearch(3);
        graph.depthFirstSearch(4);
        graph.depthFirstSearch(5);
        graph.depthFirstSearch(6);
        graph.depthFirstSearch(7);
    }
    
}
