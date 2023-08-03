import javax.swing.JFrame;

public class QuizGameFrame extends JFrame {
    public QuizGameFrame() {
        setTitle("Guess the Number Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        QuizGame quizGame = new QuizGame();
        GuessPanel guessPanel = new GuessPanel(quizGame);

        add(guessPanel);

        setSize(350, 300); // Set the size of the frame
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
