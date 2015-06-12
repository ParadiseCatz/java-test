import java.util.*;
import java.lang.reflect.Array;
import java.io.*;

class Solution {
	StringTokenizer tokenizer;
    BufferedReader in;
    static int NMAX = 100100;

    int n, s;
    int[] pre;

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            initVariable();
            solve();
        } catch (Exception e) {
            System.exit(9000);
        }
    }

    String nextToken() throws IOException {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            String line = in.readLine();
            if (line == null) {
                System.exit(0);
            }
            tokenizer = new StringTokenizer(line);
        }
        return tokenizer.nextToken();
    }

    int nextInt() throws NumberFormatException, IOException {
        return Integer.parseInt(nextToken());
    }

    long nextLong() throws NumberFormatException, IOException {
    	return Long.parseLong(nextToken());
    }

    double nextDouble() throws NumberFormatException, IOException {
        return Double.parseDouble(nextToken());
    }

    String nextString() throws IOException {
        return nextToken();
    }

    void initVariable() throws NumberFormatException {
    	pre = new int[NMAX];
    }
 
    void solve() throws NumberFormatException, IOException {
    	while (true){
            n = nextInt();
            s = nextInt();
            for (int i = 1; i<=n ; ++i) {
                int x = nextInt();
                pre[i] = x+pre[i-1];
            }
            int p1,p2;
            p1 = 1;
            p2 = 2;
            int ans = NMAX;
            while (p1!=p2) {
                if (pre[p2-1] - pre[p1-1] < s) {
                    if (p2 != n+1)
                        p2++;
                    else
                        break;
                } else {
                    while (pre[p2-1] - pre[p1-1] >= s) {
                        ans = Math.min(p2-p1,ans);
                        p1++;
                        if (p1 == p2) {
                            break;
                        }
                    }
                }
            }
            if (ans!=NMAX) {
                System.out.println(ans);
            } else {
                System.out.println(0);
            }
        }
    }

	public static void main(String []args) {
		new Solution().run();
	}
}