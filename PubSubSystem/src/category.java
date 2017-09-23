import java.util.HashMap;

public class category implements java.io.Serializable {
	int id;
	String name;
	int count = 0;

	public category() {
	}

	public category(int id, String name, int count) {
		super();
		this.id = id;
		this.name = name;
		this.count = count;
	}

	public HashMap add(HashMap cat) {
		category c1 = new category(1, "Music", 0);
		category c2 = new category(2, "Fashion", 0);
		category c3 = new category(3, "Food", 0);
		category c4 = new category(4, "Sports", 0);
		category c5 = new category(5, "Movies", 0);
		category c6 = new category(6, "Politics", 0);
		category c7 = new category(7, "Health", 0);
		category c8 = new category(8, "Technology", 0);
		category c9 = new category(9, "Science", 0);
		category c10 = new category(10, "Career", 0);
		category c11 = new category(11, "Celebrity Gossips", 0);
		category c12 = new category(12, "Business", 0);
		category c13 = new category(13, "Car", 0);
		category c14 = new category(14, "Gadget", 0);
		category c15 = new category(15, "IT", 0);
		category c16 = new category(16, "Other", 0);
		cat.put(c1.getId(), c1);
		cat.put(c2.getId(), c2);
		cat.put(c3.getId(), c3);
		cat.put(c4.getId(), c4);
		cat.put(c5.getId(), c5);
		cat.put(c6.getId(), c6);
		cat.put(c7.getId(), c7);
		cat.put(c8.getId(), c8);
		cat.put(c9.getId(), c9);
		cat.put(c10.getId(), c10);
		cat.put(c11.getId(), c11);
		cat.put(c12.getId(), c12);
		cat.put(c13.getId(), c13);
		cat.put(c14.getId(), c14);
		cat.put(c15.getId(), c15);
		cat.put(c16.getId(), c16);
		return cat;
	}

	public void increment_count() {
		count = count + 1;
	}

	public void decrement_count() {
		count = count - 1;
	}

	public int getcount() {
		return count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
