package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.SupplierPaymentEntryRepository;
import com.ets.SecurePharmacy.tenant.model.AccountCreationEntity;
import com.ets.SecurePharmacy.tenant.model.SupplierPaymentEntry;
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

@ContextConfiguration(classes = {SupplierPaymentEntryServiceImpl.class})
@ExtendWith(SpringExtension.class)
class SupplierPaymentEntryServiceImplTest {
    @MockBean
    private SupplierPaymentEntryRepository supplierPaymentEntryRepository;

    @Autowired
    private SupplierPaymentEntryServiceImpl supplierPaymentEntryServiceImpl;

    /**
     * Method under test: {@link SupplierPaymentEntryServiceImpl#getAll()}
     */
    @Test
    void testGetAll() {
        // Arrange
        when(supplierPaymentEntryRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(supplierPaymentEntryServiceImpl.getAll().isEmpty());
        verify(supplierPaymentEntryRepository).findAll();
    }

    /**
     * Method under test: {@link SupplierPaymentEntryServiceImpl#getAll()}
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

        SupplierPaymentEntry supplierPaymentEntry = new SupplierPaymentEntry();
        supplierPaymentEntry.setAccountEntity(accountCreationEntity);
        supplierPaymentEntry.setId(123L);
        supplierPaymentEntry.setNetAmount(10.0d);
        supplierPaymentEntry.setPaymentDate(LocalDate.ofEpochDay(1L));
        supplierPaymentEntry.setRefernceDate(LocalDate.ofEpochDay(1L));
        supplierPaymentEntry.setRefernceNumber("42");
        supplierPaymentEntry.setRemarks("Remarks");
        supplierPaymentEntry.setStatus("Status");
        supplierPaymentEntry.setSupplierPaymentEntryDetails(new ArrayList<>());

        ArrayList<SupplierPaymentEntry> supplierPaymentEntryList = new ArrayList<>();
        supplierPaymentEntryList.add(supplierPaymentEntry);
        when(supplierPaymentEntryRepository.findAll()).thenReturn(supplierPaymentEntryList);

        // Act
        List<SupplierPaymentEntry> actualAll = supplierPaymentEntryServiceImpl.getAll();

        // Assert
        assertSame(supplierPaymentEntryList, actualAll);
        assertEquals(1, actualAll.size());
        verify(supplierPaymentEntryRepository).findAll();
    }

    /**
     * Method under test: {@link SupplierPaymentEntryServiceImpl#getAll()}
     */
    @Test
    void testGetAll3() {
        // Arrange
        when(supplierPaymentEntryRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> supplierPaymentEntryServiceImpl.getAll());
        verify(supplierPaymentEntryRepository).findAll();
    }

    /**
     * Method under test: {@link SupplierPaymentEntryServiceImpl#getById(Long)}
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

        SupplierPaymentEntry supplierPaymentEntry = new SupplierPaymentEntry();
        supplierPaymentEntry.setAccountEntity(accountCreationEntity);
        supplierPaymentEntry.setId(123L);
        supplierPaymentEntry.setNetAmount(10.0d);
        supplierPaymentEntry.setPaymentDate(LocalDate.ofEpochDay(1L));
        supplierPaymentEntry.setRefernceDate(LocalDate.ofEpochDay(1L));
        supplierPaymentEntry.setRefernceNumber("42");
        supplierPaymentEntry.setRemarks("Remarks");
        supplierPaymentEntry.setStatus("Status");
        supplierPaymentEntry.setSupplierPaymentEntryDetails(new ArrayList<>());
        Optional<SupplierPaymentEntry> ofResult = Optional.of(supplierPaymentEntry);
        when(supplierPaymentEntryRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(supplierPaymentEntry, supplierPaymentEntryServiceImpl.getById(123L));
        verify(supplierPaymentEntryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SupplierPaymentEntryServiceImpl#createOrUpdate(SupplierPaymentEntry)}
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

        SupplierPaymentEntry supplierPaymentEntry = new SupplierPaymentEntry();
        supplierPaymentEntry.setAccountEntity(accountCreationEntity);
        supplierPaymentEntry.setId(123L);
        supplierPaymentEntry.setNetAmount(10.0d);
        supplierPaymentEntry.setPaymentDate(LocalDate.ofEpochDay(1L));
        supplierPaymentEntry.setRefernceDate(LocalDate.ofEpochDay(1L));
        supplierPaymentEntry.setRefernceNumber("42");
        supplierPaymentEntry.setRemarks("Remarks");
        supplierPaymentEntry.setStatus("Status");
        supplierPaymentEntry.setSupplierPaymentEntryDetails(new ArrayList<>());
        when(supplierPaymentEntryRepository.save((SupplierPaymentEntry) any())).thenReturn(supplierPaymentEntry);

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

        SupplierPaymentEntry supplierPaymentEntry1 = new SupplierPaymentEntry();
        supplierPaymentEntry1.setAccountEntity(accountCreationEntity1);
        supplierPaymentEntry1.setId(123L);
        supplierPaymentEntry1.setNetAmount(10.0d);
        supplierPaymentEntry1.setPaymentDate(LocalDate.ofEpochDay(1L));
        supplierPaymentEntry1.setRefernceDate(LocalDate.ofEpochDay(1L));
        supplierPaymentEntry1.setRefernceNumber("42");
        supplierPaymentEntry1.setRemarks("Remarks");
        supplierPaymentEntry1.setStatus("Status");
        supplierPaymentEntry1.setSupplierPaymentEntryDetails(new ArrayList<>());

        // Act and Assert
        assertSame(supplierPaymentEntry, supplierPaymentEntryServiceImpl.createOrUpdate(supplierPaymentEntry1));
        verify(supplierPaymentEntryRepository).save((SupplierPaymentEntry) any());
    }

    /**
     * Method under test: {@link SupplierPaymentEntryServiceImpl#createOrUpdate(SupplierPaymentEntry)}
     */
    @Test
    void testCreateOrUpdate2() {
        // Arrange
        when(supplierPaymentEntryRepository.save((SupplierPaymentEntry) any()))
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

        SupplierPaymentEntry supplierPaymentEntry = new SupplierPaymentEntry();
        supplierPaymentEntry.setAccountEntity(accountCreationEntity);
        supplierPaymentEntry.setId(123L);
        supplierPaymentEntry.setNetAmount(10.0d);
        supplierPaymentEntry.setPaymentDate(LocalDate.ofEpochDay(1L));
        supplierPaymentEntry.setRefernceDate(LocalDate.ofEpochDay(1L));
        supplierPaymentEntry.setRefernceNumber("42");
        supplierPaymentEntry.setRemarks("Remarks");
        supplierPaymentEntry.setStatus("Status");
        supplierPaymentEntry.setSupplierPaymentEntryDetails(new ArrayList<>());

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> supplierPaymentEntryServiceImpl.createOrUpdate(supplierPaymentEntry));
        verify(supplierPaymentEntryRepository).save((SupplierPaymentEntry) any());
    }

