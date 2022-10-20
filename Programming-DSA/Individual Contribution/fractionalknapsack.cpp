#include <iostream>
#include <bits/stdc++.h>
using namespace std;
typedef struct
{
    int value;
    int weight;
    float density;
} Item;
void input(Item items[], int sizeOfItems)
{
    cout << "Enter total " << sizeOfItems << " item's values and weight" << endl;
    for (int i = 0; i < sizeOfItems; i++)
    {
        cout << "Enter " << i + 1 << " Value ";
        cin >> items[i].value;
        cout << "Enter " << i + 1 << " Weight ";
        cin >> items[i].weight;
    }
}
void display(Item items[], int sizeOfItems)
{
    cout << "values: ";
    for (int i = 0; i < sizeOfItems; i++)
    {
        cout << items[i].value << "\t";
    }
    cout << endl
         << "weight: ";
    for (int i = 0; i < sizeOfItems; i++)
    {
        cout << items[i].weight << "\t";
    }
    cout << endl;
}
bool compare(Item i1, Item i2)
{
    return (i1.density > i2.density);
}
float knapsack(Item items[], int sizeOfItems, int W)
{
    float totalValue = 0, totalWeight = 0;
    for (int i = 0; i < sizeOfItems; i++)
    {

        items[i].density = items[i].value / items[i].weight;
    }
    sort(items, items + sizeOfItems, compare);
    for (int i = 0; i < sizeOfItems; i++)
    {
        if (totalWeight + items[i].weight <= W)
        {
            totalValue += items[i].value;
            totalWeight += items[i].weight;
        }
        else
        {
            int wt = W - totalWeight;
            totalValue += (wt * items[i].density);
            totalWeight += wt;
            break;
        }
    }
    cout << "total weight in bag " << totalWeight << endl;
    return totalValue;
}
int main()
{
    int W;
    Item items[3];
    input(items, 3);
    cout << "Entered data \n";
    display(items, 3);
    cout << "Enter Knapsack weight \n";
    cin >> W;
    float mxVal = knapsack(items, 3, W);
    cout << "---Max value for " << W << " weight is " << mxVal;
    return 0;
}