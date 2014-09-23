package q1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class State implements Comparable<State>{

    public int steps;
    public int costSoFar;
    public int h;
    public int[][] puzzle;
    public int[] empty;
    public List<State> statesSoFar;
    
    public State(int[][] puzzle, int[] empty, int steps, int costSoFar, List<State> statesSoFar){
        
        this.statesSoFar = new ArrayList<State>(statesSoFar);
        this.statesSoFar.add(this);
        
        this.puzzle = new int[4][];
        for(int i=0;i<4;i++) this.puzzle[i] = Arrays.copyOf(puzzle[i], puzzle[i].length);
        
        this.empty = Arrays.copyOf(empty, empty.length);
        
        this.costSoFar = costSoFar;
        this.steps = steps;
        this.h = 0;
        
        computeH();
    }
    
    private void computeH(){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                int n = puzzle[i][j]-1;
                if(n==-1){continue;}
                h += Math.abs(n/4-i) + Math.abs(n%4-j);
            }
        }
    }
    
    @Override
    public int compareTo (State arg0) {
        return costSoFar+h-arg0.costSoFar-arg0.h;
    }
    
    public Set<State> genNext(State current){
        Set<State> next = new HashSet<State>();
        
        // increment steps, costSoFar, and compute H, for all possible moves
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                int manD = Math.abs(empty[0]-i) + Math.abs(empty[1]-j);
                if(manD==3 && (Math.abs(empty[0]-i)==1 || Math.abs(empty[1]-j)==1)){
                    int temp = puzzle[i][j];
                    puzzle[i][j]=0;
                    puzzle[empty[0]][empty[1]]=temp;
                    next.add(new State(puzzle, new int[]{i, j}, current.steps+1, current.costSoFar+current.h, current.statesSoFar));
                    puzzle[i][j]=temp;
                    puzzle[empty[0]][empty[1]]=0;
                }
            }
        }
        
        return next;
    }
}
