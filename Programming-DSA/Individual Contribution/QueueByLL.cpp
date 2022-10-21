#include<bits/stdc++.h>
using namespace std;
struct Node{
    int data;
    struct Node* next;
} *front=NULL, *rear=NULL;

void enQueue(int x){
    struct Node *t= new Node;
    if(t==NULL){cout << "Queue is Full" << endl;}
    else{
        t->data=x;
        t->next=NULL;
        if(front== NULL && rear==NULL){
        front=rear=t;}
        else{ 
            rear->next=t;
            rear=t;
        }
    }
}
int deQueue(){
    int x=-1;
    struct Node *p;
    if(front==NULL){
        cout << "Queue is Empty" << endl;
    }
    else{
        p=front;
        front=front->next;
        x=p->data;
        free(p);
    }
    return x;
}
void Display(){
   struct Node *p;
   p=front;
   while(p){
    cout << p->data << " " ;
    p=p->next;
   }
   cout << endl;
}

int main(){
    enQueue(5);
    enQueue(6);
    enQueue(51);
    enQueue(7);
    enQueue(10);
    Display();
    deQueue();
    deQueue();
    Display();
    enQueue(66);
    Display();
    // Frontelement();
    // Rearelement();
}
