package q2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class State implements Comparable<State>{

    int[] ipos;
    int h;
    int costSoFar;
    int lastFilledJ;
    
    public State(int[] ipos, int costSoFar, int lastFilledJ){
        
        this.ipos = Arrays.copyOf(ipos, ipos.length);
        
        this.costSoFar = costSoFar;
        this.lastFilledJ = lastFilledJ;
        
        computeH();
    }
    
    private void computeH(){
        int attackCnt = 0;
        boolean diagTop=false, diagBot=false;
        for(int i=0;i<lastFilledJ;i++){
            if (!diagTop && lastFilledJ-i == ipos[lastFilledJ]-ipos[i]){
                diagTop=true;
                attackCnt++;
            } else if (!diagBot && lastFilledJ-i == ipos[i]-ipos[lastFilledJ]){
                diagBot=true;
                attackCnt++;
            } else if (Math.abs(i-lastFilledJ) + Math.abs(ipos[i]-ipos[lastFilledJ]) == 3 && (Math.abs(i-lastFilledJ)==1 || Math.abs(ipos[i]-ipos[lastFilledJ])==1)){
                attackCnt++;
            }
        }
        this.h = attackCnt;
    }
    
    public Set<State> genNext(State current){
        Set<State> nextStates = new HashSet<State>();
        
        Set<Integer> usedRows = getUsedRows();
        for(int i=0;i<ipos.length;i++){
            if(usedRows.contains(i)) continue;
            ipos[lastFilledJ+1]=i;
            nextStates.add(new State(ipos, costSoFar+h, lastFilledJ+1));
        }
        
        return nextStates;
    }

    private Set<Integer> getUsedRows(){
        Set<Integer> usedRows = new HashSet<Integer>();
        for(int i=0;i<lastFilledJ+1;i++){
            usedRows.add(ipos[i]);
        }
        return usedRows;
    }
    
    @Override
    public int compareTo (State arg0) {
        return this.costSoFar + h - arg0.costSoFar - arg0.h;
    }
}
