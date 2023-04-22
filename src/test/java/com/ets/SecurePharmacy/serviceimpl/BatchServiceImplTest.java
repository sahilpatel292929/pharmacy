package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.BatchRepository;
import com.ets.SecurePharmacy.tenant.model.BatchEntity;
import com.ets.SecurePharmacy.tenant.model.CompostionCreationEntity;
import com.ets.SecurePharmacy.tenant.model.DiscountSlabCreationEntity;
import com.ets.SecurePharmacy.tenant.model.GroupCreationEntity;
import com.ets.SecurePharmacy.tenant.model.HsnSacCreationEntity;
import com.ets.SecurePharmacy.tenant.model.ItemCreationEntity;
import com.ets.SecurePharmacy.tenant.model.ManufacturerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.SchedulerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.StoreTypeCreationEntity;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {BatchServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BatchServiceImplTest {
    @MockBean
    private BatchRepository batchRepository;

    @Autowired
    private BatchServiceImpl batchServiceImpl;

    /**
     * Method under test: {@link BatchServiceImpl#getAllAccounts()}
     */
    @Test
    void testGetAllAccounts() {
        // Arrange
        when(batchRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(batchServiceImpl.getAllAccounts().isEmpty());
        verify(batchRepository).findAll();
    }

    /**
     * Method under test: {@link BatchServiceImpl#getAllAccounts()}
     */
    @Test
    void testGetAllAccounts2() {
        // Arrange
        BatchEntity batchEntity = getBatchEntity();

        ArrayList<BatchEntity> batchEntityList = new ArrayList<>();
        batchEntityList.add(batchEntity);
        when(batchRepository.findAll()).thenReturn(batchEntityList);

        // Act
        List<BatchEntity> actualAllAccounts = batchServiceImpl.getAllAccounts();

        // Assert
        assertSame(batchEntityList, actualAllAccounts);
        assertEquals(1, actualAllAccounts.size());
        verify(batchRepository).findAll();
    }

    private BatchEntity getBatchEntity() {
        CompostionCreationEntity compostionCreationEntity = new CompostionCreationEntity();
        compostionCreationEntity.setCompName("Comp Name");
        compostionCreationEntity.setId(123L);
        compostionCreationEntity.setStatus("Status");

        DiscountSlabCreationEntity discountSlabCreationEntity = new DiscountSlabCreationEntity();
        discountSlabCreationEntity.setDiscount("3");
        discountSlabCreationEntity.setDiscountSlabName("3");
        discountSlabCreationEntity.setFrom_amt(1);
        discountSlabCreationEntity.setId(123L);
        discountSlabCreationEntity.setStatus("Status");
        discountSlabCreationEntity.setTo_amt(1);

        GroupCreationEntity groupCreationEntity = new GroupCreationEntity();
        groupCreationEntity.setGroupName("Group Name");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("Status");

        HsnSacCreationEntity hsnSacCreationEntity = new HsnSacCreationEntity();
        hsnSacCreationEntity.setDescirption("Descirption");
        hsnSacCreationEntity.setHsnName("Hsn Name");
        hsnSacCreationEntity.setId(123L);
        hsnSacCreationEntity.setStatus("Status");

        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity.setStatus("Status");

        SchedulerCreationEntity schedulerCreationEntity = new SchedulerCreationEntity();
        schedulerCreationEntity.setId(123L);
        schedulerCreationEntity.setSchedulerName("Scheduler Name");
        schedulerCreationEntity.setStatus("Status");
        schedulerCreationEntity.setWaringMsg("Waring Msg");
        schedulerCreationEntity.setWarning("Warning");

        StoreTypeCreationEntity storeTypeCreationEntity = new StoreTypeCreationEntity();
        storeTypeCreationEntity.setId(123L);
        storeTypeCreationEntity.setStatus("Status");
        storeTypeCreationEntity.setStoreTypeName("Store Type Name");

        ItemCreationEntity itemCreationEntity = new ItemCreationEntity();
        itemCreationEntity.setCompositionEntity(compostionCreationEntity);
        itemCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        itemCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        itemCreationEntity.setDiscSalbEntity(discountSlabCreationEntity);
        itemCreationEntity.setGroupEntity(groupCreationEntity);
        itemCreationEntity.setGst(1);
        itemCreationEntity.setHsnsac(hsnSacCreationEntity);
        itemCreationEntity.setId(123L);
        itemCreationEntity.setItemName("Item Name");
        itemCreationEntity.setManufactureEntity(manufacturerCreationEntity);
        itemCreationEntity.setMax_amt(10.0d);
        itemCreationEntity.setMax_qty(10.0d);
        itemCreationEntity.setMin_amt(10.0d);
        itemCreationEntity.setMin_qty(10.0d);
        itemCreationEntity.setPackName("Pack Name");
        itemCreationEntity.setQty_per_pack("Qty per pack");
        itemCreationEntity.setRateA("Rate A");
        itemCreationEntity.setRateB("Rate B");
        itemCreationEntity.setRateC("Rate C");
        itemCreationEntity.setRateD("Rate D");
        itemCreationEntity.setScheduleEntity(schedulerCreationEntity);
        itemCreationEntity.setStatus("Status");
        itemCreationEntity.setStoreTypeEntity(storeTypeCreationEntity);
        itemCreationEntity.setUpdated_by(1);
        itemCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        BatchEntity batchEntity = new BatchEntity();
        batchEntity.setAvailableStock(10.0d);
        batchEntity.setBatchDetails(new ArrayList<>());
        batchEntity.setBatchNo("Batch No");
        batchEntity.setExpiry(LocalDate.ofEpochDay(1L));
        batchEntity.setId(123L);
        batchEntity.setItems(itemCreationEntity);
        batchEntity.setMrp(1.900);
        batchEntity.setQtyPerPack(1L);
        return batchEntity;
    }

    /**
     * Method under test: {@link BatchServiceImpl#getAllAccounts()}
     */
    @Test
    void testGetAllAccounts3() {
        // Arrange
        when(batchRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> batchServiceImpl.getAllAccounts());
        verify(batchRepository).findAll();
    }

    /**
     * Method under test: {@link BatchServiceImpl#getAccountsById(Long)}
     */
    @Test
    void testGetAccountsById() throws RecordNotFoundException {
        // Arrange
        BatchEntity batchEntity = getBatchEntity();
        Optional<BatchEntity> ofResult = Optional.of(batchEntity);
        when(batchRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(batchEntity, batchServiceImpl.getAccountsById(123L));
        verify(batchRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link BatchServiceImpl#getAccountsById(Long)}
     */
    @Test
    void testGetAccountsById1()  {

        when(batchRepository.findById((Long) any())).thenThrow(new UnableToProcessException("Unable to process at this moment, Try Again! After some time"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> batchServiceImpl.getAccountsById(123L));
        verify(batchRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link BatchServiceImpl#getAccountsById(Long)}
     */
    @Test
    void testGetAccountsById2()  {

        when(batchRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> batchServiceImpl.getAccountsById(123L));
        verify(batchRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link BatchServiceImpl#createOrUpdateAccounts(BatchEntity)}
     */
    @Test
    void testCreateOrUpdateAccounts() {
        // Arrange
        BatchEntity batchEntity = getBatchEntity();
        when(batchRepository.save((BatchEntity) any())).thenReturn(batchEntity);

        BatchEntity batchEntity1 = getBatchEntity();

        // Act and Assert
        assertSame(batchEntity, batchServiceImpl.createOrUpdateAccounts(batchEntity1));
        verify(batchRepository).save((BatchEntity) any());
    }

    /**
     * Method under test: {@link BatchServiceImpl#createOrUpdateAccounts(BatchEntity)}
     */
    @Test
    void testCreateOrUpdateAccounts1() {
        // Arrange
        when(batchRepository.save((BatchEntity) any())).thenThrow(new UnableToProcessException("Unable to process at this moment, Try Again! After some time"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,() ->batchServiceImpl.createOrUpdateAccounts((BatchEntity) any()));
        verify(batchRepository).save((BatchEntity) any());
    }

    /**
     * Method under test: {@link BatchServiceImpl#deleteAccountsById(Long)}
     */
    @Test
    void testDeleteAccountsById() throws RecordNotFoundException {
        // Arrange
        BatchEntity batchEntity = getBatchEntity();
        Optional<BatchEntity> ofResult = Optional.of(batchEntity);
        doNothing().when(batchRepository).deleteById((Long) any());
        when(batchRepository.findById((Long) any())).thenReturn(ofResult);

        // Act
        batchServiceImpl.deleteAccountsById(123L);

        // Assert that nothing has changed
        verify(batchRepository).findById((Long) any());
        verify(batchRepository).deleteById((Long) any());
        assertTrue(batchServiceImpl.getAllAccounts().isEmpty());
    }

    /**
     * Method under test: {@link BatchServiceImpl#deleteAccountsById(Long)}
     */
    @Test
    void testDeleteAccountsById2() throws RecordNotFoundException {
        // Arrange
        when(batchRepository.findById((Long) any())).thenReturn(Optional.empty());
        doNothing().when(batchRepository).deleteById((Long) any());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> batchServiceImpl.deleteAccountsById(123L));
        verify(batchRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link BatchServiceImpl#deleteAccountsById(Long)}
     */
    @Test
    void testDeleteAccountsById3() throws RecordNotFoundException {
        // Arrange
        when(batchRepository.findById((Long) any())).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> batchServiceImpl.deleteAccountsById(123L));
        verify(batchRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link BatchServiceImpl#getAllBatchWithPagination(int, int)}
     */
    @Test
    void testGetAllBatchWithPagination() {
        // Arrange
        PageImpl<BatchEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(batchRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<BatchEntity> actualAllBatchWithPagination = batchServiceImpl.getAllBatchWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllBatchWithPagination);
        assertTrue(actualAllBatchWithPagination.toList().isEmpty());
        verify(batchRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link BatchServiceImpl#getAllBatchWithPagination(int, int)}
     */
    @Test
    void testGetAllBatchWithPagination2() {
        // Arrange
        when(batchRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> batchServiceImpl.getAllBatchWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link BatchServiceImpl#getAllBatchWithPagination(int, int)}
     */
    @Test
    void testGetAllBatchWithPagination3() {
        // Arrange
        when(batchRepository.findAll((Pageable) any())).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> batchServiceImpl.getAllBatchWithPagination(2, 3));
        verify(batchRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link BatchServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<BatchEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(batchRepository.findAllByIdContains((String) any(), (Pageable) any())).thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<BatchEntity> actualSearch = batchServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(batchRepository).findAllByIdContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link BatchServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(batchRepository.findAllByIdContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> batchServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(batchRepository).findAllByIdContains((String) any(), (Pageable) any());
    }

    /**
     * Method under test: {@link BatchServiceImpl#getMRP(String, Long)}
     */
    @Test
    void testGetMRP() {
        // Arrange
        BatchEntity batchEntity = getBatchEntity();
        when(batchRepository.getMRPand((String) any(), (Long) any())).thenReturn(batchEntity);

        // Act and Assert
        assertSame(batchEntity, batchServiceImpl.getMRP("Batch no", 42L));
        verify(batchRepository).getMRPand((String) any(), (Long) any());
    }

    /**
     * Method under test: {@link BatchServiceImpl#getMRP(String, Long)}
     */
    @Test
    void testGetMRP1() {
        // Arrange
        when(batchRepository.getMRPand((String) any(), (Long) any())).thenThrow(new UnableToProcessException("Unable to process at this moment, Try Again! After some time"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,()->batchServiceImpl.getMRP((String) any(),(Long) any()));
        verify(batchRepository).getMRPand((String) any(), (Long) any());
    }

    /**
     * Method under test: {@link BatchServiceImpl#getItemBatchDetails(Long)}
     */
    @Test
    void testGetItemBatchDetails() {
        // Arrange
        when(batchRepository.getListBatches((Long) any())).thenReturn(Collections.emptyList());

        // Act and Assert
        assertTrue(batchServiceImpl.getItemBatchDetails((Long) any()).isEmpty());
        verify(batchRepository).getListBatches((Long) any());
    }

    /**
     * Method under test: {@link BatchServiceImpl#getItemBatchDetails(Long)}
     */
    @Test
    void testGetItemBatchDetails1() {
        // Arrange
        when(batchRepository.getListBatches((Long) any())).thenThrow(new UnableToProcessException("Unable to process at this moment, Try Again! After some time"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,()->batchServiceImpl.getItemBatchDetails((Long) any()));
        verify(batchRepository).getListBatches((Long) any());
    }

    /**
     * Method under test: {@link BatchServiceImpl#getBatchDetails(String)}
     */
    @Test
    void testGetBatchDetails() {
        // Arrange
        when(batchRepository.getBatchDetails((String) any())).thenThrow(new UnableToProcessException("Unable to process at this moment, Try Again! After some time"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,()->batchServiceImpl.getBatchDetails((String) any()));
        verify(batchRepository).getBatchDetails((String) any());
    }

    /**
     * Method under test: {@link BatchServiceImpl#getBatchDetails(String)}
     */
    @Test
    void testGetBatchDetails1() throws RecordNotFoundException {
        // Arrange
        BatchEntity batchEntity = getBatchEntity();
        when(batchRepository.getBatchDetails((String) any())).thenReturn(batchEntity);

        // Act and Assert
        assertSame(batchEntity,batchServiceImpl.getBatchDetails((String) any()));
        verify(batchRepository).getBatchDetails((String) any());
    }
}

