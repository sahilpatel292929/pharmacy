package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.PaymentModeRepository;
import com.ets.SecurePharmacy.tenant.model.PaymentModeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {PaymentModeServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PaymentModeServiceImplTest {
    @MockBean
    private PaymentModeRepository paymentModeRepository;

    @Autowired
    private PaymentModeServiceImpl paymentModeServiceImpl;

    /**
     * Method under test: {@link PaymentModeServiceImpl#getAllPaymentModes()}
     */
    @Test
    void testGetAllPaymentModes() {
        // Arrange
        when(paymentModeRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(paymentModeServiceImpl.getAllPaymentModes().isEmpty());
        verify(paymentModeRepository).findAll();
    }

    /**
     * Method under test: {@link PaymentModeServiceImpl#getAllPaymentModes()}
     */
    @Test
    void testGetAllPaymentModes2() {
        // Arrange
        PaymentModeEntity paymentModeEntity = new PaymentModeEntity();
        paymentModeEntity.setId(123L);
        paymentModeEntity.setPaymentModeName("Payment Mode Name");
        paymentModeEntity.setStatus("Status");

        ArrayList<PaymentModeEntity> paymentModeEntityList = new ArrayList<>();
        paymentModeEntityList.add(paymentModeEntity);
        when(paymentModeRepository.findAll()).thenReturn(paymentModeEntityList);

        // Act
        List<PaymentModeEntity> actualAllPaymentModes = paymentModeServiceImpl.getAllPaymentModes();

        // Assert
        assertSame(paymentModeEntityList, actualAllPaymentModes);
        assertEquals(1, actualAllPaymentModes.size());
        verify(paymentModeRepository).findAll();
    }

    /**
     * Method under test: {@link PaymentModeServiceImpl#getAllPaymentModes()}
     */
    @Test
    void testGetAllPaymentModes3() {
        // Arrange
        when(paymentModeRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> paymentModeServiceImpl.getAllPaymentModes());
        verify(paymentModeRepository).findAll();
    }

    /**
     * Method under test: {@link PaymentModeServiceImpl#getAllPaymentModeWithPagination(int, int)}
     */
    @Test
    void testGetAllPaymentModeWithPagination() {
        // Arrange
        PageImpl<PaymentModeEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(paymentModeRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<PaymentModeEntity> actualAllPaymentModeWithPagination = paymentModeServiceImpl
                .getAllPaymentModeWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllPaymentModeWithPagination);
        assertTrue(actualAllPaymentModeWithPagination.toList().isEmpty());
        verify(paymentModeRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link PaymentModeServiceImpl#getAllPaymentModeWithPagination(int, int)}
     */
    @Test
    void testGetAllPaymentModeWithPagination2() {
        // Arrange
        when(paymentModeRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> paymentModeServiceImpl.getAllPaymentModeWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link PaymentModeServiceImpl#getAllPaymentModeWithPagination(int, int)}
     */
    @Test
    void testGetAllPaymentModeWithPagination3() {
        // Arrange
        when(paymentModeRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> paymentModeServiceImpl.getAllPaymentModeWithPagination(2, 3));
        verify(paymentModeRepository).findAll((Pageable) any());
    }
}

