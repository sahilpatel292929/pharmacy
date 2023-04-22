package com.ets.SecurePharmacy.config.dbConfig;

import org.hibernate.HibernateException;
import org.hibernate.engine.config.internal.ConfigurationServiceImpl;
import org.hibernate.engine.config.spi.ConfigurationService;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {MultiTenantConnectionProviderImpl.class})
@ExtendWith(SpringExtension.class)
class MultiTenantConnectionProviderImplTest {
    @Autowired
    private ApplicationContext applicationContext;

    @MockBean
    private DataSource dataSource;

    @Autowired
    private MultiTenantConnectionProviderImpl multiTenantConnectionProviderImpl;

    /**
     * Method under test: {@link MultiTenantConnectionProviderImpl#selectAnyDataSource()}
     */
    @Test
    void testSelectAnyDataSource() {
        // Arrange, Act and Assert
        assertNotNull(multiTenantConnectionProviderImpl.selectAnyDataSource());
    }


    /**
     * Method under test: {@link MultiTenantConnectionProviderImpl#getAnyConnection()}
     */
    @Test
    void testGetAnyConnection() throws SQLException {
        // Arrange
        when(dataSource.getConnection()).thenReturn(mock(Connection.class));

        // Act
        multiTenantConnectionProviderImpl.getAnyConnection();

        // Assert
        verify(dataSource).getConnection();
    }

    /**
     * Method under test: {@link MultiTenantConnectionProviderImpl#getAnyConnection()}
     */
    @Test
    void testGetAnyConnection2() throws SQLException {
        // Arrange
        when(dataSource.getConnection()).thenThrow(new SQLException());

        // Act and Assert
        assertThrows(SQLException.class, () -> multiTenantConnectionProviderImpl.getAnyConnection());
        verify(dataSource).getConnection();
    }

    /**
     * Method under test: {@link MultiTenantConnectionProviderImpl#releaseAnyConnection(Connection)}
     */
    @Test
    void testReleaseAnyConnection() throws SQLException {
        // Arrange
        Connection connection = mock(Connection.class);
        doNothing().when(connection).close();

        // Act
        multiTenantConnectionProviderImpl.releaseAnyConnection(connection);

        // Assert that nothing has changed
        verify(connection).close();
    }

    /**
     * Method under test: {@link MultiTenantConnectionProviderImpl#releaseAnyConnection(Connection)}
     */
    @Test
    void testReleaseAnyConnection2() throws SQLException {
        // Arrange
        Connection connection = mock(Connection.class);
        doThrow(new SQLException()).when(connection).close();

        // Act and Assert
        assertThrows(SQLException.class, () -> multiTenantConnectionProviderImpl.releaseAnyConnection(connection));
        verify(connection).close();
    }

    /**
     * Method under test: {@link MultiTenantConnectionProviderImpl#getConnection(String)}
     */
    @Test
    void testGetConnection() throws SQLException {
        // Arrange
        Statement statement = mock(Statement.class);
        when(statement.execute((String) any())).thenReturn(true);
        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenReturn(statement);
        when(dataSource.getConnection()).thenReturn(connection);

        // Act
        multiTenantConnectionProviderImpl.getConnection("42");

        // Assert
        verify(dataSource).getConnection();
        verify(connection).createStatement();
        verify(statement).execute((String) any());
    }

    /**
     * Method under test: {@link MultiTenantConnectionProviderImpl#getConnection(String)}
     */
    @Test
    void testGetConnection2() throws SQLException {
        // Arrange
        Statement statement = mock(Statement.class);
        when(statement.execute((String) any())).thenThrow(new SQLException());
        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenReturn(statement);
        when(dataSource.getConnection()).thenReturn(connection);

        // Act and Assert
        assertThrows(HibernateException.class, () -> multiTenantConnectionProviderImpl.getConnection("42"));
        verify(dataSource).getConnection();
        verify(connection).createStatement();
        verify(statement).execute((String) any());
    }

