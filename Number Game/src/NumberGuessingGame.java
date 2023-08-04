import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame {
    private int secretNumber;
    private int attempts;
    private int maxAttempts;

    private JFrame frame;
    private JTextField inputField;
    private JTextArea outputArea;

    public NumberGuessingGame() {
        secretNumber = generateSecretNumber();
        attempts = 0;
        maxAttempts = 10;

        frame = new JFrame("Number Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        inputField = new JTextField();
        JButton guessButton = new JButton("Guess");
        guessButton.addActionListener(new GuessButtonListener());

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        frame.add(inputField, BorderLayout.NORTH);
        frame.add(outputArea, BorderLayout.CENTER);
        frame.add(guessButton, BorderLayout.SOUTH);

        showWelcomeMessage();
    }

    private int generateSecretNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    private void showWelcomeMessage() {
        showMessage("Welcome to the Number Guessing Game!\nI've picked a number between 1 to 100. Can you guess it?");
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (attempts < maxAttempts) {
                try {
                    int guess = Integer.parseInt(inputField.getText());
                    attempts++;

                    if (guess == secretNumber) {
                        showMessage("Congratulations! You guessed the correct number " + secretNumber + " in " + attempts + " attempts.");
                        frame.dispose();
                    } else if (guess < secretNumber) {
                        showMessage("Try a higher number.");
                    } else {
                        showMessage("Try a lower number.");
                    }
                } catch (NumberFormatException ex) {
                    showMessage("Invalid input! Please enter a valid number.");
                }
            } else {
                showMessage("Sorry, you've reached the maximum number of attempts. The correct number was: " + secretNumber);
                frame.dispose();
            }

            inputField.setText("");
            inputField.requestFocus();
        }
    }

    public void startGame() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberGuessingGame game = new NumberGuessingGame();
            game.startGame();
        });
    }
}
