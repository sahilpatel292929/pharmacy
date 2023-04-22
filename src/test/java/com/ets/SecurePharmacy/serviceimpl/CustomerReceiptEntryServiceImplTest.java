package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.CustomerReceiptEntryRepository;
import com.ets.SecurePharmacy.tenant.model.AccountCreationEntity;
import com.ets.SecurePharmacy.tenant.model.CustomerReceiptEntryEntity;
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

@ContextConfiguration(classes = {CustomerReceiptEntryServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CustomerReceiptEntryServiceImplTest {
    @MockBean
    private CustomerReceiptEntryRepository customerReceiptEntryRepository;

    @Autowired
    private CustomerReceiptEntryServiceImpl customerReceiptEntryServiceImpl;

    /**
     * Method under test: {@link CustomerReceiptEntryServiceImpl#getAll()}
     */
    @Test
    void testGetAll() {
        // Arrange
        when(customerReceiptEntryRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(customerReceiptEntryServiceImpl.getAll().isEmpty());
        verify(customerReceiptEntryRepository).findAll();
    }

    /**
     * Method under test: {@link CustomerReceiptEntryServiceImpl#getAll()}
     */
    @Test
    void testGetAll2() {
        // Arrange
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

        CustomerReceiptEntryEntity customerReceiptEntryEntity = new CustomerReceiptEntryEntity();
        customerReceiptEntryEntity.setAccountEntity(accountCreationEntity);
        customerReceiptEntryEntity.setCustomerReceiptDetailsEntity(new ArrayList<>());
        customerReceiptEntryEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        customerReceiptEntryEntity.setId(123L);
        customerReceiptEntryEntity.setReferenceNumber("42");
        customerReceiptEntryEntity.setRefernceDate(LocalDate.ofEpochDay(1L));
        customerReceiptEntryEntity.setRemarks("Remarks");
        customerReceiptEntryEntity.setStatus("Status");
        customerReceiptEntryEntity.setTotalAmount(10L);

        ArrayList<CustomerReceiptEntryEntity> customerReceiptEntryEntityList = new ArrayList<>();
        customerReceiptEntryEntityList.add(customerReceiptEntryEntity);
        when(customerReceiptEntryRepository.findAll()).thenReturn(customerReceiptEntryEntityList);

        // Act
        List<CustomerReceiptEntryEntity> actualAll = customerReceiptEntryServiceImpl.getAll();

        // Assert
        assertSame(customerReceiptEntryEntityList, actualAll);
        assertEquals(1, actualAll.size());
        verify(customerReceiptEntryRepository).findAll();
    }

    /**
     * Method under test: {@link CustomerReceiptEntryServiceImpl#getAll()}
     */
    @Test
    void testGetAll3() {
        // Arrange
        when(customerReceiptEntryRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> customerReceiptEntryServiceImpl.getAll());
        verify(customerReceiptEntryRepository).findAll();
    }

    /**
     * Method under test: {@link CustomerReceiptEntryServiceImpl#getById(Long)}
     */
    @Test
    void testGetById() throws RecordNotFoundException {
        // Arrange
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

        CustomerReceiptEntryEntity customerReceiptEntryEntity = new CustomerReceiptEntryEntity();
        customerReceiptEntryEntity.setAccountEntity(accountCreationEntity);
        customerReceiptEntryEntity.setCustomerReceiptDetailsEntity(new ArrayList<>());
        customerReceiptEntryEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        customerReceiptEntryEntity.setId(123L);
        customerReceiptEntryEntity.setReferenceNumber("42");
        customerReceiptEntryEntity.setRefernceDate(LocalDate.ofEpochDay(1L));
        customerReceiptEntryEntity.setRemarks("Remarks");
        customerReceiptEntryEntity.setStatus("Status");
        customerReceiptEntryEntity.setTotalAmount(10L);
        Optional<CustomerReceiptEntryEntity> ofResult = Optional.of(customerReceiptEntryEntity);
        when(customerReceiptEntryRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(customerReceiptEntryEntity, customerReceiptEntryServiceImpl.getById(123L));
        verify(customerReceiptEntryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CustomerReceiptEntryServiceImpl#getById(Long)}
     */
    @Test
    void testGetById2() throws RecordNotFoundException {
        // Arrange
        when(customerReceiptEntryRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> customerReceiptEntryServiceImpl.getById(123L));
        verify(customerReceiptEntryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CustomerReceiptEntryServiceImpl#getById(Long)}
     */
    @Test
    void testGetById3() throws RecordNotFoundException {
        // Arrange
        when(customerReceiptEntryRepository.findById((Long) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> customerReceiptEntryServiceImpl.getById(123L));
        verify(customerReceiptEntryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CustomerReceiptEntryServiceImpl#createOrUpdate(CustomerReceiptEntryEntity)}
     */
    @Test
    void testCreateOrUpdate() {
        // Arrange
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

        CustomerReceiptEntryEntity customerReceiptEntryEntity = new CustomerReceiptEntryEntity();
        customerReceiptEntryEntity.setAccountEntity(accountCreationEntity);
        customerReceiptEntryEntity.setCustomerReceiptDetailsEntity(new ArrayList<>());
        customerReceiptEntryEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        customerReceiptEntryEntity.setId(123L);
        customerReceiptEntryEntity.setReferenceNumber("42");
        customerReceiptEntryEntity.setRefernceDate(LocalDate.ofEpochDay(1L));
        customerReceiptEntryEntity.setRemarks("Remarks");
        customerReceiptEntryEntity.setStatus("Status");
        customerReceiptEntryEntity.setTotalAmount(10L);
        when(customerReceiptEntryRepository.save((CustomerReceiptEntryEntity) any()))
                .thenReturn(customerReceiptEntryEntity);

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

        CustomerReceiptEntryEntity customerReceiptEntryEntity1 = new CustomerReceiptEntryEntity();
        customerReceiptEntryEntity1.setAccountEntity(accountCreationEntity1);
        customerReceiptEntryEntity1.setCustomerReceiptDetailsEntity(new ArrayList<>());
        customerReceiptEntryEntity1.setEntryDate(LocalDate.ofEpochDay(1L));
        customerReceiptEntryEntity1.setId(123L);
        customerReceiptEntryEntity1.setReferenceNumber("42");
        customerReceiptEntryEntity1.setRefernceDate(LocalDate.ofEpochDay(1L));
        customerReceiptEntryEntity1.setRemarks("Remarks");
        customerReceiptEntryEntity1.setStatus("Status");
        customerReceiptEntryEntity1.setTotalAmount(10L);

        // Act and Assert
        assertSame(customerReceiptEntryEntity, customerReceiptEntryServiceImpl.createOrUpdate(customerReceiptEntryEntity1));
        verify(customerReceiptEntryRepository).save((CustomerReceiptEntryEntity) any());
    }

    /**
     * Method under test: {@link CustomerReceiptEntryServiceImpl#createOrUpdate(CustomerReceiptEntryEntity)}
     */
    @Test
    void testCreateOrUpdate2() {
        // Arrange
        when(customerReceiptEntryRepository.save((CustomerReceiptEntryEntity) any()))
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

        CustomerReceiptEntryEntity customerReceiptEntryEntity = new CustomerReceiptEntryEntity();
        customerReceiptEntryEntity.setAccountEntity(accountCreationEntity);
        customerReceiptEntryEntity.setCustomerReceiptDetailsEntity(new ArrayList<>());
        customerReceiptEntryEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        customerReceiptEntryEntity.setId(123L);
        customerReceiptEntryEntity.setReferenceNumber("42");
        customerReceiptEntryEntity.setRefernceDate(LocalDate.ofEpochDay(1L));
        customerReceiptEntryEntity.setRemarks("Remarks");
        customerReceiptEntryEntity.setStatus("Status");
        customerReceiptEntryEntity.setTotalAmount(10L);

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> customerReceiptEntryServiceImpl.createOrUpdate(customerReceiptEntryEntity));
        verify(customerReceiptEntryRepository).save((CustomerReceiptEntryEntity) any());
    }

    /**
     * Method under test: {@link CustomerReceiptEntryServiceImpl#getAllReceiptEntryWithPagination(int, int)}
     */
    @Test
    void testGetAllReceiptEntryWithPagination() {
        // Arrange
        PageImpl<CustomerReceiptEntryEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(customerReceiptEntryRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<CustomerReceiptEntryEntity> actualAllReceiptEntryWithPagination = customerReceiptEntryServiceImpl
                .getAllReceiptEntryWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllReceiptEntryWithPagination);
        assertTrue(actualAllReceiptEntryWithPagination.toList().isEmpty());
        verify(customerReceiptEntryRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link CustomerReceiptEntryServiceImpl#getAllReceiptEntryWithPagination(int, int)}
     */
    @Test
    void testGetAllReceiptEntryWithPagination2() {
        // Arrange
        when(customerReceiptEntryRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> customerReceiptEntryServiceImpl.getAllReceiptEntryWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link CustomerReceiptEntryServiceImpl#getAllReceiptEntryWithPagination(int, int)}
     */
    @Test
    void testGetAllReceiptEntryWithPagination3() {
        // Arrange
        when(customerReceiptEntryRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> customerReceiptEntryServiceImpl.getAllReceiptEntryWithPagination(2, 3));
        verify(customerReceiptEntryRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link CustomerReceiptEntryServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<CustomerReceiptEntryEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(customerReceiptEntryRepository.findAllByReferenceNumberContains((String) any(), (Pageable) any()))
                .thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<CustomerReceiptEntryEntity> actualSearch = customerReceiptEntryServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(customerReceiptEntryRepository).findAllByReferenceNumberContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link CustomerReceiptEntryServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(customerReceiptEntryRepository.findAllByReferenceNumberContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> customerReceiptEntryServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(customerReceiptEntryRepository).findAllByReferenceNumberContains((String) any(), (Pageable) any());
    }
}

