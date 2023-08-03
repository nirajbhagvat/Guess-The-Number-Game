import javax.swing.*;

public class GameStats {
    public static boolean showWinPopup(QuizGame quizGame, GuessPanel guessPanel) {
        int option = JOptionPane.showConfirmDialog(
                guessPanel,
                "Congratulations! You guessed the correct number:- "+ quizGame.getTargetNumber() + "\nDo you want to play again?",
                "You Win!",
                JOptionPane.YES_NO_OPTION
        );

        return option == JOptionPane.YES_OPTION;
    }

    public static boolean showLosePopup(QuizGame quizGame, GuessPanel guessPanel) {
        int option = JOptionPane.showConfirmDialog(
                guessPanel,
                "Sorry! You've run out of attempts. The number was " + quizGame.getTargetNumber() + ". Do you want to play again?",
                "You Lose!",
                JOptionPane.YES_NO_OPTION
        );
        return option == JOptionPane.YES_OPTION;
    }
}