    /**
     * Method under test: {@link MultiTenantConnectionProviderImpl#getConnection(String)}
     */
    @Test
    void testGetConnection3() throws SQLException {
        // Arrange
        Statement statement = mock(Statement.class);
        when(statement.execute((String) any())).thenThrow(new HibernateException("An error occurred"));
        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenReturn(statement);
        when(dataSource.getConnection()).thenReturn(connection);

        // Act and Assert
        assertThrows(HibernateException.class, () -> multiTenantConnectionProviderImpl.getConnection("42"));
        verify(dataSource).getConnection();
        verify(connection).createStatement();
        verify(statement).execute((String) any());
    }

    /**
     * Method under test: {@link MultiTenantConnectionProviderImpl#getConnection(String)}
     */
    @Test
    void testGetConnection4() throws SQLException {
        // Arrange
        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenThrow(new SQLException());
        when(dataSource.getConnection()).thenReturn(connection);

        // Act and Assert
        assertThrows(HibernateException.class, () -> multiTenantConnectionProviderImpl.getConnection("USE "));
        verify(dataSource).getConnection();
        verify(connection).createStatement();
    }

    /**
     * Method under test: {@link MultiTenantConnectionProviderImpl#getConnection(String)}
     */
    @Test
    void testGetConnection5() throws SQLException {
        // Arrange
        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenThrow(new HibernateException("An error occurred"));
        when(dataSource.getConnection()).thenReturn(connection);
        new SQLException();

        // Act and Assert
        assertThrows(HibernateException.class, () -> multiTenantConnectionProviderImpl.getConnection("42"));
        verify(dataSource).getConnection();
        verify(connection).createStatement();
    }

    /**
     * Method under test: {@link MultiTenantConnectionProviderImpl#getConnection(String)}
     */
    @Test
    void testGetConnection6() throws SQLException {
        // Arrange
        Statement statement = mock(Statement.class);
        when(statement.execute((String) any())).thenReturn(true);
        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenReturn(statement);
        when(dataSource.getConnection()).thenReturn(connection);
        new HibernateException("An error occurred");
        new SQLException();

        // Act
        multiTenantConnectionProviderImpl.getConnection(null);

        // Assert
        verify(dataSource).getConnection();
        verify(connection).createStatement();
        verify(statement).execute((String) any());
    }

    /**
     * Method under test: {@link MultiTenantConnectionProviderImpl#getConnection(String)}
     */
    @Test
    void testGetConnection7() throws SQLException {
        // Arrange
        Statement statement = mock(Statement.class);
        when(statement.execute((String) any())).thenThrow(new SQLException());
        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenReturn(statement);
        when(dataSource.getConnection()).thenReturn(connection);
        new HibernateException("An error occurred");
        new SQLException();

        // Act and Assert
        assertThrows(HibernateException.class, () -> multiTenantConnectionProviderImpl.getConnection(null));
        verify(dataSource).getConnection();
        verify(connection).createStatement();
        verify(statement).execute((String) any());
    }

    /**
     * Method under test: {@link MultiTenantConnectionProviderImpl#getConnection(String)}
     */
    @Test
    void testGetConnection8() throws SQLException {
        // Arrange
        Statement statement = mock(Statement.class);
        when(statement.execute((String) any())).thenThrow(new HibernateException("An error occurred"));
        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenReturn(statement);
        when(dataSource.getConnection()).thenReturn(connection);
        new HibernateException("An error occurred");
        new SQLException();

        // Act and Assert
        assertThrows(HibernateException.class, () -> multiTenantConnectionProviderImpl.getConnection(null));
        verify(dataSource).getConnection();
        verify(connection).createStatement();
        verify(statement).execute((String) any());
    }

    /**
     * Method under test: {@link MultiTenantConnectionProviderImpl#getConnection(String)}
     */
    @Test
    void testGetConnection9() throws SQLException {
        // Arrange
        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenThrow(new SQLException());
        when(dataSource.getConnection()).thenReturn(connection);
        new HibernateException("Not all who wander are lost");
        new SQLException();

        // Act and Assert
        assertThrows(HibernateException.class, () -> multiTenantConnectionProviderImpl.getConnection(null));
        verify(dataSource).getConnection();
        verify(connection).createStatement();
    }

