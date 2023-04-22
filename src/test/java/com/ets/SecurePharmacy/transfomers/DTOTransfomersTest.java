package com.ets.SecurePharmacy.transfomers;

import com.ets.SecurePharmacy.DTO.AccountCreationDTO;
import com.ets.SecurePharmacy.DTO.AreaCreationDTO;
import com.ets.SecurePharmacy.DTO.BoyCreationDTO;
import com.ets.SecurePharmacy.DTO.BranchCreationDTO;
import com.ets.SecurePharmacy.DTO.CashWithdrawDTO;
import com.ets.SecurePharmacy.DTO.CompostionCreationDTO;
import com.ets.SecurePharmacy.DTO.CounterSaleDTO;
import com.ets.SecurePharmacy.DTO.CustomerCreationDTO;
import com.ets.SecurePharmacy.DTO.DiscountSlabCreationDTO;
import com.ets.SecurePharmacy.DTO.GSTTypeCreationDTO;
import com.ets.SecurePharmacy.DTO.GroupCreationDTO;
import com.ets.SecurePharmacy.DTO.HsnSacCreationDTO;
import com.ets.SecurePharmacy.DTO.ItemCreationDTO;
import com.ets.SecurePharmacy.DTO.ManufacturerCreationDTO;
import com.ets.SecurePharmacy.DTO.OpenStockEntryDTO;
import com.ets.SecurePharmacy.DTO.OrderGenerationDTO;
import com.ets.SecurePharmacy.DTO.PackCreationDTO;
import com.ets.SecurePharmacy.DTO.PurchaseEntryDTO;
import com.ets.SecurePharmacy.DTO.PurchaseOrderDTO;
import com.ets.SecurePharmacy.DTO.ReceiptEntryDTO;
import com.ets.SecurePharmacy.DTO.RouteCreationDTO;
import com.ets.SecurePharmacy.DTO.SalesInvoiceDTO;
import com.ets.SecurePharmacy.DTO.SalesManCreationDTO;
import com.ets.SecurePharmacy.DTO.SchedulerCreationDTO;
import com.ets.SecurePharmacy.DTO.ShortageEntryDTO;
import com.ets.SecurePharmacy.DTO.StockAdjDTO;
import com.ets.SecurePharmacy.DTO.StoreTypeCreationDTO;
import com.ets.SecurePharmacy.DTO.SupplierCreationDTO;
import com.ets.SecurePharmacy.DTO.WarningCreationDTO;
import com.ets.SecurePharmacy.tenant.model.AccountCreationEntity;
import com.ets.SecurePharmacy.tenant.model.AreaCreationEntity;
import com.ets.SecurePharmacy.tenant.model.BoyCreationEntity;
import com.ets.SecurePharmacy.tenant.model.BranchCreationEntity;
import com.ets.SecurePharmacy.tenant.model.CashWithdrawEntity;
import com.ets.SecurePharmacy.tenant.model.CompostionCreationEntity;
import com.ets.SecurePharmacy.tenant.model.CounterSaleEntity;
import com.ets.SecurePharmacy.tenant.model.CustomerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.DiscountSlabCreationEntity;
import com.ets.SecurePharmacy.tenant.model.GSTTypeCreationEntity;
import com.ets.SecurePharmacy.tenant.model.GroupCreationEntity;
import com.ets.SecurePharmacy.tenant.model.HsnSacCreationEntity;
import com.ets.SecurePharmacy.tenant.model.ItemCreationEntity;
import com.ets.SecurePharmacy.tenant.model.ManufacturerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.OpenStockEntryEntity;
import com.ets.SecurePharmacy.tenant.model.OrderGenerationEntity;
import com.ets.SecurePharmacy.tenant.model.PackCreationEntity;
import com.ets.SecurePharmacy.tenant.model.PurchaseEntryEntity;
import com.ets.SecurePharmacy.tenant.model.PurchaseOrderEntity;
import com.ets.SecurePharmacy.tenant.model.ReceiptEntryEntity;
import com.ets.SecurePharmacy.tenant.model.RouteCreationEntity;
import com.ets.SecurePharmacy.tenant.model.SalesInvoiceEntity;
import com.ets.SecurePharmacy.tenant.model.SalesManCreationEntity;
import com.ets.SecurePharmacy.tenant.model.SchedulerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.ShortageEntryEntity;
import com.ets.SecurePharmacy.tenant.model.StockAdjEntity;
import com.ets.SecurePharmacy.tenant.model.StoreTypeCreationEntity;
import com.ets.SecurePharmacy.tenant.model.SupplierCreationEntity;
import com.ets.SecurePharmacy.tenant.model.WarningCreationEntity;
import com.ets.SecurePharmacy.util.DateConvertUtils;
import org.apache.tomcat.util.json.ParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {DTOTransfomers.class})
@ExtendWith(SpringExtension.class)
class DTOTransfomersTest {
    @Autowired
    private DTOTransfomers dTOTransfomers;

    @MockBean
    private DateConvertUtils dateConvertUtils;

    @MockBean
    private ModelMapper modelMapper;

