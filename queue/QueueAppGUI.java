package queue;

class QueueAppGUI {
    private int maxSize;
    private int[] queueArray;
    private int front;
    private int rear;
    private int nItems;

    // Constructor
    public QueueAppGUI(int size) {
        maxSize = size;
        queueArray = new int[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    // Enqueue: add element
    public boolean enqueue(int value) {
        if (isFull()) return false;
        if (rear == maxSize - 1) rear = -1; // Wraparound
        queueArray[++rear] = value;
        nItems++;
        return true;
    }

    // Dequeue: remove element
    public int dequeue() {
        if (isEmpty()) return -1;
        int temp = queueArray[front++];
        if (front == maxSize) front = 0; // Wraparound
        nItems--;
        return temp;
    }

    // Peek: view front element
    public int peek() {
        if (isEmpty()) return -1;
        return queueArray[front];
    }

    // Check if empty
    public boolean isEmpty() {
        return (nItems == 0);
    }

    // Check if full
    public boolean isFull() {
        return (nItems == maxSize);
    }

    // Display queue as string
    public String displayString() {
        if (isEmpty()) return "Queue is empty!";
        StringBuilder sb = new StringBuilder("Front -> [ ");
        for (int i = 0; i < nItems; i++) {
            int index = (front + i) % maxSize;
            sb.append(queueArray[index]).append(" ");
        }
        sb.append("] <- Rear");
        return sb.toString();
    }
}
