public class $5_DeleteNode_BST_DriverClass {
    public static void main(String[] args) {
        int arr[]={24,19,30,10,22,26,37,25};
        $1_BinarySearchTree binarySearchTree=new $1_BinarySearchTree(arr);
        binarySearchTree.inOrderTraversal();

        binarySearchTree.deleteNode(22);
        binarySearchTree.inOrderTraversal();

        binarySearchTree.deleteNode(37);
        binarySearchTree.inOrderTraversal();
    }
}