    /**
     * Method under test: {@link DTOTransfomers#dtoToEntity(Object, Class)}
     */
    @Test
    void testDtoToEntity() {
        // Arrange
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");

        // Act and Assert
        assertEquals("Map", dTOTransfomers.dtoToEntity("Dto", Object.class));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#entityToDto(Object, Class)}
     */
    @Test
    void testEntityToDto() {
        // Arrange
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");

        // Act and Assert
        assertEquals("Map", dTOTransfomers.entityToDto("Entity", Object.class));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#accountCreationConvertToDto(AccountCreationEntity)}
     */
    @Test
    void testAccountCreationConvertToDto() {
        // Arrange
        AccountCreationDTO accountCreationDTO = new AccountCreationDTO();
        when(modelMapper.map((Object) any(), (Class<AccountCreationDTO>) any())).thenReturn(accountCreationDTO);

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

        // Act and Assert
        assertSame(accountCreationDTO, dTOTransfomers.accountCreationConvertToDto(accountCreationEntity));
        verify(modelMapper).map((Object) any(), (Class<AccountCreationDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#accountCreationConvertToEntity(AccountCreationDTO)}
     */
    @Test
    void testAccountCreationConvertToEntity() throws ParseException {
        // Arrange
        when(dateConvertUtils.convertStringToDate((String) any())).thenReturn(LocalDate.ofEpochDay(1L));

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
        when(modelMapper.map((Object) any(), (Class<AccountCreationEntity>) any())).thenReturn(accountCreationEntity);

        // Act
        AccountCreationEntity actualAccountCreationConvertToEntityResult = dTOTransfomers
                .accountCreationConvertToEntity(new AccountCreationDTO());

        // Assert
        assertSame(accountCreationEntity, actualAccountCreationConvertToEntityResult);
        assertEquals("1970-01-02", actualAccountCreationConvertToEntityResult.getOpeningBalDate().toString());
        verify(dateConvertUtils).convertStringToDate((String) any());
        verify(modelMapper).map((Object) any(), (Class<AccountCreationEntity>) any());
    }


    /**
     * Method under test: {@link DTOTransfomers#accountCreationConvertToEntityList(List)}
     */
    @Test
    void testAccountCreationConvertToEntityList() throws ParseException {
        // Arrange, Act and Assert
        assertTrue(dTOTransfomers.accountCreationConvertToEntityList(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link DTOTransfomers#accountCreationConvertToEntityList(List)}
     */
    @Test
    void testAccountCreationConvertToEntityList2() throws ParseException {
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
        when(modelMapper.map((Object) any(), (Class<AccountCreationEntity>) any())).thenReturn(accountCreationEntity);

        ArrayList<AccountCreationDTO> accountCreationDTOList = new ArrayList<>();
        accountCreationDTOList.add(new AccountCreationDTO());

        // Act and Assert
        assertEquals(1, dTOTransfomers.accountCreationConvertToEntityList(accountCreationDTOList).size());
        verify(modelMapper).map((Object) any(), (Class<AccountCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#accountCreationConvertToEntityList(List)}
     */
    @Test
    void testAccountCreationConvertToEntityList3() throws ParseException {
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
        when(modelMapper.map((Object) any(), (Class<AccountCreationEntity>) any())).thenReturn(accountCreationEntity);

        ArrayList<AccountCreationDTO> accountCreationDTOList = new ArrayList<>();
        accountCreationDTOList.add(new AccountCreationDTO());
        accountCreationDTOList.add(new AccountCreationDTO());

        // Act and Assert
        assertEquals(2, dTOTransfomers.accountCreationConvertToEntityList(accountCreationDTOList).size());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<AccountCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#areaCreationConvertToDto(AreaCreationEntity)}
     */
    @Test
    void testAreaCreationConvertToDto() {
        // Arrange
        AreaCreationDTO areaCreationDTO = new AreaCreationDTO();
        when(modelMapper.map((Object) any(), (Class<AreaCreationDTO>) any())).thenReturn(areaCreationDTO);

        AreaCreationEntity areaCreationEntity = new AreaCreationEntity();
        areaCreationEntity.setAreaName("Area Name");
        areaCreationEntity.setId(123L);
        areaCreationEntity.setRoute("Route");
        areaCreationEntity.setStatus("Status");

        // Act and Assert
        assertSame(areaCreationDTO, dTOTransfomers.areaCreationConvertToDto(areaCreationEntity));
        verify(modelMapper).map((Object) any(), (Class<AreaCreationDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#areaCreationConvertToEntityList(List)}
     */
    @Test
    void testAreaCreationConvertToEntityList() throws ParseException {
        // Arrange, Act and Assert
        assertTrue(dTOTransfomers.areaCreationConvertToEntityList(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link DTOTransfomers#areaCreationConvertToEntityList(List)}
     */
    @Test
    void testAreaCreationConvertToEntityList2() throws ParseException {
        // Arrange
        AreaCreationEntity areaCreationEntity = new AreaCreationEntity();
        areaCreationEntity.setAreaName("Area Name");
        areaCreationEntity.setId(123L);
        areaCreationEntity.setRoute("Route");
        areaCreationEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<AreaCreationEntity>) any())).thenReturn(areaCreationEntity);

        ArrayList<AreaCreationDTO> areaCreationDTOList = new ArrayList<>();
        areaCreationDTOList.add(new AreaCreationDTO());

        // Act and Assert
        assertEquals(1, dTOTransfomers.areaCreationConvertToEntityList(areaCreationDTOList).size());
        verify(modelMapper).map((Object) any(), (Class<AreaCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#areaCreationConvertToEntityList(List)}
     */
    @Test
    void testAreaCreationConvertToEntityList3() throws ParseException {
        // Arrange
        AreaCreationEntity areaCreationEntity = new AreaCreationEntity();
        areaCreationEntity.setAreaName("Area Name");
        areaCreationEntity.setId(123L);
        areaCreationEntity.setRoute("Route");
        areaCreationEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<AreaCreationEntity>) any())).thenReturn(areaCreationEntity);

        ArrayList<AreaCreationDTO> areaCreationDTOList = new ArrayList<>();
        areaCreationDTOList.add(new AreaCreationDTO());
        areaCreationDTOList.add(new AreaCreationDTO());

        // Act and Assert
        assertEquals(2, dTOTransfomers.areaCreationConvertToEntityList(areaCreationDTOList).size());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<AreaCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#areaCreationConvertToEntity(AreaCreationDTO)}
     */
    @Test
    void testAreaCreationConvertToEntity() throws ParseException {
        // Arrange
        AreaCreationEntity areaCreationEntity = new AreaCreationEntity();
        areaCreationEntity.setAreaName("Area Name");
        areaCreationEntity.setId(123L);
        areaCreationEntity.setRoute("Route");
        areaCreationEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<AreaCreationEntity>) any())).thenReturn(areaCreationEntity);

        // Act and Assert
        assertSame(areaCreationEntity, dTOTransfomers.areaCreationConvertToEntity(new AreaCreationDTO()));
        verify(modelMapper).map((Object) any(), (Class<AreaCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#boyCreationConvertToDto(BoyCreationEntity)}
     */
    @Test
    void testBoyCreationConvertToDto() {
        // Arrange
        BoyCreationDTO boyCreationDTO = new BoyCreationDTO();
        when(modelMapper.map((Object) any(), (Class<BoyCreationDTO>) any())).thenReturn(boyCreationDTO);

        BoyCreationEntity boyCreationEntity = new BoyCreationEntity();
        boyCreationEntity.setAddress1("42 Main St");
        boyCreationEntity.setAddress2("42 Main St");
        boyCreationEntity.setBoyName("Boy Name");
        boyCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        boyCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        boyCreationEntity.setId(123L);
        boyCreationEntity.setMobileNo("Mobile No");
        boyCreationEntity.setSalary("Salary");
        boyCreationEntity.setStatus("Status");
        boyCreationEntity.setUpdated_by(1);
        boyCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        // Act and Assert
        assertSame(boyCreationDTO, dTOTransfomers.boyCreationConvertToDto(boyCreationEntity));
        verify(modelMapper).map((Object) any(), (Class<BoyCreationDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#boyCreationConvertToEntity(BoyCreationDTO)}
     */
    @Test
    void testBoyCreationConvertToEntity() throws ParseException {
        // Arrange
        BoyCreationEntity boyCreationEntity = new BoyCreationEntity();
        boyCreationEntity.setAddress1("42 Main St");
        boyCreationEntity.setAddress2("42 Main St");
        boyCreationEntity.setBoyName("Boy Name");
        boyCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        boyCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        boyCreationEntity.setId(123L);
        boyCreationEntity.setMobileNo("Mobile No");
        boyCreationEntity.setSalary("Salary");
        boyCreationEntity.setStatus("Status");
        boyCreationEntity.setUpdated_by(1);
        boyCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));
        when(modelMapper.map((Object) any(), (Class<BoyCreationEntity>) any())).thenReturn(boyCreationEntity);

        // Act and Assert
        assertSame(boyCreationEntity, dTOTransfomers.boyCreationConvertToEntity(new BoyCreationDTO()));
        verify(modelMapper).map((Object) any(), (Class<BoyCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#boyCreationConvertToEntity(List)}
     */
    @Test
    void testBoyCreationConvertToEntity2() throws ParseException {
        // Arrange, Act and Assert
        assertTrue(dTOTransfomers.boyCreationConvertToEntity(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link DTOTransfomers#boyCreationConvertToEntity(List)}
     */
    @Test
    void testBoyCreationConvertToEntity3() throws ParseException {
        // Arrange
        BoyCreationEntity boyCreationEntity = new BoyCreationEntity();
        boyCreationEntity.setAddress1("42 Main St");
        boyCreationEntity.setAddress2("42 Main St");
        boyCreationEntity.setBoyName("Boy Name");
        boyCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        boyCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        boyCreationEntity.setId(123L);
        boyCreationEntity.setMobileNo("Mobile No");
        boyCreationEntity.setSalary("Salary");
        boyCreationEntity.setStatus("Status");
        boyCreationEntity.setUpdated_by(1);
        boyCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));
        when(modelMapper.map((Object) any(), (Class<BoyCreationEntity>) any())).thenReturn(boyCreationEntity);

        ArrayList<BoyCreationDTO> boyCreationDTOList = new ArrayList<>();
        boyCreationDTOList.add(new BoyCreationDTO());

        // Act and Assert
        assertEquals(1, dTOTransfomers.boyCreationConvertToEntity(boyCreationDTOList).size());
        verify(modelMapper).map((Object) any(), (Class<BoyCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#boyCreationConvertToEntity(List)}
     */
    @Test
    void testBoyCreationConvertToEntity4() throws ParseException {
        // Arrange
        BoyCreationEntity boyCreationEntity = new BoyCreationEntity();
        boyCreationEntity.setAddress1("42 Main St");
        boyCreationEntity.setAddress2("42 Main St");
        boyCreationEntity.setBoyName("Boy Name");
        boyCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        boyCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        boyCreationEntity.setId(123L);
        boyCreationEntity.setMobileNo("Mobile No");
        boyCreationEntity.setSalary("Salary");
        boyCreationEntity.setStatus("Status");
        boyCreationEntity.setUpdated_by(1);
        boyCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));
        when(modelMapper.map((Object) any(), (Class<BoyCreationEntity>) any())).thenReturn(boyCreationEntity);

        ArrayList<BoyCreationDTO> boyCreationDTOList = new ArrayList<>();
        boyCreationDTOList.add(new BoyCreationDTO());
        boyCreationDTOList.add(new BoyCreationDTO());

        // Act and Assert
        assertEquals(2, dTOTransfomers.boyCreationConvertToEntity(boyCreationDTOList).size());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<BoyCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#branchCreationConvertToDto(BranchCreationEntity)}
     */
    @Test
    void testBranchCreationConvertToDto() {
        // Arrange
        BranchCreationDTO branchCreationDTO = new BranchCreationDTO();
        when(modelMapper.map((Object) any(), (Class<BranchCreationDTO>) any())).thenReturn(branchCreationDTO);

        BranchCreationEntity branchCreationEntity = new BranchCreationEntity();
        branchCreationEntity.setAddreess("42 Main St");
        branchCreationEntity.setBranchName("janedoe/featurebranch");
        branchCreationEntity.setContact_name("Contact name");
        branchCreationEntity.setDlNumber("42");
        branchCreationEntity.setGstNumber("42");
        branchCreationEntity.setId(123L);
        branchCreationEntity.setLocation("Location");
        branchCreationEntity.setMobileNo("Mobile No");
        branchCreationEntity.setPincode(1);
        branchCreationEntity.setStatus("Status");

        // Act and Assert
        assertSame(branchCreationDTO, dTOTransfomers.branchCreationConvertToDto(branchCreationEntity));
        verify(modelMapper).map((Object) any(), (Class<BranchCreationDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#branchCreationConvertToEntity(BranchCreationDTO)}
     */
    @Test
    void testBranchCreationConvertToEntity() throws ParseException {
        // Arrange
        BranchCreationEntity branchCreationEntity = new BranchCreationEntity();
        branchCreationEntity.setAddreess("42 Main St");
        branchCreationEntity.setBranchName("janedoe/featurebranch");
        branchCreationEntity.setContact_name("Contact name");
        branchCreationEntity.setDlNumber("42");
        branchCreationEntity.setGstNumber("42");
        branchCreationEntity.setId(123L);
        branchCreationEntity.setLocation("Location");
        branchCreationEntity.setMobileNo("Mobile No");
        branchCreationEntity.setPincode(1);
        branchCreationEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<BranchCreationEntity>) any())).thenReturn(branchCreationEntity);

        // Act and Assert
        assertSame(branchCreationEntity, dTOTransfomers.branchCreationConvertToEntity(new BranchCreationDTO()));
        verify(modelMapper).map((Object) any(), (Class<BranchCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#branchCreationConvertToEntityList(List)}
     */
    @Test
    void testBranchCreationConvertToEntityList() throws ParseException {
        // Arrange, Act and Assert
        assertTrue(dTOTransfomers.branchCreationConvertToEntityList(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link DTOTransfomers#branchCreationConvertToEntityList(List)}
     */
    @Test
    void testBranchCreationConvertToEntityList2() throws ParseException {
        // Arrange
        BranchCreationEntity branchCreationEntity = new BranchCreationEntity();
        branchCreationEntity.setAddreess("42 Main St");
        branchCreationEntity.setBranchName("janedoe/featurebranch");
        branchCreationEntity.setContact_name("Contact name");
        branchCreationEntity.setDlNumber("42");
        branchCreationEntity.setGstNumber("42");
        branchCreationEntity.setId(123L);
        branchCreationEntity.setLocation("Location");
        branchCreationEntity.setMobileNo("Mobile No");
        branchCreationEntity.setPincode(1);
        branchCreationEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<BranchCreationEntity>) any())).thenReturn(branchCreationEntity);

        ArrayList<BranchCreationDTO> branchCreationDTOList = new ArrayList<>();
        branchCreationDTOList.add(new BranchCreationDTO());

        // Act and Assert
        assertEquals(1, dTOTransfomers.branchCreationConvertToEntityList(branchCreationDTOList).size());
        verify(modelMapper).map((Object) any(), (Class<BranchCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#branchCreationConvertToEntityList(List)}
     */
    @Test
    void testBranchCreationConvertToEntityList3() throws ParseException {
        // Arrange
        BranchCreationEntity branchCreationEntity = new BranchCreationEntity();
        branchCreationEntity.setAddreess("42 Main St");
        branchCreationEntity.setBranchName("janedoe/featurebranch");
        branchCreationEntity.setContact_name("Contact name");
        branchCreationEntity.setDlNumber("42");
        branchCreationEntity.setGstNumber("42");
        branchCreationEntity.setId(123L);
        branchCreationEntity.setLocation("Location");
        branchCreationEntity.setMobileNo("Mobile No");
        branchCreationEntity.setPincode(1);
        branchCreationEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<BranchCreationEntity>) any())).thenReturn(branchCreationEntity);

        ArrayList<BranchCreationDTO> branchCreationDTOList = new ArrayList<>();
        branchCreationDTOList.add(new BranchCreationDTO());
        branchCreationDTOList.add(new BranchCreationDTO());

        // Act and Assert
        assertEquals(2, dTOTransfomers.branchCreationConvertToEntityList(branchCreationDTOList).size());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<BranchCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#cashWithdrawConvertToDto(CashWithdrawEntity)}
     */
    @Test
    void testCashWithdrawConvertToDto() {
        // Arrange
        CashWithdrawDTO cashWithdrawDTO = new CashWithdrawDTO();
        when(modelMapper.map((Object) any(), (Class<CashWithdrawDTO>) any())).thenReturn(cashWithdrawDTO);

        CashWithdrawEntity cashWithdrawEntity = new CashWithdrawEntity();
        cashWithdrawEntity.setAmount(10.0d);
        cashWithdrawEntity.setBankName("Bank Name");
        cashWithdrawEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        cashWithdrawEntity.setId(123L);
        cashWithdrawEntity.setStatus("Status");

        // Act and Assert
        assertSame(cashWithdrawDTO, dTOTransfomers.cashWithdrawConvertToDto(cashWithdrawEntity));
        verify(modelMapper).map((Object) any(), (Class<CashWithdrawDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#cashWithdrawConvertToEntity(CashWithdrawDTO)}
     */
    @Test
    void testCashWithdrawConvertToEntity() throws ParseException {
        // Arrange
        when(dateConvertUtils.convertStringToDate((String) any())).thenReturn(LocalDate.ofEpochDay(1L));

        CashWithdrawEntity cashWithdrawEntity = new CashWithdrawEntity();
        cashWithdrawEntity.setAmount(10.0d);
        cashWithdrawEntity.setBankName("Bank Name");
        cashWithdrawEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        cashWithdrawEntity.setId(123L);
        cashWithdrawEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<CashWithdrawEntity>) any())).thenReturn(cashWithdrawEntity);

        // Act
        CashWithdrawEntity actualCashWithdrawConvertToEntityResult = dTOTransfomers
                .cashWithdrawConvertToEntity(new CashWithdrawDTO());

        // Assert
        assertSame(cashWithdrawEntity, actualCashWithdrawConvertToEntityResult);
        assertEquals("1970-01-02", actualCashWithdrawConvertToEntityResult.getEntryDate().toString());
        verify(dateConvertUtils).convertStringToDate((String) any());
        verify(modelMapper).map((Object) any(), (Class<CashWithdrawEntity>) any());
    }


    /**
     * Method under test: {@link DTOTransfomers#cashWithdrawConvertToEntityList(List)}
     */
    @Test
    void testCashWithdrawConvertToEntityList() throws ParseException {
        // Arrange, Act and Assert
        assertTrue(dTOTransfomers.cashWithdrawConvertToEntityList(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link DTOTransfomers#cashWithdrawConvertToEntityList(List)}
     */
    @Test
    void testCashWithdrawConvertToEntityList2() throws ParseException {
        // Arrange
        CashWithdrawEntity cashWithdrawEntity = new CashWithdrawEntity();
        cashWithdrawEntity.setAmount(10.0d);
        cashWithdrawEntity.setBankName("Bank Name");
        cashWithdrawEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        cashWithdrawEntity.setId(123L);
        cashWithdrawEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<CashWithdrawEntity>) any())).thenReturn(cashWithdrawEntity);

        ArrayList<CashWithdrawDTO> cashWithdrawDTOList = new ArrayList<>();
        cashWithdrawDTOList.add(new CashWithdrawDTO());

        // Act and Assert
        assertEquals(1, dTOTransfomers.cashWithdrawConvertToEntityList(cashWithdrawDTOList).size());
        verify(modelMapper).map((Object) any(), (Class<CashWithdrawEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#cashWithdrawConvertToEntityList(List)}
     */
    @Test
    void testCashWithdrawConvertToEntityList3() throws ParseException {
        // Arrange
        CashWithdrawEntity cashWithdrawEntity = new CashWithdrawEntity();
        cashWithdrawEntity.setAmount(10.0d);
        cashWithdrawEntity.setBankName("Bank Name");
        cashWithdrawEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        cashWithdrawEntity.setId(123L);
        cashWithdrawEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<CashWithdrawEntity>) any())).thenReturn(cashWithdrawEntity);

        ArrayList<CashWithdrawDTO> cashWithdrawDTOList = new ArrayList<>();
        cashWithdrawDTOList.add(new CashWithdrawDTO());
        cashWithdrawDTOList.add(new CashWithdrawDTO());

        // Act and Assert
        assertEquals(2, dTOTransfomers.cashWithdrawConvertToEntityList(cashWithdrawDTOList).size());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<CashWithdrawEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#counterSaleConvertToDto(CounterSaleEntity)}
     */
    @Test
    void testCounterSaleConvertToDto() {
        // Arrange
        CounterSaleDTO counterSaleDTO = new CounterSaleDTO();
        when(modelMapper.map((Object) any(), (Class<CounterSaleDTO>) any())).thenReturn(counterSaleDTO);

        CounterSaleEntity counterSaleEntity = new CounterSaleEntity();
        counterSaleEntity.setCounteSaleDetails(new ArrayList<>());
        counterSaleEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        counterSaleEntity.setId(123L);
        counterSaleEntity.setStatus("Status");
        counterSaleEntity.setTotalItems(1000);
        counterSaleEntity.setTotalVal(10.0d);

        // Act and Assert
        assertSame(counterSaleDTO, dTOTransfomers.counterSaleConvertToDto(counterSaleEntity));
        verify(modelMapper).map((Object) any(), (Class<CounterSaleDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#counterSaleConvertToEntity(CounterSaleDTO)}
     */
    @Test
    void testCounterSaleConvertToEntity() throws ParseException {
        // Arrange
        when(dateConvertUtils.convertStringToDate((String) any())).thenReturn(LocalDate.ofEpochDay(1L));

        CounterSaleEntity counterSaleEntity = new CounterSaleEntity();
        counterSaleEntity.setCounteSaleDetails(new ArrayList<>());
        counterSaleEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        counterSaleEntity.setId(123L);
        counterSaleEntity.setStatus("Status");
        counterSaleEntity.setTotalItems(1000);
        counterSaleEntity.setTotalVal(10.0d);
        when(modelMapper.map((Object) any(), (Class<CounterSaleEntity>) any())).thenReturn(counterSaleEntity);

        // Act
        CounterSaleEntity actualCounterSaleConvertToEntityResult = dTOTransfomers
                .counterSaleConvertToEntity(new CounterSaleDTO());

        // Assert
        assertSame(counterSaleEntity, actualCounterSaleConvertToEntityResult);
        assertEquals("1970-01-02", actualCounterSaleConvertToEntityResult.getEntryDate().toString());
        verify(dateConvertUtils).convertStringToDate((String) any());
        verify(modelMapper).map((Object) any(), (Class<CounterSaleEntity>) any());
    }


    /**
     * Method under test: {@link DTOTransfomers#counterSaleConvertToEntityList(List)}
     */
    @Test
    void testCounterSaleConvertToEntityList() throws ParseException {
        // Arrange, Act and Assert
        assertTrue(dTOTransfomers.counterSaleConvertToEntityList(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link DTOTransfomers#counterSaleConvertToEntityList(List)}
     */
    @Test
    void testCounterSaleConvertToEntityList2() throws ParseException {
        // Arrange
        CounterSaleEntity counterSaleEntity = new CounterSaleEntity();
        counterSaleEntity.setCounteSaleDetails(new ArrayList<>());
        counterSaleEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        counterSaleEntity.setId(123L);
        counterSaleEntity.setStatus("Status");
        counterSaleEntity.setTotalItems(1000);
        counterSaleEntity.setTotalVal(10.0d);
        when(modelMapper.map((Object) any(), (Class<CounterSaleEntity>) any())).thenReturn(counterSaleEntity);

        ArrayList<CounterSaleDTO> counterSaleDTOList = new ArrayList<>();
        counterSaleDTOList.add(new CounterSaleDTO());

        // Act and Assert
        assertEquals(1, dTOTransfomers.counterSaleConvertToEntityList(counterSaleDTOList).size());
        verify(modelMapper).map((Object) any(), (Class<CounterSaleEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#counterSaleConvertToEntityList(List)}
     */
    @Test
    void testCounterSaleConvertToEntityList3() throws ParseException {
        // Arrange
        CounterSaleEntity counterSaleEntity = new CounterSaleEntity();
        counterSaleEntity.setCounteSaleDetails(new ArrayList<>());
        counterSaleEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        counterSaleEntity.setId(123L);
        counterSaleEntity.setStatus("Status");
        counterSaleEntity.setTotalItems(1000);
        counterSaleEntity.setTotalVal(10.0d);
        when(modelMapper.map((Object) any(), (Class<CounterSaleEntity>) any())).thenReturn(counterSaleEntity);

        ArrayList<CounterSaleDTO> counterSaleDTOList = new ArrayList<>();
        counterSaleDTOList.add(new CounterSaleDTO());
        counterSaleDTOList.add(new CounterSaleDTO());

        // Act and Assert
        assertEquals(2, dTOTransfomers.counterSaleConvertToEntityList(counterSaleDTOList).size());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<CounterSaleEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#customerCreationConvertToDto(CustomerCreationEntity)}
     */
    @Test
    void testCustomerCreationConvertToDto() {
        // Arrange
        CustomerCreationDTO customerCreationDTO = new CustomerCreationDTO();
        when(modelMapper.map((Object) any(), (Class<CustomerCreationDTO>) any())).thenReturn(customerCreationDTO);

        CustomerCreationEntity customerCreationEntity = new CustomerCreationEntity();
        customerCreationEntity.setAddress1("42 Main St");
        customerCreationEntity.setAddress2("42 Main St");
        customerCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        customerCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setCreditLimit(1);
        customerCreationEntity.setCustomerName("Customer Name");
        customerCreationEntity.setDiscount(3);
        customerCreationEntity.setDueDays(1);
        customerCreationEntity.setGstNo("Gst No");
        customerCreationEntity.setGstType("Gst Type");
        customerCreationEntity.setId(123L);
        customerCreationEntity.setMobileNo("Mobile No");
        customerCreationEntity.setModeOfPayment("Mode Of Payment");
        customerCreationEntity.setOpeningBal(1);
        customerCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setRateSlab(1);
        customerCreationEntity.setStatus("Status");
        customerCreationEntity.setUpdated_by("2020-03-01");
        customerCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        // Act and Assert
        assertSame(customerCreationDTO, dTOTransfomers.customerCreationConvertToDto(customerCreationEntity));
        verify(modelMapper).map((Object) any(), (Class<CustomerCreationDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#customerCreationConvertToEntity(CustomerCreationDTO)}
     */
    @Test
    void testCustomerCreationConvertToEntity() throws ParseException {
        // Arrange
        when(dateConvertUtils.convertStringToDate((String) any())).thenReturn(LocalDate.ofEpochDay(1L));

        CustomerCreationEntity customerCreationEntity = new CustomerCreationEntity();
        customerCreationEntity.setAddress1("42 Main St");
        customerCreationEntity.setAddress2("42 Main St");
        customerCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        customerCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setCreditLimit(1);
        customerCreationEntity.setCustomerName("Customer Name");
        customerCreationEntity.setDiscount(3);
        customerCreationEntity.setDueDays(1);
        customerCreationEntity.setGstNo("Gst No");
        customerCreationEntity.setGstType("Gst Type");
        customerCreationEntity.setId(123L);
        customerCreationEntity.setMobileNo("Mobile No");
        customerCreationEntity.setModeOfPayment("Mode Of Payment");
        customerCreationEntity.setOpeningBal(1);
        customerCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setRateSlab(1);
        customerCreationEntity.setStatus("Status");
        customerCreationEntity.setUpdated_by("2020-03-01");
        customerCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));
        when(modelMapper.map((Object) any(), (Class<CustomerCreationEntity>) any())).thenReturn(customerCreationEntity);

        // Act
        CustomerCreationEntity actualCustomerCreationConvertToEntityResult = dTOTransfomers
                .customerCreationConvertToEntity(new CustomerCreationDTO());

        // Assert
        assertSame(customerCreationEntity, actualCustomerCreationConvertToEntityResult);
        assertEquals("1970-01-02", actualCustomerCreationConvertToEntityResult.getOpeningBalDate().toString());
        verify(dateConvertUtils).convertStringToDate((String) any());
        verify(modelMapper).map((Object) any(), (Class<CustomerCreationEntity>) any());
    }


    /**
     * Method under test: {@link DTOTransfomers#customerCreationConvertToEntityList(List)}
     */
    @Test
    void testCustomerCreationConvertToEntityList() throws ParseException {
        // Arrange, Act and Assert
        assertTrue(dTOTransfomers.customerCreationConvertToEntityList(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link DTOTransfomers#customerCreationConvertToEntityList(List)}
     */
    @Test
    void testCustomerCreationConvertToEntityList2() throws ParseException {
        // Arrange
        CustomerCreationEntity customerCreationEntity = new CustomerCreationEntity();
        customerCreationEntity.setAddress1("42 Main St");
        customerCreationEntity.setAddress2("42 Main St");
        customerCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        customerCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setCreditLimit(1);
        customerCreationEntity.setCustomerName("Customer Name");
        customerCreationEntity.setDiscount(3);
        customerCreationEntity.setDueDays(1);
        customerCreationEntity.setGstNo("Gst No");
        customerCreationEntity.setGstType("Gst Type");
        customerCreationEntity.setId(123L);
        customerCreationEntity.setMobileNo("Mobile No");
        customerCreationEntity.setModeOfPayment("Mode Of Payment");
        customerCreationEntity.setOpeningBal(1);
        customerCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setRateSlab(1);
        customerCreationEntity.setStatus("Status");
        customerCreationEntity.setUpdated_by("2020-03-01");
        customerCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));
        when(modelMapper.map((Object) any(), (Class<CustomerCreationEntity>) any())).thenReturn(customerCreationEntity);

        ArrayList<CustomerCreationDTO> customerCreationDTOList = new ArrayList<>();
        customerCreationDTOList.add(new CustomerCreationDTO());

        // Act and Assert
        assertEquals(1, dTOTransfomers.customerCreationConvertToEntityList(customerCreationDTOList).size());
        verify(modelMapper).map((Object) any(), (Class<CustomerCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#customerCreationConvertToEntityList(List)}
     */
    @Test
    void testCustomerCreationConvertToEntityList3() throws ParseException {
        // Arrange
        CustomerCreationEntity customerCreationEntity = new CustomerCreationEntity();
        customerCreationEntity.setAddress1("42 Main St");
        customerCreationEntity.setAddress2("42 Main St");
        customerCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        customerCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setCreditLimit(1);
        customerCreationEntity.setCustomerName("Customer Name");
        customerCreationEntity.setDiscount(3);
        customerCreationEntity.setDueDays(1);
        customerCreationEntity.setGstNo("Gst No");
        customerCreationEntity.setGstType("Gst Type");
        customerCreationEntity.setId(123L);
        customerCreationEntity.setMobileNo("Mobile No");
        customerCreationEntity.setModeOfPayment("Mode Of Payment");
        customerCreationEntity.setOpeningBal(1);
        customerCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setRateSlab(1);
        customerCreationEntity.setStatus("Status");
        customerCreationEntity.setUpdated_by("2020-03-01");
        customerCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));
        when(modelMapper.map((Object) any(), (Class<CustomerCreationEntity>) any())).thenReturn(customerCreationEntity);

        ArrayList<CustomerCreationDTO> customerCreationDTOList = new ArrayList<>();
        customerCreationDTOList.add(new CustomerCreationDTO());
        customerCreationDTOList.add(new CustomerCreationDTO());

        // Act and Assert
        assertEquals(2, dTOTransfomers.customerCreationConvertToEntityList(customerCreationDTOList).size());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<CustomerCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#discountSlabConvertToDto(DiscountSlabCreationEntity)}
     */
    @Test
    void testDiscountSlabConvertToDto() {
        // Arrange
        DiscountSlabCreationDTO discountSlabCreationDTO = new DiscountSlabCreationDTO();
        when(modelMapper.map((Object) any(), (Class<DiscountSlabCreationDTO>) any())).thenReturn(discountSlabCreationDTO);

        DiscountSlabCreationEntity discountSlabCreationEntity = new DiscountSlabCreationEntity();
        discountSlabCreationEntity.setDiscount("3");
        discountSlabCreationEntity.setDiscountSlabName("3");
        discountSlabCreationEntity.setFrom_amt(1);
        discountSlabCreationEntity.setId(123L);
        discountSlabCreationEntity.setStatus("Status");
        discountSlabCreationEntity.setTo_amt(1);

        // Act and Assert
        assertSame(discountSlabCreationDTO, dTOTransfomers.discountSlabConvertToDto(discountSlabCreationEntity));
        verify(modelMapper).map((Object) any(), (Class<DiscountSlabCreationDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#discountConvertToEntity(DiscountSlabCreationDTO)}
     */
    @Test
    void testDiscountConvertToEntity() throws ParseException {
        // Arrange
        DiscountSlabCreationEntity discountSlabCreationEntity = new DiscountSlabCreationEntity();
        discountSlabCreationEntity.setDiscount("3");
        discountSlabCreationEntity.setDiscountSlabName("3");
        discountSlabCreationEntity.setFrom_amt(1);
        discountSlabCreationEntity.setId(123L);
        discountSlabCreationEntity.setStatus("Status");
        discountSlabCreationEntity.setTo_amt(1);
        when(modelMapper.map((Object) any(), (Class<DiscountSlabCreationEntity>) any()))
                .thenReturn(discountSlabCreationEntity);

        // Act and Assert
        assertSame(discountSlabCreationEntity, dTOTransfomers.discountConvertToEntity(new DiscountSlabCreationDTO()));
        verify(modelMapper).map((Object) any(), (Class<DiscountSlabCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#discountConvertToEntityList(List)}
     */
    @Test
    void testDiscountConvertToEntityList() throws ParseException {
        // Arrange, Act and Assert
        assertTrue(dTOTransfomers.discountConvertToEntityList(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link DTOTransfomers#discountConvertToEntityList(List)}
     */
    @Test
    void testDiscountConvertToEntityList2() throws ParseException {
        // Arrange
        DiscountSlabCreationEntity discountSlabCreationEntity = new DiscountSlabCreationEntity();
        discountSlabCreationEntity.setDiscount("3");
        discountSlabCreationEntity.setDiscountSlabName("3");
        discountSlabCreationEntity.setFrom_amt(1);
        discountSlabCreationEntity.setId(123L);
        discountSlabCreationEntity.setStatus("Status");
        discountSlabCreationEntity.setTo_amt(1);
        when(modelMapper.map((Object) any(), (Class<DiscountSlabCreationEntity>) any()))
                .thenReturn(discountSlabCreationEntity);

        ArrayList<DiscountSlabCreationDTO> discountSlabCreationDTOList = new ArrayList<>();
        discountSlabCreationDTOList.add(new DiscountSlabCreationDTO());

        // Act and Assert
        assertEquals(1, dTOTransfomers.discountConvertToEntityList(discountSlabCreationDTOList).size());
        verify(modelMapper).map((Object) any(), (Class<DiscountSlabCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#discountConvertToEntityList(List)}
     */
    @Test
    void testDiscountConvertToEntityList3() throws ParseException {
        // Arrange
        DiscountSlabCreationEntity discountSlabCreationEntity = new DiscountSlabCreationEntity();
        discountSlabCreationEntity.setDiscount("3");
        discountSlabCreationEntity.setDiscountSlabName("3");
        discountSlabCreationEntity.setFrom_amt(1);
        discountSlabCreationEntity.setId(123L);
        discountSlabCreationEntity.setStatus("Status");
        discountSlabCreationEntity.setTo_amt(1);
        when(modelMapper.map((Object) any(), (Class<DiscountSlabCreationEntity>) any()))
                .thenReturn(discountSlabCreationEntity);

        ArrayList<DiscountSlabCreationDTO> discountSlabCreationDTOList = new ArrayList<>();
        discountSlabCreationDTOList.add(new DiscountSlabCreationDTO());
        discountSlabCreationDTOList.add(new DiscountSlabCreationDTO());

        // Act and Assert
        assertEquals(2, dTOTransfomers.discountConvertToEntityList(discountSlabCreationDTOList).size());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<DiscountSlabCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#salesManConvertToDto(SalesManCreationEntity)}
     */
    @Test
    void testSalesManConvertToDto() {
        // Arrange
        SalesManCreationDTO salesManCreationDTO = new SalesManCreationDTO();
        when(modelMapper.map((Object) any(), (Class<SalesManCreationDTO>) any())).thenReturn(salesManCreationDTO);

        AreaCreationEntity areaCreationEntity = new AreaCreationEntity();
        areaCreationEntity.setAreaName("Area Name");
        areaCreationEntity.setId(123L);
        areaCreationEntity.setRoute("Route");
        areaCreationEntity.setStatus("Status");

        SalesManCreationEntity salesManCreationEntity = new SalesManCreationEntity();
        salesManCreationEntity.setAreaCreation(areaCreationEntity);
        salesManCreationEntity.setId(123L);
        salesManCreationEntity.setIncentive(1);
        salesManCreationEntity.setSalesManName("Sales Man Name");
        salesManCreationEntity.setStatus("Status");
        salesManCreationEntity.setTarget("Target");

        // Act and Assert
        assertSame(salesManCreationDTO, dTOTransfomers.salesManConvertToDto(salesManCreationEntity));
        verify(modelMapper).map((Object) any(), (Class<SalesManCreationDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#salesManConvertToEntity(SalesManCreationDTO)}
     */
    @Test
    void testSalesManConvertToEntity() throws ParseException {
        // Arrange
        AreaCreationEntity areaCreationEntity = new AreaCreationEntity();
        areaCreationEntity.setAreaName("Area Name");
        areaCreationEntity.setId(123L);
        areaCreationEntity.setRoute("Route");
        areaCreationEntity.setStatus("Status");

        SalesManCreationEntity salesManCreationEntity = new SalesManCreationEntity();
        salesManCreationEntity.setAreaCreation(areaCreationEntity);
        salesManCreationEntity.setId(123L);
        salesManCreationEntity.setIncentive(1);
        salesManCreationEntity.setSalesManName("Sales Man Name");
        salesManCreationEntity.setStatus("Status");
        salesManCreationEntity.setTarget("Target");
        when(modelMapper.map((Object) any(), (Class<SalesManCreationEntity>) any())).thenReturn(salesManCreationEntity);

        // Act and Assert
        assertSame(salesManCreationEntity, dTOTransfomers.salesManConvertToEntity(new SalesManCreationDTO()));
        verify(modelMapper).map((Object) any(), (Class<SalesManCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#salesManConvertToEntityList(List)}
     */
    @Test
    void testSalesManConvertToEntityList() throws ParseException {
        // Arrange, Act and Assert
        assertTrue(dTOTransfomers.salesManConvertToEntityList(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link DTOTransfomers#salesManConvertToEntityList(List)}
     */
    @Test
    void testSalesManConvertToEntityList2() throws ParseException {
        // Arrange
        AreaCreationEntity areaCreationEntity = new AreaCreationEntity();
        areaCreationEntity.setAreaName("Area Name");
        areaCreationEntity.setId(123L);
        areaCreationEntity.setRoute("Route");
        areaCreationEntity.setStatus("Status");

        SalesManCreationEntity salesManCreationEntity = new SalesManCreationEntity();
        salesManCreationEntity.setAreaCreation(areaCreationEntity);
        salesManCreationEntity.setId(123L);
        salesManCreationEntity.setIncentive(1);
        salesManCreationEntity.setSalesManName("Sales Man Name");
        salesManCreationEntity.setStatus("Status");
        salesManCreationEntity.setTarget("Target");
        when(modelMapper.map((Object) any(), (Class<SalesManCreationEntity>) any())).thenReturn(salesManCreationEntity);

        ArrayList<SalesManCreationDTO> salesManCreationDTOList = new ArrayList<>();
        salesManCreationDTOList.add(new SalesManCreationDTO());

        // Act and Assert
        assertEquals(1, dTOTransfomers.salesManConvertToEntityList(salesManCreationDTOList).size());
        verify(modelMapper).map((Object) any(), (Class<SalesManCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#salesManConvertToEntityList(List)}
     */
    @Test
    void testSalesManConvertToEntityList3() throws ParseException {
        // Arrange
        AreaCreationEntity areaCreationEntity = new AreaCreationEntity();
        areaCreationEntity.setAreaName("Area Name");
        areaCreationEntity.setId(123L);
        areaCreationEntity.setRoute("Route");
        areaCreationEntity.setStatus("Status");

        SalesManCreationEntity salesManCreationEntity = new SalesManCreationEntity();
        salesManCreationEntity.setAreaCreation(areaCreationEntity);
        salesManCreationEntity.setId(123L);
        salesManCreationEntity.setIncentive(1);
        salesManCreationEntity.setSalesManName("Sales Man Name");
        salesManCreationEntity.setStatus("Status");
        salesManCreationEntity.setTarget("Target");
        when(modelMapper.map((Object) any(), (Class<SalesManCreationEntity>) any())).thenReturn(salesManCreationEntity);

        ArrayList<SalesManCreationDTO> salesManCreationDTOList = new ArrayList<>();
        salesManCreationDTOList.add(new SalesManCreationDTO());
        salesManCreationDTOList.add(new SalesManCreationDTO());

        // Act and Assert
        assertEquals(2, dTOTransfomers.salesManConvertToEntityList(salesManCreationDTOList).size());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<SalesManCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#groupCreationConvertToDto(GroupCreationEntity)}
     */
    @Test
    void testGroupCreationConvertToDto() {
        // Arrange
        GroupCreationDTO groupCreationDTO = new GroupCreationDTO();
        when(modelMapper.map((Object) any(), (Class<GroupCreationDTO>) any())).thenReturn(groupCreationDTO);

        GroupCreationEntity groupCreationEntity = new GroupCreationEntity();
        groupCreationEntity.setGroupName("Group Name");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("Status");

        // Act and Assert
        assertSame(groupCreationDTO, dTOTransfomers.groupCreationConvertToDto(groupCreationEntity));
        verify(modelMapper).map((Object) any(), (Class<GroupCreationDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#groupConvertToEntity(GroupCreationDTO)}
     */
    @Test
    void testGroupConvertToEntity() throws ParseException {
        // Arrange
        GroupCreationEntity groupCreationEntity = new GroupCreationEntity();
        groupCreationEntity.setGroupName("Group Name");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<GroupCreationEntity>) any())).thenReturn(groupCreationEntity);

        // Act and Assert
        assertSame(groupCreationEntity, dTOTransfomers.groupConvertToEntity(new GroupCreationDTO()));
        verify(modelMapper).map((Object) any(), (Class<GroupCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#groupConvertToEntityList(List)}
     */
    @Test
    void testGroupConvertToEntityList() throws ParseException {
        // Arrange, Act and Assert
        assertTrue(dTOTransfomers.groupConvertToEntityList(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link DTOTransfomers#groupConvertToEntityList(List)}
     */
    @Test
    void testGroupConvertToEntityList2() throws ParseException {
        // Arrange
        GroupCreationEntity groupCreationEntity = new GroupCreationEntity();
        groupCreationEntity.setGroupName("Group Name");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<GroupCreationEntity>) any())).thenReturn(groupCreationEntity);

        ArrayList<GroupCreationDTO> groupCreationDTOList = new ArrayList<>();
        groupCreationDTOList.add(new GroupCreationDTO());

        // Act and Assert
        assertEquals(1, dTOTransfomers.groupConvertToEntityList(groupCreationDTOList).size());
        verify(modelMapper).map((Object) any(), (Class<GroupCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#groupConvertToEntityList(List)}
     */
    @Test
    void testGroupConvertToEntityList3() throws ParseException {
        // Arrange
        GroupCreationEntity groupCreationEntity = new GroupCreationEntity();
        groupCreationEntity.setGroupName("Group Name");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<GroupCreationEntity>) any())).thenReturn(groupCreationEntity);

        ArrayList<GroupCreationDTO> groupCreationDTOList = new ArrayList<>();
        groupCreationDTOList.add(new GroupCreationDTO());
        groupCreationDTOList.add(new GroupCreationDTO());

        // Act and Assert
        assertEquals(2, dTOTransfomers.groupConvertToEntityList(groupCreationDTOList).size());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<GroupCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#gstTypeCreationConvertToDto(GSTTypeCreationEntity)}
     */
    @Test
    void testGstTypeCreationConvertToDto() {
        // Arrange
        GSTTypeCreationDTO gstTypeCreationDTO = new GSTTypeCreationDTO();
        when(modelMapper.map((Object) any(), (Class<GSTTypeCreationDTO>) any())).thenReturn(gstTypeCreationDTO);

        GSTTypeCreationEntity gstTypeCreationEntity = new GSTTypeCreationEntity();
        gstTypeCreationEntity.setGstTypeName("Gst Type Name");
        gstTypeCreationEntity.setId(123L);
        gstTypeCreationEntity.setStatus("Status");

        // Act and Assert
        assertSame(gstTypeCreationDTO, dTOTransfomers.gstTypeCreationConvertToDto(gstTypeCreationEntity));
        verify(modelMapper).map((Object) any(), (Class<GSTTypeCreationDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#gstTypeConvertToEntity(GSTTypeCreationDTO)}
     */
    @Test
    void testGstTypeConvertToEntity() throws ParseException {
        // Arrange
        GSTTypeCreationEntity gstTypeCreationEntity = new GSTTypeCreationEntity();
        gstTypeCreationEntity.setGstTypeName("Gst Type Name");
        gstTypeCreationEntity.setId(123L);
        gstTypeCreationEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<GSTTypeCreationEntity>) any())).thenReturn(gstTypeCreationEntity);

        // Act and Assert
        assertSame(gstTypeCreationEntity, dTOTransfomers.gstTypeConvertToEntity(new GSTTypeCreationDTO()));
        verify(modelMapper).map((Object) any(), (Class<GSTTypeCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#hsnCreationConvertToDto(HsnSacCreationEntity)}
     */
    @Test
    void testHsnCreationConvertToDto() {
        // Arrange
        HsnSacCreationDTO hsnSacCreationDTO = new HsnSacCreationDTO();
        when(modelMapper.map((Object) any(), (Class<HsnSacCreationDTO>) any())).thenReturn(hsnSacCreationDTO);

        HsnSacCreationEntity hsnSacCreationEntity = new HsnSacCreationEntity();
        hsnSacCreationEntity.setDescirption("Descirption");
        hsnSacCreationEntity.setHsnName("Hsn Name");
        hsnSacCreationEntity.setId(123L);
        hsnSacCreationEntity.setStatus("Status");

        // Act and Assert
        assertSame(hsnSacCreationDTO, dTOTransfomers.hsnCreationConvertToDto(hsnSacCreationEntity));
        verify(modelMapper).map((Object) any(), (Class<HsnSacCreationDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#hsnConvertToEntity(HsnSacCreationDTO)}
     */
    @Test
    void testHsnConvertToEntity() throws ParseException {
        // Arrange
        HsnSacCreationEntity hsnSacCreationEntity = new HsnSacCreationEntity();
        hsnSacCreationEntity.setDescirption("Descirption");
        hsnSacCreationEntity.setHsnName("Hsn Name");
        hsnSacCreationEntity.setId(123L);
        hsnSacCreationEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<HsnSacCreationEntity>) any())).thenReturn(hsnSacCreationEntity);

        // Act and Assert
        assertSame(hsnSacCreationEntity, dTOTransfomers.hsnConvertToEntity(new HsnSacCreationDTO()));
        verify(modelMapper).map((Object) any(), (Class<HsnSacCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#hsnConvertToEntityList(List)}
     */
    @Test
    void testHsnConvertToEntityList() throws ParseException {
        // Arrange, Act and Assert
        assertTrue(dTOTransfomers.hsnConvertToEntityList(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link DTOTransfomers#hsnConvertToEntityList(List)}
     */
    @Test
    void testHsnConvertToEntityList2() throws ParseException {
        // Arrange
        HsnSacCreationEntity hsnSacCreationEntity = new HsnSacCreationEntity();
        hsnSacCreationEntity.setDescirption("Descirption");
        hsnSacCreationEntity.setHsnName("Hsn Name");
        hsnSacCreationEntity.setId(123L);
        hsnSacCreationEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<HsnSacCreationEntity>) any())).thenReturn(hsnSacCreationEntity);

        ArrayList<HsnSacCreationDTO> hsnSacCreationDTOList = new ArrayList<>();
        hsnSacCreationDTOList.add(new HsnSacCreationDTO());

        // Act and Assert
        assertEquals(1, dTOTransfomers.hsnConvertToEntityList(hsnSacCreationDTOList).size());
        verify(modelMapper).map((Object) any(), (Class<HsnSacCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#hsnConvertToEntityList(List)}
     */
    @Test
    void testHsnConvertToEntityList3() throws ParseException {
        // Arrange
        HsnSacCreationEntity hsnSacCreationEntity = new HsnSacCreationEntity();
        hsnSacCreationEntity.setDescirption("Descirption");
        hsnSacCreationEntity.setHsnName("Hsn Name");
        hsnSacCreationEntity.setId(123L);
        hsnSacCreationEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<HsnSacCreationEntity>) any())).thenReturn(hsnSacCreationEntity);

        ArrayList<HsnSacCreationDTO> hsnSacCreationDTOList = new ArrayList<>();
        hsnSacCreationDTOList.add(new HsnSacCreationDTO());
        hsnSacCreationDTOList.add(new HsnSacCreationDTO());

        // Act and Assert
        assertEquals(2, dTOTransfomers.hsnConvertToEntityList(hsnSacCreationDTOList).size());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<HsnSacCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#compositionCreationConvertToDto(CompostionCreationEntity)}
     */
    @Test
    void testCompositionCreationConvertToDto() {
        // Arrange
        CompostionCreationDTO compostionCreationDTO = new CompostionCreationDTO();
        when(modelMapper.map((Object) any(), (Class<CompostionCreationDTO>) any())).thenReturn(compostionCreationDTO);

        CompostionCreationEntity compostionCreationEntity = new CompostionCreationEntity();
        compostionCreationEntity.setCompName("Comp Name");
        compostionCreationEntity.setId(123L);
        compostionCreationEntity.setStatus("Status");

        // Act and Assert
        assertSame(compostionCreationDTO, dTOTransfomers.compositionCreationConvertToDto(compostionCreationEntity));
        verify(modelMapper).map((Object) any(), (Class<CompostionCreationDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#compositionConvertToEntity(CompostionCreationDTO)}
     */
    @Test
    void testCompositionConvertToEntity() throws ParseException {
        // Arrange
        CompostionCreationEntity compostionCreationEntity = new CompostionCreationEntity();
        compostionCreationEntity.setCompName("Comp Name");
        compostionCreationEntity.setId(123L);
        compostionCreationEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<CompostionCreationEntity>) any()))
                .thenReturn(compostionCreationEntity);

        // Act and Assert
        assertSame(compostionCreationEntity, dTOTransfomers.compositionConvertToEntity(new CompostionCreationDTO()));
        verify(modelMapper).map((Object) any(), (Class<CompostionCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#compisitionConvertToEntityList(List)}
     */
    @Test
    void testCompisitionConvertToEntityList() throws ParseException {
        // Arrange, Act and Assert
        assertTrue(dTOTransfomers.compisitionConvertToEntityList(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link DTOTransfomers#compisitionConvertToEntityList(List)}
     */
    @Test
    void testCompisitionConvertToEntityList2() throws ParseException {
        // Arrange
        CompostionCreationEntity compostionCreationEntity = new CompostionCreationEntity();
        compostionCreationEntity.setCompName("Comp Name");
        compostionCreationEntity.setId(123L);
        compostionCreationEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<CompostionCreationEntity>) any()))
                .thenReturn(compostionCreationEntity);

        ArrayList<CompostionCreationDTO> compostionCreationDTOList = new ArrayList<>();
        compostionCreationDTOList.add(new CompostionCreationDTO());

        // Act and Assert
        assertEquals(1, dTOTransfomers.compisitionConvertToEntityList(compostionCreationDTOList).size());
        verify(modelMapper).map((Object) any(), (Class<CompostionCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#compisitionConvertToEntityList(List)}
     */
    @Test
    void testCompisitionConvertToEntityList3() throws ParseException {
        // Arrange
        CompostionCreationEntity compostionCreationEntity = new CompostionCreationEntity();
        compostionCreationEntity.setCompName("Comp Name");
        compostionCreationEntity.setId(123L);
        compostionCreationEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<CompostionCreationEntity>) any()))
                .thenReturn(compostionCreationEntity);

        ArrayList<CompostionCreationDTO> compostionCreationDTOList = new ArrayList<>();
        compostionCreationDTOList.add(new CompostionCreationDTO());
        compostionCreationDTOList.add(new CompostionCreationDTO());

        // Act and Assert
        assertEquals(2, dTOTransfomers.compisitionConvertToEntityList(compostionCreationDTOList).size());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<CompostionCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#itemCreationConvertToDto(ItemCreationEntity)}
     */
    @Test
    void testItemCreationConvertToDto() {
        // Arrange
        ItemCreationDTO itemCreationDTO = new ItemCreationDTO();
        when(modelMapper.map((Object) any(), (Class<ItemCreationDTO>) any())).thenReturn(itemCreationDTO);

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

        // Act and Assert
        assertSame(itemCreationDTO, dTOTransfomers.itemCreationConvertToDto(itemCreationEntity));
        verify(modelMapper).map((Object) any(), (Class<ItemCreationDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#itemConvertToEntity(ItemCreationDTO)}
     */
    @Test
    void testItemConvertToEntity() throws ParseException {
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
        when(modelMapper.map((Object) any(), (Class<ItemCreationEntity>) any())).thenReturn(itemCreationEntity);

        // Act and Assert
        assertSame(itemCreationEntity, dTOTransfomers.itemConvertToEntity(new ItemCreationDTO()));
        verify(modelMapper).map((Object) any(), (Class<ItemCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#itemConvertToEntityList(List)}
     */
    @Test
    void testItemConvertToEntityList() throws ParseException {
        // Arrange, Act and Assert
        assertTrue(dTOTransfomers.itemConvertToEntityList(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link DTOTransfomers#itemConvertToEntityList(List)}
     */
    @Test
    void testItemConvertToEntityList2() throws ParseException {
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
        when(modelMapper.map((Object) any(), (Class<ItemCreationEntity>) any())).thenReturn(itemCreationEntity);

        ArrayList<ItemCreationDTO> itemCreationDTOList = new ArrayList<>();
        itemCreationDTOList.add(new ItemCreationDTO());

        // Act and Assert
        assertEquals(1, dTOTransfomers.itemConvertToEntityList(itemCreationDTOList).size());
        verify(modelMapper).map((Object) any(), (Class<ItemCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#manufactureCreationConvertToDto(ManufacturerCreationEntity)}
     */
    @Test
    void testManufactureCreationConvertToDto() {
        // Arrange
        ManufacturerCreationDTO manufacturerCreationDTO = new ManufacturerCreationDTO();
        when(modelMapper.map((Object) any(), (Class<ManufacturerCreationDTO>) any())).thenReturn(manufacturerCreationDTO);

        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity.setStatus("Status");

        // Act and Assert
        assertSame(manufacturerCreationDTO, dTOTransfomers.manufactureCreationConvertToDto(manufacturerCreationEntity));
        verify(modelMapper).map((Object) any(), (Class<ManufacturerCreationDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#manufactureConvertToEntity(ManufacturerCreationDTO)}
     */
    @Test
    void testManufactureConvertToEntity() throws ParseException {
        // Arrange
        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<ManufacturerCreationEntity>) any()))
                .thenReturn(manufacturerCreationEntity);

        // Act and Assert
        assertSame(manufacturerCreationEntity, dTOTransfomers.manufactureConvertToEntity(new ManufacturerCreationDTO()));
        verify(modelMapper).map((Object) any(), (Class<ManufacturerCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#manufactureConvertToEntityList(List)}
     */
    @Test
    void testManufactureConvertToEntityList() throws ParseException {
        // Arrange, Act and Assert
        assertTrue(dTOTransfomers.manufactureConvertToEntityList(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link DTOTransfomers#manufactureConvertToEntityList(List)}
     */
    @Test
    void testManufactureConvertToEntityList2() throws ParseException {
        // Arrange
        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<ManufacturerCreationEntity>) any()))
                .thenReturn(manufacturerCreationEntity);

        ArrayList<ManufacturerCreationDTO> manufacturerCreationDTOList = new ArrayList<>();
        manufacturerCreationDTOList.add(new ManufacturerCreationDTO());

        // Act and Assert
        assertEquals(1, dTOTransfomers.manufactureConvertToEntityList(manufacturerCreationDTOList).size());
        verify(modelMapper).map((Object) any(), (Class<ManufacturerCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#manufactureConvertToEntityList(List)}
     */
    @Test
    void testManufactureConvertToEntityList3() throws ParseException {
        // Arrange
        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<ManufacturerCreationEntity>) any()))
                .thenReturn(manufacturerCreationEntity);

        ArrayList<ManufacturerCreationDTO> manufacturerCreationDTOList = new ArrayList<>();
        manufacturerCreationDTOList.add(new ManufacturerCreationDTO());
        manufacturerCreationDTOList.add(new ManufacturerCreationDTO());

        // Act and Assert
        assertEquals(2, dTOTransfomers.manufactureConvertToEntityList(manufacturerCreationDTOList).size());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<ManufacturerCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#openingStockConvertToDto(OpenStockEntryEntity)}
     */
    @Test
    void testOpeningStockConvertToDto() {
        // Arrange
        OpenStockEntryDTO openStockEntryDTO = new OpenStockEntryDTO();
        when(modelMapper.map((Object) any(), (Class<OpenStockEntryDTO>) any())).thenReturn(openStockEntryDTO);

        OpenStockEntryEntity openStockEntryEntity = new OpenStockEntryEntity();
        openStockEntryEntity.setDelStatus("Del Status");
        openStockEntryEntity.setId(123L);
        openStockEntryEntity.setMrpAmount(10.0d);
        openStockEntryEntity.setOpenStockDetails(new ArrayList<>());
        openStockEntryEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        openStockEntryEntity.setPurchaseVal(10.0d);
        openStockEntryEntity.setSalesVal(10.0d);
        openStockEntryEntity.setSrt(10.0d);
        openStockEntryEntity.setStatus("Status");
        openStockEntryEntity.setTotalItems(1000);

        // Act and Assert
        assertSame(openStockEntryDTO, dTOTransfomers.openingStockConvertToDto(openStockEntryEntity));
        verify(modelMapper).map((Object) any(), (Class<OpenStockEntryDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#openingStockConvertToEntity(OpenStockEntryDTO)}
     */
    @Test
    void testOpeningStockConvertToEntity() throws ParseException {
        // Arrange
        when(dateConvertUtils.convertStringToDate((String) any())).thenReturn(LocalDate.ofEpochDay(1L));

        OpenStockEntryEntity openStockEntryEntity = new OpenStockEntryEntity();
        openStockEntryEntity.setDelStatus("Del Status");
        openStockEntryEntity.setId(123L);
        openStockEntryEntity.setMrpAmount(10.0d);
        openStockEntryEntity.setOpenStockDetails(new ArrayList<>());
        openStockEntryEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        openStockEntryEntity.setPurchaseVal(10.0d);
        openStockEntryEntity.setSalesVal(10.0d);
        openStockEntryEntity.setSrt(10.0d);
        openStockEntryEntity.setStatus("Status");
        openStockEntryEntity.setTotalItems(1000);
        when(modelMapper.map((Object) any(), (Class<OpenStockEntryEntity>) any())).thenReturn(openStockEntryEntity);

        // Act
        OpenStockEntryEntity actualOpeningStockConvertToEntityResult = dTOTransfomers
                .openingStockConvertToEntity(new OpenStockEntryDTO());

        // Assert
        assertSame(openStockEntryEntity, actualOpeningStockConvertToEntityResult);
        assertEquals("1970-01-02", actualOpeningStockConvertToEntityResult.getOrderDate().toString());
        verify(dateConvertUtils).convertStringToDate((String) any());
        verify(modelMapper).map((Object) any(), (Class<OpenStockEntryEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#orderGenerateConvertToDto(OrderGenerationEntity)}
     */
    @Test
    void testOrderGenerateConvertToDto() {
        // Arrange
        OrderGenerationDTO orderGenerationDTO = new OrderGenerationDTO();
        when(modelMapper.map((Object) any(), (Class<OrderGenerationDTO>) any())).thenReturn(orderGenerationDTO);

        OrderGenerationEntity orderGenerationEntity = new OrderGenerationEntity();
        orderGenerationEntity.setId(123L);
        orderGenerationEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        orderGenerationEntity.setOrderDetails(new ArrayList<>());
        orderGenerationEntity.setOrderQty(1L);
        orderGenerationEntity.setStatus("Status");
        orderGenerationEntity.setTotalCount(3L);

        // Act and Assert
        assertSame(orderGenerationDTO, dTOTransfomers.orderGenerateConvertToDto(orderGenerationEntity));
        verify(modelMapper).map((Object) any(), (Class<OrderGenerationDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#orderGenerateConvertToEntity(OrderGenerationDTO)}
     */
    @Test
    void testOrderGenerateConvertToEntity() throws ParseException {
        // Arrange
        when(dateConvertUtils.convertStringToDate((String) any())).thenReturn(LocalDate.ofEpochDay(1L));

        OrderGenerationEntity orderGenerationEntity = new OrderGenerationEntity();
        orderGenerationEntity.setId(123L);
        orderGenerationEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        orderGenerationEntity.setOrderDetails(new ArrayList<>());
        orderGenerationEntity.setOrderQty(1L);
        orderGenerationEntity.setStatus("Status");
        orderGenerationEntity.setTotalCount(3L);
        when(modelMapper.map((Object) any(), (Class<OrderGenerationEntity>) any())).thenReturn(orderGenerationEntity);

        // Act
        OrderGenerationEntity actualOrderGenerateConvertToEntityResult = dTOTransfomers
                .orderGenerateConvertToEntity(new OrderGenerationDTO());

        // Assert
        assertSame(orderGenerationEntity, actualOrderGenerateConvertToEntityResult);
        assertEquals("1970-01-02", actualOrderGenerateConvertToEntityResult.getOrderDate().toString());
        verify(dateConvertUtils).convertStringToDate((String) any());
        verify(modelMapper).map((Object) any(), (Class<OrderGenerationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#packCreateConvertToDto(PackCreationEntity)}
     */
    @Test
    void testPackCreateConvertToDto() {
        // Arrange
        PackCreationDTO packCreationDTO = new PackCreationDTO();
        when(modelMapper.map((Object) any(), (Class<PackCreationDTO>) any())).thenReturn(packCreationDTO);

        PackCreationEntity packCreationEntity = new PackCreationEntity();
        packCreationEntity.setId(123L);
        packCreationEntity.setPackName("Pack Name");
        packCreationEntity.setQty(1);
        packCreationEntity.setStatus("Status");

        // Act and Assert
        assertSame(packCreationDTO, dTOTransfomers.packCreateConvertToDto(packCreationEntity));
        verify(modelMapper).map((Object) any(), (Class<PackCreationDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#packCreateConvertToEntity(PackCreationDTO)}
     */
    @Test
    void testPackCreateConvertToEntity() throws ParseException {
        // Arrange
        PackCreationEntity packCreationEntity = new PackCreationEntity();
        packCreationEntity.setId(123L);
        packCreationEntity.setPackName("Pack Name");
        packCreationEntity.setQty(1);
        packCreationEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<PackCreationEntity>) any())).thenReturn(packCreationEntity);

        // Act and Assert
        assertSame(packCreationEntity, dTOTransfomers.packCreateConvertToEntity(new PackCreationDTO()));
        verify(modelMapper).map((Object) any(), (Class<PackCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#packCreateConvertToEntityList(List)}
     */
    @Test
    void testPackCreateConvertToEntityList() throws ParseException {
        // Arrange, Act and Assert
        assertTrue(dTOTransfomers.packCreateConvertToEntityList(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link DTOTransfomers#packCreateConvertToEntityList(List)}
     */
    @Test
    void testPackCreateConvertToEntityList2() throws ParseException {
        // Arrange
        PackCreationEntity packCreationEntity = new PackCreationEntity();
        packCreationEntity.setId(123L);
        packCreationEntity.setPackName("Pack Name");
        packCreationEntity.setQty(1);
        packCreationEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<PackCreationEntity>) any())).thenReturn(packCreationEntity);

        ArrayList<PackCreationDTO> packCreationDTOList = new ArrayList<>();
        packCreationDTOList.add(new PackCreationDTO());

        // Act and Assert
        assertEquals(1, dTOTransfomers.packCreateConvertToEntityList(packCreationDTOList).size());
        verify(modelMapper).map((Object) any(), (Class<PackCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#packCreateConvertToEntityList(List)}
     */
    @Test
    void testPackCreateConvertToEntityList3() throws ParseException {
        // Arrange
        PackCreationEntity packCreationEntity = new PackCreationEntity();
        packCreationEntity.setId(123L);
        packCreationEntity.setPackName("Pack Name");
        packCreationEntity.setQty(1);
        packCreationEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<PackCreationEntity>) any())).thenReturn(packCreationEntity);

        ArrayList<PackCreationDTO> packCreationDTOList = new ArrayList<>();
        packCreationDTOList.add(new PackCreationDTO());
        packCreationDTOList.add(new PackCreationDTO());

        // Act and Assert
        assertEquals(2, dTOTransfomers.packCreateConvertToEntityList(packCreationDTOList).size());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<PackCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#purchaseEntryConvertToDto(PurchaseEntryEntity)}
     */
    @Test
    void testPurchaseEntryConvertToDto() {
        // Arrange
        PurchaseEntryDTO purchaseEntryDTO = new PurchaseEntryDTO();
        purchaseEntryDTO.setDisPercentage(10.0d);
        purchaseEntryDTO.setDiscAmount(10.0d);
        purchaseEntryDTO.setDiscount(10.0d);
        purchaseEntryDTO.setDuedate("2020-03-01");
        purchaseEntryDTO.setDueday(1);
        purchaseEntryDTO.setEntrydate("2020-03-01");
        purchaseEntryDTO.setFooterDate("2020-03-01");
        purchaseEntryDTO.setGrossAmounts(10.0d);
        purchaseEntryDTO.setGstamount(10.0d);
        purchaseEntryDTO.setId(123L);
        purchaseEntryDTO.setInvoiceDate("2020-03-01");
        purchaseEntryDTO.setInvoiceNo("Invoice No");
        purchaseEntryDTO.setNetAmount(10.0d);
        purchaseEntryDTO.setNoItems(1000);
        purchaseEntryDTO.setPurchaseDetails(new ArrayList<>());
        purchaseEntryDTO.setRoundAmount(10.0d);
        purchaseEntryDTO.setSrtMargin("Srt Margin");
        purchaseEntryDTO.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<PurchaseEntryDTO>) any())).thenReturn(purchaseEntryDTO);

        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("Gst No");
        supplierCreationEntity.setGstType("Gst Type");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("Mobile No");
        supplierCreationEntity.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("Rate Slab");
        supplierCreationEntity.setStatus("Status");
        supplierCreationEntity.setSupplierName("Supplier Name");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseEntryEntity purchaseEntryEntity = new PurchaseEntryEntity();
        purchaseEntryEntity.setDisPercentage(1L);
        purchaseEntryEntity.setDiscAmount(10.0d);
        purchaseEntryEntity.setDiscount(3L);
        purchaseEntryEntity.setDuedate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setDueday(1);
        purchaseEntryEntity.setEntrydate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setFooterDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setGrossAmounts(1L);
        purchaseEntryEntity.setGstamount(10L);
        purchaseEntryEntity.setId(123L);
        purchaseEntryEntity.setInvoiceDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setInvoiceNo("Invoice No");
        purchaseEntryEntity.setNetAmount(1L);
        purchaseEntryEntity.setNoItems(1000);
        purchaseEntryEntity.setPurchaseDetails(new ArrayList<>());
        purchaseEntryEntity.setRoundAmount(1L);
        purchaseEntryEntity.setSrtMargin("Srt Margin");
        purchaseEntryEntity.setSupplierDetails(supplierCreationEntity);
        purchaseEntryEntity.setSupplierName("Supplier Name");

        // Act and Assert
        assertSame(purchaseEntryDTO, dTOTransfomers.purchaseEntryConvertToDto(purchaseEntryEntity));
        verify(modelMapper).map((Object) any(), (Class<PurchaseEntryDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#purchaseEntryConvertToEntity(PurchaseEntryDTO)}
     */
    @Test
    void testPurchaseEntryConvertToEntity() throws ParseException {
        // Arrange
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        when(dateConvertUtils.convertStringToDate((String) any())).thenReturn(ofEpochDayResult);

        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("Gst No");
        supplierCreationEntity.setGstType("Gst Type");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("Mobile No");
        supplierCreationEntity.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("Rate Slab");
        supplierCreationEntity.setStatus("Status");
        supplierCreationEntity.setSupplierName("Supplier Name");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseEntryEntity purchaseEntryEntity = new PurchaseEntryEntity();
        purchaseEntryEntity.setDisPercentage(1L);
        purchaseEntryEntity.setDiscAmount(10.0d);
        purchaseEntryEntity.setDiscount(3L);
        purchaseEntryEntity.setDuedate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setDueday(1);
        purchaseEntryEntity.setEntrydate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setFooterDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setGrossAmounts(1L);
        purchaseEntryEntity.setGstamount(10L);
        purchaseEntryEntity.setId(123L);
        purchaseEntryEntity.setInvoiceDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setInvoiceNo("Invoice No");
        purchaseEntryEntity.setNetAmount(1L);
        purchaseEntryEntity.setNoItems(1000);
        purchaseEntryEntity.setPurchaseDetails(new ArrayList<>());
        purchaseEntryEntity.setRoundAmount(1L);
        purchaseEntryEntity.setSrtMargin("Srt Margin");
        purchaseEntryEntity.setSupplierDetails(supplierCreationEntity);
        purchaseEntryEntity.setSupplierName("Supplier Name");
        when(modelMapper.map((Object) any(), (Class<PurchaseEntryEntity>) any())).thenReturn(purchaseEntryEntity);

        PurchaseEntryDTO purchaseEntryDTO = new PurchaseEntryDTO();
        purchaseEntryDTO.setDisPercentage(10.0d);
        purchaseEntryDTO.setDiscAmount(10.0d);
        purchaseEntryDTO.setDiscount(10.0d);
        purchaseEntryDTO.setDuedate("2020-03-01");
        purchaseEntryDTO.setDueday(1);
        purchaseEntryDTO.setEntrydate("2020-03-01");
        purchaseEntryDTO.setFooterDate("2020-03-01");
        purchaseEntryDTO.setGrossAmounts(10.0d);
        purchaseEntryDTO.setGstamount(10.0d);
        purchaseEntryDTO.setId(123L);
        purchaseEntryDTO.setInvoiceDate("2020-03-01");
        purchaseEntryDTO.setInvoiceNo("Invoice No");
        purchaseEntryDTO.setNetAmount(10.0d);
        purchaseEntryDTO.setNoItems(1000);
        purchaseEntryDTO.setPurchaseDetails(new ArrayList<>());
        purchaseEntryDTO.setRoundAmount(10.0d);
        purchaseEntryDTO.setSrtMargin("Srt Margin");
        purchaseEntryDTO.setStatus("Status");

        // Act
        PurchaseEntryEntity actualPurchaseEntryConvertToEntityResult = dTOTransfomers
                .purchaseEntryConvertToEntity(purchaseEntryDTO);

        // Assert
        assertSame(purchaseEntryEntity, actualPurchaseEntryConvertToEntityResult);
        assertEquals("1970-01-02", actualPurchaseEntryConvertToEntityResult.getDuedate().toString());
        assertSame(ofEpochDayResult, actualPurchaseEntryConvertToEntityResult.getInvoiceDate());
        assertSame(ofEpochDayResult, actualPurchaseEntryConvertToEntityResult.getEntrydate());
        assertSame(ofEpochDayResult, actualPurchaseEntryConvertToEntityResult.getFooterDate());
        assertTrue(actualPurchaseEntryConvertToEntityResult.getPurchaseDetails().isEmpty());
        verify(dateConvertUtils, atLeast(1)).convertStringToDate((String) any());
        verify(modelMapper).map((Object) any(), (Class<PurchaseEntryEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#purchaseOrderConvertToDto(PurchaseOrderEntity)}
     */
    @Test
    void testPurchaseOrderConvertToDto() {
        // Arrange
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        when(modelMapper.map((Object) any(), (Class<PurchaseOrderDTO>) any())).thenReturn(purchaseOrderDTO);

        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("Gst No");
        supplierCreationEntity.setGstType("Gst Type");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("Mobile No");
        supplierCreationEntity.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("Rate Slab");
        supplierCreationEntity.setStatus("Status");
        supplierCreationEntity.setSupplierName("Supplier Name");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseOrderEntity purchaseOrderEntity = new PurchaseOrderEntity();
        purchaseOrderEntity.setId(123L);
        purchaseOrderEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        purchaseOrderEntity.setOrderQty(1L);
        purchaseOrderEntity.setPurchaseOrderDetails(new ArrayList<>());
        purchaseOrderEntity.setStatus("Status");
        purchaseOrderEntity.setSupplierDetails(supplierCreationEntity);
        purchaseOrderEntity.setSupplierName("Supplier Name");
        purchaseOrderEntity.setTotalCount(3L);

        // Act and Assert
        assertSame(purchaseOrderDTO, dTOTransfomers.purchaseOrderConvertToDto(purchaseOrderEntity));
        verify(modelMapper).map((Object) any(), (Class<PurchaseOrderDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#purchaseOrderConvertToEntity(PurchaseOrderDTO)}
     */
    @Test
    void testPurchaseOrderConvertToEntity() throws ParseException {
        // Arrange
        when(dateConvertUtils.convertStringToDate((String) any())).thenReturn(LocalDate.ofEpochDay(1L));

        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("Gst No");
        supplierCreationEntity.setGstType("Gst Type");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("Mobile No");
        supplierCreationEntity.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("Rate Slab");
        supplierCreationEntity.setStatus("Status");
        supplierCreationEntity.setSupplierName("Supplier Name");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseOrderEntity purchaseOrderEntity = new PurchaseOrderEntity();
        purchaseOrderEntity.setId(123L);
        purchaseOrderEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        purchaseOrderEntity.setOrderQty(1L);
        purchaseOrderEntity.setPurchaseOrderDetails(new ArrayList<>());
        purchaseOrderEntity.setStatus("Status");
        purchaseOrderEntity.setSupplierDetails(supplierCreationEntity);
        purchaseOrderEntity.setSupplierName("Supplier Name");
        purchaseOrderEntity.setTotalCount(3L);
        when(modelMapper.map((Object) any(), (Class<PurchaseOrderEntity>) any())).thenReturn(purchaseOrderEntity);

        // Act
        PurchaseOrderEntity actualPurchaseOrderConvertToEntityResult = dTOTransfomers
                .purchaseOrderConvertToEntity(new PurchaseOrderDTO());

        // Assert
        assertSame(purchaseOrderEntity, actualPurchaseOrderConvertToEntityResult);
        assertEquals("1970-01-02", actualPurchaseOrderConvertToEntityResult.getOrderDate().toString());
        verify(dateConvertUtils).convertStringToDate((String) any());
        verify(modelMapper).map((Object) any(), (Class<PurchaseOrderEntity>) any());
    }


    /**
     * Method under test: {@link DTOTransfomers#receiptConvertToDto(ReceiptEntryEntity)}
     */
    @Test
    void testReceiptConvertToDto() {
        // Arrange
        ReceiptEntryDTO receiptEntryDTO = new ReceiptEntryDTO();
        when(modelMapper.map((Object) any(), (Class<ReceiptEntryDTO>) any())).thenReturn(receiptEntryDTO);

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
        assertSame(receiptEntryDTO, dTOTransfomers.receiptConvertToDto(receiptEntryEntity));
        verify(modelMapper).map((Object) any(), (Class<ReceiptEntryDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#receiptConvertToEntity(ReceiptEntryDTO)}
     */
    @Test
    void testReceiptConvertToEntity() throws ParseException {
        // Arrange
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        when(dateConvertUtils.convertStringToDate((String) any())).thenReturn(ofEpochDayResult);

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
        when(modelMapper.map((Object) any(), (Class<ReceiptEntryEntity>) any())).thenReturn(receiptEntryEntity);

        // Act
        ReceiptEntryEntity actualReceiptConvertToEntityResult = dTOTransfomers
                .receiptConvertToEntity(new ReceiptEntryDTO());

        // Assert
        assertSame(receiptEntryEntity, actualReceiptConvertToEntityResult);
        assertEquals("1970-01-02", actualReceiptConvertToEntityResult.getEntryDate().toString());
        assertSame(ofEpochDayResult, actualReceiptConvertToEntityResult.getRefernceDate());
        verify(dateConvertUtils, atLeast(1)).convertStringToDate((String) any());
        verify(modelMapper).map((Object) any(), (Class<ReceiptEntryEntity>) any());
    }


    /**
     * Method under test: {@link DTOTransfomers#routeConvertToDto(RouteCreationEntity)}
     */
    @Test
    void testRouteConvertToDto() {
        // Arrange
        RouteCreationDTO routeCreationDTO = new RouteCreationDTO();
        when(modelMapper.map((Object) any(), (Class<RouteCreationDTO>) any())).thenReturn(routeCreationDTO);

        RouteCreationEntity routeCreationEntity = new RouteCreationEntity();
        routeCreationEntity.setId(123L);
        routeCreationEntity.setRouteName("Route Name");
        routeCreationEntity.setStatus("Status");

        // Act and Assert
        assertSame(routeCreationDTO, dTOTransfomers.routeConvertToDto(routeCreationEntity));
        verify(modelMapper).map((Object) any(), (Class<RouteCreationDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#routeConvertToEntity(RouteCreationDTO)}
     */
    @Test
    void testRouteConvertToEntity() throws ParseException {
        // Arrange
        RouteCreationEntity routeCreationEntity = new RouteCreationEntity();
        routeCreationEntity.setId(123L);
        routeCreationEntity.setRouteName("Route Name");
        routeCreationEntity.setStatus("Status");
        when(modelMapper.map((Object) any(), (Class<RouteCreationEntity>) any())).thenReturn(routeCreationEntity);

        // Act and Assert
        assertSame(routeCreationEntity, dTOTransfomers.routeConvertToEntity(new RouteCreationDTO()));
        verify(modelMapper).map((Object) any(), (Class<RouteCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#saleInvoiceConvertToDto(SalesInvoiceEntity)}
     */
    @Test
    void testSaleInvoiceConvertToDto() {
        // Arrange
        SalesInvoiceDTO salesInvoiceDTO = new SalesInvoiceDTO();
        when(modelMapper.map((Object) any(), (Class<SalesInvoiceDTO>) any())).thenReturn(salesInvoiceDTO);
        CustomerCreationEntity customerCreationEntity = new CustomerCreationEntity();
        customerCreationEntity.setAddress1("42 Main St");
        customerCreationEntity.setAddress2("42 Main St");
        customerCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        customerCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setCreditLimit(1);
        customerCreationEntity.setCustomerName("Customer Name");
        customerCreationEntity.setDiscount(3);
        customerCreationEntity.setDueDays(1);
        customerCreationEntity.setGstNo("Gst No");
        customerCreationEntity.setGstType("Gst Type");
        customerCreationEntity.setId(123L);
        customerCreationEntity.setMobileNo("Mobile No");
        customerCreationEntity.setModeOfPayment("Mode Of Payment");
        customerCreationEntity.setOpeningBal(1);
        customerCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setRateSlab(1);
        customerCreationEntity.setStatus("Status");
        customerCreationEntity.setUpdated_by("2020-03-01");
        customerCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        SalesInvoiceEntity salesInvoiceEntity = new SalesInvoiceEntity();
        salesInvoiceEntity.setCustomerCreationEntity(customerCreationEntity);
        salesInvoiceEntity.setBilldate(LocalDate.ofEpochDay(1L));
        salesInvoiceEntity.setCustomerAddress("42 Main St");
        salesInvoiceEntity.setCustomerName("Customer Name");
        salesInvoiceEntity.setDiscountAmt(10.00);
        salesInvoiceEntity.setDiscount(10.00);
        salesInvoiceEntity.setDoctor_name("Doctor");
        salesInvoiceEntity.setDoctorAddress("42 Main St");
        salesInvoiceEntity.setExpenses(10.333);
        salesInvoiceEntity.setGrossAmount(10.222);
        salesInvoiceEntity.setGstAmt(10.222);
        salesInvoiceEntity.setHomeDelivery("Home Delivery");
        salesInvoiceEntity.setId(123l);
        salesInvoiceEntity.setNetAmt(122.00);
        salesInvoiceEntity.setOtherExpenses("Other Expenses");
        salesInvoiceEntity.setRemainderDate(LocalDate.ofEpochDay(1L));
        salesInvoiceEntity.setRemainderDays(1);
        salesInvoiceEntity.setRoundOffAmt(10.222);
        salesInvoiceEntity.setMargin(10.111);
        salesInvoiceEntity.setMarginOn("margin on");
        salesInvoiceEntity.setStatus("Status");
        salesInvoiceEntity.setStockinvoiceDetails(new ArrayList<>());
        // Act and Assert
        assertSame(salesInvoiceDTO, dTOTransfomers.saleInvoiceConvertToDto(salesInvoiceEntity));
        verify(modelMapper).map((Object) any(), (Class<SalesInvoiceDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#saleInvoiceConvertToDto(ShortageEntryEntity)}
     */
    @Test
    void testSaleInvoiceConvertToDto2() {
        // Arrange
        ShortageEntryDTO shortageEntryDTO = new ShortageEntryDTO();
        when(modelMapper.map((Object) any(), (Class<ShortageEntryDTO>) any())).thenReturn(shortageEntryDTO);

        ShortageEntryEntity shortageEntryEntity = new ShortageEntryEntity();
        shortageEntryEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        shortageEntryEntity.setId(123L);
        shortageEntryEntity.setShortageDetails(new ArrayList<>());
        shortageEntryEntity.setStatus("Status");
        shortageEntryEntity.setTotalItems(10.0d);
        shortageEntryEntity.setTotalQty(10.0d);

        // Act and Assert
        assertSame(shortageEntryDTO, dTOTransfomers.saleInvoiceConvertToDto(shortageEntryEntity));
        verify(modelMapper).map((Object) any(), (Class<ShortageEntryDTO>) any());
    }


    /**
     * Method under test: {@link DTOTransfomers#saleInvoiceConvertToEntity(SalesInvoiceDTO)}
     */
   /* @Test
    void testSaleInvoiceConvertToEntity2() throws ParseException {
        // Arrange
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        when(dateConvertUtils.convertStringToDate((String) any())).thenReturn(ofEpochDayResult);

        CustomerCreationEntity customerCreationEntity = new CustomerCreationEntity();
        customerCreationEntity.setAddress1("42 Main St");
        customerCreationEntity.setAddress2("42 Main St");
        customerCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        customerCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setCreditLimit(1);
        customerCreationEntity.setCustomerName("Customer Name");
        customerCreationEntity.setDiscount(3);
        customerCreationEntity.setDueDays(1);
        customerCreationEntity.setGstNo("Gst No");
        customerCreationEntity.setGstType("Gst Type");
        customerCreationEntity.setId(123L);
        customerCreationEntity.setMobileNo("Mobile No");
        customerCreationEntity.setModeOfPayment("Mode Of Payment");
        customerCreationEntity.setOpeningBal(1);
        customerCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setRateSlab(1);
        customerCreationEntity.setStatus("Status");
        customerCreationEntity.setUpdated_by("2020-03-01");
        customerCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        SalesInvoiceEntity salesInvoiceEntity = new SalesInvoiceEntity();
        salesInvoiceEntity.setCustomerCreationEntity(customerCreationEntity);
        salesInvoiceEntity.setBilldate(LocalDate.ofEpochDay(1L));
        salesInvoiceEntity.setCustomerAddress("42 Main St");
        salesInvoiceEntity.setCustomerName("Customer Name");
        salesInvoiceEntity.setDiscountAmt(10L);
        salesInvoiceEntity.setDiscount(10L);
        salesInvoiceEntity.setDoctor_name("Doctor");
        salesInvoiceEntity.setDoctorAddress("42 Main St");
        salesInvoiceEntity.setExpenses(10L);
        salesInvoiceEntity.setGrossAmount(10L);
        salesInvoiceEntity.setGstAmt(10L);
        salesInvoiceEntity.setHomeDelivery("Home Delivery");
        salesInvoiceEntity.setId(123L);
        salesInvoiceEntity.setNetAmt(10L);
        salesInvoiceEntity.setOtherExpenses("Other Expenses");
        salesInvoiceEntity.setRemainderDate(LocalDate.ofEpochDay(1L));
        salesInvoiceEntity.setRemainderDays(1);
        salesInvoiceEntity.setRoundOffAmt(10L);
        salesInvoiceEntity.setMargin(10L);
        salesInvoiceEntity.setMarginOn("margin on");
        salesInvoiceEntity.setStatus("Status");
        salesInvoiceEntity.setStockinvoiceDetails(new ArrayList<>());
        when(modelMapper.map((Object) any(), (Class<SalesInvoiceEntity>) any())).thenReturn(salesInvoiceEntity);

        // Act
        SalesInvoiceEntity actualSaleInvoiceConvertToEntityResult = dTOTransfomers
                .saleInvoiceConvertToEntity(new SalesInvoiceDTO(123L, "Customer Name","42 Main St","Doctor"
                ,"42 Main St",10L,1,LocalDate.ofEpochDay(1L),"Home Delivery",LocalDate.ofEpochDay(1L)
                ,10L,10L,"margin on",10.999,10.222,10.222,10.22,"Other Expenses",10.222,"Status",customerCreationEntity,new ArrayList<>()));

        // Assert
        assertSame(salesInvoiceEntity, actualSaleInvoiceConvertToEntityResult);
        assertEquals("1970-01-02", actualSaleInvoiceConvertToEntityResult.getBilldate().toString());
        assertTrue(actualSaleInvoiceConvertToEntityResult.getStockinvoiceDetails().isEmpty());
        verify(modelMapper).map((Object) any(), (Class<SalesInvoiceEntity>) any());
    }
    
    */

    /**
     * Method under test: {@link DTOTransfomers#saleInvoiceConvertToEntity(ShortageEntryDTO)}
     */
    @Test
    void testSaleInvoiceConvertToEntity4() throws ParseException {
        // Arrange
        when(dateConvertUtils.convertStringToDate((String) any())).thenReturn(LocalDate.ofEpochDay(1L));

        ShortageEntryEntity shortageEntryEntity = new ShortageEntryEntity();
        shortageEntryEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        shortageEntryEntity.setId(123L);
        shortageEntryEntity.setShortageDetails(new ArrayList<>());
        shortageEntryEntity.setStatus("Status");
        shortageEntryEntity.setTotalItems(10.0d);
        shortageEntryEntity.setTotalQty(10.0d);
        when(modelMapper.map((Object) any(), (Class<ShortageEntryEntity>) any())).thenReturn(shortageEntryEntity);

        // Act
        ShortageEntryEntity actualSaleInvoiceConvertToEntityResult = dTOTransfomers
                .saleInvoiceConvertToEntity(new ShortageEntryDTO());

        // Assert
        assertSame(shortageEntryEntity, actualSaleInvoiceConvertToEntityResult);
        assertEquals("1970-01-02", actualSaleInvoiceConvertToEntityResult.getEntryDate().toString());
        verify(dateConvertUtils).convertStringToDate((String) any());
        verify(modelMapper).map((Object) any(), (Class<ShortageEntryEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#stockAdjConvertToDto(StockAdjEntity)}
     */
    @Test
    void testStockAdjConvertToDto() {
        // Arrange
        StockAdjDTO stockAdjDTO = new StockAdjDTO();
        stockAdjDTO.setEntryDate("2020-03-01");
        stockAdjDTO.setId(123L);
        stockAdjDTO.setStatus("Status");
        stockAdjDTO.setStockAdjDetails(new ArrayList<>());
        stockAdjDTO.setTotalAdj("Total Adj");
        when(modelMapper.map((Object) any(), (Class<StockAdjDTO>) any())).thenReturn(stockAdjDTO);

        StockAdjEntity stockAdjEntity = new StockAdjEntity();
        stockAdjEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        stockAdjEntity.setId(123L);
        stockAdjEntity.setStatus("Status");
        stockAdjEntity.setStockAdjDetails(new ArrayList<>());
        stockAdjEntity.setTotalAdj("Total Adj");

        // Act and Assert
        assertSame(stockAdjDTO, dTOTransfomers.stockAdjConvertToDto(stockAdjEntity));
        verify(modelMapper).map((Object) any(), (Class<StockAdjDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#stockAdjConvertToEntity(StockAdjDTO)}
     */
    @Test
    void testStockAdjConvertToEntity() throws ParseException {
        // Arrange
        when(dateConvertUtils.convertStringToDate((String) any())).thenReturn(LocalDate.ofEpochDay(1L));

        StockAdjEntity stockAdjEntity = new StockAdjEntity();
        stockAdjEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        stockAdjEntity.setId(123L);
        stockAdjEntity.setStatus("Status");
        stockAdjEntity.setStockAdjDetails(new ArrayList<>());
        stockAdjEntity.setTotalAdj("Total Adj");
        when(modelMapper.map((Object) any(), (Class<StockAdjEntity>) any())).thenReturn(stockAdjEntity);

        StockAdjDTO stockAdjDTO = new StockAdjDTO();
        stockAdjDTO.setEntryDate("2020-03-01");
        stockAdjDTO.setId(123L);
        stockAdjDTO.setStatus("Status");
        stockAdjDTO.setStockAdjDetails(new ArrayList<>());
        stockAdjDTO.setTotalAdj("Total Adj");

        // Act
        StockAdjEntity actualStockAdjConvertToEntityResult = dTOTransfomers.stockAdjConvertToEntity(stockAdjDTO);

        // Assert
        assertSame(stockAdjEntity, actualStockAdjConvertToEntityResult);
        assertEquals("1970-01-02", actualStockAdjConvertToEntityResult.getEntryDate().toString());
        verify(dateConvertUtils).convertStringToDate((String) any());
        verify(modelMapper).map((Object) any(), (Class<StockAdjEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#storeTypeConvertToDto(StoreTypeCreationEntity)}
     */
    @Test
    void testStoreTypeConvertToDto() {
        // Arrange
        StoreTypeCreationDTO storeTypeCreationDTO = new StoreTypeCreationDTO();
        storeTypeCreationDTO.setId(123L);
        storeTypeCreationDTO.setStatus("Status");
        storeTypeCreationDTO.setStoreTypeName("Store Type Name");
        when(modelMapper.map((Object) any(), (Class<StoreTypeCreationDTO>) any())).thenReturn(storeTypeCreationDTO);

        StoreTypeCreationEntity storeTypeCreationEntity = new StoreTypeCreationEntity();
        storeTypeCreationEntity.setId(123L);
        storeTypeCreationEntity.setStatus("Status");
        storeTypeCreationEntity.setStoreTypeName("Store Type Name");

        // Act and Assert
        assertSame(storeTypeCreationDTO, dTOTransfomers.storeTypeConvertToDto(storeTypeCreationEntity));
        verify(modelMapper).map((Object) any(), (Class<StoreTypeCreationDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#storeTypeConvertToEntity(StoreTypeCreationDTO)}
     */
    @Test
    void testStoreTypeConvertToEntity() throws ParseException {
        // Arrange
        StoreTypeCreationEntity storeTypeCreationEntity = new StoreTypeCreationEntity();
        storeTypeCreationEntity.setId(123L);
        storeTypeCreationEntity.setStatus("Status");
        storeTypeCreationEntity.setStoreTypeName("Store Type Name");
        when(modelMapper.map((Object) any(), (Class<StoreTypeCreationEntity>) any())).thenReturn(storeTypeCreationEntity);

        StoreTypeCreationDTO storeTypeCreationDTO = new StoreTypeCreationDTO();
        storeTypeCreationDTO.setId(123L);
        storeTypeCreationDTO.setStatus("Status");
        storeTypeCreationDTO.setStoreTypeName("Store Type Name");

        // Act and Assert
        assertSame(storeTypeCreationEntity, dTOTransfomers.storeTypeConvertToEntity(storeTypeCreationDTO));
        verify(modelMapper).map((Object) any(), (Class<StoreTypeCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#supplierConvertToDto(SupplierCreationEntity)}
     */
    @Test
    void testSupplierConvertToDto() {
        // Arrange
        SupplierCreationDTO supplierCreationDTO = new SupplierCreationDTO();
        supplierCreationDTO.setAddress1("42 Main St");
        supplierCreationDTO.setAddress2("42 Main St");
        supplierCreationDTO.setCredit_days(1);
        supplierCreationDTO.setDiscount("3");
        supplierCreationDTO.setGstNo("Gst No");
        supplierCreationDTO.setGstType("Gst Type");
        supplierCreationDTO.setId(123L);
        supplierCreationDTO.setMobileNo("Mobile No");
        supplierCreationDTO.setModeOfPayment("Mode Of Payment");
        supplierCreationDTO.setOpeningBal(1L);
        supplierCreationDTO.setOpeningBalDate("2020-03-01");
        supplierCreationDTO.setRateSlab("Rate Slab");
        supplierCreationDTO.setStatus("Status");
        supplierCreationDTO.setSupplierName("Supplier Name");
        when(modelMapper.map((Object) any(), (Class<SupplierCreationDTO>) any())).thenReturn(supplierCreationDTO);

        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("Gst No");
        supplierCreationEntity.setGstType("Gst Type");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("Mobile No");
        supplierCreationEntity.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("Rate Slab");
        supplierCreationEntity.setStatus("Status");
        supplierCreationEntity.setSupplierName("Supplier Name");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        // Act and Assert
        assertSame(supplierCreationDTO, dTOTransfomers.supplierConvertToDto(supplierCreationEntity));
        verify(modelMapper).map((Object) any(), (Class<SupplierCreationDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#supplierConvertToEntity(SupplierCreationDTO)}
     */
    @Test
    void testSupplierConvertToEntity() throws ParseException {
        // Arrange
        when(dateConvertUtils.convertStringToDate((String) any())).thenReturn(LocalDate.ofEpochDay(1L));

        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("Gst No");
        supplierCreationEntity.setGstType("Gst Type");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("Mobile No");
        supplierCreationEntity.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("Rate Slab");
        supplierCreationEntity.setStatus("Status");
        supplierCreationEntity.setSupplierName("Supplier Name");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));
        when(modelMapper.map((Object) any(), (Class<SupplierCreationEntity>) any())).thenReturn(supplierCreationEntity);

        SupplierCreationDTO supplierCreationDTO = new SupplierCreationDTO();
        supplierCreationDTO.setAddress1("42 Main St");
        supplierCreationDTO.setAddress2("42 Main St");
        supplierCreationDTO.setCredit_days(1);
        supplierCreationDTO.setDiscount("3");
        supplierCreationDTO.setGstNo("Gst No");
        supplierCreationDTO.setGstType("Gst Type");
        supplierCreationDTO.setId(123L);
        supplierCreationDTO.setMobileNo("Mobile No");
        supplierCreationDTO.setModeOfPayment("Mode Of Payment");
        supplierCreationDTO.setOpeningBal(1L);
        supplierCreationDTO.setOpeningBalDate("2020-03-01");
        supplierCreationDTO.setRateSlab("Rate Slab");
        supplierCreationDTO.setStatus("Status");
        supplierCreationDTO.setSupplierName("Supplier Name");

        // Act
        SupplierCreationEntity actualSupplierConvertToEntityResult = dTOTransfomers
                .supplierConvertToEntity(supplierCreationDTO);

        // Assert
        assertSame(supplierCreationEntity, actualSupplierConvertToEntityResult);
        assertEquals("1970-01-02", actualSupplierConvertToEntityResult.getOpeningBalDate().toString());
        verify(dateConvertUtils).convertStringToDate((String) any());
        verify(modelMapper).map((Object) any(), (Class<SupplierCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#supplierConvertToEntityList(List)}
     */
    @Test
    void testSupplierConvertToEntityList() throws ParseException {
        // Arrange, Act and Assert
        assertTrue(dTOTransfomers.supplierConvertToEntityList(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link DTOTransfomers#supplierConvertToEntityList(List)}
     */
    @Test
    void testSupplierConvertToEntityList2() throws ParseException {
        // Arrange
        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("Gst No");
        supplierCreationEntity.setGstType("Gst Type");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("Mobile No");
        supplierCreationEntity.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("Rate Slab");
        supplierCreationEntity.setStatus("Status");
        supplierCreationEntity.setSupplierName("Supplier Name");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));
        when(modelMapper.map((Object) any(), (Class<SupplierCreationEntity>) any())).thenReturn(supplierCreationEntity);

        SupplierCreationDTO supplierCreationDTO = new SupplierCreationDTO();
        supplierCreationDTO.setAddress1("42 Main St");
        supplierCreationDTO.setAddress2("42 Main St");
        supplierCreationDTO.setCredit_days(1);
        supplierCreationDTO.setDiscount("3");
        supplierCreationDTO.setGstNo("Gst No");
        supplierCreationDTO.setGstType("Gst Type");
        supplierCreationDTO.setId(123L);
        supplierCreationDTO.setMobileNo("Mobile No");
        supplierCreationDTO.setModeOfPayment("Mode Of Payment");
        supplierCreationDTO.setOpeningBal(1L);
        supplierCreationDTO.setOpeningBalDate("2020-03-01");
        supplierCreationDTO.setRateSlab("Rate Slab");
        supplierCreationDTO.setStatus("Status");
        supplierCreationDTO.setSupplierName("Supplier Name");

        ArrayList<SupplierCreationDTO> supplierCreationDTOList = new ArrayList<>();
        supplierCreationDTOList.add(supplierCreationDTO);

        // Act and Assert
        assertEquals(1, dTOTransfomers.supplierConvertToEntityList(supplierCreationDTOList).size());
        verify(modelMapper).map((Object) any(), (Class<SupplierCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#supplierConvertToEntityList(List)}
     */
    @Test
    void testSupplierConvertToEntityList3() throws ParseException {
        // Arrange
        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("Gst No");
        supplierCreationEntity.setGstType("Gst Type");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("Mobile No");
        supplierCreationEntity.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("Rate Slab");
        supplierCreationEntity.setStatus("Status");
        supplierCreationEntity.setSupplierName("Supplier Name");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));
        when(modelMapper.map((Object) any(), (Class<SupplierCreationEntity>) any())).thenReturn(supplierCreationEntity);

        SupplierCreationDTO supplierCreationDTO = new SupplierCreationDTO();
        supplierCreationDTO.setAddress1("42 Main St");
        supplierCreationDTO.setAddress2("42 Main St");
        supplierCreationDTO.setCredit_days(1);
        supplierCreationDTO.setDiscount("3");
        supplierCreationDTO.setGstNo("Gst No");
        supplierCreationDTO.setGstType("Gst Type");
        supplierCreationDTO.setId(123L);
        supplierCreationDTO.setMobileNo("Mobile No");
        supplierCreationDTO.setModeOfPayment("Mode Of Payment");
        supplierCreationDTO.setOpeningBal(1L);
        supplierCreationDTO.setOpeningBalDate("2020-03-01");
        supplierCreationDTO.setRateSlab("Rate Slab");
        supplierCreationDTO.setStatus("Status");
        supplierCreationDTO.setSupplierName("Supplier Name");

        SupplierCreationDTO supplierCreationDTO1 = new SupplierCreationDTO();
        supplierCreationDTO1.setAddress1("42 Main St");
        supplierCreationDTO1.setAddress2("42 Main St");
        supplierCreationDTO1.setCredit_days(1);
        supplierCreationDTO1.setDiscount("3");
        supplierCreationDTO1.setGstNo("Gst No");
        supplierCreationDTO1.setGstType("Gst Type");
        supplierCreationDTO1.setId(123L);
        supplierCreationDTO1.setMobileNo("Mobile No");
        supplierCreationDTO1.setModeOfPayment("Mode Of Payment");
        supplierCreationDTO1.setOpeningBal(1L);
        supplierCreationDTO1.setOpeningBalDate("2020-03-01");
        supplierCreationDTO1.setRateSlab("Rate Slab");
        supplierCreationDTO1.setStatus("Status");
        supplierCreationDTO1.setSupplierName("Supplier Name");

        ArrayList<SupplierCreationDTO> supplierCreationDTOList = new ArrayList<>();
        supplierCreationDTOList.add(supplierCreationDTO1);
        supplierCreationDTOList.add(supplierCreationDTO);

        // Act and Assert
        assertEquals(2, dTOTransfomers.supplierConvertToEntityList(supplierCreationDTOList).size());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<SupplierCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#warningConvertToDto(WarningCreationEntity)}
     */
    @Test
    void testWarningConvertToDto() {
        // Arrange
        WarningCreationDTO warningCreationDTO = new WarningCreationDTO();
        when(modelMapper.map((Object) any(), (Class<WarningCreationDTO>) any())).thenReturn(warningCreationDTO);

        WarningCreationEntity warningCreationEntity = new WarningCreationEntity();
        warningCreationEntity.setId(123L);
        warningCreationEntity.setStatus("Status");
        warningCreationEntity.setWarningName("Warning Name");

        // Act and Assert
        assertSame(warningCreationDTO, dTOTransfomers.warningConvertToDto(warningCreationEntity));
        verify(modelMapper).map((Object) any(), (Class<WarningCreationDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#warningConvertToEntity(WarningCreationDTO)}
     */
    @Test
    void testWarningConvertToEntity() throws ParseException {
        // Arrange
        WarningCreationEntity warningCreationEntity = new WarningCreationEntity();
        warningCreationEntity.setId(123L);
        warningCreationEntity.setStatus("Status");
        warningCreationEntity.setWarningName("Warning Name");
        when(modelMapper.map((Object) any(), (Class<WarningCreationEntity>) any())).thenReturn(warningCreationEntity);

        // Act and Assert
        assertSame(warningCreationEntity, dTOTransfomers.warningConvertToEntity(new WarningCreationDTO()));
        verify(modelMapper).map((Object) any(), (Class<WarningCreationEntity>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#schedulerConvertToDto(SchedulerCreationEntity)}
     */
    @Test
    void testSchedulerConvertToDto() {
        // Arrange
        SchedulerCreationDTO schedulerCreationDTO = new SchedulerCreationDTO();
        when(modelMapper.map((Object) any(), (Class<SchedulerCreationDTO>) any())).thenReturn(schedulerCreationDTO);

        SchedulerCreationEntity schedulerCreationEntity = new SchedulerCreationEntity();
        schedulerCreationEntity.setId(123L);
        schedulerCreationEntity.setSchedulerName("Scheduler Name");
        schedulerCreationEntity.setStatus("Status");
        schedulerCreationEntity.setWaringMsg("Waring Msg");
        schedulerCreationEntity.setWarning("Warning");

        // Act and Assert
        assertSame(schedulerCreationDTO, dTOTransfomers.schedulerConvertToDto(schedulerCreationEntity));
        verify(modelMapper).map((Object) any(), (Class<SchedulerCreationDTO>) any());
    }

    /**
     * Method under test: {@link DTOTransfomers#schedulerConvertToEntity(SchedulerCreationDTO)}
     */
    @Test
    void testSchedulerConvertToEntity() throws ParseException {
        // Arrange
        SchedulerCreationEntity schedulerCreationEntity = new SchedulerCreationEntity();
        schedulerCreationEntity.setId(123L);
        schedulerCreationEntity.setSchedulerName("Scheduler Name");
        schedulerCreationEntity.setStatus("Status");
        schedulerCreationEntity.setWaringMsg("Waring Msg");
        schedulerCreationEntity.setWarning("Warning");
        when(modelMapper.map((Object) any(), (Class<SchedulerCreationEntity>) any())).thenReturn(schedulerCreationEntity);

        // Act and Assert
        assertSame(schedulerCreationEntity, dTOTransfomers.schedulerConvertToEntity(new SchedulerCreationDTO()));
        verify(modelMapper).map((Object) any(), (Class<SchedulerCreationEntity>) any());
    }
}

