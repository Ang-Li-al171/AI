package q1;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args){
        
        Scanner in = new Scanner(System.in);
        
        int[][] inPuzzle = new int[4][4];
        int[] empty = new int[2];
        
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                inPuzzle[i][j]=in.nextInt();
                if(inPuzzle[i][j]==0){
                    empty = new int[]{i, j};
                }
            }
        }
        
//        System.out.println(new State(inPuzzle, empty, 0, 0).h);
        
        // A* Search Algorithm
        PriorityQueue<State> q = new PriorityQueue<State>();
        q.add(new State(inPuzzle, empty, 0, 0, new ArrayList<State>()));
        
        while(!q.isEmpty()){
            State current = q.poll();
            if(current.h == 0){
                printResult(current);
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
    
    private static void printResult(State goal){
        for(State intermediate : goal.statesSoFar){
            System.out.println(printBoard(intermediate.puzzle));
        }
        System.out.println(goal.steps);
    }
    
    private static String printBoard(int[][] board){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                sb.append(board[i][j]);
                sb.append(' ');
            }
            sb.append('\n');
        }
        sb.append('\n');
        return sb.toString();
    }
            
}
