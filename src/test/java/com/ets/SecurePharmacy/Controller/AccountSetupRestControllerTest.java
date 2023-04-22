package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.DTO.APIResponse;
import com.ets.SecurePharmacy.DTO.AccountCreationDTO;
import com.ets.SecurePharmacy.DTO.AreaCreationDTO;
import com.ets.SecurePharmacy.DTO.BranchCreationDTO;
import com.ets.SecurePharmacy.DTO.CompostionCreationDTO;
import com.ets.SecurePharmacy.DTO.CustomerCreationDTO;
import com.ets.SecurePharmacy.DTO.DiscountSlabCreationDTO;
import com.ets.SecurePharmacy.DTO.GroupCreationDTO;
import com.ets.SecurePharmacy.DTO.HsnSacCreationDTO;
import com.ets.SecurePharmacy.DTO.ItemCreationDTO;
import com.ets.SecurePharmacy.DTO.ManufacturerCreationDTO;
import com.ets.SecurePharmacy.DTO.PackCreationDTO;
import com.ets.SecurePharmacy.DTO.SalesManCreationDTO;
import com.ets.SecurePharmacy.DTO.SchedulerCreationDTO;
import com.ets.SecurePharmacy.DTO.StoreTypeCreationDTO;
import com.ets.SecurePharmacy.DTO.SupplierCreationDTO;
import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.service.AccountCreationService;
import com.ets.SecurePharmacy.service.AccountHeadCreationService;
import com.ets.SecurePharmacy.service.AccountTypeCreationService;
import com.ets.SecurePharmacy.service.AreaCreationService;
import com.ets.SecurePharmacy.service.BestBeforeService;
import com.ets.SecurePharmacy.service.BranchCreationService;
import com.ets.SecurePharmacy.service.CompostionCreationService;
import com.ets.SecurePharmacy.service.CustomerCreationService;
import com.ets.SecurePharmacy.service.DiscountSlabCreationService;
import com.ets.SecurePharmacy.service.GSTPercentageService;
import com.ets.SecurePharmacy.service.GSTTypeCreationService;
import com.ets.SecurePharmacy.service.GroupCreationService;
import com.ets.SecurePharmacy.service.HsnSacCreationService;
import com.ets.SecurePharmacy.service.ItemCreationService;
import com.ets.SecurePharmacy.service.ManufacturerCreationService;
import com.ets.SecurePharmacy.service.PackCreationService;
import com.ets.SecurePharmacy.service.PaymentModeService;
import com.ets.SecurePharmacy.service.RouteCreationService;
import com.ets.SecurePharmacy.service.SalesManCreationService;
import com.ets.SecurePharmacy.service.SchedulerCreationService;
import com.ets.SecurePharmacy.service.StockCreationService;
import com.ets.SecurePharmacy.service.StoreTypeCreationService;
import com.ets.SecurePharmacy.service.SupplierCreationService;
import com.ets.SecurePharmacy.service.WarningCreationService;
import com.ets.SecurePharmacy.tenant.model.AccountCreationEntity;
import com.ets.SecurePharmacy.tenant.model.AreaCreationEntity;
import com.ets.SecurePharmacy.tenant.model.BranchCreationEntity;
import com.ets.SecurePharmacy.tenant.model.CompostionCreationEntity;
import com.ets.SecurePharmacy.tenant.model.CustomerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.DiscountSlabCreationEntity;
import com.ets.SecurePharmacy.tenant.model.GroupCreationEntity;
import com.ets.SecurePharmacy.tenant.model.HsnSacCreationEntity;
import com.ets.SecurePharmacy.tenant.model.ItemCreationEntity;
import com.ets.SecurePharmacy.tenant.model.ManufacturerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.PackCreationEntity;
import com.ets.SecurePharmacy.tenant.model.RouteCreationEntity;
import com.ets.SecurePharmacy.tenant.model.SalesManCreationEntity;
import com.ets.SecurePharmacy.tenant.model.SchedulerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.StockCreationEntity;
import com.ets.SecurePharmacy.tenant.model.StoreTypeCreationEntity;
import com.ets.SecurePharmacy.tenant.model.SupplierCreationEntity;
import com.ets.SecurePharmacy.transfomers.DTOTransfomers;
import com.ets.SecurePharmacy.util.DateConvertUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.MediaType;
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
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ContextConfiguration(classes = {AccountSetupRestController.class})
@ExtendWith(SpringExtension.class)
class AccountSetupRestControllerTest {
    @MockBean
    private AccountCreationService accountCreationService;

    @MockBean
    private AccountHeadCreationService accountHeadCreationService;

    @Autowired
    private AccountSetupRestController accountSetupRestController;

    @MockBean
    private AccountTypeCreationService accountTypeCreationService;

    @MockBean
    private AreaCreationService areaCreationService;

    @MockBean
    private BestBeforeService bestBeforeService;

    @MockBean
    private BranchCreationService branchCreationService;

    @MockBean
    private CompostionCreationService compostionCreationService;

    @MockBean
    private CustomerCreationService customerCreationService;

    @MockBean
    private DTOTransfomers dTOTransfomers;

    @MockBean
    private DateConvertUtils dateConvertUtils;

    @MockBean
    private DiscountSlabCreationService discountSlabCreationService;

    @MockBean
    private GSTPercentageService gSTPercentageService;

    @MockBean
    private GSTTypeCreationService gSTTypeCreationService;

    @MockBean
    private GroupCreationService groupCreationService;

    @MockBean
    private HsnSacCreationService hsnSacCreationService;

    @MockBean
    private ItemCreationService itemCreationService;

    @MockBean
    private ManufacturerCreationService manufacturerCreationService;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private PackCreationService packCreationService;

    @MockBean(name = "paymentModeService")
    private PaymentModeService paymentModeService;

    @MockBean
    private RouteCreationService routeCreationService;

    @MockBean
    private SalesManCreationService salesManCreationService;

    @MockBean
    private SchedulerCreationService schedulerCreationService;

    @MockBean
    private StockCreationService stockCreationService;

    @MockBean
    private StoreTypeCreationService storeTypeCreationService;

    @MockBean(name = "supplierService")
    private SupplierCreationService supplierCreationService;

    @MockBean
    private WarningCreationService warningCreationService;

