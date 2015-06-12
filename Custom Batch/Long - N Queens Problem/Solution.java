import java.util.*;
import java.io.*;

public class Solution {
   int n;
   Scanner scanIn = new Scanner(System.in);
   Random generator = new Random
         ( this.hashCode() * System.currentTimeMillis() );
   
   void shuffleArray ( int [] x, int lim )
   {  while ( lim > 1 )
      {  int item;
         int save = x[lim-1];
         item = generator.nextInt(lim);
         x[--lim] = x[item];
         x[item] = save;
      }
   }

   static void swap ( int[] x, int p, int q )
      {  int temp = x[p];  x[p] = x[q];  x[q] = temp;  }

   boolean build( int[] board, int size )
      {
         int row, nxt, k;
         boolean[] diagChk = new boolean[2*size-1],
                   antiChk = new boolean[2*size-1];

         shuffleArray ( board, size );

         diagChk[0-board[0]+size-1] = true;
         antiChk[0+board[0]] = true;

         for ( row = 1; row < size; row++ )
         {
            nxt = row + 1;
         // If the current row is invalid, try swapping with succeeding
         // rows until there is a valid one --- or NONE work.
            while ( (diagChk[row-board[row]+size-1] ||
                     antiChk[row+board[row]]) )
            {
               if (nxt == size)
                  return false;      // Failed to find a good replacement
               swap ( board, row, nxt++ );
            }
            diagChk[row-board[row]+size-1] = true;
            antiChk[row+board[row]] = true;
         }
         return true;
      }

   void run() {
      n = scanIn.nextInt();
      if (n==2 || n==3){
         System.out.println(-1);
         return;
      }
      int[] trial = new int[n];
      int k;

      for ( k = 0; k < n; k++ )
         trial[k] = k;

      while ( !build(trial, n) ) {}

      for (int i=0; i<n ; ++i) {
         for (int j = 0; j<n ; ++j) {
            if (trial[i] == j)
               System.out.print("*");
            else {
               System.out.print(".");
            }
         }
         System.out.println();
      }
      System.out.println();
   }

   public static void main(String []args) {
      new Solution().run();
   }
}