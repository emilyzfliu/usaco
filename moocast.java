import java.util.*;
import java.io.*;

public class moocast {
	public static void print(int[][] a) {
		for (int[] i: a) print(i);
	}
	public static void print (int[] i) {
		for (int j: i) System.out.print(j+" ");
		System.out.println();
	}
	public static int mindex(int[] arr, boolean[]seen) {
		int ret = 0;
		int min = Integer.MAX_VALUE;
		for (int i=0; i<arr.length; i++) {
			if (seen[i]) continue;
			if (arr[i]<min) {
				ret = i;
				min = arr[i];
			}
		}
		return ret;
	}
	public static int max(int[] arr) {
		int ret = Integer.MIN_VALUE;
		for (int i: arr) ret = Math.max(ret, i);
		return ret;
	}
	public static void main (String[] args) throws IOException{
		//System.setIn(new BufferedInputStream(new FileInputStream("moocast.in")));
		//System.setOut(new PrintStream(new FileOutputStream("moocast.out")));
		Scanner in = new Scanner (System.in);
		int N = in.nextInt();
		int[][] locs =  new int[N][2];
		for (int i=0; i<N; i++) {
			locs[i][0] = in.nextInt();
			locs[i][1] = in.nextInt();
		}
		int[][] map = new int[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				map[i][j] = (int) (Math.pow(locs[i][0]-locs[j][0], 2)+Math.pow(locs[i][1]-locs[j][1], 2));
			}
		}
		print(map);
		int[] mins = new int[N];
		for (int i=1; i<N; i++) {
			mins[i] = Integer.MAX_VALUE;
		}
		boolean[] seen = new boolean[N];
		for (int i=1; i<N; i++) {
			int u = mindex(mins, seen);
			//System.out.println(u);
			seen[u] = true;
			for (int v=0; v<N; v++) {
				if (map[u][v]!=0 && !seen[v] && map[u][v]<mins[v]) {
					mins[v] = map[u][v];
				}
			}
		}
		print(mins);
		System.out.println(max(mins));
		in.close();
	}
}
