package queue;

public class ArrayQueue {
    private int maxSize;
    private int[] queueArray;
    private int front;
    private int rear;
    private int nItems;

    // Initialize queue with a specific size
    public ArrayQueue(int size) {
        maxSize = size;
        queueArray = new int[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    // Insert an item at the rear
    public void enqueue(int value) {
        if (isFull()) {
            System.out.println("Queue is full! Cannot insert " + value);
            return;
        }
        if (rear == maxSize - 1) {
            rear = -1; // Deal with wraparound
        }
        queueArray[++rear] = value;
        nItems++;
        System.out.println(value + " added to the queue.");
    }

    // Remove an item from the front
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty! Nothing to remove.");
            return -1;
        }
        int temp = queueArray[front++];
        if (front == maxSize) {
            front = 0; // Deal with wraparound
        }
        nItems--;
        System.out.println(temp + " removed from the queue.");
        return temp;
    }

    // View the item at the front
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return -1;
        }
        return queueArray[front];
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return (nItems == 0);
    }

    // Check if queue is full
    public boolean isFull() {
        return (nItems == maxSize);
    }

    // Display all items in the queue
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return;
        }
        System.out.print("Queue elements (Front to Rear): ");
        for (int i = 0; i < nItems; i++) {
            int index = (front + i) % maxSize;
            System.out.print(queueArray[index] + " ");
        }
        System.out.println();
    }
}
