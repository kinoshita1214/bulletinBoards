package bulletinBoard.dao;

import static bulletinBoard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import bulletinBoard.beans.User;
import bulletinBoard.exception.SQLRuntimeException;

public class UserDao {
	public User getUser(Connection connection , String login_id , String password ) {
		PreparedStatement ps = null;

		try {
			String sql = "SELECT * FROM users WHERE login_id = ? AND password = ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, login_id);
			ps.setString(2, password);

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


	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				int branch_id = rs.getInt("branch_id");
				int department_id = rs.getInt("department_id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				int is_stoped = rs.getInt("is_stoped");

				User user = new User();
				user.setId(id);
				user.setLogin_id(login_id);
				user.setBranch_id(branch_id);
				user.setDepartment_id(department_id);
				user.setName(name);
				user.setPassword(password);
				user.setIs_stoped(is_stoped);

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
			sql.append("INSERT INTO users ( ");
			//sql.append("id");
			sql.append(" login_id");
			sql.append(", branch_id");
			sql.append(", department_id");
			sql.append(", name");
			sql.append(", password");
			sql.append(") VALUES (");
			//sql.append("?"); // id
			sql.append(" ?"); // login_id
			sql.append(", ?"); // branch_id
			sql.append(", ?"); // department_id
			sql.append(", ?"); // name
			sql.append(", ?"); // password
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			//ps.setInt(1 , user.getId());
			ps.setString(1, user.getLogin_id());
			ps.setInt(2, user.getBranch_id());
			ps.setInt(3, user.getDepartment_id());
			ps.setString(4, user.getName());
			ps.setString(5, user.getPassword());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void update(Connection connection, User editUser ) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append("  login_id = ?");
			sql.append(", name = ?");
			if (!StringUtils.isEmpty(editUser.getPassword())) {
				sql.append(", password = ?");
			}
			sql.append(", branch_id = ?");
			sql.append(", department_id = ?");
			sql.append(" WHERE");
			sql.append(" id = ?");
			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, editUser.getLogin_id());
			ps.setString(2, editUser.getName());
			if (StringUtils.isEmpty(editUser.getPassword())) {
				ps.setInt(3, editUser.getBranch_id());
				ps.setInt(4, editUser.getDepartment_id());
				ps.setInt(5 , editUser.getId());
			} else {
				ps.setString(3, editUser.getPassword());
				ps.setInt(4, editUser.getBranch_id());
				ps.setInt(5, editUser.getDepartment_id());
				ps.setInt(6 , editUser.getId());
			}

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	public User getUser(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

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

	public void stop (Connection connection , User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append(" is_stoped = ?");
			sql.append(" WHERE id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt (1 , user.getIs_stoped());
			ps.setInt (2 , user.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void delete (Connection connection , User user) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM users");
			sql.append(" WHERE id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt (1 , user.getId());

			ps.execute();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public User overlap(Connection connection, String login_id) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE login_id = ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, login_id);

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


