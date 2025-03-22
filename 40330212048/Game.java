import java.util.Scanner;
    public class Game {
        public void start() {
            boolean playAgain;
            do {
                playGame();
                playAgain = askReplay();
            } while (playAgain);
        }
        private boolean askReplay() {
            System.out.println("Play again? (yes/no)");
            Scanner scanner = new Scanner(System.in);
            return scanner.next().equalsIgnoreCase("yes");
        }
        private void playGame() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to the Battle Ship!");
            System.out.println("1.Play With ai\n2.Play With friends");
            int choice = scanner.nextInt();
            while (choice != 1 && choice != 2) {
                System.out.print("Invalid Choice\nTry again : ");
                choice = scanner.nextInt();
            }
            System.out.print("Enter Grid size : (5-10) ");
            int gridSize = scanner.nextInt();
            while(gridSize>10 || gridSize<5){
                System.out.print("invalid size\nTry again");
                gridSize = scanner.nextInt();
            }
            if (choice == 1) {
                player player1 = new player(gridSize , "Player 1");
                Aiplayer player2 = new Aiplayer(gridSize , "AI ");
                choosing( player1 ,1);
                player2.playergrid.atomaticplace(player2.playergrid.grid);
                player curruntplayer = player1;
                boolean player1turn = true;
                while(!gameover(curruntplayer)){
                    if(player1turn){
                        System.out.println("Player 1's turn");
                        player2.playergrid.printGrid(player2.playergrid.trakinggrid);
                        player1.playershoots(player1.playergrid, player2.playergrid);
                        curruntplayer = player2;
                    }else{
                        System.out.println("AI's turn");
                        player1.playergrid.printGrid(player1.playergrid.trakinggrid);
                        player2.attakeMove(player2.playergrid, player1.playergrid);
                        curruntplayer = player1;
                    }
                    player1turn = !player1turn;
                }
                curruntplayer.playergrid.printGrid(curruntplayer.playergrid.trakinggrid);
                if(curruntplayer == player1){
                    curruntplayer = player2;
                }else{
                    curruntplayer = player1;
                }
                System.out.println(curruntplayer.getname()+" Won");
            } else {
                player player1 = new player(gridSize , "Player 1");
                player player2 = new player(gridSize , "Player 2");
                choosing( player1 ,1);
                choosing( player2 , 2) ;
                player curruntplayer = player1;
                boolean player1turn=true;
                while(!gameover(curruntplayer)){
                    if(player1turn){
                        System.out.println("Player 1's turn");
                        player2.playergrid.printGrid(player2.playergrid.trakinggrid);
                        player1.playershoots(player1.playergrid, player2.playergrid);
                        curruntplayer = player2;
                    }else{
                        System.out.println("Player 2's turn");
                        player1.playergrid.printGrid(player1.playergrid.trakinggrid);
                        player2.playershoots(player2.playergrid, player1.playergrid);
                        curruntplayer = player1;
                    }
                    player1turn = !player1turn;
                }
                if(curruntplayer == player1){
                    curruntplayer = player2;
                }else{
                    curruntplayer = player1;
                }
                curruntplayer.playergrid.printGrid(curruntplayer.playergrid.trakinggrid);
                System.out.println(curruntplayer.getname()+" Won");
            }
            System.out.println("Game Over");
        }
        private void choosing( player player1 , int x) {
            System.out.println("Player "+ x +"\n1.Place ships manually\n2.Place ships randomly");
            Scanner scanner = new Scanner(System.in);
            int choice1 = scanner.nextInt();
            while (choice1 != 1 && choice1 != 2) {
                System.out.print("Invalid Choice\nTry again");
                choice1 = scanner.nextInt();
            }
            if (choice1 == 1) {
                player1.playergrid.manual(player1.playergrid.grid);
            } else {
                player1.playergrid.atomaticplace(player1.playergrid.grid);
            }
        }

        private boolean allshipssunk(char[][] grid) {
            int x = 0;
            for (char[] chars : grid) {
                for (char aChar : chars) {
                    if (aChar == 'X') {
                        x++;
                    }
                }
            }
            return x == 14;
        }
        private boolean gameover(player player) {
            return allshipssunk(player.playergrid.trakinggrid);
        }
        public static void main(String[] args) {
            Game game = new Game();
            game.start();
        }
}