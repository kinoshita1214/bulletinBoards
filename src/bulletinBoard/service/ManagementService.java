package bulletinBoard.service;

import static bulletinBoard.utils.CloseableUtil.*;
import static bulletinBoard.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bulletinBoard.beans.UserManagement;
import bulletinBoard.dao.UserManagementDao;

public class ManagementService {

	public List<UserManagement> getManagement(Integer user_id) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserManagementDao messageDao = new UserManagementDao();
			List<UserManagement> ret = messageDao.getManagements(connection , user_id);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

}