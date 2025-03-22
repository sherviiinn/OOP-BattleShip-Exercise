import java.util.Random;
public class Aiplayer extends player {
    public Gridd aigrid;
    private Validinput validinput;
    public Aiplayer(int x , String name) {
        super(x , name);
        aigrid = new Gridd(x);
    }
    private String makeMove() {
        Random rand = new Random();
        return "" + (char) ('A' + rand.nextInt(aigrid.getsize())) + rand.nextInt(aigrid.getsize());
    }
    public void attakeMove(Gridd player, Gridd opponent ) {
        String w = makeMove();
        int y= w.charAt(0) -65;
        int x= w.charAt(1)-48;
        if(opponent.trakinggrid[x][y] == 'X' || opponent.trakinggrid[x][y] == 'O' ){
            makeMove();
        }
        System.out.println(w);
        shoot(opponent, w.charAt(0) , w.charAt(1));
    }
}
