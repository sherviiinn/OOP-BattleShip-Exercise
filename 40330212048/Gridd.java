import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
public class Gridd {
        private int sizegrid;
        public char[][] trakinggrid ;
        public char[][] grid;
        public char lastword = (char)('A'+this.sizegrid-1);
        public Gridd(int n){
            this.sizegrid = n;
            grid = new char[sizegrid][sizegrid];
            trakinggrid = new char[sizegrid][sizegrid];
            initializer(grid);
            initializer(trakinggrid);
        }
        private void initializer(char[][] grid){
            for (char[] chars : grid) {
                Arrays.fill(chars, '~');
            }
        }
        public int getsize(){
            return sizegrid;
        }
        private boolean canplaceship(int z,int x ,int w,int y,boolean place,char[][] grid){
            int c=0;
            if(z==1 || z==3){
                c=x;
            }else{
                c=w;
            }
            if(z==1 || z==4){
                y=y*-1;
                if (c + y < 0) {
                    return false;
                } else {
                    for (int j = c; j > c + y ; j--) {
                        if (z==1 && grid[j][2] == '~') {
                            place = true;
                        }
                        else if (z==4 && grid[x][j] == '~') {
                            place = true;
                        }
                        else{
                            return false;
                        }
                    }
                }
            }else{
                if (c + y > sizegrid) {
                    return false;
                } else {
                    for (int j = c; j < c + y ; j++) {
                        if (z==2 && grid[x][j] == '~') {
                            place = true;
                        }
                        else if (z==3 && grid[j][w] == '~') {
                            place = true;
                        }
                        else{
                            return false;
                        }
                    }
                }
            }
            return place;
        }
        private void placeship(char[][] grid,int z,int x,int w,int y){
            int c=0;
            if(z==1 || z==3){
                c=x;
            }else{
                c=w;
            }
            if(z==1 || z==4){
                y=-1*y;
                for(int i = c;i > c + y ;i--){
                    if(z==1){
                        grid[i][w] = 'S';
                    }else{
                        grid[x][i] = 'S';
                    }
                }
            }else{
                for(int i = c;i < c + y ;i++){
                    if(z==2){
                        grid[x][i] = 'S';
                    }else{
                        grid[i][w] = 'S';
                    }
                }
            }
        }
        public void atomaticplace(char[][] grid){
            Random rand = new Random();
            int y = 2;
            while (y <= 5) {
                int x = rand.nextInt(sizegrid);//satr
                int w = rand.nextInt(sizegrid);//soton
                int z = rand.nextInt(3) + 1;
                boolean place = true;
                if (canplaceship(z,x,w,y,place,grid)) {
                    placeship(grid,z,x,w,y);
                } else {
                    continue;
                }
                y++;
            }
        }
        public void manual(char[][] grid){
            int s = this.sizegrid - 1 ;
            int y = 2;
            while (y <= 5) {
                printGrid(grid);
                Scanner scan = new Scanner(System.in);
                System.out.println("Placing the ship (size : "+y+")");
                System.out.print("Enter a number between 0 and "+ s +" : " );
                int x = scan.nextInt();//satr
                while(!validxmanual(x)){
                    System.out.print("invalid number \nTry again : ");
                    x = scan.nextInt();
                }
                System.out.print("Enter a Word(A-"+(char)(64+this.sizegrid)+") : ");
                String w = scan.next() ;//soton
                while(!validwmanual(w.charAt(0))|| w.length()!=1){
                    System.out.print("invalid input \nTry again : ");
                    w = scan.next();
                }
                int q = (int)(w.charAt(0))-65;
                System.out.print("Enter Direction Up,Right,Down,Left (1,2,3,4) : ");
                int z = scan.nextInt();
                while(z<1 || z>4){
                    System.out.print("invalid input \nTry again : ");
                    z = scan.nextInt();
                }
                boolean place = true;
                if (canplaceship(z,x,q,y,place,grid)){
                    placeship(grid,z,x,q,y);
                }else{
                    System.out.print("invalid direction \nTry again\n");
                    continue;
                }
                y++;
            }
            printGrid(grid);
            System.out.println("Ships are placed");
        }
        private boolean validwmanual(char a){
            return a <= (char) 65 + this.sizegrid && a >= 'A';
        }
        private boolean validxmanual(int a){
            return a <= this.sizegrid-1 && a >= 0;
        }
        public void printGrid(char[][] grid) {
            System.out.print("   ");
            for (int i = 0; i < this.sizegrid; i++) {
                System.out.print((char) ((int) 'A' + i));
                System.out.print(" ");
            }
            System.out.println("");
            for (int i = 0; i < this.sizegrid; i++) {
                if(i>=10){
                    System.out.print(i + " ");
                }else{
                    System.out.print(i + "  ");
                }
                for (int j = 0; j < this.sizegrid; j++) {
                    System.out.print(grid[i][j] + " ");
                }
                System.out.println("");
            }
        }
    }