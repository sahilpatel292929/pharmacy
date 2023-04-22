package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.AccountRepository;
import com.ets.SecurePharmacy.tenant.model.AccountEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {AccountServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AccountServiceImplTest {
    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountServiceImpl accountServiceImpl;

    /**
     * Method under test: {@link AccountServiceImpl#getAllAccounts()}
     */
    @Test
    void testGetAllAccounts() {
        // Arrange
        when(accountRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(accountServiceImpl.getAllAccounts().isEmpty());
        verify(accountRepository).findAll();
    }

    /**
     * Method under test: {@link AccountServiceImpl#getAllAccounts()}
     */
    @Test
    void testGetAllAccounts2() {
        // Arrange
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAddress1("42 Main St");
        accountEntity.setAddress2("42 Main St");
        accountEntity.setCompanyName("Company Name");
        accountEntity.setEmailId("42");
        accountEntity.setId(123L);
        accountEntity.setLogoUrl("https://example.org/example");
        accountEntity.setMobileNumber("42");
        accountEntity.setPostalCode("Postal Code");
        accountEntity.setUserName("janedoe");

        ArrayList<AccountEntity> accountEntityList = new ArrayList<>();
        accountEntityList.add(accountEntity);
        when(accountRepository.findAll()).thenReturn(accountEntityList);

        // Act
        List<AccountEntity> actualAllAccounts = accountServiceImpl.getAllAccounts();

        // Assert
        assertSame(accountEntityList, actualAllAccounts);
        assertEquals(1, actualAllAccounts.size());
        verify(accountRepository).findAll();
    }

    /**
     * Method under test: {@link AccountServiceImpl#getAllAccounts()}
     */
    @Test
    void testGetAllAccounts3() {
        // Arrange
        when(accountRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> accountServiceImpl.getAllAccounts());
        verify(accountRepository).findAll();
    }

    /**
     * Method under test: {@link AccountServiceImpl#getAccountsById(Long)}
     */
    @Test
    void testGetAccountsById() throws RecordNotFoundException {
        // Arrange
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAddress1("42 Main St");
        accountEntity.setAddress2("42 Main St");
        accountEntity.setCompanyName("Company Name");
        accountEntity.setEmailId("42");
        accountEntity.setId(123L);
        accountEntity.setLogoUrl("https://example.org/example");
        accountEntity.setMobileNumber("42");
        accountEntity.setPostalCode("Postal Code");
        accountEntity.setUserName("janedoe");
        Optional<AccountEntity> ofResult = Optional.of(accountEntity);
        when(accountRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(accountEntity, accountServiceImpl.getAccountsById(123L));
        verify(accountRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AccountServiceImpl#getAccountsById(Long)}
     */
    @Test
    void testGetAccountsById2() throws RecordNotFoundException {
        // Arrange
        when(accountRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> accountServiceImpl.getAccountsById(123L));
        verify(accountRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AccountServiceImpl#getAccountsById(Long)}
     */
    @Test
    void testGetAccountsById3() throws RecordNotFoundException {
        // Arrange
        when(accountRepository.findById((Long) any())).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> accountServiceImpl.getAccountsById(123L));
        verify(accountRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AccountServiceImpl#createOrUpdateAccounts(AccountEntity)}
     */
    @Test
    void testCreateOrUpdateAccounts() {
        // Arrange
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAddress1("42 Main St");
        accountEntity.setAddress2("42 Main St");
        accountEntity.setCompanyName("Company Name");
        accountEntity.setEmailId("42");
        accountEntity.setId(123L);
        accountEntity.setLogoUrl("https://example.org/example");
        accountEntity.setMobileNumber("42");
        accountEntity.setPostalCode("Postal Code");
        accountEntity.setUserName("janedoe");
        when(accountRepository.save((AccountEntity) any())).thenReturn(accountEntity);

        AccountEntity accountEntity1 = new AccountEntity();
        accountEntity1.setAddress1("42 Main St");
        accountEntity1.setAddress2("42 Main St");
        accountEntity1.setCompanyName("Company Name");
        accountEntity1.setEmailId("42");
        accountEntity1.setId(123L);
        accountEntity1.setLogoUrl("https://example.org/example");
        accountEntity1.setMobileNumber("42");
        accountEntity1.setPostalCode("Postal Code");
        accountEntity1.setUserName("janedoe");

        // Act and Assert
        assertSame(accountEntity, accountServiceImpl.createOrUpdateAccounts(accountEntity1));
        verify(accountRepository).save((AccountEntity) any());
    }

    /**
     * Method under test: {@link AccountServiceImpl#createOrUpdateAccounts(AccountEntity)}
     */
    @Test
    void testCreateOrUpdateAccounts2() {
        // Arrange
        when(accountRepository.save((AccountEntity) any())).thenThrow(new UnableToProcessException("An error occurred"));

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAddress1("42 Main St");
        accountEntity.setAddress2("42 Main St");
        accountEntity.setCompanyName("Company Name");
        accountEntity.setEmailId("42");
        accountEntity.setId(123L);
        accountEntity.setLogoUrl("https://example.org/example");
        accountEntity.setMobileNumber("42");
        accountEntity.setPostalCode("Postal Code");
        accountEntity.setUserName("janedoe");

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> accountServiceImpl.createOrUpdateAccounts(accountEntity));
        verify(accountRepository).save((AccountEntity) any());
    }

    /**
     * Method under test: {@link AccountServiceImpl#createOrUpdateAccountsBulk(List)}
     */
    @Test
    void testCreateOrUpdateAccountsBulk() {
        // Arrange
        when(accountRepository.saveAll((Iterable<AccountEntity>) any()))
                .thenReturn((Iterable<AccountEntity>) mock(Iterable.class));

        // Act
        accountServiceImpl.createOrUpdateAccountsBulk(new ArrayList<>());

        // Assert that nothing has changed
        verify(accountRepository).saveAll((Iterable<AccountEntity>) any());
        assertTrue(accountServiceImpl.getAllAccounts().isEmpty());
    }

    /**
     * Method under test: {@link AccountServiceImpl#createOrUpdateAccountsBulk(List)}
     */
    @Test
    void testCreateOrUpdateAccountsBulk2() {
        // Arrange
        when(accountRepository.saveAll((Iterable<AccountEntity>) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> accountServiceImpl.createOrUpdateAccountsBulk(new ArrayList<>()));
        verify(accountRepository).saveAll((Iterable<AccountEntity>) any());
    }

    /**
     * Method under test: {@link AccountServiceImpl#deleteAccountsById(Long)}
     */
    @Test
    void testDeleteAccountsById() throws RecordNotFoundException {
        // Arrange
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAddress1("42 Main St");
        accountEntity.setAddress2("42 Main St");
        accountEntity.setCompanyName("Company Name");
        accountEntity.setEmailId("42");
        accountEntity.setId(123L);
        accountEntity.setLogoUrl("https://example.org/example");
        accountEntity.setMobileNumber("42");
        accountEntity.setPostalCode("Postal Code");
        accountEntity.setUserName("janedoe");
        Optional<AccountEntity> ofResult = Optional.of(accountEntity);
        doNothing().when(accountRepository).deleteById((Long) any());
        when(accountRepository.findById((Long) any())).thenReturn(ofResult);

        // Act
        accountServiceImpl.deleteAccountsById(123L);

        // Assert that nothing has changed
        verify(accountRepository).findById((Long) any());
        verify(accountRepository).deleteById((Long) any());
        assertTrue(accountServiceImpl.getAllAccounts().isEmpty());
    }

    /**
     * Method under test: {@link AccountServiceImpl#deleteAccountsById(Long)}
     */
    @Test
    void testDeleteAccountsById2() throws RecordNotFoundException {
        // Arrange
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAddress1("42 Main St");
        accountEntity.setAddress2("42 Main St");
        accountEntity.setCompanyName("Company Name");
        accountEntity.setEmailId("42");
        accountEntity.setId(123L);
        accountEntity.setLogoUrl("https://example.org/example");
        accountEntity.setMobileNumber("42");
        accountEntity.setPostalCode("Postal Code");
        accountEntity.setUserName("janedoe");
        when(accountRepository.findById((Long) any())).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> accountServiceImpl.deleteAccountsById(123L));
        verify(accountRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AccountServiceImpl#deleteAccountsById(Long)}
     */
    @Test
    void testDeleteAccountsById3() throws RecordNotFoundException {
        // Arrange
        doNothing().when(accountRepository).deleteById((Long) any());
        when(accountRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> accountServiceImpl.deleteAccountsById(123L));
        verify(accountRepository).findById((Long) any());
    }
}

