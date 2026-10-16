package laboratory;

import javax.swing.table.DefaultTableModel;

class InventoryLinkedList {
	private Node head;

	public void add(Product product) {
		Node newNode = new Node(product);
		if (head == null) {
			head = newNode;
		} else {
			Node current = head;
			while (current.next != null) {
				current = current.next;
			}
			current.next = newNode;
		}
	}

	public boolean delete(String id) {
		if (head == null)
			return false;
		if (head.data.id.equals(id)) {
			head = head.next;
			return true;
		}
		Node current = head;
		while (current.next != null) {
			if (current.next.data.id.equals(id)) {
				current.next = current.next.next;
				return true;
			}
			current = current.next;
		}
		return false;
	}

// Populate a JTable model by traversing the linked list
	public void populateTable(DefaultTableModel model) {
		model.setRowCount(0); // Clear existing rows
		Node current = head;
		while (current != null) {
			model.addRow(
					new Object[] { current.data.id, current.data.name, current.data.quantity, current.data.price });
			current = current.next;
		}
	}
}
