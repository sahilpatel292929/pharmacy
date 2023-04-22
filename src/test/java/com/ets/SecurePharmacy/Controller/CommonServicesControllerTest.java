package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.service.AccountCreationService;
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CommonServicesController.class})
@ExtendWith(SpringExtension.class)
class CommonServicesControllerTest {
    @MockBean
    private AccountCreationService accountCreationService;

    @MockBean
    private BatchService batchService;

    @Autowired
    private CommonServicesController commonServicesController;

    /**
     * Method under test: {@link CommonServicesController#getPaymentModes()}
     */
    @Test
    void testGetPaymentModes() throws Exception {
        when(accountCreationService.getPaymentModes()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/common/payment_modes");
        MockMvcBuilders.standaloneSetup(commonServicesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link CommonServicesController#getPaymentModes()}
     */
    @Test
    void testGetPaymentModes2() throws Exception {
        when(accountCreationService.getPaymentModes()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/common/payment_modes");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(commonServicesController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link CommonServicesController#getBankNames()}
     */
    @Test
    void testGetBankNames() throws Exception {
        when(accountCreationService.getBankNames()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/common/bank_names");
        MockMvcBuilders.standaloneSetup(commonServicesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link CommonServicesController#getBankNames()}
     */
    @Test
    void testGetBankNames2() throws Exception {
        when(accountCreationService.getBankNames()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/common/bank_names");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(commonServicesController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link CommonServicesController#getMRP(String, Long)}
     */
    @Test
    void testGetMRP() throws Exception {
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
        batchEntity.setMrp(1.00);
        batchEntity.setQtyPerPack(1L);
        when(batchService.getMRP((String) any(), (Long) any())).thenReturn(batchEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/common/get_MRP/{batch}/{item_id}",
                "Batch", 42L);
        MockMvcBuilders.standaloneSetup(commonServicesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<BatchEntity><id>123</id><items><itemName>Item Name</itemName><packName>Pack Name</packName><qty_per_pack>Qty per pack</qty_per_pack><min_amt>10.0</min_amt><max_amt>10.0</max_amt><min_qty>10.0</min_qty><max_qty>10.0</max_qty><gst>1</gst><rateA>Rate A</rateA><rateB>Rate B</rateB><rateC>Rate C</rateC><rateD>Rate D</rateD><status>Status</status><manufactureEntity><id>123</id><manufacturerName>Manufacturer Name</manufacturerName><status>Status</status></manufactureEntity><groupEntity><id>123</id><groupName>Group Name</groupName><status>Status</status></groupEntity><storeTypeEntity><id>123</id><storeTypeName>Store Type Name</storeTypeName><status>Status</status></storeTypeEntity><scheduleEntity><id>123</id><schedulerName>Scheduler Name</schedulerName><waringMsg>Waring Msg</waringMsg><warning>Warning</warning><status>Status</status></scheduleEntity><compositionEntity><id>123</id><compName>Comp Name</compName><information/><schedulerCreationEntity/><status>Status</status></compositionEntity><discSalbEntity><id>123</id><discountSlabName>3</discountSlabName><discount>3</discount><from_amt>1</from_amt><to_amt>1</to_amt><status>Status</status></discSalbEntity><hsnsac><id>123</id><hsnName>Hsn Name</hsnName><descirption>Descirption</descirption><status>Status</status></hsnsac><created_by>Jan 1, 2020 8:00am GMT+0100</created_by><created_date>1970</created_date><created_date>1</created_date><created_date>2</created_date><updated_by>1</updated_by><updated_date>1970</updated_date><updated_date>1</updated_date><updated_date>2</updated_date><id>123</id></items><expiry>1970</expiry><expiry>1</expiry><expiry>2</expiry><qtyPerPack>1</qtyPerPack><batchNo>Batch No</batchNo><mrp>1.0</mrp><looseMRP/><availableStock>10.0</availableStock><batchDetails/></BatchEntity>"));
    }
}

