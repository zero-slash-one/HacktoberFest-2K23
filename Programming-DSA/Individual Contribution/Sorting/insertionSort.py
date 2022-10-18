def insertionSort(arr: list, n: int) -> None:
    '''
    Taking array and its size as arguments
    '''
    # iterate from second to last element 
    for i in range(1,n):
        j = i
        # first ith elements are already sorted 
        # place arr[j] in correct position in the sorted array  
        while j>0 and arr[j]<arr[j-1]:
            arr[j],arr[j-1] = arr[j-1],arr[j]
            j-=1

# Driver code start 
if __name__ == "__main__":
    arr = [4,1,5,3,7,1,7,5]
    n = len(arr)
    print('Original array:',arr)
    insertionSort(arr,n)
    print('Sorted array  :',arr)
# Driver code end