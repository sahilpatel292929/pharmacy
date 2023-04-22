package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.SupplierCreationRepository;
import com.ets.SecurePharmacy.tenant.model.SupplierCreationEntity;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {SupplierCreationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class SupplierCreationServiceImplTest {
    @MockBean
    private SupplierCreationRepository supplierCreationRepository;

    @Autowired
    private SupplierCreationServiceImpl supplierCreationServiceImpl;

    /**
     * Method under test: {@link SupplierCreationServiceImpl#getAllSuppliers()}
     */
    @Test
    void testGetAllSuppliers() {
        // Arrange
        when(supplierCreationRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(supplierCreationServiceImpl.getAllSuppliers().isEmpty());
        verify(supplierCreationRepository).findAll();
    }

    /**
     * Method under test: {@link SupplierCreationServiceImpl#getAllSuppliers()}
     */
    @Test
    void testGetAllSuppliers2() {
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

        ArrayList<SupplierCreationEntity> supplierCreationEntityList = new ArrayList<>();
        supplierCreationEntityList.add(supplierCreationEntity);
        when(supplierCreationRepository.findAll()).thenReturn(supplierCreationEntityList);

        // Act
        List<SupplierCreationEntity> actualAllSuppliers = supplierCreationServiceImpl.getAllSuppliers();

        // Assert
        assertSame(supplierCreationEntityList, actualAllSuppliers);
        assertEquals(1, actualAllSuppliers.size());
        verify(supplierCreationRepository).findAll();
    }

    /**
     * Method under test: {@link SupplierCreationServiceImpl#getAllSuppliers()}
     */
    @Test
    void testGetAllSuppliers3() {
        // Arrange
        when(supplierCreationRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> supplierCreationServiceImpl.getAllSuppliers());
        verify(supplierCreationRepository).findAll();
    }

    /**
     * Method under test: {@link SupplierCreationServiceImpl#getSupplierById(Long)}
     */
    @Test
    void testGetSupplierById() throws RecordNotFoundException {
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
        Optional<SupplierCreationEntity> ofResult = Optional.of(supplierCreationEntity);
        when(supplierCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(supplierCreationEntity, supplierCreationServiceImpl.getSupplierById(123L));
        verify(supplierCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SupplierCreationServiceImpl#getSupplierById(Long)}
     */
    @Test
    void testGetSupplierById2() throws RecordNotFoundException {
        // Arrange
        when(supplierCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> supplierCreationServiceImpl.getSupplierById(123L));
        verify(supplierCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SupplierCreationServiceImpl#getSupplierById(Long)}
     */
    @Test
    void testGetSupplierById3() throws RecordNotFoundException {
        // Arrange
        when(supplierCreationRepository.findById((Long) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> supplierCreationServiceImpl.getSupplierById(123L));
        verify(supplierCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SupplierCreationServiceImpl#createOrUpdateSupplier(SupplierCreationEntity)}
     */
    @Test
    void testCreateOrUpdateSupplier() {
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
        when(supplierCreationRepository.save((SupplierCreationEntity) any())).thenReturn(supplierCreationEntity);

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

        // Act and Assert
        assertSame(supplierCreationEntity, supplierCreationServiceImpl.createOrUpdateSupplier(supplierCreationEntity1));
        verify(supplierCreationRepository).save((SupplierCreationEntity) any());
    }

    /**
     * Method under test: {@link SupplierCreationServiceImpl#createOrUpdateSupplier(SupplierCreationEntity)}
     */
    @Test
    void testCreateOrUpdateSupplier2() {
        // Arrange
        when(supplierCreationRepository.save((SupplierCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

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

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> supplierCreationServiceImpl.createOrUpdateSupplier(supplierCreationEntity));
        verify(supplierCreationRepository).save((SupplierCreationEntity) any());
    }

    /**
     * Method under test: {@link SupplierCreationServiceImpl#deleteSupplierById(Long)}
     */
    @Test
    void testDeleteSupplierById() throws RecordNotFoundException {
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
        Optional<SupplierCreationEntity> ofResult = Optional.of(supplierCreationEntity);
        doNothing().when(supplierCreationRepository).deleteById((Long) any());
        when(supplierCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act
        supplierCreationServiceImpl.deleteSupplierById(123L);

        // Assert that nothing has changed
        verify(supplierCreationRepository).findById((Long) any());
        verify(supplierCreationRepository).deleteById((Long) any());
        assertTrue(supplierCreationServiceImpl.getAllSuppliers().isEmpty());
    }

    /**
     * Method under test: {@link SupplierCreationServiceImpl#deleteSupplierById(Long)}
     */
    @Test
    void testDeleteSupplierById2() throws RecordNotFoundException {
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
        Optional<SupplierCreationEntity> ofResult = Optional.of(supplierCreationEntity);
        doThrow(new UnableToProcessException("An error occurred")).when(supplierCreationRepository)
                .deleteById((Long) any());
        when(supplierCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> supplierCreationServiceImpl.deleteSupplierById(123L));
        verify(supplierCreationRepository).findById((Long) any());
        verify(supplierCreationRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link SupplierCreationServiceImpl#deleteSupplierById(Long)}
     */
    @Test
    void testDeleteSupplierById3() throws RecordNotFoundException {
        // Arrange
        doNothing().when(supplierCreationRepository).deleteById((Long) any());
        when(supplierCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> supplierCreationServiceImpl.deleteSupplierById(123L));
        verify(supplierCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SupplierCreationServiceImpl#getSupplierNameOrMobile(String, String)}
     */
    @Test
    void testGetSupplierNameOrMobile() {
        // Arrange
        when(supplierCreationRepository.findBySupplierNameOrMobileNo((String) any(), (String) any()))
                .thenReturn(new ArrayList<>());

        // Act and Assert
        assertFalse(supplierCreationServiceImpl.getSupplierNameOrMobile("Sname", "Mobile"));
        verify(supplierCreationRepository).findBySupplierNameOrMobileNo((String) any(), (String) any());
    }

    /**
     * Method under test: {@link SupplierCreationServiceImpl#getSupplierNameOrMobile(String, String)}
     */
    @Test
    void testGetSupplierNameOrMobile2() {
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

        ArrayList<SupplierCreationEntity> supplierCreationEntityList = new ArrayList<>();
        supplierCreationEntityList.add(supplierCreationEntity);
        when(supplierCreationRepository.findBySupplierNameOrMobileNo((String) any(), (String) any()))
                .thenReturn(supplierCreationEntityList);

        // Act and Assert
        assertTrue(supplierCreationServiceImpl.getSupplierNameOrMobile("Sname", "Mobile"));
        verify(supplierCreationRepository).findBySupplierNameOrMobileNo((String) any(), (String) any());
    }

    /**
     * Method under test: {@link SupplierCreationServiceImpl#getSupplierNameOrMobile(String, String)}
     */
    @Test
    void testGetSupplierNameOrMobile3() {
        // Arrange
        when(supplierCreationRepository.findBySupplierNameOrMobileNo((String) any(), (String) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> supplierCreationServiceImpl.getSupplierNameOrMobile("Sname", "Mobile"));
        verify(supplierCreationRepository).findBySupplierNameOrMobileNo((String) any(), (String) any());
    }

    /**
     * Method under test: {@link SupplierCreationServiceImpl#createOrUpdateSupplierList(List)}
     */
    @Test
    void testCreateOrUpdateSupplierList() {
        // Arrange
        when(supplierCreationRepository.saveAll((Iterable<SupplierCreationEntity>) any())).thenReturn(new ArrayList<>());

        // Act
        supplierCreationServiceImpl.createOrUpdateSupplierList(new ArrayList<>());

        // Assert that nothing has changed
        verify(supplierCreationRepository).saveAll((Iterable<SupplierCreationEntity>) any());
        assertTrue(supplierCreationServiceImpl.getAllSuppliers().isEmpty());
    }

    /**
     * Method under test: {@link SupplierCreationServiceImpl#createOrUpdateSupplierList(List)}
     */
    @Test
    void testCreateOrUpdateSupplierList2() {
        // Arrange
        when(supplierCreationRepository.saveAll((Iterable<SupplierCreationEntity>) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> supplierCreationServiceImpl.createOrUpdateSupplierList(new ArrayList<>()));
        verify(supplierCreationRepository).saveAll((Iterable<SupplierCreationEntity>) any());
    }

    /**
     * Method under test: {@link SupplierCreationServiceImpl#getAllSuppliersWithPagination(int, int)}
     */
    @Test
    void testGetAllSuppliersWithPagination() {
        // Arrange
        PageImpl<SupplierCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(supplierCreationRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<SupplierCreationEntity> actualAllSuppliersWithPagination = supplierCreationServiceImpl
                .getAllSuppliersWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllSuppliersWithPagination);
        assertTrue(actualAllSuppliersWithPagination.toList().isEmpty());
        verify(supplierCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link SupplierCreationServiceImpl#getAllSuppliersWithPagination(int, int)}
     */
    @Test
    void testGetAllSuppliersWithPagination2() {
        // Arrange
        when(supplierCreationRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> supplierCreationServiceImpl.getAllSuppliersWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link SupplierCreationServiceImpl#getAllSuppliersWithPagination(int, int)}
     */
    @Test
    void testGetAllSuppliersWithPagination3() {
        // Arrange
        when(supplierCreationRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> supplierCreationServiceImpl.getAllSuppliersWithPagination(2, 3));
        verify(supplierCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link SupplierCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<SupplierCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(supplierCreationRepository.findAllBySupplierNameOrMobileNoContains((String) any(),(String) any(), (Pageable) any()))
                .thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<SupplierCreationEntity> actualSearch = supplierCreationServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(supplierCreationRepository).findAllBySupplierNameOrMobileNoContains((String) any(),(String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link SupplierCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(supplierCreationRepository.findAllBySupplierNameOrMobileNoContains((String) any(), (String) any(),(Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> supplierCreationServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(supplierCreationRepository).findAllBySupplierNameOrMobileNoContains((String) any(), (String) any(),(Pageable) any());
    }

    /**
     * Method under test: {@link SupplierCreationServiceImpl#updateSupplier(SupplierCreationEntity)}
     */
    @Test
    void testUpdateSupplier() {
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
        when(supplierCreationRepository.save((SupplierCreationEntity) any())).thenReturn(supplierCreationEntity);

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

        // Act and Assert
        assertSame(supplierCreationEntity, supplierCreationServiceImpl.updateSupplier(supplierCreationEntity1));
        verify(supplierCreationRepository).save((SupplierCreationEntity) any());
    }

    /**
     * Method under test: {@link SupplierCreationServiceImpl#updateSupplier(SupplierCreationEntity)}
     */
    @Test
    void testUpdateSupplier2() {
        // Arrange
        when(supplierCreationRepository.save((SupplierCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

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

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> supplierCreationServiceImpl.updateSupplier(supplierCreationEntity));
        verify(supplierCreationRepository).save((SupplierCreationEntity) any());
    }
}

