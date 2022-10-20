#include <stdio.h>
#include <stdlib.h>
int permutation(int arr[], int n, int k)
{
    int l, temp;
    l = n - 1;
    while (arr[l] <= arr[k - 1]) // finding largest number in suffix
        l--;
    temp = arr[k - 1]; // swapping the largest number with the starting of suffix
    arr[k - 1] = arr[l];
    arr[l] = temp;
    l = n - 1;
    while (k < l) // arranging the suffix in ascending order
    {
        temp = arr[k];
        arr[k] = arr[l];
        arr[l] = temp;
        k++;
        l--;
    }
    for (int j = 0; j < n; j++) // printing the possiblity
        printf("%d ", arr[j]);
    printf("\n");
    k = n; // assigning k again to check that possibility exists
    while (k > 0 && arr[k - 1] >= arr[k])
        k--;
    if (k > 0)
    {
        permutation(arr, n, k); // if possibility exists function is called
        k = n;
    }
    if (k <= 0) // if it doesn't exit
        return 0;
    return 0;
}
int main()
{
    int *arr, number, n, k, l, flag = 1, count = 0, temp;
    printf("Enter the number of digits: ");
    scanf("%d", &n); // total number of digits
    printf("Enter the number: ");
    scanf("%d", &number);                // the number for which permutations has to be calculated
    arr = (int *)calloc(n, sizeof(int)); // allocate memory
    while (number > 0)                   // separating digits
    {
        arr[count] = number % 10;
        number /= 10;
        count++;
    }
    for (int i = 0; i < n; i++) // sorting in increasing order
    {
        for (int j = i + 1; j < n; j++)
        {
            if (arr[i] > arr[j])
            {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    }
    k = n;                      // variable k assisgned the value of n which is total numbers in array
    for (int j = 0; j < n; j++) // printing the first possibility
        printf("%d ", arr[j]);
    printf("\n");
    while (k > 0 && arr[k - 1] >= arr[k]) // traversing k backwards till we find the number smaller than k
        k--;
    if (k)                      // if k exists
        permutation(arr, n, k); // function call
    free(arr);                  // free arr
    return 0;
}