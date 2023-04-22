package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.AccountCreationRepository;
import com.ets.SecurePharmacy.tenant.dao.PaymentEntryRepository;
import com.ets.SecurePharmacy.tenant.model.AccountCreationEntity;
import com.ets.SecurePharmacy.tenant.model.PaymentEntryEntity;
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

@ContextConfiguration(classes = {PaymentEntryServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PaymentEntryServiceImplTest {
    @MockBean
    private AccountCreationRepository accountCreationRepository;

    @MockBean
    private PaymentEntryRepository paymentEntryRepository;

    @Autowired
    private PaymentEntryServiceImpl paymentEntryServiceImpl;

    /**
     * Method under test: {@link PaymentEntryServiceImpl#getAll()}
     */
    @Test
    void testGetAll() {
        // Arrange
        when(paymentEntryRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(paymentEntryServiceImpl.getAll().isEmpty());
        verify(paymentEntryRepository).findAll();
    }

    /**
     * Method under test: {@link PaymentEntryServiceImpl#getAll()}
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

        PaymentEntryEntity paymentEntryEntity = new PaymentEntryEntity();
        paymentEntryEntity.setAccountEntity(accountCreationEntity);
        paymentEntryEntity.setId(123L);
        paymentEntryEntity.setNetAmount(10.0d);
        paymentEntryEntity.setPaymentDate(LocalDate.ofEpochDay(1L));
        paymentEntryEntity.setPaymentEntryDetails(new ArrayList<>());
        paymentEntryEntity.setRefernceDate(LocalDate.ofEpochDay(1L));
        paymentEntryEntity.setRefernceNumber("42");
        paymentEntryEntity.setRemarks("Remarks");
        paymentEntryEntity.setStatus("Status");

        ArrayList<PaymentEntryEntity> paymentEntryEntityList = new ArrayList<>();
        paymentEntryEntityList.add(paymentEntryEntity);
        when(paymentEntryRepository.findAll()).thenReturn(paymentEntryEntityList);

        // Act
        List<PaymentEntryEntity> actualAll = paymentEntryServiceImpl.getAll();

        // Assert
        assertSame(paymentEntryEntityList, actualAll);
        assertEquals(1, actualAll.size());
        verify(paymentEntryRepository).findAll();
    }

    /**
     * Method under test: {@link PaymentEntryServiceImpl#getAll()}
     */
    @Test
    void testGetAll3() {
        // Arrange
        when(paymentEntryRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> paymentEntryServiceImpl.getAll());
        verify(paymentEntryRepository).findAll();
    }

    /**
     * Method under test: {@link PaymentEntryServiceImpl#getById(Long)}
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

        PaymentEntryEntity paymentEntryEntity = new PaymentEntryEntity();
        paymentEntryEntity.setAccountEntity(accountCreationEntity);
        paymentEntryEntity.setId(123L);
        paymentEntryEntity.setNetAmount(10.0d);
        paymentEntryEntity.setPaymentDate(LocalDate.ofEpochDay(1L));
        paymentEntryEntity.setPaymentEntryDetails(new ArrayList<>());
        paymentEntryEntity.setRefernceDate(LocalDate.ofEpochDay(1L));
        paymentEntryEntity.setRefernceNumber("42");
        paymentEntryEntity.setRemarks("Remarks");
        paymentEntryEntity.setStatus("Status");
        Optional<PaymentEntryEntity> ofResult = Optional.of(paymentEntryEntity);
        when(paymentEntryRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(paymentEntryEntity, paymentEntryServiceImpl.getById(123L));
        verify(paymentEntryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PaymentEntryServiceImpl#getById(Long)}
     */
    @Test
    void testGetById2() throws RecordNotFoundException {
        // Arrange
        when(paymentEntryRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> paymentEntryServiceImpl.getById(123L));
        verify(paymentEntryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PaymentEntryServiceImpl#getById(Long)}
     */
    @Test
    void testGetById3() throws RecordNotFoundException {
        // Arrange
        when(paymentEntryRepository.findById((Long) any())).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> paymentEntryServiceImpl.getById(123L));
        verify(paymentEntryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PaymentEntryServiceImpl#createOrUpdate(PaymentEntryEntity)}
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

        PaymentEntryEntity paymentEntryEntity = new PaymentEntryEntity();
        paymentEntryEntity.setAccountEntity(accountCreationEntity);
        paymentEntryEntity.setId(123L);
        paymentEntryEntity.setNetAmount(10.0d);
        paymentEntryEntity.setPaymentDate(LocalDate.ofEpochDay(1L));
        paymentEntryEntity.setPaymentEntryDetails(new ArrayList<>());
        paymentEntryEntity.setRefernceDate(LocalDate.ofEpochDay(1L));
        paymentEntryEntity.setRefernceNumber("42");
        paymentEntryEntity.setRemarks("Remarks");
        paymentEntryEntity.setStatus("Status");
        when(paymentEntryRepository.save((PaymentEntryEntity) any())).thenReturn(paymentEntryEntity);

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

        PaymentEntryEntity paymentEntryEntity1 = new PaymentEntryEntity();
        paymentEntryEntity1.setAccountEntity(accountCreationEntity1);
        paymentEntryEntity1.setId(123L);
        paymentEntryEntity1.setNetAmount(10.0d);
        paymentEntryEntity1.setPaymentDate(LocalDate.ofEpochDay(1L));
        paymentEntryEntity1.setPaymentEntryDetails(new ArrayList<>());
        paymentEntryEntity1.setRefernceDate(LocalDate.ofEpochDay(1L));
        paymentEntryEntity1.setRefernceNumber("42");
        paymentEntryEntity1.setRemarks("Remarks");
        paymentEntryEntity1.setStatus("Status");

        // Act and Assert
        assertSame(paymentEntryEntity, paymentEntryServiceImpl.createOrUpdate(paymentEntryEntity1));
        verify(paymentEntryRepository).save((PaymentEntryEntity) any());
    }

    /**
     * Method under test: {@link PaymentEntryServiceImpl#createOrUpdate(PaymentEntryEntity)}
     */
    @Test
    void testCreateOrUpdate2() {
        // Arrange
        when(paymentEntryRepository.save((PaymentEntryEntity) any()))
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

        PaymentEntryEntity paymentEntryEntity = new PaymentEntryEntity();
        paymentEntryEntity.setAccountEntity(accountCreationEntity);
        paymentEntryEntity.setId(123L);
        paymentEntryEntity.setNetAmount(10.0d);
        paymentEntryEntity.setPaymentDate(LocalDate.ofEpochDay(1L));
        paymentEntryEntity.setPaymentEntryDetails(new ArrayList<>());
        paymentEntryEntity.setRefernceDate(LocalDate.ofEpochDay(1L));
        paymentEntryEntity.setRefernceNumber("42");
        paymentEntryEntity.setRemarks("Remarks");
        paymentEntryEntity.setStatus("Status");

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> paymentEntryServiceImpl.createOrUpdate(paymentEntryEntity));
        verify(paymentEntryRepository).save((PaymentEntryEntity) any());
    }

    /**
     * Method under test: {@link PaymentEntryServiceImpl#getAllPaymentEntryWithPagination(int, int)}
     */
    @Test
    void testGetAllPaymentEntryWithPagination() {
        // Arrange
        PageImpl<PaymentEntryEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(paymentEntryRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<PaymentEntryEntity> actualAllPaymentEntryWithPagination = paymentEntryServiceImpl
                .getAllPaymentEntryWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllPaymentEntryWithPagination);
        assertTrue(actualAllPaymentEntryWithPagination.toList().isEmpty());
        verify(paymentEntryRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link PaymentEntryServiceImpl#getAllPaymentEntryWithPagination(int, int)}
     */
    @Test
    void testGetAllPaymentEntryWithPagination2() {
        // Arrange
        when(paymentEntryRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> paymentEntryServiceImpl.getAllPaymentEntryWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link PaymentEntryServiceImpl#getAllPaymentEntryWithPagination(int, int)}
     */
    @Test
    void testGetAllPaymentEntryWithPagination3() {
        // Arrange
        when(paymentEntryRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> paymentEntryServiceImpl.getAllPaymentEntryWithPagination(2, 3));
        verify(paymentEntryRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link PaymentEntryServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<PaymentEntryEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(paymentEntryRepository.findAllByPaymentDateContains((String) any(), (Pageable) any())).thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<PaymentEntryEntity> actualSearch = paymentEntryServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(paymentEntryRepository).findAllByPaymentDateContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link PaymentEntryServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(paymentEntryRepository.findAllByPaymentDateContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> paymentEntryServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(paymentEntryRepository).findAllByPaymentDateContains((String) any(), (Pageable) any());
    }
}

