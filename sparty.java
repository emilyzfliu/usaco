import java.util.*;

public class sparty {
	private static int mindex(int[] arr, boolean[] seen) {
		int min = Integer.MAX_VALUE;
		int mindex = -1;
		for (int i=0; i<arr.length; i++) {
			if (seen[i]) continue;
			if (arr[i]<=min) {
				min = arr[i];
				mindex = i;
			}
		}
		return mindex;
	}
	public static int[] minpath(int[][] map, int start) {
		//System.out.println("executing minpath with "+start);
		int N = map.length;
		int[] dist = new int[N];
		int[] parent = new int[N];
		boolean[] seen = new boolean[N];
		for (int i=0; i<N; i++) {
			if (i==start) continue;
			dist[i] = Integer.MAX_VALUE;
		}
		for (int i=1; i<N; i++) {
			int u = mindex(dist, seen);
			seen[u] = true;
			for (int v=0; v<N; v++) {
				if (!seen[v] && map[u][v]!=0 && dist[u]!=Integer.MAX_VALUE && 
						dist[v]>dist[u]+map[u][v]) {
					dist[v] = dist[u]+map[u][v];
					parent[v] = u;
					//System.out.println(v+" "+dist[v]);
				}
			}
		}
		//print(dist);
		return dist;
	}
	public static void print(int[] a) {
		for (int i: a) System.out.print(i+" ");
		System.out.println();
	}
	public static void print(ArrayList<Integer> a) {
		for (int i: a) System.out.print(i+" ");
		System.out.println();
	}
	public static void print(int[][] a) {
		for (int[] i: a) print(i);
	}
	public static void main (String [] args) {
		Scanner in = new Scanner (System.in);
		int N = in.nextInt();
		int M = in.nextInt();
		int X = in.nextInt();
		int[][] map = new int[N][N];
		for (int i=0; i<M; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			map[a-1][b-1] = in.nextInt();
		}
		//print(map);
		int[] to = new int[N];
		for (int i=0; i<N; i++) {
			to[i] = minpath(map,i)[X-1];
		}
		int[] from = minpath(map,X-1);
		int max = Integer.MIN_VALUE;
		for (int i=0; i<N; i++) {
			max = Math.max(to[i]+from[i], max);
		}
		System.out.println(max);
		in.close();
	}
}
