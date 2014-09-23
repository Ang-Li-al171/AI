package q2;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import q2.State;

public class Main {

    public static void main(String[] args){
        
        Scanner in = new Scanner(System.in);
        
        int n = in.nextInt();
        
        int[] ipos = new int[n];
        
        // A* Search Algorithm
        PriorityQueue<State> q = new PriorityQueue<State>();
        q.add(new State(ipos, 0, -1));
        
        while(!q.isEmpty()){
            State current = q.poll();
            if(current.lastFilledJ == n-1){
                printBoard(current.ipos);
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
    
    private static void printBoard(int[] ipos){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<ipos.length;i++){
            for(int j=0;j<ipos.length;j++){
                if(ipos[j]==i){
                    sb.append("1 ");
                } else {
                    sb.append("0 ");
                }
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }
}
