package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.CashWithdrawRepository;
import com.ets.SecurePharmacy.tenant.model.CashWithdrawEntity;
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

@ContextConfiguration(classes = {CashWithdrawServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CashWithdrawServiceImplTest {
    @MockBean
    private CashWithdrawRepository cashWithdrawRepository;

    @Autowired
    private CashWithdrawServiceImpl cashWithdrawServiceImpl;

    /**
     * Method under test: {@link CashWithdrawServiceImpl#getAllCashWithdraw()}
     */
    @Test
    void testGetAllCashWithdraw() {
        // Arrange
        when(cashWithdrawRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(cashWithdrawServiceImpl.getAllCashWithdraw().isEmpty());
        verify(cashWithdrawRepository).findAll();
    }

    /**
     * Method under test: {@link CashWithdrawServiceImpl#getAllCashWithdraw()}
     */
    @Test
    void testGetAllCashWithdraw2() {
        // Arrange
        CashWithdrawEntity cashWithdrawEntity = new CashWithdrawEntity();
        cashWithdrawEntity.setAmount(10.0d);
        cashWithdrawEntity.setBankName("Bank Name");
        cashWithdrawEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        cashWithdrawEntity.setId(123L);
        cashWithdrawEntity.setStatus("Status");

        ArrayList<CashWithdrawEntity> cashWithdrawEntityList = new ArrayList<>();
        cashWithdrawEntityList.add(cashWithdrawEntity);
        when(cashWithdrawRepository.findAll()).thenReturn(cashWithdrawEntityList);

        // Act
        List<CashWithdrawEntity> actualAllCashWithdraw = cashWithdrawServiceImpl.getAllCashWithdraw();

        // Assert
        assertSame(cashWithdrawEntityList, actualAllCashWithdraw);
        assertEquals(1, actualAllCashWithdraw.size());
        verify(cashWithdrawRepository).findAll();
    }

    /**
     * Method under test: {@link CashWithdrawServiceImpl#getAllCashWithdraw()}
     */
    @Test
    void testGetAllCashWithdraw3() {
        // Arrange
        when(cashWithdrawRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> cashWithdrawServiceImpl.getAllCashWithdraw());
        verify(cashWithdrawRepository).findAll();
    }

    /**
     * Method under test: {@link CashWithdrawServiceImpl#getCashWithDrawById(Long)}
     */
    @Test
    void testGetCashWithDrawById() throws RecordNotFoundException {
        // Arrange
        CashWithdrawEntity cashWithdrawEntity = new CashWithdrawEntity();
        cashWithdrawEntity.setAmount(10.0d);
        cashWithdrawEntity.setBankName("Bank Name");
        cashWithdrawEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        cashWithdrawEntity.setId(123L);
        cashWithdrawEntity.setStatus("Status");
        Optional<CashWithdrawEntity> ofResult = Optional.of(cashWithdrawEntity);
        when(cashWithdrawRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(cashWithdrawEntity, cashWithdrawServiceImpl.getCashWithDrawById(123L));
        verify(cashWithdrawRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CashWithdrawServiceImpl#getCashWithDrawById(Long)}
     */
    @Test
    void testGetCashWithDrawById2() throws RecordNotFoundException {
        // Arrange
        when(cashWithdrawRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> cashWithdrawServiceImpl.getCashWithDrawById(123L));
        verify(cashWithdrawRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CashWithdrawServiceImpl#getCashWithDrawById(Long)}
     */
    @Test
    void testGetCashWithDrawById3() throws RecordNotFoundException {
        // Arrange
        when(cashWithdrawRepository.findById((Long) any())).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> cashWithdrawServiceImpl.getCashWithDrawById(123L));
        verify(cashWithdrawRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CashWithdrawServiceImpl#createOrUpdateCashWithdraw(CashWithdrawEntity)}
     */
    @Test
    void testCreateOrUpdateCashWithdraw() {
        // Arrange
        CashWithdrawEntity cashWithdrawEntity = new CashWithdrawEntity();
        cashWithdrawEntity.setAmount(10.0d);
        cashWithdrawEntity.setBankName("Bank Name");
        cashWithdrawEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        cashWithdrawEntity.setId(123L);
        cashWithdrawEntity.setStatus("Status");
        when(cashWithdrawRepository.save((CashWithdrawEntity) any())).thenReturn(cashWithdrawEntity);

        CashWithdrawEntity cashWithdrawEntity1 = new CashWithdrawEntity();
        cashWithdrawEntity1.setAmount(10.0d);
        cashWithdrawEntity1.setBankName("Bank Name");
        cashWithdrawEntity1.setEntryDate(LocalDate.ofEpochDay(1L));
        cashWithdrawEntity1.setId(123L);
        cashWithdrawEntity1.setStatus("Status");

        // Act and Assert
        assertSame(cashWithdrawEntity, cashWithdrawServiceImpl.createOrUpdateCashWithdraw(cashWithdrawEntity1));
        verify(cashWithdrawRepository).save((CashWithdrawEntity) any());
    }

    /**
     * Method under test: {@link CashWithdrawServiceImpl#createOrUpdateCashWithdraw(CashWithdrawEntity)}
     */
    @Test
    void testCreateOrUpdateCashWithdraw2() {
        // Arrange
        when(cashWithdrawRepository.save((CashWithdrawEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        CashWithdrawEntity cashWithdrawEntity = new CashWithdrawEntity();
        cashWithdrawEntity.setAmount(10.0d);
        cashWithdrawEntity.setBankName("Bank Name");
        cashWithdrawEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        cashWithdrawEntity.setId(123L);
        cashWithdrawEntity.setStatus("Status");

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> cashWithdrawServiceImpl.createOrUpdateCashWithdraw(cashWithdrawEntity));
        verify(cashWithdrawRepository).save((CashWithdrawEntity) any());
    }

    /**
     * Method under test: {@link CashWithdrawServiceImpl#getAllDepositsWithPagination(int, int)}
     */
    @Test
    void testGetAllDepositsWithPagination() {
        // Arrange
        PageImpl<CashWithdrawEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(cashWithdrawRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<CashWithdrawEntity> actualAllDepositsWithPagination = cashWithdrawServiceImpl.getAllDepositsWithPagination(2,
                3);

        // Assert
        assertSame(pageImpl, actualAllDepositsWithPagination);
        assertTrue(actualAllDepositsWithPagination.toList().isEmpty());
        verify(cashWithdrawRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link CashWithdrawServiceImpl#getAllDepositsWithPagination(int, int)}
     */
    @Test
    void testGetAllDepositsWithPagination2() {
        // Arrange
        when(cashWithdrawRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> cashWithdrawServiceImpl.getAllDepositsWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link CashWithdrawServiceImpl#getAllDepositsWithPagination(int, int)}
     */
    @Test
    void testGetAllDepositsWithPagination3() {
        // Arrange
        when(cashWithdrawRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> cashWithdrawServiceImpl.getAllDepositsWithPagination(2, 3));
        verify(cashWithdrawRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link CashWithdrawServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<CashWithdrawEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(cashWithdrawRepository.findAllByBankNameContains((String) any(), (Pageable) any())).thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<CashWithdrawEntity> actualSearch = cashWithdrawServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(cashWithdrawRepository).findAllByBankNameContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link CashWithdrawServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(cashWithdrawRepository.findAllByBankNameContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> cashWithdrawServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(cashWithdrawRepository).findAllByBankNameContains((String) any(), (Pageable) any());
    }
}

