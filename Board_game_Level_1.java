import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class GameError extends Exception
{
    GameError(String err) {
        super(err);
    }
}

class Display
{
    public static void showBoard(String board[][])
    {
        for(int i=0; i<5; i++)
        {
            for(int j=0; j<5; j++)
            {
                System.out.print(board[i][j]+"\t");
            }
            System.out.println();
        }
    }

}

class Game
{
    public static int checkDefeat(String board[][])
    {
        int p1c=0, p2c=0;
        for(int i=0; i<5; i++)
        {
            for(int j=0; j<5; j++) {
                if (board[i][j].substring(0, 1) == "A") p1c++;
                else p2c++;
            }
        }
        if(p1c==0) return 2;
        else if(p2c==0) return 1;
        return -1;
    }
}


public class Main {
    public static void main(String args[])
    {
        String board[][]= new String[5][5];
        for(int i=0; i<5; i++)
        {
            for(int j=0; j<5; j++)
            {
                board[i][j]="-";
            }
        }

        System.out.print("Player1 Input: ");
        Scanner sc= new Scanner(System.in);
        String player1InputString= sc.nextLine();
        String p1Input[]= player1InputString.split(",");
        for(int i=0; i<5; i++)
        {
            board[4][i]= "A-"+(p1Input[i]).trim();
        }
        Display.showBoard(board);

        System.out.print("Player2 Input: ");
        String player2InputString= sc.nextLine();
        String p2Input[]= player2InputString.split(",");
        for(int i=0; i<5; i++)
        {
            board[0][i]= "B-"+(p2Input[i]).trim();
        }
        Display.showBoard(board);

        List<String> alive= new ArrayList<>();
        for(int i=0; i<5; i++) alive.add("A-"+(p1Input[i]).trim());
        for(int i=0; i<5; i++) alive.add("B-"+(p2Input[i]).trim());


        boolean p1chance= true;
        String message="Player1 Move: ";
        while(Game.checkDefeat(board)!=-1)
        {
            try
            {
                System.out.println(message);
                String move;
                System.out.println(alive);
                if(p1chance) {

                    message="Player1 Move: ";
                    move = sc.nextLine();
                    if(move.length()!=4) throw new GameError("Invalid Input");
                    System.out.println("A-"+move.substring(0,2));
                    if(!alive.contains("A-"+move.substring(0,2))) throw new GameError("Character Doesn't Exist");

                    p1chance=false;
                }
                else
                {

                    message="Player2 Move: ";
                    move = sc.nextLine();
                    if(move.length()!=4) throw new GameError("Invalid Input");
                    if(!alive.contains("B-"+move.substring(0,2))) throw new GameError("Character Doesn't Exist");
                    p1chance=true;
                }


                Display.showBoard(board);
            }
            catch (GameError err)
            {
                System.out.println(err);
            }
        }
    }
}
