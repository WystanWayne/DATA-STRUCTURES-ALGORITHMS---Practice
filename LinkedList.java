package laboratory;

class LinkedList {

    Node head;

    // Add at head
    public void addFirst(Product data) {

        Node newNode = new Node(data);

        newNode.next = head;

        head = newNode;
    }

    // Add at tail
    public void addLast(Product data) {

        Node newNode = new Node(data);

        if (head == null) {

            head = newNode;

            return;
        }

        Node current = head;

        while (current.next != null) {

            current = current.next;
        }

        current.next = newNode;
    }

    // Add at middle
    public void addMiddle(int index, Product data) {

        if (index <= 0) {

            addFirst(data);

            return;
        }

        Node newNode = new Node(data);

        Node current = head;

        for (int i = 0; i < index - 1 && current != null; i++) {

            current = current.next;
        }

        if (current == null) {

            addLast(data);

        } else {

            newNode.next = current.next;

            current.next = newNode;
        }
    }

    // Delete head
    public void deleteFirst() {

        if (head != null) {

            head = head.next;
        }
    }

    // Delete tail
    public void deleteLast() {

        if (head == null)
            return;

        if (head.next == null) {

            head = null;

            return;
        }

        Node current = head;

        while (current.next.next != null) {

            current = current.next;
        }

        current.next = null;
    }

    // Delete middle
    public void deleteMiddle(int index) {

        if (head == null)
            return;

        if (index == 0) {

            deleteFirst();

            return;
        }

        Node current = head;

        for (int i = 0; i < index - 1 && current != null; i++) {

            current = current.next;
        }

        if (current == null || current.next == null) {

            return;
        }

        current.next = current.next.next;
    }

    // Display list
    public String display() {

        if (head == null)

            return "List is empty";

        StringBuilder sb = new StringBuilder();

        Node current = head;

        while (current != null) {

            sb.append(current.data).append(" -> ");

            current = current.next;
        }

        sb.append("null");

        return sb.toString();
    }
}