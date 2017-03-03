package bulletinBoard.dao;

import static bulletinBoard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bulletinBoard.beans.UserManagement;
import bulletinBoard.exception.SQLRuntimeException;

public class UserManagementDao {

	public List<UserManagement> getManagements(Connection Connection , Integer user_id ) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM managements");
			if (user_id != null) {
				sql.append ("WHERE user_id = ?");
			}

			ps = Connection.prepareStatement(sql.toString());

			if (user_id != null) {
				ps.setInt (1 ,  user_id);
			}

			ResultSet rs = ps.executeQuery();

			List<UserManagement> ret = toUserManagementList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserManagement> toUserManagementList(ResultSet rs) throws SQLException {
		List<UserManagement> ret = new ArrayList<UserManagement>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String name = rs.getString("name");
				String branch_name = rs.getString("branch_name");
				String department_name = rs.getString("department_name");
				int is_stoped = rs.getInt("is_stoped");

				UserManagement management = new UserManagement();
				management.setId(id);
				management.setLogin_id(login_id);
				management.setName(name);
				management.setBranch_name(branch_name);
				management.setDepartment_name(department_name);
				management.setIs_stoped(is_stoped);

				ret.add(management);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}