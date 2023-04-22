package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.OderGenerationEntryRepository;
import com.ets.SecurePharmacy.tenant.model.OrderGenerationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {OrderGeneratioEntryServiceImpl.class})
@ExtendWith(SpringExtension.class)
class OrderGeneratioEntryServiceImplTest {
    @MockBean
    private OderGenerationEntryRepository oderGenerationEntryRepository;

    @Autowired
    private OrderGeneratioEntryServiceImpl orderGeneratioEntryServiceImpl;

    /**
     * Method under test: {@link OrderGeneratioEntryServiceImpl#getAll()}
     */
    @Test
    void testGetAll() {
        // Arrange
        when(oderGenerationEntryRepository.findAll()).thenReturn(Collections.emptyList());

        // Act and Assert
        assertTrue(orderGeneratioEntryServiceImpl.getAll().isEmpty());
        verify(oderGenerationEntryRepository).findAll();
    }

    /**
     * Method under test: {@link OrderGeneratioEntryServiceImpl#getAll()}
     */
    @Test
    void testGetAll1() {
        // Arrange
        OrderGenerationEntity orderGenerationEntity = new OrderGenerationEntity();
        orderGenerationEntity.setId(123L);
        orderGenerationEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        orderGenerationEntity.setOrderDetails(new ArrayList<>());
        orderGenerationEntity.setOrderQty(1L);
        orderGenerationEntity.setStatus("Status");
        orderGenerationEntity.setTotalCount(3L);

        when(oderGenerationEntryRepository.findAll()).thenReturn(Collections.singletonList(orderGenerationEntity));

        // Act and Assert
        assertEquals(1,orderGeneratioEntryServiceImpl.getAll().size());
        verify(oderGenerationEntryRepository).findAll();
    }

    /**
     * Method under test: {@link OrderGeneratioEntryServiceImpl#getAll()}
     */
    @Test
    void testGetAll2() {
        // Arrange
        when(oderGenerationEntryRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> orderGeneratioEntryServiceImpl.getAll());
        verify(oderGenerationEntryRepository).findAll();
    }

    /**
     * Method under test: {@link OrderGeneratioEntryServiceImpl#getById(Long)}
     */
    @Test
    void testGetById() throws RecordNotFoundException {
        // Arrange
        OrderGenerationEntity orderGenerationEntity = new OrderGenerationEntity();
        orderGenerationEntity.setId(123L);
        orderGenerationEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        orderGenerationEntity.setOrderDetails(new ArrayList<>());
        orderGenerationEntity.setOrderQty(1L);
        orderGenerationEntity.setStatus("Status");
        orderGenerationEntity.setTotalCount(3L);
        Optional<OrderGenerationEntity> ofResult = Optional.of(orderGenerationEntity);
        when(oderGenerationEntryRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(orderGenerationEntity, orderGeneratioEntryServiceImpl.getById(123L));
        verify(oderGenerationEntryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link OrderGeneratioEntryServiceImpl#getById(Long)}
     */
    @Test
    void testGetById2() throws RecordNotFoundException {
        // Arrange
        when(oderGenerationEntryRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> orderGeneratioEntryServiceImpl.getById(123L));
        verify(oderGenerationEntryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link OrderGeneratioEntryServiceImpl#getById(Long)}
     */
    @Test
    void testGetById3() throws RecordNotFoundException {
        // Arrange
        when(oderGenerationEntryRepository.findById((Long) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> orderGeneratioEntryServiceImpl.getById(123L));
        verify(oderGenerationEntryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link OrderGeneratioEntryServiceImpl#createOrUpdate(OrderGenerationEntity)}
     */
    @Test
    void testCreateOrUpdate() {
        // Arrange
        OrderGenerationEntity orderGenerationEntity = new OrderGenerationEntity();
        orderGenerationEntity.setId(123L);
        orderGenerationEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        orderGenerationEntity.setOrderDetails(new ArrayList<>());
        orderGenerationEntity.setOrderQty(1L);
        orderGenerationEntity.setStatus("Status");
        orderGenerationEntity.setTotalCount(3L);
        when(oderGenerationEntryRepository.save((OrderGenerationEntity) any())).thenReturn(orderGenerationEntity);

        OrderGenerationEntity orderGenerationEntity1 = new OrderGenerationEntity();
        orderGenerationEntity1.setId(123L);
        orderGenerationEntity1.setOrderDate(LocalDate.ofEpochDay(1L));
        orderGenerationEntity1.setOrderDetails(new ArrayList<>());
        orderGenerationEntity1.setOrderQty(1L);
        orderGenerationEntity1.setStatus("Status");
        orderGenerationEntity1.setTotalCount(3L);

        // Act and Assert
        assertSame(orderGenerationEntity, orderGeneratioEntryServiceImpl.createOrUpdate(orderGenerationEntity1));
        verify(oderGenerationEntryRepository).save((OrderGenerationEntity) any());
    }

    /**
     * Method under test: {@link OrderGeneratioEntryServiceImpl#createOrUpdate(OrderGenerationEntity)}
     */
    @Test
    void testCreateOrUpdate2() {
        // Arrange
        when(oderGenerationEntryRepository.save((OrderGenerationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        OrderGenerationEntity orderGenerationEntity = new OrderGenerationEntity();
        orderGenerationEntity.setId(123L);
        orderGenerationEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        orderGenerationEntity.setOrderDetails(new ArrayList<>());
        orderGenerationEntity.setOrderQty(1L);
        orderGenerationEntity.setStatus("Status");
        orderGenerationEntity.setTotalCount(3L);

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> orderGeneratioEntryServiceImpl.createOrUpdate(orderGenerationEntity));
        verify(oderGenerationEntryRepository).save((OrderGenerationEntity) any());
    }
}

