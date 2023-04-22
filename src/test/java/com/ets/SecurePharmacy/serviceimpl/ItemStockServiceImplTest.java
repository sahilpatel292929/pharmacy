package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.ItemStockRepository;
import com.ets.SecurePharmacy.tenant.model.CompostionCreationEntity;
import com.ets.SecurePharmacy.tenant.model.DiscountSlabCreationEntity;
import com.ets.SecurePharmacy.tenant.model.GroupCreationEntity;
import com.ets.SecurePharmacy.tenant.model.HsnSacCreationEntity;
import com.ets.SecurePharmacy.tenant.model.ItemCreationEntity;
import com.ets.SecurePharmacy.tenant.model.ItemStockEntity;
import com.ets.SecurePharmacy.tenant.model.ManufacturerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.SchedulerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.StoreTypeCreationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ItemStockServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ItemStockServiceImplTest {
    @MockBean
    private ItemStockRepository itemStockRepository;

    @Autowired
    private ItemStockServiceImpl itemStockServiceImpl;

    /**
     * Method under test: {@link ItemStockServiceImpl#getItemStockDetails(Long)}
     */
    @Test
    void testGetItemStockDetails() {
        // Arrange
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

        ItemStockEntity itemStockEntity = new ItemStockEntity();
        itemStockEntity.setAddStock(1L);
        itemStockEntity.setAvailableStock(1L);
        itemStockEntity.setCreatedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        itemStockEntity.setId(123L);
        itemStockEntity.setItemEntity(itemCreationEntity);
        itemStockEntity.setLastUpdateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        itemStockEntity.setLessStock(1.00);
        when(itemStockRepository.findByItemEntityId((Long) any())).thenReturn(itemStockEntity);

        // Act and Assert
        assertSame(itemStockEntity, itemStockServiceImpl.getItemStockDetails(123L));
        verify(itemStockRepository).findByItemEntityId((Long) any());
    }


    /**
     * Method under test: {@link ItemStockServiceImpl#getItemStockDetails(Long)}
     */
    @Test
    void testGetItemStockDetails1() {
        // Arrange
        when(itemStockRepository.findByItemEntityId((Long) any())).thenThrow(new UnableToProcessException("Unable to process at this moment, Try Again! After some time"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,()->itemStockServiceImpl.getItemStockDetails((Long) any()));
        verify(itemStockRepository).findByItemEntityId((Long) any());
    }
}

