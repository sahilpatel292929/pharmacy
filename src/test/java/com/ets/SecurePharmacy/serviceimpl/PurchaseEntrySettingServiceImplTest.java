package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.PurchaseEntrySettingRepo;
import com.ets.SecurePharmacy.tenant.model.PurchaseEntrySettingEntity;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {PurchaseEntrySettingServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PurchaseEntrySettingServiceImplTest {
    @MockBean
    private PurchaseEntrySettingRepo purchaseEntrySettingRepo;

    @Autowired
    private PurchaseEntrySettingServiceImpl purchaseEntrySettingServiceImpl;

    /**
     * Method under test: {@link PurchaseEntrySettingServiceImpl#getAllPurchaseSetting()}
     */
    @Test
    void testGetAllPurchaseSetting() {
        // Arrange
        when(purchaseEntrySettingRepo.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(purchaseEntrySettingServiceImpl.getAllPurchaseSetting().isEmpty());
        verify(purchaseEntrySettingRepo).findAll();
    }

    /**
     * Method under test: {@link PurchaseEntrySettingServiceImpl#getAllPurchaseSetting()}
     */
    @Test
    void testGetAllPurchaseSetting2() {
        // Arrange
        PurchaseEntrySettingEntity purchaseEntrySettingEntity = new PurchaseEntrySettingEntity();
        purchaseEntrySettingEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        purchaseEntrySettingEntity.setId(123L);
        purchaseEntrySettingEntity.setSettingName("Setting Name");
        purchaseEntrySettingEntity.setStatus("Status");
        purchaseEntrySettingEntity.setUpdated_by(1);
        purchaseEntrySettingEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        ArrayList<PurchaseEntrySettingEntity> purchaseEntrySettingEntityList = new ArrayList<>();
        purchaseEntrySettingEntityList.add(purchaseEntrySettingEntity);
        when(purchaseEntrySettingRepo.findAll()).thenReturn(purchaseEntrySettingEntityList);

        // Act
        List<PurchaseEntrySettingEntity> actualAllPurchaseSetting = purchaseEntrySettingServiceImpl
                .getAllPurchaseSetting();

        // Assert
        assertSame(purchaseEntrySettingEntityList, actualAllPurchaseSetting);
        assertEquals(1, actualAllPurchaseSetting.size());
        verify(purchaseEntrySettingRepo).findAll();
    }

    /**
     * Method under test: {@link PurchaseEntrySettingServiceImpl#getAllPurchaseSetting()}
     */
    @Test
    void testGetAllPurchaseSetting3() {
        // Arrange
        when(purchaseEntrySettingRepo.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> purchaseEntrySettingServiceImpl.getAllPurchaseSetting());
        verify(purchaseEntrySettingRepo).findAll();
    }

    /**
     * Method under test: {@link PurchaseEntrySettingServiceImpl#createOrUpdateSetttings(PurchaseEntrySettingEntity)}
     */
    @Test
    void testCreateOrUpdateSetttings() {
        // Arrange
        PurchaseEntrySettingEntity purchaseEntrySettingEntity = new PurchaseEntrySettingEntity();
        purchaseEntrySettingEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        purchaseEntrySettingEntity.setId(123L);
        purchaseEntrySettingEntity.setSettingName("Setting Name");
        purchaseEntrySettingEntity.setStatus("Status");
        purchaseEntrySettingEntity.setUpdated_by(1);
        purchaseEntrySettingEntity.setUpdated_date(LocalDate.ofEpochDay(1L));
        when(purchaseEntrySettingRepo.save((PurchaseEntrySettingEntity) any())).thenReturn(purchaseEntrySettingEntity);

        PurchaseEntrySettingEntity purchaseEntrySettingEntity1 = new PurchaseEntrySettingEntity();
        purchaseEntrySettingEntity1.setCreated_date(LocalDate.ofEpochDay(1L));
        purchaseEntrySettingEntity1.setId(123L);
        purchaseEntrySettingEntity1.setSettingName("Setting Name");
        purchaseEntrySettingEntity1.setStatus("Status");
        purchaseEntrySettingEntity1.setUpdated_by(1);
        purchaseEntrySettingEntity1.setUpdated_date(LocalDate.ofEpochDay(1L));

        // Act and Assert
        assertSame(purchaseEntrySettingEntity,
                purchaseEntrySettingServiceImpl.createOrUpdateSetttings(purchaseEntrySettingEntity1));
        verify(purchaseEntrySettingRepo).save((PurchaseEntrySettingEntity) any());
    }

    /**
     * Method under test: {@link PurchaseEntrySettingServiceImpl#createOrUpdateSetttings(PurchaseEntrySettingEntity)}
     */
    @Test
    void testCreateOrUpdateSetttings2() {
        // Arrange
        when(purchaseEntrySettingRepo.save((PurchaseEntrySettingEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        PurchaseEntrySettingEntity purchaseEntrySettingEntity = new PurchaseEntrySettingEntity();
        purchaseEntrySettingEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        purchaseEntrySettingEntity.setId(123L);
        purchaseEntrySettingEntity.setSettingName("Setting Name");
        purchaseEntrySettingEntity.setStatus("Status");
        purchaseEntrySettingEntity.setUpdated_by(1);
        purchaseEntrySettingEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> purchaseEntrySettingServiceImpl.createOrUpdateSetttings(purchaseEntrySettingEntity));
        verify(purchaseEntrySettingRepo).save((PurchaseEntrySettingEntity) any());
    }

    /**
     * Method under test: {@link PurchaseEntrySettingServiceImpl#getAllPurchaseEntrySettingWithPagination(int, int)}
     */
    @Test
    void testGetAllPurchaseEntrySettingWithPagination() {
        // Arrange
        PageImpl<PurchaseEntrySettingEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(purchaseEntrySettingRepo.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<PurchaseEntrySettingEntity> actualAllPurchaseEntrySettingWithPagination = purchaseEntrySettingServiceImpl
                .getAllPurchaseEntrySettingWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllPurchaseEntrySettingWithPagination);
        assertTrue(actualAllPurchaseEntrySettingWithPagination.toList().isEmpty());
        verify(purchaseEntrySettingRepo).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link PurchaseEntrySettingServiceImpl#getAllPurchaseEntrySettingWithPagination(int, int)}
     */
    @Test
    void testGetAllPurchaseEntrySettingWithPagination3() {
        // Arrange
        when(purchaseEntrySettingRepo.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> purchaseEntrySettingServiceImpl.getAllPurchaseEntrySettingWithPagination(2, 3));
        verify(purchaseEntrySettingRepo).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link PurchaseEntrySettingServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<PurchaseEntrySettingEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(purchaseEntrySettingRepo.findAllBySettingNameContains((String) any(), (Pageable) any()))
                .thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<PurchaseEntrySettingEntity> actualSearch = purchaseEntrySettingServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(purchaseEntrySettingRepo).findAllBySettingNameContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link PurchaseEntrySettingServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(purchaseEntrySettingRepo.findAllBySettingNameContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> purchaseEntrySettingServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(purchaseEntrySettingRepo).findAllBySettingNameContains((String) any(), (Pageable) any());
    }
}

