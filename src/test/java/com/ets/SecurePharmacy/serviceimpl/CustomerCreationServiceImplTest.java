package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.CutomerCreationRepository;
import com.ets.SecurePharmacy.tenant.model.CustomerCreationEntity;
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

@ContextConfiguration(classes = {CustomerCreationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CustomerCreationServiceImplTest {
    @Autowired
    private CustomerCreationServiceImpl customerCreationServiceImpl;

    @MockBean
    private CutomerCreationRepository cutomerCreationRepository;

    /**
     * Method under test: {@link CustomerCreationServiceImpl#getAllCutomer()}
     */
    @Test
    void testGetAllCutomer() {
        // Arrange
        when(cutomerCreationRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(customerCreationServiceImpl.getAllCutomer().isEmpty());
        verify(cutomerCreationRepository).findAll();
    }

    /**
     * Method under test: {@link CustomerCreationServiceImpl#getAllCutomer()}
     */
    @Test
    void testGetAllCutomer2() {
        // Arrange
        CustomerCreationEntity customerCreationEntity = new CustomerCreationEntity();
        customerCreationEntity.setAddress1("42 Main St");
        customerCreationEntity.setAddress2("42 Main St");
        customerCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        customerCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setCreditLimit(1);
        customerCreationEntity.setCustomerName("Customer Name");
        customerCreationEntity.setDiscount(3);
        customerCreationEntity.setDueDays(1);
        customerCreationEntity.setGstNo("Gst No");
        customerCreationEntity.setGstType("Gst Type");
        customerCreationEntity.setId(123L);
        customerCreationEntity.setMobileNo("Mobile No");
        customerCreationEntity.setModeOfPayment("Mode Of Payment");
        customerCreationEntity.setOpeningBal(1);
        customerCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setRateSlab(1);
        customerCreationEntity.setStatus("Status");
        customerCreationEntity.setUpdated_by("2020-03-01");
        customerCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        ArrayList<CustomerCreationEntity> customerCreationEntityList = new ArrayList<>();
        customerCreationEntityList.add(customerCreationEntity);
        when(cutomerCreationRepository.findAll()).thenReturn(customerCreationEntityList);

        // Act
        List<CustomerCreationEntity> actualAllCutomer = customerCreationServiceImpl.getAllCutomer();

        // Assert
        assertSame(customerCreationEntityList, actualAllCutomer);
        assertEquals(1, actualAllCutomer.size());
        verify(cutomerCreationRepository).findAll();
    }

    /**
     * Method under test: {@link CustomerCreationServiceImpl#getAllCutomer()}
     */
    @Test
    void testGetAllCutomer3() {
        // Arrange
        when(cutomerCreationRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> customerCreationServiceImpl.getAllCutomer());
        verify(cutomerCreationRepository).findAll();
    }

    /**
     * Method under test: {@link CustomerCreationServiceImpl#getCustomerById(Long)}
     */
    @Test
    void testGetCustomerById() throws RecordNotFoundException {
        // Arrange
        CustomerCreationEntity customerCreationEntity = new CustomerCreationEntity();
        customerCreationEntity.setAddress1("42 Main St");
        customerCreationEntity.setAddress2("42 Main St");
        customerCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        customerCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setCreditLimit(1);
        customerCreationEntity.setCustomerName("Customer Name");
        customerCreationEntity.setDiscount(3);
        customerCreationEntity.setDueDays(1);
        customerCreationEntity.setGstNo("Gst No");
        customerCreationEntity.setGstType("Gst Type");
        customerCreationEntity.setId(123L);
        customerCreationEntity.setMobileNo("Mobile No");
        customerCreationEntity.setModeOfPayment("Mode Of Payment");
        customerCreationEntity.setOpeningBal(1);
        customerCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setRateSlab(1);
        customerCreationEntity.setStatus("Status");
        customerCreationEntity.setUpdated_by("2020-03-01");
        customerCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));
        Optional<CustomerCreationEntity> ofResult = Optional.of(customerCreationEntity);
        when(cutomerCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(customerCreationEntity, customerCreationServiceImpl.getCustomerById(123L));
        verify(cutomerCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CustomerCreationServiceImpl#getCustomerById(Long)}
     */
    @Test
    void testGetCustomerById2() throws RecordNotFoundException {
        // Arrange
        when(cutomerCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> customerCreationServiceImpl.getCustomerById(123L));
        verify(cutomerCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CustomerCreationServiceImpl#getCustomerById(Long)}
     */
    @Test
    void testGetCustomerById3() throws RecordNotFoundException {
        // Arrange
        when(cutomerCreationRepository.findById((Long) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> customerCreationServiceImpl.getCustomerById(123L));
        verify(cutomerCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CustomerCreationServiceImpl#createOrUpdateCustomer(CustomerCreationEntity)}
     */
    @Test
    void testCreateOrUpdateCustomer() {
        // Arrange
        CustomerCreationEntity customerCreationEntity = new CustomerCreationEntity();
        customerCreationEntity.setAddress1("42 Main St");
        customerCreationEntity.setAddress2("42 Main St");
        customerCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        customerCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setCreditLimit(1);
        customerCreationEntity.setCustomerName("Customer Name");
        customerCreationEntity.setDiscount(3);
        customerCreationEntity.setDueDays(1);
        customerCreationEntity.setGstNo("Gst No");
        customerCreationEntity.setGstType("Gst Type");
        customerCreationEntity.setId(123L);
        customerCreationEntity.setMobileNo("Mobile No");
        customerCreationEntity.setModeOfPayment("Mode Of Payment");
        customerCreationEntity.setOpeningBal(1);
        customerCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setRateSlab(1);
        customerCreationEntity.setStatus("Status");
        customerCreationEntity.setUpdated_by("2020-03-01");
        customerCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        CustomerCreationEntity customerCreationEntity1 = new CustomerCreationEntity();
        customerCreationEntity1.setAddress1("42 Main St");
        customerCreationEntity1.setAddress2("42 Main St");
        customerCreationEntity1.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        customerCreationEntity1.setCreated_date(LocalDate.ofEpochDay(1L));
        customerCreationEntity1.setCreditLimit(1);
        customerCreationEntity1.setCustomerName("Customer Name");
        customerCreationEntity1.setDiscount(3);
        customerCreationEntity1.setDueDays(1);
        customerCreationEntity1.setGstNo("Gst No");
        customerCreationEntity1.setGstType("Gst Type");
        customerCreationEntity1.setId(123L);
        customerCreationEntity1.setMobileNo("Mobile No");
        customerCreationEntity1.setModeOfPayment("Mode Of Payment");
        customerCreationEntity1.setOpeningBal(1);
        customerCreationEntity1.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        customerCreationEntity1.setRateSlab(1);
        customerCreationEntity1.setStatus("Status");
        customerCreationEntity1.setUpdated_by("2020-03-01");
        customerCreationEntity1.setUpdated_date(LocalDate.ofEpochDay(1L));
        when(cutomerCreationRepository.findByMobileNo((String) any())).thenReturn(customerCreationEntity);
        when(cutomerCreationRepository.save((CustomerCreationEntity) any())).thenReturn(customerCreationEntity1);

        CustomerCreationEntity customerCreationEntity2 = new CustomerCreationEntity();
        customerCreationEntity2.setAddress1("42 Main St");
        customerCreationEntity2.setAddress2("42 Main St");
        customerCreationEntity2.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        customerCreationEntity2.setCreated_date(LocalDate.ofEpochDay(1L));
        customerCreationEntity2.setCreditLimit(1);
        customerCreationEntity2.setCustomerName("Customer Name");
        customerCreationEntity2.setDiscount(3);
        customerCreationEntity2.setDueDays(1);
        customerCreationEntity2.setGstNo("Gst No");
        customerCreationEntity2.setGstType("Gst Type");
        customerCreationEntity2.setId(123L);
        customerCreationEntity2.setMobileNo("Mobile No");
        customerCreationEntity2.setModeOfPayment("Mode Of Payment");
        customerCreationEntity2.setOpeningBal(1);
        customerCreationEntity2.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        customerCreationEntity2.setRateSlab(1);
        customerCreationEntity2.setStatus("Status");
        customerCreationEntity2.setUpdated_by("2020-03-01");
        customerCreationEntity2.setUpdated_date(LocalDate.ofEpochDay(1L));

        // Act and Assert
        assertThrows(RecordAlreadyPresentException.class,
                () -> customerCreationServiceImpl.createOrUpdateCustomer(customerCreationEntity2));
        verify(cutomerCreationRepository).findByMobileNo((String) any());
    }

    /**
     * Method under test: {@link CustomerCreationServiceImpl#deleteCustomerById(Long)}
     */
    @Test
    void testDeleteCustomerById() throws RecordNotFoundException {
        // Arrange
        CustomerCreationEntity customerCreationEntity = new CustomerCreationEntity();
        customerCreationEntity.setAddress1("42 Main St");
        customerCreationEntity.setAddress2("42 Main St");
        customerCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        customerCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setCreditLimit(1);
        customerCreationEntity.setCustomerName("Customer Name");
        customerCreationEntity.setDiscount(3);
        customerCreationEntity.setDueDays(1);
        customerCreationEntity.setGstNo("Gst No");
        customerCreationEntity.setGstType("Gst Type");
        customerCreationEntity.setId(123L);
        customerCreationEntity.setMobileNo("Mobile No");
        customerCreationEntity.setModeOfPayment("Mode Of Payment");
        customerCreationEntity.setOpeningBal(1);
        customerCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setRateSlab(1);
        customerCreationEntity.setStatus("Status");
        customerCreationEntity.setUpdated_by("2020-03-01");
        customerCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));
        Optional<CustomerCreationEntity> ofResult = Optional.of(customerCreationEntity);
        doNothing().when(cutomerCreationRepository).deleteById((Long) any());
        when(cutomerCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act
        customerCreationServiceImpl.deleteCustomerById(123L);

        // Assert that nothing has changed
        verify(cutomerCreationRepository).findById((Long) any());
        verify(cutomerCreationRepository).deleteById((Long) any());
        assertTrue(customerCreationServiceImpl.getAllCutomer().isEmpty());
    }

    /**
     * Method under test: {@link CustomerCreationServiceImpl#deleteCustomerById(Long)}
     */
    @Test
    void testDeleteCustomerById2() throws RecordNotFoundException {
        // Arrange
        CustomerCreationEntity customerCreationEntity = new CustomerCreationEntity();
        customerCreationEntity.setAddress1("42 Main St");
        customerCreationEntity.setAddress2("42 Main St");
        customerCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        customerCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setCreditLimit(1);
        customerCreationEntity.setCustomerName("Customer Name");
        customerCreationEntity.setDiscount(3);
        customerCreationEntity.setDueDays(1);
        customerCreationEntity.setGstNo("Gst No");
        customerCreationEntity.setGstType("Gst Type");
        customerCreationEntity.setId(123L);
        customerCreationEntity.setMobileNo("Mobile No");
        customerCreationEntity.setModeOfPayment("Mode Of Payment");
        customerCreationEntity.setOpeningBal(1);
        customerCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setRateSlab(1);
        customerCreationEntity.setStatus("Status");
        customerCreationEntity.setUpdated_by("2020-03-01");
        customerCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));
        Optional<CustomerCreationEntity> ofResult = Optional.of(customerCreationEntity);
        doThrow(new UnableToProcessException("An error occurred")).when(cutomerCreationRepository)
                .deleteById((Long) any());
        when(cutomerCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> customerCreationServiceImpl.deleteCustomerById(123L));
        verify(cutomerCreationRepository).findById((Long) any());
        verify(cutomerCreationRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link CustomerCreationServiceImpl#deleteCustomerById(Long)}
     */
    @Test
    void testDeleteCustomerById3() throws RecordNotFoundException {
        // Arrange
        doNothing().when(cutomerCreationRepository).deleteById((Long) any());
        when(cutomerCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> customerCreationServiceImpl.deleteCustomerById(123L));
        verify(cutomerCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CustomerCreationServiceImpl#getCustomerByMobile(String)}
     */
    @Test
    void testGetCustomerByMobile() {
        // Arrange
        CustomerCreationEntity customerCreationEntity = new CustomerCreationEntity();
        customerCreationEntity.setAddress1("42 Main St");
        customerCreationEntity.setAddress2("42 Main St");
        customerCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        customerCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setCreditLimit(1);
        customerCreationEntity.setCustomerName("Customer Name");
        customerCreationEntity.setDiscount(3);
        customerCreationEntity.setDueDays(1);
        customerCreationEntity.setGstNo("Gst No");
        customerCreationEntity.setGstType("Gst Type");
        customerCreationEntity.setId(123L);
        customerCreationEntity.setMobileNo("Mobile No");
        customerCreationEntity.setModeOfPayment("Mode Of Payment");
        customerCreationEntity.setOpeningBal(1);
        customerCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setRateSlab(1);
        customerCreationEntity.setStatus("Status");
        customerCreationEntity.setUpdated_by("2020-03-01");
        customerCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));
        when(cutomerCreationRepository.findByMobileNo((String) any())).thenReturn(customerCreationEntity);

        // Act and Assert
        assertSame(customerCreationEntity, customerCreationServiceImpl.getCustomerByMobile("Mobile"));
        verify(cutomerCreationRepository).findByMobileNo((String) any());
    }

    /**
     * Method under test: {@link CustomerCreationServiceImpl#getCustomerByMobile(String)}
     */
    @Test
    void testGetCustomerByMobile2() {
        // Arrange
        when(cutomerCreationRepository.findByMobileNo((String) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> customerCreationServiceImpl.getCustomerByMobile("Mobile"));
        verify(cutomerCreationRepository).findByMobileNo((String) any());
    }

    /**
     * Method under test: {@link CustomerCreationServiceImpl#createOrUpdateCustomerList(List)}
     */
    @Test
    void testCreateOrUpdateCustomerList() {
        // Arrange
        when(cutomerCreationRepository.saveAll((Iterable<CustomerCreationEntity>) any())).thenReturn(new ArrayList<>());

        // Act
        customerCreationServiceImpl.createOrUpdateCustomerList(new ArrayList<>());

        // Assert that nothing has changed
        verify(cutomerCreationRepository).saveAll((Iterable<CustomerCreationEntity>) any());
        assertTrue(customerCreationServiceImpl.getAllCutomer().isEmpty());
    }

    /**
     * Method under test: {@link CustomerCreationServiceImpl#createOrUpdateCustomerList(List)}
     */
    @Test
    void testCreateOrUpdateCustomerList2() {
        // Arrange
        when(cutomerCreationRepository.saveAll((Iterable<CustomerCreationEntity>) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> customerCreationServiceImpl.createOrUpdateCustomerList(new ArrayList<>()));
        verify(cutomerCreationRepository).saveAll((Iterable<CustomerCreationEntity>) any());
    }

    /**
     * Method under test: {@link CustomerCreationServiceImpl#getAllCustomersWithPagination(int, int)}
     */
    @Test
    void testGetAllCustomersWithPagination() {
        // Arrange
        PageImpl<CustomerCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(cutomerCreationRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<CustomerCreationEntity> actualAllCustomersWithPagination = customerCreationServiceImpl
                .getAllCustomersWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllCustomersWithPagination);
        assertTrue(actualAllCustomersWithPagination.toList().isEmpty());
        verify(cutomerCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link CustomerCreationServiceImpl#getAllCustomersWithPagination(int, int)}
     */
    @Test
    void testGetAllCustomersWithPagination2() {
        // Arrange
        when(cutomerCreationRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> customerCreationServiceImpl.getAllCustomersWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link CustomerCreationServiceImpl#getAllCustomersWithPagination(int, int)}
     */
    @Test
    void testGetAllCustomersWithPagination3() {
        // Arrange
        when(cutomerCreationRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> customerCreationServiceImpl.getAllCustomersWithPagination(2, 3));
        verify(cutomerCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link CustomerCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<CustomerCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(cutomerCreationRepository.findAllByCustomerNameOrMobileNoContains((String) any(),(String) any(), (Pageable) any()))
                .thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<CustomerCreationEntity> actualSearch = customerCreationServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(cutomerCreationRepository).findAllByCustomerNameOrMobileNoContains((String) any(),(String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link CustomerCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(cutomerCreationRepository.findAllByCustomerNameOrMobileNoContains((String) any(),(String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> customerCreationServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(cutomerCreationRepository).findAllByCustomerNameOrMobileNoContains((String) any(), (String) any(),(Pageable) any());
    }

    /**
     * Method under test: {@link CustomerCreationServiceImpl#updateCustomer(CustomerCreationEntity)}
     */
    @Test
    void testUpdateCustomer() {
        // Arrange
        CustomerCreationEntity customerCreationEntity = new CustomerCreationEntity();
        customerCreationEntity.setAddress1("42 Main St");
        customerCreationEntity.setAddress2("42 Main St");
        customerCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        customerCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setCreditLimit(1);
        customerCreationEntity.setCustomerName("Customer Name");
        customerCreationEntity.setDiscount(3);
        customerCreationEntity.setDueDays(1);
        customerCreationEntity.setGstNo("Gst No");
        customerCreationEntity.setGstType("Gst Type");
        customerCreationEntity.setId(123L);
        customerCreationEntity.setMobileNo("Mobile No");
        customerCreationEntity.setModeOfPayment("Mode Of Payment");
        customerCreationEntity.setOpeningBal(1);
        customerCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setRateSlab(1);
        customerCreationEntity.setStatus("Status");
        customerCreationEntity.setUpdated_by("2020-03-01");
        customerCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));
        when(cutomerCreationRepository.save((CustomerCreationEntity) any())).thenReturn(customerCreationEntity);

        CustomerCreationEntity customerCreationEntity1 = new CustomerCreationEntity();
        customerCreationEntity1.setAddress1("42 Main St");
        customerCreationEntity1.setAddress2("42 Main St");
        customerCreationEntity1.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        customerCreationEntity1.setCreated_date(LocalDate.ofEpochDay(1L));
        customerCreationEntity1.setCreditLimit(1);
        customerCreationEntity1.setCustomerName("Customer Name");
        customerCreationEntity1.setDiscount(3);
        customerCreationEntity1.setDueDays(1);
        customerCreationEntity1.setGstNo("Gst No");
        customerCreationEntity1.setGstType("Gst Type");
        customerCreationEntity1.setId(123L);
        customerCreationEntity1.setMobileNo("Mobile No");
        customerCreationEntity1.setModeOfPayment("Mode Of Payment");
        customerCreationEntity1.setOpeningBal(1);
        customerCreationEntity1.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        customerCreationEntity1.setRateSlab(1);
        customerCreationEntity1.setStatus("Status");
        customerCreationEntity1.setUpdated_by("2020-03-01");
        customerCreationEntity1.setUpdated_date(LocalDate.ofEpochDay(1L));

        // Act and Assert
        assertSame(customerCreationEntity, customerCreationServiceImpl.updateCustomer(customerCreationEntity1));
        verify(cutomerCreationRepository).save((CustomerCreationEntity) any());
        assertEquals("admin", customerCreationEntity1.getCreated_by());
    }

    /**
     * Method under test: {@link CustomerCreationServiceImpl#updateCustomer(CustomerCreationEntity)}
     */
    @Test
    void testUpdateCustomer2() {
        // Arrange
        when(cutomerCreationRepository.save((CustomerCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        CustomerCreationEntity customerCreationEntity = new CustomerCreationEntity();
        customerCreationEntity.setAddress1("42 Main St");
        customerCreationEntity.setAddress2("42 Main St");
        customerCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        customerCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setCreditLimit(1);
        customerCreationEntity.setCustomerName("Customer Name");
        customerCreationEntity.setDiscount(3);
        customerCreationEntity.setDueDays(1);
        customerCreationEntity.setGstNo("Gst No");
        customerCreationEntity.setGstType("Gst Type");
        customerCreationEntity.setId(123L);
        customerCreationEntity.setMobileNo("Mobile No");
        customerCreationEntity.setModeOfPayment("Mode Of Payment");
        customerCreationEntity.setOpeningBal(1);
        customerCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setRateSlab(1);
        customerCreationEntity.setStatus("Status");
        customerCreationEntity.setUpdated_by("2020-03-01");
        customerCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> customerCreationServiceImpl.updateCustomer(customerCreationEntity));
        verify(cutomerCreationRepository).save((CustomerCreationEntity) any());
    }
}

