import java.util.Scanner;

public class player {
    private final String name;
    public String playershoot = "";
    public Gridd playergrid;
    private Validinput validinput;
    public player(int x , String name) {
        playergrid = new Gridd(x);
        this.name = name;
    }
    public String getname(){
        return this.name;
    }
    public void playershoots(Gridd playergrid , Gridd opponentgrid) {
        System.out.println("Enter the column and row to shoot : ");
        Scanner in = new Scanner(System.in);
        playershoot = in.nextLine();
        validinput = new Validinput(playershoot);
        while (!validinput.validate(playergrid.getsize())) {
            System.out.print("invalid input\nTry again : ");
            playershoot = in.nextLine();
            validinput = new Validinput(playershoot);
        }
        shoot(opponentgrid , playershoot.charAt(0), playershoot.charAt(1));
    }
        static void shoot(Gridd opponentgrid, int c, int c2) {
            int y= c -65;
            int x= c2-48;
            if(opponentgrid.trakinggrid[x][y] == 'X' || opponentgrid.trakinggrid[x][y] == 'O' ){
                System.out.println("You Cannot Have a Duplicate Selection");
                return;
            }
            if(opponentgrid.grid[x][y] == 'S'){
                System.out.println("Hit");
                opponentgrid.trakinggrid[x][y] = 'X';
            }else{
                System.out.println("Miss");
                opponentgrid.trakinggrid[x][y] = 'O';
            }
        }

}