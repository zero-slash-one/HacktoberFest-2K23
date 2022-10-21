#include<bits/stdc++.h>
using namespace std;

struct Node {
    int data;
    struct Node *next;
}*top=NULL;

void Ipush(int x){
    struct Node *t=new Node;
    if(t==NULL){
        cout << "Stack is Full" << endl;
    }
    else{
        t->data=x;
        t->next=top;
        top=t;
    }
}

int Ipop(){
    int x=-1;
    struct Node *p;
    if(top==NULL){
        cout << "Stack is Empty" << endl;
    }
    else{
        p=top;
        x=p->data;
        top=top->next;
        delete(p);
    }
    return x;
}

void Display(){
    struct Node *p;
    p=top;
    while(p!=NULL){
        cout <<  p->data << " ";
        p=p->next;
    }
    cout << endl; 
}

int main(){
    Ipush(30);
    Ipush(11);
    Ipush(2002);
    Ipush(16);
    Ipush(13);

    Display();
    
    cout << Ipop();

}