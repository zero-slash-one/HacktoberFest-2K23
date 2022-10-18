class Deque:
    def __init__(self, size):
        self.arr = [-1]*size
        self.size = size
        self.front = -1
        self.rear = -1

    # Insert 'val' in the front of the deque. Returns true if the operation is successfull else returns false.
    def pushFront(self, val):
        # Write your code here
        if self.isFull():
            return False
        if self.front==-1:
            self.front = 0
            self.rear = 0
        else:
            self.front = (self.front-1)%self.size
        self.arr[self.front] = val
        return True

    # Insert 'val' in the back of the deque. Returns true if the operation is successfull else returns false.
    def pushRear(self, val):
        # Write your code here
        if self.isFull():
            return False
        if self.front==-1:
            self.front=0
            self.rear=0
        else:
            self.rear = (self.rear+1)%self.size
        self.arr[self.rear] = val
        return True

    # Delete and returns the element from the front in deque, return None if stack is empty
    def popFront(self):
        # Write your code here
        if self.isEmpty():
            return None
        val = self.arr[self.front]
        if self.front==self.rear:
            self.front=-1
            self.rear=-1
        else:
            self.front = (self.front+1)%self.size
        return val

    # Delete and returns the element from the back in deque, return None if stack is empty
    def popRear(self):
        # Write your code here
        if self.isEmpty():
            return None
        val = self.arr[self.rear]
        if self.front==self.rear:
            self.front = -1
            self.rear = -1
        else:
            self.rear = (self.rear-1)%self.size
        return val

    # Returns the element from the front, return None if deque is empty
    def getFront(self):
        # Write your code here
        if self.isEmpty():
            return None
        return self.arr[self.front]

    # Returns the element from the back, return None if deque is empty
    def getRear(self):
        # Write your code here
        if self.isEmpty():
            return None
        return self.arr[self.rear]

    # Returns true if the deque is empty else returns false
    def isEmpty(self):
        # Write your code here
        return self.front==-1 

    # Returns true if the deque is full else returns false
    def isFull(self):
        # Write your code here
        return (self.front==0 and self.rear==self.size-1) or (self.rear == (self.front-1)%self.size)

    # Print the deque
    def display(self):
        if self.isEmpty():
            print('Deque is empty')
            return 

        start = self.front
        end = self.rear

        print('[',end="")
        while start!=end:
            print(self.arr[start],end=", ")
            start = (start+1)%self.size
        print(self.arr[start],end=']\n')
    


# Driver code start 
if __name__ == "__main__":
    dq = Deque(10)

    dq.display()

    dq.pushFront(1)
    dq.pushFront(2)
    dq.pushFront(3)
    dq.display()
    dq.pushRear(4)
    dq.pushRear(5)
    dq.display()

    poppedElement = dq.popFront()
    print('Popped element from front:',poppedElement)
    poppedElement = dq.popRear()
    print('Popped element from back:',poppedElement)
    dq.display()

    frontElement = dq.getFront()
    backElement = dq.getRear()
    print('Front element:',frontElement)
    print('Back element:',backElement)

# Driver code end