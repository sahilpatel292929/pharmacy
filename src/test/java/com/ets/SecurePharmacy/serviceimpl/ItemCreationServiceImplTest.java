package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.ItemCreationRepository;
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
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ItemCreationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ItemCreationServiceImplTest {
    @MockBean
    private ItemCreationRepository itemCreationRepository;

    @Autowired
    private ItemCreationServiceImpl itemCreationServiceImpl;

    /**
     * Method under test: {@link ItemCreationServiceImpl#getAllItemList()}
     */
    @Test
    void testGetAllItemList() {
        // Arrange
        when(itemCreationRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(itemCreationServiceImpl.getAllItemList().isEmpty());
        verify(itemCreationRepository).findAll();
    }

    /**
     * Method under test: {@link ItemCreationServiceImpl#getAllItemList()}
     */
    @Test
    void testGetAllItemList2() {
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

        ArrayList<ItemCreationEntity> itemCreationEntityList = new ArrayList<>();
        itemCreationEntityList.add(itemCreationEntity);
        when(itemCreationRepository.findAll()).thenReturn(itemCreationEntityList);

        // Act
        List<ItemCreationEntity> actualAllItemList = itemCreationServiceImpl.getAllItemList();

        // Assert
        assertSame(itemCreationEntityList, actualAllItemList);
        assertEquals(1, actualAllItemList.size());
        verify(itemCreationRepository).findAll();
    }

    /**
     * Method under test: {@link ItemCreationServiceImpl#getAllItemList()}
     */
    @Test
    void testGetAllItemList3() {
        // Arrange
        when(itemCreationRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> itemCreationServiceImpl.getAllItemList());
        verify(itemCreationRepository).findAll();
    }

    /**
     * Method under test: {@link ItemCreationServiceImpl#getAllItemSortedList(String)}
     */
    @Test
    void testGetAllItemSortedList() {
        // Arrange
        when(itemCreationRepository.findAll((Sort) any())).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(itemCreationServiceImpl.getAllItemSortedList("Field").isEmpty());
        verify(itemCreationRepository).findAll((Sort) any());
    }

    /**
     * Method under test: {@link ItemCreationServiceImpl#getAllItemSortedList(String)}
     */
    @Test
    void testGetAllItemSortedList2() {
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

        ArrayList<ItemCreationEntity> itemCreationEntityList = new ArrayList<>();
        itemCreationEntityList.add(itemCreationEntity);
        when(itemCreationRepository.findAll((Sort) any())).thenReturn(itemCreationEntityList);

        // Act
        List<ItemCreationEntity> actualAllItemSortedList = itemCreationServiceImpl.getAllItemSortedList("Field");

        // Assert
        assertSame(itemCreationEntityList, actualAllItemSortedList);
        assertEquals(1, actualAllItemSortedList.size());
        verify(itemCreationRepository).findAll((Sort) any());
    }

    /**
     * Method under test: {@link ItemCreationServiceImpl#getAllItemSortedList(String)}
     */
    @Test
    void testGetAllItemSortedList3() {
        // Arrange
        when(itemCreationRepository.findAll((Sort) any())).thenReturn(new ArrayList<>());

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> itemCreationServiceImpl.getAllItemSortedList(""));
    }

    /**
     * Method under test: {@link ItemCreationServiceImpl#getAllItemSortedList(String)}
     */
    @Test
    void testGetAllItemSortedList4() {
        // Arrange
        when(itemCreationRepository.findAll((Sort) any())).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> itemCreationServiceImpl.getAllItemSortedList("Field"));
        verify(itemCreationRepository).findAll((Sort) any());
    }

    /**
     * Method under test: {@link ItemCreationServiceImpl#getAllItemWithPagination(int, int)}
     */
    @Test
    void testGetAllItemWithPagination() {
        // Arrange
        PageImpl<ItemCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(itemCreationRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<ItemCreationEntity> actualAllItemWithPagination = itemCreationServiceImpl.getAllItemWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllItemWithPagination);
        assertTrue(actualAllItemWithPagination.toList().isEmpty());
        verify(itemCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link ItemCreationServiceImpl#getAllItemWithPagination(int, int)}
     */
    @Test
    void testGetAllItemWithPagination2() {
        // Arrange
        when(itemCreationRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> itemCreationServiceImpl.getAllItemWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link ItemCreationServiceImpl#getAllItemWithPagination(int, int)}
     */
    @Test
    void testGetAllItemWithPagination3() {
        // Arrange
        when(itemCreationRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> itemCreationServiceImpl.getAllItemWithPagination(2, 3));
        verify(itemCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link ItemCreationServiceImpl#getItemById(Long)}
     */
    @Test
    void testGetItemById() throws RecordNotFoundException {
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
        Optional<ItemCreationEntity> ofResult = Optional.of(itemCreationEntity);
        when(itemCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(itemCreationEntity, itemCreationServiceImpl.getItemById(123L));
        verify(itemCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ItemCreationServiceImpl#getItemName(String)}
     */
    @Test
    void testGetItemName() {
        // Arrange
        when(itemCreationRepository.findByItemName((String) any())).thenReturn(new ArrayList<>());

        // Act and Assert
        assertFalse(itemCreationServiceImpl.getItemName("Iname"));
        verify(itemCreationRepository).findByItemName((String) any());
    }

    /**
     * Method under test: {@link ItemCreationServiceImpl#getItemName(String)}
     */
    @Test
    void testGetItemName2() {
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

        ArrayList<ItemCreationEntity> itemCreationEntityList = new ArrayList<>();
        itemCreationEntityList.add(itemCreationEntity);
        when(itemCreationRepository.findByItemName((String) any())).thenReturn(itemCreationEntityList);

        // Act and Assert
        assertTrue(itemCreationServiceImpl.getItemName("Iname"));
        verify(itemCreationRepository).findByItemName((String) any());
    }

    /**
     * Method under test: {@link ItemCreationServiceImpl#getItemName(String)}
     */
    @Test
    void testGetItemName3() {
        // Arrange
        when(itemCreationRepository.findByItemName((String) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> itemCreationServiceImpl.getItemName("Iname"));
        verify(itemCreationRepository).findByItemName((String) any());
    }

    /**
     * Method under test: {@link ItemCreationServiceImpl#createOrUpdateItem(ItemCreationEntity)}
     */
    @Test
    void testCreateOrUpdateItem() {
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
        when(itemCreationRepository.save((ItemCreationEntity) any())).thenReturn(itemCreationEntity);
        when(itemCreationRepository.findByItemName((String) any())).thenReturn(new ArrayList<>());

        CompostionCreationEntity compostionCreationEntity1 = new CompostionCreationEntity();
        compostionCreationEntity1.setCompName("Comp Name");
        compostionCreationEntity1.setId(123L);
        compostionCreationEntity1.setStatus("Status");

        DiscountSlabCreationEntity discountSlabCreationEntity1 = new DiscountSlabCreationEntity();
        discountSlabCreationEntity1.setDiscount("3");
        discountSlabCreationEntity1.setDiscountSlabName("3");
        discountSlabCreationEntity1.setFrom_amt(1);
        discountSlabCreationEntity1.setId(123L);
        discountSlabCreationEntity1.setStatus("Status");
        discountSlabCreationEntity1.setTo_amt(1);

        GroupCreationEntity groupCreationEntity1 = new GroupCreationEntity();
        groupCreationEntity1.setGroupName("Group Name");
        groupCreationEntity1.setId(123L);
        groupCreationEntity1.setStatus("Status");

        HsnSacCreationEntity hsnSacCreationEntity1 = new HsnSacCreationEntity();
        hsnSacCreationEntity1.setDescirption("Descirption");
        hsnSacCreationEntity1.setHsnName("Hsn Name");
        hsnSacCreationEntity1.setId(123L);
        hsnSacCreationEntity1.setStatus("Status");

        ManufacturerCreationEntity manufacturerCreationEntity1 = new ManufacturerCreationEntity();
        manufacturerCreationEntity1.setId(123L);
        manufacturerCreationEntity1.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity1.setStatus("Status");

        SchedulerCreationEntity schedulerCreationEntity1 = new SchedulerCreationEntity();
        schedulerCreationEntity1.setId(123L);
        schedulerCreationEntity1.setSchedulerName("Scheduler Name");
        schedulerCreationEntity1.setStatus("Status");
        schedulerCreationEntity1.setWaringMsg("Waring Msg");
        schedulerCreationEntity1.setWarning("Warning");

        StoreTypeCreationEntity storeTypeCreationEntity1 = new StoreTypeCreationEntity();
        storeTypeCreationEntity1.setId(123L);
        storeTypeCreationEntity1.setStatus("Status");
        storeTypeCreationEntity1.setStoreTypeName("Store Type Name");

        ItemCreationEntity itemCreationEntity1 = new ItemCreationEntity();
        itemCreationEntity1.setCompositionEntity(compostionCreationEntity1);
        itemCreationEntity1.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        itemCreationEntity1.setCreated_date(LocalDate.ofEpochDay(1L));
        itemCreationEntity1.setDiscSalbEntity(discountSlabCreationEntity1);
        itemCreationEntity1.setGroupEntity(groupCreationEntity1);
        itemCreationEntity1.setGst(1);
        itemCreationEntity1.setHsnsac(hsnSacCreationEntity1);
        itemCreationEntity1.setId(123L);
        itemCreationEntity1.setItemName("Item Name");
        itemCreationEntity1.setManufactureEntity(manufacturerCreationEntity1);
        itemCreationEntity1.setMax_amt(10.0d);
        itemCreationEntity1.setMax_qty(10.0d);
        itemCreationEntity1.setMin_amt(10.0d);
        itemCreationEntity1.setMin_qty(10.0d);
        itemCreationEntity1.setPackName("Pack Name");
        itemCreationEntity1.setQty_per_pack("Qty per pack");
        itemCreationEntity1.setRateA("Rate A");
        itemCreationEntity1.setRateB("Rate B");
        itemCreationEntity1.setRateC("Rate C");
        itemCreationEntity1.setRateD("Rate D");
        itemCreationEntity1.setScheduleEntity(schedulerCreationEntity1);
        itemCreationEntity1.setStatus("Status");
        itemCreationEntity1.setStoreTypeEntity(storeTypeCreationEntity1);
        itemCreationEntity1.setUpdated_by(1);
        itemCreationEntity1.setUpdated_date(LocalDate.ofEpochDay(1L));

        // Act and Assert
        assertSame(itemCreationEntity, itemCreationServiceImpl.createOrUpdateItem(itemCreationEntity1));
        verify(itemCreationRepository).save((ItemCreationEntity) any());
        verify(itemCreationRepository).findByItemName((String) any());
    }

    /**
     * Method under test: {@link ItemCreationServiceImpl#createOrUpdateItem(List)}
     */
    @Test
    void testCreateOrUpdateItem2() {
        // Arrange
        when(itemCreationRepository.saveAll((Iterable<ItemCreationEntity>) any())).thenReturn(new ArrayList<>());
        ArrayList<ItemCreationEntity> itemCreationEntityList = new ArrayList<>();

        // Act
        itemCreationServiceImpl.createOrUpdateItem(itemCreationEntityList);

        // Assert that nothing has changed
        verify(itemCreationRepository).saveAll((Iterable<ItemCreationEntity>) any());
        assertEquals(itemCreationEntityList, itemCreationServiceImpl.getAllItemList());
    }

    /**
     * Method under test: {@link ItemCreationServiceImpl#createOrUpdateItem(List)}
     */
    @Test
    void testCreateOrUpdateItem3() {
        // Arrange
        when(itemCreationRepository.saveAll((Iterable<ItemCreationEntity>) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> itemCreationServiceImpl.createOrUpdateItem(new ArrayList<>()));
        verify(itemCreationRepository).saveAll((Iterable<ItemCreationEntity>) any());
    }

    /**
     * Method under test: {@link ItemCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<ItemCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(itemCreationRepository.findAllByItemNameContains((String) any(), (Pageable) any())).thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<ItemCreationEntity> actualSearch = itemCreationServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(itemCreationRepository).findAllByItemNameContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link ItemCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(itemCreationRepository.findAllByItemNameContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> itemCreationServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(itemCreationRepository).findAllByItemNameContains((String) any(), (Pageable) any());
    }

    /**
     * Method under test: {@link ItemCreationServiceImpl#updateItem(ItemCreationEntity)}
     */
    @Test
    void testUpdateItem() {
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
        when(itemCreationRepository.save((ItemCreationEntity) any())).thenReturn(itemCreationEntity);

        CompostionCreationEntity compostionCreationEntity1 = new CompostionCreationEntity();
        compostionCreationEntity1.setCompName("Comp Name");
        compostionCreationEntity1.setId(123L);
        compostionCreationEntity1.setStatus("Status");

        DiscountSlabCreationEntity discountSlabCreationEntity1 = new DiscountSlabCreationEntity();
        discountSlabCreationEntity1.setDiscount("3");
        discountSlabCreationEntity1.setDiscountSlabName("3");
        discountSlabCreationEntity1.setFrom_amt(1);
        discountSlabCreationEntity1.setId(123L);
        discountSlabCreationEntity1.setStatus("Status");
        discountSlabCreationEntity1.setTo_amt(1);

        GroupCreationEntity groupCreationEntity1 = new GroupCreationEntity();
        groupCreationEntity1.setGroupName("Group Name");
        groupCreationEntity1.setId(123L);
        groupCreationEntity1.setStatus("Status");

        HsnSacCreationEntity hsnSacCreationEntity1 = new HsnSacCreationEntity();
        hsnSacCreationEntity1.setDescirption("Descirption");
        hsnSacCreationEntity1.setHsnName("Hsn Name");
        hsnSacCreationEntity1.setId(123L);
        hsnSacCreationEntity1.setStatus("Status");

        ManufacturerCreationEntity manufacturerCreationEntity1 = new ManufacturerCreationEntity();
        manufacturerCreationEntity1.setId(123L);
        manufacturerCreationEntity1.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity1.setStatus("Status");

        SchedulerCreationEntity schedulerCreationEntity1 = new SchedulerCreationEntity();
        schedulerCreationEntity1.setId(123L);
        schedulerCreationEntity1.setSchedulerName("Scheduler Name");
        schedulerCreationEntity1.setStatus("Status");
        schedulerCreationEntity1.setWaringMsg("Waring Msg");
        schedulerCreationEntity1.setWarning("Warning");

        StoreTypeCreationEntity storeTypeCreationEntity1 = new StoreTypeCreationEntity();
        storeTypeCreationEntity1.setId(123L);
        storeTypeCreationEntity1.setStatus("Status");
        storeTypeCreationEntity1.setStoreTypeName("Store Type Name");

        ItemCreationEntity itemCreationEntity1 = new ItemCreationEntity();
        itemCreationEntity1.setCompositionEntity(compostionCreationEntity1);
        itemCreationEntity1.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        itemCreationEntity1.setCreated_date(LocalDate.ofEpochDay(1L));
        itemCreationEntity1.setDiscSalbEntity(discountSlabCreationEntity1);
        itemCreationEntity1.setGroupEntity(groupCreationEntity1);
        itemCreationEntity1.setGst(1);
        itemCreationEntity1.setHsnsac(hsnSacCreationEntity1);
        itemCreationEntity1.setId(123L);
        itemCreationEntity1.setItemName("Item Name");
        itemCreationEntity1.setManufactureEntity(manufacturerCreationEntity1);
        itemCreationEntity1.setMax_amt(10.0d);
        itemCreationEntity1.setMax_qty(10.0d);
        itemCreationEntity1.setMin_amt(10.0d);
        itemCreationEntity1.setMin_qty(10.0d);
        itemCreationEntity1.setPackName("Pack Name");
        itemCreationEntity1.setQty_per_pack("Qty per pack");
        itemCreationEntity1.setRateA("Rate A");
        itemCreationEntity1.setRateB("Rate B");
        itemCreationEntity1.setRateC("Rate C");
        itemCreationEntity1.setRateD("Rate D");
        itemCreationEntity1.setScheduleEntity(schedulerCreationEntity1);
        itemCreationEntity1.setStatus("Status");
        itemCreationEntity1.setStoreTypeEntity(storeTypeCreationEntity1);
        itemCreationEntity1.setUpdated_by(1);
        itemCreationEntity1.setUpdated_date(LocalDate.ofEpochDay(1L));

        // Act and Assert
        assertSame(itemCreationEntity, itemCreationServiceImpl.updateItem(itemCreationEntity1));
        verify(itemCreationRepository).save((ItemCreationEntity) any());
    }

    /**
     * Method under test: {@link ItemCreationServiceImpl#deleteItemById(Long)}
     */
    @Test
    void testDeleteItemById() throws RecordNotFoundException {
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
        Optional<ItemCreationEntity> ofResult = Optional.of(itemCreationEntity);
        doNothing().when(itemCreationRepository).deleteById((Long) any());
        when(itemCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act
        itemCreationServiceImpl.deleteItemById(123L);

        // Assert that nothing has changed
        verify(itemCreationRepository).findById((Long) any());
        verify(itemCreationRepository).deleteById((Long) any());
        assertTrue(itemCreationServiceImpl.getAllItemList().isEmpty());
    }

    /**
     * Method under test: {@link ItemCreationServiceImpl#deleteItemById(Long)}
     */
    @Test
    void testDeleteItemById2() throws RecordNotFoundException {
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
        Optional<ItemCreationEntity> ofResult = Optional.of(itemCreationEntity);
        doThrow(new UnableToProcessException("An error occurred")).when(itemCreationRepository).deleteById((Long) any());
        when(itemCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> itemCreationServiceImpl.deleteItemById(123L));
        verify(itemCreationRepository).findById((Long) any());
        verify(itemCreationRepository).deleteById((Long) any());
    }
}

