import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Position
{
    public int i,j;
    Position()
    {
        this.i=-1;
        this.j=-1;
    }

}

class Data
{
    String board[][];
    List alive;
    Data(String board[][], List alive)
    {
        this.board=board;
        this.alive=alive;
    }

}

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

class PawnMove
{
    public static boolean isOutOfBounds(int a, int b)
    {
        if(a>=5) return true;
        if(b>=5) return true;

        if(a<0) return true;
        if(a<0) return true;

        return false;
    }

    public static Data movePawn(String board[][], String piece, String direction, String player, List alive) throws GameError
    {
        Position pawnPos= new Position();

        outer:
        for(int i=0; i<5; i++)
        {
            for(int j=0; j<5; j++)
            {

                if(board[i][j].equals(piece))
                {
                    pawnPos.i=i;
                    pawnPos.j=j;
                    break outer;
                }
            }
        }

        Position oldPawnPosition= new Position();
        oldPawnPosition.i=pawnPos.i;
        oldPawnPosition.j=pawnPos.j;

        if(direction.equals("F") && player.equals("P1"))
        {
            pawnPos.i--;
            if(isOutOfBounds(pawnPos.i, pawnPos.j)) throw new GameError("Target out of bounds");
        }
        else if(direction.equals("F") && player.equals("P2"))
        {
            pawnPos.i++;
            if(isOutOfBounds(pawnPos.i, pawnPos.j)) throw new GameError("Target out of bounds");
        }

        if(direction.equals("B") && player.equals("P1"))
        {
            pawnPos.i++;
            if(isOutOfBounds(pawnPos.i, pawnPos.j)) throw new GameError("Target out of bounds");
        }
        else if(direction.equals("B") && player.equals("P2"))
        {
            pawnPos.i--;
            if(isOutOfBounds(pawnPos.i, pawnPos.j)) throw new GameError("Target out of bounds");
        }

        if(direction.equals("L") && player.equals("P1"))
        {
            pawnPos.j--;
            if(isOutOfBounds(pawnPos.i, pawnPos.j)) throw new GameError("Target out of bounds");
        }
        else if(direction.equals("L") && player.equals("P2"))
        {
            pawnPos.j++;
            if(isOutOfBounds(pawnPos.i, pawnPos.j)) throw new GameError("Target out of bounds");
        }

        if(direction.equals("R") && player.equals("P1"))
        {
            pawnPos.j++;
            if(isOutOfBounds(pawnPos.i, pawnPos.j)) throw new GameError("Target out of bounds");
        }
        else if(direction.equals("R") && player.equals("P2"))
        {
            pawnPos.j--;
            if(isOutOfBounds(pawnPos.i, pawnPos.j)) throw new GameError("Target out of bounds");
        }

        if(player.equals("P1") && board[pawnPos.i][pawnPos.j].substring(0,1).equals("A")) throw new GameError("Player 1 has already a piece standing at "+ pawnPos.i+","+pawnPos.j);
        else if(player.equals("P2") && board[pawnPos.i][pawnPos.j].substring(0,1).equals("B")) throw new GameError("Player 2 has already a piece standing at "+ pawnPos.i+","+pawnPos.j);

        if(!board[pawnPos.i][pawnPos.j].equals("-")) alive.remove(board[pawnPos.i][pawnPos.j]);

        board[oldPawnPosition.i][oldPawnPosition.j]="-";
        board[pawnPos.i][pawnPos.j]=piece;


        Data dat= new Data(board, alive);

        return dat;

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
                if (board[i][j].substring(0, 1).equals("A")) p1c++;
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
        while(Game.checkDefeat(board)==-1)
        {
            try
            {

                String move;
                System.out.println(alive);
                if(p1chance) {

                    message="Player1 Move: ";
                    System.out.print(message);
                    move = sc.nextLine();
                    if(move.length()!=4) throw new GameError("Invalid Input");
                    if(!alive.contains("A-"+move.substring(0,2))) throw new GameError("Character Doesn't Exist");

                    String piece= "A-"+move.substring(0,2);
                    String direction= move.substring(3,4);

                    Data dat= PawnMove.movePawn(board, piece, direction, "P1", alive);
                    alive= dat.alive;
                    board= dat.board;
                    Display.showBoard(board);
                    p1chance=false;
                }
                else
                {
                    message="Player2 Move: ";
                    System.out.print(message);
                    move = sc.nextLine();
                    if(move.length()!=4) throw new GameError("Invalid Input");
                    if(!alive.contains("B-"+move.substring(0,2))) throw new GameError("Character Doesn't Exist");

                    String piece= "B-"+move.substring(0,2);
                    String direction= move.substring(3,4);

                    Data dat= PawnMove.movePawn(board, piece, direction, "P2", alive);
                    alive= dat.alive;
                    board= dat.board;
                    Display.showBoard(board);

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
