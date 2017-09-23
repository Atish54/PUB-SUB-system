import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class file {
	public void setCount() {
		try {
			ArrayList count = new ArrayList();
			File file1 = new File("c:\\PubSub\\count.txt");
			FileInputStream f1 = new FileInputStream(file1);
			ObjectInputStream s1 = new ObjectInputStream(f1);
			count = (ArrayList) s1.readObject();
			s1.close();
			pubsub.setCount((Integer) count.get(0));
			post.setCount((Integer) count.get(1));
		} catch (Exception e) {
		}
	}

	public void setCount_file() {
		try {
			ArrayList count = new ArrayList();
			count.add(pubsub.getCount());
			count.add(post.getCount());
			File file1 = new File("c:\\PubSub\\count.txt");
			FileOutputStream f1 = new FileOutputStream(file1);
			ObjectOutputStream s1 = new ObjectOutputStream(f1);
			s1.writeObject(count);
			s1.close();

		} catch (Exception e) {
		}
	}

	public void restore(HashMap pub, HashMap sub, HashMap cat, HashMap post_pub, HashMap post_cat) {
		try {
			File file1 = new File("c:\\PubSub\\publisher.txt");
			FileInputStream f1 = new FileInputStream(file1);
			ObjectInputStream s1 = new ObjectInputStream(f1);
			HashMap p = new HashMap();
			p = (HashMap) s1.readObject();
			for (Object o : p.keySet()) {
				pub.put(o, p.get(o));
			}

		} catch (Exception e) {
		}
		try {
			HashMap p = new HashMap();
			File file1 = new File("c:\\PubSub\\subscriber.txt");
			FileInputStream f1 = new FileInputStream(file1);
			ObjectInputStream s1 = new ObjectInputStream(f1);
			p = (HashMap) s1.readObject();
			for (Object o : p.keySet()) {
				sub.put(o, p.get(o));
			}
			s1.close();

		} catch (Exception e) {
			// System.out.println(e.getMessage());
		}
		try {

			File file1 = new File("c:\\PubSub\\category.txt");
			FileInputStream f1 = new FileInputStream(file1);
			ObjectInputStream s1 = new ObjectInputStream(f1);
			HashMap p = new HashMap();
			p = (HashMap) s1.readObject();
			for (Object o : p.keySet()) {
				cat.put(o, p.get(o));
			}

			s1.close();

		} catch (Exception e) {
		}
		try {
			File file1 = new File("c:\\PubSub\\post_publisher.txt");
			FileInputStream f1 = new FileInputStream(file1);
			ObjectInputStream s1 = new ObjectInputStream(f1);
			HashMap p = new HashMap();
			p = (HashMap) s1.readObject();
			for (Object o : p.keySet()) {
				post_pub.put(o, p.get(o));
			}

			s1.close();

		} catch (Exception e) {
		}
		try {
			File file1 = new File("c:\\PubSub\\post_category.txt");
			FileInputStream f1 = new FileInputStream(file1);
			ObjectInputStream s1 = new ObjectInputStream(f1);
			HashMap p = new HashMap();
			p = (HashMap) s1.readObject();
			for (Object o : p.keySet()) {
				post_cat.put(o, p.get(o));
			}

			s1.close();

		} catch (Exception e) {
		}
		setCount();
	}

	public void backup(HashMap pub, HashMap sub, HashMap cat, HashMap post_pub, HashMap post_cat) {
		try {
			File file1 = new File("c:\\PubSub\\publisher.txt");
			FileOutputStream f = new FileOutputStream(file1);
			ObjectOutputStream s1 = new ObjectOutputStream(f);
			s1.writeObject(pub);
			s1.close();

		} catch (Exception e) {
		}
		try {
			File file1 = new File("c:\\PubSub\\subscriber.txt");
			FileOutputStream f = new FileOutputStream(file1);
			ObjectOutputStream s1 = new ObjectOutputStream(f);
			s1.writeObject(sub);
			s1.close();

		} catch (Exception e) {
		}
		try {
			File file1 = new File("c:\\PubSub\\category.txt");
			FileOutputStream f = new FileOutputStream(file1);
			ObjectOutputStream s1 = new ObjectOutputStream(f);
			s1.writeObject(cat);
			s1.close();

		} catch (Exception e) {
		}
		try {
			File file1 = new File("c:\\PubSub\\post_publisher.txt");
			FileOutputStream f = new FileOutputStream(file1);
			ObjectOutputStream s1 = new ObjectOutputStream(f);
			s1.writeObject(post_pub);
			s1.close();

		} catch (Exception e) {
		}
		try {
			File file1 = new File("c:\\PubSub\\post_category.txt");
			FileOutputStream f = new FileOutputStream(file1);
			ObjectOutputStream s1 = new ObjectOutputStream(f);
			s1.writeObject(post_cat);
			s1.close();

		} catch (Exception e) {
		}
		setCount_file();
	}

}
