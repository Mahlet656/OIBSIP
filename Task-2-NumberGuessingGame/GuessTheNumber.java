import java.util.*;
public class GuessTheNumber {

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        String playAgain;
        int totalScore = 0;
        int roundsWon = 0;

        System.out.println("--- Welcome to the Number Guessing Game! ---");

        do {
            int numberToGuess = random.nextInt(100) + 1;
            int attempts = 0;
            final int MAX_ATTEMPTS = 10;
            boolean hasGuessedCorrectly = false;

            System.out.println("\nI've picked a new number between 1 and 100.");
            System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess it.");


            while (attempts < MAX_ATTEMPTS) {
                System.out.print("Enter your guess (" + (attempts + 1) + "/" + MAX_ATTEMPTS + "): ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess < numberToGuess) {
                    System.out.println("Too low!");
                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Congratulations! You guessed the number correctly in " + attempts + " attempts!");
                    hasGuessedCorrectly = true;

                    int roundScore = (MAX_ATTEMPTS - attempts + 1) * 10;
                    totalScore += roundScore;
                    roundsWon++;
                    System.out.println("You earned " + roundScore + " points this round.");
                    break;
                }
            }

            if (!hasGuessedCorrectly) {
                System.out.println("\nSorry, you've used all your attempts. The number was: " + numberToGuess);
            }

            System.out.println("Your current total score is: " + totalScore);

            System.out.print("\nDo you want to play another round? (yes/no): ");
            playAgain = scanner.next();

        } while (playAgain.equalsIgnoreCase("yes"));

        System.out.println("\n--- Game Over ---");
        System.out.println("Thanks for playing! You won " + roundsWon + " rounds.");
        System.out.println("Your final score is: " + totalScore);

        scanner.close();
    }
}
