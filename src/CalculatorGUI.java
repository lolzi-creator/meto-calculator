import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {
    private JTextField display;
    private JPanel buttonPanel;

    private double currentResult = 0; // Stores the result of the last calculation
    private String operator = ""; // Current operator
    private boolean isNewInput = true; // Determines if it's a new input or continuation

    public CalculatorGUI() {
        // Set up the frame
        setTitle("Visual Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display at the top
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Buttons panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));
        add(buttonPanel, BorderLayout.CENTER);

        // Add buttons
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(this);
            buttonPanel.add(button);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("\\d")) { // If it's a number
            if (isNewInput) {
                display.setText(command); // Start fresh for new input
            } else {
                display.setText(display.getText() + command); // Append to existing input
            }
            isNewInput = false;
        } else if (command.equals("C")) { // Clear
            display.setText("0");
            currentResult = 0;
            operator = "";
            isNewInput = true;
        } else if (command.equals("=")) { // Calculate result
            if (!operator.isEmpty()) {
                double input = Double.parseDouble(display.getText());
                currentResult = calculate(currentResult, input, operator);
                display.setText(String.valueOf(currentResult));
                operator = ""; // Reset operator after calculation
            }
            isNewInput = true;
        } else { // Operator
            if (!isNewInput) { // Perform calculation if there's already an input
                double input = Double.parseDouble(display.getText());
                currentResult = operator.isEmpty() ? input : calculate(currentResult, input, operator);
                display.setText(String.valueOf(currentResult));
            }
            operator = command; // Set the new operator
            isNewInput = true;
        }
    }

    private double calculate(double a, double b, String operator) {
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> b != 0 ? a / b : Double.NaN; // Handle division by zero
            default -> a;
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorGUI calculator = new CalculatorGUI();
            calculator.setVisible(true);
        });
    }
}
