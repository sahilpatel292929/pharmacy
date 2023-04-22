package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.PurchaseOrderReposiotory;
import com.ets.SecurePharmacy.tenant.model.PurchaseOrderEntity;
import com.ets.SecurePharmacy.tenant.model.SupplierCreationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

@ContextConfiguration(classes = {PurchaseOrderServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PurchaseOrderServiceImplTest {
    @MockBean
    private PurchaseOrderReposiotory purchaseOrderReposiotory;

    @Autowired
    private PurchaseOrderServiceImpl purchaseOrderServiceImpl;

    /**
     * Method under test: {@link PurchaseOrderServiceImpl#getAll()}
     */
    @Test
    void testGetAll() {
        // Arrange
        when(purchaseOrderReposiotory.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(purchaseOrderServiceImpl.getAll().isEmpty());
        verify(purchaseOrderReposiotory).findAll();
    }

    /**
     * Method under test: {@link PurchaseOrderServiceImpl#getAll()}
     */
    @Test
    void testGetAll2() {
        // Arrange
        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("Gst No");
        supplierCreationEntity.setGstType("Gst Type");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("Mobile No");
        supplierCreationEntity.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("Rate Slab");
        supplierCreationEntity.setStatus("Status");
        supplierCreationEntity.setSupplierName("Supplier Name");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseOrderEntity purchaseOrderEntity = new PurchaseOrderEntity();
        purchaseOrderEntity.setId(123L);
        purchaseOrderEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        purchaseOrderEntity.setOrderQty(1L);
        purchaseOrderEntity.setPurchaseOrderDetails(new ArrayList<>());
        purchaseOrderEntity.setStatus("Status");
        purchaseOrderEntity.setSupplierDetails(supplierCreationEntity);
        purchaseOrderEntity.setSupplierName("Supplier Name");
        purchaseOrderEntity.setTotalCount(3L);

        ArrayList<PurchaseOrderEntity> purchaseOrderEntityList = new ArrayList<>();
        purchaseOrderEntityList.add(purchaseOrderEntity);
        when(purchaseOrderReposiotory.findAll()).thenReturn(purchaseOrderEntityList);

        // Act
        List<PurchaseOrderEntity> actualAll = purchaseOrderServiceImpl.getAll();

        // Assert
        assertSame(purchaseOrderEntityList, actualAll);
        assertEquals(1, actualAll.size());
        verify(purchaseOrderReposiotory).findAll();
    }

    /**
     * Method under test: {@link PurchaseOrderServiceImpl#getAll()}
     */
    @Test
    void testGetAll3() {
        // Arrange
        when(purchaseOrderReposiotory.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> purchaseOrderServiceImpl.getAll());
        verify(purchaseOrderReposiotory).findAll();
    }

    /**
     * Method under test: {@link PurchaseOrderServiceImpl#getById(Long)}
     */
    @Test
    void testGetById() throws RecordNotFoundException {
        // Arrange
        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("Gst No");
        supplierCreationEntity.setGstType("Gst Type");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("Mobile No");
        supplierCreationEntity.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("Rate Slab");
        supplierCreationEntity.setStatus("Status");
        supplierCreationEntity.setSupplierName("Supplier Name");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseOrderEntity purchaseOrderEntity = new PurchaseOrderEntity();
        purchaseOrderEntity.setId(123L);
        purchaseOrderEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        purchaseOrderEntity.setOrderQty(1L);
        purchaseOrderEntity.setPurchaseOrderDetails(new ArrayList<>());
        purchaseOrderEntity.setStatus("Status");
        purchaseOrderEntity.setSupplierDetails(supplierCreationEntity);
        purchaseOrderEntity.setSupplierName("Supplier Name");
        purchaseOrderEntity.setTotalCount(3L);
        Optional<PurchaseOrderEntity> ofResult = Optional.of(purchaseOrderEntity);
        when(purchaseOrderReposiotory.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(purchaseOrderEntity, purchaseOrderServiceImpl.getById(123L));
        verify(purchaseOrderReposiotory).findById((Long) any());
    }

    /**
     * Method under test: {@link PurchaseOrderServiceImpl#getById(Long)}
     */
    @Test
    void testGetById2() throws RecordNotFoundException {
        // Arrange
        when(purchaseOrderReposiotory.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> purchaseOrderServiceImpl.getById(123L));
        verify(purchaseOrderReposiotory).findById((Long) any());
    }

    /**
     * Method under test: {@link PurchaseOrderServiceImpl#getById(Long)}
     */
    @Test
    void testGetById3() throws RecordNotFoundException {
        // Arrange
        when(purchaseOrderReposiotory.findById((Long) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> purchaseOrderServiceImpl.getById(123L));
        verify(purchaseOrderReposiotory).findById((Long) any());
    }

    /**
     * Method under test: {@link PurchaseOrderServiceImpl#createOrUpdate(PurchaseOrderEntity)}
     */
    @Test
    void testCreateOrUpdate() {
        // Arrange
        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("Gst No");
        supplierCreationEntity.setGstType("Gst Type");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("Mobile No");
        supplierCreationEntity.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("Rate Slab");
        supplierCreationEntity.setStatus("Status");
        supplierCreationEntity.setSupplierName("Supplier Name");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseOrderEntity purchaseOrderEntity = new PurchaseOrderEntity();
        purchaseOrderEntity.setId(123L);
        purchaseOrderEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        purchaseOrderEntity.setOrderQty(1L);
        purchaseOrderEntity.setPurchaseOrderDetails(new ArrayList<>());
        purchaseOrderEntity.setStatus("Status");
        purchaseOrderEntity.setSupplierDetails(supplierCreationEntity);
        purchaseOrderEntity.setSupplierName("Supplier Name");
        purchaseOrderEntity.setTotalCount(3L);
        when(purchaseOrderReposiotory.save((PurchaseOrderEntity) any())).thenReturn(purchaseOrderEntity);

        SupplierCreationEntity supplierCreationEntity1 = new SupplierCreationEntity();
        supplierCreationEntity1.setAddress1("42 Main St");
        supplierCreationEntity1.setAddress2("42 Main St");
        supplierCreationEntity1.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity1.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity1.setCredit_days(1);
        supplierCreationEntity1.setDiscount("3");
        supplierCreationEntity1.setGstNo("Gst No");
        supplierCreationEntity1.setGstType("Gst Type");
        supplierCreationEntity1.setId(123L);
        supplierCreationEntity1.setMobileNo("Mobile No");
        supplierCreationEntity1.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity1.setOpeningBal(1L);
        supplierCreationEntity1.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity1.setRateSlab("Rate Slab");
        supplierCreationEntity1.setStatus("Status");
        supplierCreationEntity1.setSupplierName("Supplier Name");
        supplierCreationEntity1.setUpdated_by(1);
        supplierCreationEntity1.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseOrderEntity purchaseOrderEntity1 = new PurchaseOrderEntity();
        purchaseOrderEntity1.setId(123L);
        purchaseOrderEntity1.setOrderDate(LocalDate.ofEpochDay(1L));
        purchaseOrderEntity1.setOrderQty(1L);
        purchaseOrderEntity1.setPurchaseOrderDetails(new ArrayList<>());
        purchaseOrderEntity1.setStatus("Status");
        purchaseOrderEntity1.setSupplierDetails(supplierCreationEntity1);
        purchaseOrderEntity1.setSupplierName("Supplier Name");
        purchaseOrderEntity1.setTotalCount(3L);

        // Act and Assert
        assertSame(purchaseOrderEntity, purchaseOrderServiceImpl.createOrUpdate(purchaseOrderEntity1));
        verify(purchaseOrderReposiotory).save((PurchaseOrderEntity) any());
    }

    /**
     * Method under test: {@link PurchaseOrderServiceImpl#createOrUpdate(PurchaseOrderEntity)}
     */
    @Test
    void testCreateOrUpdate1() {
        // Arrange
        when(purchaseOrderReposiotory.save((PurchaseOrderEntity) any())).thenThrow(new UnableToProcessException("Unable to process at this moment, Try Again! After some time"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,()->purchaseOrderServiceImpl.createOrUpdate((PurchaseOrderEntity) any()));
        verify(purchaseOrderReposiotory).save((PurchaseOrderEntity) any());
    }


    /**
     * Method under test: {@link PurchaseOrderServiceImpl#findByCriteria(Long, Long)}
     */
    @Test
    void testFindByCriteria() {
        // Arrange
        ArrayList<PurchaseOrderEntity> purchaseOrderEntityList = new ArrayList<>();
        when(purchaseOrderReposiotory.findAll((Specification<PurchaseOrderEntity>) any()))
                .thenReturn(purchaseOrderEntityList);

        // Act
        List<PurchaseOrderEntity> actualFindByCriteriaResult = purchaseOrderServiceImpl.findByCriteria(123L, 123L);

        // Assert
        assertSame(purchaseOrderEntityList, actualFindByCriteriaResult);
        assertTrue(actualFindByCriteriaResult.isEmpty());
        verify(purchaseOrderReposiotory).findAll((Specification<PurchaseOrderEntity>) any());
    }

    /**
     * Method under test: {@link PurchaseOrderServiceImpl#findByCriteria(Long, Long)}
     */
    @Test
    void testFindByCriteria2() {
        // Arrange
        when(purchaseOrderReposiotory.findAll((Specification<PurchaseOrderEntity>) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> purchaseOrderServiceImpl.findByCriteria(123L, 123L));
        verify(purchaseOrderReposiotory).findAll((Specification<PurchaseOrderEntity>) any());
    }

    /**
     * Method under test: {@link PurchaseOrderServiceImpl#getAllPurchaseOrderWithPagination(int, int)}
     */
    @Test
    void testGetAllPurchaseOrderWithPagination() {
        // Arrange
        PageImpl<PurchaseOrderEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(purchaseOrderReposiotory.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<PurchaseOrderEntity> actualAllPurchaseOrderWithPagination = purchaseOrderServiceImpl
                .getAllPurchaseOrderWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllPurchaseOrderWithPagination);
        assertTrue(actualAllPurchaseOrderWithPagination.toList().isEmpty());
        verify(purchaseOrderReposiotory).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link PurchaseOrderServiceImpl#getAllPurchaseOrderWithPagination(int, int)}
     */
    @Test
    void testGetAllPurchaseOrderWithPagination2() {
        // Arrange
        when(purchaseOrderReposiotory.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> purchaseOrderServiceImpl.getAllPurchaseOrderWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link PurchaseOrderServiceImpl#getAllPurchaseOrderWithPagination(int, int)}
     */
    @Test
    void testGetAllPurchaseOrderWithPagination3() {
        // Arrange
        when(purchaseOrderReposiotory.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> purchaseOrderServiceImpl.getAllPurchaseOrderWithPagination(2, 3));
        verify(purchaseOrderReposiotory).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link PurchaseOrderServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<PurchaseOrderEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(purchaseOrderReposiotory.findAllBySupplierNameContains((String) any(), (Pageable) any()))
                .thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<PurchaseOrderEntity> actualSearch = purchaseOrderServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(purchaseOrderReposiotory).findAllBySupplierNameContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link PurchaseOrderServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(purchaseOrderReposiotory.findAllBySupplierNameContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> purchaseOrderServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(purchaseOrderReposiotory).findAllBySupplierNameContains((String) any(), (Pageable) any());
    }
}

