package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.SalesOrderEntryRepository;
import com.ets.SecurePharmacy.tenant.model.SalesOrderEntryEntity;
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

@ContextConfiguration(classes = {SalesOrderServiceImpl.class})
@ExtendWith(SpringExtension.class)
class SalesOrderServiceImplTest {
    @MockBean
    private SalesOrderEntryRepository salesOrderEntryRepository;

    @Autowired
    private SalesOrderServiceImpl salesOrderServiceImpl;

    /**
     * Method under test: {@link SalesOrderServiceImpl#getAll()}
     */
    @Test
    void testGetAll() {
        // Arrange
        when(salesOrderEntryRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(salesOrderServiceImpl.getAll().isEmpty());
        verify(salesOrderEntryRepository).findAll();
    }

    /**
     * Method under test: {@link SalesOrderServiceImpl#getAll()}
     */
    @Test
    void testGetAll2() {
        // Arrange
        SalesOrderEntryEntity salesOrderEntryEntity = new SalesOrderEntryEntity();
        salesOrderEntryEntity.setCustomerAddress("42 Main St");
        salesOrderEntryEntity.setCustomerMobileNo(1L);
        salesOrderEntryEntity.setCustomerName("Customer Name");
        salesOrderEntryEntity.setDeliveryDate(LocalDate.ofEpochDay(1L));
        salesOrderEntryEntity.setId(123L);
        salesOrderEntryEntity.setSalesOrderDetailsEntity(new ArrayList<>());
        salesOrderEntryEntity.setSoDate(LocalDate.ofEpochDay(1L));
        salesOrderEntryEntity.setStatus("Status");
        salesOrderEntryEntity.setTotalCount(3L);
        salesOrderEntryEntity.setTotalQty(1L);

        ArrayList<SalesOrderEntryEntity> salesOrderEntryEntityList = new ArrayList<>();
        salesOrderEntryEntityList.add(salesOrderEntryEntity);
        when(salesOrderEntryRepository.findAll()).thenReturn(salesOrderEntryEntityList);

        // Act
        List<SalesOrderEntryEntity> actualAll = salesOrderServiceImpl.getAll();

        // Assert
        assertSame(salesOrderEntryEntityList, actualAll);
        assertEquals(1, actualAll.size());
        verify(salesOrderEntryRepository).findAll();
    }

    /**
     * Method under test: {@link SalesOrderServiceImpl#getAll()}
     */
    @Test
    void testGetAll3() {
        // Arrange
        when(salesOrderEntryRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> salesOrderServiceImpl.getAll());
        verify(salesOrderEntryRepository).findAll();
    }

    /**
     * Method under test: {@link SalesOrderServiceImpl#getById(Long)}
     */
    @Test
    void testGetById() throws RecordNotFoundException {
        // Arrange
        SalesOrderEntryEntity salesOrderEntryEntity = new SalesOrderEntryEntity();
        salesOrderEntryEntity.setCustomerAddress("42 Main St");
        salesOrderEntryEntity.setCustomerMobileNo(1L);
        salesOrderEntryEntity.setCustomerName("Customer Name");
        salesOrderEntryEntity.setDeliveryDate(LocalDate.ofEpochDay(1L));
        salesOrderEntryEntity.setId(123L);
        salesOrderEntryEntity.setSalesOrderDetailsEntity(new ArrayList<>());
        salesOrderEntryEntity.setSoDate(LocalDate.ofEpochDay(1L));
        salesOrderEntryEntity.setStatus("Status");
        salesOrderEntryEntity.setTotalCount(3L);
        salesOrderEntryEntity.setTotalQty(1L);
        Optional<SalesOrderEntryEntity> ofResult = Optional.of(salesOrderEntryEntity);
        when(salesOrderEntryRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(salesOrderEntryEntity, salesOrderServiceImpl.getById(123L));
        verify(salesOrderEntryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SalesOrderServiceImpl#getById(Long)}
     */
    @Test
    void testGetById2() throws RecordNotFoundException {
        // Arrange
        when(salesOrderEntryRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> salesOrderServiceImpl.getById(123L));
        verify(salesOrderEntryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SalesOrderServiceImpl#getById(Long)}
     */
    @Test
    void testGetById3() throws RecordNotFoundException {
        // Arrange
        when(salesOrderEntryRepository.findById((Long) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> salesOrderServiceImpl.getById(123L));
        verify(salesOrderEntryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SalesOrderServiceImpl#createOrUpdate(SalesOrderEntryEntity)}
     */
    @Test
    void testCreateOrUpdate() {
        // Arrange
        SalesOrderEntryEntity salesOrderEntryEntity = new SalesOrderEntryEntity();
        salesOrderEntryEntity.setCustomerAddress("42 Main St");
        salesOrderEntryEntity.setCustomerMobileNo(1L);
        salesOrderEntryEntity.setCustomerName("Customer Name");
        salesOrderEntryEntity.setDeliveryDate(LocalDate.ofEpochDay(1L));
        salesOrderEntryEntity.setId(123L);
        salesOrderEntryEntity.setSalesOrderDetailsEntity(new ArrayList<>());
        salesOrderEntryEntity.setSoDate(LocalDate.ofEpochDay(1L));
        salesOrderEntryEntity.setStatus("Status");
        salesOrderEntryEntity.setTotalCount(3L);
        salesOrderEntryEntity.setTotalQty(1L);
        when(salesOrderEntryRepository.save((SalesOrderEntryEntity) any())).thenReturn(salesOrderEntryEntity);

        SalesOrderEntryEntity salesOrderEntryEntity1 = new SalesOrderEntryEntity();
        salesOrderEntryEntity1.setCustomerAddress("42 Main St");
        salesOrderEntryEntity1.setCustomerMobileNo(1L);
        salesOrderEntryEntity1.setCustomerName("Customer Name");
        salesOrderEntryEntity1.setDeliveryDate(LocalDate.ofEpochDay(1L));
        salesOrderEntryEntity1.setId(123L);
        salesOrderEntryEntity1.setSalesOrderDetailsEntity(new ArrayList<>());
        salesOrderEntryEntity1.setSoDate(LocalDate.ofEpochDay(1L));
        salesOrderEntryEntity1.setStatus("Status");
        salesOrderEntryEntity1.setTotalCount(3L);
        salesOrderEntryEntity1.setTotalQty(1L);

        // Act and Assert
        assertSame(salesOrderEntryEntity, salesOrderServiceImpl.createOrUpdate(salesOrderEntryEntity1));
        verify(salesOrderEntryRepository).save((SalesOrderEntryEntity) any());
    }

    /**
     * Method under test: {@link SalesOrderServiceImpl#createOrUpdate(SalesOrderEntryEntity)}
     */
    @Test
    void testCreateOrUpdate2() {
        // Arrange
        when(salesOrderEntryRepository.save((SalesOrderEntryEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        SalesOrderEntryEntity salesOrderEntryEntity = new SalesOrderEntryEntity();
        salesOrderEntryEntity.setCustomerAddress("42 Main St");
        salesOrderEntryEntity.setCustomerMobileNo(1L);
        salesOrderEntryEntity.setCustomerName("Customer Name");
        salesOrderEntryEntity.setDeliveryDate(LocalDate.ofEpochDay(1L));
        salesOrderEntryEntity.setId(123L);
        salesOrderEntryEntity.setSalesOrderDetailsEntity(new ArrayList<>());
        salesOrderEntryEntity.setSoDate(LocalDate.ofEpochDay(1L));
        salesOrderEntryEntity.setStatus("Status");
        salesOrderEntryEntity.setTotalCount(3L);
        salesOrderEntryEntity.setTotalQty(1L);

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> salesOrderServiceImpl.createOrUpdate(salesOrderEntryEntity));
        verify(salesOrderEntryRepository).save((SalesOrderEntryEntity) any());
    }

    /**
     * Method under test: {@link SalesOrderServiceImpl#getByMobileNumber(Long)}
     */
    @Test
    void testGetByMobileNumber() throws RecordNotFoundException {
        // Arrange
        when(salesOrderEntryRepository.findByCustomerMobileNo((Long) any())).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(salesOrderServiceImpl.getByMobileNumber(1L).isEmpty());
        verify(salesOrderEntryRepository).findByCustomerMobileNo((Long) any());
    }

    /**
     * Method under test: {@link SalesOrderServiceImpl#getByMobileNumber(Long)}
     */
    @Test
    void testGetByMobileNumber2() throws RecordNotFoundException {
        // Arrange
        SalesOrderEntryEntity salesOrderEntryEntity = new SalesOrderEntryEntity();
        salesOrderEntryEntity.setCustomerAddress("42 Main St");
        salesOrderEntryEntity.setCustomerMobileNo(1L);
        salesOrderEntryEntity.setCustomerName("local date curerent");
        salesOrderEntryEntity.setDeliveryDate(LocalDate.ofEpochDay(1L));
        salesOrderEntryEntity.setId(123L);
        salesOrderEntryEntity.setSalesOrderDetailsEntity(new ArrayList<>());
        salesOrderEntryEntity.setSoDate(LocalDate.ofEpochDay(1L));
        salesOrderEntryEntity.setStatus("local date curerent");
        salesOrderEntryEntity.setTotalCount(3L);
        salesOrderEntryEntity.setTotalQty(1L);

        ArrayList<SalesOrderEntryEntity> salesOrderEntryEntityList = new ArrayList<>();
        salesOrderEntryEntityList.add(salesOrderEntryEntity);
        when(salesOrderEntryRepository.findByCustomerMobileNo((Long) any())).thenReturn(salesOrderEntryEntityList);

        // Act
        List<SalesOrderEntryEntity> actualByMobileNumber = salesOrderServiceImpl.getByMobileNumber(1L);

        // Assert
        assertSame(salesOrderEntryEntityList, actualByMobileNumber);
        assertEquals(1, actualByMobileNumber.size());
        verify(salesOrderEntryRepository).findByCustomerMobileNo((Long) any());
    }

    /**
     * Method under test: {@link SalesOrderServiceImpl#getByMobileNumber(Long)}
     */
    @Test
    void testGetByMobileNumber3() throws RecordNotFoundException {
        // Arrange
        when(salesOrderEntryRepository.findByCustomerMobileNo((Long) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> salesOrderServiceImpl.getByMobileNumber(1L));
        verify(salesOrderEntryRepository).findByCustomerMobileNo((Long) any());
    }

    /**
     * Method under test: {@link SalesOrderServiceImpl#getAllSalesOrderEntryEntityWithPagination(int, int)}
     */
    @Test
    void testGetAllSalesOrderEntryEntityWithPagination() {
        // Arrange
        PageImpl<SalesOrderEntryEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(salesOrderEntryRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<SalesOrderEntryEntity> actualAllSalesOrderEntryEntityWithPagination = salesOrderServiceImpl
                .getAllSalesOrderEntryEntityWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllSalesOrderEntryEntityWithPagination);
        assertTrue(actualAllSalesOrderEntryEntityWithPagination.toList().isEmpty());
        verify(salesOrderEntryRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link SalesOrderServiceImpl#getAllSalesOrderEntryEntityWithPagination(int, int)}
     */
    @Test
    void testGetAllSalesOrderEntryEntityWithPagination2() {
        // Arrange
        when(salesOrderEntryRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> salesOrderServiceImpl.getAllSalesOrderEntryEntityWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link SalesOrderServiceImpl#getAllSalesOrderEntryEntityWithPagination(int, int)}
     */
    @Test
    void testGetAllSalesOrderEntryEntityWithPagination3() {
        // Arrange
        when(salesOrderEntryRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> salesOrderServiceImpl.getAllSalesOrderEntryEntityWithPagination(2, 3));
        verify(salesOrderEntryRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link SalesOrderServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<SalesOrderEntryEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(salesOrderEntryRepository.findAllByCustomerNameContains((String) any(), (Pageable) any()))
                .thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<SalesOrderEntryEntity> actualSearch = salesOrderServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(salesOrderEntryRepository).findAllByCustomerNameContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link SalesOrderServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        PageImpl<SalesOrderEntryEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(salesOrderEntryRepository.findAllByCustomerMobileNo((Long) any(), (Pageable) any())).thenReturn(pageImpl);
        when(salesOrderEntryRepository.findAllByCustomerNameContains((String) any(), (Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<SalesOrderEntryEntity> actualSearch = salesOrderServiceImpl.getSearch("42", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(salesOrderEntryRepository).findAllByCustomerMobileNo((Long) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link SalesOrderServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch3() {
        // Arrange
        when(salesOrderEntryRepository.findAllByCustomerMobileNo((Long) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));
        when(salesOrderEntryRepository.findAllByCustomerNameContains((String) any(), (Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> salesOrderServiceImpl.getSearch("42", QPageRequest.of(1, 3)));
        verify(salesOrderEntryRepository).findAllByCustomerMobileNo((Long) any(), (Pageable) any());
    }
}

