package bulletinBoard.dao;

import static bulletinBoard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bulletinBoard.beans.Department;
import bulletinBoard.exception.SQLRuntimeException;

public class DepartmentDao {

	public List<Department> getDepartment(Connection connection) {
		PreparedStatement ps  = null;

		try {
			String sql = "SELECT * FROM departments ORDER BY id ";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Department> departmentList = toDepartmentList(rs);

			if (departmentList.isEmpty() == true) {
				return null;
			} else {
				return departmentList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	private List<Department> toDepartmentList(ResultSet rs) throws SQLException {

		List<Department> ret = new ArrayList<Department>();
		try {
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");

				Department department = new Department();
				department.setId(id);
				department.setName(name);

				ret.add(department);

			}
			return ret;

		} finally {
			close(rs);
		}
	}

}
