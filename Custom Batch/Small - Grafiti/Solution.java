import java.util.*;
import java.lang.reflect.Array;
import java.io.*;

public class Solution {
	StringTokenizer tokenizer;
    BufferedReader in;

    int v,l;
    char[] s;
    boolean[] used;
    Deque<Integer> ans;

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

    void initVariable() throws NumberFormatException {
    	used = new boolean[512];
    	s = new char[512];
        ans = new ArrayDeque<Integer>();
    }
 
    void solve() throws NumberFormatException, IOException {
        s = nextString().toCharArray();
        l = s.length;
        v = len(l);
        dfs(0);
    }

    void dfs(int ptr) {
        if(ptr == l) {
            while (ans.size() > 1) {
                System.out.print(toHexadecimal(ans.pollFirst()) + " ");
            }
            System.out.println(toHexadecimal(ans.pollFirst()));

            System.exit(0);
            return;
        }


        int v1 = toDecimal(s[ptr]);
        int v2 = (ptr < (l - 1)) ? (v1 * 16 + toDecimal(s[ptr + 1])) : 999;

        if(v1 > 0 && v1 <= v && !used[v1]) {
            used[v1] = true;
            ans.addLast(v1);

            dfs(ptr + 1);

            ans.removeLast();
            used[v1] = false;
        }

        if(v2 > 0 && v2 <= v && !used[v2]) {
            used[v2] = true;
            ans.addLast(v2);

            dfs(ptr + 2);

            ans.removeLast();
            used[v2] = false;
        }
    }

    int len(int l) {
        if(l <= 15) return l;
        else {
            l -= 15;
            return l/2 + 15;
        }
    }

    int toDecimal(char c) {
        if(c <= '9') return c - '0';
        return 10 + c - 'A';
    }

    char toHexadecimalChar(int v) {
        if(v < 10) return (char)(v + (int)'0');
        return (char)(v - 10 + (int)'A');
    }

    String toHexadecimal(int v) {
        String res = "";

        while(v > 0) {
            res = toHexadecimalChar(v % 16) + res;
            v /= 16;
        }

        return res;
    }

	public static void main(String []args) {
		new Solution().run();
	}
}