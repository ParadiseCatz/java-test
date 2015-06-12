import java.util.*;
import java.lang.reflect.Array;
import java.io.*;

public class Solution {
	StringTokenizer tokenizer;
    BufferedReader in;
    PrintWriter out;

    static public final int MAXK = 650;
    int _k_query = 0;
    String header;
    String[] a;
    int n, q;

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
        a = new String[30];
    }
 
    void solve() throws NumberFormatException, IOException {
    	// read headers
        // cin >> header;
        header = nextString();

        // read N and Q
        // cin >> n >> q;
        n = nextInt();
        q = nextInt();

        // a[i] = i-th alphabet
        for (int i = 0; i < 26; ++i) {
            a[i] = new String();
            a[i] += (char)((int)'A' + i);
        }

        Vector<Integer> c = new Vector<Integer>();
        if (header.charAt(1) == '1') {
            // only ABC
            c.add(0);
            c.add(1);
            c.add(2);
        } else if (header.charAt(3) == '3') {
            // all A-Z appear
            for (int i = 0; i < 26; ++i) {
                c.add(i);
            }
        } else {
            for (int i = 0; i < 26; ++i) {
                if (query("" + a[i])) {
                    c.add(i);
                }
            }
        }

        // determine the order of the characters
        if (c.size() > 1) {
            boolean[] used = new boolean[c.size()];
            used[0] = true;
            Vector<Integer> r, l;
            l = new Vector<Integer>();
            r = new Vector<Integer>();

            // determine r
            String sr = a[c.get(0)];
            while (true) {
                boolean cont = false;
                for (int i = 0; i < c.size(); ++i) {
                    if (used[i]) continue;
                    if (query(sr + a[c.get(i)])) {
                        r.add(c.get(i));
                        sr = a[c.get(i)];
                        cont = true;
                        used[i] = true;
                        break;
                    }
                }
                if (!cont) break;
            }


            // determine l
            String sl = a[c.get(0)];
            while (true) {
                boolean cont = false;
                for (int i = 0; i < c.size(); ++i) {
                    if (used[i]) continue;
                    if (query(a[c.get(i)] + sl)) {
                        l.add(c.get(i));
                        sl = a[c.get(i)];
                        cont = true;
                        used[i] = true;
                        break;
                    }
                }
                if (!cont) break;
            }

            // combine them
            Vector<Integer> temp = new Vector<Integer>();
            for (int i = l.size() - 1; i >= 0; --i) {
                temp.add(l.get(i));
            }
            
            temp.add(c.get(0));
            for (int i = 0; i < r.size(); ++i) {
                temp.add(r.get(i));
            }
            c = temp;
            
        }

        // do binary search to determine the number of each character
        int total = 0;
        String ans = new String();
        if (header.charAt(4) == '4') {
            for (int i = 0; i + 1 < c.size(); ++i) {
                ans += (a[c.get(i)].charAt(0));
                if (header.charAt(3) == '3') {
                    // always 1 character, so do nothing
                } else {
                    while (query(ans + a[c.get(i)].charAt(0))) {
                        ans += (a[c.get(i)].charAt(0));
                    }
                }
            }
            total = ans.length();
            for (int i = 0; i < n - total; ++i) {
                ans += (a[c.lastElement()].charAt(0));
            }
        } else {
            for (int i = 0; i + 1 < c.size(); ++i) {
                int l = 1, r = n - total;
                // for subtask 3: always 1
                if (header.charAt(3) == '3') {
                    r = 1;
                }
                while (l < r) {
                    int m = (l + r + 1) / 2;
                    String tempq = new String();
                    for (int j = 0; j < m; ++j) {
                        tempq += a[c.get(i)];
                    }
                    if (query(tempq)) {
                        l = m;
                    } else {
                        r = m - 1;
                    }
                }
                total += l;
                for (int j = 0; j < l; ++j) {
                    ans += (a[c.get(i)].charAt(0));
                }
            }
            for (int i = 0; i < n - total; ++i) {
                ans += (a[c.lastElement()].charAt(0));
            }
        }

        answer(ans);

    }

    boolean query(String s) throws NumberFormatException, IOException {
        if (++_k_query > MAXK) {
            System.exit(0);
        }

        // cout << "TANYA "<<s.c_str()<<endl;
        out.println("TANYA " + s);
        out.flush();

        String response;
        // cin >> response;
        response = nextString();
        // return strcmp(response, "YA") == 0;
        return response.equals("YA");
    }

    void answer(String s) {
        // cout <<"JAWAB "<<s.c_str()<<endl;
        out.println("JAWAB " + s);
        out.flush();

        System.exit(0);
    }

	public static void main(String []args) {
		new Solution().run();
	}
}
