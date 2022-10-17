#include <bits/stdc++.h>

class Solution {
public:
    // NOTE: IN THIS QUESTION, RECURSION IS BEING MORE TIME EFFICIENT BUT LESS SPACE EFFICIENT WHILE ITREATIVE APPROACH IS MORE SPACE EFFICIENT BUT LESS SPACE EFFICIENT ;
    
    // REASON : MAYBE RECURSION STACK SPACE:
    
    
    
    // Used recursion, but since we can't see any overlapping subproblem : dp is not possible
    // hence it is better to use iterative approach
    
    
    // RECURSION
//     int sol(int f, int s, int &maxi, vector<int> &h) 
//     {
//         if(f == s) return maxi;
//         // int temp = 0;
        
//         int temp = (s - f) * min(h[f], h[s]);
//         if(temp > maxi) maxi = temp;
        
//         if(h[f] <= h[s]) sol( f+1, s, maxi,h);
//         if(h[s] < h[f]) sol( f, s - 1, maxi, h);
            
//         return maxi;
        
//         // return max(sol(f + 1, s, maxi, h) , sol(f, s - 1, maxi, h));
        
//     }
    
    

    int maxArea(vector<int> &h) {
        // return sol(0, height.size() - 1, maxi, height);
        
        int maxi = 0;
        int temp = 0; 
        
        for(int i = 0, j = h.size() - 1; i < j ; )
        {
            maxi = max(maxi, (j - i) * min(h[j], h[i]));
            h[i] > h[j] ? j-- : i++;
            
        } 
        return maxi;       
    }
};