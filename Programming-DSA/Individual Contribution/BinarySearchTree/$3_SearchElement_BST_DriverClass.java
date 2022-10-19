public class $3_SearchElement_BST_DriverClass {
    public static void main(String[] args) {
        int arr[]={24,19,30,10,22,26,37,25};
        $1_BinarySearchTree binarySearchTree=new $1_BinarySearchTree(arr);
        binarySearchTree.searchKey(10);
        binarySearchTree.searchKey(25);
        binarySearchTree.searchKey(36);

    }
    
}
