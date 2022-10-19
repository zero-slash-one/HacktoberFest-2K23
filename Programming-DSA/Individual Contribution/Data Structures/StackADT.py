class Stack():
    # initializing stack 
    def __init__(self) -> None:
        self.stack = []
        self.top = -1

    # print the elements of the stack
    def display(self):
        print(self.stack)

    # push the element on the top of the stack
    def push(self,element):
        self.stack.append(element)
        self.top+=1
    
    # return True if stack is empty else returns false
    def isEmpty(self):
        return self.top==-1

    # delete and return the top element of the stack, return None if stack is empty
    def pop(self):
        if self.isEmpty():
            print('Stack is empty')
            return None
        else:
            element = self.stack[self.top]
            del self.stack[self.top]
            self.top-=1
            return element

    # return the top element of the stack, return None if stack is empty
    def peek(self):
        if self.isEmpty():
            print('Stack is empty')
            return None
        return self.stack[self.top]


# Driver code start 
if __name__== '__main__':
    s = Stack() 
    print(s.peek())
    
    s.push(9)
    s.push(8)
    s.push(7)
    s.push(9)

    s.display()

    print(s.pop())
    s.display()
    print(s.peek())
# Driver code end 

