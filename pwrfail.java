import java.util.*;

public class pwrfail {
	static class Pole {
		int x;
		int y;
		public Pole(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static double dist(Pole a, Pole b) {
		if (a.x==b.x && a.y==b.y) return Integer.MAX_VALUE;
		return Math.sqrt(Math.pow(b.x-a.x,2)+Math.pow(b.y-a.y, 2));
	}
	public static void print(double[][] a) {
		for (double[] b: a) {
			print(b);
		}
	}
	public static void print(double[] a) {
		for (double b: a) {
			System.out.print(b+" ");
		}
		System.out.println();
	}
	public static int mincostdex(double[] cost, boolean[] connected) {
		double min = Integer.MAX_VALUE;
		int mindex = -1;
		for (int i=0; i<cost.length; i++) {
			if (connected[i]) continue;
			if (cost[i]<=min) {
				min = cost[i];
				mindex = i;
			}
		}
		return mindex;
	}
	public static void main (String[] args) {
		Scanner in = new Scanner (System.in);
		int N = in.nextInt();
		int W = in.nextInt();
		Pole[] poles = new Pole[N];
		boolean[] connected = new boolean[N];
		double max = in.nextDouble();
		for (int i=0; i<N; i++) {
			poles[i] = new Pole(in.nextInt(), in.nextInt());
		}
		double[][] map = new double[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				map[i][j] = dist(poles[i], poles[j]);
				if (map[i][j]>max) map[i][j] = Integer.MAX_VALUE;
				map[j][i] = map[i][j];
			}
		}
		for (int i=0; i<W; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			map[a-1][b-1] = 0;
			map[b-1][a-1] = 0;
		}
		double[] cost = new double[N];
		for (int i=1; i<N; i++) {
			cost[i] = Integer.MAX_VALUE;
		}
		for (int i=1; i<N; i++) {
			int u = mincostdex(cost, connected);
			connected[u] = true;
			for (int v=0; v<N; v++) {
				if (!connected[v] && cost[u]!=Integer.MAX_VALUE && cost[v]>cost[u]+map[u][v]) {
					cost[v] = cost[u]+map[u][v];
				}
			}
		}
		if (cost[N-1]==Integer.MAX_VALUE) cost[N-1] = -0.001;
		System.out.println((int)(1000*cost[N-1]));
		in.close();
	}
}
