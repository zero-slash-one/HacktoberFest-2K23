#include <stdio.h>
#include <stdlib.h>
#include <time.h>
void insertion(int arr[], int n)
{
	int i, key, j;
	for (i = 1; i < n; i++)
	{
		key = arr[i];
		j = i - 1;
		while (j >= 0 && arr[j] > key)
		{
			arr[j + 1] = arr[j];
			j--;
		}
		arr[j + 1] = key;
	}
}
void bubble(int a[], int n)
{
	int i, j, temp;
	for (i = 0; i < n; i++)
	{
		for (j = i + 1; j < n; j++)
		{
			if (a[j] < a[i])
			{
				temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}
		}
	}
}
void selection(int arr[], int n)
{
	int i, j, position, swap;
	for (i = 0; i < (n - 1); i++)
	{
		position = i;
		for (j = i + 1; j < n; j++)
		{
			if (arr[position] > arr[j])
				position = j;
		}
		if (position != i)
		{
			swap = arr[i];
			arr[i] = arr[position];
			arr[position] = swap;
		}
	}
}
void merge(int i, int j, int arr[], int tmp[])
{
	if (j <= i)
		return;
	int m = (i + j) / 2;
	merge(i, m, arr, tmp);
	merge(m + 1, j, arr, tmp);
	int pg = i;
	int pd = m + 1;
	int c;
	for (c = i; c <= j; c++)
	{
		if (pg == m + 1)
		{
			tmp[c] = arr[pd];
			pd++;
		}
		else if (pd == j + 1)
		{
			tmp[c] = arr[pg];
			pg++;
		}
		else if (arr[pg] < arr[pd])
		{
			tmp[c] = arr[pg];
			pg++;
		}
		else
		{
			tmp[c] = arr[pd];
			pd++;
		}
	}
	for (c = i; c <= j; c++)
		arr[c] = tmp[c];
}
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
void heap(int arr[], int n)
{
	int a, b, temp;
	for (int i = n / 2; i >= 0; i--)
	{
		a = (2 * i) + 1;
		b = a + 1;
		if ((arr[i] < arr[a]) || (arr[i] < arr[b]))
		{
			if (arr[a] > arr[b])
			{
				temp = arr[i];
				arr[i] = arr[a];
				arr[a] = temp;
			}
			else
			{
				temp = arr[i];
				arr[i] = arr[b];
				arr[b] = temp;
			}
		}
	}
	for (int i = (n - 1) / 2; i >= 0; i--)
	{
		a = (2 * i) + 1;
		b = a + 1;
		if ((arr[i] < arr[a]) || (arr[i] < arr[b]))
			heap(arr, n);
	}
}
int main()
{
	int *arr1, *arr2, *arr3, *arr4, *arr5, *arr6, *temp, n;
	printf("Total Numbers to be generated: ");
	arr1 = malloc(sizeof(int) * n); 
	arr2 = malloc(sizeof(int) * n); 
	arr3 = malloc(sizeof(int) * n); 
	arr4 = malloc(sizeof(int) * n); 
	arr5 = malloc(sizeof(int) * n); 
	arr6 = malloc(sizeof(int) * n); 
	temp = malloc(sizeof(int) * n); 
	scanf("%d", &n);
	clock_t t1, t2, t3, t4, t5, t6;
	for (int i = 0; i < n; i++)
	{
		arr1[i] = rand();
		arr2[i] = arr1[i];
		arr3[i] = arr1[i];
		arr4[i] = arr1[i];
		arr5[i] = arr1[i];
		arr6[i] = arr1[i];
	}
	// for(int i=0;i<n;i++)
	//	printf("%d ", arr1[i]);
	t1 = clock();
	insertion(arr1, n);
	t1 = clock() - t1;

	t2 = clock();
	bubble(arr2, n);
	t2 = clock() - t2;

	t3 = clock();
	selection(arr3, n);
	t3 = clock() - t3;

	t4 = clock();
	merge(0, n - 1, arr4, temp);
	t4 = clock() - t4;

	t5 = clock();
	quick(arr5, 0, n - 1);
	t5 = clock() - t5;

	t6 = clock();
	heap(arr6, n);
	t6 = clock() - t6;

	// for(int i=0;i<n;i++)
	//	printf("%d ", arr6[i]);

	printf("\nTime taken for Insertion sort: -\n");
	printf("No. of clicks %ld clicks (%f seconds).\n", t1, ((float)t1) / CLOCKS_PER_SEC);

	printf("\nTime taken for Bubble sort: -\n");
	printf("No. of clicks %ld clicks (%f seconds).\n", t2, ((float)t2) / CLOCKS_PER_SEC);

	printf("\nTime taken for Selection sort: -\n");
	printf("No. of clicks %ld clicks (%f seconds).\n", t3, ((float)t3) / CLOCKS_PER_SEC);

	printf("\nTime taken for Merge sort: -\n");
	printf("No. of clicks %ld clicks (%f seconds).\n", t4, ((float)t4) / CLOCKS_PER_SEC);

	printf("\nTime taken for Quick sort: -\n");
	printf("No. of clicks %ld clicks (%f seconds).\n", t5, ((float)t5) / CLOCKS_PER_SEC);

	printf("\nTime taken for Heap sort: -\n");
	printf("No. of clicks %ld clicks (%f seconds).\n", t6, ((float)t6) / CLOCKS_PER_SEC);
	return 0;
}
