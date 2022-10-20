#include <stdio.h>
#include <time.h>
#include <stdlib.h>
int main()
{
    int n, index, count, max = 0, arr[10000], cf[1000], arr_sor[1000];
    clock_t t;
    scanf("%d", &n);
    for (int t = 0; t < n; t++) // input generate
    {
        arr[t] = rand() % 1000;
        printf("%d ", arr[t]);
    }
    t = clock();
    for (int y = 0; y < n; y++) // max
    {
        if (arr[y] > max)
            max = arr[y];
    }
    for (int g = 0; g <= max; g++) // frequency
    {
        count = 0;
        for (int h = 0; h < n; h++)
        {
            if (arr[h] == g)
                count++;
        }
        cf[g] = count;
    }
    for (int p = 1; p <= max; p++)// cf
        cf[p] += cf[p - 1];
    for (int i = n - 1; i >= 0; i--)
    {
        for (int j = 0; j <= max; j++)
        {
            if (arr[i] == j)
            {
                index = cf[j];
                arr_sor[index] = arr[i];
                cf[j]--;
            }
        }
    }
    t = clock() - t;
    printf("\n");
    for (int x = 1; x <= n; x++) // output
        printf("%d ", arr_sor[x]);
    printf("\nNo. of clicks %ld clicks (%f seconds).\n", t, ((float)t) / CLOCKS_PER_SEC);    
    return 0;
}