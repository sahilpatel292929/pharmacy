package com.ets.SecurePharmacy.config.dbConfig;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryDelegatingImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.orm.jpa.DefaultJpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(classes = {AsynceService.class, JpaProperties.class})
@ExtendWith(SpringExtension.class)
@TestPropertySource(value = "classpath:application-test.properties")
class AsynceServiceTest {
    @Autowired
    private AsynceService asynceService;

    @Autowired
    private JpaProperties jpaProperties;

    @MockBean
    private MultiTenantConnectionProviderImpl multiTenantConnectionProviderImpl;

    @MockBean
    private TenantSchemaResolver tenantSchemaResolver;

    /**
     * Method under test: {@link AsynceService#transactionManager(EntityManagerFactory)}
     */
    @Test
    void testTransactionManager() {
        // Arrange
        SessionFactoryDelegatingImpl sessionFactoryDelegatingImpl = new SessionFactoryDelegatingImpl(
                new SessionFactoryDelegatingImpl(new SessionFactoryDelegatingImpl(new SessionFactoryDelegatingImpl(null))));

        // Act
        JpaTransactionManager actualTransactionManagerResult = asynceService
                .transactionManager(sessionFactoryDelegatingImpl);

        // Assert
        assertFalse(actualTransactionManagerResult.isValidateExistingTransaction());
        assertFalse(actualTransactionManagerResult.isRollbackOnCommitFailure());
        assertTrue(actualTransactionManagerResult.isNestedTransactionAllowed());
        assertTrue(actualTransactionManagerResult.isGlobalRollbackOnParticipationFailure());
        assertFalse(actualTransactionManagerResult.isFailEarlyOnGlobalRollbackOnly());
        assertEquals(0, actualTransactionManagerResult.getTransactionSynchronization());
        assertSame(sessionFactoryDelegatingImpl, actualTransactionManagerResult.getResourceFactory());
        assertEquals(-1, actualTransactionManagerResult.getDefaultTimeout());
        assertTrue(actualTransactionManagerResult.getJpaDialect() instanceof DefaultJpaDialect);
    }

    /**
     * Method under test: {@link AsynceService#entity(DataSource, MultiTenantConnectionProvider, CurrentTenantIdentifierResolver)}
     */
    @Test
    void testEntity() {
        DataSource dataSource = Mockito.mock(DataSource.class);
        MultiTenantConnectionProvider connectionProvider = Mockito.mock(MultiTenantConnectionProvider.class);
        CurrentTenantIdentifierResolver tenantResolver = Mockito.mock(CurrentTenantIdentifierResolver.class);
        Assertions.assertThrows(Exception.class, () -> asynceService.entity(dataSource, connectionProvider, tenantResolver));
    }

    @Test
    void testAddTenant() {
        Assertions.assertThrows(Exception.class, () -> asynceService.addTenant("sample"));
    }

    @Test
    void testConstructDataSource() {
        AsynceService asynceService1 = Mockito.mock(AsynceService.class);
        Mockito.when(asynceService1.entity(Mockito.any(DataSource.class), Mockito.any(MultiTenantConnectionProvider.class), Mockito.any(CurrentTenantIdentifierResolver.class)))
            .thenReturn(Mockito.mock(LocalContainerEntityManagerFactoryBean.class));
        Assertions.assertNull(asynceService1.constructDataSource("sample"));
    }
}

