package connection.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connection.DbManager;

class DBManagerTest {

	Connection connection;
	
	@BeforeEach
	void setUp() throws Exception {
		connection = DbManager.createConnection();
	}

	@AfterEach
	void tearDown() throws Exception {
		if(connection != null)
			connection.close();
	}

	@Test
	void test() {
		if(connection != null) {
			try {
				assertTrue(connection.isValid(DbManager.LoginTimeout));
			} catch (SQLException e) {
				fail();
			}
		}
		else
			fail();
	}

}
