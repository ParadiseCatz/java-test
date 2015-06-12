import java.util.*;
import java.lang.reflect.Array;
import java.io.*;

public class Solution {
	StringTokenizer tokenizer;
    BufferedReader in;
    PrintWriter out;
 
    static boolean[][] vis;
    static public final String[] command = { "up", "down", "left", "right" };
    static public final int[] dr = { -1, 1, 0, 0 };
    static public final int[] dc = { 0, 0, -1, 1 };

    int sol;

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
            initVariable();
            solve();
        } catch (Exception e) {
            System.exit(9000);
        } finally {
            out.flush();
            out.close();
        }
    }

    String nextToken() throws IOException {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(in.readLine());
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

    char nextChar() throws IOException {
    	return nextString().charAt(0);
    }

    void initVariable() throws NumberFormatException {
        vis = new boolean[2001][2001];
    }
 
    void solve() throws NumberFormatException, IOException {
        int n, m;
        n = nextInt();
        m = nextInt();
        vis[1000+0][1000+0] = true;
        dfs(0, 0);
        out.println(sol);
    }

    void dfs( int r, int c ) throws NumberFormatException, IOException
    {
        ++sol;

        for ( int dir=0; dir<4; ++dir ) {
            int nr = r+dr[dir], nc = c+dc[dir];
            if ( vis[1000+nr][1000+nc] ) continue;

            vis[1000+nr][1000+nc] = true;

            String reply;

            out.println( command[dir] ); out.flush();
            reply = nextString();
            if ( reply.equals("ok") ) {
                dfs( nr, nc );
                out.println( command[dir^1] ); out.flush();
                reply = nextString();
            }
        }
    }

	public static void main(String []args) {
		new Solution().run();
	}
}