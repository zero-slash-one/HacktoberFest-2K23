#include <stdio.h>
#include <stdlib.h>
#include <time.h>
void quick(int arr[], int first, int last)
{
    int i, j, pivot, temp;

    if (first < last)
    {
        pivot = first;
        i = first;
        j = last;
        while (i < j)
        {
            while (arr[i] <= arr[pivot] && i < last)
                i++;
            while (arr[j] > arr[pivot])
                j--;
            if (i < j)
            {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        temp = arr[pivot];
        arr[pivot] = arr[j];
        arr[j] = temp;
        quick(arr, first, j - 1);
        quick(arr, j + 1, last);
    }
}
int linearSearch(int a[], int num, int val)
{
    for (int i = 0; i < num; i++)
    {
        if (a[i] == val)
            return i + 1;
    }
    return -1;
}
int binarySearch(int a[], int beg, int end, int val)
{
    int mid;
    if (end >= beg)
    {
        mid = (beg + end) / 2;

        if (a[mid] == val)
            return mid + 1;
        else if (a[mid] < val)
            return binarySearch(a, mid + 1, end, val);
        else
            return binarySearch(a, beg, mid - 1, val);
    }
    return -1;
}
void binary(int a[], int num, int val)
{
    int bin_val;
    clock_t t2;
    printf("\n\n##Binary Search##\n");
    quick(a, 0, num - 1); 
    t2 = clock();
    bin_val = binarySearch(a, 0, num - 1, val);
    t2 = clock() - t2;
    // printf("The elements of the sorted array are - ");
    // for (int i = 0; i < num; i++)
    //    printf("%d ", a[i]);
    if (bin_val == -1)
        printf("\nElement is not present in the array\n");
    else
        printf("\nElement is present at %d position of array\n", bin_val);
    printf("Time taken to search %d :-\n", val);
    printf("No. of clicks %ld clicks (%f seconds).\n", t2, ((float)t2) / CLOCKS_PER_SEC);
}
int main()
{
    int *a, num, val, lin_val;
    a = malloc(sizeof(int) * num);
    printf("Total Numbers to be generated:");
    scanf("%d", &num);
    clock_t t1;
    for (int i = 0; i < num; i++)
        a[i] = rand();
    printf("Enter the value to be searched ");
    scanf("%d", &val);
    // printf("The elements of the array are - \n");
    // for (int i = 0; i < num; i++)
    //     printf("%d ", a[i]);
    printf("\n##Linear Search##\n");
    t1 = clock();
    lin_val = linearSearch(a, num, val);
    t1 = clock() - t1;
    if (lin_val == -1)
        printf("\nElement is not present in the array\n");
    else
        printf("\nElement is present at %d position of array\n", lin_val);
    printf("Time taken to search %d :-\n", val);
    printf("No. of clicks %ld clicks (%f seconds).\n", t1, ((float)t1) / CLOCKS_PER_SEC);
    binary(a, num, val);
    free(a);
    return 0;
}