package com.markfox.patientmanager;

import com.markfox.patientmanager.connections.ConnectionPool;
import com.markfox.patientmanager.connections.MyConnectionPool;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConnectionPoolTests {

    @Test
    public void createConnectionTest() throws SQLException {
        ConnectionPool connectionPool = MyConnectionPool.create("jdbc:h2:mem:test", "user", "password");

        assertTrue(connectionPool.getConnection().isValid(1));
    }
}
