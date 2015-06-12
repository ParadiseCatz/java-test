import java.util.*;

public class Solution {
   
   public static void main(String []args) {
		int	n;
		int[][] m = new int[105][105];

		Scanner scanIn = new Scanner(System.in);
		n = scanIn.nextInt();
		for (int i = 0; i<n ; i++) {
			for (int j = 0 ; j<n ; j++) {
				m[i][j] = scanIn.nextInt();
			}
		}

		boolean[] flag = new boolean[105];

		for (int i = 0; i<n ; ++i) {
			flag[i] = true;
		}

		for (int i = 0; i<n ; i++) {
			for (int j = 0 ; j<n ; j++) {
				if (m[i][j] == 1) {
					flag[i] = false;
				}
				if (m[i][j] == 2) {
					flag[j] = false;
				}
				if (m[i][j] == 3) {
					flag[i] = false;
					flag[j] = false;
				}
			}
		}

		ArrayList<Integer> ans = new ArrayList<Integer>();  

		for (int i = 0; i<n ; ++i) {
			if (flag[i]) {
				ans.add(i+1);
			}
		}
		System.out.println(ans.size());
		Optional<String> reduced =
		    ans
		        .stream()
		        .map(Object::toString)
		        .reduce((s1, s2) -> s1 + " " + s2);

		reduced.ifPresent(System.out::println);

   }
} 
