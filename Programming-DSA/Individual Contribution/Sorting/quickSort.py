def partition(arr,start,end):
    '''
    take array, start and end index of the array as argument 
    '''
    # choosing first element as pivot 
    # all the elements greater the first element will be on the right side
    # all the elements smaller the first element will be on the left side
    pivot = start
    
    # iterate while start<=end 
    while start<=end:
        # increment the start pointer untill it reaches element greater than pivot element
        while start<=end and arr[start]<=arr[pivot]: start+=1
        # decrement the end pointer untill it reaches element smaller than pivot element
        while start<=end and arr[end]>arr[pivot]: end-=1
        # if start < end swap start and end position of array 
        if start<end:
            arr[start],arr[end] = arr[end],arr[start]
    
    # swap pivot element with the end position     
    arr[pivot],arr[end] = arr[end],arr[pivot]
    
    # end is the right position of the pivot element in the sorted array
    return end

def quickSort(arr,start,end):
    '''
    Take array as argument
    start and end index of the array
    '''
    # execute only when array has more then one element  
    if start<end:
        # all the elements greater the arr[index] are on the right side
        # all the elements smaller the arr[index] are on the left side
        index = partition(arr,start,end)
        # sort the left and right part of the array individually using quicksort
        quickSort(arr,start,index-1)
        quickSort(arr,index+1,end)


# Driver code start 
if __name__ == "__main__":
    arr = [4,1,5,3,7,1,7,5]
    n = len(arr)
    print('Original array:',arr)
    quickSort(arr,0,n-1)
    print('Sorted array  :',arr)
# Driver code end