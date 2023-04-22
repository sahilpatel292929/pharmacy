package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.AccountHeadCreationRepository;
import com.ets.SecurePharmacy.tenant.model.AccountHeadCreationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {AccountHeadCreationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AccountHeadCreationServiceImplTest {
    @MockBean
    private AccountHeadCreationRepository accountHeadCreationRepository;

    @Autowired
    private AccountHeadCreationServiceImpl accountHeadCreationServiceImpl;

    /**
     * Method under test: {@link AccountHeadCreationServiceImpl#getAllAccountHead()}
     */
    @Test
    void testGetAllAccountHead() {
        // Arrange
        when(accountHeadCreationRepository.findAll())
                .thenReturn((Iterable<AccountHeadCreationEntity>) mock(Iterable.class));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> accountHeadCreationServiceImpl.getAllAccountHead());
        verify(accountHeadCreationRepository).findAll();
    }

    /**
     * Method under test: {@link AccountHeadCreationServiceImpl#getAllAccountHead()}
     */
    @Test
    void testGetAllAccountHead2() {
        AccountHeadCreationEntity accountHeadCreationEntity = new AccountHeadCreationEntity();
        accountHeadCreationEntity.setId(1L);
        accountHeadCreationEntity.setAccountHeadName("account head name");
        accountHeadCreationEntity.setStatus("status");
        List<AccountHeadCreationEntity> accountHeadCreationEntityList = new ArrayList<>();
        accountHeadCreationEntityList.add(accountHeadCreationEntity);
        // Arrange
        when(accountHeadCreationRepository.findAll()).thenReturn(accountHeadCreationEntityList);

        // Act and Assert
        assertSame(accountHeadCreationEntityList,accountHeadCreationServiceImpl.getAllAccountHead());
        verify(accountHeadCreationRepository).findAll();
    }

    /**
     * Method under test: {@link AccountHeadCreationServiceImpl#getAllAccountHead()}
     */
    @Test
    void testGetAllAccountHead3() {

        // Arrange
        when(accountHeadCreationRepository.findAll()).thenReturn(null);
        when(accountHeadCreationServiceImpl.getAllAccountHead()).thenReturn(Collections.emptyList());

        // Act and Assert
        assertSame(Collections.emptyList(),accountHeadCreationServiceImpl.getAllAccountHead());
        verify(accountHeadCreationRepository).findAll();
    }
}

