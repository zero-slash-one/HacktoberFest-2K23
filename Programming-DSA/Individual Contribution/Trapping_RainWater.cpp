#include<bits/stdc++.h>

using namespace std;


 // } Driver Code Ends
class Solution{

    // Function to find the trapped water between the blocks.
    public:
    long long trappingWater(int arr[], int n){
        long long res=0;
        int leftmax[n],rightmax[n];
        leftmax[0]=arr[0];
        rightmax[n-1]=arr[n-1];
        for(int i=1,j=n-2;i<n,j>=0;i++,j--){
            leftmax[i]=max(arr[i],leftmax[i-1]);
            rightmax[j]=max(arr[j],rightmax[j+1]);
        }
        for(int i=0;i<n;i++){
            if(min(leftmax[i],rightmax[i])-arr[i]>0){
                res += min(leftmax[i],rightmax[i])-arr[i];
            }
        }
        return res;
    }
};

// { Driver Code Starts.

int main(){
    
    int t;
    //testcases
    cin >> t;
    
    while(t--){
        int n;
        
        //size of array
        cin >> n;
        
        int a[n];
        
        //adding elements to the array
        for(int i =0;i<n;i++){
            cin >> a[i];            
        }
        Solution obj;
        //calling trappingWater() function
        cout << obj.trappingWater(a, n) << endl;
        
    }
    
    return 0;
}  // } Driver Code Ends
