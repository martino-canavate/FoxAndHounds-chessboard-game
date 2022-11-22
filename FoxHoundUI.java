import java.util.Arrays;
import java.util.Scanner;
import java.util.Objects;

import static java.util.Arrays.copyOfRange;

/**
 * A utility class for the fox hound program.
 * 
 * It contains helper functions for all user interface related
 * functionality such as printing menus and displaying the game board.
 */
public class FoxHoundUI {

    /** Number of main menu entries. */
    private static final int MENU_ENTRIES = 2;
    /** Main menu display string. */
    private static final String MAIN_MENU =
        "\n1. Move\n2. Exit\n\nEnter 1 - 2:";

    /** Menu entry to select a move action. */
    public static final int MENU_MOVE = 1;
    /** Menu entry to terminate the program. */
    public static final int MENU_EXIT = 2;

    public static void displayBoard(String[] players, int dimension) {
        System.out.print("  ");
        for (int column = 0; column < dimension; column++)
        {
            System.out.print("  " + (char)(65+column) + " ");
        }
        for (int row = 0; row < dimension; row++)
        {
            System.out.println("");
            System.out.print("  ");
            for (int unit = 0; unit < dimension; unit++)
            {
                System.out.print("----");
            }
            System.out.print("-");
            System.out.println("");
            System.out.print(row+1);
            if (row<9){
                System.out.print(" ");
            }
            for (int column = 0; column < dimension; column++)
            {
                char y = (char)(65+column);
                int x = row +1;
                String z = y+ ""+x;
                int contains = 0;
                for (String s: players) {
                    if (s.equals(z)){
                        contains = 1;
                        break;
                    }
                }
                if (players[(players.length)-1].equals(z)){
                    System.out.print("| " + "F" + " ");
                }else if (contains == 1){
                    System.out.print("| " + "H" + " ");
                } else if ((row+column)%2 == 0){
                    System.out.print("| " + "#" + " ");
                } else{
                    System.out.print("| " + " " + " ");
                }
            }
            System.out.print("|");
            System.out.print(row+1);
        }
        System.out.println("");
        System.out.print("  -");
        for (int unit = 0; unit < dimension; unit++)
        {
            System.out.print("----");
        }
        System.out.println("");
        System.out.print("  ");
        for (int column = 0; column < dimension; column++)
        {
            System.out.print("  " + (char)(65+column) + " ");
        }
        System.out.println("");
    }

    /**
     * Print the main menu and query the user for an entry selection.
     * 
     * @param figureToMove the figure type that has the next move
     * @param stdin a Scanner object to read user input from
     * @return a number representing the menu entry selected by the user
     * @throws IllegalArgumentException if the given figure type is invalid
     * @throws NullPointerException if the given Scanner is null
     */
    public static int mainMenuQuery(char figureToMove, Scanner stdin) {
        Objects.requireNonNull(stdin, "Given Scanner must not be null");
        if (figureToMove != FoxHoundUtils.FOX_FIELD 
         && figureToMove != FoxHoundUtils.HOUND_FIELD) {
            throw new IllegalArgumentException("Given figure field invalid: " + figureToMove);
        }

        String nextFigure = 
            figureToMove == FoxHoundUtils.FOX_FIELD ? "Fox" : "Hounds";

        int input = -1;
        while (input == -1) {
            System.out.println(nextFigure + " to move");
            System.out.println(MAIN_MENU);

            boolean validInput = false;
            if (stdin.hasNextInt()) {
                input = stdin.nextInt();
                validInput = input > 0 && input <= MENU_ENTRIES;
            }

            if (!validInput) {
                System.out.println("Please enter valid number.");
                input = -1; // reset input variable
            }

            stdin.nextLine(); // throw away the rest of the line
        }

        return input;
    }
    public static String[] positionQuery(char figureToMove, Scanner stdin, String[] players, int dim) {
        Objects.requireNonNull(stdin, "Given Scanner must not be null");

        String[] table = new String[dim*dim];
        String init = null;
        String fina = null;

        for (int sup = 0; sup < dim; sup+=1) {
            char c = (char) (65 + sup);
            for (int sep = 0; sep<dim; sep+=1) {
                int d = sep + 1;
                table[sup*dim + sep] = c +""+ d;
            }
        }

        String[] hounds = Arrays.copyOfRange(players, 0, players.length -1);
        boolean validInput = false;
        while (!validInput) {
            System.out.println("Provide origin and destination coordinates of the piece you want to move");
            System.out.println("Hounds current positions are " + Arrays.toString(hounds));
            System.out.println("Fox's current position is " + players[(players.length -1)]);

            String str = stdin.nextLine();
            int position = 0;
            for (int i =0; i < str.length(); i+=1){
                if (str.charAt(i) == (' ')){
                    position = i;
                }
            }
            init = str.substring(0, (position));
            fina = str.substring((position + 1));
            boolean ini = false;
            boolean fin = false;

            for (String s: table) {
                if (s.equals(init)){
                    ini = true;
                }
                if (s.equals(fina)) {
                    fin = true;
                }
            }

            if (!ini) {
                System.out.println("Please enter valid origin coordinates");
            }else if(!fin){
                System.out.println("Please enter valid destination coordinates");
            }
            else {
                validInput = FoxHoundUtils.isValidMove(dim, players, figureToMove, init, fina);
                if (!validInput){
                    System.out.println("That's an illegal movement, please choose a different one");
                }
            }
        }
        for (int i = 0; i < ((dim/2)+1); i+=1) {
            if (players[i].equals(init)) {
                players[i] = fina;
            }
        }
        return players;
    }
}