    /**
     * Method under test: {@link SupplierPaymentEntryServiceImpl#getAllPaymentEntryWithPagination(int, int)}
     */
    @Test
    void testGetAllPaymentEntryWithPagination() {
        // Arrange
        PageImpl<SupplierPaymentEntry> pageImpl = new PageImpl<>(new ArrayList<>());
        when(supplierPaymentEntryRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<SupplierPaymentEntry> actualAllPaymentEntryWithPagination = supplierPaymentEntryServiceImpl
                .getAllPaymentEntryWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllPaymentEntryWithPagination);
        assertTrue(actualAllPaymentEntryWithPagination.toList().isEmpty());
        verify(supplierPaymentEntryRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link SupplierPaymentEntryServiceImpl#getAllPaymentEntryWithPagination(int, int)}
     */
    @Test
    void testGetAllPaymentEntryWithPagination2() {
        // Arrange
        when(supplierPaymentEntryRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> supplierPaymentEntryServiceImpl.getAllPaymentEntryWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link SupplierPaymentEntryServiceImpl#getAllPaymentEntryWithPagination(int, int)}
     */
    @Test
    void testGetAllPaymentEntryWithPagination3() {
        // Arrange
        when(supplierPaymentEntryRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> supplierPaymentEntryServiceImpl.getAllPaymentEntryWithPagination(2, 3));
        verify(supplierPaymentEntryRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link SupplierPaymentEntryServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<SupplierPaymentEntry> pageImpl = new PageImpl<>(new ArrayList<>());
        when(supplierPaymentEntryRepository.findAllByPaymentDateContains((String) any(), (Pageable) any()))
                .thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<SupplierPaymentEntry> actualSearch = supplierPaymentEntryServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(supplierPaymentEntryRepository).findAllByPaymentDateContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link SupplierPaymentEntryServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(supplierPaymentEntryRepository.findAllByPaymentDateContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> supplierPaymentEntryServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(supplierPaymentEntryRepository).findAllByPaymentDateContains((String) any(), (Pageable) any());
    }
}

