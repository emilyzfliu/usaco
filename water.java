import java.util.*;
public class water {
	public static void print(int[] arr) {
		for (int a: arr) {
			System.out.print(a+" ");
		}
		System.out.println();
	}
	public static int mindex(int[] arr, boolean[] seen) {
		int min = Integer.MAX_VALUE;
		int mindex = 0;
		for (int i=0; i<arr.length; i++){
			if (seen[i]==true) continue;
			if (min>arr[i]) {
				mindex = i;
				min = arr[i];
			}
		}
		return mindex;
	}
	public static int sum(int[] a) {
		int sum = 0;
		for (int i: a) sum+=i;
		return sum;
	}
	public static void main (String[] args) {
		Scanner in = new Scanner (System.in);
		int N = in.nextInt();
		int[][] map = new int[N+1][N+1];
		for (int i=1; i<=N; i++) {
			map[0][i] = in.nextInt();
			map[i][0] = map[0][i];
		}
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				map[i][j] = in.nextInt();
			}
		}
		int[] mins = new int[N+1];
		for (int i=1; i<N+1; i++) {
			mins[i] = Integer.MAX_VALUE;
		}
		boolean[] seen = new boolean[N+1];
		for (int i=1; i<N+1; i++) {
			int u = mindex(mins, seen);
			seen[u] = true;
			for (int v = 0; v < N+1; v++){
                if (map[u][v]!=0 && seen[v] == false &&
                    map[u][v] <  mins[v])
                {
                    mins[v] = map[u][v];
                }
			}
		}
		System.out.println(sum(mins));
		in.close();
	}
}
