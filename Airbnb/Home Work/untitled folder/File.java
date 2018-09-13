package ab;
import java.util.*;
public class File {
	class Node {
		String name;
		int value;
		Map<String, Node> children;
		public Node(String name, int value) {
			this.name = name;
			this.value = value;
			children = new HashMap<>();
		}
	}
	Node root;
	public File() {
		this.root = new Node("root", -1);
	}
	
	public boolean create(String path, int value) {
		String[] p = path.split("/");
		Node cur = root;
		int index = 1;
		while (cur != null) {
			if (index < p.length - 1 && !cur.children.containsKey(p[index])) {
				return false;
			} else if (index == p.length - 1 && cur.children.containsKey(p[index])) {
				return false;
			}
			if (index == p.length - 1) {
				break;
			}
			cur = cur.children.get(p[index++]);
		}
		Node temp = new Node(p[p.length - 1], value);
		cur.children.put(p[p.length - 1], temp);
		return true;
	}
	
	public boolean set(String path, int value) {
		return true;
	}
	
	public Integer get(String path) {
		String[] p = path.split("/");
		Node cur = root;
		int index = 1;
		while (index < p.length && cur != null) {
			if (!cur.children.containsKey(p[index])) {
				return null;
			}
			cur = cur.children.get(p[index++]);
		}
		return cur.value;
	}
	
	public boolean watch()
	
	public static void main(String[] args) {
		File ff = new File();
		System.out.println(ff.create("/a", 1));
		System.out.println(ff.get("/a"));
		System.out.println(ff.create("/a/b", 2));
		System.out.println(ff.get("/a/b"));
		System.out.println(ff.create("/c/d", 1));
		System.out.println(ff.create("/a/c/d", 3));
		System.out.println(ff.create("/a/b", 2));
	}
}
