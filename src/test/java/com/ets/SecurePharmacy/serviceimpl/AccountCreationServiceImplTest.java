package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.AccountCreationRepository;
import com.ets.SecurePharmacy.tenant.model.AccountCreationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {AccountCreationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AccountCreationServiceImplTest {
    @MockBean
    private AccountCreationRepository accountCreationRepository;

    @Autowired
    private AccountCreationServiceImpl accountCreationServiceImpl;

    /**
     * Method under test: {@link AccountCreationServiceImpl#getAllAccounts()}
     */
    @Test
    void testGetAllAccounts() {
        when(accountCreationRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(accountCreationServiceImpl.getAllAccounts().isEmpty());
        verify(accountCreationRepository).findAll();
    }

    /**
     * Method under test: {@link AccountCreationServiceImpl#getAllAccounts()}
     */
    @Test
    void testGetAllAccounts2() {
        AccountCreationEntity accountCreationEntity = new AccountCreationEntity();
        accountCreationEntity.setActGroup("Act Group");
        accountCreationEntity.setActName("Act Name");
        accountCreationEntity.setActType("Act Type");
        accountCreationEntity.setCreated_by(1);
        accountCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        accountCreationEntity.setId(123L);
        accountCreationEntity.setOpeningBal("Opening Bal");
        accountCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        accountCreationEntity.setStatus("Status");
        accountCreationEntity.setUpdated_by(1);
        accountCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        ArrayList<AccountCreationEntity> accountCreationEntityList = new ArrayList<>();
        accountCreationEntityList.add(accountCreationEntity);
        when(accountCreationRepository.findAll()).thenReturn(accountCreationEntityList);
        List<AccountCreationEntity> actualAllAccounts = accountCreationServiceImpl.getAllAccounts();
        assertSame(accountCreationEntityList, actualAllAccounts);
        assertEquals(1, actualAllAccounts.size());
        verify(accountCreationRepository).findAll();
    }

    /**
     * Method under test: {@link AccountCreationServiceImpl#getAllAccounts()}
     */
    @Test
    void testGetAllAccounts3() {
        when(accountCreationRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));
        assertThrows(UnableToProcessException.class, () -> accountCreationServiceImpl.getAllAccounts());
        verify(accountCreationRepository).findAll();
    }

    /**
     * Method under test: {@link AccountCreationServiceImpl#getAccountsById(Long)}
     */
    @Test
    void testGetAccountsById() throws RecordNotFoundException {
        AccountCreationEntity accountCreationEntity = new AccountCreationEntity();
        accountCreationEntity.setActGroup("Act Group");
        accountCreationEntity.setActName("Act Name");
        accountCreationEntity.setActType("Act Type");
        accountCreationEntity.setCreated_by(1);
        accountCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        accountCreationEntity.setId(123L);
        accountCreationEntity.setOpeningBal("Opening Bal");
        accountCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        accountCreationEntity.setStatus("Status");
        accountCreationEntity.setUpdated_by(1);
        accountCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));
        Optional<AccountCreationEntity> ofResult = Optional.of(accountCreationEntity);
        when(accountCreationRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(accountCreationEntity, accountCreationServiceImpl.getAccountsById(123L));
        verify(accountCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AccountCreationServiceImpl#getAccountsById(Long)}
     */
    @Test
    void testGetAccountsById2() throws RecordNotFoundException {
        when(accountCreationRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(RecordNotFoundException.class, () -> accountCreationServiceImpl.getAccountsById(123L));
        verify(accountCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AccountCreationServiceImpl#getAccountsById(Long)}
     */
    @Test
    void testGetAccountsById3() throws RecordNotFoundException {
        when(accountCreationRepository.findById((Long) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));
        assertThrows(UnableToProcessException.class, () -> accountCreationServiceImpl.getAccountsById(123L));
        verify(accountCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AccountCreationServiceImpl#createOrUpdateAccounts(AccountCreationEntity)}
     */
    @Test
    void testCreateOrUpdateAccounts() {
        AccountCreationEntity accountCreationEntity = new AccountCreationEntity();
        accountCreationEntity.setActGroup("Act Group");
        accountCreationEntity.setActName("Act Name");
        accountCreationEntity.setActType("Act Type");
        accountCreationEntity.setCreated_by(1);
        accountCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        accountCreationEntity.setId(123L);
        accountCreationEntity.setOpeningBal("Opening Bal");
        accountCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        accountCreationEntity.setStatus("Status");
        accountCreationEntity.setUpdated_by(1);
        accountCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));
        when(accountCreationRepository.save((AccountCreationEntity) any())).thenReturn(accountCreationEntity);
        when(accountCreationRepository.findByActName((String) any())).thenReturn(new ArrayList<>());

        assertSame(accountCreationEntity, accountCreationServiceImpl.createOrUpdateAccounts(accountCreationEntity));
        verify(accountCreationRepository).save((AccountCreationEntity) any());
        verify(accountCreationRepository).findByActName((String) any());
    }

    /**
     * Method under test: {@link AccountCreationServiceImpl#createOrUpdateAccounts(AccountCreationEntity)}
     */
    @Test
    void testCreateOrUpdateAccounts2() {
        AccountCreationEntity accountCreationEntity = new AccountCreationEntity();
        accountCreationEntity.setActGroup("Act Group");
        accountCreationEntity.setActName("Act Name");
        accountCreationEntity.setActType("Act Type");
        accountCreationEntity.setCreated_by(1);
        accountCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        accountCreationEntity.setId(123L);
        accountCreationEntity.setOpeningBal("Opening Bal");
        accountCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        accountCreationEntity.setStatus("Status");
        accountCreationEntity.setUpdated_by(1);
        accountCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));


        ArrayList<AccountCreationEntity> accountCreationEntityList = new ArrayList<>();
        accountCreationEntityList.add(accountCreationEntity);
        when(accountCreationRepository.save((AccountCreationEntity) any())).thenReturn(accountCreationEntity);
        when(accountCreationRepository.findByActName((String) any())).thenReturn(accountCreationEntityList);

        assertThrows(RecordAlreadyPresentException.class,
                () -> accountCreationServiceImpl.createOrUpdateAccounts(accountCreationEntity));
        verify(accountCreationRepository).findByActName((String) any());
    }

    /**
     * Method under test: {@link AccountCreationServiceImpl#createOrUpdateAccounts(AccountCreationEntity)}
     */
    @Test
    void testCreateOrUpdateAccounts3() {
        when(accountCreationRepository.save((AccountCreationEntity) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));
        when(accountCreationRepository.findByActName((String) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));

        AccountCreationEntity accountCreationEntity = new AccountCreationEntity();
        accountCreationEntity.setActGroup("Act Group");
        accountCreationEntity.setActName("Act Name");
        accountCreationEntity.setActType("Act Type");
        accountCreationEntity.setCreated_by(1);
        accountCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        accountCreationEntity.setId(123L);
        accountCreationEntity.setOpeningBal("Opening Bal");
        accountCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        accountCreationEntity.setStatus("Status");
        accountCreationEntity.setUpdated_by(1);
        accountCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));
        assertThrows(UnableToProcessException.class,
                () -> accountCreationServiceImpl.createOrUpdateAccounts(accountCreationEntity));
        verify(accountCreationRepository).findByActName((String) any());
    }

    /**
     * Method under test: {@link AccountCreationServiceImpl#createOrUpdateAccountsBulk(List)}
     */
    @Test
    void testCreateOrUpdateAccountsBulk() {
        when(accountCreationRepository.saveAll((Iterable<AccountCreationEntity>) any())).thenReturn(new ArrayList<>());
        accountCreationServiceImpl.createOrUpdateAccountsBulk(new ArrayList<>());
        verify(accountCreationRepository).saveAll((Iterable<AccountCreationEntity>) any());
        assertTrue(accountCreationServiceImpl.getPaymentModes().isEmpty());
    }

    /**
     * Method under test: {@link AccountCreationServiceImpl#createOrUpdateAccountsBulk(List)}
     */
    @Test
    void testCreateOrUpdateAccountsBulk2() {
        when(accountCreationRepository.saveAll((Iterable<AccountCreationEntity>) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));
        assertThrows(UnableToProcessException.class,
                () -> accountCreationServiceImpl.createOrUpdateAccountsBulk(new ArrayList<>()));
        verify(accountCreationRepository).saveAll((Iterable<AccountCreationEntity>) any());
    }

    /**
     * Method under test: {@link AccountCreationServiceImpl#deleteAccountsById(Long)}
     */
    @Test
    void testDeleteAccountsById() throws RecordNotFoundException {
        AccountCreationEntity accountCreationEntity = new AccountCreationEntity();
        accountCreationEntity.setActGroup("Act Group");
        accountCreationEntity.setActName("Act Name");
        accountCreationEntity.setActType("Act Type");
        accountCreationEntity.setCreated_by(1);
        accountCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        accountCreationEntity.setId(123L);
        accountCreationEntity.setOpeningBal("Opening Bal");
        accountCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        accountCreationEntity.setStatus("Status");
        accountCreationEntity.setUpdated_by(1);
        accountCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));
        Optional<AccountCreationEntity> ofResult = Optional.of(accountCreationEntity);
        doNothing().when(accountCreationRepository).deleteById((Long) any());
        when(accountCreationRepository.findById((Long) any())).thenReturn(ofResult);
        accountCreationServiceImpl.deleteAccountsById(123L);
        verify(accountCreationRepository).findById((Long) any());
        verify(accountCreationRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link AccountCreationServiceImpl#deleteAccountsById(Long)}
     */
    @Test
    void testDeleteAccountsById2() throws RecordNotFoundException {
        AccountCreationEntity accountCreationEntity = new AccountCreationEntity();
        accountCreationEntity.setActGroup("Act Group");
        accountCreationEntity.setActName("Act Name");
        accountCreationEntity.setActType("Act Type");
        accountCreationEntity.setCreated_by(1);
        accountCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        accountCreationEntity.setId(123L);
        accountCreationEntity.setOpeningBal("Opening Bal");
        accountCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        accountCreationEntity.setStatus("Status");
        accountCreationEntity.setUpdated_by(1);
        accountCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));
        Optional<AccountCreationEntity> ofResult = Optional.of(accountCreationEntity);

        when(accountCreationRepository.findById((Long) any())).thenThrow(new UnableToProcessException("An error occurred"));
        assertThrows(UnableToProcessException.class, () -> accountCreationServiceImpl.deleteAccountsById(123L));
        verify(accountCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AccountCreationServiceImpl#deleteAccountsById(Long)}
     */
    @Test
    void testDeleteAccountsById3() throws RecordNotFoundException {
        doNothing().when(accountCreationRepository).deleteById((Long) any());
        when(accountCreationRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(RecordNotFoundException.class, () -> accountCreationServiceImpl.deleteAccountsById(123L));
        verify(accountCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AccountCreationServiceImpl#getAllAccountsWithPagination(int, int)}
     */
    @Test
    void testGetAllAccountsWithPagination() {
        PageImpl<AccountCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(accountCreationRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        Page<AccountCreationEntity> actualAllAccountsWithPagination = accountCreationServiceImpl
                .getAllAccountsWithPagination(2, 3);
        assertSame(pageImpl, actualAllAccountsWithPagination);
        assertTrue(actualAllAccountsWithPagination.toList().isEmpty());
        verify(accountCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link AccountCreationServiceImpl#getAllAccountsWithPagination(int, int)}
     */
    @Test
    void testGetAllAccountsWithPagination2() {
        when(accountCreationRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        assertThrows(UnableToProcessException.class,
                () -> accountCreationServiceImpl.getAllAccountsWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link AccountCreationServiceImpl#getAllAccountsWithPagination(int, int)}
     */
    @Test
    void testGetAllAccountsWithPagination3() {
        when(accountCreationRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));
        assertThrows(UnableToProcessException.class, () -> accountCreationServiceImpl.getAllAccountsWithPagination(2, 3));
        verify(accountCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link AccountCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        PageImpl<AccountCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(accountCreationRepository.findAllByActNameContains((String) any(), (Pageable) any())).thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);
        Page<AccountCreationEntity> actualSearch = accountCreationServiceImpl.getSearch("Query", ofResult);
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(accountCreationRepository).findAllByActNameContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link AccountCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        when(accountCreationRepository.findAllByActNameContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));
        assertThrows(UnableToProcessException.class,
                () -> accountCreationServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(accountCreationRepository).findAllByActNameContains((String) any(), (Pageable) any());
    }

    /**
     * Method under test: {@link AccountCreationServiceImpl#updateAccounts(AccountCreationEntity)}
     */
    @Test
    void testUpdateAccounts() {
        AccountCreationEntity accountCreationEntity = new AccountCreationEntity();
        accountCreationEntity.setActGroup("Act Group");
        accountCreationEntity.setActName("Act Name");
        accountCreationEntity.setActType("Act Type");
        accountCreationEntity.setCreated_by(1);
        accountCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        accountCreationEntity.setId(123L);
        accountCreationEntity.setOpeningBal("Opening Bal");
        accountCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        accountCreationEntity.setStatus("Status");
        accountCreationEntity.setUpdated_by(1);
        accountCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));
        when(accountCreationRepository.save((AccountCreationEntity) any())).thenReturn(accountCreationEntity);

        AccountCreationEntity accountCreationEntity1 = new AccountCreationEntity();
        accountCreationEntity1.setActGroup("Act Group");
        accountCreationEntity1.setActName("Act Name");
        accountCreationEntity1.setActType("Act Type");
        accountCreationEntity1.setCreated_by(1);
        accountCreationEntity1.setCreated_date(LocalDate.ofEpochDay(1L));
        accountCreationEntity1.setId(123L);
        accountCreationEntity1.setOpeningBal("Opening Bal");
        accountCreationEntity1.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        accountCreationEntity1.setStatus("Status");
        accountCreationEntity1.setUpdated_by(1);
        accountCreationEntity1.setUpdated_date(LocalDate.ofEpochDay(1L));
        assertSame(accountCreationEntity, accountCreationServiceImpl.updateAccounts(accountCreationEntity1));
        verify(accountCreationRepository).save((AccountCreationEntity) any());
    }

    /**
     * Method under test: {@link AccountCreationServiceImpl#updateAccounts(AccountCreationEntity)}
     */
    @Test
    void testUpdateAccounts2() {
        when(accountCreationRepository.save((AccountCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        AccountCreationEntity accountCreationEntity = new AccountCreationEntity();
        accountCreationEntity.setActGroup("Act Group");
        accountCreationEntity.setActName("Act Name");
        accountCreationEntity.setActType("Act Type");
        accountCreationEntity.setCreated_by(1);
        accountCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        accountCreationEntity.setId(123L);
        accountCreationEntity.setOpeningBal("Opening Bal");
        accountCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        accountCreationEntity.setStatus("Status");
        accountCreationEntity.setUpdated_by(1);
        accountCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));
        assertThrows(UnableToProcessException.class,
                () -> accountCreationServiceImpl.updateAccounts(accountCreationEntity));
        verify(accountCreationRepository).save((AccountCreationEntity) any());
    }

    /**
     * Method under test: {@link AccountCreationServiceImpl#getPaymentModes()}
     */
    @Test
    void testGetPaymentModes() {
        ArrayList<AccountCreationEntity> accountCreationEntityList = new ArrayList<>();
        when(accountCreationRepository.paymentModes()).thenReturn(accountCreationEntityList);
        List<AccountCreationEntity> actualPaymentModes = accountCreationServiceImpl.getPaymentModes();
        assertSame(accountCreationEntityList, actualPaymentModes);
        assertTrue(actualPaymentModes.isEmpty());
        verify(accountCreationRepository).paymentModes();
    }

    /**
     * Method under test: {@link AccountCreationServiceImpl#getPaymentModes()}
     */
    @Test
    void testGetPaymentModes2() {
        when(accountCreationRepository.paymentModes()).thenThrow(new UnableToProcessException("An error occurred"));
        assertThrows(UnableToProcessException.class, () -> accountCreationServiceImpl.getPaymentModes());
        verify(accountCreationRepository).paymentModes();
    }

    /**
     * Method under test: {@link AccountCreationServiceImpl#getBankNames()}
     */
    @Test
    void testGetBankNames() {
        ArrayList<AccountCreationEntity> accountCreationEntityList = new ArrayList<>();
        when(accountCreationRepository.bankNames()).thenReturn(accountCreationEntityList);
        List<AccountCreationEntity> actualBankNames = accountCreationServiceImpl.getBankNames();
        assertSame(accountCreationEntityList, actualBankNames);
        assertTrue(actualBankNames.isEmpty());
        verify(accountCreationRepository).bankNames();
    }

    /**
     * Method under test: {@link AccountCreationServiceImpl#getBankNames()}
     */
    @Test
    void testGetBankNames2() {
        when(accountCreationRepository.bankNames()).thenThrow(new UnableToProcessException("An error occurred"));
        assertThrows(UnableToProcessException.class, () -> accountCreationServiceImpl.getBankNames());
        verify(accountCreationRepository).bankNames();
    }
}

