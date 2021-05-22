// Given 2 integers A and B and an array of integars C of size N.

// Element C[i] represents length of ith board.

// You have to paint all N boards [C0, C1, C2, C3 … CN-1]. There are A painters available and each of them takes B units of time to paint 1 unit of board.

// Calculate and return minimum time required to paint all boards under the constraints that any painter will only paint contiguous sections of board.


public class Solution {
   
    private static int MOD = 10000003;
   
    private int numberOfPaintersRequired(ArrayList<Integer> boards, long timeUnits) {
        int numberOfPainters = 1;
        long timeUnitsTakenByThePainter = 0;
       
        for(int i = 0; i < boards.size(); i++) {
            timeUnitsTakenByThePainter += (long)boards.get(i);
            if(timeUnitsTakenByThePainter > timeUnits) {
                numberOfPainters++;
                timeUnitsTakenByThePainter = boards.get(i);
            }
        }
        return numberOfPainters;
    }
   
    private long sumOfBoards(ArrayList<Integer> boards) {
        long sum = 0;
        for(Integer board : boards) {
            sum += (long)board;
        }
        return sum;
    }
   
    private long maxOfBoards(ArrayList<Integer> boards) {
        Integer maxi = Integer.MIN_VALUE;
        for(Integer board : boards) {
            maxi = Math.max(maxi, board);
        }
        return (long)maxi;
    }
   
    public int paint(int A, int B, ArrayList<Integer> C) {
        int paintersWeHave = A;
        int timeToPaintEachUnit = B;
        ArrayList<Integer> boards = new ArrayList<>(C);
       
        long lowestTimeUnits = maxOfBoards(boards);
        long highestTimeUnits = sumOfBoards(boards);
        long ansTimeUnits = 0;
       
        while(lowestTimeUnits <= highestTimeUnits) {
            long midTimeUnits = lowestTimeUnits + (highestTimeUnits - lowestTimeUnits) / 2L;
           
            if(numberOfPaintersRequired(boards, midTimeUnits) <= paintersWeHave) {
                ansTimeUnits = midTimeUnits;
                highestTimeUnits = midTimeUnits - 1;
            } else {
                lowestTimeUnits = midTimeUnits + 1;
            }
        }
       
        return (int)(((ansTimeUnits % MOD) * (B % MOD)) % MOD);
    }
}
