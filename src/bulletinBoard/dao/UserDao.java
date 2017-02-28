package bulletinBoard.dao;

import static bulletinBoard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bulletinBoard.beans.User;
import bulletinBoard.exception.SQLRuntimeException;

public class UserDao {
	public User getUser(Connection connection , String login_id , String password) {
		PreparedStatement ps = null;
		List<User> userList = new ArrayList<User>();
		try {
			String sql = "SELECT login_id , password FROM users";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				login_id = rs.getString ("login_id");
				password = rs.getString ("password");
				User user = new User();
				userList.add (user);
			}
			if (userList.isEmpty() == true) {
				return null;
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException (e);
		} finally {
			close(ps);
		}
	}

	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String branch_name = rs.getString("branch_name");
				String department_name = rs.getString("department_name");
				String name = rs.getString("name");
				String password = rs.getString("password");

				User user = new User();
				user.setId(id);
				user.setLogin_id(login_id);
				user.setBranch_name(branch_name);
				user.setDepartment_name(department_name);
				user.setName(name);
				user.setPassword(password);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO managements ( ");
			sql.append("id");
			sql.append(", login_id");
			sql.append(", branch_name");
			sql.append(", department_name");
			sql.append(", name");
			sql.append(", password");
			sql.append(") VALUES (");
			sql.append("?"); // id
			sql.append(", ?"); // login_id
			sql.append(", ?"); // branch_name
			sql.append(", ?"); // department_name
			sql.append(", ?"); // name
			sql.append(", ?"); // password
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLogin_id());
			ps.setString(2, user.getBranch_name());
			ps.setString(3, user.getDepartment_name());
			ps.setString(4, user.getName());
			ps.setString(5, user.getPassword());

			//System.out.println(ps.toString());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void update(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE user SET");
			sql.append("  login_id = ?");
			sql.append(", branch_name = ?");
			sql.append(", department_name = ?");
			sql.append(", name = ?");
			sql.append(", password = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLogin_id());
			ps.setString(2, user.getBranch_name());
			ps.setString(3, user.getDepartment_name());
			ps.setString(4, user.getName());
			ps.setString(5, user.getPassword());

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	public User getUser(Connection connection, int uId) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, uId);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}


