import java.util.*;

public class traffic {
	public static void print(int[] a) {
		for (int i: a) System.out.print(i+" ");
		System.out.println();
	}
	public static void print(int[][] a) {
		for (int[] i: a) print(i);
		System.out.println();
	}
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
	public static int shortestpath(int[][] map, int start, int end, int[][] log){
		int N = map.length;
		boolean[] seen = new boolean[N];
		int[] dist = new int[N];
		int[] parent=  new int[N];
		ArrayList<Integer> path = new ArrayList<Integer>();
		for (int i=0; i<N; i++) {
			if (start==i) continue;
			dist[i] = Integer.MAX_VALUE;
		}
		for (int i=1; i<N; i++) {
			int u = mindex(dist, seen);
			if (u==-1) break;
			seen[u] = true;
			for (int v=0; v<N; v++) {
				int t = time(dist[u], u,v,log[u],log[v],map[u][v]);
				if (!seen[v] && map[u][v]!=-1 && dist[u]!=Integer.MAX_VALUE &&
						dist[v]>dist[u]+t && t!=0) {
					dist[v] = dist[u] +t;
					parent[v] = u;
				}
			}
			//System.out.println("map: ");
			//print(map);
		}
		//print(dist);
		
		int index = end;
		path.add(0, index);
		while (parent[index] !=0) {
			index = parent[index];
			path.add(0, index);
		}
		path.add(0,0);
		
		//for (int i: path) System.out.print(i+" ");
		//System.out.println();
		if (dist[end]==Integer.MAX_VALUE) dist[end] = 0;
		return dist[end];
	}
	private static int time (int current, int u, int v, int[] logu, int[] logv, int weight) {
		if (logu[0]!=logv[0] && logu[1]==logv[1] && logu[2] == logv[2] &&
				logu[2]==logu[3] && logv[2]==logv[3] || weight<0) {
			//System.out.println("invalid path 1");
			return 0;
		}
		if (logu[0]!=logv[0] && logu[1]==logv[1] && logu[3] == logv[2] &&
				logu[2]==logv[3]) {
			//System.out.println("invalid path 2");
			return 0;
		}
		//System.out.println("NEW PATH: "+u+" "+v);
		//System.out.println("current: "+current);
		int[] tempu = new int[2];
		int[] tempv = new int[2];
		tempu[0] = logu[0];
		tempv[0] = logv[0];
		
		
		tempu[1] = logu[1] - current;
		tempv[1] = logv[1] - current;
		while (tempu[1]<=0) {
			tempu[1] += logu[2+tempu[0]];
			tempu[0] = 1-tempu[0];
		}
		while (tempv[1]<=0) {
			tempv[1] += logv[2+tempv[0]];
			tempv[0] = 1-tempv[0];
		}
		/*
		System.out.println("weight: "+weight);
		System.out.println("min: "+Math.min(logv[2+tempv[0]], logu[2+tempu[0]]));
		*/
		if (tempu[0]==tempv[0])  {
			//System.out.println(weight);
			return weight;
		}
		int elapsed = 0;
		int count = 0;
		while (tempu[1]==tempv[1] && count<10) {
			elapsed+=tempu[1];
			tempu[1] = logu[2+tempu[0]];
			tempu[0] = 1-tempu[0];
			tempv[1] = logv[2+tempv[0]];
			tempv[0] = 1-tempv[0];
			count++;
		}
		/*System.out.println("adjusted");
		print(tempu);
		print(tempv);*/
		//System.out.println(weight+ elapsed+Math.min(tempv[1], tempu[1]));
		return weight+ elapsed+Math.min(tempv[1], tempu[1]);
	}
	public static void main (String[] args) {
		Scanner in = new Scanner (System.in);
		int start = in.nextInt()-1;
		int end = in.nextInt()-1;
		int N = in.nextInt();
		int M = in.nextInt();
		int[][] map = new int[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) map[i][j] = -1;
		}
		int[][] log = new int[N][4];
		for (int i=0; i<N; i++) {
			int a = 0;
			if (in.next().equals("B")) a=1;
			int[] temp = {a, in.nextInt(), in.nextInt(), in.nextInt()};
			log[i] =temp;
		}
		for (int i=0; i<M; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			map[a-1][b-1] = in.nextInt();
			map[b-1][a-1] = map[a-1][b-1];
		}
		//System.out.println("map: ");
		//print(map);
		//System.out.println("log: ");
		//print(log);
		System.out.println(shortestpath(map, start, end, log));
		in.close();
	}
}
