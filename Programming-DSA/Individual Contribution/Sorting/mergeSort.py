def merge(arr,start,mid,end):
    '''
    function to merge two sorted arrays
    taking array, start,mid and end of the array
    arr[start:mid+1] and arr[mid+1:end] are sorted 
    '''
    # create an array s from arr 
    s = arr[:]
    # initialize i for s[start:mid+1], j for s[mid+1:end]
    i,j,k = start,mid+1,start

    # iterate while one of the pointer reaches its end 
    while i<=mid and j<=end:
        #insert the smaller element in arr and increment the pointer
        if s[i]<=s[j]:
            arr[k] = s[i]
            i+=1
        else:
            arr[k] = s[j]
            j+=1
        k+=1

    # insert remaining elements if any 
    while i<=mid:
        arr[k] = s[i]
        i+=1
        k+=1

    while j<=end:
        arr[k] = s[j]
        j+=1
        k+=1

    
def mergeSort(arr,start,end):
    '''
    Take array as argument
    start and end index of the array
    '''
    # execute only when array has more then one element  
    if start<end:
        # take the middle index of the element 
        mid = start+(end-start)//2
        # divide the array into two halves 
        # sort the left and right half individually using mergeSort 
        mergeSort(arr,start,mid)
        mergeSort(arr,mid+1,end)
        # merge the two halves into one sorted array 
        merge(arr,start,mid,end)


# Driver code start 
if __name__ == "__main__":
    arr = [4,1,5,3,7,1,7,5]
    n = len(arr)
    print('Original array:',arr)
    mergeSort(arr,0,n-1)
    print('Sorted array  :',arr)
# Driver code end