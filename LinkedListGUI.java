
package laboratory;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class LinkedListGUI extends JFrame {

	private LinkedList list = new LinkedList();

	private JTextArea displayArea;

	private JTextField idField;
	private JTextField nameField;
	private JTextField quantityField;
	private JTextField priceField;
	private JTextField indexField;

	public LinkedListGUI() {

		setTitle("Linked List Product Manager");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(15, 15));

		JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

		// HEADER
		JLabel titleLabel = new JLabel("LINKED LIST PRODUCT MANAGER", SwingConstants.CENTER);

		titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
		titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));

		// LINKED LIST DISPLAY
		JPanel displayPanel = new JPanel(new BorderLayout(5, 5));
		displayPanel.setBorder(BorderFactory.createTitledBorder("Linked List Display"));

		displayArea = new JTextArea("List is empty");
		displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		displayArea.setEditable(false);
		displayArea.setLineWrap(true);
		displayArea.setWrapStyleWord(true);
		displayArea.setMargin(new Insets(10, 10, 10, 10));

		JScrollPane scrollPane = new JScrollPane(displayArea);
		scrollPane.setPreferredSize(new java.awt.Dimension(700, 120));

		displayPanel.add(scrollPane, BorderLayout.CENTER);

		JPanel topPanel = new JPanel(new BorderLayout(5, 10));
		topPanel.add(titleLabel, BorderLayout.NORTH);
		topPanel.add(displayPanel, BorderLayout.CENTER);

		mainPanel.add(topPanel, BorderLayout.NORTH);

		// PRODUCT INFORMATION
		JPanel inputPanel = new JPanel(new GridLayout(5, 2, 12, 12));
		inputPanel.setBorder(BorderFactory.createTitledBorder("Product Information"));

		idField = new JTextField();
		nameField = new JTextField();
		quantityField = new JTextField();
		priceField = new JTextField();
		indexField = new JTextField();

		addInput(inputPanel, "Product ID:", idField);
		addInput(inputPanel, "Product Name:", nameField);
		addInput(inputPanel, "Quantity:", quantityField);
		addInput(inputPanel, "Price:", priceField);
		addInput(inputPanel, "Index (starting at 0):", indexField);

		// ADD OPERATIONS
		JPanel addPanel = new JPanel(new GridLayout(1, 3, 10, 10));
		addPanel.setBorder(BorderFactory.createTitledBorder("Add Operations"));

		JButton addHeadBtn = new JButton("Add Head");
		JButton addMidBtn = new JButton("Add Middle");
		JButton addTailBtn = new JButton("Add Tail");

		addPanel.add(addHeadBtn);
		addPanel.add(addMidBtn);
		addPanel.add(addTailBtn);

		// DELETE OPERATIONS
		JPanel deletePanel = new JPanel(new GridLayout(1, 3, 10, 10));
		deletePanel.setBorder(BorderFactory.createTitledBorder("Delete Operations"));

		JButton delHeadBtn = new JButton("Delete Head");
		JButton delMidBtn = new JButton("Delete Middle");
		JButton delTailBtn = new JButton("Delete Tail");

		deletePanel.add(delHeadBtn);
		deletePanel.add(delMidBtn);
		deletePanel.add(delTailBtn);

		// ORGANIZE INPUTS AND BUTTONS
		JPanel operationsPanel = new JPanel(new BorderLayout(10, 15));
		operationsPanel.add(inputPanel, BorderLayout.NORTH);
		operationsPanel.add(addPanel, BorderLayout.CENTER);
		operationsPanel.add(deletePanel, BorderLayout.SOUTH);

		mainPanel.add(operationsPanel, BorderLayout.CENTER);

		add(mainPanel, BorderLayout.CENTER);

		// ADD HEAD
		addHeadBtn.addActionListener(e -> {
			try {
				Product product = getProduct();
				list.addFirst(product);
				updateDisplay();
			} catch (NumberFormatException ex) {
				showError("Enter a valid quantity and price.");
			} catch (IllegalArgumentException ex) {
				showError(ex.getMessage());
			}
		});

		// ADD MIDDLE
		addMidBtn.addActionListener(e -> {
			try {
				Product product = getProduct();
				int index = getIndex();

				list.addMiddle(index, product);
				updateDisplay();
			} catch (NumberFormatException ex) {
				showError("Enter valid product information and index.");
			} catch (IndexOutOfBoundsException ex) {
				showError("Index is outside the valid list range.");
			} catch (IllegalArgumentException ex) {
				showError(ex.getMessage());
			}
		});

		// ADD TAIL
		addTailBtn.addActionListener(e -> {
			try {
				Product product = getProduct();
				list.addLast(product);
				updateDisplay();
			} catch (NumberFormatException ex) {
				showError("Enter a valid quantity and price.");
			} catch (IllegalArgumentException ex) {
				showError(ex.getMessage());
			}
		});

		// DELETE HEAD
		delHeadBtn.addActionListener(e -> {
			if (confirmDelete("the first product")) {
				list.deleteFirst();
				updateDisplay();
			}
		});

		// DELETE MIDDLE
		delMidBtn.addActionListener(e -> {
			try {
				int index = getIndex();

				if (confirmDelete("the product at index " + index)) {
					list.deleteMiddle(index);
					updateDisplay();
				}
			} catch (NumberFormatException ex) {
				showError("Enter a valid index.");
			} catch (IndexOutOfBoundsException ex) {
				showError("Index is outside the valid list range.");
			} catch (IllegalArgumentException ex) {
				showError(ex.getMessage());
			}
		});

		// DELETE TAIL
		delTailBtn.addActionListener(e -> {
			if (confirmDelete("the last product")) {
				list.deleteLast();
				updateDisplay();
			}
		});
	}

	// Adds a label and input field to the form
	private void addInput(JPanel panel, String label, JTextField field) {

		panel.add(new JLabel(label));
		panel.add(field);
	}

	// Creates a Product using the input fields
	private Product getProduct() {

		String id = idField.getText().trim();
		String name = nameField.getText().trim();

		if (id.isEmpty() || name.isEmpty() || quantityField.getText().trim().isEmpty()
				|| priceField.getText().trim().isEmpty()) {

			throw new IllegalArgumentException("Please complete all product fields.");
		}

		int quantity = Integer.parseInt(quantityField.getText().trim());

		double price = Double.parseDouble(priceField.getText().trim());

		if (quantity < 0 || !Double.isFinite(price) || price < 0) {
			throw new IllegalArgumentException("Quantity and price must be non-negative.");
		}

		return new Product(id, name, quantity, price);
	}

	// Reads the index field
	private int getIndex() {

		String value = indexField.getText().trim();

		if (value.isEmpty()) {
			throw new NumberFormatException("Index is empty");
		}

		int index = Integer.parseInt(value);

		if (index < 0) {
			throw new IllegalArgumentException("Index cannot be negative.");
		}

		return index;
	}

	// Updates the displayed linked list
	private void updateDisplay() {

		String result = list.display();

		if (result == null || result.trim().isEmpty()) {
			displayArea.setText("List is empty");
		} else {
			displayArea.setText(result);
		}

		displayArea.setCaretPosition(0);

		idField.setText("");
		nameField.setText("");
		quantityField.setText("");
		priceField.setText("");
		indexField.setText("");
	}

	// Confirms deletion
	private boolean confirmDelete(String item) {

		int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete " + item + "?",
				"Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		return choice == JOptionPane.YES_OPTION;
	}

	// Displays an error message
	private void showError(String message) {

		JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			new LinkedListGUI().setVisible(true);
		});
	}
}
