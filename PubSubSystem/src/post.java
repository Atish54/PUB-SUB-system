import java.sql.Date;

public class post implements java.io.Serializable {
	int id;
	String content;
	int cat_id;
	int pub_id;
	String pub_name;
	String time;
	static int count = 0;

	public void setcount(int n) {
		count = n;
	}

	post() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCat_id() {
		return cat_id;
	}

	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}

	public int getPub_id() {
		return pub_id;
	}

	public void setPub_id(int pub_id) {
		this.pub_id = pub_id;
	}

	public String getPub_name() {
		return pub_name;
	}

	public void setPub_name(String pub_name) {
		this.pub_name = pub_name;
	}

	public post(String content, int cat_id, int pub_id, String pub_name, String time) {
		super();
		count++;
		this.id = count;
		this.content = content;
		this.cat_id = cat_id;
		this.pub_id = pub_id;
		this.pub_name = pub_name;
		this.time = time;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		post.count = count;
	}

}
