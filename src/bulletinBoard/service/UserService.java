package bulletinBoard.service;

import static bulletinBoard.utils.CloseableUtil.*;
import static bulletinBoard.utils.DBUtil.*;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import bulletinBoard.beans.User;
import bulletinBoard.dao.UserDao;
import bulletinBoard.utils.CipherUtil;
public class UserService {

	public void register (User user) {

		Connection connection = null;
		try {
			connection = getConnection();

			String encPassword = CipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);

			UserDao userDao = new UserDao();
			userDao.insert (connection , user);

			commit (connection);
		} catch (RuntimeException e) {
			rollback (connection);
			throw e;
		} catch(Error e) {
			rollback (connection);
			throw e;
		} finally {
			close (connection);
		}
	}

	public void update (User editUser) {
		Connection connection = null;
		try {
			connection = getConnection();

			if (!StringUtils.isEmpty(editUser.getPassword())) {
				String encPassword = CipherUtil.encrypt (editUser.getPassword());
				editUser.setPassword (encPassword);
			}
			UserDao userDao = new UserDao();
			userDao.update (connection , editUser);

			commit (connection);
		} catch (RuntimeException e) {
			rollback (connection);
			throw e;
		} catch (Error e) {
			rollback (connection);
			throw e;
		} finally {
			close (connection);
		}
	}

	public User getUser (int id) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			User user = userDao.getUser (connection , id);
			commit (connection);

			return user;
		} catch (RuntimeException e) {
			rollback (connection);
			throw e;
		} catch (Error e) {
			rollback (connection);
			throw e;
		} finally {
			close (connection);
		}
	}
	public void invert (User user) {
		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			userDao.stop(connection, user);

			commit(connection);

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

	public void delete (User user) {
		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			userDao.delete(connection, user);

			commit(connection);

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
	public User overlap (String login_id) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			User overlap = userDao.overlap (connection , login_id);
			commit (connection);

			return overlap;
		} catch (RuntimeException e) {
			rollback (connection);
			throw e;
		} catch (Error e) {
			rollback (connection);
			throw e;
		} finally {
			close (connection);
		}

	}
}
