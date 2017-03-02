package bulletinBoard.beans;

import javax.servlet.http.HttpServlet;

public class Department extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
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
