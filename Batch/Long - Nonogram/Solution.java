import java.util.*;
import java.lang.reflect.Array;
import java.io.*;

public class Solution {
	class VectorArray extends Vector<Integer> { }
	static int NMAX = 100;
	int n,m;
	boolean[][] flag;
	VectorArray[] hor,ver;

	StringTokenizer tokenizer;
    BufferedReader in;
    PrintWriter out;

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

    void initVariable() throws NumberFormatException {
    	hor = new VectorArray[NMAX];
    	ver = new VectorArray[NMAX];
    	flag = new boolean[NMAX][NMAX];
    }
 
    void solve() throws NumberFormatException, IOException {
    	n = nextInt();
    	m = nextInt();
    	for (int i = 1; i <= n; ++i)
		{
			int x;
			x = nextInt();
			hor[i] = new VectorArray();
			for (int j = 0; j < x; ++j)
			{
				int y;
				y = nextInt();
				hor[i].add(y);
			}
		}
		

		for (int i = 1; i <= m; ++i)
		{

			int x;
			x = nextInt();
			ver[i] = new VectorArray();

			for (int j = 0; j < x; ++j)
			{
				int y;
				y = nextInt();
				ver[i].add(y);
			}
		}

  		backtrack(1,1);
    }

    void backtrack(int x, int y) {
    	// System.out.println(x + " " + y);
    	if (y>m) {
			backtrack(x+1,1);
			return;
		}
		if (x>n) {
			cetak();
			System.exit(0);
		}
		if (y==1) {
			int temp = 0;
			for (int i = 0; i < hor[x].size(); ++i) {
				temp+=hor[x].get(i);
			}
			if (temp+hor[x].size()-1 == m) {
				int pos = 1;
				for (int i = 0; i < hor[x].size(); ++i)	{
					for (int j = 0; j < hor[x].get(i); ++j)
						flag[x][pos++] = true;
					pos++;
				}
				backtrack(x+1,1);
				return;
			}
		}
		flag[x][y] = true;
		if (cekhor(x,y) && cekver(x,y)) {
			backtrack(x,y+1);
		}
		flag[x][y] = false;
		if (cekhor(x,y) && cekver(x,y)) {
			backtrack(x,y+1);
		}
		return;
    }

    void cetak() {
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= m; ++j) {
				System.out.print(flag[i][j] ? "1" : "0");
			}
			System.out.println();
		}
	}

	boolean cekhor(int x, int y)
	{
		VectorArray tmp = new VectorArray();
		int cnt=0;
		for (int i = 1; i <= y; ++i)
		{
			if (flag[x][i])
			{
				cnt++;
			}
			else
			{
				if (cnt>0)
					tmp.add(cnt);
				cnt = 0;
			}
		}
		if (cnt>0)
			tmp.add(cnt);
		if (tmp.size() > hor[x].size())
			return false;
		if (y==m)
			return tmp.equals(hor[x]);
		for (int i = 0; i < tmp.size(); ++i)
		{
			if (tmp.get(i) > hor[x].get(i))
				return false;
		}
		return true;
	}

	boolean cekver(int x, int y)
	{
		VectorArray tmp = new VectorArray();
		int cnt=0;
		for (int i = 1; i <= x; ++i)
		{
			if (flag[i][y])
			{
				cnt++;
			}
			else
			{
				if (cnt>0)
					tmp.add(cnt);
				cnt = 0;
			}
		}
		if (cnt>0)
			tmp.add(cnt);
		if (tmp.size() > ver[y].size())
			return false;
		if (x==n)
			return tmp.equals(ver[y]);
		for (int i = 0; i < tmp.size(); ++i)
		{
			if (tmp.get(i) > ver[y].get(i))
				return false;
		}
		return true;
	}

	public static void main(String []args) {
		new Solution().run();
	}
}