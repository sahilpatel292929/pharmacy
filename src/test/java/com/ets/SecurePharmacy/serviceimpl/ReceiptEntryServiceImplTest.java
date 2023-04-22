package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.ReceiptEntryRepository;
import com.ets.SecurePharmacy.tenant.model.AccountCreationEntity;
import com.ets.SecurePharmacy.tenant.model.ReceiptEntryEntity;
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

@ContextConfiguration(classes = {ReceiptEntryServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ReceiptEntryServiceImplTest {
    @MockBean
    private ReceiptEntryRepository receiptEntryRepository;

    @Autowired
    private ReceiptEntryServiceImpl receiptEntryServiceImpl;

    /**
     * Method under test: {@link ReceiptEntryServiceImpl#getAll()}
     */
    @Test
    void testGetAll() {
        // Arrange
        when(receiptEntryRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(receiptEntryServiceImpl.getAll().isEmpty());
        verify(receiptEntryRepository).findAll();
    }

    /**
     * Method under test: {@link ReceiptEntryServiceImpl#getAll()}
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

        ReceiptEntryEntity receiptEntryEntity = new ReceiptEntryEntity();
        receiptEntryEntity.setAccountEntity(accountCreationEntity);
        receiptEntryEntity.setAmount(10.0d);
        receiptEntryEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        receiptEntryEntity.setId(123L);
        receiptEntryEntity.setReceiptDetailsEntity(new ArrayList<>());
        receiptEntryEntity.setReferenceNumber("42");
        receiptEntryEntity.setRefernceDate(LocalDate.ofEpochDay(1L));
        receiptEntryEntity.setRemarks("Remarks");
        receiptEntryEntity.setStatus("Status");
        receiptEntryEntity.setTotalAmount(10.0d);

        ArrayList<ReceiptEntryEntity> receiptEntryEntityList = new ArrayList<>();
        receiptEntryEntityList.add(receiptEntryEntity);
        when(receiptEntryRepository.findAll()).thenReturn(receiptEntryEntityList);

        // Act
        List<ReceiptEntryEntity> actualAll = receiptEntryServiceImpl.getAll();

        // Assert
        assertSame(receiptEntryEntityList, actualAll);
        assertEquals(1, actualAll.size());
        verify(receiptEntryRepository).findAll();
    }

    /**
     * Method under test: {@link ReceiptEntryServiceImpl#getAll()}
     */
    @Test
    void testGetAll3() {
        // Arrange
        when(receiptEntryRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> receiptEntryServiceImpl.getAll());
        verify(receiptEntryRepository).findAll();
    }

    /**
     * Method under test: {@link ReceiptEntryServiceImpl#getById(Long)}
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

        ReceiptEntryEntity receiptEntryEntity = new ReceiptEntryEntity();
        receiptEntryEntity.setAccountEntity(accountCreationEntity);
        receiptEntryEntity.setAmount(10.0d);
        receiptEntryEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        receiptEntryEntity.setId(123L);
        receiptEntryEntity.setReceiptDetailsEntity(new ArrayList<>());
        receiptEntryEntity.setReferenceNumber("42");
        receiptEntryEntity.setRefernceDate(LocalDate.ofEpochDay(1L));
        receiptEntryEntity.setRemarks("Remarks");
        receiptEntryEntity.setStatus("Status");
        receiptEntryEntity.setTotalAmount(10.0d);
        Optional<ReceiptEntryEntity> ofResult = Optional.of(receiptEntryEntity);
        when(receiptEntryRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(receiptEntryEntity, receiptEntryServiceImpl.getById(123L));
        verify(receiptEntryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ReceiptEntryServiceImpl#getById(Long)}
     */
    @Test
    void testGetById2() throws RecordNotFoundException {
        // Arrange
        when(receiptEntryRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> receiptEntryServiceImpl.getById(123L));
        verify(receiptEntryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ReceiptEntryServiceImpl#getById(Long)}
     */
    @Test
    void testGetById3() throws RecordNotFoundException {
        // Arrange
        when(receiptEntryRepository.findById((Long) any())).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> receiptEntryServiceImpl.getById(123L));
        verify(receiptEntryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ReceiptEntryServiceImpl#createOrUpdate(ReceiptEntryEntity)}
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

        ReceiptEntryEntity receiptEntryEntity = new ReceiptEntryEntity();
        receiptEntryEntity.setAccountEntity(accountCreationEntity);
        receiptEntryEntity.setAmount(10.0d);
        receiptEntryEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        receiptEntryEntity.setId(123L);
        receiptEntryEntity.setReceiptDetailsEntity(new ArrayList<>());
        receiptEntryEntity.setReferenceNumber("42");
        receiptEntryEntity.setRefernceDate(LocalDate.ofEpochDay(1L));
        receiptEntryEntity.setRemarks("Remarks");
        receiptEntryEntity.setStatus("Status");
        receiptEntryEntity.setTotalAmount(10.0d);
        when(receiptEntryRepository.save((ReceiptEntryEntity) any())).thenReturn(receiptEntryEntity);

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

        ReceiptEntryEntity receiptEntryEntity1 = new ReceiptEntryEntity();
        receiptEntryEntity1.setAccountEntity(accountCreationEntity1);
        receiptEntryEntity1.setAmount(10.0d);
        receiptEntryEntity1.setEntryDate(LocalDate.ofEpochDay(1L));
        receiptEntryEntity1.setId(123L);
        receiptEntryEntity1.setReceiptDetailsEntity(new ArrayList<>());
        receiptEntryEntity1.setReferenceNumber("42");
        receiptEntryEntity1.setRefernceDate(LocalDate.ofEpochDay(1L));
        receiptEntryEntity1.setRemarks("Remarks");
        receiptEntryEntity1.setStatus("Status");
        receiptEntryEntity1.setTotalAmount(10.0d);

        // Act and Assert
        assertSame(receiptEntryEntity, receiptEntryServiceImpl.createOrUpdate(receiptEntryEntity1));
        verify(receiptEntryRepository).save((ReceiptEntryEntity) any());
    }

    /**
     * Method under test: {@link ReceiptEntryServiceImpl#createOrUpdate(ReceiptEntryEntity)}
     */
    @Test
    void testCreateOrUpdate2() {
        // Arrange
        when(receiptEntryRepository.save((ReceiptEntryEntity) any()))
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

        ReceiptEntryEntity receiptEntryEntity = new ReceiptEntryEntity();
        receiptEntryEntity.setAccountEntity(accountCreationEntity);
        receiptEntryEntity.setAmount(10.0d);
        receiptEntryEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        receiptEntryEntity.setId(123L);
        receiptEntryEntity.setReceiptDetailsEntity(new ArrayList<>());
        receiptEntryEntity.setReferenceNumber("42");
        receiptEntryEntity.setRefernceDate(LocalDate.ofEpochDay(1L));
        receiptEntryEntity.setRemarks("Remarks");
        receiptEntryEntity.setStatus("Status");
        receiptEntryEntity.setTotalAmount(10.0d);

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> receiptEntryServiceImpl.createOrUpdate(receiptEntryEntity));
        verify(receiptEntryRepository).save((ReceiptEntryEntity) any());
    }

    /**
     * Method under test: {@link ReceiptEntryServiceImpl#getAllReceiptEntryWithPagination(int, int)}
     */
    @Test
    void testGetAllReceiptEntryWithPagination() {
        // Arrange
        PageImpl<ReceiptEntryEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(receiptEntryRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<ReceiptEntryEntity> actualAllReceiptEntryWithPagination = receiptEntryServiceImpl
                .getAllReceiptEntryWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllReceiptEntryWithPagination);
        assertTrue(actualAllReceiptEntryWithPagination.toList().isEmpty());
        verify(receiptEntryRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link ReceiptEntryServiceImpl#getAllReceiptEntryWithPagination(int, int)}
     */
    @Test
    void testGetAllReceiptEntryWithPagination2() {
        // Arrange
        when(receiptEntryRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> receiptEntryServiceImpl.getAllReceiptEntryWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link ReceiptEntryServiceImpl#getAllReceiptEntryWithPagination(int, int)}
     */
    @Test
    void testGetAllReceiptEntryWithPagination3() {
        // Arrange
        when(receiptEntryRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> receiptEntryServiceImpl.getAllReceiptEntryWithPagination(2, 3));
        verify(receiptEntryRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link ReceiptEntryServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<ReceiptEntryEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(receiptEntryRepository.findAllByReferenceNumberContains((String) any(), (Pageable) any()))
                .thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<ReceiptEntryEntity> actualSearch = receiptEntryServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(receiptEntryRepository).findAllByReferenceNumberContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link ReceiptEntryServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(receiptEntryRepository.findAllByReferenceNumberContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> receiptEntryServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(receiptEntryRepository).findAllByReferenceNumberContains((String) any(), (Pageable) any());
    }
}

