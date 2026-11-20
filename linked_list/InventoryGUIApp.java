package linked_list;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
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
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class InventoryGUIApp extends JFrame {

    private InventoryLinkedList inventory = new InventoryLinkedList();

    private DefaultTableModel tableModel;
    private JTable table;

    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtQty;
    private JTextField txtPrice;

    public InventoryGUIApp() {

        // WINDOW SETTINGS
        setTitle("Linked List Inventory System");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // MAIN PANEL
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

        // LEFT SIDE: PRODUCT DETAILS
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 8, 12));

        panelForm.setBorder(
            BorderFactory.createTitledBorder("Product Details")
        );

        // Product ID
        panelForm.add(new JLabel("Product ID:"));
        txtId = new JTextField();
        panelForm.add(txtId);

        // Product Name
        panelForm.add(new JLabel("Product Name:"));
        txtName = new JTextField();
        panelForm.add(txtName);

        // Quantity
        panelForm.add(new JLabel("Quantity:"));
        txtQty = new JTextField();
        panelForm.add(txtQty);

        // Price
        panelForm.add(new JLabel("Price ($):"));
        txtPrice = new JTextField();
        panelForm.add(txtPrice);

        // FORM CONTAINER
        JPanel leftPanel = new JPanel(new BorderLayout(5, 15));
        leftPanel.setPreferredSize(new Dimension(300, 0));
        leftPanel.add(panelForm, BorderLayout.NORTH);

        // LEFT SIDE: BUTTONS
        JPanel panelButtons = new JPanel(
            new GridLayout(3, 1, 5, 10)
        );

        JButton btnAdd = new JButton("Add to Inventory");
        JButton btnDelete = new JButton("Delete Selected");
        JButton btnClear = new JButton("Clear Fields");

        btnAdd.setPreferredSize(new Dimension(250, 35));
        btnDelete.setPreferredSize(new Dimension(250, 35));
        btnClear.setPreferredSize(new Dimension(250, 35));

        panelButtons.add(btnAdd);
        panelButtons.add(btnDelete);
        panelButtons.add(btnClear);

        JPanel buttonContainer = new JPanel(new BorderLayout());
        buttonContainer.setBorder(
            BorderFactory.createEmptyBorder(10, 5, 0, 5)
        );

        buttonContainer.add(panelButtons, BorderLayout.NORTH);
        leftPanel.add(buttonContainer, BorderLayout.CENTER);

        // RIGHT SIDE: INVENTORY TABLE
        tableModel = new DefaultTableModel(
            new String[] {
                "Product ID",
                "Product Name",
                "Quantity",
                "Price ($)"
            }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(28);
        table.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );
        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(
            BorderFactory.createTitledBorder("Inventory List")
        );

        // ADD PANELS TO MAIN PANEL
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        // BUTTON ACTIONS
		// ADD PRODUCT
        btnAdd.addActionListener(e -> Add());

        // DELETE SELECTED PRODUCT
        btnDelete.addActionListener(e -> Delete());

        // CLEAR FIELDS
        btnClear.addActionListener(e -> clearFields());

    }


	private void Add() {
		try {

                String id = txtId.getText().trim();
                String name = txtName.getText().trim();
                String quantity = txtQty.getText().trim();
                String priceText = txtPrice.getText().trim();

                // Check empty fields
                if (id.isEmpty() || name.isEmpty()
                        || quantity.isEmpty() || priceText.isEmpty()) {

                    JOptionPane.showMessageDialog(
                        this,
                        "Please fill in all fields!"
                    );

                    return;
                }

                // Convert values
                int qty = Integer.parseInt(quantity);
                double price = Double.parseDouble(priceText);

                // Validate values
                if (qty < 0 || price < 0) {

                    JOptionPane.showMessageDialog(
                        this,
                        "Quantity and Price cannot be negative!"
                    );

                    return;
                }

                // Create product
                Product product = new Product(id, name, qty, price);

                // Add to linked list
                inventory.add(product);

                // Refresh table
                inventory.populateTable(tableModel);

                clearFields();

                JOptionPane.showMessageDialog(
                    this,
                    "Product added successfully!"
                );

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numbers for Quantity and Price."
                );
            }
	}


	private void Delete() {
		int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please select a product from the table."
                );

                return;
            }

            int modelRow = table.convertRowIndexToModel(selectedRow);

            String id = tableModel.getValueAt(modelRow, 0).toString();

            boolean removed = inventory.delete(id);

            if (removed) {

                inventory.populateTable(tableModel);

                clearFields();

                JOptionPane.showMessageDialog(
                    this,
                    "Product deleted successfully!"
                );

            } else {

                JOptionPane.showMessageDialog(
                    this,
                    "Product ID not found."
                );
            }
	}


	private void clearFields() {

        txtId.setText("");
        txtName.setText("");
        txtQty.setText("");
        txtPrice.setText("");

        txtId.requestFocus();
    }


    // MAIN METHOD
        public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new InventoryGUIApp().setVisible(true);

        });
    }
}