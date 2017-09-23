import java.io.Console;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class pubsub_child {
	Scanner sc = new Scanner(System.in);
	ArrayList a;
	file f = new file();

	public void home(int id, HashMap pub, HashMap sub, HashMap cat, HashMap post_sub, HashMap post_cat) {
		while (true) {
			System.out.println(
					"1)See Timeline\n2)Add/View Post\n3)Add/View/Delete Publisher\n4)Add/View/Delete Categories\n5)List All Subscribers\n6)List All Subscribed\n7)Update Details\n8)Exit");
			System.out.println("Enter Choice..");
			int ch = sc.nextInt();
			switch (ch) {
			case 1:
				wall(id, pub, sub, cat, post_sub, post_cat);
				break;
			case 2:
				add_view_post(id, pub, sub, cat, post_sub, post_cat);
				break;
			case 3:
				add_view_delete_pub(id, pub, sub, cat, post_sub, post_cat);
				break;
			case 4:
				add_view_delete_cat(id, pub, sub, cat, post_sub, post_cat);
				break;
			case 5:
				show_subscription(id, pub, sub, cat, post_sub, post_cat);
				break;
			case 6:
				show_subscribed(id, pub, sub, cat, post_sub, post_cat);
				break;
			case 7:
				System.out.println("***************Update Menu**************");
				while (true) {
					System.out.println("1.View Your Details");
					System.out.println("2.Change Your Details");
					System.out.println("3.Back To Main Menu");
					int choice = sc.nextInt();
					switch (choice) {
					case 1:
						System.out.println("View Details");
						viewDetail((pubsub) sub.get(id));
						break;
					case 2:
						while (true) {
							System.out.println("Which Detail You Want To Change?");
							System.out.println("1.Change In Name");
							System.out.println("2.Change In Password");
							System.out.println("3.Change In Email");
							System.out.println("4.Change In Mobile");
							System.out.println("5.Back To Previous Menu");
							int key = sc.nextInt();
							switch (key) {
							case 1:
								sc.nextLine();
								System.out.println("Enter Updated Name");
								String newname = sc.nextLine();
								((pubsub) sub.get(id)).name = newname;
								f.backup(pub, sub, cat, post_sub, post_cat);
								System.out.println("Your Name Has Been Successfully Changed.");
								break;
							case 2:
								System.out.println("Enter Current Password");
								String pwd1 = sc.next();
								if (!((pubsub) sub.get(id)).password.equals(pwd1)) {
									System.out.println("Your Current Password Is Incorrect.");
									break;
								}
								System.out.println("Enter New Password");
								String newpwd = sc.next();
								((pubsub) sub.get(id)).password = newpwd;
								f.backup(pub, sub, cat, post_sub, post_cat);
								System.out.println("Your Password Has Been Successfully Changed.");
								break;
							case 3:
								System.out.println("Enter New Email");
								String email = sc.next();
								((pubsub) sub.get(id)).email = email;
								f.backup(pub, sub, cat, post_sub, post_cat);
								System.out.println("Your E-Mail Has Been Successfully Changed.");
								break;
							case 4:
								System.out.println("Enter New Number");
								Long mobilNumber = sc.nextLong();
								((pubsub) sub.get(id)).number = mobilNumber;
								f.backup(pub, sub, cat, post_sub, post_cat);
								System.out.println("Your Mobile No Has Been Successfully Changed.");
								break;

							default:
								if (key != 5)
									System.out.println("Invalid choice");
								break;
							}
							if (key == 5)
								break;
						}
						break;

					default:
						if (choice != 3)
							System.out.println("Invalid choice");
						break;
					}
					if (choice == 3)
						break;
				}
				break;
			default:
				return;
			}

		}
	}

	public void show_subscribed(int id, HashMap pub, HashMap sub, HashMap cat, HashMap post_sub, HashMap post_cat) {
		pubsub ps = (pubsub) sub.get(id);
		HashSet subscrided = ps.getsubscribed();
		Iterator it = subscrided.iterator();
		while (it.hasNext()) {
			pubsub publisher = (pubsub) sub.get(it.next());
			System.out.println("ID : " + publisher.getId());
			System.out.println("Name : " + publisher.getName());
			System.out.println("No. Of Subscribers : " + publisher.subscribers.size());
			LinkedList l = (LinkedList) post_sub.get(publisher.getId());
			System.out.println("No. Of Posts : " + l.size());
			System.out.println("-------------------------------------------");
		}

		if (subscrided.size() == 0)
			System.out.println("No Subscription");
		System.out.println("\nCategory Subscribed...\n");
		HashSet category = ps.getCategory();
		Iterator it1 = category.iterator();
		while (it1.hasNext()) {
			int catid = (Integer) it1.next();
			category cate = (category) cat.get(catid);
			String name = cate.getName();
			System.out.println(name);
		}
		if (category.size() == 0) {
			System.out.println("No Category Subscribed...");
		}
	}

	public void show_subscription(int id, HashMap pub, HashMap sub, HashMap cat, HashMap post_sub, HashMap post_cat) {
		pubsub ps = (pubsub) sub.get(id);
		HashSet subscriber = ps.getSubscribers();
		System.out.println(subscriber.size());
		Iterator it = subscriber.iterator();
		while (it.hasNext()) {
			pubsub publisher = (pubsub) sub.get(it.next());
			System.out.println("ID : " + publisher.getId());
			System.out.println("Name : " + publisher.getName());
			System.out.println("No. Of Subscribers : " + publisher.subscribers.size());
			LinkedList l = (LinkedList) post_sub.get(publisher.getId());
			if (l == null) {
				System.out.println("No. Of Posts : 0");
			} else {
				System.out.println("No. Of Posts : " + l.size());
			}

			System.out.println("-------------------------------------------");
		}
		if (subscriber.size() == 0)
			System.out.println("No Subscribers");
	}

	public void wall(int id, HashMap pub, HashMap sub, HashMap cat, HashMap post_sub, HashMap post_cat) {
		TreeMap posts = new TreeMap();
		pubsub ps = (pubsub) sub.get(id);
		HashSet hs = ps.getCategory();
		Iterator next = hs.iterator();
		while (next.hasNext()) {
			int catid = (Integer) next.next();
			LinkedList ll = (LinkedList) post_cat.get(catid);
			for (Object obj : ll) {
				post p = (post) obj;
				posts.put(p.getId(), p);

			}

		}
		if (post_sub.containsKey(id)) {
			LinkedList ll = (LinkedList) post_sub.get(id);
			for (Object obj : ll) {
				post p = (post) obj;
		posts.put(p.getId(), p);
			}
		}
		HashSet hs1 = ps.getsubscribed();

		Iterator next1 = hs1.iterator();
		while (next1.hasNext()) {
			int pubid = (Integer) next1.next();
			// System.out.println(pubid);
			LinkedList ll = (LinkedList) post_sub.get(pubid);
			for (Object obj : ll) {
				post p = (post) obj;
				posts.put(p.getId(), p);
			}

		}
		for (Object o : posts.keySet()) {
			post p = (post) posts.get(o);
			System.out.println("Posted by," + p.getPub_name() + " on " + p.getTime());
			System.out.println(p.getContent());
			category category = (category) cat.get(p.getCat_id());
			System.out.println("Category : " + category.getName().toString());
			System.out.println("-----------------------------------------------");

		}
		if (posts.isEmpty()) {
			System.out.println("No Subscription....");
		}
	}

	public void get_all_post_by_publisher(LinkedList a, HashMap cat) {
		for (Object obj : a) {
			post p = (post) obj;
			System.out.println("Posted by," + p.getPub_name() + " on " + p.getTime());
			System.out.println(p.getContent());
			category category = (category) cat.get(p.getCat_id());
			System.out.println("Category : " + category.getName().toString());
			System.out.println("-----------------------------------------------");
		}

	}

	public void view_publisher(int id, HashMap pub, HashMap sub, HashMap cat, HashMap post_sub, HashMap post_cat,
			int pubid) {
		pubsub publisher = (pubsub) pub.get(pubid);

		System.out.println("ID : " + publisher.getId());
		System.out.println("Name : " + publisher.getName());
		System.out.println("No. Of Subscribers : " + publisher.subscribers.size());
		LinkedList l = (LinkedList) post_sub.get(publisher.getId());
		System.out.println("No. Of Posts : " + l.size());
		HashSet a = publisher.category_post;
		Iterator next = a.iterator();
		String s = "";
		while (next.hasNext()) {
			int q = (Integer) next.next();
			category ca=(category) cat.get(q);
			s = s + " " + ca.getName();
		}

		System.out.println("Categories of Post : " + s);

		pubsub subscriber = (pubsub) sub.get(id);
		pubsub subscriber1 = (pubsub) sub.get(pubid);
		HashSet h = subscriber.getsubscribed();
		HashSet h2 = subscriber1.getSubscribers();
		HashSet h1 = publisher.getSubscribers();
		boolean choice = true;
		while (choice) {
			System.out.println("1)View Publisher's Post\n2)Subscribe Publisher\n3)Un-Subscribe Publisher\n4)Back");
			System.out.println("Enter Choice..");
			int ch = sc.nextInt();
			switch (ch) {
			case 1:
				LinkedList posts = (LinkedList) post_sub.get(publisher.getId());
				if (posts.size() == 0) {
					System.out.println("No Posts Yet...");
				} else {
					get_all_post_by_publisher(posts, cat);
				}
				break;
			case 2:

				if (h.contains(pubid)) {
					System.out.println("Its Already Subscribed!");
				} else {
					h.add(pubid);

					h1.add(id);
					h2.add(id);
					System.out.println("Subscription Successfull...");
					return;
				}

				break;
			case 3:
				if (!h.contains(pubid)) {
					System.out.println("You Haven't Subscribed!");
				} else {
					h.remove(pubid);
					h1.remove(id);
					h2.remove(id);
					System.out.println("Successfully Un-Subscribed....");
					return;
				}
				break;
			default:
				choice = false;
			}
		}
		f.backup(pub, sub, cat, post_sub, post_cat);
	}

	public void view_all_publisher(int id, HashMap pub, HashMap sub, HashMap cat, HashMap post_sub, HashMap post_cat) {
		for (Object o : pub.keySet()) {
			pubsub publisher = (pubsub) pub.get(o);

			System.out.println("ID : " + publisher.getId());
			System.out.println("Name : " + publisher.getName());
			System.out.println("No. Of Subscribers : " + publisher.subscribers.size());
			LinkedList l = (LinkedList) post_sub.get(publisher.getId());
			System.out.println("No. Of Posts : " + l.size());
			HashSet a = publisher.category_post;
			Iterator next = a.iterator();
			String s = "";
			while (next.hasNext()) {
				int q = (Integer) next.next();
				category cc=(category) cat.get(q);
				s = s + " " +cc.getName();
			}

			System.out.println("Categories of Post : " + s);

			System.out.println("--------------------------------------");
		}
	}

	public void add_view_delete_pub(int id, HashMap pub, HashMap sub, HashMap cat, HashMap post_sub, HashMap post_cat) {
		boolean b = true;
		while (b) {
			System.out.println("1)View All Publishers\n2)Search Publisher by Name\n3)View Publisher\n4)Back");
			System.out.println("Enter Choice..");
			int ch = sc.nextInt();
			switch (ch) {
			case 1:
				view_all_publisher(id, pub, sub, cat, post_sub, post_cat);
				break;
			case 2:
				System.out.println("Enter Publisher's Name to Search...");
				String name = sc.next();
				ArrayList publist = new ArrayList();
				for (Object o : pub.keySet()) {
					pubsub p = (pubsub) pub.get(o);
					if (p.getName().equals(name)) {
						publist.add(p);
					}

				}
				if (publist.size() > 1) {
					System.out.println("ID  \tName");
					for (int i = 0; i < publist.size(); i++) {
						pubsub p = (pubsub) publist.get(i);
						System.out.println(p.getId() + "\t" + p.getName());
					}
					System.out.println("Enter Id You Want To See...\n");
					int pid = sc.nextInt();
					if (pub.containsKey(pid)) {
						view_publisher(id, pub, sub, cat, post_sub, post_cat, pid);
					}
				} else if (publist.size() == 1) {
					pubsub p = (pubsub) publist.get(0);
					int pid = p.getId();
					view_publisher(id, pub, sub, cat, post_sub, post_cat, pid);
				} else {
					System.out.println("No Match Found..!");
				}

				break;
			case 3:
				System.out.println("Enter Publisher ID...");
				int pid = sc.nextInt();
				view_publisher(id, pub, sub, cat, post_sub, post_cat, pid);
				break;
			case 4:
				b = false;
			default:
				System.out.println("Wrong choice.....");
				return;
			}
		}
		f.backup(pub, sub, cat, post_sub, post_cat);
	}

	public void add_view_post(int id, HashMap pub, HashMap sub, HashMap cat, HashMap post_sub, HashMap post_cat) {
		while (true) {
			System.out.println("1)View Post\n2)Add Post\n3)Back");
			System.out.println("Enter Choice..");
			int ch = sc.nextInt();
			switch (ch) {
			case 1:
				view_post(id, post_sub, cat);
				break;
			case 2:
				add_post(id, pub, sub, cat, post_sub, post_cat);
				break;
			default:
				return;
			}
		}

	}

	public void view_post(int id, HashMap post_sub, HashMap cat) {
		if (post_sub.containsKey(id)) {
			LinkedList ll = (LinkedList) post_sub.get(id);
			for (Object obj : ll) {
				post p = (post) obj;
				System.out.println("Posted by," + p.getPub_name() + " on " + p.getTime());
				System.out.println(p.getContent());
				category category = (category) cat.get(p.getCat_id());
				System.out.println("Category : " + category.getName().toString());
				System.out.println("-----------------------------------------------");
			}
		} else {
			System.out.println("You Haven't Published Anything Yet...!");
		}
	}

	public void add_post(int id, HashMap pub, HashMap sub, HashMap cat, HashMap post_sub, HashMap post_cat) {
		System.out.println("Enter Category For Your Post..");
		for (Object obj : cat.keySet()) {
			category c = (category) cat.get(obj);
			System.out.println(c.getId() + ".) " + c.getName());
		}

		int i = sc.nextInt();
		if (i > 16 || i < 0) {
			return;
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		System.out.println("Enter Content....");

		sc.nextLine();
		String content = sc.nextLine();
		pubsub subscriber = (pubsub) sub.get(id);

		post p = new post(content, i, id, subscriber.getName(), date);
		// subscriber.setCategory_post(i);
		HashSet ab = subscriber.category_post;
		ab.add(i);
		/*
		 * if (a==null) { a=new ArrayList(); a.add(i); } else {
		 * if(a.contains(i)) {} else { a.add(i);} }
		 */
		if (!pub.containsKey(id)) {
			System.out.println("Congratulations!You've Made Your First Post.Your Are Also A Publisher Now.");
			pub.put(id, sub.get(id));
			LinkedList ll = new LinkedList();
			ll.add(p);
			post_sub.put(id, ll);
		} else {
			LinkedList ll = (LinkedList) post_sub.get(id);
			ll.add(p);
		}
		if (post_cat.containsKey(i)) {
			LinkedList ll = (LinkedList) post_cat.get(i);
			ll.add(p);
		} else {
			LinkedList ll = new LinkedList();
			ll.add(p);
			post_cat.put(i, ll);
		}

		System.out.println("Your Post Is Added...");
		f.backup(pub, sub, cat, post_sub, post_cat);
	}

	public int login(HashMap sub) {
		System.out.println("Enter User Id :");
		int id = sc.nextInt();
		System.out.println("Enter Password :");
		String password = sc.next();
		if (sub.containsKey(id)) {
			pubsub ps = (pubsub) sub.get(id);
			if (password.equals(ps.getPassword())) {
				return id;
			} else {
				System.out.println("Password is Wrong...!");
				return 0;
			}
		}
		System.out.println("User Id Is Wrong...!");
		return 0;
	}

	public pubsub registration() {
		System.out.println("Enter Your Name : ");
		String name = sc.next();
		System.out.println("Enter Your Password : ");
		Console console = System.console();

		String password = sc.next();
		System.out.println("Enter Your Email : ");
		String email = sc.next();
		System.out.println("Enter Your Mobile Number : ");
		long no = sc.nextLong();
		System.out.println("Enter Your Dob : ");
		String dob = sc.next();
		System.out.println("Enter Your Gender : ");
		String gender = sc.next();
		pubsub ps = new pubsub(name, password, no, email, gender, dob);
		return ps;
	}

	public void viewDetail(pubsub pubsub) {
		System.out.println("Name->" + pubsub.name);
		System.out.println("Email->" + pubsub.email);
		System.out.println("Mobile no->" + pubsub.number);
		System.out.println("Dob->" + pubsub.dob);
		System.out.println("Gender->" + pubsub.gender);
	}

	public void forgetPassword(HashMap<Integer, pubsub> subscriber) {
		while (true) {
			System.out.println("Enter Email or mobile");
			System.out.println("1.Email");
			System.out.println("2.Mobile");
			System.out.println("3.Return to Previous Menu");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter email");
				String email = sc.next();
				if (!findPassword(subscriber, String.valueOf(email), true))
					System.out.println("Invalid Email Id");
				break;
			case 2:
				System.out.println("Enter mobile");
				long mobile = sc.nextLong();
				if (!findPassword(subscriber, String.valueOf(mobile), false))

					System.out.println("Invalid Mobile Number");
				break;

			default:
				if (choice != 3)
					System.out.println("Invalid choice");
				break;
			}
			if (choice == 3)
				return;
		}
	}

	private boolean findPassword(HashMap<Integer, pubsub> subscriber, String value, Boolean isEmail) {
		boolean found = false;
		for (Entry<Integer, pubsub> entry : subscriber.entrySet()) {
			if (isEmail && entry.getValue().email.equals(value)) {
				System.out.println("Your Id->" + entry.getKey());
				System.out.println("Your Password->" + entry.getValue().password);
				found = true;
				break;
			}
			if (!isEmail && entry.getValue().number == Long.valueOf(value)) {
				System.out.println("Your Id->" + entry.getKey());
				System.out.println("Your Password->" + entry.getValue().password);
				found = true;
				break;
			}
		}
		return found;
	}

	public void add_view_delete_cat(int id, HashMap pub, HashMap sub, HashMap cat, HashMap post_sub, HashMap post_cat) {

		boolean b = true;

		while (b) {
			System.out.println("Id\tNo. Subscriber\tNo. of Post");
			for (Object obj : cat.keySet()) {
				category c = (category) cat.get(obj);
				LinkedList p = (LinkedList) post_cat.get(c.getId());
				int k = 0;
				if (p == null) {
				} else {
					k = p.size();
				}
				System.out.println(c.getId() + ".) " + c.getName() + "\t" + c.getcount() + "\t" + k);
			}

			System.out.println("1)view post\n2)Subscribe Category\n3)Un-Subscribe Category\n4)Back");
			System.out.println("Enter Choice..");
			int ch = sc.nextInt();
			switch (ch) {
			case 1:
				System.out.println("Enter Category Id To See The Post..");
				int cat_id = sc.nextInt();
				if (cat_id > 0 && cat_id < 17) {
					category c = (category) cat.get(cat_id);
					LinkedList p = (LinkedList) post_cat.get(c.getId());
					if (p == null) {
					} else {
						for (Object o : p) {
							post p1 = (post) o;
							System.out.println("Posted by," + p1.getPub_name() + " on " + p1.getTime());
							System.out.println(p1.getContent());
							category category = (category) cat.get(p1.getCat_id());
							System.out.println("Category : " + category.getName().toString());
							System.out.println("-----------------------------------------------");

						}
					}
				} else {
					System.out.println("Wrong Category Id..");
				}
				break;
			case 2:
				System.out.println("Enter Category Id to Subscribe..");
				int catid = sc.nextInt();
				if (catid > 0 && catid < 17) {
					category c = (category) cat.get(catid);
					c.increment_count();
					pubsub pub1 = (pubsub) sub.get(id);
					HashSet hs = pub1.getCategory();
					if (hs.add(catid)) {
						System.out.println("Added...");
					} else {
						System.out.println("Already Subscribed..");
					}
					if (pub.containsKey(id)) {
						pubsub pub2 = (pubsub) pub.get(id);
						HashSet hs1 = pub1.getCategory();
						hs1.add(catid);
					}
				}
				b = false;
				break;
			case 3:
				System.out.println("Enter category Id to Un-Subscribe..");
				int cid = sc.nextInt();
				if (cid > 0 && cid < 17) {
					category c = (category) cat.get(cid);
					c.increment_count();
					pubsub pub1 = (pubsub) sub.get(id);
					HashSet hs = pub1.getCategory();
					if (hs.remove(cid)) {
						System.out.println("Removed..");
					} else {
						System.out.println("Not Subscribed..");
					}
					if (pub.containsKey(id)) {
						pubsub pub2 = (pubsub) pub.get(id);
						HashSet hs1 = pub1.getCategory();
						hs1.remove(cid);
					}
				}
				b = false;
				break;
			default:
				b = false;
			}

		}
		f.backup(pub, sub, cat, post_sub, post_cat);
	}

}