    /**
     * Method under test: {@link MultiTenantConnectionProviderImpl#getConnection(String)}
     */
    @Test
    void testGetConnection10() throws SQLException {
        // Arrange
        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenThrow(new HibernateException("An error occurred"));
        when(dataSource.getConnection()).thenReturn(connection);
        new SQLException();
        new HibernateException("An error occurred");
        new SQLException();

        // Act and Assert
        assertThrows(HibernateException.class, () -> multiTenantConnectionProviderImpl.getConnection(null));
        verify(dataSource).getConnection();
        verify(connection).createStatement();
    }

    /**
     * Method under test: {@link MultiTenantConnectionProviderImpl#releaseConnection(String, Connection)}
     */
    @Test
    void testReleaseConnection() throws SQLException {
        // Arrange
        Statement statement = mock(Statement.class);
        when(statement.execute((String) any())).thenReturn(true);
        Connection connection = mock(Connection.class);
        doNothing().when(connection).close();
        when(connection.createStatement()).thenReturn(statement);

        // Act
        multiTenantConnectionProviderImpl.releaseConnection("42", connection);

        // Assert
        verify(connection).createStatement();
        verify(connection).close();
        verify(statement).execute((String) any());
    }

    /**
     * Method under test: {@link MultiTenantConnectionProviderImpl#releaseConnection(String, Connection)}
     */
    @Test
    void testReleaseConnection2() throws SQLException {
        // Arrange
        Statement statement = mock(Statement.class);
        when(statement.execute((String) any())).thenThrow(new SQLException());
        Connection connection = mock(Connection.class);
        doNothing().when(connection).close();
        when(connection.createStatement()).thenReturn(statement);

        // Act and Assert
        assertThrows(HibernateException.class,
                () -> multiTenantConnectionProviderImpl.releaseConnection("42", connection));
        verify(connection).createStatement();
        verify(statement).execute((String) any());
    }

    /**
     * Method under test: {@link MultiTenantConnectionProviderImpl#releaseConnection(String, Connection)}
     */
    @Test
    void testReleaseConnection3() throws SQLException {
        // Arrange
        Statement statement = mock(Statement.class);
        when(statement.execute((String) any())).thenThrow(new HibernateException("An error occurred"));
        Connection connection = mock(Connection.class);
        doNothing().when(connection).close();
        when(connection.createStatement()).thenReturn(statement);

        // Act and Assert
        assertThrows(HibernateException.class,
                () -> multiTenantConnectionProviderImpl.releaseConnection("42", connection));
        verify(connection).createStatement();
        verify(statement).execute((String) any());
    }

    /**
     * Method under test: {@link MultiTenantConnectionProviderImpl#isUnwrappableAs(Class)}
     */
    @Test
    void testIsUnwrappableAs() {
        // Arrange, Act and Assert
        assertFalse(multiTenantConnectionProviderImpl.isUnwrappableAs(Object.class));
    }

    /**
     * Method under test: {@link MultiTenantConnectionProviderImpl#unwrap(Class)}
     */
    @Test
    void testUnwrap() {
        // Arrange, Act and Assert
        assertNull(multiTenantConnectionProviderImpl.unwrap(Object.class));
    }

    /**
     * Method under test: {@link MultiTenantConnectionProviderImpl#supportsAggressiveRelease()}
     */
    @Test
    void testSupportsAggressiveRelease() {
        // Arrange, Act and Assert
        assertTrue(multiTenantConnectionProviderImpl.supportsAggressiveRelease());
    }

    /**
     * Method under test: {@link MultiTenantConnectionProviderImpl#injectServices(ServiceRegistryImplementor)}
     */
    @Test
    void testInjectServices3() {
        // Arrange
        ServiceRegistryImplementor serviceRegistryImplementor = mock(ServiceRegistryImplementor.class);
        when(serviceRegistryImplementor.getService((Class<ConfigurationService>) any()))
                .thenReturn(new ConfigurationServiceImpl(new HashMap<>()));

        // Act
        multiTenantConnectionProviderImpl.injectServices(serviceRegistryImplementor);

        // Assert
        verify(serviceRegistryImplementor).getService((Class<ConfigurationService>) any());
    }
}

