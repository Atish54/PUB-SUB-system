import java.util.ArrayList;
import java.util.HashSet;

public class pubsub implements java.io.Serializable {
	int id;
	String name;
	HashSet category;
	HashSet category_post;
	String password;
	static int count = 1010;
	HashSet subscribed;
	HashSet subscribers;
	long number;
	String email;
	String gender;
	String dob;

	public pubsub(int id, String name, HashSet category, HashSet category_post, String password, HashSet subscribed,
			HashSet subscribers, long number, String email, String gender, String dob) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.category_post = category_post;
		this.password = password;
		this.subscribed = subscribed;
		this.subscribers = subscribers;
		this.number = number;
		this.email = email;
		this.gender = gender;
		this.dob = dob;
	}

	public pubsub(String name, String password, long number, String email, String gender, String dob) {
		super();
		count++;
		category = new HashSet();
		category_post = new HashSet();
		subscribed = new HashSet();
		subscribers = new HashSet();
		this.id = count;
		this.name = name;
		this.password = password;
		this.number = number;
		this.email = email;
		this.gender = gender;
		this.dob = dob;
	}

	public HashSet getCategory_post() {
		return category_post;
	}

	public void setCategory_post(int catpost) {
		if (this.category_post.contains(catpost)) {
		} else {
			this.category_post.add(catpost);
		}
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public pubsub() {
		subscribed = new HashSet();
		subscribers = new HashSet();
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

	public HashSet getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category.add(category);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		pubsub.count = count;
	}

	public HashSet getsubscribed() {
		return subscribed;
	}

	public void setsubscribed(HashSet subscribed) {
		this.subscribed = subscribed;
	}

	public HashSet getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(HashSet subscribers) {
		this.subscribers = subscribers;
	}

}
