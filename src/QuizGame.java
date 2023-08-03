import java.util.Random;

public class QuizGame {
    private int targetNumber;
    private int attemptsLeft;
    private int round;

    public QuizGame() {
        round = 0;
        startNewGame();
    }

    public void startNewGame() {
        targetNumber = generateTargetNumber();
        attemptsLeft = 5;
        ++round;
    }

    public int getTargetNumber() {
        return targetNumber;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public int getRound() {
        return round;
    }

    public void decreaseAttempts() {
        attemptsLeft--;
    }

    private int generateTargetNumber() {
        return new Random().nextInt(100) + 1;
    }
}
