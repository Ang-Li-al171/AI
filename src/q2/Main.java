package q2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import q2.State;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException{
        
        Scanner in = new Scanner(System.in);
        
        int n = in.nextInt();
        
        int[] ipos = new int[n];
        
        // A* Search Algorithm
        PriorityQueue<State> q = new PriorityQueue<State>();
        q.add(new State(ipos, 0, -1));
        
        while(!q.isEmpty()){
            State current = q.poll();
            if(current.lastFilledJ == n-1){
                printBoardAndWriteToFile(current.ipos);
                System.out.println(current.costSoFar+current.h);
                break; //solution found
            } else {
                Set<State> nextStates = current.genNext(current);
                for(State s : nextStates){
                    q.add(s);
                }
            }
        }
        
        in.close();
    }
    
    private static void printBoardAndWriteToFile(int[] ipos) throws FileNotFoundException, UnsupportedEncodingException{
    	PrintWriter writer = new PrintWriter("queens-al171.csv", "UTF-8");
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<ipos.length;i++){
            for(int j=0;j<ipos.length;j++){
                if(ipos[j]==i){
                    sb.append("Q,");
                } else {
                    sb.append("_,");
                }
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append('\n');
        }
        System.out.println(sb.toString());
        writer.write(sb.toString());
    	writer.close();
    }
}
