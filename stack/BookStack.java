package stack;

import java.util.EmptyStackException;

public class BookStack {
    private Book[] stackArray;
    private int top;
    private int capacity;

    // Initialize stack with a specific size
    public BookStack(int size) {
        this.capacity = size;
        this.stackArray = new Book[capacity];
        this.top = -1; // Stack is initially empty
    }

    // Push: Add a book to the top of the stack
    public void push(Book book) {
        if (isFull()) {
            expandCapacity(); // Dynamically grow array if full
        }
        stackArray[++top] = book;
    }

    // Pop: Remove and return the top book from the stack
    public Book pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        Book poppedBook = stackArray[top];
        stackArray[top--] = null; // Clean up reference for garbage collection
        return poppedBook;
    }

    // Peek: Look at the top book without removing it
    public Book peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stackArray[top];
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Check if the stack is full
    public boolean isFull() {
        return top == capacity - 1;
    }

    // Get current number of books in the stack
    public int size() {
        return top + 1;
    }

    // Dynamic resizing helper
    private void expandCapacity() {
        capacity *= 2;
        Book[] newArray = new Book[capacity];
        System.arraycopy(stackArray, 0, newArray, 0, stackArray.length);
        stackArray = newArray;
    }

    // Display all books in the stack from top to bottom
    public void displayStack() {
        if (isEmpty()) {
            System.out.println("No books in this history tracker.");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(" -> " + stackArray[i]);
        }
    }
}

