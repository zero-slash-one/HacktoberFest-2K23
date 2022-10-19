public class $6_HeightofBST_DriverClass {
    public static void main(String[] args) {
        int arr[]={24,19,30,10,22,26,37,25};
        $1_BinarySearchTree binarySearchTree=new $1_BinarySearchTree(arr);
        System.out.println("Height of Tree::"+binarySearchTree.heightOfTree(binarySearchTree.root));
    }  
}
