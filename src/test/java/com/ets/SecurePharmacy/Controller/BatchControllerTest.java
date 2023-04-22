package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.DTO.APIResponse;
import com.ets.SecurePharmacy.exception.UserNotFoundException;
import com.ets.SecurePharmacy.service.BatchService;
import com.ets.SecurePharmacy.tenant.model.BatchEntity;
import com.ets.SecurePharmacy.tenant.model.CompostionCreationEntity;
import com.ets.SecurePharmacy.tenant.model.DiscountSlabCreationEntity;
import com.ets.SecurePharmacy.tenant.model.GroupCreationEntity;
import com.ets.SecurePharmacy.tenant.model.HsnSacCreationEntity;
import com.ets.SecurePharmacy.tenant.model.ItemCreationEntity;
import com.ets.SecurePharmacy.tenant.model.ManufacturerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.SchedulerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.StoreTypeCreationEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {BatchController.class})
@ExtendWith(SpringExtension.class)
class BatchControllerTest {
    @Autowired
    private BatchController batchController;

    @MockBean
    private BatchService batchService;

    /**
     * Method under test: {@link BatchController#exportReport10()}
     */
    @Test
    void testExportReport10() throws Exception {
        when(batchService.getAllAccounts()).thenReturn(new ArrayList<>());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(batchController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link BatchController#exportReport10()}
     */
    @Test
    void testExportReport102() throws Exception {
        when(batchService.getAllAccounts()).thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/batch-service/getBatchDetailsReport");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(batchController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link BatchController#getAllOpenStockAccounts()}
     */
    @Test
    void testGetAllOpenStockAccounts() throws Exception {
        when(batchService.getAllAccounts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/batch-service/getAll");
        MockMvcBuilders.standaloneSetup(batchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link BatchController#getAllOpenStockAccounts()}
     */
    @Test
    void testGetAllOpenStockAccounts2() throws Exception {
        when(batchService.getAllAccounts()).thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/batch-service/getAll");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(batchController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link BatchController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice() throws Exception {
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
        batchEntity.setMrp(1.99);
        batchEntity.setQtyPerPack(1L);
        when(batchService.getAccountsById((Long) any())).thenReturn(batchEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/batch-service/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(batchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<BatchEntity><id>123</id><items><itemName>Item Name</itemName><packName>Pack Name</packName><qty_per_pack>Qty per pack</qty_per_pack><min_amt>10.0</min_amt><max_amt>10.0</max_amt><min_qty>10.0</min_qty><max_qty>10.0</max_qty><gst>1</gst><rateA>Rate A</rateA><rateB>Rate B</rateB><rateC>Rate C</rateC><rateD>Rate D</rateD><status>Status</status><manufactureEntity><id>123</id><manufacturerName>Manufacturer Name</manufacturerName><status>Status</status></manufactureEntity><groupEntity><id>123</id><groupName>Group Name</groupName><status>Status</status></groupEntity><storeTypeEntity><id>123</id><storeTypeName>Store Type Name</storeTypeName><status>Status</status></storeTypeEntity><scheduleEntity><id>123</id><schedulerName>Scheduler Name</schedulerName><waringMsg>Waring Msg</waringMsg><warning>Warning</warning><status>Status</status></scheduleEntity><compositionEntity><id>123</id><compName>Comp Name</compName><information/><schedulerCreationEntity/><status>Status</status></compositionEntity><discSalbEntity><id>123</id><discountSlabName>3</discountSlabName><discount>3</discount><from_amt>1</from_amt><to_amt>1</to_amt><status>Status</status></discSalbEntity><hsnsac><id>123</id><hsnName>Hsn Name</hsnName><descirption>Descirption</descirption><status>Status</status></hsnsac><created_by>Jan 1, 2020 8:00am GMT+0100</created_by><created_date>1970</created_date><created_date>1</created_date><created_date>2</created_date><updated_by>1</updated_by><updated_date>1970</updated_date><updated_date>1</updated_date><updated_date>2</updated_date><id>123</id></items><expiry>1970</expiry><expiry>1</expiry><expiry>2</expiry><qtyPerPack>1</qtyPerPack><batchNo>Batch No</batchNo><mrp>1.99</mrp><looseMRP/><availableStock>10.0</availableStock><batchDetails/></BatchEntity>")
                );
    }

    /**
     * Method under test: {@link BatchController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice2() throws Exception {
        when(batchService.getAccountsById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/batch-service/get/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(batchController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link BatchController#getByBatchNo(String)}
     */
    @Test
    void testGetByBatchNo() throws Exception {
        when(batchService.getBatchDetails((String) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/batch-service/getBatch/{batch_number}", "1234");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(batchController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link BatchController#getByBatchNo(String)}
     */
    @Test
    void testGetByBatchNo1() throws Exception {
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
        batchEntity.setBatchNo("1234");
        batchEntity.setExpiry(LocalDate.ofEpochDay(1L));
        batchEntity.setId(123L);
        batchEntity.setItems(itemCreationEntity);
        batchEntity.setMrp(1.899);
        batchEntity.setQtyPerPack(1L);
        when(batchService.getBatchDetails((String) any())).thenReturn(batchEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/batch-service/getBatch/{batch_number}", "1234");

        MockMvcBuilders.standaloneSetup(batchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<BatchEntity><id>123</id><items><itemName>Item Name</itemName><packName>Pack Name</packName><qty_per_pack>Qty per pack</qty_per_pack><min_amt>10.0</min_amt><max_amt>10.0</max_amt><min_qty>10.0</min_qty><max_qty>10.0</max_qty><gst>1</gst><rateA>Rate A</rateA><rateB>Rate B</rateB><rateC>Rate C</rateC><rateD>Rate D</rateD><status>Status</status><manufactureEntity><id>123</id><manufacturerName>Manufacturer Name</manufacturerName><status>Status</status></manufactureEntity><groupEntity><id>123</id><groupName>Group Name</groupName><status>Status</status></groupEntity><storeTypeEntity><id>123</id><storeTypeName>Store Type Name</storeTypeName><status>Status</status></storeTypeEntity><scheduleEntity><id>123</id><schedulerName>Scheduler Name</schedulerName><waringMsg>Waring Msg</waringMsg><warning>Warning</warning><status>Status</status></scheduleEntity><compositionEntity><id>123</id><compName>Comp Name</compName><information/><schedulerCreationEntity/><status>Status</status></compositionEntity><discSalbEntity><id>123</id><discountSlabName>3</discountSlabName><discount>3</discount><from_amt>1</from_amt><to_amt>1</to_amt><status>Status</status></discSalbEntity><hsnsac><id>123</id><hsnName>Hsn Name</hsnName><descirption>Descirption</descirption><status>Status</status></hsnsac><created_by>Jan 1, 2020 8:00am GMT+0100</created_by><created_date>1970</created_date><created_date>1</created_date><created_date>2</created_date><updated_by>1</updated_by><updated_date>1970</updated_date><updated_date>1</updated_date><updated_date>2</updated_date><id>123</id></items><expiry>1970</expiry><expiry>1</expiry><expiry>2</expiry><qtyPerPack>1</qtyPerPack><batchNo>1234</batchNo><mrp>1.899</mrp><looseMRP/><availableStock>10.0</availableStock><batchDetails/></BatchEntity>")
                );
    }


    /**
     * Method under test: {@link BatchController#openStockCreate(BatchEntity)}
     */
    @Test
    void testOpenStockCreate() throws Exception {
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
        batchEntity.setMrp(1.7878);
        batchEntity.setQtyPerPack(1L);
        BatchEntity batchEntity1 = new BatchEntity();
        batchEntity.setAvailableStock(10.0d);
        batchEntity.setBatchDetails(new ArrayList<>());
        batchEntity.setBatchNo("Batch No");
        batchEntity.setExpiry(LocalDate.ofEpochDay(1L));
        batchEntity.setId(123L);
        batchEntity.setItems(itemCreationEntity);
        batchEntity.setMrp(1.899);
        batchEntity.setQtyPerPack(1L);
        batchEntity1.setId(1l);
        when(batchService.createOrUpdateAccounts(batchEntity)).thenReturn(batchEntity1);
        ResponseEntity<BatchEntity> response = batchController.openStockCreate(batchEntity);
        Assertions.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    /**
     * Method under test: {@link BatchController#updateOpenStock(BatchEntity)}
     */
    @Test
    void testUpdateOpenStock() throws Exception {
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
        batchEntity.setMrp(1.899);
        batchEntity.setQtyPerPack(1L);
        when(batchService.createOrUpdateAccounts(batchEntity)).thenReturn(batchEntity);
        ResponseEntity<BatchEntity> response = batchController.updateOpenStock(batchEntity);
        Assertions.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        Assertions.assertTrue(response.getBody().equals(batchEntity));
    }

    /**
     * Method under test: {@link BatchController#getBatchWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetBatchWithPagination() throws Exception {
        when(batchService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<BatchEntity>> response = batchController.getBatchWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link BatchController#getBatchWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetBatchWithPagination2() throws Exception {
        when(batchService.getAllBatchWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<BatchEntity>> response = batchController.getBatchWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

}

