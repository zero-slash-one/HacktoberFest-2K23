public class $4_InsertNode_BST_DriverClass {
    public static void main(String[] args) {
        int arr[]={24,19,30,10,22,26,37,25};
        $1_BinarySearchTree binarySearchTree=new $1_BinarySearchTree(arr);

        binarySearchTree.inOrderTraversal();

        binarySearchTree.insertIterative(76);
        binarySearchTree.insertIterative(22);
        binarySearchTree.insertIterative(12);

        binarySearchTree.inOrderTraversal();
    }
}
