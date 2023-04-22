package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.AccountTypeCreationRepository;
import com.ets.SecurePharmacy.tenant.model.AccountHeadCreationEntity;
import com.ets.SecurePharmacy.tenant.model.AccountTypeCreationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {AccountTypeCreationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AccountTypeCreationServiceImplTest {
    @MockBean
    private AccountTypeCreationRepository accountTypeCreationRepository;

    @Autowired
    private AccountTypeCreationServiceImpl accountTypeCreationServiceImpl;

    /**
     * Method under test: {@link AccountTypeCreationServiceImpl#getAllAccountTypes()}
     */
    @Test
    void testGetAllAccountTypes() {
        // Arrange
        when(accountTypeCreationRepository.findAll())
                .thenReturn((Iterable<AccountTypeCreationEntity>) mock(Iterable.class));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> accountTypeCreationServiceImpl.getAllAccountTypes());
        verify(accountTypeCreationRepository).findAll();
    }

    /**
     * Method under test: {@link AccountTypeCreationServiceImpl#getAllAccountTypes()}
     */
    @Test
    void testGetAllAccountTypes2() {
        AccountTypeCreationEntity accountTypeCreationEntity = new AccountTypeCreationEntity();
        accountTypeCreationEntity.setId(1L);
        accountTypeCreationEntity.setAccountTypeName("account type");
        accountTypeCreationEntity.setStatus("status");
        List<AccountTypeCreationEntity> accountTypeCreationEntityArrayList = new ArrayList<>();
        accountTypeCreationEntityArrayList.add(accountTypeCreationEntity);
        // Arrange
        when(accountTypeCreationRepository.findAll()).thenReturn(accountTypeCreationEntityArrayList);

        // Act and Assert
        assertSame(accountTypeCreationEntityArrayList,accountTypeCreationServiceImpl.getAllAccountTypes());
        verify(accountTypeCreationRepository).findAll();
    }
}

