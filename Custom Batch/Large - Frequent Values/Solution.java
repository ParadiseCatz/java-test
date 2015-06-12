import java.util.*;
import java.lang.reflect.Array;
import java.io.*;

class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second) {
        super();
        this.first = first;
        this.second = second;
    }

    public boolean equals(Object other) {
        if (other instanceof Pair) {
            Pair otherPair = (Pair) other;
            return 
            ((  this.first == otherPair.first ||
                ( this.first != null && otherPair.first != null &&
                  this.first.equals(otherPair.first))) &&
             (  this.second == otherPair.second ||
                ( this.second != null && otherPair.second != null &&
                  this.second.equals(otherPair.second))) );
        }

        return false;
    }

    public String toString()
    { 
           return "(" + first + ", " + second + ")"; 
    }

    public A getFirst() {
        return first;
    }

    public void setFirst(A first) {
        this.first = first;
    }

    public B getSecond() {
        return second;
    }

    public void setSecond(B second) {
        this.second = second;
    }
}

class Solution {
	StringTokenizer tokenizer;
    BufferedReader in;
    static int NMAX = 300100;

    int a, b;
    Pair<Integer, Integer>[] tree;
    int[] m, bil;

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
    	m = new int[NMAX];
        bil = new int[NMAX];
        // tree = new int[NMAX*4];
        tree = (Pair<Integer, Integer>[]) Array.newInstance(Pair.class, 300010);
    }
 
    void solve() throws NumberFormatException, IOException {
    	while (true){
            int n,q;
            n = nextInt();
            if (n == 0) {
                System.exit(0);
            }
            q = nextInt();
            int x,cnt=1,p=0;
            x = nextInt();
            for (int i=1;i<n;i++)
            {
                int t;
                t = nextInt();
                if (t!=x)
                {
                    m[++p]=cnt;
                    bil[p] = x;
                    cnt=1;
                    x=t;
                }
                else
                    cnt++;
            }
            m[++p]=cnt;
            bil[p] = x;
            init(1,1,p);
            for (int i=1;i<=p;i++)
                m[i]+=m[i-1];
            for (int i=0;i<q;i++)
            {
                a = nextInt();
                b = nextInt();
                System.out.println(query(1,1,p).getSecond());
            }
        }
            
    }  

    void init(int nd, int x, int y)
    {
        if (x==y) {
            tree[nd] = new Pair(0, 0);
            tree[nd].setFirst(m[x]);
            tree[nd].setSecond(bil[x]);
        }
        else
        {
            int tng=(x+y)/2;
            init(2*nd,x,tng);
            init(2*nd+1,tng+1,y);
            tree[nd] = new Pair(0, 0);
            tree[nd].setFirst(Math.max(tree[2*nd].getFirst(),tree[2*nd+1].getFirst()));
            if (tree[2*nd].getFirst() < tree[2*nd+1].getFirst()) {
                tree[nd].setSecond(tree[2*nd+1].getSecond());
            } else {
                tree[nd].setSecond(tree[2*nd].getSecond());
            }
        }
    }

    Pair<Integer, Integer> query(int nd, int x, int y)
    {
        int pos1,pos2;
        pos1=m[x-1]+1;
        pos2=m[y];
        if (pos1>=a && pos2<=b)
            return tree[nd];
        if (pos1>b || pos2<a)
            return new Pair(-1,-1);
        if (x==y)
            if (pos1>=a && pos1<=b)
                return new Pair(b-Math.max(pos1,a)+1,bil[x]);
            else
                return new Pair(Math.min(pos2,b)-a+1,bil[x]);
        int tng=(x+y)/2;
        Pair<Integer, Integer> p1, p2;
        p1 = query(2*nd,x,tng);
        p2 = query(2*nd+1,tng+1,y);
        if (p1.getFirst() < p2.getFirst()) {
            return p2;
        } else {
            return p1;
        }
    }

	public static void main(String []args) {
		new Solution().run();
	}
}