    /**
     * Method under test: {@link AccountSetupRestController#areaCreateList(List)}
     */
    @Test
    void testAreaCreateList() throws Exception {
        doNothing().when(areaCreationService).createOrUpdateAreaList((List<AreaCreationEntity>) any());
        when(dTOTransfomers.areaCreationConvertToEntityList((List<AreaCreationDTO>) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/AccountSetup/AreaCreate/bulk")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#areaCreateList(List)}
     */
    @Test
    void testAreaCreateList2() throws Exception {
        doThrow(new UnableToProcessException("An error occurred")).when(areaCreationService).createOrUpdateAreaList((List<AreaCreationEntity>) any());

        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/AccountSetup/AreaCreate/bulk")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#customerCreateBulk(List)}
     */
    @Test
    void testCustomerCreateBulk() throws Exception {
        doNothing().when(customerCreationService).createOrUpdateCustomerList((List<CustomerCreationEntity>) any());
        when(dTOTransfomers.customerCreationConvertToEntityList((List<CustomerCreationDTO>) any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/AccountSetup/CustomerCreate/bulk")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#customerCreateBulk(List)}
     */
    @Test
    void testCustomerCreateBulk2() throws Exception {
        doThrow(new UnableToProcessException("An error occurred")).when(customerCreationService).createOrUpdateCustomerList((List<CustomerCreationEntity>) any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/AccountSetup/CustomerCreate/bulk")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#customerCreateMobile(String)}
     */
    @Test
    void testCustomerCreateMobile() throws Exception {
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
        when(customerCreationService.getCustomerByMobile((String) any())).thenReturn(customerCreationEntity);
        when(dTOTransfomers.customerCreationConvertToDto((CustomerCreationEntity) any()))
                .thenReturn(new CustomerCreationDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/AccountSetup/CustomerCreate/{mobileNo}", "Mobile No");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<CustomerCreationDTO><id/><customerName/><mobileNo/><address1/><address2/><gstType/><gstNo/><discount"
                                        + "/><rateSlab/><modeOfPayment/><actName/><dueDays/><openingBal/><openingBalDate/><status/></CustomerCreationDTO"
                                        + ">"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllAccounts()}
     */
    @Test
    void testGetAllAccounts() throws Exception {
        when(accountCreationService.getAllAccounts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getAccountDetails");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAccountsWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetAccountsWithPagination() {
        when(accountCreationService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<AccountCreationEntity>> response = accountSetupRestController.getAccountsWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAccountsWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetAccountsWithPagination2() {
        when(accountCreationService.getAllAccountsWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<AccountCreationEntity>> response = accountSetupRestController.getAccountsWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getArea(Long)}
     */
    @Test
    void testGetArea() throws Exception {
        AreaCreationEntity areaCreationEntity = new AreaCreationEntity();
        areaCreationEntity.setAreaName("Area Name");
        areaCreationEntity.setId(123L);
        areaCreationEntity.setRoute("Route");
        areaCreationEntity.setStatus("Status");
        when(areaCreationService.getAreaById((Long) any())).thenReturn(areaCreationEntity);
        when(dTOTransfomers.areaCreationConvertToDto((AreaCreationEntity) any())).thenReturn(new AreaCreationDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getArea/{id}", 123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<AreaCreationDTO><id/><areaName/><route/><status/></AreaCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getArea(Long)}
     */
    @Test
    void testGetArea2() throws Exception {
        when(areaCreationService.getAreaById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getArea/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getPackType(Long)}
     */
    @Test
    void testGetPackType() throws Exception {
        when(dTOTransfomers.packCreateConvertToDto((PackCreationEntity) any())).thenReturn(new PackCreationDTO());

        PackCreationEntity packCreationEntity = new PackCreationEntity();
        packCreationEntity.setId(123L);
        packCreationEntity.setPackName("Pack Name");
        packCreationEntity.setQty(1);
        packCreationEntity.setStatus("Status");
        when(packCreationService.getPakById((Long) any())).thenReturn(packCreationEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getPack/{id}", 123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<PackCreationDTO><id/><packName/><qty>0</qty><status/></PackCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getPackType(Long)}
     */
    @Test
    void testGetPackType2() throws Exception {
        when(packCreationService.getPakById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getPack/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountSetupRestController#hsntypeCreate(HsnSacCreationDTO)}
     */
    @Test
    void testHsntypeCreate() throws Exception {
        HsnSacCreationEntity hsnSacCreationEntity = new HsnSacCreationEntity();
        hsnSacCreationEntity.setDescirption("Descirption");
        hsnSacCreationEntity.setHsnName("Hsn Name");
        hsnSacCreationEntity.setId(123L);
        hsnSacCreationEntity.setStatus("Status");
        when(dTOTransfomers.hsnCreationConvertToDto((HsnSacCreationEntity) any())).thenReturn(new HsnSacCreationDTO());
        when(dTOTransfomers.hsnConvertToEntity((HsnSacCreationDTO) any())).thenReturn(hsnSacCreationEntity);

        HsnSacCreationEntity hsnSacCreationEntity1 = new HsnSacCreationEntity();
        hsnSacCreationEntity1.setDescirption("Descirption");
        hsnSacCreationEntity1.setHsnName("Hsn Name");
        hsnSacCreationEntity1.setId(123L);
        hsnSacCreationEntity1.setStatus("Status");
        when(hsnSacCreationService.getHsnByName((String) any())).thenReturn(true);
        when(hsnSacCreationService.createOrUpdateHsn((HsnSacCreationEntity) any())).thenReturn(hsnSacCreationEntity1);

        HsnSacCreationDTO hsnSacCreationDTO = new HsnSacCreationDTO();
        hsnSacCreationDTO.setDescirption("Descirption");
        hsnSacCreationDTO.setHsnName("Hsn Name");
        hsnSacCreationDTO.setId(123L);
        hsnSacCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(hsnSacCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/AccountSetup/HSNCreate/HSNSAC")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link AccountSetupRestController#hsntypeCreate(HsnSacCreationDTO)}
     */
    @Test
    void testHsntypeCreate2() throws Exception {
        HsnSacCreationEntity hsnSacCreationEntity = new HsnSacCreationEntity();
        hsnSacCreationEntity.setDescirption("Descirption");
        hsnSacCreationEntity.setHsnName("Hsn Name");
        hsnSacCreationEntity.setId(123L);
        hsnSacCreationEntity.setStatus("Status");
        when(dTOTransfomers.hsnCreationConvertToDto((HsnSacCreationEntity) any())).thenReturn(new HsnSacCreationDTO());
        when(dTOTransfomers.hsnConvertToEntity((HsnSacCreationDTO) any())).thenReturn(hsnSacCreationEntity);
        when(hsnSacCreationService.getHsnByName((String) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));
        when(hsnSacCreationService.createOrUpdateHsn((HsnSacCreationEntity) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));

        HsnSacCreationDTO hsnSacCreationDTO = new HsnSacCreationDTO();
        hsnSacCreationDTO.setDescirption("Descirption");
        hsnSacCreationDTO.setHsnName("Hsn Name");
        hsnSacCreationDTO.setId(123L);
        hsnSacCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(hsnSacCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/AccountSetup/HSNCreate/HSNSAC")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link AccountSetupRestController#hsntypeCreate(HsnSacCreationDTO)}
     */
    @Test
    void testHsntypeCreate3() throws Exception {
        HsnSacCreationEntity hsnSacCreationEntity = new HsnSacCreationEntity();
        hsnSacCreationEntity.setDescirption("Descirption");
        hsnSacCreationEntity.setHsnName("Hsn Name");
        hsnSacCreationEntity.setId(123L);
        hsnSacCreationEntity.setStatus("Status");
        when(dTOTransfomers.hsnCreationConvertToDto((HsnSacCreationEntity) any())).thenReturn(new HsnSacCreationDTO());
        when(dTOTransfomers.hsnConvertToEntity((HsnSacCreationDTO) any())).thenReturn(hsnSacCreationEntity);

        HsnSacCreationEntity hsnSacCreationEntity1 = new HsnSacCreationEntity();
        hsnSacCreationEntity1.setDescirption("Descirption");
        hsnSacCreationEntity1.setHsnName("Hsn Name");
        hsnSacCreationEntity1.setId(123L);
        hsnSacCreationEntity1.setStatus("Status");
        when(hsnSacCreationService.getHsnByName((String) any())).thenReturn(false);
        when(hsnSacCreationService.createOrUpdateHsn((HsnSacCreationEntity) any())).thenReturn(hsnSacCreationEntity1);

        HsnSacCreationDTO hsnSacCreationDTO = new HsnSacCreationDTO();
        hsnSacCreationDTO.setDescirption("Descirption");
        hsnSacCreationDTO.setHsnName("Hsn Name");
        hsnSacCreationDTO.setId(123L);
        hsnSacCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(hsnSacCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/AccountSetup/HSNCreate/HSNSAC")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<HsnSacCreationDTO><id/><hsnName/><descirption/><status/></HsnSacCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#PackCreateList(List)}
     */
    @Test
    void testPackCreateList() throws Exception {
        when(dTOTransfomers.packCreateConvertToEntityList((List<PackCreationDTO>) any())).thenReturn(new ArrayList<>());
        doNothing().when(packCreationService).createOrUpdatePack((List<PackCreationEntity>) any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/AccountSetup/PackCreate/bulk")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#PackCreateList(List)}
     */
    @Test
    void testPackCreateList2() throws Exception {
        doThrow(new UnableToProcessException("An error occurred")).when(packCreationService)
                .createOrUpdatePack((List<PackCreationEntity>) any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/AccountSetup/PackCreate/bulk")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#schedulerCreate(SchedulerCreationDTO)}
     */
    @Test
    void testSchedulerCreate() throws Exception {
        SchedulerCreationEntity schedulerCreationEntity = new SchedulerCreationEntity();
        schedulerCreationEntity.setId(123L);
        schedulerCreationEntity.setSchedulerName("Scheduler Name");
        schedulerCreationEntity.setStatus("Status");
        schedulerCreationEntity.setWaringMsg("Waring Msg");
        schedulerCreationEntity.setWarning("Warning");
        when(dTOTransfomers.schedulerConvertToDto((SchedulerCreationEntity) any()))
                .thenReturn(new SchedulerCreationDTO());
        when(dTOTransfomers.schedulerConvertToEntity((SchedulerCreationDTO) any())).thenReturn(schedulerCreationEntity);

        SchedulerCreationEntity schedulerCreationEntity1 = new SchedulerCreationEntity();
        schedulerCreationEntity1.setId(123L);
        schedulerCreationEntity1.setSchedulerName("Scheduler Name");
        schedulerCreationEntity1.setStatus("Status");
        schedulerCreationEntity1.setWaringMsg("Waring Msg");
        schedulerCreationEntity1.setWarning("Warning");
        when(schedulerCreationService.getSchedulerByName((String) any())).thenReturn(true);
        when(schedulerCreationService.createOrUpdateScheduler((SchedulerCreationEntity) any()))
                .thenReturn(schedulerCreationEntity1);

        SchedulerCreationDTO schedulerCreationDTO = new SchedulerCreationDTO();
        schedulerCreationDTO.setId(123L);
        schedulerCreationDTO.setSchedulerName("Scheduler Name");
        schedulerCreationDTO.setStatus("Status");
        schedulerCreationDTO.setWaringMsg("Waring Msg");
        schedulerCreationDTO.setWarning("Warning");
        String content = (new ObjectMapper()).writeValueAsString(schedulerCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/AccountSetup/SchedulerCreate/Scheduler")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link AccountSetupRestController#schedulerCreate(SchedulerCreationDTO)}
     */
    @Test
    void testSchedulerCreate2() throws Exception {
        SchedulerCreationEntity schedulerCreationEntity = new SchedulerCreationEntity();
        schedulerCreationEntity.setId(123L);
        schedulerCreationEntity.setSchedulerName("Scheduler Name");
        schedulerCreationEntity.setStatus("Status");
        schedulerCreationEntity.setWaringMsg("Waring Msg");
        schedulerCreationEntity.setWarning("Warning");
        when(dTOTransfomers.schedulerConvertToDto((SchedulerCreationEntity) any()))
                .thenReturn(new SchedulerCreationDTO());
        when(dTOTransfomers.schedulerConvertToEntity((SchedulerCreationDTO) any())).thenReturn(schedulerCreationEntity);
        when(schedulerCreationService.getSchedulerByName((String) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));
        when(schedulerCreationService.createOrUpdateScheduler((SchedulerCreationEntity) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));

        SchedulerCreationDTO schedulerCreationDTO = new SchedulerCreationDTO();
        schedulerCreationDTO.setId(123L);
        schedulerCreationDTO.setSchedulerName("Scheduler Name");
        schedulerCreationDTO.setStatus("Status");
        schedulerCreationDTO.setWaringMsg("Waring Msg");
        schedulerCreationDTO.setWarning("Warning");
        String content = (new ObjectMapper()).writeValueAsString(schedulerCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/AccountSetup/SchedulerCreate/Scheduler")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link AccountSetupRestController#schedulerCreate(SchedulerCreationDTO)}
     */
    @Test
    void testSchedulerCreate3() throws Exception {
        SchedulerCreationEntity schedulerCreationEntity = new SchedulerCreationEntity();
        schedulerCreationEntity.setId(123L);
        schedulerCreationEntity.setSchedulerName("Scheduler Name");
        schedulerCreationEntity.setStatus("Status");
        schedulerCreationEntity.setWaringMsg("Waring Msg");
        schedulerCreationEntity.setWarning("Warning");
        when(dTOTransfomers.schedulerConvertToDto((SchedulerCreationEntity) any()))
                .thenReturn(new SchedulerCreationDTO());
        when(dTOTransfomers.schedulerConvertToEntity((SchedulerCreationDTO) any())).thenReturn(schedulerCreationEntity);

        SchedulerCreationEntity schedulerCreationEntity1 = new SchedulerCreationEntity();
        schedulerCreationEntity1.setId(123L);
        schedulerCreationEntity1.setSchedulerName("Scheduler Name");
        schedulerCreationEntity1.setStatus("Status");
        schedulerCreationEntity1.setWaringMsg("Waring Msg");
        schedulerCreationEntity1.setWarning("Warning");
        when(schedulerCreationService.getSchedulerByName((String) any())).thenReturn(false);
        when(schedulerCreationService.createOrUpdateScheduler((SchedulerCreationEntity) any()))
                .thenReturn(schedulerCreationEntity1);

        SchedulerCreationDTO schedulerCreationDTO = new SchedulerCreationDTO();
        schedulerCreationDTO.setId(123L);
        schedulerCreationDTO.setSchedulerName("Scheduler Name");
        schedulerCreationDTO.setStatus("Status");
        schedulerCreationDTO.setWaringMsg("Waring Msg");
        schedulerCreationDTO.setWarning("Warning");
        String content = (new ObjectMapper()).writeValueAsString(schedulerCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/AccountSetup/SchedulerCreate/Scheduler")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<SchedulerCreationDTO><id/><schedulerName/><waringMsg/><warning/><status/></SchedulerCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#storetypeCreate(StoreTypeCreationDTO)}
     */
    @Test
    void testStoretypeCreate() throws Exception {
        StoreTypeCreationDTO storeTypeCreationDTO = new StoreTypeCreationDTO();
        storeTypeCreationDTO.setId(123L);
        storeTypeCreationDTO.setStatus("Status");
        storeTypeCreationDTO.setStoreTypeName("Store Type Name");

        StoreTypeCreationEntity storeTypeCreationEntity = new StoreTypeCreationEntity();
        storeTypeCreationEntity.setId(123L);
        storeTypeCreationEntity.setStatus("Status");
        storeTypeCreationEntity.setStoreTypeName("Store Type Name");
        when(dTOTransfomers.storeTypeConvertToDto((StoreTypeCreationEntity) any())).thenReturn(storeTypeCreationDTO);
        when(dTOTransfomers.storeTypeConvertToEntity((StoreTypeCreationDTO) any())).thenReturn(storeTypeCreationEntity);

        StoreTypeCreationEntity storeTypeCreationEntity1 = new StoreTypeCreationEntity();
        storeTypeCreationEntity1.setId(123L);
        storeTypeCreationEntity1.setStatus("Status");
        storeTypeCreationEntity1.setStoreTypeName("Store Type Name");
        when(storeTypeCreationService.getStoreByName((String) any())).thenReturn(true);
        when(storeTypeCreationService.createOrUpdateStoreType((StoreTypeCreationEntity) any()))
                .thenReturn(storeTypeCreationEntity1);

        StoreTypeCreationDTO storeTypeCreationDTO1 = new StoreTypeCreationDTO();
        storeTypeCreationDTO1.setId(123L);
        storeTypeCreationDTO1.setStatus("Status");
        storeTypeCreationDTO1.setStoreTypeName("Store Type Name");
        String content = (new ObjectMapper()).writeValueAsString(storeTypeCreationDTO1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/AccountSetup/StoreTypeCreate/StoreType")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link AccountSetupRestController#storetypeCreate(StoreTypeCreationDTO)}
     */
    @Test
    void testStoretypeCreate2() throws Exception {
        StoreTypeCreationDTO storeTypeCreationDTO = new StoreTypeCreationDTO();
        storeTypeCreationDTO.setId(123L);
        storeTypeCreationDTO.setStatus("Status");
        storeTypeCreationDTO.setStoreTypeName("Store Type Name");

        StoreTypeCreationEntity storeTypeCreationEntity = new StoreTypeCreationEntity();
        storeTypeCreationEntity.setId(123L);
        storeTypeCreationEntity.setStatus("Status");
        storeTypeCreationEntity.setStoreTypeName("Store Type Name");
        when(dTOTransfomers.storeTypeConvertToDto((StoreTypeCreationEntity) any())).thenReturn(storeTypeCreationDTO);
        when(dTOTransfomers.storeTypeConvertToEntity((StoreTypeCreationDTO) any())).thenReturn(storeTypeCreationEntity);
        when(storeTypeCreationService.getStoreByName((String) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));
        when(storeTypeCreationService.createOrUpdateStoreType((StoreTypeCreationEntity) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));

        StoreTypeCreationDTO storeTypeCreationDTO1 = new StoreTypeCreationDTO();
        storeTypeCreationDTO1.setId(123L);
        storeTypeCreationDTO1.setStatus("Status");
        storeTypeCreationDTO1.setStoreTypeName("Store Type Name");
        String content = (new ObjectMapper()).writeValueAsString(storeTypeCreationDTO1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/AccountSetup/StoreTypeCreate/StoreType")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link AccountSetupRestController#storetypeCreate(StoreTypeCreationDTO)}
     */
    @Test
    void testStoretypeCreate3() throws Exception {
        StoreTypeCreationDTO storeTypeCreationDTO = new StoreTypeCreationDTO();
        storeTypeCreationDTO.setId(123L);
        storeTypeCreationDTO.setStatus("Status");
        storeTypeCreationDTO.setStoreTypeName("Store Type Name");

        StoreTypeCreationEntity storeTypeCreationEntity = new StoreTypeCreationEntity();
        storeTypeCreationEntity.setId(123L);
        storeTypeCreationEntity.setStatus("Status");
        storeTypeCreationEntity.setStoreTypeName("Store Type Name");
        when(dTOTransfomers.storeTypeConvertToDto((StoreTypeCreationEntity) any())).thenReturn(storeTypeCreationDTO);
        when(dTOTransfomers.storeTypeConvertToEntity((StoreTypeCreationDTO) any())).thenReturn(storeTypeCreationEntity);

        StoreTypeCreationEntity storeTypeCreationEntity1 = new StoreTypeCreationEntity();
        storeTypeCreationEntity1.setId(123L);
        storeTypeCreationEntity1.setStatus("Status");
        storeTypeCreationEntity1.setStoreTypeName("Store Type Name");
        when(storeTypeCreationService.getStoreByName((String) any())).thenReturn(false);
        when(storeTypeCreationService.createOrUpdateStoreType((StoreTypeCreationEntity) any()))
                .thenReturn(storeTypeCreationEntity1);

        StoreTypeCreationDTO storeTypeCreationDTO1 = new StoreTypeCreationDTO();
        storeTypeCreationDTO1.setId(123L);
        storeTypeCreationDTO1.setStatus("Status");
        storeTypeCreationDTO1.setStoreTypeName("Store Type Name");
        String content = (new ObjectMapper()).writeValueAsString(storeTypeCreationDTO1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/AccountSetup/StoreTypeCreate/StoreType")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<StoreTypeCreationDTO><id>123</id><storeTypeName>Store Type Name</storeTypeName><status>Status</status"
                                        + "></StoreTypeCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#updateCustomer(CustomerCreationDTO, long)}
     */
    @Test
    void testUpdateCustomer() throws Exception {
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
        when(customerCreationService.updateCustomer((CustomerCreationEntity) any())).thenReturn(customerCreationEntity);

        CustomerCreationEntity customerCreationEntity1 = new CustomerCreationEntity();
        customerCreationEntity1.setAddress1("42 Main St");
        customerCreationEntity1.setAddress2("42 Main St");
        customerCreationEntity1.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        customerCreationEntity1.setCreated_date(LocalDate.ofEpochDay(1L));
        customerCreationEntity1.setCreditLimit(1);
        customerCreationEntity1.setCustomerName("Customer Name");
        customerCreationEntity1.setDiscount(3);
        customerCreationEntity1.setDueDays(1);
        customerCreationEntity1.setGstNo("Gst No");
        customerCreationEntity1.setGstType("Gst Type");
        customerCreationEntity1.setId(123L);
        customerCreationEntity1.setMobileNo("Mobile No");
        customerCreationEntity1.setModeOfPayment("Mode Of Payment");
        customerCreationEntity1.setOpeningBal(1);
        customerCreationEntity1.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        customerCreationEntity1.setRateSlab(1);
        customerCreationEntity1.setStatus("Status");
        customerCreationEntity1.setUpdated_by("2020-03-01");
        customerCreationEntity1.setUpdated_date(LocalDate.ofEpochDay(1L));
        when(dTOTransfomers.customerCreationConvertToDto((CustomerCreationEntity) any()))
                .thenReturn(new CustomerCreationDTO());
        when(dTOTransfomers.customerCreationConvertToEntity((CustomerCreationDTO) any()))
                .thenReturn(customerCreationEntity1);

        CustomerCreationDTO customerCreationDTO = new CustomerCreationDTO();
        customerCreationDTO.setAddress1("42 Main St");
        customerCreationDTO.setAddress2("42 Main St");
        customerCreationDTO.setCreditLimit(1);
        customerCreationDTO.setCustomerName("Customer Name");
        customerCreationDTO.setDiscount(3);
        customerCreationDTO.setDueDays(1);
        customerCreationDTO.setGstNo("Gst No");
        customerCreationDTO.setGstType("Gst Type");
        customerCreationDTO.setId(123L);
        customerCreationDTO.setMobileNo("Mobile No");
        customerCreationDTO.setModeOfPayment("Mode Of Payment");
        customerCreationDTO.setOpeningBal(1);
        customerCreationDTO.setOpeningBalDate("2020-03-01");
        customerCreationDTO.setRateSlab(1);
        customerCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(customerCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/AccountSetup/updateCustomer/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<CustomerCreationDTO><id/><customerName/><mobileNo/><address1/><address2/><gstType/><gstNo/><discount"
                                        + "/><rateSlab/><modeOfPayment/><actName/><dueDays/><openingBal/><openingBalDate/><status/></CustomerCreationDTO"
                                        + ">"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllAccountTypes()}
     */
    @Test
    void testGetAllAccountTypes() throws Exception {
        when(accountTypeCreationService.getAllAccountTypes()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getAccountType");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllAccountTypes()}
     */
    @Test
    void testGetAllAccountTypes2() throws Exception {
        when(accountTypeCreationService.getAllAccountTypes())
                .thenThrow(new UnableToProcessException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getAccountType");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }


    /**
     * Method under test: {@link AccountSetupRestController#deleteAreaById(Long)}
     */
    @Test
    void testDeleteAreaById() throws Exception {
        doNothing().when(areaCreationService).deleteAreaById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/AccountSetup/deleteArea/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getSalesManWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetSalesManWithPagination() {
        when(salesManCreationService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<SalesManCreationEntity>> response = accountSetupRestController.getSalesManWithPagination(2, 3, "key", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getSalesManWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetSalesManWithPagination2() {
        when(salesManCreationService.getAllSalesManWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<SalesManCreationEntity>> response = accountSetupRestController.getSalesManWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }


    /**
     * Method under test: {@link AccountSetupRestController#deleteSalesManById(Long)}
     */
    @Test
    void testDeleteSalesManById() throws Exception {
        doNothing().when(salesManCreationService).deleteSalesManById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/AccountSetup/deleteSalesBoy/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#deleteDiscountSlabById(Long)}
     */
    @Test
    void testDeleteDiscountSlabById() throws Exception {
        doNothing().when(discountSlabCreationService).deleteDiscountSlabById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/AccountSetup/deletDiscoutSlab/{id}", 123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#deleteDiscountSlabById(Long)}
     */
    @Test
    void testDeleteDiscountSlabById2() throws Exception {
        doThrow(new UnableToProcessException("An error occurred")).when(discountSlabCreationService)
                .deleteDiscountSlabById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/AccountSetup/deletDiscoutSlab/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#updateDiscountSlab(DiscountSlabCreationDTO, long)}
     */
    @Test
    void testUpdateDiscountSlab() throws Exception {
        DiscountSlabCreationEntity discountSlabCreationEntity = new DiscountSlabCreationEntity();
        discountSlabCreationEntity.setDiscount("3");
        discountSlabCreationEntity.setDiscountSlabName("3");
        discountSlabCreationEntity.setFrom_amt(1);
        discountSlabCreationEntity.setId(123L);
        discountSlabCreationEntity.setStatus("Status");
        discountSlabCreationEntity.setTo_amt(1);
        when(discountSlabCreationService.updateDiscountSlab((DiscountSlabCreationEntity) any()))
                .thenReturn(discountSlabCreationEntity);

        DiscountSlabCreationEntity discountSlabCreationEntity1 = new DiscountSlabCreationEntity();
        discountSlabCreationEntity1.setDiscount("3");
        discountSlabCreationEntity1.setDiscountSlabName("3");
        discountSlabCreationEntity1.setFrom_amt(1);
        discountSlabCreationEntity1.setId(123L);
        discountSlabCreationEntity1.setStatus("Status");
        discountSlabCreationEntity1.setTo_amt(1);
        when(dTOTransfomers.discountSlabConvertToDto((DiscountSlabCreationEntity) any()))
                .thenReturn(new DiscountSlabCreationDTO());
        when(dTOTransfomers.discountConvertToEntity((DiscountSlabCreationDTO) any()))
                .thenReturn(discountSlabCreationEntity1);

        DiscountSlabCreationDTO discountSlabCreationDTO = new DiscountSlabCreationDTO();
        discountSlabCreationDTO.setDiscount("3");
        discountSlabCreationDTO.setDiscountSlabName("3");
        discountSlabCreationDTO.setFrom_amt(1);
        discountSlabCreationDTO.setId(123L);
        discountSlabCreationDTO.setStatus("Status");
        discountSlabCreationDTO.setTo_amt(1);
        String content = (new ObjectMapper()).writeValueAsString(discountSlabCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/AccountSetup/updateDiscoutSlab/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<DiscountSlabCreationDTO><id/><discountSlabName/><discount/><from_amt/><to_amt/><status/></DiscountS"
                                        + "labCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#deleteBranchById(Long)}
     */
    @Test
    void testDeleteBranchById() throws Exception {
        doNothing().when(branchCreationService).deleteBranchById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/AccountSetup/deleteBranch/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#updateBranch(BranchCreationDTO, long)}
     */
    @Test
    void testUpdateBranch() throws Exception {
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
        when(branchCreationService.updateBranch((BranchCreationEntity) any())).thenReturn(branchCreationEntity);

        BranchCreationEntity branchCreationEntity1 = new BranchCreationEntity();
        branchCreationEntity1.setAddreess("42 Main St");
        branchCreationEntity1.setBranchName("janedoe/featurebranch");
        branchCreationEntity1.setContact_name("Contact name");
        branchCreationEntity1.setDlNumber("42");
        branchCreationEntity1.setGstNumber("42");
        branchCreationEntity1.setId(123L);
        branchCreationEntity1.setLocation("Location");
        branchCreationEntity1.setMobileNo("Mobile No");
        branchCreationEntity1.setPincode(1);
        branchCreationEntity1.setStatus("Status");
        when(dTOTransfomers.branchCreationConvertToEntity((BranchCreationDTO) any())).thenReturn(branchCreationEntity1);

        BranchCreationDTO branchCreationDTO = new BranchCreationDTO();
        branchCreationDTO.setAddreess("42 Main St");
        branchCreationDTO.setBranchName("janedoe/featurebranch");
        branchCreationDTO.setContact_name("Contact name");
        branchCreationDTO.setDlNumber("42");
        branchCreationDTO.setGstNumber("42");
        branchCreationDTO.setId(123L);
        branchCreationDTO.setLocation("Location");
        branchCreationDTO.setMobileNo("Mobile No");
        branchCreationDTO.setPincode(1);
        branchCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(branchCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/AccountSetup/updateBranch/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<BranchCreationEntity><id>123</id><branchName>janedoe/featurebranch</branchName><mobileNo>Mobile"
                                + " No</mobileNo><addreess>42 Main St</addreess><location>Location</location><contact_name>Contact"
                                + " name</contact_name><dlNumber>42</dlNumber><gstNumber>42</gstNumber><pincode>1</pincode><status>Status"
                                + "</status></BranchCreationEntity>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#updateBranch(BranchCreationDTO, long)}
     */
    @Test
    void testUpdateBranch2() throws Exception {
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
        when(branchCreationService.updateBranch((BranchCreationEntity) any())).thenReturn(branchCreationEntity);
        when(dTOTransfomers.branchCreationConvertToEntity((BranchCreationDTO) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        BranchCreationDTO branchCreationDTO = new BranchCreationDTO();
        branchCreationDTO.setAddreess("42 Main St");
        branchCreationDTO.setBranchName("janedoe/featurebranch");
        branchCreationDTO.setContact_name("Contact name");
        branchCreationDTO.setDlNumber("42");
        branchCreationDTO.setGstNumber("42");
        branchCreationDTO.setId(123L);
        branchCreationDTO.setLocation("Location");
        branchCreationDTO.setMobileNo("Mobile No");
        branchCreationDTO.setPincode(1);
        branchCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(branchCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/AccountSetup/updateBranch/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#deleteStockById(Long)}
     */
    @Test
    void testDeleteStockById() throws Exception {
        doNothing().when(stockCreationService).deleteStockById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/AccountSetup/deleteStock/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#updateManufacture(ManufacturerCreationDTO, long)}
     */
    @Test
    void testUpdateManufacture() throws Exception {
        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity.setStatus("Status");
        when(dTOTransfomers.manufactureCreationConvertToDto((ManufacturerCreationEntity) any()))
                .thenReturn(new ManufacturerCreationDTO());
        when(dTOTransfomers.manufactureConvertToEntity((ManufacturerCreationDTO) any()))
                .thenReturn(manufacturerCreationEntity);

        ManufacturerCreationEntity manufacturerCreationEntity1 = new ManufacturerCreationEntity();
        manufacturerCreationEntity1.setId(123L);
        manufacturerCreationEntity1.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity1.setStatus("Status");
        when(manufacturerCreationService.updateManufacture((ManufacturerCreationEntity) any()))
                .thenReturn(manufacturerCreationEntity1);

        ManufacturerCreationDTO manufacturerCreationDTO = new ManufacturerCreationDTO();
        manufacturerCreationDTO.setId(123L);
        manufacturerCreationDTO.setManufacturerName("Manufacturer Name");
        manufacturerCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(manufacturerCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/AccountSetup/updateManufacture/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ManufacturerCreationDTO><id/><manufacturerName/><status/></ManufacturerCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#updateManufacture(ManufacturerCreationDTO, long)}
     */
    @Test
    void testUpdateManufacture2() throws Exception {
        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity.setStatus("Status");
        when(dTOTransfomers.manufactureCreationConvertToDto((ManufacturerCreationEntity) any()))
                .thenReturn(new ManufacturerCreationDTO());
        when(dTOTransfomers.manufactureConvertToEntity((ManufacturerCreationDTO) any()))
                .thenReturn(manufacturerCreationEntity);
        when(manufacturerCreationService.updateManufacture((ManufacturerCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        ManufacturerCreationDTO manufacturerCreationDTO = new ManufacturerCreationDTO();
        manufacturerCreationDTO.setId(123L);
        manufacturerCreationDTO.setManufacturerName("Manufacturer Name");
        manufacturerCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(manufacturerCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/AccountSetup/updateManufacture/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }


    /**
     * Method under test: {@link AccountSetupRestController#deleteGroupById(Long)}
     */
    @Test
    void testDeleteGroupById() throws Exception {
        doNothing().when(groupCreationService).deleteGroupById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/AccountSetup/deleteGroup/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#deleteGroupById(Long)}
     */
    @Test
    void testDeleteGroupById2() throws Exception {
        doThrow(new UnableToProcessException("An error occurred")).when(groupCreationService)
                .deleteGroupById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/AccountSetup/deleteGroup/{id}",
                123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#updateGroup(GroupCreationDTO, long)}
     */
    @Test
    void testUpdateGroup() throws Exception {
        GroupCreationEntity groupCreationEntity = new GroupCreationEntity();
        groupCreationEntity.setGroupName("Group Name");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("Status");
        when(dTOTransfomers.groupCreationConvertToDto((GroupCreationEntity) any())).thenReturn(new GroupCreationDTO());
        when(dTOTransfomers.groupConvertToEntity((GroupCreationDTO) any())).thenReturn(groupCreationEntity);

        GroupCreationEntity groupCreationEntity1 = new GroupCreationEntity();
        groupCreationEntity1.setGroupName("Group Name");
        groupCreationEntity1.setId(123L);
        groupCreationEntity1.setStatus("Status");
        when(groupCreationService.updateGroup((GroupCreationEntity) any())).thenReturn(groupCreationEntity1);

        GroupCreationDTO groupCreationDTO = new GroupCreationDTO();
        groupCreationDTO.setGroupName("Group Name");
        groupCreationDTO.setId(123L);
        groupCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(groupCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/AccountSetup/updateGroup/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<GroupCreationDTO><id/><groupName/><status/></GroupCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#updateGroup(GroupCreationDTO, long)}
     */
    @Test
    void testUpdateGroup2() throws Exception {
        GroupCreationEntity groupCreationEntity = new GroupCreationEntity();
        groupCreationEntity.setGroupName("Group Name");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("Status");
        when(dTOTransfomers.groupCreationConvertToDto((GroupCreationEntity) any())).thenReturn(new GroupCreationDTO());
        when(dTOTransfomers.groupConvertToEntity((GroupCreationDTO) any())).thenReturn(groupCreationEntity);
        when(groupCreationService.updateGroup((GroupCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        GroupCreationDTO groupCreationDTO = new GroupCreationDTO();
        groupCreationDTO.setGroupName("Group Name");
        groupCreationDTO.setId(123L);
        groupCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(groupCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/AccountSetup/updateGroup/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#deleteStoreById(Long)}
     */
    @Test
    void testDeleteStoreById() throws Exception {
        doNothing().when(storeTypeCreationService).deleteStoreById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/AccountSetup/deleteStoreType/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#updatePackType(PackCreationDTO, long)}
     */
    @Test
    void testUpdatePackType() throws Exception {
        PackCreationEntity packCreationEntity = new PackCreationEntity();
        packCreationEntity.setId(123L);
        packCreationEntity.setPackName("Pack Name");
        packCreationEntity.setQty(1);
        packCreationEntity.setStatus("Status");
        when(dTOTransfomers.packCreateConvertToDto((PackCreationEntity) any())).thenReturn(new PackCreationDTO());
        when(dTOTransfomers.packCreateConvertToEntity((PackCreationDTO) any())).thenReturn(packCreationEntity);

        PackCreationEntity packCreationEntity1 = new PackCreationEntity();
        packCreationEntity1.setId(123L);
        packCreationEntity1.setPackName("Pack Name");
        packCreationEntity1.setQty(1);
        packCreationEntity1.setStatus("Status");
        when(packCreationService.createOrUpdatePack((PackCreationEntity) any())).thenReturn(packCreationEntity1);

        PackCreationDTO packCreationDTO = new PackCreationDTO();
        packCreationDTO.setId(123L);
        packCreationDTO.setPackName("Pack Name");
        packCreationDTO.setQty(1);
        packCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(packCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/AccountSetup/updatePackType/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<PackCreationDTO><id/><packName/><qty>0</qty><status/></PackCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#updatePackType(PackCreationDTO, long)}
     */
    @Test
    void testUpdatePackType2() throws Exception {
        PackCreationEntity packCreationEntity = new PackCreationEntity();
        packCreationEntity.setId(123L);
        packCreationEntity.setPackName("Pack Name");
        packCreationEntity.setQty(1);
        packCreationEntity.setStatus("Status");
        when(dTOTransfomers.packCreateConvertToDto((PackCreationEntity) any())).thenReturn(new PackCreationDTO());
        when(dTOTransfomers.packCreateConvertToEntity((PackCreationDTO) any())).thenReturn(packCreationEntity);
        when(packCreationService.createOrUpdatePack((PackCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        PackCreationDTO packCreationDTO = new PackCreationDTO();
        packCreationDTO.setId(123L);
        packCreationDTO.setPackName("Pack Name");
        packCreationDTO.setQty(1);
        packCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(packCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/AccountSetup/updatePackType/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#updateStoreType(StoreTypeCreationDTO, long)}
     */
    @Test
    void testUpdateStoreType() throws Exception {
        StoreTypeCreationDTO storeTypeCreationDTO = new StoreTypeCreationDTO();
        storeTypeCreationDTO.setId(123L);
        storeTypeCreationDTO.setStatus("Status");
        storeTypeCreationDTO.setStoreTypeName("Store Type Name");

        StoreTypeCreationEntity storeTypeCreationEntity = new StoreTypeCreationEntity();
        storeTypeCreationEntity.setId(123L);
        storeTypeCreationEntity.setStatus("Status");
        storeTypeCreationEntity.setStoreTypeName("Store Type Name");
        when(dTOTransfomers.storeTypeConvertToDto((StoreTypeCreationEntity) any())).thenReturn(storeTypeCreationDTO);
        when(dTOTransfomers.storeTypeConvertToEntity((StoreTypeCreationDTO) any())).thenReturn(storeTypeCreationEntity);

        StoreTypeCreationEntity storeTypeCreationEntity1 = new StoreTypeCreationEntity();
        storeTypeCreationEntity1.setId(123L);
        storeTypeCreationEntity1.setStatus("Status");
        storeTypeCreationEntity1.setStoreTypeName("Store Type Name");
        when(storeTypeCreationService.updateStoreType((StoreTypeCreationEntity) any()))
                .thenReturn(storeTypeCreationEntity1);

        StoreTypeCreationDTO storeTypeCreationDTO1 = new StoreTypeCreationDTO();
        storeTypeCreationDTO1.setId(123L);
        storeTypeCreationDTO1.setStatus("Status");
        storeTypeCreationDTO1.setStoreTypeName("Store Type Name");
        String content = (new ObjectMapper()).writeValueAsString(storeTypeCreationDTO1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/AccountSetup/updateStoreType/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<StoreTypeCreationDTO><id>123</id><storeTypeName>Store Type Name</storeTypeName><status>Status</status"
                                        + "></StoreTypeCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#updateStoreType(StoreTypeCreationDTO, long)}
     */
    @Test
    void testUpdateStoreType2() throws Exception {
        StoreTypeCreationDTO storeTypeCreationDTO = new StoreTypeCreationDTO();
        storeTypeCreationDTO.setId(123L);
        storeTypeCreationDTO.setStatus("Status");
        storeTypeCreationDTO.setStoreTypeName("Store Type Name");

        StoreTypeCreationEntity storeTypeCreationEntity = new StoreTypeCreationEntity();
        storeTypeCreationEntity.setId(123L);
        storeTypeCreationEntity.setStatus("Status");
        storeTypeCreationEntity.setStoreTypeName("Store Type Name");
        when(dTOTransfomers.storeTypeConvertToDto((StoreTypeCreationEntity) any())).thenReturn(storeTypeCreationDTO);
        when(dTOTransfomers.storeTypeConvertToEntity((StoreTypeCreationDTO) any())).thenReturn(storeTypeCreationEntity);
        when(storeTypeCreationService.updateStoreType((StoreTypeCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        StoreTypeCreationDTO storeTypeCreationDTO1 = new StoreTypeCreationDTO();
        storeTypeCreationDTO1.setId(123L);
        storeTypeCreationDTO1.setStatus("Status");
        storeTypeCreationDTO1.setStoreTypeName("Store Type Name");
        String content = (new ObjectMapper()).writeValueAsString(storeTypeCreationDTO1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/AccountSetup/updateStoreType/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#exportReport3()}
     */
    @Test
    void testExportReport3() throws Exception {
        when(routeCreationService.getAllRoutes()).thenReturn(new ArrayList<>());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
                .formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountSetupRestController#deleteSchedulerById(Long)}
     */
    @Test
    void testDeleteSchedulerById() throws Exception {
        doNothing().when(schedulerCreationService).deleteSchedulerById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/AccountSetup/deleteScheduler/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#AccountCreate(AccountCreationDTO)}
     */
    @Test
    void testAccountCreate() throws Exception {
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
        when(accountCreationService.createOrUpdateAccounts((AccountCreationEntity) any()))
                .thenReturn(accountCreationEntity);

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
        when(dTOTransfomers.accountCreationConvertToEntity((AccountCreationDTO) any())).thenReturn(accountCreationEntity1);

        AccountCreationDTO accountCreationDTO = new AccountCreationDTO();
        accountCreationDTO.setActGroup("Act Group");
        accountCreationDTO.setActName("Act Name");
        accountCreationDTO.setActType("Act Type");
        accountCreationDTO.setId(123L);
        accountCreationDTO.setOpeningBal("Opening Bal");
        accountCreationDTO.setOpeningBalDate("2020-03-01");
        accountCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(accountCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/AccountSetup/AccountCreate/Accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#areaCreate(AreaCreationDTO)}
     */
    @Test
    void testAreaCreate() throws Exception {
        AreaCreationEntity areaCreationEntity = new AreaCreationEntity();
        areaCreationEntity.setAreaName("Area Name");
        areaCreationEntity.setId(123L);
        areaCreationEntity.setRoute("Route");
        areaCreationEntity.setStatus("Status");
        when(areaCreationService.getAreaByName((String) any())).thenReturn(true);
        when(areaCreationService.createOrUpdateArea((AreaCreationEntity) any())).thenReturn(areaCreationEntity);

        AreaCreationDTO areaCreationDTO = new AreaCreationDTO();
        areaCreationDTO.setAreaName("Area Name");
        areaCreationDTO.setId(123L);
        areaCreationDTO.setRoute("Route");
        areaCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(areaCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/AccountSetup/AreaCreate/Area")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link AccountSetupRestController#areaCreate(AreaCreationDTO)}
     */
    @Test
    void testAreaCreate2() throws Exception {
        AreaCreationEntity areaCreationEntity = new AreaCreationEntity();
        areaCreationEntity.setAreaName("Area Name");
        areaCreationEntity.setId(123L);
        areaCreationEntity.setRoute("Route");
        areaCreationEntity.setStatus("Status");
        when(areaCreationService.getAreaByName((String) any())).thenReturn(false);
        when(areaCreationService.createOrUpdateArea((AreaCreationEntity) any())).thenReturn(areaCreationEntity);

        AreaCreationEntity areaCreationEntity1 = new AreaCreationEntity();
        areaCreationEntity1.setAreaName("Area Name");
        areaCreationEntity1.setId(123L);
        areaCreationEntity1.setRoute("Route");
        areaCreationEntity1.setStatus("Status");
        when(dTOTransfomers.areaCreationConvertToDto((AreaCreationEntity) any())).thenReturn(new AreaCreationDTO());
        when(dTOTransfomers.areaCreationConvertToEntity((AreaCreationDTO) any())).thenReturn(areaCreationEntity1);

        AreaCreationDTO areaCreationDTO = new AreaCreationDTO();
        areaCreationDTO.setAreaName("Area Name");
        areaCreationDTO.setId(123L);
        areaCreationDTO.setRoute("Route");
        areaCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(areaCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/AccountSetup/AreaCreate/Area")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<AreaCreationDTO><id/><areaName/><route/><status/></AreaCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#branchCreate(BranchCreationDTO)}
     */
    @Test
    void testBranchCreate() throws Exception {
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
        when(branchCreationService.getBranchByName((String) any())).thenReturn(true);
        when(branchCreationService.createOrUpdateBranch((BranchCreationEntity) any())).thenReturn(branchCreationEntity);

        BranchCreationDTO branchCreationDTO = new BranchCreationDTO();
        branchCreationDTO.setAddreess("42 Main St");
        branchCreationDTO.setBranchName("janedoe/featurebranch");
        branchCreationDTO.setContact_name("Contact name");
        branchCreationDTO.setDlNumber("42");
        branchCreationDTO.setGstNumber("42");
        branchCreationDTO.setId(123L);
        branchCreationDTO.setLocation("Location");
        branchCreationDTO.setMobileNo("Mobile No");
        branchCreationDTO.setPincode(1);
        branchCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(branchCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/AccountSetup/BranchCreate/Branch")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link AccountSetupRestController#branchCreate(BranchCreationDTO)}
     */
    @Test
    void testBranchCreate2() throws Exception {
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
        when(branchCreationService.getBranchByName((String) any())).thenReturn(false);
        when(branchCreationService.createOrUpdateBranch((BranchCreationEntity) any())).thenReturn(branchCreationEntity);

        BranchCreationEntity branchCreationEntity1 = new BranchCreationEntity();
        branchCreationEntity1.setAddreess("42 Main St");
        branchCreationEntity1.setBranchName("janedoe/featurebranch");
        branchCreationEntity1.setContact_name("Contact name");
        branchCreationEntity1.setDlNumber("42");
        branchCreationEntity1.setGstNumber("42");
        branchCreationEntity1.setId(123L);
        branchCreationEntity1.setLocation("Location");
        branchCreationEntity1.setMobileNo("Mobile No");
        branchCreationEntity1.setPincode(1);
        branchCreationEntity1.setStatus("Status");
        when(dTOTransfomers.branchCreationConvertToDto((BranchCreationEntity) any())).thenReturn(new BranchCreationDTO());
        when(dTOTransfomers.branchCreationConvertToEntity((BranchCreationDTO) any())).thenReturn(branchCreationEntity1);

        BranchCreationDTO branchCreationDTO = new BranchCreationDTO();
        branchCreationDTO.setAddreess("42 Main St");
        branchCreationDTO.setBranchName("janedoe/featurebranch");
        branchCreationDTO.setContact_name("Contact name");
        branchCreationDTO.setDlNumber("42");
        branchCreationDTO.setGstNumber("42");
        branchCreationDTO.setId(123L);
        branchCreationDTO.setLocation("Location");
        branchCreationDTO.setMobileNo("Mobile No");
        branchCreationDTO.setPincode(1);
        branchCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(branchCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/AccountSetup/BranchCreate/Branch")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<BranchCreationDTO><id/><branchName/><mobileNo/><addreess/><location/><contact_name/><dl_number/><gst"
                                        + "_number/><pincode/><status/></BranchCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#compostionCreate(CompostionCreationEntity)}
     */
    @Test
    void testCompostionCreate() throws Exception {
        CompostionCreationEntity compostionCreationEntity = new CompostionCreationEntity();
        compostionCreationEntity.setCompName("Comp Name");
        compostionCreationEntity.setId(123L);
        compostionCreationEntity.setStatus("Status");
        when(compostionCreationService.getCompositonByName((String) any())).thenReturn(true);
        when(compostionCreationService.createOrUpdateCompostion((CompostionCreationEntity) any()))
                .thenReturn(compostionCreationEntity);

        CompostionCreationEntity compostionCreationEntity1 = new CompostionCreationEntity();
        compostionCreationEntity1.setCompName("Comp Name");
        compostionCreationEntity1.setId(123L);
        compostionCreationEntity1.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(compostionCreationEntity1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/AccountSetup/CompostionCreate/Composition")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link AccountSetupRestController#compostionCreate(CompostionCreationEntity)}
     */
    @Test
    void testCompostionCreate2() throws Exception {
        CompostionCreationEntity compostionCreationEntity = new CompostionCreationEntity();
        compostionCreationEntity.setCompName("Comp Name");
        compostionCreationEntity.setId(123L);
        compostionCreationEntity.setStatus("Status");
        when(compostionCreationService.getCompositonByName((String) any())).thenReturn(false);
        when(compostionCreationService.createOrUpdateCompostion((CompostionCreationEntity) any()))
                .thenReturn(compostionCreationEntity);

        CompostionCreationEntity compostionCreationEntity1 = new CompostionCreationEntity();
        compostionCreationEntity1.setCompName("Comp Name");
        compostionCreationEntity1.setId(123L);
        compostionCreationEntity1.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(compostionCreationEntity1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/AccountSetup/CompostionCreate/Composition")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#compostionCreate(CompostionCreationEntity)}
     */
    @Test
    void testCompostionCreate3() throws Exception {
        when(compostionCreationService.getCompositonByName((String) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));
        when(compostionCreationService.createOrUpdateCompostion((CompostionCreationEntity) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));

        CompostionCreationEntity compostionCreationEntity = new CompostionCreationEntity();
        compostionCreationEntity.setCompName("Comp Name");
        compostionCreationEntity.setId(123L);
        compostionCreationEntity.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(compostionCreationEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/AccountSetup/CompostionCreate/Composition")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link AccountSetupRestController#compostionCreate(List)}
     */
    @Test
    void testCompostionCreate4() throws Exception {
        doNothing().when(compostionCreationService).createOrUpdateCompostion((List<CompostionCreationEntity>) any());
        when(dTOTransfomers.compisitionConvertToEntityList((List<CompostionCreationDTO>) any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .post("/AccountSetup/CompostionCreate/bulk")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#compostionCreate(List)}
     */
    @Test
    void testCompostionCreate5() throws Exception {
        doThrow(new UnableToProcessException("An error occurred")).when(compostionCreationService).createOrUpdateCompostion((List<CompostionCreationEntity>) any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .post("/AccountSetup/CompostionCreate/bulk")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#customerCreate(CustomerCreationDTO)}
     */
    @Test
    void testCustomerCreate() throws Exception {
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
        when(customerCreationService.createOrUpdateCustomer((CustomerCreationEntity) any()))
                .thenReturn(customerCreationEntity);

        CustomerCreationEntity customerCreationEntity1 = new CustomerCreationEntity();
        customerCreationEntity1.setAddress1("42 Main St");
        customerCreationEntity1.setAddress2("42 Main St");
        customerCreationEntity1.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        customerCreationEntity1.setCreated_date(LocalDate.ofEpochDay(1L));
        customerCreationEntity1.setCreditLimit(1);
        customerCreationEntity1.setCustomerName("Customer Name");
        customerCreationEntity1.setDiscount(3);
        customerCreationEntity1.setDueDays(1);
        customerCreationEntity1.setGstNo("Gst No");
        customerCreationEntity1.setGstType("Gst Type");
        customerCreationEntity1.setId(123L);
        customerCreationEntity1.setMobileNo("Mobile No");
        customerCreationEntity1.setModeOfPayment("Mode Of Payment");
        customerCreationEntity1.setOpeningBal(1);
        customerCreationEntity1.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        customerCreationEntity1.setRateSlab(1);
        customerCreationEntity1.setStatus("Status");
        customerCreationEntity1.setUpdated_by("2020-03-01");
        customerCreationEntity1.setUpdated_date(LocalDate.ofEpochDay(1L));
        when(dTOTransfomers.customerCreationConvertToDto((CustomerCreationEntity) any()))
                .thenReturn(new CustomerCreationDTO());
        when(dTOTransfomers.customerCreationConvertToEntity((CustomerCreationDTO) any()))
                .thenReturn(customerCreationEntity1);

        CustomerCreationDTO customerCreationDTO = new CustomerCreationDTO();
        customerCreationDTO.setAddress1("42 Main St");
        customerCreationDTO.setAddress2("42 Main St");
        customerCreationDTO.setCreditLimit(1);
        customerCreationDTO.setCustomerName("Customer Name");
        customerCreationDTO.setDiscount(3);
        customerCreationDTO.setDueDays(1);
        customerCreationDTO.setGstNo("Gst No");
        customerCreationDTO.setGstType("Gst Type");
        customerCreationDTO.setId(123L);
        customerCreationDTO.setMobileNo("Mobile No");
        customerCreationDTO.setModeOfPayment("Mode Of Payment");
        customerCreationDTO.setOpeningBal(1);
        customerCreationDTO.setOpeningBalDate("2020-03-01");
        customerCreationDTO.setRateSlab(1);
        customerCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(customerCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/AccountSetup/CustomerCreate/Customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<CustomerCreationDTO><id/><customerName/><mobileNo/><address1/><address2/><gstType/><gstNo/><discount"
                                        + "/><rateSlab/><modeOfPayment/><actName/><dueDays/><openingBal/><openingBalDate/><status/></CustomerCreationDTO"
                                        + ">"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#deleteAccount(Long)}
     */
    @Test
    void testDeleteAccount() throws Exception {
        doNothing().when(accountCreationService).deleteAccountsById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/AccountSetup/deleteAccount/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link AccountSetupRestController#deleteCompositionById(Long)}
     */
    @Test
    void testDeleteCompositionById() throws Exception {
        doNothing().when(compostionCreationService).deleteCompostionById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/AccountSetup/deleteCompisition/{id}", 123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#deleteCustomer(Long)}
     */
    @Test
    void testDeleteCustomer() throws Exception {
        doNothing().when(customerCreationService).deleteCustomerById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/AccountSetup/deleteCustomer/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#deleteManfuctureById(Long)}
     */
    @Test
    void testDeleteManfuctureById() throws Exception {
        doNothing().when(manufacturerCreationService).deleteManufactureById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/AccountSetup/deleteManufacture/{id}", 123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#deleteSHSNById(Long)}
     */
    @Test
    void testDeleteSHSNById() throws Exception {
        doNothing().when(hsnSacCreationService).deleteHsnById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/AccountSetup/deleteHSNType/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getHsnType(Long)}
     */
    @Test
    void testGetHsnType() throws Exception {
        HsnSacCreationEntity hsnSacCreationEntity = new HsnSacCreationEntity();
        hsnSacCreationEntity.setDescirption("?");
        hsnSacCreationEntity.setHsnName("?");
        hsnSacCreationEntity.setId(123L);
        hsnSacCreationEntity.setStatus("?");

        when(hsnSacCreationService.getHsnById((Long) any())).thenReturn(hsnSacCreationEntity);
        when(dTOTransfomers.hsnCreationConvertToDto((HsnSacCreationEntity) any()))
                .thenReturn(new HsnSacCreationDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getHSNType/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HsnSacCreationDTO><id/><hsnName/><descirption/><status/></HsnSacCreationDTO>"));
    }


    /**
     * Method under test: {@link AccountSetupRestController#getHsnType(Long)}
     */
    @Test
    void testGetHsnType1() throws Exception {

        when(hsnSacCreationService.getHsnById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getHSNType/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountSetupRestController#deleteSupplier(Long)}
     */
    @Test
    void testDeleteSupplier() throws Exception {
        doNothing().when(supplierCreationService).deleteSupplierById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/AccountSetup/deleteSupplier/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#discountSlabCreate(DiscountSlabCreationDTO)}
     */
    @Test
    void testDiscountSlabCreate() throws Exception {
        DiscountSlabCreationEntity discountSlabCreationEntity = new DiscountSlabCreationEntity();
        discountSlabCreationEntity.setDiscount("3");
        discountSlabCreationEntity.setDiscountSlabName("3");
        discountSlabCreationEntity.setFrom_amt(1);
        discountSlabCreationEntity.setId(123L);
        discountSlabCreationEntity.setStatus("Status");
        discountSlabCreationEntity.setTo_amt(1);
        when(discountSlabCreationService.getDiscountSlabName((String) any())).thenReturn(true);
        when(discountSlabCreationService.createOrUpdateDiscountSlab((DiscountSlabCreationEntity) any()))
                .thenReturn(discountSlabCreationEntity);

        DiscountSlabCreationDTO discountSlabCreationDTO = new DiscountSlabCreationDTO();
        discountSlabCreationDTO.setDiscount("3");
        discountSlabCreationDTO.setDiscountSlabName("3");
        discountSlabCreationDTO.setFrom_amt(1);
        discountSlabCreationDTO.setId(123L);
        discountSlabCreationDTO.setStatus("Status");
        discountSlabCreationDTO.setTo_amt(1);
        String content = (new ObjectMapper()).writeValueAsString(discountSlabCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/AccountSetup/discountSlabCreate/discountSlab")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link AccountSetupRestController#discountSlabCreate(DiscountSlabCreationDTO)}
     */
    @Test
    void testDiscountSlabCreate2() throws Exception {
        DiscountSlabCreationEntity discountSlabCreationEntity = new DiscountSlabCreationEntity();
        discountSlabCreationEntity.setDiscount("3");
        discountSlabCreationEntity.setDiscountSlabName("3");
        discountSlabCreationEntity.setFrom_amt(1);
        discountSlabCreationEntity.setId(123L);
        discountSlabCreationEntity.setStatus("Status");
        discountSlabCreationEntity.setTo_amt(1);
        when(discountSlabCreationService.getDiscountSlabName((String) any())).thenReturn(false);
        when(discountSlabCreationService.createOrUpdateDiscountSlab((DiscountSlabCreationEntity) any()))
                .thenReturn(discountSlabCreationEntity);

        DiscountSlabCreationEntity discountSlabCreationEntity1 = new DiscountSlabCreationEntity();
        discountSlabCreationEntity1.setDiscount("3");
        discountSlabCreationEntity1.setDiscountSlabName("3");
        discountSlabCreationEntity1.setFrom_amt(1);
        discountSlabCreationEntity1.setId(123L);
        discountSlabCreationEntity1.setStatus("Status");
        discountSlabCreationEntity1.setTo_amt(1);
        when(dTOTransfomers.discountSlabConvertToDto((DiscountSlabCreationEntity) any()))
                .thenReturn(new DiscountSlabCreationDTO());
        when(dTOTransfomers.discountConvertToEntity((DiscountSlabCreationDTO) any()))
                .thenReturn(discountSlabCreationEntity1);

        DiscountSlabCreationDTO discountSlabCreationDTO = new DiscountSlabCreationDTO();
        discountSlabCreationDTO.setDiscount("3");
        discountSlabCreationDTO.setDiscountSlabName("3");
        discountSlabCreationDTO.setFrom_amt(1);
        discountSlabCreationDTO.setId(123L);
        discountSlabCreationDTO.setStatus("Status");
        discountSlabCreationDTO.setTo_amt(1);
        String content = (new ObjectMapper()).writeValueAsString(discountSlabCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/AccountSetup/discountSlabCreate/discountSlab")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<DiscountSlabCreationDTO><id/><discountSlabName/><discount/><from_amt/><to_amt/><status/></DiscountS"
                                        + "labCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#discountSlabCreateList(List)}
     */
    @Test
    void testDiscountSlabCreateList() throws Exception {
        doNothing().when(discountSlabCreationService)
                .createOrUpdateDiscountSlabList((List<DiscountSlabCreationEntity>) any());
        when(dTOTransfomers.discountConvertToEntityList((List<DiscountSlabCreationDTO>) any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .post("/AccountSetup/discountSlabCreate/bulk")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#discountSlabCreateList(List)}
     */
    @Test
    void testDiscountSlabCreateList1() throws Exception {
        doThrow(new UnableToProcessException("An error occurred")).when(discountSlabCreationService)
                .createOrUpdateDiscountSlabList((List<DiscountSlabCreationEntity>) any());
        when(dTOTransfomers.discountConvertToEntityList((List<DiscountSlabCreationDTO>) any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .post("/AccountSetup/discountSlabCreate/bulk")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#exportReport13()}
     */
    @Test
    void testExportReport13() throws Exception {
        when(packCreationService.getAllPackList()).thenReturn(new ArrayList<>());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
                .formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountSetupRestController#exportReport14()}
     */
    @Test
    void testExportReport14() throws Exception {
        when(itemCreationService.getAllItemList()).thenReturn(new ArrayList<>());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
                .formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountSetupRestController#exportReport3()}
     */
    @Test
    void testExportReport32() throws Exception {
        when(routeCreationService.getAllRoutes()).thenThrow(new UnableToProcessException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getroutedetailsReport");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#exportReport13()}
     */
    @Test
    void testExportReport132() throws Exception {
        when(packCreationService.getAllPackList()).thenThrow(new UnableToProcessException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getPackDetailsReport");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#exportReport14()}
     */
    @Test
    void testExportReport142() throws Exception {
        when(itemCreationService.getAllItemList()).thenThrow(new UnableToProcessException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getItemDetailsReport");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAccoountHeads()}
     */
    @Test
    void testGetAccoountHeads() throws Exception {
        when(accountHeadCreationService.getAllAccountHead()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getAccountHead");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAccoountHeads()}
     */
    @Test
    void testGetAccoountHeads2() throws Exception {
        when(accountHeadCreationService.getAllAccountHead()).thenThrow(new UnableToProcessException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getAccountHead");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllAreaDetails()}
     */
    @Test
    void testGetAllAreaDetails() throws Exception {
        when(areaCreationService.getAllAreaList()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getAreaDetails");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllBestBeforeDetails()}
     */
    @Test
    void testGetAllBestBeforeDetails() throws Exception {
        when(bestBeforeService.getAllBestBeforeTypes()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getBestBeforeDetails");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllBranchDetails()}
     */
    @Test
    void testGetAllBranchDetails() throws Exception {
        when(branchCreationService.getAllBranchList()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getBranchDetails");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllCompositionDetails()}
     */
    @Test
    void testGetAllCompositionDetails() throws Exception {
        when(compostionCreationService.getAllCompositionList()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getCompositionDetails");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllCustomers()}
     */
    @Test
    void testGetAllCustomers() throws Exception {
        when(customerCreationService.getAllCutomer()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getCustomerDetails");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllDiscountSlabDetails()}
     */
    @Test
    void testGetAllDiscountSlabDetails() throws Exception {
        when(discountSlabCreationService.getAllDisountSlabDetails()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getDiscountDetails");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllGSTTypes()}
     */
    @Test
    void testGetAllGSTTypes() throws Exception {
        when(gSTTypeCreationService.getAllGSTType()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getGstType");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllGroupDetails()}
     */
    @Test
    void testGetAllGroupDetails() throws Exception {
        when(groupCreationService.getAllGroupList()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getGroupDetails");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllGroupDetails()}
     */
    @Test
    void testGetAllGroupDetails2() throws Exception {
        when(dTOTransfomers.groupCreationConvertToDto((GroupCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        GroupCreationEntity groupCreationEntity = new GroupCreationEntity();
        groupCreationEntity.setGroupName("?");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("?");

        ArrayList<GroupCreationEntity> groupCreationEntityList = new ArrayList<>();
        groupCreationEntityList.add(groupCreationEntity);
        when(groupCreationService.getAllGroupList()).thenReturn(groupCreationEntityList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getGroupDetails");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllHSNeDetails()}
     */
    @Test
    void testGetAllHSNeDetails() throws Exception {
        when(hsnSacCreationService.getAllHSNSACList()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getHSNeDetails");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllItemDetails()}
     */
    @Test
    void testGetAllItemDetails() throws Exception {
        when(itemCreationService.getAllItemList()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getItemDetails");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllItemDetails()}
     */
    @Test
    void testGetAllItemDetails2() throws Exception {
        when(dTOTransfomers.itemCreationConvertToDto((ItemCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        CompostionCreationEntity compostionCreationEntity = new CompostionCreationEntity();
        compostionCreationEntity.setCompName("?");
        compostionCreationEntity.setId(123L);
        compostionCreationEntity.setStatus("?");

        DiscountSlabCreationEntity discountSlabCreationEntity = new DiscountSlabCreationEntity();
        discountSlabCreationEntity.setDiscount("3");
        discountSlabCreationEntity.setDiscountSlabName("3");
        discountSlabCreationEntity.setFrom_amt(1);
        discountSlabCreationEntity.setId(123L);
        discountSlabCreationEntity.setStatus("?");
        discountSlabCreationEntity.setTo_amt(1);

        GroupCreationEntity groupCreationEntity = new GroupCreationEntity();
        groupCreationEntity.setGroupName("?");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("?");

        HsnSacCreationEntity hsnSacCreationEntity = new HsnSacCreationEntity();
        hsnSacCreationEntity.setDescirption("?");
        hsnSacCreationEntity.setHsnName("?");
        hsnSacCreationEntity.setId(123L);
        hsnSacCreationEntity.setStatus("?");

        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("?");
        manufacturerCreationEntity.setStatus("?");

        SchedulerCreationEntity schedulerCreationEntity = new SchedulerCreationEntity();
        schedulerCreationEntity.setId(123L);
        schedulerCreationEntity.setSchedulerName("?");
        schedulerCreationEntity.setStatus("?");
        schedulerCreationEntity.setWaringMsg("?");
        schedulerCreationEntity.setWarning("?");

        StoreTypeCreationEntity storeTypeCreationEntity = new StoreTypeCreationEntity();
        storeTypeCreationEntity.setId(123L);
        storeTypeCreationEntity.setStatus("?");
        storeTypeCreationEntity.setStoreTypeName("?");

        ItemCreationEntity itemCreationEntity = new ItemCreationEntity();
        itemCreationEntity.setCompositionEntity(compostionCreationEntity);
        itemCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        itemCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        itemCreationEntity.setDiscSalbEntity(discountSlabCreationEntity);
        itemCreationEntity.setGroupEntity(groupCreationEntity);
        itemCreationEntity.setGst(1);
        itemCreationEntity.setHsnsac(hsnSacCreationEntity);
        itemCreationEntity.setId(123L);
        itemCreationEntity.setItemName("?");
        itemCreationEntity.setManufactureEntity(manufacturerCreationEntity);
        itemCreationEntity.setMax_amt(10.0d);
        itemCreationEntity.setMax_qty(10.0d);
        itemCreationEntity.setMin_amt(10.0d);
        itemCreationEntity.setMin_qty(10.0d);
        itemCreationEntity.setPackName("?");
        itemCreationEntity.setQty_per_pack("?");
        itemCreationEntity.setRateA("?");
        itemCreationEntity.setRateB("?");
        itemCreationEntity.setRateC("?");
        itemCreationEntity.setRateD("?");
        itemCreationEntity.setScheduleEntity(schedulerCreationEntity);
        itemCreationEntity.setStatus("?");
        itemCreationEntity.setStoreTypeEntity(storeTypeCreationEntity);
        itemCreationEntity.setUpdated_by(1);
        itemCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        ArrayList<ItemCreationEntity> itemCreationEntityList = new ArrayList<>();
        itemCreationEntityList.add(itemCreationEntity);
        when(itemCreationService.getAllItemList()).thenReturn(itemCreationEntityList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getItemDetails");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllManufactureDetails()}
     */
    @Test
    void testGetAllManufactureDetails() throws Exception {
        when(manufacturerCreationService.getAllManufactureList()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getManufactureDetails");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllManufactureDetails()}
     */
    @Test
    void testGetAllManufactureDetails2() throws Exception {
        when(dTOTransfomers.manufactureCreationConvertToDto((ManufacturerCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("?");
        manufacturerCreationEntity.setStatus("?");

        ArrayList<ManufacturerCreationEntity> manufacturerCreationEntityList = new ArrayList<>();
        manufacturerCreationEntityList.add(manufacturerCreationEntity);
        when(manufacturerCreationService.getAllManufactureList()).thenReturn(manufacturerCreationEntityList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getManufactureDetails");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllOpenStockGstPercentage()}
     */
    @Test
    void testGetAllOpenStockGstPercentage() throws Exception {
        when(gSTPercentageService.getAllGSTPercentage()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getGSTPercentage");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllOpenStockGstPercentage()}
     */
    @Test
    void testGetAllOpenStockGstPercentage2() throws Exception {
        when(gSTPercentageService.getAllGSTPercentage()).thenThrow(new UnableToProcessException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getGSTPercentage");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllPackDetails()}
     */
    @Test
    void testGetAllPackDetails() throws Exception {
        when(packCreationService.getAllPackList()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getPackDetails");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllRouteTypes()}
     */
    @Test
    void testGetAllRouteTypes() throws Exception {
        when(routeCreationService.getAllRoutes()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getRouteDetails");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllRouteWithPagination(int, int)}
     */
    @Test
    void testGetAllRouteWithPagination() throws Exception {
        when(routeCreationService.getAllRouteWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<RouteCreationEntity>> response = accountSetupRestController.getAllRouteWithPagination(2, 3);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllSalesBoyDetails()}
     */
    @Test
    void testGetAllSalesBoyDetails() throws Exception {
        when(salesManCreationService.getAllSalesManDetails()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getSalesBoyDetails");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllScheduerDetails()}
     */
    @Test
    void testGetAllScheduerDetails() throws Exception {
        when(schedulerCreationService.getAllSchedulerList()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getSchedulerDetails");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllStockDetails()}
     */
    @Test
    void testGetAllStockDetails() throws Exception {
        when(stockCreationService.getAllStock()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getStockDetails");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllStoreTypeDetails()}
     */
    @Test
    void testGetAllStoreTypeDetails() throws Exception {
        when(storeTypeCreationService.getAllStoreTypeList()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getStoreTypeDetails");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllStoreTypeDetails()}
     */
    @Test
    void testGetAllStoreTypeDetails2() throws Exception {
        when(dTOTransfomers.storeTypeConvertToDto((StoreTypeCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        StoreTypeCreationEntity storeTypeCreationEntity = new StoreTypeCreationEntity();
        storeTypeCreationEntity.setId(123L);
        storeTypeCreationEntity.setStatus("?");
        storeTypeCreationEntity.setStoreTypeName("?");

        ArrayList<StoreTypeCreationEntity> storeTypeCreationEntityList = new ArrayList<>();
        storeTypeCreationEntityList.add(storeTypeCreationEntity);
        when(storeTypeCreationService.getAllStoreTypeList()).thenReturn(storeTypeCreationEntityList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getStoreTypeDetails");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getBranch(Long)}
     */
    @Test
    void testGetBranch() throws Exception {
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
        when(branchCreationService.getBranchById((Long) any())).thenReturn(branchCreationEntity);
        when(dTOTransfomers.branchCreationConvertToDto((BranchCreationEntity) any())).thenReturn(new BranchCreationDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getBranch/{id}", 123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<BranchCreationDTO><id/><branchName/><mobileNo/><addreess/><location/><contact_name/><dl_number/><gst"
                                        + "_number/><pincode/><status/></BranchCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getBranch(Long)}
     */
    @Test
    void testGetBranch2() throws Exception {
        when(branchCreationService.getBranchById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getBranch/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getBranchWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetBranchWithPagination() throws Exception {
        when(branchCreationService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<BranchCreationEntity>> response = accountSetupRestController.getBranchWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getBranchWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetBranchWithPagination2() throws Exception {
        when(branchCreationService.getAllBranchWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<BranchCreationEntity>> response = accountSetupRestController.getBranchWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getCompositionWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetCompositionWithPagination() throws Exception {
        when(compostionCreationService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<CompostionCreationEntity>> response = accountSetupRestController.getCompositionWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getCompositionWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetCompositionWithPagination2() throws Exception {
        when(compostionCreationService.getAllCompostionWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<CompostionCreationEntity>> response = accountSetupRestController.getCompositionWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getCompostionType(Long)}
     */
    @Test
    void testGetCompostionType() throws Exception {
        CompostionCreationEntity compostionCreationEntity = new CompostionCreationEntity();
        compostionCreationEntity.setCompName("Comp Name");
        compostionCreationEntity.setId(123L);
        compostionCreationEntity.setStatus("Status");
        when(compostionCreationService.getCompositionById((Long) any())).thenReturn(compostionCreationEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getCompostion/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<CompostionCreationEntity><id>123</id><compName>Comp Name</compName><information/><schedulerCreationEntity/><status>Status</status></CompostionCreationEntity>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getCompostionType(Long)}
     */
    @Test
    void testGetCompostionType2() throws Exception {
        when(compostionCreationService.getCompositionById((Long) any()))
                .thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getCompostion/{id}",
                123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getCustomer(Long)}
     */
    @Test
    void testGetCustomer() throws Exception {
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
        when(customerCreationService.getCustomerById((Long) any())).thenReturn(customerCreationEntity);
        when(dTOTransfomers.customerCreationConvertToDto((CustomerCreationEntity) any()))
                .thenReturn(new CustomerCreationDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getCustomer/{id}", 123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<CustomerCreationDTO><id/><customerName/><mobileNo/><address1/><address2/><gstType/><gstNo/><discount"
                                        + "/><rateSlab/><modeOfPayment/><actName/><dueDays/><openingBal/><openingBalDate/><status/></CustomerCreationDTO"
                                        + ">"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getCustomer(Long)}
     */
    @Test
    void testGetCustomer2() throws Exception {
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
        when(customerCreationService.getCustomerById((Long) any())).thenReturn(null);
        when(dTOTransfomers.customerCreationConvertToDto((CustomerCreationEntity) any()))
                .thenReturn(new CustomerCreationDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getCustomer/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getCustomersWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetCustomersWithPagination() throws Exception {
        when(customerCreationService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<CustomerCreationEntity>> response = accountSetupRestController.getCustomersWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getCustomersWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetCustomersWithPagination2() throws Exception {
        when(customerCreationService.getAllCustomersWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<CustomerCreationEntity>> response = accountSetupRestController.getCustomersWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getDiscountSlabWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetDiscountSlabWithPagination() throws Exception {
        when(discountSlabCreationService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<DiscountSlabCreationEntity>> response = accountSetupRestController.getDiscountSlabWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getDiscountSlabWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetDiscountSlabWithPagination2() throws Exception {
        when(discountSlabCreationService.getAllDiscountSlabWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<DiscountSlabCreationEntity>> response = accountSetupRestController.getDiscountSlabWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getDisountSale(Long)}
     */
    @Test
    void testGetDisountSale() throws Exception {
        DiscountSlabCreationEntity discountSlabCreationEntity = new DiscountSlabCreationEntity();
        discountSlabCreationEntity.setDiscount("3");
        discountSlabCreationEntity.setDiscountSlabName("3");
        discountSlabCreationEntity.setFrom_amt(1);
        discountSlabCreationEntity.setId(123L);
        discountSlabCreationEntity.setStatus("Status");
        discountSlabCreationEntity.setTo_amt(1);
        when(discountSlabCreationService.getDiscountSalbById((Long) any())).thenReturn(discountSlabCreationEntity);
        when(dTOTransfomers.discountSlabConvertToDto((DiscountSlabCreationEntity) any()))
                .thenReturn(new DiscountSlabCreationDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getDiscoutSlab/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<DiscountSlabCreationDTO><id/><discountSlabName/><discount/><from_amt/><to_amt/><status/></DiscountS"
                                        + "labCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getDisountSale(Long)}
     */
    @Test
    void testGetDisountSale2() throws Exception {
        when(discountSlabCreationService.getDiscountSalbById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getDiscoutSlab/{id}",
                123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getGroup(Long)}
     */
    @Test
    void testGetGroup() throws Exception {
        when(dTOTransfomers.groupCreationConvertToDto((GroupCreationEntity) any())).thenReturn(new GroupCreationDTO());

        GroupCreationEntity groupCreationEntity = new GroupCreationEntity();
        groupCreationEntity.setGroupName("Group Name");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("Status");
        when(groupCreationService.getGroupById((Long) any())).thenReturn(groupCreationEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getGroup/{id}", 123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<GroupCreationDTO><id/><groupName/><status/></GroupCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getGroup(Long)}
     */
    @Test
    void testGetGroup2() throws Exception {
        when(groupCreationService.getGroupById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getGroup/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getGroupWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetGroupWithPagination() throws Exception {
        when(groupCreationService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<GroupCreationEntity>> response = accountSetupRestController.getGroupWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getGroupWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetGroupWithPagination2() throws Exception {
        when(groupCreationService.getAllGroupWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<GroupCreationEntity>> response = accountSetupRestController.getGroupWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getHsnSacWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetHsnSacWithPagination() throws Exception {
        when(hsnSacCreationService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<HsnSacCreationEntity>> response = accountSetupRestController.getHsnSacWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getHsnSacWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetHsnSacWithPagination2() throws Exception {
        when(hsnSacCreationService.getAllHsnSacWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<HsnSacCreationEntity>> response = accountSetupRestController.getHsnSacWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getMackIdDetails()}
     */
    @Test
    void testGetMackIdDetails() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getMacIdDetails");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(
                        MockMvcResultMatchers.content().string("<MacIdEntity><id/><macId/><hddId/><osName/></MacIdEntity>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getManufacture(Long)}
     */
    @Test
    void testGetManufacture() throws Exception {
        when(dTOTransfomers.manufactureCreationConvertToDto((ManufacturerCreationEntity) any()))
                .thenReturn(new ManufacturerCreationDTO());

        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity.setStatus("Status");
        when(manufacturerCreationService.getManufactureById((Long) any())).thenReturn(manufacturerCreationEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getManufacture/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ManufacturerCreationDTO><id/><manufacturerName/><status/></ManufacturerCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getManufacture(Long)}
     */
    @Test
    void testGetManufacture2() throws Exception {
        when(manufacturerCreationService.getManufactureById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getManufacture/{id}",
                123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getManufacturerWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetManufacturerWithPagination2() throws Exception {
        when(manufacturerCreationService.getAllManufacturerWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<ManufacturerCreationEntity>> response = accountSetupRestController.getManufacturerWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getManufacturerWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetManufacturerWithPagination() throws Exception {
        when(manufacturerCreationService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<ManufacturerCreationEntity>> response = accountSetupRestController.getManufacturerWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getPackWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetPackWithPagination() throws Exception {
        when(packCreationService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<PackCreationEntity>> response = accountSetupRestController.getPackWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getPackWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetPackWithPagination2() throws Exception {
        when(packCreationService.getAllPackWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<PackCreationEntity>> response = accountSetupRestController.getPackWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getPaymentModes()}
     */
    @Test
    void testGetPaymentModes() throws Exception {
        when(paymentModeService.getAllPaymentModes()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getPaymentModes");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getProductsWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetProductsWithPagination() throws Exception {
        when(itemCreationService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<ItemCreationEntity>> response = accountSetupRestController.getProductsWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getProductsWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetProductsWithPagination2() throws Exception {
        when(itemCreationService.getAllItemWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<ItemCreationEntity>> response = accountSetupRestController.getProductsWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getPurchaseEntrySettings()}
     */
    @Test
    void testGetPurchaseEntrySettings() throws Exception {
        when(warningCreationService.getAllWarningList()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/AccountSetup/getPurchaseEntrySettings");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getSalesBoy(Long)}
     */
    @Test
    void testGetSalesBoy() throws Exception {
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
        when(salesManCreationService.getSalesManById((Long) any())).thenReturn(salesManCreationEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getSaleBoy/{id}", 123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<SalesManCreationEntity><id>123</id><salesManName>Sales Man Name</salesManName><incentive>1</incentive"
                                        + "><target>Target</target><status>Status</status><areaCreation><id>123</id><areaName>Area Name</areaName"
                                        + "><route>Route</route><status>Status</status></areaCreation></SalesManCreationEntity>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getSalesBoy(Long)}
     */
    @Test
    void testGetSalesBoy2() throws Exception {
        when(salesManCreationService.getSalesManById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getSaleBoy/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getSchedulerType(Long)}
     */
    @Test
    void testGetSchedulerType() throws Exception {
        when(dTOTransfomers.schedulerConvertToDto((SchedulerCreationEntity) any()))
                .thenReturn(new SchedulerCreationDTO());

        SchedulerCreationEntity schedulerCreationEntity = new SchedulerCreationEntity();
        schedulerCreationEntity.setId(123L);
        schedulerCreationEntity.setSchedulerName("Scheduler Name");
        schedulerCreationEntity.setStatus("Status");
        schedulerCreationEntity.setWaringMsg("Waring Msg");
        schedulerCreationEntity.setWarning("Warning");
        when(schedulerCreationService.getSchedulerById((Long) any())).thenReturn(schedulerCreationEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getSceduler/{id}", 123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<SchedulerCreationDTO><id/><schedulerName/><waringMsg/><warning/><status/></SchedulerCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getSchedulerType(Long)}
     */
    @Test
    void testGetSchedulerType2() throws Exception {
        when(schedulerCreationService.getSchedulerById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getSceduler/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getSchedulerWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetSchedulerWithPagination() throws Exception {
        when(schedulerCreationService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<SchedulerCreationEntity>> response = accountSetupRestController.getSchedulerWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getSchedulerWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetSchedulerWithPagination2() throws Exception {
        when(schedulerCreationService.getAllSchedulerWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<SchedulerCreationEntity>> response = accountSetupRestController.getSchedulerWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getStock(Long)}
     */
    @Test
    void testGetStock() throws Exception {
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

        StockCreationEntity stockCreationEntity = new StockCreationEntity();
        stockCreationEntity.setBarnchCreation(branchCreationEntity);
        stockCreationEntity.setId(123L);
        stockCreationEntity.setStatus("Status");
        stockCreationEntity.setStockName("Stock Name");
        when(stockCreationService.getStockById((Long) any())).thenReturn(stockCreationEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getStock/{id}", 123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<StockCreationEntity><id>123</id><stockName>Stock Name</stockName><status>Status</status><barnchCreation"
                                        + "><id>123</id><branchName>janedoe/featurebranch</branchName><mobileNo>Mobile No</mobileNo><addreess>42"
                                        + " Main St</addreess><location>Location</location><contact_name>Contact name</contact_name><dlNumber>42"
                                        + "</dlNumber><gstNumber>42</gstNumber><pincode>1</pincode><status>Status</status></barnchCreation><"
                                        + "/StockCreationEntity>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getStock(Long)}
     */
    @Test
    void testGetStock2() throws Exception {
        when(stockCreationService.getStockById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getStock/{id}", 123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getStoreType(Long)}
     */
    @Test
    void testGetStoreType() throws Exception {
        StoreTypeCreationDTO storeTypeCreationDTO = new StoreTypeCreationDTO();
        storeTypeCreationDTO.setId(123L);
        storeTypeCreationDTO.setStatus("Status");
        storeTypeCreationDTO.setStoreTypeName("Store Type Name");
        when(dTOTransfomers.storeTypeConvertToDto((StoreTypeCreationEntity) any())).thenReturn(storeTypeCreationDTO);

        StoreTypeCreationEntity storeTypeCreationEntity = new StoreTypeCreationEntity();
        storeTypeCreationEntity.setId(123L);
        storeTypeCreationEntity.setStatus("Status");
        storeTypeCreationEntity.setStoreTypeName("Store Type Name");
        when(storeTypeCreationService.getStoreById((Long) any())).thenReturn(storeTypeCreationEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getStoreType/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<StoreTypeCreationDTO><id>123</id><storeTypeName>Store Type Name</storeTypeName><status>Status</status"
                                        + "></StoreTypeCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getStoreType(Long)}
     */
    @Test
    void testGetStoreType2() throws Exception {
        when(storeTypeCreationService.getStoreById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getStoreType/{id}",
                123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getStoreTypeWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetStoreTypeWithPagination() throws Exception {
        when(storeTypeCreationService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<StoreTypeCreationEntity>> response = accountSetupRestController.getStoreTypeWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }


    /**
     * Method under test: {@link AccountSetupRestController#getStoreTypeWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetStoreTypeWithPagination2() throws Exception {
        when(storeTypeCreationService.getAllStoreTypeWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<StoreTypeCreationEntity>> response = accountSetupRestController.getStoreTypeWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getSupplier()}
     */
    @Test
    void testGetSupplier() throws Exception {
        when(supplierCreationService.getAllSuppliers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getSuppliers");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getSupplier()}
     */
    @Test
    void testGetSupplier2() throws Exception {
        when(dTOTransfomers.supplierConvertToDto((SupplierCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("?");
        supplierCreationEntity.setGstType("?");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("?");
        supplierCreationEntity.setModeOfPayment("?");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("?");
        supplierCreationEntity.setStatus("?");
        supplierCreationEntity.setSupplierName("?");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        ArrayList<SupplierCreationEntity> supplierCreationEntityList = new ArrayList<>();
        supplierCreationEntityList.add(supplierCreationEntity);
        when(supplierCreationService.getAllSuppliers()).thenReturn(supplierCreationEntityList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getSuppliers");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getSupplier(Long)}
     */
    @Test
    void testGetSupplier3() throws Exception {
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
        when(dTOTransfomers.supplierConvertToDto((SupplierCreationEntity) any())).thenReturn(supplierCreationDTO);

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
        when(supplierCreationService.getSupplierById((Long) any())).thenReturn(supplierCreationEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getSupplier/{id}", 123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<SupplierCreationDTO><id>123</id><supplierName>Supplier Name</supplierName><mobileNo>Mobile"
                                + " No</mobileNo><address1>42 Main St</address1><address2>42 Main St</address2><gstNo>Gst No</gstNo><gstType>Gst"
                                + " Type</gstType><credit_days>1</credit_days><discount>3</discount><rateSlabrateSlab>Rate Slab<"
                                + "/rateSlabrateSlab><modeOfPayment>Mode Of Payment</modeOfPayment><openingBal>1</openingBal><opening_Bal"
                                + "_date>2020-03-01</opening_Bal_date><status>Status</status></SupplierCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getSupplier(Long)}
     */
    @Test
    void testGetSupplier4() throws Exception {
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
        when(dTOTransfomers.supplierConvertToDto((SupplierCreationEntity) any())).thenReturn(supplierCreationDTO);
        when(supplierCreationService.getSupplierById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getSupplier/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getSuppliersWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetSuppliersWithPagination() throws Exception {
        when(supplierCreationService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<SupplierCreationEntity>> response = accountSetupRestController.getSuppliersWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getSuppliersWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetSuppliersWithPagination2() throws Exception {
        when(supplierCreationService.getAllSuppliersWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<SupplierCreationEntity>> response = accountSetupRestController.getSuppliersWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#updateSupplier(SupplierCreationDTO, long)}
     */
    @Test
    void testUpdateSupplier() throws Exception {
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
        when(dTOTransfomers.supplierConvertToDto((SupplierCreationEntity) any())).thenReturn(supplierCreationDTO);
        when(dTOTransfomers.supplierConvertToEntity((SupplierCreationDTO) any())).thenReturn(supplierCreationEntity);

        SupplierCreationEntity supplierCreationEntity1 = new SupplierCreationEntity();
        supplierCreationEntity1.setAddress1("42 Main St");
        supplierCreationEntity1.setAddress2("42 Main St");
        supplierCreationEntity1.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity1.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity1.setCredit_days(1);
        supplierCreationEntity1.setDiscount("3");
        supplierCreationEntity1.setGstNo("Gst No");
        supplierCreationEntity1.setGstType("Gst Type");
        supplierCreationEntity1.setId(123L);
        supplierCreationEntity1.setMobileNo("Mobile No");
        supplierCreationEntity1.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity1.setOpeningBal(1L);
        supplierCreationEntity1.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity1.setRateSlab("Rate Slab");
        supplierCreationEntity1.setStatus("Status");
        supplierCreationEntity1.setSupplierName("Supplier Name");
        supplierCreationEntity1.setUpdated_by(1);
        supplierCreationEntity1.setUpdated_date(LocalDate.ofEpochDay(1L));
        when(supplierCreationService.updateSupplier((SupplierCreationEntity) any())).thenReturn(supplierCreationEntity1);

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
        String content = (new ObjectMapper()).writeValueAsString(supplierCreationDTO1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/AccountSetup/updateSupplier/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#updateSupplier(SupplierCreationDTO, long)}
     */
    @Test
    void testUpdateSupplier2() throws Exception {
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
        when(dTOTransfomers.supplierConvertToDto((SupplierCreationEntity) any())).thenReturn(supplierCreationDTO);
        when(dTOTransfomers.supplierConvertToEntity((SupplierCreationDTO) any())).thenReturn(supplierCreationEntity);
        when(supplierCreationService.updateSupplier((SupplierCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

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
        String content = (new ObjectMapper()).writeValueAsString(supplierCreationDTO1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/AccountSetup/updateSupplier/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getWarningDetails()}
     */
    @Test
    void testGetWarningDetails() throws Exception {
        when(warningCreationService.getAllWarningList()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getWaringDetails");
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getWarningDetails()}
     */
    @Test
    void testGetWarningDetails2() throws Exception {
        when(warningCreationService.getAllWarningList()).thenThrow(new UnableToProcessException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getWaringDetails");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#groupCreate(GroupCreationDTO)}
     */
    @Test
    void testGroupCreate() throws Exception {
        GroupCreationEntity groupCreationEntity = new GroupCreationEntity();
        groupCreationEntity.setGroupName("Group Name");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("Status");
        when(dTOTransfomers.groupCreationConvertToDto((GroupCreationEntity) any())).thenReturn(new GroupCreationDTO());
        when(dTOTransfomers.groupConvertToEntity((GroupCreationDTO) any())).thenReturn(groupCreationEntity);

        GroupCreationEntity groupCreationEntity1 = new GroupCreationEntity();
        groupCreationEntity1.setGroupName("Group Name");
        groupCreationEntity1.setId(123L);
        groupCreationEntity1.setStatus("Status");
        when(groupCreationService.getGroupByName((String) any())).thenReturn(true);
        when(groupCreationService.createOrUpdateGroup((GroupCreationEntity) any())).thenReturn(groupCreationEntity1);

        GroupCreationDTO groupCreationDTO = new GroupCreationDTO();
        groupCreationDTO.setGroupName("Group Name");
        groupCreationDTO.setId(123L);
        groupCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(groupCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/AccountSetup/GroupCreate/Group")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link AccountSetupRestController#groupCreate(GroupCreationDTO)}
     */
    @Test
    void testGroupCreate2() throws Exception {
        GroupCreationEntity groupCreationEntity = new GroupCreationEntity();
        groupCreationEntity.setGroupName("Group Name");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("Status");
        when(dTOTransfomers.groupCreationConvertToDto((GroupCreationEntity) any())).thenReturn(new GroupCreationDTO());
        when(dTOTransfomers.groupConvertToEntity((GroupCreationDTO) any())).thenReturn(groupCreationEntity);
        when(groupCreationService.getGroupByName((String) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));
        when(groupCreationService.createOrUpdateGroup((GroupCreationEntity) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));

        GroupCreationDTO groupCreationDTO = new GroupCreationDTO();
        groupCreationDTO.setGroupName("Group Name");
        groupCreationDTO.setId(123L);
        groupCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(groupCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/AccountSetup/GroupCreate/Group")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link AccountSetupRestController#groupCreate(GroupCreationDTO)}
     */
    @Test
    void testGroupCreate3() throws Exception {
        GroupCreationEntity groupCreationEntity = new GroupCreationEntity();
        groupCreationEntity.setGroupName("Group Name");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("Status");
        when(dTOTransfomers.groupCreationConvertToDto((GroupCreationEntity) any())).thenReturn(new GroupCreationDTO());
        when(dTOTransfomers.groupConvertToEntity((GroupCreationDTO) any())).thenReturn(groupCreationEntity);

        GroupCreationEntity groupCreationEntity1 = new GroupCreationEntity();
        groupCreationEntity1.setGroupName("Group Name");
        groupCreationEntity1.setId(123L);
        groupCreationEntity1.setStatus("Status");
        when(groupCreationService.getGroupByName((String) any())).thenReturn(false);
        when(groupCreationService.createOrUpdateGroup((GroupCreationEntity) any())).thenReturn(groupCreationEntity1);

        GroupCreationDTO groupCreationDTO = new GroupCreationDTO();
        groupCreationDTO.setGroupName("Group Name");
        groupCreationDTO.setId(123L);
        groupCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(groupCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/AccountSetup/GroupCreate/Group")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<GroupCreationDTO><id/><groupName/><status/></GroupCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#groupCreate(List)}
     */
    @Test
    void testGroupCreate4() throws Exception {
        when(dTOTransfomers.groupConvertToEntityList((List<GroupCreationDTO>) any())).thenReturn(new ArrayList<>());
        doNothing().when(groupCreationService).createOrUpdateGroup((List<GroupCreationEntity>) any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/AccountSetup/GroupCreate/bulk")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#groupCreate(List)}
     */
    @Test
    void testGroupCreate5() throws Exception {
        when(dTOTransfomers.groupConvertToEntityList((List<GroupCreationDTO>) any())).thenReturn(new ArrayList<>());
        doThrow(new UnableToProcessException("An error occurred")).when(groupCreationService)
                .createOrUpdateGroup((List<GroupCreationEntity>) any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/AccountSetup/GroupCreate/bulk")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#hsntypeCreateList(List)}
     */
    @Test
    void testHsntypeCreateList() throws Exception {
        when(dTOTransfomers.hsnConvertToEntityList((List<HsnSacCreationDTO>) any())).thenReturn(new ArrayList<>());
        doNothing().when(hsnSacCreationService).createOrUpdateHsnList((List<HsnSacCreationEntity>) any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/AccountSetup/HSNCreate/bulk")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#hsntypeCreateList(List)}
     */
    @Test
    void testHsntypeCreateList2() throws Exception {
        when(dTOTransfomers.hsnConvertToEntityList((List<HsnSacCreationDTO>) any())).thenReturn(new ArrayList<>());
        doThrow(new UnableToProcessException("An error occurred")).when(hsnSacCreationService)
                .createOrUpdateHsnList((List<HsnSacCreationEntity>) any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/AccountSetup/HSNCreate/bulk")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#itemCreate(ItemCreationDTO)}
     */
    @Test
    void testItemCreate() throws Exception {
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
        when(dTOTransfomers.itemCreationConvertToDto((ItemCreationEntity) any())).thenReturn(new ItemCreationDTO());
        when(dTOTransfomers.itemConvertToEntity((ItemCreationDTO) any())).thenReturn(itemCreationEntity);

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
        when(itemCreationService.createOrUpdateItem((ItemCreationEntity) any())).thenReturn(itemCreationEntity1);

        CompostionCreationEntity compostionCreationEntity2 = new CompostionCreationEntity();
        compostionCreationEntity2.setCompName("Comp Name");
        compostionCreationEntity2.setId(123L);
        compostionCreationEntity2.setStatus("Status");

        DiscountSlabCreationEntity discountSlabCreationEntity2 = new DiscountSlabCreationEntity();
        discountSlabCreationEntity2.setDiscount("3");
        discountSlabCreationEntity2.setDiscountSlabName("3");
        discountSlabCreationEntity2.setFrom_amt(1);
        discountSlabCreationEntity2.setId(123L);
        discountSlabCreationEntity2.setStatus("Status");
        discountSlabCreationEntity2.setTo_amt(1);

        GroupCreationEntity groupCreationEntity2 = new GroupCreationEntity();
        groupCreationEntity2.setGroupName("Group Name");
        groupCreationEntity2.setId(123L);
        groupCreationEntity2.setStatus("Status");

        HsnSacCreationEntity hsnSacCreationEntity2 = new HsnSacCreationEntity();
        hsnSacCreationEntity2.setDescirption("Descirption");
        hsnSacCreationEntity2.setHsnName("Hsn Name");
        hsnSacCreationEntity2.setId(123L);
        hsnSacCreationEntity2.setStatus("Status");

        ManufacturerCreationEntity manufacturerCreationEntity2 = new ManufacturerCreationEntity();
        manufacturerCreationEntity2.setId(123L);
        manufacturerCreationEntity2.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity2.setStatus("Status");

        SchedulerCreationEntity schedulerCreationEntity2 = new SchedulerCreationEntity();
        schedulerCreationEntity2.setId(123L);
        schedulerCreationEntity2.setSchedulerName("Scheduler Name");
        schedulerCreationEntity2.setStatus("Status");
        schedulerCreationEntity2.setWaringMsg("Waring Msg");
        schedulerCreationEntity2.setWarning("Warning");

        StoreTypeCreationEntity storeTypeCreationEntity2 = new StoreTypeCreationEntity();
        storeTypeCreationEntity2.setId(123L);
        storeTypeCreationEntity2.setStatus("Status");
        storeTypeCreationEntity2.setStoreTypeName("Store Type Name");

        ItemCreationDTO itemCreationDTO = new ItemCreationDTO();
        itemCreationDTO.setCompositionEntity(compostionCreationEntity2);
        itemCreationDTO.setDiscSalbEntity(discountSlabCreationEntity2);
        itemCreationDTO.setGroupEntity(groupCreationEntity2);
        itemCreationDTO.setGst(1);
        itemCreationDTO.setHsnsac(hsnSacCreationEntity2);
        itemCreationDTO.setId(123L);
        itemCreationDTO.setItemName("Item Name");
        itemCreationDTO.setManufactureEntity(manufacturerCreationEntity2);
        itemCreationDTO.setMax_amt(10.0d);
        itemCreationDTO.setMax_qty(10.0d);
        itemCreationDTO.setMin_amt(10.0d);
        itemCreationDTO.setMin_qty(10.0d);
        itemCreationDTO.setPackName("Pack Name");
        itemCreationDTO.setQty_per_pack("Qty per pack");
        itemCreationDTO.setRateA("Rate A");
        itemCreationDTO.setRateB("Rate B");
        itemCreationDTO.setRateC("Rate C");
        itemCreationDTO.setRateD("Rate D");
        itemCreationDTO.setScheduleEntity(schedulerCreationEntity2);
        itemCreationDTO.setStatus("Status");
        itemCreationDTO.setStoreTypeEntity(storeTypeCreationEntity2);
        String content = (new ObjectMapper()).writeValueAsString(itemCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/AccountSetup/itemCreate/Item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<ItemCreationDTO><id/><itemName/><packName/><qty_per_pack/><min_amt>0.0</min_amt><max_amt>0.0</max_amt"
                                        + "><min_qty>0.0</min_qty><max_qty>0.0</max_qty><gst/><rateA/><rateB/><rateC/><rateD/><status/>"
                                        + "<manufactureEntity/><groupEntity/><storeTypeEntity/><scheduleEntity/><compositionEntity/><discSalbEntity"
                                        + "/><hsnsac/></ItemCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#itemCreate(ItemCreationDTO)}
     */
    @Test
    void testItemCreate2() throws Exception {
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
        when(dTOTransfomers.itemCreationConvertToDto((ItemCreationEntity) any())).thenReturn(new ItemCreationDTO());
        when(dTOTransfomers.itemConvertToEntity((ItemCreationDTO) any())).thenReturn(itemCreationEntity);
        when(itemCreationService.createOrUpdateItem((ItemCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

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

        ItemCreationDTO itemCreationDTO = new ItemCreationDTO();
        itemCreationDTO.setCompositionEntity(compostionCreationEntity1);
        itemCreationDTO.setDiscSalbEntity(discountSlabCreationEntity1);
        itemCreationDTO.setGroupEntity(groupCreationEntity1);
        itemCreationDTO.setGst(1);
        itemCreationDTO.setHsnsac(hsnSacCreationEntity1);
        itemCreationDTO.setId(123L);
        itemCreationDTO.setItemName("Item Name");
        itemCreationDTO.setManufactureEntity(manufacturerCreationEntity1);
        itemCreationDTO.setMax_amt(10.0d);
        itemCreationDTO.setMax_qty(10.0d);
        itemCreationDTO.setMin_amt(10.0d);
        itemCreationDTO.setMin_qty(10.0d);
        itemCreationDTO.setPackName("Pack Name");
        itemCreationDTO.setQty_per_pack("Qty per pack");
        itemCreationDTO.setRateA("Rate A");
        itemCreationDTO.setRateB("Rate B");
        itemCreationDTO.setRateC("Rate C");
        itemCreationDTO.setRateD("Rate D");
        itemCreationDTO.setScheduleEntity(schedulerCreationEntity1);
        itemCreationDTO.setStatus("Status");
        itemCreationDTO.setStoreTypeEntity(storeTypeCreationEntity1);
        String content = (new ObjectMapper()).writeValueAsString(itemCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/AccountSetup/itemCreate/Item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#itemCreate(List)}
     */
    @Test
    void testItemCreate3() throws Exception {
        when(dTOTransfomers.itemConvertToEntityList((List<ItemCreationDTO>) any())).thenReturn(new ArrayList<>());
        doNothing().when(itemCreationService).createOrUpdateItem((List<ItemCreationEntity>) any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/AccountSetup/itemCreate/bulk")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#itemCreate(List)}
     */
    @Test
    void testItemCreate4() throws Exception {
        when(dTOTransfomers.itemConvertToEntityList((List<ItemCreationDTO>) any())).thenReturn(new ArrayList<>());
        doThrow(new UnableToProcessException("An error occurred")).when(itemCreationService)
                .createOrUpdateItem((List<ItemCreationEntity>) any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/AccountSetup/itemCreate/bulk")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#manufacturerkCreate(ManufacturerCreationDTO)}
     */
    @Test
    void testManufacturerkCreate() throws Exception {
        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity.setStatus("Status");
        when(dTOTransfomers.manufactureCreationConvertToDto((ManufacturerCreationEntity) any()))
                .thenReturn(new ManufacturerCreationDTO());
        when(dTOTransfomers.manufactureConvertToEntity((ManufacturerCreationDTO) any()))
                .thenReturn(manufacturerCreationEntity);

        ManufacturerCreationEntity manufacturerCreationEntity1 = new ManufacturerCreationEntity();
        manufacturerCreationEntity1.setId(123L);
        manufacturerCreationEntity1.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity1.setStatus("Status");
        when(manufacturerCreationService.getManufactureByName((String) any())).thenReturn(true);
        when(manufacturerCreationService.createOrUpdateManufacture((ManufacturerCreationEntity) any()))
                .thenReturn(manufacturerCreationEntity1);

        ManufacturerCreationDTO manufacturerCreationDTO = new ManufacturerCreationDTO();
        manufacturerCreationDTO.setId(123L);
        manufacturerCreationDTO.setManufacturerName("Manufacturer Name");
        manufacturerCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(manufacturerCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/AccountSetup/ManfactureCreate/Manufacturer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link AccountSetupRestController#manufacturerkCreate(ManufacturerCreationDTO)}
     */
    @Test
    void testManufacturerkCreate2() throws Exception {
        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity.setStatus("Status");
        when(dTOTransfomers.manufactureCreationConvertToDto((ManufacturerCreationEntity) any()))
                .thenReturn(new ManufacturerCreationDTO());
        when(dTOTransfomers.manufactureConvertToEntity((ManufacturerCreationDTO) any()))
                .thenReturn(manufacturerCreationEntity);
        when(manufacturerCreationService.getManufactureByName((String) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));
        when(manufacturerCreationService.createOrUpdateManufacture((ManufacturerCreationEntity) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));

        ManufacturerCreationDTO manufacturerCreationDTO = new ManufacturerCreationDTO();
        manufacturerCreationDTO.setId(123L);
        manufacturerCreationDTO.setManufacturerName("Manufacturer Name");
        manufacturerCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(manufacturerCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/AccountSetup/ManfactureCreate/Manufacturer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link AccountSetupRestController#manufacturerkCreate(ManufacturerCreationDTO)}
     */
    @Test
    void testManufacturerkCreate3() throws Exception {
        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity.setStatus("Status");
        when(dTOTransfomers.manufactureCreationConvertToDto((ManufacturerCreationEntity) any()))
                .thenReturn(new ManufacturerCreationDTO());
        when(dTOTransfomers.manufactureConvertToEntity((ManufacturerCreationDTO) any()))
                .thenReturn(manufacturerCreationEntity);

        ManufacturerCreationEntity manufacturerCreationEntity1 = new ManufacturerCreationEntity();
        manufacturerCreationEntity1.setId(123L);
        manufacturerCreationEntity1.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity1.setStatus("Status");
        when(manufacturerCreationService.getManufactureByName((String) any())).thenReturn(false);
        when(manufacturerCreationService.createOrUpdateManufacture((ManufacturerCreationEntity) any()))
                .thenReturn(manufacturerCreationEntity1);

        ManufacturerCreationDTO manufacturerCreationDTO = new ManufacturerCreationDTO();
        manufacturerCreationDTO.setId(123L);
        manufacturerCreationDTO.setManufacturerName("Manufacturer Name");
        manufacturerCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(manufacturerCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/AccountSetup/ManfactureCreate/Manufacturer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ManufacturerCreationDTO><id/><manufacturerName/><status/></ManufacturerCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#manufacturerkCreate(List)}
     */
    @Test
    void testManufacturerkCreate4() throws Exception {
        when(dTOTransfomers.manufactureConvertToEntityList((List<ManufacturerCreationDTO>) any()))
                .thenReturn(new ArrayList<>());
        doNothing().when(manufacturerCreationService)
                .createOrUpdateManufactureList((List<ManufacturerCreationEntity>) any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .post("/AccountSetup/ManfactureCreate/bulk")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#manufacturerkCreate(List)}
     */
    @Test
    void testManufacturerkCreate5() throws Exception {
        when(dTOTransfomers.manufactureConvertToEntityList((List<ManufacturerCreationDTO>) any()))
                .thenReturn(new ArrayList<>());
        doThrow(new UnableToProcessException("An error occurred")).when(manufacturerCreationService)
                .createOrUpdateManufactureList((List<ManufacturerCreationEntity>) any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .post("/AccountSetup/ManfactureCreate/bulk")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#deletePackById(Long)}
     */
    @Test
    void testDeletePackById() throws Exception {
        doNothing().when(packCreationService).deletePackById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/AccountSetup/deletePack/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#getAllItemDetails(String, Pageable)}
     */
    @Test
    void testGetAllItemDetails3() {
        when(itemCreationService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<ItemCreationEntity>> response = accountSetupRestController.getAllItemDetails("Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getItemDetails(Long)}
     */
    @Test
    void testGetItemDetails() throws Exception {
        when(itemCreationService.getItemById(Mockito.anyLong())).thenReturn(mock(ItemCreationEntity.class));
        when(dTOTransfomers.itemCreationConvertToDto(mock(ItemCreationEntity.class))).thenReturn(mock(ItemCreationDTO.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getItem/{id}", 123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link AccountSetupRestController#getItemDetails(Long)}
     */
    @Test
    void testGetItemDetails2() throws Exception {
        doReturn(null).when(itemCreationService).getItemById(Mockito.anyLong());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getItem/{id}", 123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountSetupRestController#deleteItemById(Long)}
     */
    @Test
    void testDeleteItemById() throws Exception {
        doNothing().when(itemCreationService).deleteItemById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/AccountSetup/deleteItem/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#AccountCreate(AccountCreationDTO)}
     */
    @Test
    void testAccountCreate2() throws Exception {
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
        when(dTOTransfomers.accountCreationConvertToEntity((AccountCreationDTO) any()))
                .thenReturn(accountCreationEntity);

        AccountCreationDTO accountCreationDTO = new AccountCreationDTO();
        accountCreationDTO.setActGroup("Act Group");
        accountCreationDTO.setActName("Act Name");
        accountCreationDTO.setActType("Act Type");
        accountCreationDTO.setId(123L);
        accountCreationDTO.setOpeningBal("Opening Bal");
        accountCreationDTO.setOpeningBalDate("2020-03-01");
        accountCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(accountCreationDTO);
        when(accountCreationService.createOrUpdateAccounts((AccountCreationEntity) any()))
                .thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/AccountSetup/AccountCreate/Accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>BAD_REQUEST</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#AccountCreateBulk(List)}
     */
    @Test
    void testAccountCreateBulk() throws Exception {
        doNothing().when(accountCreationService).createOrUpdateAccountsBulk((List<AccountCreationEntity>) any());
        when(dTOTransfomers.accountCreationConvertToEntityList((List<AccountCreationDTO>) any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/AccountSetup/AccountCreate/bulk")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#AccountCreateBulk(List)}
     */
    @Test
    void testAccountCreateBulk2() throws Exception {
        doThrow(new UnableToProcessException("Currently unable to process request")).when(accountCreationService).createOrUpdateAccountsBulk((List<AccountCreationEntity>) any());
        when(dTOTransfomers.accountCreationConvertToEntityList((List<AccountCreationDTO>) any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/AccountSetup/AccountCreate/bulk")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#PackCreate(PackCreationDTO)}
     */
    @Test
    void testPackCreate() throws Exception {
        PackCreationEntity packCreationEntity = new PackCreationEntity();
        packCreationEntity.setId(123L);
        packCreationEntity.setPackName("Pack Name");
        packCreationEntity.setQty(1);
        packCreationEntity.setStatus("Status");
        when(dTOTransfomers.packCreateConvertToDto((PackCreationEntity) any())).thenReturn(new PackCreationDTO());
        when(dTOTransfomers.packCreateConvertToEntity((PackCreationDTO) any())).thenReturn(packCreationEntity);

        PackCreationEntity packCreationEntity1 = new PackCreationEntity();
        packCreationEntity1.setId(123L);
        packCreationEntity1.setPackName("Pack Name");
        packCreationEntity1.setQty(1);
        packCreationEntity1.setStatus("Status");
        when(packCreationService.getPackByName((String) any())).thenReturn(true);
        when(packCreationService.createOrUpdatePack((PackCreationEntity) any())).thenReturn(packCreationEntity1);

        PackCreationDTO packCreationDTO = new PackCreationDTO();
        packCreationDTO.setId(123L);
        packCreationDTO.setPackName("Pack Name");
        packCreationDTO.setQty(1);
        packCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(packCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/AccountSetup/PackCreate/Pack")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link AccountSetupRestController#PackCreate(PackCreationDTO)}
     */
    @Test
    void testPackCreate2() throws Exception {
        PackCreationEntity packCreationEntity = new PackCreationEntity();
        packCreationEntity.setId(123L);
        packCreationEntity.setPackName("Pack Name");
        packCreationEntity.setQty(1);
        packCreationEntity.setStatus("Status");
        when(dTOTransfomers.packCreateConvertToDto((PackCreationEntity) any())).thenReturn(new PackCreationDTO());
        when(dTOTransfomers.packCreateConvertToEntity((PackCreationDTO) any())).thenReturn(packCreationEntity);
        when(packCreationService.getPackByName((String) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));
        when(packCreationService.createOrUpdatePack((PackCreationEntity) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));

        PackCreationDTO packCreationDTO = new PackCreationDTO();
        packCreationDTO.setId(123L);
        packCreationDTO.setPackName("Pack Name");
        packCreationDTO.setQty(1);
        packCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(packCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/AccountSetup/PackCreate/Pack")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link AccountSetupRestController#PackCreate(PackCreationDTO)}
     */
    @Test
    void testPackCreate3() throws Exception {
        PackCreationEntity packCreationEntity = new PackCreationEntity();
        packCreationEntity.setId(123L);
        packCreationEntity.setPackName("Pack Name");
        packCreationEntity.setQty(1);
        packCreationEntity.setStatus("Status");
        when(dTOTransfomers.packCreateConvertToDto((PackCreationEntity) any())).thenReturn(new PackCreationDTO());
        when(dTOTransfomers.packCreateConvertToEntity((PackCreationDTO) any())).thenReturn(packCreationEntity);

        PackCreationEntity packCreationEntity1 = new PackCreationEntity();
        packCreationEntity1.setId(123L);
        packCreationEntity1.setPackName("Pack Name");
        packCreationEntity1.setQty(1);
        packCreationEntity1.setStatus("Status");
        when(packCreationService.getPackByName((String) any())).thenReturn(false);
        when(packCreationService.createOrUpdatePack((PackCreationEntity) any())).thenReturn(packCreationEntity1);

        PackCreationDTO packCreationDTO = new PackCreationDTO();
        packCreationDTO.setId(123L);
        packCreationDTO.setPackName("Pack Name");
        packCreationDTO.setQty(1);
        packCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(packCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/AccountSetup/PackCreate/Pack")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<PackCreationDTO><id/><packName/><qty>0</qty><status/></PackCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#retrieveUser(Long)}
     */
    @Test
    void testRetrieveUser() throws Exception {
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
        when(accountCreationService.getAccountsById((Long) any())).thenReturn(accountCreationEntity);
        when(dTOTransfomers.accountCreationConvertToDto((AccountCreationEntity) any()))
                .thenReturn(new AccountCreationDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getAccount/{id}", 123L);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<AccountCreationDTO><id/><actName/><actType/><actGroup/><openingBal/><openingBalDate/><status/><"
                                + "/AccountCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#retrieveUser(Long)}
     */
    @Test
    void testRetrieveUser1() throws Exception {
        when(accountCreationService.getAccountsById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/AccountSetup/getAccount/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountSetupRestController#salesBoyCreate(SalesManCreationEntity)}
     */
    @Test
    void testSalesBoyCreate() throws Exception {
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
        when(salesManCreationService.getSalesManName((String) any())).thenReturn(true);
        when(salesManCreationService.createOrUpdateSalesBoy((SalesManCreationEntity) any()))
                .thenReturn(salesManCreationEntity);

        AreaCreationEntity areaCreationEntity1 = new AreaCreationEntity();
        areaCreationEntity1.setAreaName("Area Name");
        areaCreationEntity1.setId(123L);
        areaCreationEntity1.setRoute("Route");
        areaCreationEntity1.setStatus("Status");

        SalesManCreationEntity salesManCreationEntity1 = new SalesManCreationEntity();
        salesManCreationEntity1.setAreaCreation(areaCreationEntity1);
        salesManCreationEntity1.setId(123L);
        salesManCreationEntity1.setIncentive(1);
        salesManCreationEntity1.setSalesManName("Sales Man Name");
        salesManCreationEntity1.setStatus("Status");
        salesManCreationEntity1.setTarget("Target");
        String content = (new ObjectMapper()).writeValueAsString(salesManCreationEntity1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/AccountSetup/SalesBoyCreate/SaleBoy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link AccountSetupRestController#salesBoyCreate(SalesManCreationEntity)}
     */
    @Test
    void testSalesBoyCreate2() throws Exception {
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
        when(salesManCreationService.getSalesManName((String) any())).thenReturn(false);
        when(salesManCreationService.createOrUpdateSalesBoy((SalesManCreationEntity) any()))
                .thenReturn(salesManCreationEntity);

        AreaCreationEntity areaCreationEntity1 = new AreaCreationEntity();
        areaCreationEntity1.setAreaName("Area Name");
        areaCreationEntity1.setId(123L);
        areaCreationEntity1.setRoute("Route");
        areaCreationEntity1.setStatus("Status");

        SalesManCreationEntity salesManCreationEntity1 = new SalesManCreationEntity();
        salesManCreationEntity1.setAreaCreation(areaCreationEntity1);
        salesManCreationEntity1.setId(123L);
        salesManCreationEntity1.setIncentive(1);
        salesManCreationEntity1.setSalesManName("Sales Man Name");
        salesManCreationEntity1.setStatus("Status");
        salesManCreationEntity1.setTarget("Target");
        String content = (new ObjectMapper()).writeValueAsString(salesManCreationEntity1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/AccountSetup/SalesBoyCreate/SaleBoy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#salesBoyCreate(SalesManCreationEntity)}
     */
    @Test
    void testSalesBoyCreate3() throws Exception {
        when(salesManCreationService.getSalesManName((String) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));
        when(salesManCreationService.createOrUpdateSalesBoy((SalesManCreationEntity) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));

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
        String content = (new ObjectMapper()).writeValueAsString(salesManCreationEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/AccountSetup/SalesBoyCreate/SaleBoy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link AccountSetupRestController#salesBoyCreateList(List)}
     */
    @Test
    void testSalesBoyCreateList() throws Exception {
        when(dTOTransfomers.salesManConvertToEntityList((List<SalesManCreationDTO>) any())).thenReturn(new ArrayList<>());
        doNothing().when(salesManCreationService).createOrUpdateSalesBoyList((List<SalesManCreationEntity>) any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/AccountSetup/SalesBoyCreate/bulk")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#salesBoyCreateList(List)}
     */
    @Test
    void testSalesBoyCreateList2() throws Exception {
        when(dTOTransfomers.salesManConvertToEntityList((List<SalesManCreationDTO>) any())).thenReturn(new ArrayList<>());
        doThrow(new UnableToProcessException("An error occurred")).when(salesManCreationService)
                .createOrUpdateSalesBoyList((List<SalesManCreationEntity>) any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/AccountSetup/SalesBoyCreate/bulk")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#stockCreate(StockCreationEntity)}
     */
    @Test
    void testStockCreate() throws Exception {
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

        StockCreationEntity stockCreationEntity = new StockCreationEntity();
        stockCreationEntity.setBarnchCreation(branchCreationEntity);
        stockCreationEntity.setId(123L);
        stockCreationEntity.setStatus("Status");
        stockCreationEntity.setStockName("Stock Name");
        when(stockCreationService.getStockName((String) any())).thenReturn(true);
        when(stockCreationService.createOrUpdateStock((StockCreationEntity) any())).thenReturn(stockCreationEntity);

        BranchCreationEntity branchCreationEntity1 = new BranchCreationEntity();
        branchCreationEntity1.setAddreess("42 Main St");
        branchCreationEntity1.setBranchName("janedoe/featurebranch");
        branchCreationEntity1.setContact_name("Contact name");
        branchCreationEntity1.setDlNumber("42");
        branchCreationEntity1.setGstNumber("42");
        branchCreationEntity1.setId(123L);
        branchCreationEntity1.setLocation("Location");
        branchCreationEntity1.setMobileNo("Mobile No");
        branchCreationEntity1.setPincode(1);
        branchCreationEntity1.setStatus("Status");

        StockCreationEntity stockCreationEntity1 = new StockCreationEntity();
        stockCreationEntity1.setBarnchCreation(branchCreationEntity1);
        stockCreationEntity1.setId(123L);
        stockCreationEntity1.setStatus("Status");
        stockCreationEntity1.setStockName("Stock Name");
        String content = (new ObjectMapper()).writeValueAsString(stockCreationEntity1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/AccountSetup/StockCreate/Stock")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link AccountSetupRestController#stockCreate(StockCreationEntity)}
     */
    @Test
    void testStockCreate2() throws Exception {
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

        StockCreationEntity stockCreationEntity = new StockCreationEntity();
        stockCreationEntity.setBarnchCreation(branchCreationEntity);
        stockCreationEntity.setId(123L);
        stockCreationEntity.setStatus("Status");
        stockCreationEntity.setStockName("Stock Name");
        when(stockCreationService.getStockName((String) any())).thenReturn(false);
        when(stockCreationService.createOrUpdateStock((StockCreationEntity) any())).thenReturn(stockCreationEntity);

        BranchCreationEntity branchCreationEntity1 = new BranchCreationEntity();
        branchCreationEntity1.setAddreess("42 Main St");
        branchCreationEntity1.setBranchName("janedoe/featurebranch");
        branchCreationEntity1.setContact_name("Contact name");
        branchCreationEntity1.setDlNumber("42");
        branchCreationEntity1.setGstNumber("42");
        branchCreationEntity1.setId(123L);
        branchCreationEntity1.setLocation("Location");
        branchCreationEntity1.setMobileNo("Mobile No");
        branchCreationEntity1.setPincode(1);
        branchCreationEntity1.setStatus("Status");

        StockCreationEntity stockCreationEntity1 = new StockCreationEntity();
        stockCreationEntity1.setBarnchCreation(branchCreationEntity1);
        stockCreationEntity1.setId(123L);
        stockCreationEntity1.setStatus("Status");
        stockCreationEntity1.setStockName("Stock Name");
        String content = (new ObjectMapper()).writeValueAsString(stockCreationEntity1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/AccountSetup/StockCreate/Stock")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#stockCreate(StockCreationEntity)}
     */
    @Test
    void testStockCreate3() throws Exception {
        when(stockCreationService.getStockName((String) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));
        when(stockCreationService.createOrUpdateStock((StockCreationEntity) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));

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

        StockCreationEntity stockCreationEntity = new StockCreationEntity();
        stockCreationEntity.setBarnchCreation(branchCreationEntity);
        stockCreationEntity.setId(123L);
        stockCreationEntity.setStatus("Status");
        stockCreationEntity.setStockName("Stock Name");
        String content = (new ObjectMapper()).writeValueAsString(stockCreationEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/AccountSetup/StockCreate/Stock")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link AccountSetupRestController#supplierCreate(SupplierCreationDTO)}
     */
    @Test
    void testSupplierCreate() throws Exception {
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
        when(dTOTransfomers.supplierConvertToDto((SupplierCreationEntity) any())).thenReturn(supplierCreationDTO);
        when(dTOTransfomers.supplierConvertToEntity((SupplierCreationDTO) any())).thenReturn(supplierCreationEntity);

        SupplierCreationEntity supplierCreationEntity1 = new SupplierCreationEntity();
        supplierCreationEntity1.setAddress1("42 Main St");
        supplierCreationEntity1.setAddress2("42 Main St");
        supplierCreationEntity1.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity1.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity1.setCredit_days(1);
        supplierCreationEntity1.setDiscount("3");
        supplierCreationEntity1.setGstNo("Gst No");
        supplierCreationEntity1.setGstType("Gst Type");
        supplierCreationEntity1.setId(123L);
        supplierCreationEntity1.setMobileNo("Mobile No");
        supplierCreationEntity1.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity1.setOpeningBal(1L);
        supplierCreationEntity1.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity1.setRateSlab("Rate Slab");
        supplierCreationEntity1.setStatus("Status");
        supplierCreationEntity1.setSupplierName("Supplier Name");
        supplierCreationEntity1.setUpdated_by(1);
        supplierCreationEntity1.setUpdated_date(LocalDate.ofEpochDay(1L));
        when(supplierCreationService.createOrUpdateSupplier((SupplierCreationEntity) any()))
                .thenReturn(supplierCreationEntity1);

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
        String content = (new ObjectMapper()).writeValueAsString(supplierCreationDTO1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/AccountSetup/SupplierCreate/Supplier")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#supplierCreate(SupplierCreationDTO)}
     */
    @Test
    void testSupplierCreate2() throws Exception {
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
        when(dTOTransfomers.supplierConvertToDto((SupplierCreationEntity) any())).thenReturn(supplierCreationDTO);
        when(dTOTransfomers.supplierConvertToEntity((SupplierCreationDTO) any())).thenReturn(supplierCreationEntity);
        when(supplierCreationService.createOrUpdateSupplier((SupplierCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

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
        String content = (new ObjectMapper()).writeValueAsString(supplierCreationDTO1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/AccountSetup/SupplierCreate/Supplier")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#supplierCreateList(List)}
     */
    @Test
    void testSupplierCreateList() throws Exception {
        when(dTOTransfomers.supplierConvertToEntityList((List<SupplierCreationDTO>) any())).thenReturn(new ArrayList<>());
        doNothing().when(supplierCreationService).createOrUpdateSupplierList((List<SupplierCreationEntity>) any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/AccountSetup/SupplierCreate/bulk")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#supplierCreateList(List)}
     */
    @Test
    void testSupplierCreateList2() throws Exception {
        when(dTOTransfomers.supplierConvertToEntityList((List<SupplierCreationDTO>) any())).thenReturn(new ArrayList<>());
        doThrow(new UnableToProcessException("An error occurred")).when(supplierCreationService)
                .createOrUpdateSupplierList((List<SupplierCreationEntity>) any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/AccountSetup/SupplierCreate/bulk")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#updatSalesMan(SalesManCreationEntity, long)}
     */
    @Test
    void testUpdatSalesMan() throws Exception {
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
        when(salesManCreationService.updateSalesBoy((SalesManCreationEntity) any())).thenReturn(salesManCreationEntity);

        AreaCreationEntity areaCreationEntity1 = new AreaCreationEntity();
        areaCreationEntity1.setAreaName("Area Name");
        areaCreationEntity1.setId(123L);
        areaCreationEntity1.setRoute("Route");
        areaCreationEntity1.setStatus("Status");

        SalesManCreationEntity salesManCreationEntity1 = new SalesManCreationEntity();
        salesManCreationEntity1.setAreaCreation(areaCreationEntity1);
        salesManCreationEntity1.setId(123L);
        salesManCreationEntity1.setIncentive(1);
        salesManCreationEntity1.setSalesManName("Sales Man Name");
        salesManCreationEntity1.setStatus("Status");
        salesManCreationEntity1.setTarget("Target");
        String content = (new ObjectMapper()).writeValueAsString(salesManCreationEntity1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/AccountSetup/updateSalesBoy/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<SalesManCreationEntity><id>123</id><salesManName>Sales Man Name</salesManName><incentive>1</incentive"
                                        + "><target>Target</target><status>Status</status><areaCreation><id>123</id><areaName>Area Name</areaName"
                                        + "><route>Route</route><status>Status</status></areaCreation></SalesManCreationEntity>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#updateAccount(AccountCreationDTO, long)}
     */
    @Test
    void testUpdateAccount() throws Exception {
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
        when(accountCreationService.updateAccounts((AccountCreationEntity) any())).thenReturn(accountCreationEntity);

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
        when(dTOTransfomers.accountCreationConvertToDto((AccountCreationEntity) any()))
                .thenReturn(new AccountCreationDTO());
        when(dTOTransfomers.accountCreationConvertToEntity((AccountCreationDTO) any()))
                .thenReturn(accountCreationEntity1);

        AccountCreationDTO accountCreationDTO = new AccountCreationDTO();
        accountCreationDTO.setActGroup("Act Group");
        accountCreationDTO.setActName("Act Name");
        accountCreationDTO.setActType("Act Type");
        accountCreationDTO.setId(123L);
        accountCreationDTO.setOpeningBal("Opening Bal");
        accountCreationDTO.setOpeningBalDate("2020-03-01");
        accountCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(accountCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/AccountSetup/updateAccount/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<AccountCreationDTO><id/><actName/><actType/><actGroup/><openingBal/><openingBalDate/><status/><"
                                + "/AccountCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#updateArea(AreaCreationDTO, long)}
     */
    @Test
    void testUpdateArea() throws Exception {
        AreaCreationEntity areaCreationEntity = new AreaCreationEntity();
        areaCreationEntity.setAreaName("Area Name");
        areaCreationEntity.setId(123L);
        areaCreationEntity.setRoute("Route");
        areaCreationEntity.setStatus("Status");
        when(areaCreationService.createOrUpdateArea((AreaCreationEntity) any())).thenReturn(areaCreationEntity);

        AreaCreationEntity areaCreationEntity1 = new AreaCreationEntity();
        areaCreationEntity1.setAreaName("Area Name");
        areaCreationEntity1.setId(123L);
        areaCreationEntity1.setRoute("Route");
        areaCreationEntity1.setStatus("Status");
        when(dTOTransfomers.areaCreationConvertToDto((AreaCreationEntity) any())).thenReturn(new AreaCreationDTO());
        when(dTOTransfomers.areaCreationConvertToEntity((AreaCreationDTO) any())).thenReturn(areaCreationEntity1);

        AreaCreationDTO areaCreationDTO = new AreaCreationDTO();
        areaCreationDTO.setAreaName("Area Name");
        areaCreationDTO.setId(123L);
        areaCreationDTO.setRoute("Route");
        areaCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(areaCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/AccountSetup/updateArea/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<HttpStatus>OK</HttpStatus>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#updateCompositionType(CompostionCreationEntity, long)}
     */
    @Test
    void testUpdateCompositionType() throws Exception {
        CompostionCreationEntity compostionCreationEntity = new CompostionCreationEntity();
        compostionCreationEntity.setCompName("Comp Name");
        compostionCreationEntity.setId(123L);
        compostionCreationEntity.setStatus("Status");
        when(compostionCreationService.updateCompostion((CompostionCreationEntity) any()))
                .thenReturn(compostionCreationEntity);

        CompostionCreationEntity compostionCreationEntity1 = new CompostionCreationEntity();
        compostionCreationEntity1.setCompName("Comp Name");
        compostionCreationEntity1.setId(123L);
        compostionCreationEntity1.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(compostionCreationEntity1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/AccountSetup/updateCompositionType/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<CompostionCreationEntity><id>123</id><compName>Comp Name</compName><information/><schedulerCreationEntity/><status>Status</status></CompostionCreationEntity>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#updateHsnType(HsnSacCreationDTO, long)}
     */
    @Test
    void testUpdateHsnType() throws Exception {
        HsnSacCreationEntity hsnSacCreationEntity = new HsnSacCreationEntity();
        hsnSacCreationEntity.setDescirption("Descirption");
        hsnSacCreationEntity.setHsnName("Hsn Name");
        hsnSacCreationEntity.setId(123L);
        hsnSacCreationEntity.setStatus("Status");
        when(dTOTransfomers.hsnCreationConvertToDto((HsnSacCreationEntity) any())).thenReturn(new HsnSacCreationDTO());
        when(dTOTransfomers.hsnConvertToEntity((HsnSacCreationDTO) any())).thenReturn(hsnSacCreationEntity);

        HsnSacCreationEntity hsnSacCreationEntity1 = new HsnSacCreationEntity();
        hsnSacCreationEntity1.setDescirption("Descirption");
        hsnSacCreationEntity1.setHsnName("Hsn Name");
        hsnSacCreationEntity1.setId(123L);
        hsnSacCreationEntity1.setStatus("Status");
        when(hsnSacCreationService.updateHsn((HsnSacCreationEntity) any())).thenReturn(hsnSacCreationEntity1);

        HsnSacCreationDTO hsnSacCreationDTO = new HsnSacCreationDTO();
        hsnSacCreationDTO.setDescirption("Descirption");
        hsnSacCreationDTO.setHsnName("Hsn Name");
        hsnSacCreationDTO.setId(123L);
        hsnSacCreationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(hsnSacCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/AccountSetup/updateHSNType/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<HsnSacCreationDTO><id/><hsnName/><descirption/><status/></HsnSacCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#updateItemType(ItemCreationDTO, long)}
     */
    @Test
    void testUpdateItemType() throws Exception {
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
        when(dTOTransfomers.itemCreationConvertToDto((ItemCreationEntity) any())).thenReturn(new ItemCreationDTO());
        when(dTOTransfomers.itemConvertToEntity((ItemCreationDTO) any())).thenReturn(itemCreationEntity);

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
        when(itemCreationService.updateItem((ItemCreationEntity) any())).thenReturn(itemCreationEntity1);

        CompostionCreationEntity compostionCreationEntity2 = new CompostionCreationEntity();
        compostionCreationEntity2.setCompName("Comp Name");
        compostionCreationEntity2.setId(123L);
        compostionCreationEntity2.setStatus("Status");

        DiscountSlabCreationEntity discountSlabCreationEntity2 = new DiscountSlabCreationEntity();
        discountSlabCreationEntity2.setDiscount("3");
        discountSlabCreationEntity2.setDiscountSlabName("3");
        discountSlabCreationEntity2.setFrom_amt(1);
        discountSlabCreationEntity2.setId(123L);
        discountSlabCreationEntity2.setStatus("Status");
        discountSlabCreationEntity2.setTo_amt(1);

        GroupCreationEntity groupCreationEntity2 = new GroupCreationEntity();
        groupCreationEntity2.setGroupName("Group Name");
        groupCreationEntity2.setId(123L);
        groupCreationEntity2.setStatus("Status");

        HsnSacCreationEntity hsnSacCreationEntity2 = new HsnSacCreationEntity();
        hsnSacCreationEntity2.setDescirption("Descirption");
        hsnSacCreationEntity2.setHsnName("Hsn Name");
        hsnSacCreationEntity2.setId(123L);
        hsnSacCreationEntity2.setStatus("Status");

        ManufacturerCreationEntity manufacturerCreationEntity2 = new ManufacturerCreationEntity();
        manufacturerCreationEntity2.setId(123L);
        manufacturerCreationEntity2.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity2.setStatus("Status");

        SchedulerCreationEntity schedulerCreationEntity2 = new SchedulerCreationEntity();
        schedulerCreationEntity2.setId(123L);
        schedulerCreationEntity2.setSchedulerName("Scheduler Name");
        schedulerCreationEntity2.setStatus("Status");
        schedulerCreationEntity2.setWaringMsg("Waring Msg");
        schedulerCreationEntity2.setWarning("Warning");

        StoreTypeCreationEntity storeTypeCreationEntity2 = new StoreTypeCreationEntity();
        storeTypeCreationEntity2.setId(123L);
        storeTypeCreationEntity2.setStatus("Status");
        storeTypeCreationEntity2.setStoreTypeName("Store Type Name");

        ItemCreationDTO itemCreationDTO = new ItemCreationDTO();
        itemCreationDTO.setCompositionEntity(compostionCreationEntity2);
        itemCreationDTO.setDiscSalbEntity(discountSlabCreationEntity2);
        itemCreationDTO.setGroupEntity(groupCreationEntity2);
        itemCreationDTO.setGst(1);
        itemCreationDTO.setHsnsac(hsnSacCreationEntity2);
        itemCreationDTO.setId(123L);
        itemCreationDTO.setItemName("Item Name");
        itemCreationDTO.setManufactureEntity(manufacturerCreationEntity2);
        itemCreationDTO.setMax_amt(10.0d);
        itemCreationDTO.setMax_qty(10.0d);
        itemCreationDTO.setMin_amt(10.0d);
        itemCreationDTO.setMin_qty(10.0d);
        itemCreationDTO.setPackName("Pack Name");
        itemCreationDTO.setQty_per_pack("Qty per pack");
        itemCreationDTO.setRateA("Rate A");
        itemCreationDTO.setRateB("Rate B");
        itemCreationDTO.setRateC("Rate C");
        itemCreationDTO.setRateD("Rate D");
        itemCreationDTO.setScheduleEntity(schedulerCreationEntity2);
        itemCreationDTO.setStatus("Status");
        itemCreationDTO.setStoreTypeEntity(storeTypeCreationEntity2);
        String content = (new ObjectMapper()).writeValueAsString(itemCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/AccountSetup/updateItemDetails/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<ItemCreationDTO><id/><itemName/><packName/><qty_per_pack/><min_amt>0.0</min_amt><max_amt>0.0</max_amt"
                                        + "><min_qty>0.0</min_qty><max_qty>0.0</max_qty><gst/><rateA/><rateB/><rateC/><rateD/><status/>"
                                        + "<manufactureEntity/><groupEntity/><storeTypeEntity/><scheduleEntity/><compositionEntity/><discSalbEntity"
                                        + "/><hsnsac/></ItemCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#updateItemType(ItemCreationDTO, long)}
     */
    @Test
    void testUpdateItemType2() throws Exception {
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
        when(dTOTransfomers.itemCreationConvertToDto((ItemCreationEntity) any())).thenReturn(new ItemCreationDTO());
        when(dTOTransfomers.itemConvertToEntity((ItemCreationDTO) any())).thenReturn(itemCreationEntity);
        when(itemCreationService.updateItem((ItemCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

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

        ItemCreationDTO itemCreationDTO = new ItemCreationDTO();
        itemCreationDTO.setCompositionEntity(compostionCreationEntity1);
        itemCreationDTO.setDiscSalbEntity(discountSlabCreationEntity1);
        itemCreationDTO.setGroupEntity(groupCreationEntity1);
        itemCreationDTO.setGst(1);
        itemCreationDTO.setHsnsac(hsnSacCreationEntity1);
        itemCreationDTO.setId(123L);
        itemCreationDTO.setItemName("Item Name");
        itemCreationDTO.setManufactureEntity(manufacturerCreationEntity1);
        itemCreationDTO.setMax_amt(10.0d);
        itemCreationDTO.setMax_qty(10.0d);
        itemCreationDTO.setMin_amt(10.0d);
        itemCreationDTO.setMin_qty(10.0d);
        itemCreationDTO.setPackName("Pack Name");
        itemCreationDTO.setQty_per_pack("Qty per pack");
        itemCreationDTO.setRateA("Rate A");
        itemCreationDTO.setRateB("Rate B");
        itemCreationDTO.setRateC("Rate C");
        itemCreationDTO.setRateD("Rate D");
        itemCreationDTO.setScheduleEntity(schedulerCreationEntity1);
        itemCreationDTO.setStatus("Status");
        itemCreationDTO.setStoreTypeEntity(storeTypeCreationEntity1);
        String content = (new ObjectMapper()).writeValueAsString(itemCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/AccountSetup/updateItemDetails/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }

    /**
     * Method under test: {@link AccountSetupRestController#updateSchedulerType(SchedulerCreationDTO, long)}
     */
    @Test
    void testUpdateSchedulerType() throws Exception {
        SchedulerCreationEntity schedulerCreationEntity = new SchedulerCreationEntity();
        schedulerCreationEntity.setId(123L);
        schedulerCreationEntity.setSchedulerName("Scheduler Name");
        schedulerCreationEntity.setStatus("Status");
        schedulerCreationEntity.setWaringMsg("Waring Msg");
        schedulerCreationEntity.setWarning("Warning");
        when(dTOTransfomers.schedulerConvertToDto((SchedulerCreationEntity) any()))
                .thenReturn(new SchedulerCreationDTO());
        when(dTOTransfomers.schedulerConvertToEntity((SchedulerCreationDTO) any())).thenReturn(schedulerCreationEntity);

        SchedulerCreationEntity schedulerCreationEntity1 = new SchedulerCreationEntity();
        schedulerCreationEntity1.setId(123L);
        schedulerCreationEntity1.setSchedulerName("Scheduler Name");
        schedulerCreationEntity1.setStatus("Status");
        schedulerCreationEntity1.setWaringMsg("Waring Msg");
        schedulerCreationEntity1.setWarning("Warning");
        when(schedulerCreationService.updateScheduler((SchedulerCreationEntity) any()))
                .thenReturn(schedulerCreationEntity1);

        SchedulerCreationDTO schedulerCreationDTO = new SchedulerCreationDTO();
        schedulerCreationDTO.setId(123L);
        schedulerCreationDTO.setSchedulerName("Scheduler Name");
        schedulerCreationDTO.setStatus("Status");
        schedulerCreationDTO.setWaringMsg("Waring Msg");
        schedulerCreationDTO.setWarning("Warning");
        String content = (new ObjectMapper()).writeValueAsString(schedulerCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/AccountSetup/updateSchedulerType/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<SchedulerCreationDTO><id/><schedulerName/><waringMsg/><warning/><status/></SchedulerCreationDTO>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#updateStock(StockCreationEntity, long)}
     */
    @Test
    void testUpdateStock() throws Exception {
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

        StockCreationEntity stockCreationEntity = new StockCreationEntity();
        stockCreationEntity.setBarnchCreation(branchCreationEntity);
        stockCreationEntity.setId(123L);
        stockCreationEntity.setStatus("Status");
        stockCreationEntity.setStockName("Stock Name");
        when(stockCreationService.createOrUpdateStock((StockCreationEntity) any())).thenReturn(stockCreationEntity);

        BranchCreationEntity branchCreationEntity1 = new BranchCreationEntity();
        branchCreationEntity1.setAddreess("42 Main St");
        branchCreationEntity1.setBranchName("janedoe/featurebranch");
        branchCreationEntity1.setContact_name("Contact name");
        branchCreationEntity1.setDlNumber("42");
        branchCreationEntity1.setGstNumber("42");
        branchCreationEntity1.setId(123L);
        branchCreationEntity1.setLocation("Location");
        branchCreationEntity1.setMobileNo("Mobile No");
        branchCreationEntity1.setPincode(1);
        branchCreationEntity1.setStatus("Status");

        StockCreationEntity stockCreationEntity1 = new StockCreationEntity();
        stockCreationEntity1.setBarnchCreation(branchCreationEntity1);
        stockCreationEntity1.setId(123L);
        stockCreationEntity1.setStatus("Status");
        stockCreationEntity1.setStockName("Stock Name");
        String content = (new ObjectMapper()).writeValueAsString(stockCreationEntity1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/AccountSetup/updateStock/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<StockCreationEntity><id>123</id><stockName>Stock Name</stockName><status>Status</status><barnchCreation"
                                        + "><id>123</id><branchName>janedoe/featurebranch</branchName><mobileNo>Mobile No</mobileNo><addreess>42"
                                        + " Main St</addreess><location>Location</location><contact_name>Contact name</contact_name><dlNumber>42"
                                        + "</dlNumber><gstNumber>42</gstNumber><pincode>1</pincode><status>Status</status></barnchCreation><"
                                        + "/StockCreationEntity>"));
    }

    /**
     * Method under test: {@link AccountSetupRestController#updateStock(StockCreationEntity, long)}
     */
    @Test
    void testUpdateStock2() throws Exception {
        when(stockCreationService.createOrUpdateStock((StockCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

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

        StockCreationEntity stockCreationEntity = new StockCreationEntity();
        stockCreationEntity.setBarnchCreation(branchCreationEntity);
        stockCreationEntity.setId(123L);
        stockCreationEntity.setStatus("Status");
        stockCreationEntity.setStockName("Stock Name");
        String content = (new ObjectMapper()).writeValueAsString(stockCreationEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/AccountSetup/updateStock/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountSetupRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(418));
    }
}

