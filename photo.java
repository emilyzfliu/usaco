import java.util.*;

public class photo {
	static class Cow {
		int id;
		public Cow(int identification) {
			id = identification;
		}
		public boolean equals(Object c) {
			if (c instanceof Cow) {
				return this.id==((Cow)c).id;
			}
			return false;
		}
		public int hashCode() {
			return id;
		}
		public String toString() {
			String ret = "";
			return ret+id;
		}
	}
	static class Photo {
		HashMap<Cow, Integer> set;
		public Photo(HashMap<Cow, Integer> set) {
			this.set = set;
		}
		public int indexOf (Cow cow) {
			return set.get(cow);
		}
	}
	static class Pair {
		Cow a;
		Cow b;
		public Pair (Cow c, Cow d) {
			a=c;
			b=d;
		}
		public boolean equals(Object p) {
			if (p instanceof Pair) return this.a.equals(((Pair)p).a) && this.b.equals(((Pair)p).b);
			return false;
		}
		public int hashCode() {
			return 37*a.id+b.id;
		}
		public String toString() {
			return a+" "+b;
		}
	}
	public static void print(Cow[][] c) {
		for (Cow[] b: c)print(b);
	}
	public static void print(Cow[] b) {
		for (Cow a: b) System.out.print(a+" ");
		System.out.println();
	}
	public static void print(Photo[] b) {
		for (Photo a: b) System.out.println(a);
		System.out.println();
	}
	public static int compareCows (Pair pair, Photo[] photos) {
		if (pair.a.equals(pair.b))return 0;
		int count = 0;
		for (int i=0; i<5; i++) {
			if (photos[i].indexOf(pair.a)<photos[i].indexOf(pair.b)) count++;
		}
		if (count>=3) return -1;
		return 1;
	}
	public static void main (String[] args) {
		Scanner in = new Scanner (System.in);
		int N = in.nextInt();
		final Photo[] photos = new Photo[5];
		for (int i=0; i<5; i++) {
			HashMap<Cow, Integer> temp = new HashMap<Cow, Integer>();
			for (int j=0; j<N; j++) {
				temp.put(new Cow(in.nextInt()), j);
			}
			photos[i] = new Photo(temp);
		}
		//print(photos);
		Cow[] cows = new Cow[N];
		int k=0;
		for (Cow c: photos[0].set.keySet()) {
			cows[k] = c;
			k++;
		}
		Arrays.sort(cows, new Comparator<Cow>(){
			public int compare (Cow a, Cow b) {
				Pair pair = new Pair(a, b);
				return compareCows(pair, photos);
			}
		});
		
		for (Cow c: cows) {
			System.out.println(c);
		}
		in.close();
	}
}
