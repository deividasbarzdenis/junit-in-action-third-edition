package org.example.extensions;

import org.example.jdbc.ConnectionManager;
import org.example.jdbc.TablesManager;
import org.junit.jupiter.api.extension.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

public class DatabaseOperationsExtension implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback,
        AfterEachCallback {

    private Connection connection;
    private Savepoint savepoint;

    @Override
    public void afterAll(ExtensionContext extensionContext) {
        ConnectionManager.closeConnection();
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws SQLException {
        connection.rollback(savepoint);
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        connection = ConnectionManager.openConnection();
        TablesManager.dropTable(connection);
        TablesManager.createTable(connection);
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws SQLException {
        connection.setAutoCommit(false);
        savepoint = connection.setSavepoint("savepoint");
    }
}
