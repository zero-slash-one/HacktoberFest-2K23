class Solution {
  public:
    Node* createMapping(Node* root, unordered_map<Node*,Node*> &nodeToParent, int target){
        Node* res = NULL;
        queue<Node*> q;
        q.push(root);
        nodeToParent[root] = NULL;
        while(!q.empty()){
            Node* fr = q.front();
            q.pop();
            
            if(fr->data == target)
                res = fr;
            
            if(fr->left != NULL){
                nodeToParent[fr->left] = fr;
                q.push(fr->left);
            }
            if(fr->right != NULL){
                nodeToParent[fr->right] = fr;
                q.push(fr->right);
            }
        }
        return res;
    }
    
    int burnTree(Node* targetNode, unordered_map<Node*, Node*> nodeToParent){
        unordered_map<Node*, bool> visited;
        visited[targetNode] = true;
        queue<Node*> q;
        q.push(targetNode);
        int ans = 0;
        while(!q.empty()){
            bool addition = false;
            int s = q.size();
            for(int i=0;i<s;i++){
                Node* frontNode = q.front();
                q.pop();
                
                visited[frontNode] = true;
                if(frontNode->left && !visited[frontNode->left]){
                    addition = true;
                    q.push(frontNode->left);
                    visited[frontNode->left] = true;
                }
                if(frontNode->right && !visited[frontNode->right]){
                    addition = true;
                    q.push(frontNode->right);
                    visited[frontNode->right] = true;
                }
                if(nodeToParent[frontNode] && !visited[nodeToParent[frontNode]]){
                    addition = true;
                    q.push(nodeToParent[frontNode]);
                    visited[nodeToParent[frontNode]] = true;
                }
            }
            if(addition == true)
                ans++;
        }
        return ans;
    }
    
    int minTime(Node* root, int target) 
    {
        // Your code goes here
        int ans=0;
        unordered_map<Node*,Node*> nodeToParent;
        Node* targetNode = createMapping(root,nodeToParent,target);
        ans = burnTree(targetNode,nodeToParent);
        return ans;
    }
};
