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
