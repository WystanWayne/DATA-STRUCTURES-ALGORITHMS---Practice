package laboratory;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class InventoryGUIApp extends JFrame {
	private InventoryLinkedList inventory = new InventoryLinkedList();
	private DefaultTableModel tableModel;
	private JTextField txtId, txtName, txtQty, txtPrice;

	public InventoryGUIApp() {
		setTitle("Linked List Inventory System");
		setSize(700, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
// Input Form Panel
		JPanel panelForm = new JPanel(new GridLayout(5, 2, 5, 5));
		panelForm.setBorder(BorderFactory.createTitledBorder("Product Details"));
		panelForm.add(new JLabel(" Product ID:"));
		txtId = new JTextField();
		panelForm.add(txtId);
		panelForm.add(new JLabel(" Product Name:"));
		txtName = new JTextField();
		panelForm.add(txtName);
		panelForm.add(new JLabel(" Quantity:"));
		txtQty = new JTextField();
		panelForm.add(txtQty);
		panelForm.add(new JLabel(" Price ($):"));
		txtPrice = new JTextField();
		panelForm.add(txtPrice);
		JButton btnAdd = new JButton("Add to Inventory");
		JButton btnDelete = new JButton("Delete Selected ID");
		panelForm.add(btnAdd);
		panelForm.add(btnDelete);
		add(panelForm, BorderLayout.NORTH);
// Table View Setup
		tableModel = new DefaultTableModel(new String[] { "ID", "Name", "Quantity", "Price" }, 0);
		JTable table = new JTable(tableModel);
		add(new JScrollPane(table), BorderLayout.CENTER);
// Button Actions
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String id = txtId.getText().trim();
					String name = txtName.getText().trim();
					int qty = Integer.parseInt(txtQty.getText().trim());
					double price = Double.parseDouble(txtPrice.getText().trim());
					if (id.isEmpty() || name.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Fields cannot be empty!");
						return;
					}
					Product p = new Product(id, name, qty, price);
					inventory.add(p);
					inventory.populateTable(tableModel);
					clearFields();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Please enter valid numbers for Quantity and Price.");
				}
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = txtId.getText().trim();
				if (id.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Enter the Product ID in the ID field to delete.");
					return;
				}
				boolean removed = inventory.delete(id);
				if (removed) {
					inventory.populateTable(tableModel);
					clearFields();
					JOptionPane.showMessageDialog(null, "Product deleted successfully.");
				} else {
					JOptionPane.showMessageDialog(null, "Product ID not found.");
				}
			}
		});
	}

	private void clearFields() {
		txtId.setText("");
		txtName.setText("");
		txtQty.setText("");
		txtPrice.setText("");
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new InventoryGUIApp().setVisible(true);
			}
		});
	}
}
