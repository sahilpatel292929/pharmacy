package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.CashDepositRepository;
import com.ets.SecurePharmacy.tenant.model.CashDepositEntity;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CashDepositServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CashDepositServiceImplTest {
    @MockBean
    private CashDepositRepository cashDepositRepository;

    @Autowired
    private CashDepositServiceImpl cashDepositServiceImpl;

    /**
     * Method under test: {@link CashDepositServiceImpl#getAllCashDeposit()}
     */
    @Test
    void testGetAllCashDeposit() {
        // Arrange
        when(cashDepositRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(cashDepositServiceImpl.getAllCashDeposit().isEmpty());
        verify(cashDepositRepository).findAll();
    }

    /**
     * Method under test: {@link CashDepositServiceImpl#getAllCashDeposit()}
     */
    @Test
    void testGetAllCashDeposit2() {
        // Arrange
        CashDepositEntity cashDepositEntity = new CashDepositEntity();
        cashDepositEntity.setAmount(10.0d);
        cashDepositEntity.setBankName("Bank Name");
        cashDepositEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        cashDepositEntity.setId(123L);
        cashDepositEntity.setStatus("Status");

        ArrayList<CashDepositEntity> cashDepositEntityList = new ArrayList<>();
        cashDepositEntityList.add(cashDepositEntity);
        when(cashDepositRepository.findAll()).thenReturn(cashDepositEntityList);

        // Act
        List<CashDepositEntity> actualAllCashDeposit = cashDepositServiceImpl.getAllCashDeposit();

        // Assert
        assertSame(cashDepositEntityList, actualAllCashDeposit);
        assertEquals(1, actualAllCashDeposit.size());
        verify(cashDepositRepository).findAll();
    }

    /**
     * Method under test: {@link CashDepositServiceImpl#getAllCashDeposit()}
     */
    @Test
    void testGetAllCashDeposit3() {
        // Arrange
        when(cashDepositRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> cashDepositServiceImpl.getAllCashDeposit());
        verify(cashDepositRepository).findAll();
    }

    /**
     * Method under test: {@link CashDepositServiceImpl#getCashDepositById(Long)}
     */
    @Test
    void testGetCashDepositById() throws RecordNotFoundException {
        // Arrange
        CashDepositEntity cashDepositEntity = new CashDepositEntity();
        cashDepositEntity.setAmount(10.0d);
        cashDepositEntity.setBankName("Bank Name");
        cashDepositEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        cashDepositEntity.setId(123L);
        cashDepositEntity.setStatus("Status");
        Optional<CashDepositEntity> ofResult = Optional.of(cashDepositEntity);
        when(cashDepositRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(cashDepositEntity, cashDepositServiceImpl.getCashDepositById(123L));
        verify(cashDepositRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CashDepositServiceImpl#getCashDepositById(Long)}
     */
    @Test
    void testGetCashDepositById2() throws RecordNotFoundException {
        // Arrange
        when(cashDepositRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> cashDepositServiceImpl.getCashDepositById(123L));
        verify(cashDepositRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CashDepositServiceImpl#getCashDepositById(Long)}
     */
    @Test
    void testGetCashDepositById3() throws RecordNotFoundException {
        // Arrange
        when(cashDepositRepository.findById((Long) any())).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> cashDepositServiceImpl.getCashDepositById(123L));
        verify(cashDepositRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CashDepositServiceImpl#createOrUpdateCashDeposit(CashDepositEntity)}
     */
    @Test
    void testCreateOrUpdateCashDeposit() {
        // Arrange
        CashDepositEntity cashDepositEntity = new CashDepositEntity();
        cashDepositEntity.setAmount(10.0d);
        cashDepositEntity.setBankName("Bank Name");
        cashDepositEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        cashDepositEntity.setId(123L);
        cashDepositEntity.setStatus("Status");
        when(cashDepositRepository.save((CashDepositEntity) any())).thenReturn(cashDepositEntity);

        CashDepositEntity cashDepositEntity1 = new CashDepositEntity();
        cashDepositEntity1.setAmount(10.0d);
        cashDepositEntity1.setBankName("Bank Name");
        cashDepositEntity1.setEntryDate(LocalDate.ofEpochDay(1L));
        cashDepositEntity1.setId(123L);
        cashDepositEntity1.setStatus("Status");

        // Act and Assert
        assertSame(cashDepositEntity, cashDepositServiceImpl.createOrUpdateCashDeposit(cashDepositEntity1));
        verify(cashDepositRepository).save((CashDepositEntity) any());
    }

    /**
     * Method under test: {@link CashDepositServiceImpl#createOrUpdateCashDeposit(CashDepositEntity)}
     */
    @Test
    void testCreateOrUpdateCashDeposit2() {
        // Arrange
        when(cashDepositRepository.save((CashDepositEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        CashDepositEntity cashDepositEntity = new CashDepositEntity();
        cashDepositEntity.setAmount(10.0d);
        cashDepositEntity.setBankName("Bank Name");
        cashDepositEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        cashDepositEntity.setId(123L);
        cashDepositEntity.setStatus("Status");

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> cashDepositServiceImpl.createOrUpdateCashDeposit(cashDepositEntity));
        verify(cashDepositRepository).save((CashDepositEntity) any());
    }

    /**
     * Method under test: {@link CashDepositServiceImpl#updateCashDeposit(CashDepositEntity)}
     */
    @Test
    void testUpdateCashDeposit() {
        // Arrange
        CashDepositEntity cashDepositEntity = new CashDepositEntity();
        cashDepositEntity.setAmount(10.0d);
        cashDepositEntity.setBankName("Bank Name");
        cashDepositEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        cashDepositEntity.setId(123L);
        cashDepositEntity.setStatus("Status");
        when(cashDepositRepository.save((CashDepositEntity) any())).thenReturn(cashDepositEntity);

        CashDepositEntity cashDepositEntity1 = new CashDepositEntity();
        cashDepositEntity1.setAmount(10.0d);
        cashDepositEntity1.setBankName("Bank Name");
        cashDepositEntity1.setEntryDate(LocalDate.ofEpochDay(1L));
        cashDepositEntity1.setId(123L);
        cashDepositEntity1.setStatus("Status");

        // Act and Assert
        assertSame(cashDepositEntity, cashDepositServiceImpl.updateCashDeposit(cashDepositEntity1));
        verify(cashDepositRepository).save((CashDepositEntity) any());
    }

    /**
     * Method under test: {@link CashDepositServiceImpl#updateCashDeposit(CashDepositEntity)}
     */
    @Test
    void testUpdateCashDeposit2() {
        // Arrange
        when(cashDepositRepository.save((CashDepositEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        CashDepositEntity cashDepositEntity = new CashDepositEntity();
        cashDepositEntity.setAmount(10.0d);
        cashDepositEntity.setBankName("Bank Name");
        cashDepositEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        cashDepositEntity.setId(123L);
        cashDepositEntity.setStatus("Status");

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> cashDepositServiceImpl.updateCashDeposit(cashDepositEntity));
        verify(cashDepositRepository).save((CashDepositEntity) any());
    }

    /**
     * Method under test: {@link CashDepositServiceImpl#getAllDepositsWithPagination(int, int)}
     */
    @Test
    void testGetAllDepositsWithPagination() {
        // Arrange
        PageImpl<CashDepositEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(cashDepositRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<CashDepositEntity> actualAllDepositsWithPagination = cashDepositServiceImpl.getAllDepositsWithPagination(2,
                3);

        // Assert
        assertSame(pageImpl, actualAllDepositsWithPagination);
        assertTrue(actualAllDepositsWithPagination.toList().isEmpty());
        verify(cashDepositRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link CashDepositServiceImpl#getAllDepositsWithPagination(int, int)}
     */
    @Test
    void testGetAllDepositsWithPagination2() {
        // Arrange
        when(cashDepositRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> cashDepositServiceImpl.getAllDepositsWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link CashDepositServiceImpl#getAllDepositsWithPagination(int, int)}
     */
    @Test
    void testGetAllDepositsWithPagination3() {
        // Arrange
        when(cashDepositRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> cashDepositServiceImpl.getAllDepositsWithPagination(2, 3));
        verify(cashDepositRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link CashDepositServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<CashDepositEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(cashDepositRepository.findAllByBankNameContains((String) any(), (Pageable) any())).thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<CashDepositEntity> actualSearch = cashDepositServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(cashDepositRepository).findAllByBankNameContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link CashDepositServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(cashDepositRepository.findAllByBankNameContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> cashDepositServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(cashDepositRepository).findAllByBankNameContains((String) any(), (Pageable) any());
    }
}

