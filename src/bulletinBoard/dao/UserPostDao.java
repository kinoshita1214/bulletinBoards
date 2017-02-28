package bulletinBoard.dao;

import static bulletinBoard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bulletinBoard.beans.UserPost;
import bulletinBoard.exception.SQLRuntimeException;

public class UserPostDao {

	public List<UserPost> getUserPosts(Connection connection,Integer user_id) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_posts ");
			if (user_id != null) {
				sql.append("WHERE user_id = ?");
			}

			ps = connection.prepareStatement(sql.toString());

			if (user_id != null) {
				ps.setInt(1, user_id);
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
				Timestamp insertDate = rs.getTimestamp("insert_date");

				UserPost post = new UserPost();
				post.setLogin_id(login_id);
				post.setName(name);
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