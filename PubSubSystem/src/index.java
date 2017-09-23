import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class index {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HashMap<Integer, pubsub> publisher = new HashMap<Integer, pubsub>();
		HashMap<Integer, pubsub> subscriber = new HashMap<Integer, pubsub>();
		HashMap<Integer, category> cat = new HashMap<Integer, category>();
		HashMap<Integer, LinkedList> post_pub = new HashMap<Integer, LinkedList>();
		HashMap<Integer, LinkedList> post_cat = new HashMap<Integer, LinkedList>();
		pubsub_child ps = new pubsub_child();
		file f = new file();
		f.restore(publisher, subscriber, cat, post_pub, post_cat);
		if (cat.isEmpty()) {
			category c = new category();
			cat=c.add(cat);
		}
		boolean c=true;
		while (c) {
			System.out.println("Welecome to Pub/Sub\n" + "1)Login\n" + "2)Registration\n"+"3)Forgot Password\n"+"4)Exit\n");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				int check = ps.login(subscriber);
				if (check != 0) {
					ps.home(check, publisher, subscriber, cat, post_pub, post_cat);
				}
				break;
			case 2:
				pubsub pubsub = ps.registration();
				subscriber.put((int)pubsub.getId(), pubsub);
				System.out.println("You Have Been Registered Successfully , " + pubsub.getName()
						+ " And Your Unique Login Id Is " + pubsub.getId());
				f.backup(publisher, subscriber, cat, post_pub, post_cat);
				break;
			case 3:
				ps.forgetPassword(subscriber);
				break;
			case 4:f.backup(publisher, subscriber, cat, post_pub, post_cat);
			System.out.println("Exited");
				c=false;
				System.exit(0);
				break;
			default:
				System.out.println("Wrong Choice....!");
				c=true;
				break;
			}
			
		}
		f.backup(publisher, subscriber, cat, post_pub, post_cat);
	}
}
