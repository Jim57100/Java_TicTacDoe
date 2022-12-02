import java.util.Scanner;
import java.util.Arrays;

class TicTacToe {
  
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    
    char player = 'X';

    System.out.println("Lets's play tic tac toe");
    char[][] grid = {
      {'_', '_', '_'},
      {'_', '_', '_'},
      {'_', '_', '_'}
    };
    printGrid(grid);

    //Player Turn
    for(int i = 0; i < 9; i++ ) {
      if(i%2 == 0) {
        player = 'X';
      } else {
        player = 'O';
      }
      //SELECT SPOT
      playerTurn(grid, player);
      //CHECK WIN
      int count = checkWin(grid);
      if(count == 3) {
        System.out.println("X wins !");
        break;
      } else if(count == -3 ) {
        System.out.println("O wins !");
        break;
      }  else if (i == 8) {
        System.out.println("It's a tie !");
      }
    }
    
    scan.close();
  }

  /**printGrid
   * Function name - printGrid()
   * @param board (char[][])
   * @param board
   */
  public static void printGrid(char[][] board) {
    System.out.print("\n");
    for(int i=0; i < board.length; i++) {
      System.out.print("\t");
      for(int j=0; j < board[i].length; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.print("\n\n");
    }
  }

   /** Task 4 - Write a function that lets the user choose a spot
     * Function name – askUser()
     * @param grid (char[][] grid)
     * @return spot (int[]) 
     */
    public static int[] askUser(char[][] grid, char player) {
      int[] spot = new int[2];
      System.out.println("Turn: " + player);
      System.out.print("Pick a row and column number (from 0 to 2): ");
      for(int i = 0; i < spot.length; i++) {
        int choice = scan.nextInt();
        while(choice > 2) {
          System.out.println("Your out of the grid, please select a spot between 0 and 2");
          choice = scan.nextInt();
        };
        spot[i] = choice; 
      }

      int row = spot[0];
      int column = spot[1];
      if(grid[row][column] != '_') {
        System.out.println("This spot is taken. Select another: ");
        for(int i = 0; i < spot.length; i++) {
          int choice = scan.nextInt();
          spot[i] = choice; 
        }
      }
      System.out.print(spot[0] + " " + spot[1]);
      return spot;
    }

    /** playerTurn
     * Function name – playerTurn()
     * @param grid (char[][] grid)
     * @param player (char player)
     * @return void
    */
    public static void playerTurn(char[][] grid, char player) {
      int[] spot = askUser(grid, player); //[1, 0]
      grid[spot[0]][spot[1]] = player;
      printGrid(grid);
    }

    /** checkWin
     * Function name - checkWin 
     * @param grid (char[][])
     * @return count (int)
    */
    public static int checkWin(char[][] grid) {
      int count = 0;
      //CHECK ROWS
        for (int i = 0; i < grid.length; i++) {
          for(int j = 0; j < grid[i].length; j++) {
            if(grid[i][j] == 'X') {
              count++;
            } else if(grid[i][j] == 'O') {
              count--;
            }
          }

          if(count == 3 || count == -3) {
            return count;
          } else {
            count = 0;
          }
        }
      //CHECK COLUMNS
      for(int i = 0; i < 3; i++) {
        for(int j = 0; j < grid.length; j++) {
          if(grid[j][i] == 'X') {
            count++;
          } else if(grid[j][i] == 'O') {
            count--;
          }
        }
        if(count == 3 || count == -3) {
          return count;
        } else {
          count = 0;
        }

      }

      //CHECK LEFT DIAGONAL
      for(int i = 0; i < 3; i++) {
        if(grid[i][i] == 'X') {
          count++;
        } else if(grid[i][i] == 'O') {
          count--;
        }
      }
      if(count == 3 || count == -3) {
        return count;
      } else {
        count = 0;
      }
      //CHECK RIGHT DIAGONAL
      for(int i = 0; i < 3; i++) {
        int rowIndex = 2 - i;
        if(grid[rowIndex][i] == 'X') {
          count++;
        } else if (grid[rowIndex][i] == 'O') {
          count--;
        }
      }
      if(count == 3 || count == -3) {
        return count;
      } else {
        count = 0;
      }
      return count;
    }
}