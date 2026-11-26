package binary_tree;

// --- 3. MAIN WINDOW WINDOW FRAME ---
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class BinaryTreeGUI2 extends JFrame {
	private final BinarySearchTree tree;
	private final TreePanel view;
	private final JTextField inputField;

	public BinaryTreeGUI2() {
		tree = new BinarySearchTree();
		view = new TreePanel(tree);
		inputField = new JTextField(10);

		JButton insertBtn = new JButton("Insert");
		JButton deleteBtn = new JButton("Delete");

		// Control Panel configuration
		JPanel panel = new JPanel();
		panel.add(new JLabel("Enter Integer: "));
		panel.add(inputField);
		panel.add(insertBtn);
		panel.add(deleteBtn);

		// Layout Configuration
		setLayout(new BorderLayout());
		add(view, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);

		// Button Event Actions
		insertBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int value = Integer.parseInt(inputField.getText().trim());
					tree.insert(value);
					view.repaint(); // Refresh UI layout
					inputField.setText("");
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Please enter a valid numeric integer.");
				}
			}
		});

		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int value = Integer.parseInt(inputField.getText().trim());
					tree.delete(value);
					view.repaint(); // Refresh UI layout
					inputField.setText("");
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Please enter a valid numeric integer.");
				}
			}
		});
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			BinaryTreeGUI2 frame = new BinaryTreeGUI2();

			frame.setTitle("Binary Tree Visualization GUI");
			frame.setSize(800, 600);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}