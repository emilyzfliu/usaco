import java.util.*;

public class superbull {
	public static void print(long[] a) {
		for (long i:a) System.out.print(i+" ");
		System.out.println();
	}
	public static void print(long[][] a) {
		for (long[] i: a) print(i);
	}
	public static int maxdex(long[] arr, boolean[] seen) {
		long max = Integer.MIN_VALUE;
		int maxdex = 0;
		for (int i=0; i<arr.length; i++) {
			if (seen[i]==true) continue;
			if (arr[i]>max) {
				maxdex = i;
				max = arr[i];
			}
		}
		return maxdex;
	}
	public static long sum(long[] a) {
		long sum = 0;
		for (long i: a) sum+=i;
		return sum;
	}
	public static void main (String [] args) {
		Scanner in = new Scanner (System.in);
		int N = in.nextInt();
		long[] teams = new long[N];
		long[][] map = new long[N][N];
		for (int i=0; i<N; i++) {
			teams[i] = in.nextInt();
		}
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				map[i][j] = teams[i]^teams[j];
			}
		}
		long[] maxes = new long[N];
		boolean[] seen = new boolean[N];
		for (int i=1; i<N; i++) {
			maxes[i] = Integer.MIN_VALUE;
		}
		for (int i=1; i<N; i++) {
			int u = maxdex(maxes, seen);
			seen[u] = true;
			for (int v = 0; v < N; v++){
                if (map[u][v]!=0 && seen[v] == false &&
                    map[u][v] >  maxes[v])
                {
                    maxes[v] = map[u][v];
                }
			}
		}
		System.out.println(sum(maxes));
		in.close();
	}
}
