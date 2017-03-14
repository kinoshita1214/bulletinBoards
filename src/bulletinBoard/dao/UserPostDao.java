package bulletinBoard.dao;

import static bulletinBoard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bulletinBoard.beans.Post;
import bulletinBoard.beans.UserPost;
import bulletinBoard.exception.SQLRuntimeException;

public class UserPostDao {

	public List<UserPost> getUserPosts(Connection connection , String start , String end , Post category) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_posts ");
			sql.append("WHERE insert_date >= ? AND insert_date < ?");

			if (category.getCategory() != null) {
				sql.append(" AND category = ?");
			}

			sql.append(" ORDER BY insert_date DESC");
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1 , start);
			ps.setString(2 , end);
			if (category.getCategory() != null){
				ps.setString(3 , category.getCategory());
			}
			ResultSet rs = ps.executeQuery();


			List<UserPost> ret = toUserPostList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserPost> toUserPostList(ResultSet rs)
			throws SQLException {

		List<UserPost> ret = new ArrayList<UserPost>();
		try {
			while (rs.next()) {
				String login_id = rs.getString("login_id");
				String name = rs.getString("name");
				int id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				String subject = rs.getString("subject");
				String text = rs.getString("text");
				String category = rs.getString("category");
				int branch_id = rs.getInt("branch_id");
				int department_id = rs.getInt("department_id");
				Timestamp insertDate = rs.getTimestamp("insert_date");

				UserPost post = new UserPost();
				post.setLogin_id(login_id);
				post.setName(name);
				post.setId(id);
				post.setUser_id(user_id);
				post.setSubject(subject);
				post.setText(text);
				post.setCategory(category);
				post.setBranch_id(branch_id);
				post.setDepartment_id(department_id);
				post.setInsertDate(insertDate);

				ret.add(post);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public List<UserPost> getCategory(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT DISTINCT category FROM user_posts ");
			//sql.append("ORDER BY insert_date DESC");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserPost> ret = toCategoryList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserPost> toCategoryList(ResultSet rs)
			throws SQLException {

		List<UserPost> ret = new ArrayList<UserPost>();
		try {
			while (rs.next()) {
				String category = rs.getString("category");

				UserPost categories = new UserPost();

				categories.setCategory(category);

				ret.add(categories);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public List<UserPost> getDate(Connection connection) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM posts");
			sql.append(" ORDER BY insert_date  LIMIT 1");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserPost> ret = toUserPostsList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	private List<UserPost> toUserPostsList(ResultSet rs)
			throws SQLException {

		List<UserPost> ret = new ArrayList<UserPost>();
		try {
			while (rs.next()) {


				int id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				String subject = rs.getString("subject");
				String text = rs.getString("text");
				String category = rs.getString("category");
				Timestamp insertDate = rs.getTimestamp("insert_date");

				UserPost post = new UserPost();
				post.setId(id);
				post.setUser_id(user_id);
				post.setSubject(subject);
				post.setText(text);
				post.setCategory(category);
				post.setInsertDate(insertDate);

				ret.add(post);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}