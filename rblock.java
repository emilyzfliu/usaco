import java.util.*;

public class rblock {
	private static int mindex(int[] arr, boolean[] seen) {
		int min = Integer.MAX_VALUE;
		int mindex = -1;
		for (int i=0; i<arr.length; i++) {
			if (seen[i]) continue;
			if (arr[i]<min) {
				min = arr[i];
				mindex = i;
			}
		}
		return mindex;
	}
	public static ArrayList<Integer> logshortestpath(int[][] map) {
		int N = map.length;
		int[] dist = new int[N];
		int[] parent = new int[N];
		boolean[] seen = new boolean[N];
		ArrayList<Integer> log = new ArrayList<Integer>();
		for (int i=1; i<N; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		for (int i=1; i<N; i++) {
			int u = mindex(dist, seen);
			seen[u] = true;
			for (int v=0; v<N; v++) {
				if (!seen[v] && map[u][v]!=0 && dist[u]!=Integer.MAX_VALUE
						&& dist[v]>dist[u]+map[u][v]) {
					dist[v] = dist[u]+map[u][v];
					parent[v] = u;
				}
			}
		}
		int index = N-1;
		log.add(0, index);
		while (parent[index] !=0) {
			index = parent[index];
			log.add(0, index);
		}
		log.add(dist[N-1]);
		log.add(0,0);
		return log;
	}
	public static int shortestpath(int[][] map) {
		int N = map.length;
		int[] dist = new int[N];
		boolean[] seen = new boolean[N];
		for (int i=1; i<N; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		for (int i=1; i<N; i++) {
			int u = mindex(dist, seen);
			seen[u] = true;
			for (int v=0; v<N; v++) {
				if (!seen[v] && map[u][v]!=0 && dist[u]!=Integer.MAX_VALUE
						&& dist[v]>dist[u]+map[u][v]) {
					dist[v] = dist[u]+map[u][v];
				}
			}
		}
		return dist[N-1];
	}
	public static void main (String[] args) {
		Scanner in = new Scanner (System.in);
		int N = in.nextInt();
		int M = in.nextInt();
		int[][] map = new int [N][N];
		for (int i=0; i<M; i++) {
			int a= in.nextInt(); int b = in.nextInt();
			map[a-1][b-1] = in.nextInt();
			map[b-1][a-1] = map[a-1][b-1];
		}
		ArrayList<Integer> path = logshortestpath(map);
		int max = 0;
		for (int i=0; i<path.size()-2; i++) {
			int a = path.get(i);
			int b = path.get(i+1);
			map[a][b]*=2;
			map[b][a]*=2;
			max = Math.max(shortestpath(map) - path.get(path.size()-1), max);
			map[a][b]/=2;
			map[b][a]/=2;
		}
		System.out.println(max);
		in.close();
	}
}
