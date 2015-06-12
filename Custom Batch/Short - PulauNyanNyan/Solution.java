import java.util.*;
import java.lang.reflect.Array;
import java.io.*;

public class Solution {
	StringTokenizer tokenizer;
    BufferedReader in;

    int n, a, b, c, d, e, f, g, h, i;
    char[][] ch;
    int[] steps;;
    int[][] peta,peta2;
    int[][][] trans;

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
    	steps = new int[10];
    	ch = new char[3][3];
    	peta = peta2 = new int[3][3];
    	trans = new int[][][] {
	    	{{1,1,0},{1,1,0},{0,0,0}},
			{{1,1,1},{0,0,0},{0,0,0}},
			{{0,1,1},{0,1,1},{0,0,0}},
			{{1,0,0},{1,0,0},{1,0,0}},
			{{0,1,0},{1,1,1},{0,1,0}},
			{{0,0,1},{0,0,1},{0,0,1}},
			{{0,0,0},{1,1,0},{1,1,0}},
			{{0,0,0},{0,0,0},{1,1,1}},
			{{0,0,0},{0,1,1},{0,1,1}}
		};
    }
 
    void solve() throws NumberFormatException, IOException {
    	n = nextInt();
    	for (int tc = 0 ; tc < n ; ++tc) {
	    	for (int i = 0; i < 3; ++i) {
	    		String x = nextString();
	    		ch[i] = x.toCharArray();
	    	}
	    	for (int i = 0; i<3 ; ++i) {
	    		for (int j = 0; j<3 ; ++j) {
	    			peta[i][j] = ubah(ch[i][j]);
	    		}
	    	}
	    	boolean sama = true;

	    	loop: for(steps[0] = 0; steps[0] < 4; steps[0]++){
	            for(steps[1] = 0; steps[1] < 4; steps[1]++){
	                for(steps[2] = 0; steps[2] < 4; steps[2]++){
	                    for(steps[3] = 0; steps[3] < 4; steps[3]++){
	                        for(steps[4] = 0; steps[4] < 4; steps[4]++){
	                            for(steps[5] = 0; steps[5] < 4; steps[5]++){
	                                for(steps[6] = 0; steps[6] < 4; steps[6]++){
	                                    for(steps[7] = 0; steps[7] < 4; steps[7]++){
	                                        for(steps[8] = 0; steps[8] < 4; steps[8]++){

	                                        	peta2 = new int[3][3];
	                                            madd(0,steps[0]);
	                                            madd(1,steps[1]);
	                                            madd(2,steps[2]);
	                                            madd(3,steps[3]);
	                                            madd(4,steps[4]);
	                                            madd(5,steps[5]);
	                                            madd(6,steps[6]);
	                                            madd(7,steps[7]);
	                                            madd(8,steps[8]);
	                                             
	                                            sama = true;
	                                            check: for(int i = 0; i<3; i++){
	                                                for(int j = 0; j<3; j++){
	                                                    if(peta[i][j]!=peta2[i][j]){
	                                                        sama = false;
	                                                        break check;
	                                                    }
	                                                }
	                                            }
	                                            if (sama) { break loop;}
	                                        }
	                                    }
	                                }
	                            }
	                        }
	                    }
	                }
	            }
	        }
            for(int i = 0; i<9; i++){
	            while(steps[i]>0){
	                System.out.print(i+1);
	                steps[i]--;
	            }
	        }
	        System.out.println();
		}
    }

    int ubah(char x) {
		if (x == 's')
			return 0;
		if (x == 'p')
			return 3;
		if (x == 'g')
			return 2;
		if (x == 'd')
			return 1;
		return -1;
	}

	void madd( int b, int val){
	    for(int i = 0; i<3; i++){
	        for(int j = 0; j<3; j++){
	            peta2[i][j] += val * trans[b][i][j];
	            peta2[i][j] = peta2[i][j]%4;
	        }
	    }
	}

	public static void main(String []args) {
		new Solution().run();
	}
} 	