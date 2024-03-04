import java.util.Scanner;
public class Game {
    final int numRows=6;
    final int numColumns=5;
    final String winningWord;
    char[][] gameBoard=new char[numRows][numColumns];
    boolean didWin=false;
    public Game(String winningWord){
        if (winningWord.length()==numColumns){
            this.winningWord=winningWord.toLowerCase();
        }else {
            System.out.println("invalid winning word!");
            System.out.println("need exactly "+numColumns+" letters!");
            System.out.println("a default word was chosen!");
            this.winningWord="hello";
        }
    }
    public void startPlaying(){
        Scanner scanner=new Scanner(System.in);
        printInstructions(scanner);
        for (int i=0;i<numRows;i++){
            printBoard();
            startNewAttempt(scanner,i+1);
            if (didWin){printBoard();break;}
        }
        if (didWin){
            System.out.println("YOU WON (:");
        }else {
            System.out.println("YOU LOST ):");
        }
    }
    private void startNewAttempt(Scanner scanner,int attemptNumber){
        System.out.println("attempt number: "+attemptNumber);
        System.out.println("insert a "+numColumns+" letter word:");
        String word;
        int counter=0;
        do {
            if (counter>0){System.out.println("invalid word! must have exactly "+numColumns+" letters!");}
            word=scanner.nextLine().toLowerCase();
            counter++;
        }while (word.length()!=numColumns);
        fillBoard(word,attemptNumber);
    }
    private void fillBoard(String word, int attemptNumber) {
        int row = attemptNumber - 1;
        for (int i = 0; i < numColumns; i++) {
            if (winningWord.charAt(i) == word.charAt(i)) {
                gameBoard[row][i] = Character.toUpperCase(word.charAt(i));
            } else if (winningWord.contains(Character.toString(word.charAt(i)))) {
                gameBoard[row][i] = Character.toLowerCase(word.charAt(i));
            } else {
                gameBoard[row][i] = '.';
            }
        }
        if (word.equals(winningWord)) {
            didWin = true;
        }
    }

    private void printBoard() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println();
        }
    }
    private void printInstructions(Scanner scanner){
        System.out.println("you need to guess a 5 letter word.");
        System.out.println("you have 6 attempts.");
        System.out.println("you would get a hint if the word you chose");
        System.out.println("contains a letter from the winning word");
        System.out.println("if a letter on the board is in capital, it means it is in the winning word.");
        System.out.println("press 'ENTER' to continue.");
        if (scanner.nextLine().isEmpty()){return;}
        printInstructions(scanner);
    }
}
