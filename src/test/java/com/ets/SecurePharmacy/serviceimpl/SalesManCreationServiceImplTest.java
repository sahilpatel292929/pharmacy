package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.SalesManCreationRepository;
import com.ets.SecurePharmacy.tenant.model.AreaCreationEntity;
import com.ets.SecurePharmacy.tenant.model.SalesManCreationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

@ContextConfiguration(classes = {SalesManCreationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class SalesManCreationServiceImplTest {

    @MockBean
    private SalesManCreationRepository salesManCreationRepository;

    @Autowired
    private SalesManCreationServiceImpl salesManCreationServiceImpl;

    /**
     * Method under test: {@link SalesManCreationServiceImpl#getAllSalesManDetails()}
     */
    @Test
    void testGetAllSalesManDetails() {
        // Arrange
        when(salesManCreationRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(salesManCreationServiceImpl.getAllSalesManDetails().isEmpty());
        verify(salesManCreationRepository).findAll();
    }

    /**
     * Method under test: {@link SalesManCreationServiceImpl#getAllSalesManDetails()}
     */
    @Test
    void testGetAllSalesManDetails2() {
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

        ArrayList<SalesManCreationEntity> salesManCreationEntityList = new ArrayList<>();
        salesManCreationEntityList.add(salesManCreationEntity);
        when(salesManCreationRepository.findAll()).thenReturn(salesManCreationEntityList);

        // Act
        List<SalesManCreationEntity> actualAllSalesManDetails = salesManCreationServiceImpl.getAllSalesManDetails();

        // Assert
        assertSame(salesManCreationEntityList, actualAllSalesManDetails);
        assertEquals(1, actualAllSalesManDetails.size());
        verify(salesManCreationRepository).findAll();
    }

    /**
     * Method under test: {@link SalesManCreationServiceImpl#getAllSalesManDetails()}
     */
    @Test
    void testGetAllSalesManDetails3() {
        // Arrange
        when(salesManCreationRepository.findAll()).thenThrow(
                new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> salesManCreationServiceImpl.getAllSalesManDetails());
        verify(salesManCreationRepository).findAll();
    }

    /**
     * Method under test: {@link SalesManCreationServiceImpl#getSalesManById(Long)}
     */
    @Test
    void testGetSalesManById() throws RecordNotFoundException {
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
        Optional<SalesManCreationEntity> ofResult = Optional.of(salesManCreationEntity);
        when(salesManCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(salesManCreationEntity, salesManCreationServiceImpl.getSalesManById(123L));
        verify(salesManCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SalesManCreationServiceImpl#getSalesManById(Long)}
     */
    @Test
    void testGetSalesManById2() throws RecordNotFoundException {
        // Arrange
        when(salesManCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class,
                () -> salesManCreationServiceImpl.getSalesManById(123L));
        verify(salesManCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SalesManCreationServiceImpl#getSalesManById(Long)}
     */
    @Test
    void testGetSalesManById3() throws RecordNotFoundException {
        // Arrange
        when(salesManCreationRepository.findById((Long) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> salesManCreationServiceImpl.getSalesManById(123L));
        verify(salesManCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SalesManCreationServiceImpl#getSalesManName(String)}
     */
    @Test
    void testGetSalesManName() {
        // Arrange
        when(salesManCreationRepository.findBySalesManName((String) any())).thenReturn(
                new ArrayList<>());

        // Act and Assert
        assertFalse(salesManCreationServiceImpl.getSalesManName("Sname"));
        verify(salesManCreationRepository).findBySalesManName((String) any());
    }

    /**
     * Method under test: {@link SalesManCreationServiceImpl#getSalesManName(String)}
     */
    @Test
    void testGetSalesManName2() {
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

        ArrayList<SalesManCreationEntity> salesManCreationEntityList = new ArrayList<>();
        salesManCreationEntityList.add(salesManCreationEntity);
        when(salesManCreationRepository.findBySalesManName((String) any())).thenReturn(
                salesManCreationEntityList);

        // Act and Assert
        assertTrue(salesManCreationServiceImpl.getSalesManName("Sname"));
        verify(salesManCreationRepository).findBySalesManName((String) any());
    }

    /**
     * Method under test: {@link SalesManCreationServiceImpl#getSalesManName(String)}
     */
    @Test
    void testGetSalesManName3() {
        // Arrange
        when(salesManCreationRepository.findBySalesManName((String) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> salesManCreationServiceImpl.getSalesManName("Sname"));
        verify(salesManCreationRepository).findBySalesManName((String) any());
    }

    /**
     * Method under test:
     * {@link SalesManCreationServiceImpl#createOrUpdateSalesBoy(SalesManCreationEntity)}
     */
    @Test
    void testCreateOrUpdateSalesBoy() {
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
        when(salesManCreationRepository.save((SalesManCreationEntity) any())).thenReturn(
                salesManCreationEntity);
        when(salesManCreationRepository.findBySalesManName((String) any())).thenReturn(
                new ArrayList<>());

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

        // Act and Assert
        assertSame(salesManCreationEntity,
                salesManCreationServiceImpl.createOrUpdateSalesBoy(salesManCreationEntity1));
        verify(salesManCreationRepository).save((SalesManCreationEntity) any());
        verify(salesManCreationRepository).findBySalesManName((String) any());
    }

    /**
     * Method under test:
     * {@link SalesManCreationServiceImpl#createOrUpdateSalesBoy(SalesManCreationEntity)}
     */
    @Test
    void testCreateOrUpdateSalesBoy2() {
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

        ArrayList<SalesManCreationEntity> salesManCreationEntityList = new ArrayList<>();
        salesManCreationEntityList.add(salesManCreationEntity1);
        when(salesManCreationRepository.save((SalesManCreationEntity) any())).thenReturn(
                salesManCreationEntity);
        when(salesManCreationRepository.findBySalesManName((String) any())).thenReturn(
                salesManCreationEntityList);

        AreaCreationEntity areaCreationEntity2 = new AreaCreationEntity();
        areaCreationEntity2.setAreaName("Area Name");
        areaCreationEntity2.setId(123L);
        areaCreationEntity2.setRoute("Route");
        areaCreationEntity2.setStatus("Status");

        SalesManCreationEntity salesManCreationEntity2 = new SalesManCreationEntity();
        salesManCreationEntity2.setAreaCreation(areaCreationEntity2);
        salesManCreationEntity2.setId(123L);
        salesManCreationEntity2.setIncentive(1);
        salesManCreationEntity2.setSalesManName("Sales Man Name");
        salesManCreationEntity2.setStatus("Status");
        salesManCreationEntity2.setTarget("Target");

        // Act and Assert
        assertThrows(RecordAlreadyPresentException.class,
                () -> salesManCreationServiceImpl.createOrUpdateSalesBoy(salesManCreationEntity2));
        verify(salesManCreationRepository).findBySalesManName((String) any());
    }

    /**
     * Method under test:
     * {@link SalesManCreationServiceImpl#createOrUpdateSalesBoy(SalesManCreationEntity)}
     */
    @Test
    void testCreateOrUpdateSalesBoy3() {
        // Arrange
        when(salesManCreationRepository.save((SalesManCreationEntity) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));
        when(salesManCreationRepository.findBySalesManName((String) any()))
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

        // Act and Assert
        assertThrows(RecordAlreadyPresentException.class,
                () -> salesManCreationServiceImpl.createOrUpdateSalesBoy(salesManCreationEntity));
        verify(salesManCreationRepository).findBySalesManName((String) any());
    }

    /**
     * Method under test: {@link SalesManCreationServiceImpl#deleteSalesManById(Long)}
     */
    @Test
    void testDeleteSalesManById() throws RecordNotFoundException {
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
        Optional<SalesManCreationEntity> ofResult = Optional.of(salesManCreationEntity);
        doNothing().when(salesManCreationRepository).deleteById((Long) any());
        when(salesManCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act
        salesManCreationServiceImpl.deleteSalesManById(123L);

        // Assert that nothing has changed
        verify(salesManCreationRepository).findById((Long) any());
        verify(salesManCreationRepository).deleteById((Long) any());
        assertTrue(salesManCreationServiceImpl.getAllSalesManDetails().isEmpty());
    }

    /**
     * Method under test: {@link SalesManCreationServiceImpl#deleteSalesManById(Long)}
     */
    @Test
    void testDeleteSalesManById2() throws RecordNotFoundException {
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
        Optional<SalesManCreationEntity> ofResult = Optional.of(salesManCreationEntity);
        doThrow(new UnableToProcessException("An error occurred")).when(salesManCreationRepository)
                .deleteById((Long) any());
        when(salesManCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> salesManCreationServiceImpl.deleteSalesManById(123L));
        verify(salesManCreationRepository).findById((Long) any());
        verify(salesManCreationRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link SalesManCreationServiceImpl#deleteSalesManById(Long)}
     */
    @Test
    void testDeleteSalesManById3() throws RecordNotFoundException {
        // Arrange
        doNothing().when(salesManCreationRepository).deleteById((Long) any());
        when(salesManCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class,
                () -> salesManCreationServiceImpl.deleteSalesManById(123L));
        verify(salesManCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SalesManCreationServiceImpl#createOrUpdateSalesBoyList(List)}
     */
    @Test
    void testCreateOrUpdateSalesBoyList() {
        // Arrange
        when(salesManCreationRepository.saveAll((Iterable<SalesManCreationEntity>) any())).thenReturn(
                new ArrayList<>());

        // Act
        salesManCreationServiceImpl.createOrUpdateSalesBoyList(new ArrayList<>());

        // Assert that nothing has changed
        verify(salesManCreationRepository).saveAll((Iterable<SalesManCreationEntity>) any());
        assertTrue(salesManCreationServiceImpl.getAllSalesManDetails().isEmpty());
    }

    /**
     * Method under test: {@link SalesManCreationServiceImpl#createOrUpdateSalesBoyList(List)}
     */
    @Test
    void testCreateOrUpdateSalesBoyList2() {
        // Arrange
        when(salesManCreationRepository.saveAll((Iterable<SalesManCreationEntity>) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> salesManCreationServiceImpl.createOrUpdateSalesBoyList(new ArrayList<>()));
        verify(salesManCreationRepository).saveAll((Iterable<SalesManCreationEntity>) any());
    }

    /**
     * Method under test: {@link SalesManCreationServiceImpl#getAllSalesManWithPagination(int, int)}
     */
    @Test
    void testGetAllSalesManWithPagination() {
        // Arrange
        PageImpl<SalesManCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(salesManCreationRepository.findAll((Pageable) any()))
                .thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<SalesManCreationEntity> actualSearch = salesManCreationServiceImpl.getAllSalesManWithPagination(1, 3);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(salesManCreationRepository).findAll((Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link SalesManCreationServiceImpl#getAllSalesManWithPagination(int, int)}
     */
    @Test
    void testGetAllSalesManWithPagination1() {
        // Arrange
        PageImpl<SalesManCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(salesManCreationRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("Unable to process at this moment, Try Again! After some time"));
        QPageRequest ofResult = QPageRequest.of(1, 3);


        // Assert
        assertThrows(UnableToProcessException.class, () -> salesManCreationServiceImpl.getAllSalesManWithPagination(1, 3));
        verify(salesManCreationRepository).findAll((Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }


    /**
     * Method under test: {@link SalesManCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<SalesManCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(salesManCreationRepository.findAllBySalesManNameContains((String) any(), (Pageable) any()))
                .thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<SalesManCreationEntity> actualSearch = salesManCreationServiceImpl.getSearch("Query",
                ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(salesManCreationRepository).findAllBySalesManNameContains((String) any(),
                (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link SalesManCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(salesManCreationRepository.findAllBySalesManNameContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> salesManCreationServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(salesManCreationRepository).findAllBySalesManNameContains((String) any(),
                (Pageable) any());
    }

    /**
     * Method under test: {@link SalesManCreationServiceImpl#updateSalesBoy(SalesManCreationEntity)}
     */
    @Test
    void testUpdateSalesBoy() {
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
        when(salesManCreationRepository.save((SalesManCreationEntity) any())).thenReturn(
                salesManCreationEntity);

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

        // Act and Assert
        assertSame(salesManCreationEntity,
                salesManCreationServiceImpl.updateSalesBoy(salesManCreationEntity1));
        verify(salesManCreationRepository).save((SalesManCreationEntity) any());
    }

    /**
     * Method under test: {@link SalesManCreationServiceImpl#updateSalesBoy(SalesManCreationEntity)}
     */
    @Test
    void testUpdateSalesBoy2() {
        // Arrange
        when(salesManCreationRepository.save((SalesManCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

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
        assertThrows(UnableToProcessException.class,
                () -> salesManCreationServiceImpl.updateSalesBoy(salesManCreationEntity));
        verify(salesManCreationRepository).save((SalesManCreationEntity) any());
    }
}

