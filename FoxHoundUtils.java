

/**
 * A utility class for the fox hound program.
 * 
 * It contains helper functions to check the state of the game
 * board and validate board coordinates and figure positions.
 */
public class FoxHoundUtils {

    // ATTENTION: Changing the following given constants can 
    // negatively affect the outcome of the auto grading!

    /** Default dimension of the game board in case none is specified. */
    public static final int DEFAULT_DIM = 8;
    /** Minimum possible dimension of the game board. */
    public static final int MIN_DIM = 4;
    /** Maximum possible dimension of the game board. */
    public static final int MAX_DIM = 26;

    /** Symbol to represent a hound figure. */
    public static final char HOUND_FIELD = 'H';
    /** Symbol to represent the fox figure. */
    public static final char FOX_FIELD = 'F';

    // HINT Write your own constants here to improve code readability ...

    public static String[] initialisePositions(int dimension) {
        char z;
        String[] initpos = new String[dimension / 2 + 1];

        for (int hey = 0; hey < dimension; hey +=2) {
            char y =(char) (66 + hey);
            initpos[hey / 2] = y+""+1;
        }

        if (dimension%4 == 0){
             z = (char)(65 + dimension/2);
        } else if (dimension%2 == 0) {
            z = (char)(64 + dimension/2);
        } else if ((dimension+1)%4 == 0){
            z = (char)(65 + dimension/2);
        } else {
            z = (char) (66 + dimension / 2);
        }
        initpos[dimension/2 ] = z+""+dimension;

        for(int j = 0; j< initpos.length; j += 1) {
            System.out.println(initpos[j]);
        }
        return initpos;
    }

    public static boolean isValidMove(int dim, String[] players, char figure, String origin, String destination){
        boolean correctorigin = false;
        boolean correctdestination = true;
        boolean correctdestination2 = false;
        boolean correctfigure;
        boolean allOK;
        String[] table = new String[dim*dim];


        for (int sup = 0; sup < dim; sup+=1) {
            char c = (char) (65 + sup);
            for (int sep = 0; sep<dim; sep+=1) {
                int d = sep + 1;
                table[sup*dim + sep] = c +""+ d;
            }
        }
        int i = Integer.parseInt(destination.substring(1)) - Integer.parseInt(origin.substring(1));
        int o = ((int)destination.charAt(0)-(int)origin.charAt(0));
        for (String s: players) {
            if (s.equals(origin)) {
                correctorigin = true;
                break;
            }
        }
        for (String s: players) {
            if (s.equals(destination)) {
                correctdestination = false;
                break;
            }
        }

        if (figure == 'H') {
            correctfigure = !(origin.equals(players[(dim / 2)]));
            for (String s : players) {
                if (s.equals(destination)) {
                    correctdestination = false;
                    break;
                }
            }
            if (!((o == 1) || (o == -1))) {
                correctdestination = false;
            }
            if (!(i == 1)) {
                correctdestination = false;
            }

        }else if (figure == 'F') {
            correctfigure = (origin.equals(players[(dim/2)]));
            for (String s: players) {
                if (s.equals(destination)) {
                    correctdestination = false;
                    break;
                }
            }
            if (!((o == 1) || (o == -1))){
                correctdestination = false;
            }
            if (!((i == 1) || (i == -1))) {
                correctdestination = false;
            }


        } else{
            correctfigure = false;
        }
        for (String s: table) {
            if (s.equals(destination)){
                correctdestination2 = true;
            }
        }

        allOK = correctdestination && correctfigure && correctorigin && correctdestination2;
        return allOK;
    }

    public static boolean isFoxWin(int dimension, String[] players){
        boolean state = false;
        if (Integer.parseInt(players[dimension/2].substring(1)) == 1){
            System.out.println("The Fox Wins!");
            state = true;
        }
        return state;
    }
    public static boolean isHoundWin(int dim, String[] players){
        boolean state = true;
        String playerpos = players[dim/2];
        String[] possibilities = new String[4];
        possibilities[0] = (char)((playerpos.charAt(0)+1))+""+ (Integer.parseInt(playerpos.substring(1))+1);
        possibilities[1] = (char)((playerpos.charAt(0)+1))+""+ (Integer.parseInt(playerpos.substring(1))-1);
        possibilities[2] = (char)((playerpos.charAt(0)-1))+""+ (Integer.parseInt(playerpos.substring(1))+1);
        possibilities[3] = (char)((playerpos.charAt(0)-1))+""+ (Integer.parseInt(playerpos.substring(1))-1);
        for (int i = 0; i<3; i+=1){
            if(isValidMove(dim, players, 'F', playerpos, possibilities[i])){
                state = false;
                System.out.println(possibilities[i]);
                break;
            }else{
                System.out.println("The Hounds Win!");
            }
        }
        return state;
    }
}
