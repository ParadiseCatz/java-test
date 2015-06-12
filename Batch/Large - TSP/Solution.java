import java.util.*;
import java.io.*;

public class Solution {
   static int NMAX = 22;
   long A,B,C,D,N,M;
   long[][] dp, cost;
   Scanner scanIn = new Scanner(System.in);

   long cari(long bm, long pos)
   {
      if (bm == ((1<<N)-1) )
         return T(pos, 0);
      if (dp[(int)bm][(int)pos]!=-1)
         return dp[(int)bm][(int)pos];
      long ret = (1L<<61)-1;
      for (long i=0; i<N; i++)
         if ((bm&(1<<i))==0)
            ret = Math.min(ret,cari(bm+(1<<i),i+1)+cost[(int)pos][(int)i+1]);
      return dp[(int)bm][(int)pos]=ret;
   }

   long T(long i, long j) {
      return (((A+B*i)%M)*((A+B*i)%M)+((C+D*j)%M)*((C+D*j)%M))%M;
   }

   void input() {
      N = scanIn.nextInt();
      A = scanIn.nextInt();
      B = scanIn.nextInt();
      C = scanIn.nextInt();
      D = scanIn.nextInt();
      M = scanIn.nextInt();
   }

   void run() {
      dp = new long[(1<<NMAX)][NMAX];
      for (long[] row: dp)
         Arrays.fill(row, -1);
      cost = new long[NMAX][NMAX];
      input();
      for (int i = 0; i<=N ; ++i) {
         for (int j = 0; j<=N ; ++j) {
            cost[i][j]=T(i,j);
         }
      }
      System.out.println(cari(0,0));
   }

   public static void main(String []args) {
      new Solution().run();
   }
}