package bulletinBoard.dao;

import static bulletinBoard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bulletinBoard.beans.UserComment;
import bulletinBoard.exception.SQLRuntimeException;

public class UserCommentDao {
	public List<UserComment> getUserComments(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_comments ");
			sql.append("ORDER BY insert_date ASC");

			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			List<UserComment> ret = toUserCommentList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserComment> toUserCommentList(ResultSet rs)
			throws SQLException {

		List<UserComment> ret = new ArrayList<UserComment>();
		try {
			while (rs.next()) {
				String login_id = rs.getString("login_id");
				String name = rs.getString("name");
				int id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				int post_id = rs.getInt("post_id");
				String text = rs.getString("text");
				int branch_id = rs.getInt("branch_id");
				int department_id = rs.getInt("department_id");
				Timestamp insertDate = rs.getTimestamp("insert_date");

				UserComment comment = new UserComment();
				comment.setLogin_id(login_id);
				comment.setName(name);
				comment.setId(id);
				comment.setUser_id(user_id);
				comment.setPost_id(post_id);
				comment.setText(text);
				comment.setBranch_id(branch_id);
				comment.setDepartment_id(department_id);
				comment.setInsertDate(insertDate);

				ret.add(comment);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
