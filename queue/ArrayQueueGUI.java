package queue;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ArrayQueueGUI extends JFrame implements ActionListener {
    private QueueAppGUI queue;
    private JTextField inputField, displayField, statusField;
    private JButton btnEnqueue, btnDequeue, btnPeek, btnDisplay, btnExit;

    public ArrayQueueGUI() {
        // Initialize queue with a fixed size of 5
        queue = new QueueAppGUI(5);

        // Frame setup
        setTitle("Queue GUI Application");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1, 5, 5));
        setLocationRelativeTo(null);

        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Enter Value:"));
        inputField = new JTextField(10);
        inputPanel.add(inputField);
        add(inputPanel);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnEnqueue = new JButton("1. Enqueue");
        btnDequeue = new JButton("2. Dequeue");
        btnPeek = new JButton("3. Peek");
        btnDisplay = new JButton("4. Display");
        btnExit = new JButton("5. Exit");
        buttonPanel.add(btnEnqueue);
        buttonPanel.add(btnDequeue);
        buttonPanel.add(btnPeek);
        buttonPanel.add(btnDisplay);
        buttonPanel.add(btnExit);
        add(buttonPanel);

        // Action listeners
        btnEnqueue.addActionListener(this);
        btnDequeue.addActionListener(this);
        btnPeek.addActionListener(this);
        btnDisplay.addActionListener(this);
        btnExit.addActionListener(this);

        // Status field
        statusField = new JTextField("Status: App Started. Queue Size: 5");
        statusField.setEditable(false);
        add(statusField);

        // Display field
        displayField = new JTextField("Queue Visualizer");
        displayField.setEditable(false);
        displayField.setFont(new Font("Monospaced", Font.BOLD, 14));
        add(displayField);
    }

    // Event handling
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        int option = 0;

        if (source == btnEnqueue) option = 1;
        else if (source == btnDequeue) option = 2;
        else if (source == btnPeek) option = 3;
        else if (source == btnDisplay) option = 4;
        else if (source == btnExit) option = 5;

        switch (option) {
            case 1: // Enqueue
                try {
                    int val = Integer.parseInt(inputField.getText().trim());
                    if (queue.enqueue(val)) {
                        statusField.setText("Status: Inserted " + val);
                        displayField.setText(queue.displayString());
                    } else {
                        statusField.setText("Status: Error! Queue is Full.");
                    }
                } catch (NumberFormatException ex) {
                    statusField.setText("Status: Please enter a valid integer.");
                }
                inputField.setText("");
                break;

            case 2: // Dequeue
                int deleted = queue.dequeue();
                if (deleted != -1) {
                    statusField.setText("Status: Removed " + deleted);
                    displayField.setText(queue.displayString());
                } else {
                    statusField.setText("Status: Error! Queue is Empty.");
                    displayField.setText("Queue is empty!");
                }
                break;

            case 3: // Peek
                int frontVal = queue.peek();
                if (frontVal != -1) {
                    statusField.setText("Status: Front element is " + frontVal);
                } else {
                    statusField.setText("Status: Queue is Empty.");
                }
                break;

            case 4: // Display
                displayField.setText(queue.displayString());
                statusField.setText("Status: Displaying current elements.");
                break;

            case 5: // Exit
                System.exit(0);
                break;

            default:
                statusField.setText("Status: Unexpected error.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ArrayQueueGUI().setVisible(true);
        });
    }
}
