import java.util.*;
public class starry {
	static class Cluster {
		ArrayList<Star> stars;
		public Cluster(ArrayList<Star> stars0) {
			stars = scale(stars0);
			Collections.sort(stars);
		}
		public void print() {
			System.out.println(stars);
			System.out.println(hashCode());
		}
		private ArrayList<Star> scale(ArrayList<Star> stars0) {
			ArrayList<Star> ret = new ArrayList<Star>();
			int[] lowerdims = lowerdims(stars0);
			for (Star star: stars0) {
				ret.add(new Star(star.x-lowerdims[0], star.y-lowerdims[1]));
			}
			return ret;
		}
		private int[] lowerdims(ArrayList<Star> stars0) {
			int minx = Integer.MAX_VALUE;
			int miny = Integer.MAX_VALUE;
			for (Star star: stars0) {
				minx = Math.min(star.x, minx);
				miny = Math.min(star.y, miny);
			}
			int[] ret = {minx, miny};
			return ret;
		}
		private int[] dimensions() {
			int maxx = 0;
			int maxy = 0;
			for (Star star: stars) {
				maxx = Math.max(star.x, maxx);
				maxy = Math.max(star.y, maxx);
			}
			int[] ret = {maxx, maxy};
			return ret;
		}
		public ArrayList<Integer> configs() {
			ArrayList<Integer> configs = new ArrayList<Integer>();
			configs.add(stars.hashCode());
			int[] dimensions = dimensions();
			ArrayList<Star> tempstar = new ArrayList<Star>();
			//switch
			for (Star star: stars) {
				tempstar.add(new Star(star.y, star.x));
			}
			Collections.sort(tempstar);
			configs.add(scale(tempstar).hashCode());
			tempstar = new ArrayList<Star>();
			//y axis
			for (Star star: stars) {
				tempstar.add(new Star(star.x, dimensions[1]-star.y));
			}
			Collections.sort(tempstar);
			configs.add(scale(tempstar).hashCode());
			tempstar = new ArrayList<Star>();
			//x axis
			for (Star star: stars) {
				tempstar.add(new Star(dimensions[0]-star.x, star.y));
			}
			Collections.sort(tempstar);
			configs.add(scale(tempstar).hashCode());
			tempstar = new ArrayList<Star>();
			//switch, x axis
			for (Star star: stars) {
				tempstar.add(new Star(star.y,dimensions[0]-star.x));
			}
			Collections.sort(tempstar);
			configs.add(scale(tempstar).hashCode());
			tempstar = new ArrayList<Star>();
			//switch, y axis
			for (Star star: stars) {
				tempstar.add(new Star(dimensions[1]-star.y,star.x));
			}
			Collections.sort(tempstar);
			configs.add(scale(tempstar).hashCode());
			tempstar = new ArrayList<Star>();
			//x axis, y axis
			for (Star star: stars) {
				tempstar.add(new Star(dimensions[0]-star.x,dimensions[1]-star.y));
			}
			Collections.sort(tempstar);
			configs.add(scale(tempstar).hashCode());
			tempstar = new ArrayList<Star>();
			//switch, x axis, y axis
			for (Star star: stars) {
				tempstar.add(new Star(dimensions[1]-star.y, dimensions[0]-star.x));
			}
			Collections.sort(tempstar);
			configs.add(scale(tempstar).hashCode());
			Collections.sort(configs);
			return configs;
		}
		public int hashCode() {
			return this.configs().hashCode();
		}
		public boolean equals(Object o) {
			return this.hashCode()==o.hashCode();
		}
	}
	static class Star implements Comparable<Star>{
		int x;
		int y;
		public Star (int a, int b) {
			x=a;
			y=b;
		}
		public int hashCode() {
			return x*23+y;
		}
		public boolean equals(Object o) {
			if (o instanceof Star) return ((Star) o).x==this.x && ((Star)o).y==this.y;
			return false;
		}
		public int compareTo(Star o) {
			return this.hashCode()-o.hashCode();
		}
		public String toString() {
			return x+" "+y;
		}
	}
	public static void floodfill(int x, int y, char[][] sky, HashSet<Star> seen, ArrayList<Star> cluster) {
		//System.out.println("blah");
		if (seen.contains(new Star(x,y))) return;
		if (x<0 || y<0 || x>=sky.length || y>=sky[0].length) return;
		if (sky[x][y]!='*') return;
		seen.add(new Star(x,y));
		cluster.add(new Star(x,y));
		floodfill(x-1, y, sky, seen, cluster);
		floodfill(x-1, y-1, sky, seen, cluster);
		floodfill(x-1, y+1, sky, seen, cluster);
		floodfill(x, y-1, sky, seen, cluster);
		floodfill(x, y+1, sky, seen, cluster);
		floodfill(x+1, y-1, sky, seen, cluster);
		floodfill(x+1, y, sky, seen, cluster);
		floodfill(x+1, y+1, sky, seen, cluster);
	}
	public static void main (String[] args) {
		
		Scanner in = new Scanner (System.in);
		int N = in.nextInt();
		int M = in.nextInt();
		char[][] sky = new char[N][M];
		for (int i=0; i<N; i++) {
			sky[i] = in.next().toCharArray();
		}
		HashSet<Star> seen = new HashSet<Star>();
		HashSet<Cluster> log = new HashSet<Cluster>();
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				ArrayList<Star> cluster = new ArrayList<Star>();
				floodfill(i,j,sky,seen,cluster);
				Cluster c = new Cluster(cluster);
				if (cluster.size()!=0 && !log.contains(c))  {
					//System.out.println(i+" "+j);
					log.add(c);
					//c.print();
				}
			}
		}
		System.out.println(log.size());
		in.close();
	}
}
