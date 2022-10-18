def bubbleSort(arr:list, n:int)->None:
    '''
    Taking array and its size as arguments
    '''
    # iterate through every element of the array 
    for i in range(n):
        isSorted = True

        # Last i elements are already sorted
        for j in range(n-1-i):
            # if element is greater than next element, swap the elements 
            if arr[j]>arr[j+1]:
                arr[j],arr[j+1] = arr[j+1],arr[j]
                # if swapping takes place then array is not sorted 
                isSorted = False

        # if no swapping takes place then array is sorted 
        if isSorted:
            break
           
# Driver code start 
if __name__ == "__main__":
    arr = [4,1,5,3,7,1,7,5]
    n = len(arr)
    print('Original array:',arr)
    bubbleSort(arr,n)
    print('Sorted array  :',arr)
# Driver code end