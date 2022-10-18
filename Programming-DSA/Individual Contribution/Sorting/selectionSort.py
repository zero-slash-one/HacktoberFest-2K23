def selectionSort(arr,n):
    '''
    Taking array and its size as arguments
    '''
    # iterate through every element of the array 
    for i in range(n):
        # initialise index of minimum element as ith index
        minIndex = i
        # first i elements are already sorted 
        for j in range(i+1,n): 
            if arr[j]<arr[minIndex]:
                minIndex = j
            # store the index of minimum element and swap with ith element 
        arr[i],arr[minIndex] = arr[minIndex],arr[i]
           
# Driver code start 
if __name__ == "__main__":
    arr = [4,1,5,3,7,1,7,5]
    n = len(arr)
    print('Original array:',arr)
    selectionSort(arr,n)
    print('Sorted array  :',arr)
# Driver code end