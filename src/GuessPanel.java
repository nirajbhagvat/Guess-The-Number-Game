import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class GuessPanel extends JPanel {
    private final QuizGame quizGame;
    private JLabel messageLabel;
    private JLabel attemptsLabel;
    private JTextField guessField;

    private final Color buttonColor = new Color(0, 150, 136); // Teal
    private final Color textColor = Color.BLACK;
    private final Color successColor = new Color(76, 175, 80); // Green
    private final Color failureColor = new Color(244, 67, 54); // Red

    public GuessPanel(QuizGame quizGame) {
        this.quizGame = quizGame;
        // Light gray
        Color backgroundColor = new Color(240, 240, 240);
        this.setBackground(backgroundColor);
        createUI();
    }

    private void createUI() {
        setLayout(new BorderLayout());

        messageLabel = new JLabel("Guess a number between 1 and 100");
        add(messageLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        guessField = new JTextField(10);
        inputPanel.add(guessField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            checkGuess();
            guessField.setText(""); // Clear the text field after submitting
        });
        inputPanel.add(submitButton);

        add(inputPanel, BorderLayout.CENTER);

        attemptsLabel = new JLabel("Round " + quizGame.getRound() + " - Attempts left: " + quizGame.getAttemptsLeft());
        Border attemptsBorder = BorderFactory.createLineBorder(Color.GREEN);
        attemptsLabel.setBorder(BorderFactory.createTitledBorder(attemptsBorder, "ROUNDS & ATTEMPTS STATUS", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), Color.BLACK));
        attemptsLabel.setHorizontalAlignment(JLabel.CENTER);
        attemptsLabel.setOpaque(true); // Allow the label to be painted with a background color
        attemptsLabel.setBackground(new Color(200, 200, 200));
        add(attemptsLabel, BorderLayout.SOUTH);

        // Customize the "Submit" button
        submitButton.setBackground(buttonColor);
        submitButton.setForeground(Color.WHITE); // Text color
        submitButton.setFont(new Font("Arial", Font.PLAIN, 14));

        // Customize the "Result" label
        messageLabel.setForeground(textColor); // Text color
        messageLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Customize the "Attempts left" label
        attemptsLabel.setForeground(textColor); // Text color
        attemptsLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        // Set the layout and add components to the panel
        this.setLayout(new GridLayout(5, 1));
        this.add(guessField);
        this.add(submitButton);
        this.add(messageLabel);
        this.add(attemptsLabel);
    }

    private void checkGuess() {

        String guessedText = guessField.getText().trim();
        if (guessedText.isEmpty()) {
            messageLabel.setText("Please enter a valid input!");
        }
        int guessedNumber;
        try {
            guessedNumber = Integer.parseInt(guessedText);
        } catch (NumberFormatException e) {
            // Show a message to the user that the input is not a valid number
            messageLabel.setText("Invalid input. Please enter a valid number.");
            return; // Exit the method without further processing
        }

        if (guessedNumber == quizGame.getTargetNumber()) {
            messageLabel.setText("Congratulations! You guessed the number.");
            messageLabel.setForeground(successColor); // Change label color to green for success
            boolean playAgain = GameStats.showWinPopup(quizGame, this);
            if (!playAgain) {
                System.exit(0);
            } else {
                quizGame.startNewGame();
                resetGame();
            }
        } else if (guessedNumber < quizGame.getTargetNumber()) {
            messageLabel.setText("Try a higher number.");
            messageLabel.setForeground(textColor); // Reset label color
        } else {
            messageLabel.setText("Try a lower number.");
            messageLabel.setForeground(textColor); // Reset label color
        }

        quizGame.decreaseAttempts();
        attemptsLabel.setText("Round " + quizGame.getRound() + " - Attempts left: " + quizGame.getAttemptsLeft());

        if (quizGame.getAttemptsLeft() == 0) {
            messageLabel.setText("Sorry! You've run out of attempts. The number was " + quizGame.getTargetNumber() + ".");
            messageLabel.setForeground(failureColor); // Change label color to red for failure
            boolean playAgain = GameStats.showLosePopup(quizGame, this);
            if (!playAgain) {
                System.exit(0);
            } else {
                quizGame.startNewGame();
                resetGame();
            }
        }
    }

    public void resetGame() {
        messageLabel.setText("Guess a number between 1 and 100");
        attemptsLabel.setText("Round " + quizGame.getRound() + " - Attempts left: " + quizGame.getAttemptsLeft());
        guessField.setText("");
    }
}