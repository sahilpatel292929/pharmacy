package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.CounterSaleRepository;
import com.ets.SecurePharmacy.tenant.model.CounterSaleEntity;
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

@ContextConfiguration(classes = {CounterSaleServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CounterSaleServiceImplTest {
    @MockBean
    private CounterSaleRepository counterSaleRepository;

    @Autowired
    private CounterSaleServiceImpl counterSaleServiceImpl;

    /**
     * Method under test: {@link CounterSaleServiceImpl#getAllCounterSales()}
     */
    @Test
    void testGetAllCounterSales() {
        // Arrange
        when(counterSaleRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(counterSaleServiceImpl.getAllCounterSales().isEmpty());
        verify(counterSaleRepository).findAll();
    }

    /**
     * Method under test: {@link CounterSaleServiceImpl#getAllCounterSales()}
     */
    @Test
    void testGetAllCounterSales2() {
        // Arrange
        CounterSaleEntity counterSaleEntity = new CounterSaleEntity();
        counterSaleEntity.setCounteSaleDetails(new ArrayList<>());
        counterSaleEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        counterSaleEntity.setId(123L);
        counterSaleEntity.setStatus("Status");
        counterSaleEntity.setTotalItems(1000);
        counterSaleEntity.setTotalVal(10.0d);

        ArrayList<CounterSaleEntity> counterSaleEntityList = new ArrayList<>();
        counterSaleEntityList.add(counterSaleEntity);
        when(counterSaleRepository.findAll()).thenReturn(counterSaleEntityList);

        // Act
        List<CounterSaleEntity> actualAllCounterSales = counterSaleServiceImpl.getAllCounterSales();

        // Assert
        assertSame(counterSaleEntityList, actualAllCounterSales);
        assertEquals(1, actualAllCounterSales.size());
        verify(counterSaleRepository).findAll();
    }

    /**
     * Method under test: {@link CounterSaleServiceImpl#getAllCounterSales()}
     */
    @Test
    void testGetAllCounterSales3() {
        // Arrange
        when(counterSaleRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> counterSaleServiceImpl.getAllCounterSales());
        verify(counterSaleRepository).findAll();
    }

    /**
     * Method under test: {@link CounterSaleServiceImpl#getCounterSaleById(Long)}
     */
    @Test
    void testGetCounterSaleById() throws RecordNotFoundException {
        // Arrange
        CounterSaleEntity counterSaleEntity = new CounterSaleEntity();
        counterSaleEntity.setCounteSaleDetails(new ArrayList<>());
        counterSaleEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        counterSaleEntity.setId(123L);
        counterSaleEntity.setStatus("Status");
        counterSaleEntity.setTotalItems(1000);
        counterSaleEntity.setTotalVal(10.0d);
        Optional<CounterSaleEntity> ofResult = Optional.of(counterSaleEntity);
        when(counterSaleRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(counterSaleEntity, counterSaleServiceImpl.getCounterSaleById(123L));
        verify(counterSaleRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CounterSaleServiceImpl#getCounterSaleById(Long)}
     */
    @Test
    void testGetCounterSaleById2() throws RecordNotFoundException {
        // Arrange
        when(counterSaleRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> counterSaleServiceImpl.getCounterSaleById(123L));
        verify(counterSaleRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CounterSaleServiceImpl#getCounterSaleById(Long)}
     */
    @Test
    void testGetCounterSaleById3() throws RecordNotFoundException {
        // Arrange
        when(counterSaleRepository.findById((Long) any())).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> counterSaleServiceImpl.getCounterSaleById(123L));
        verify(counterSaleRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CounterSaleServiceImpl#createOrUpdateCounterSale(CounterSaleEntity)}
     */
    @Test
    void testCreateOrUpdateCounterSale() {
        // Arrange
        CounterSaleEntity counterSaleEntity = new CounterSaleEntity();
        counterSaleEntity.setCounteSaleDetails(new ArrayList<>());
        counterSaleEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        counterSaleEntity.setId(123L);
        counterSaleEntity.setStatus("Status");
        counterSaleEntity.setTotalItems(1000);
        counterSaleEntity.setTotalVal(10.0d);
        when(counterSaleRepository.save((CounterSaleEntity) any())).thenReturn(counterSaleEntity);

        CounterSaleEntity counterSaleEntity1 = new CounterSaleEntity();
        counterSaleEntity1.setCounteSaleDetails(new ArrayList<>());
        counterSaleEntity1.setEntryDate(LocalDate.ofEpochDay(1L));
        counterSaleEntity1.setId(123L);
        counterSaleEntity1.setStatus("Status");
        counterSaleEntity1.setTotalItems(1000);
        counterSaleEntity1.setTotalVal(10.0d);

        // Act and Assert
        assertSame(counterSaleEntity, counterSaleServiceImpl.createOrUpdateCounterSale(counterSaleEntity1));
        verify(counterSaleRepository).save((CounterSaleEntity) any());
    }

    /**
     * Method under test: {@link CounterSaleServiceImpl#createOrUpdateCounterSale(CounterSaleEntity)}
     */
    @Test
    void testCreateOrUpdateCounterSale2() {
        // Arrange
        when(counterSaleRepository.save((CounterSaleEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        CounterSaleEntity counterSaleEntity = new CounterSaleEntity();
        counterSaleEntity.setCounteSaleDetails(new ArrayList<>());
        counterSaleEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        counterSaleEntity.setId(123L);
        counterSaleEntity.setStatus("Status");
        counterSaleEntity.setTotalItems(1000);
        counterSaleEntity.setTotalVal(10.0d);

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> counterSaleServiceImpl.createOrUpdateCounterSale(counterSaleEntity));
        verify(counterSaleRepository).save((CounterSaleEntity) any());
    }

    /**
     * Method under test: {@link CounterSaleServiceImpl#getAllCounterSaleWithPagination(int, int)}
     */
    @Test
    void testGetAllCounterSaleWithPagination() {
        // Arrange
        PageImpl<CounterSaleEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(counterSaleRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<CounterSaleEntity> actualAllCounterSaleWithPagination = counterSaleServiceImpl
                .getAllCounterSaleWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllCounterSaleWithPagination);
        assertTrue(actualAllCounterSaleWithPagination.toList().isEmpty());
        verify(counterSaleRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link CounterSaleServiceImpl#getAllCounterSaleWithPagination(int, int)}
     */
    @Test
    void testGetAllCounterSaleWithPagination2() {
        // Arrange
        when(counterSaleRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> counterSaleServiceImpl.getAllCounterSaleWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link CounterSaleServiceImpl#getAllCounterSaleWithPagination(int, int)}
     */
    @Test
    void testGetAllCounterSaleWithPagination3() {
        // Arrange
        when(counterSaleRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> counterSaleServiceImpl.getAllCounterSaleWithPagination(2, 3));
        verify(counterSaleRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link CounterSaleServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<CounterSaleEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(counterSaleRepository.findAllByEntryDateContains((String) any(), (Pageable) any())).thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<CounterSaleEntity> actualSearch = counterSaleServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(counterSaleRepository).findAllByEntryDateContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link CounterSaleServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(counterSaleRepository.findAllByEntryDateContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> counterSaleServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(counterSaleRepository).findAllByEntryDateContains((String) any(), (Pageable) any());
    }
}

