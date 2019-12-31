import java.util.*;
import java.lang.*;
class spiral {
    public static String resultCoords(int pairCoords, int startValue, int finalValue) {
        int x = pairCoords+1;
        int y = pairCoords;
        int moves = ((pairCoords+1)*2)-1;
        if(startValue > finalValue && startValue + moves <= finalValue) {
            startValue += moves;
            y-=moves;
        } else if(startValue + moves > finalValue && startValue + 2*moves + 1 <= finalValue) {
            startValue += 2*moves+1;
            y-=moves;
            x-=moves+1;
            moves++;

        } else {
            startValue += startValue + 3*moves + 2;
            y+=1;
            moves++; 
        }
    }
        
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int finalValue = scan.nextInt();
        int coords = (int) (Math.sqrt(finalValue)-1)/2;
        int startInCoords = (int) Math.pow((coords + coords+1), 2);
        if(startInCoords == finalValue) {
            System.out.println("(" + coords + "," + coords + ")");
            scan.close();
            return;
        } else {
            String result = resultCoords(coords, startInCoords+1, finalValue);
            System.out.println(result);
        }
        scan.close();
    }
}

/*
for(int i=0; i<moves; i++) {
            if(startValue == finalValue) {
                return "(" + x + "," + y + ")"; 
            }
            startValue++;
            y--;
        }
        moves++;
        for(int i=0; i<moves; i++) {
            if(startValue == finalValue) {
                return "(" + x + "," + y + ")"; 
            }
            startValue++;
            x--;
        }
        for(int i=0; i<moves; i++) {
            if(startValue == finalValue) {
                return "(" + x + "," + y + ")"; 
            }
            startValue++;
            y++;
        }
        moves++;
        for(int i=0; i<moves; i++) {
            if(startValue == finalValue) {
                return "(" + x + "," + y + ")"; 
            }
            startValue++;
            x++;
        }
        return null;
        */