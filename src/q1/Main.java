package q1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException{
        
        Scanner in = new Scanner(new File(args[0]));
        
        int[][] inPuzzle = new int[4][4];
        int[] empty = new int[2];
        
        for(int i=0;i<4;i++){
        	String[] num = in.nextLine().split(",");
            for(int j=0;j<4;j++){
                inPuzzle[i][j]=Integer.parseInt(num[j]);
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
            	printResultAndWriteToFile(current);
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
    
    private static void printResultAndWriteToFile(State goal) throws FileNotFoundException, UnsupportedEncodingException{
    	PrintWriter writer = new PrintWriter("tilepuz-al171.txt", "UTF-8");
        for(State intermediate : goal.statesSoFar){
            System.out.println(printBoard(intermediate.puzzle));
            writer.write(printBoard(intermediate.puzzle)+'\n');
        }
        System.out.println(goal.steps);
        writer.write(goal.steps+"\n");
        writer.flush();
        writer.close();
    }
    
    private static String printBoard(int[][] board){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                sb.append(board[i][j]);
                sb.append(' ');
            }
            sb=sb.deleteCharAt(sb.length()-1);
            sb.append('\n');
        }
        return sb.toString();
    }
            
}
