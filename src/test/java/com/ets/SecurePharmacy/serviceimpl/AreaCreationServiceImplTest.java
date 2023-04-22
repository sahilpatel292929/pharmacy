package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.AreaCreationRepository;
import com.ets.SecurePharmacy.tenant.model.AreaCreationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
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

@ContextConfiguration(classes = {AreaCreationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AreaCreationServiceImplTest {
    @MockBean
    private AreaCreationRepository areaCreationRepository;

    @Autowired
    private AreaCreationServiceImpl areaCreationServiceImpl;

    /**
     * Method under test: {@link AreaCreationServiceImpl#getAllAreaList()}
     */
    @Test
    void testGetAllAreaList() {
        // Arrange
        when(areaCreationRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(areaCreationServiceImpl.getAllAreaList().isEmpty());
        verify(areaCreationRepository).findAll();
    }

    /**
     * Method under test: {@link AreaCreationServiceImpl#getAllAreaList()}
     */
    @Test
    void testGetAllAreaList2() {
        // Arrange
        AreaCreationEntity areaCreationEntity = new AreaCreationEntity();
        areaCreationEntity.setAreaName("Area Name");
        areaCreationEntity.setId(123L);
        areaCreationEntity.setRoute("Route");
        areaCreationEntity.setStatus("Status");

        ArrayList<AreaCreationEntity> areaCreationEntityList = new ArrayList<>();
        areaCreationEntityList.add(areaCreationEntity);
        when(areaCreationRepository.findAll()).thenReturn(areaCreationEntityList);

        // Act
        List<AreaCreationEntity> actualAllAreaList = areaCreationServiceImpl.getAllAreaList();

        // Assert
        assertSame(areaCreationEntityList, actualAllAreaList);
        assertEquals(1, actualAllAreaList.size());
        verify(areaCreationRepository).findAll();
    }

    /**
     * Method under test: {@link AreaCreationServiceImpl#getAllAreaList()}
     */
    @Test
    void testGetAllAreaList3() {
        // Arrange
        when(areaCreationRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> areaCreationServiceImpl.getAllAreaList());
        verify(areaCreationRepository).findAll();
    }

    /**
     * Method under test: {@link AreaCreationServiceImpl#getAreaById(Long)}
     */
    @Test
    void testGetAreaById() throws RecordNotFoundException {
        // Arrange
        AreaCreationEntity areaCreationEntity = new AreaCreationEntity();
        areaCreationEntity.setAreaName("Area Name");
        areaCreationEntity.setId(123L);
        areaCreationEntity.setRoute("Route");
        areaCreationEntity.setStatus("Status");
        Optional<AreaCreationEntity> ofResult = Optional.of(areaCreationEntity);
        when(areaCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(areaCreationEntity, areaCreationServiceImpl.getAreaById(123L));
        verify(areaCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AreaCreationServiceImpl#getAreaById(Long)}
     */
    @Test
    void testGetAreaById2() throws RecordNotFoundException {
        // Arrange
        when(areaCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> areaCreationServiceImpl.getAreaById(123L));
        verify(areaCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AreaCreationServiceImpl#getAreaById(Long)}
     */
    @Test
    void testGetAreaById3() throws RecordNotFoundException {
        // Arrange
        when(areaCreationRepository.findById((Long) any())).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> areaCreationServiceImpl.getAreaById(123L));
        verify(areaCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AreaCreationServiceImpl#getAreaByName(String)}
     */
    @Test
    void testGetAreaByName() {
        // Arrange
        when(areaCreationRepository.findByAreaName((String) any())).thenReturn(new ArrayList<>());

        // Act and Assert
        assertFalse(areaCreationServiceImpl.getAreaByName("Area"));
        verify(areaCreationRepository).findByAreaName((String) any());
    }

    /**
     * Method under test: {@link AreaCreationServiceImpl#getAreaByName(String)}
     */
    @Test
    void testGetAreaByName2() {
        // Arrange
        AreaCreationEntity areaCreationEntity = new AreaCreationEntity();
        areaCreationEntity.setAreaName("Area Name");
        areaCreationEntity.setId(123L);
        areaCreationEntity.setRoute("Route");
        areaCreationEntity.setStatus("Status");

        ArrayList<AreaCreationEntity> areaCreationEntityList = new ArrayList<>();
        areaCreationEntityList.add(areaCreationEntity);
        when(areaCreationRepository.findByAreaName((String) any())).thenReturn(areaCreationEntityList);

        // Act and Assert
        assertTrue(areaCreationServiceImpl.getAreaByName("Area"));
        verify(areaCreationRepository).findByAreaName((String) any());
    }

    /**
     * Method under test: {@link AreaCreationServiceImpl#getAreaByName(String)}
     */
    @Test
    void testGetAreaByName3() {
        // Arrange
        when(areaCreationRepository.findByAreaName((String) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> areaCreationServiceImpl.getAreaByName("Area"));
        verify(areaCreationRepository).findByAreaName((String) any());
    }

    /**
     * Method under test: {@link AreaCreationServiceImpl#createOrUpdateArea(AreaCreationEntity)}
     */
    @Test
    void testCreateOrUpdateArea() {
        // Arrange
        AreaCreationEntity areaCreationEntity = new AreaCreationEntity();
        areaCreationEntity.setAreaName("Area Name");
        areaCreationEntity.setId(123L);
        areaCreationEntity.setRoute("Route");
        areaCreationEntity.setStatus("Status");
        when(areaCreationRepository.save((AreaCreationEntity) any())).thenReturn(areaCreationEntity);
        when(areaCreationRepository.findByAreaName((String) any())).thenReturn(new ArrayList<>());

        AreaCreationEntity areaCreationEntity1 = new AreaCreationEntity();
        areaCreationEntity1.setAreaName("Area Name");
        areaCreationEntity1.setId(123L);
        areaCreationEntity1.setRoute("Route");
        areaCreationEntity1.setStatus("Status");

        // Act and Assert
        assertSame(areaCreationEntity, areaCreationServiceImpl.createOrUpdateArea(areaCreationEntity1));
        verify(areaCreationRepository).save((AreaCreationEntity) any());
        verify(areaCreationRepository).findByAreaName((String) any());
    }

    /**
     * Method under test: {@link AreaCreationServiceImpl#createOrUpdateArea(AreaCreationEntity)}
     */
    @Test
    void testCreateOrUpdateArea2() {
        // Arrange
        AreaCreationEntity areaCreationEntity = new AreaCreationEntity();
        areaCreationEntity.setAreaName("Area Name");
        areaCreationEntity.setId(123L);
        areaCreationEntity.setRoute("Route");
        areaCreationEntity.setStatus("Status");

        AreaCreationEntity areaCreationEntity1 = new AreaCreationEntity();
        areaCreationEntity1.setAreaName("Area Name");
        areaCreationEntity1.setId(123L);
        areaCreationEntity1.setRoute("Route");
        areaCreationEntity1.setStatus("Status");

        ArrayList<AreaCreationEntity> areaCreationEntityList = new ArrayList<>();
        areaCreationEntityList.add(areaCreationEntity1);
        when(areaCreationRepository.save((AreaCreationEntity) any())).thenReturn(areaCreationEntity);
        when(areaCreationRepository.findByAreaName((String) any())).thenReturn(areaCreationEntityList);

        AreaCreationEntity areaCreationEntity2 = new AreaCreationEntity();
        areaCreationEntity2.setAreaName("Area Name");
        areaCreationEntity2.setId(123L);
        areaCreationEntity2.setRoute("Route");
        areaCreationEntity2.setStatus("Status");

        // Act and Assert
        assertThrows(RecordAlreadyPresentException.class,
                () -> areaCreationServiceImpl.createOrUpdateArea(areaCreationEntity2));
        verify(areaCreationRepository).findByAreaName((String) any());
    }

    /**
     * Method under test: {@link AreaCreationServiceImpl#createOrUpdateArea(AreaCreationEntity)}
     */
    @Test
    void testCreateOrUpdateArea3() {
        // Arrange
        when(areaCreationRepository.save((AreaCreationEntity) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));
        when(areaCreationRepository.findByAreaName((String) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));

        AreaCreationEntity areaCreationEntity = new AreaCreationEntity();
        areaCreationEntity.setAreaName("Area Name");
        areaCreationEntity.setId(123L);
        areaCreationEntity.setRoute("Route");
        areaCreationEntity.setStatus("Status");

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> areaCreationServiceImpl.createOrUpdateArea(areaCreationEntity));
        verify(areaCreationRepository).findByAreaName((String) any());
    }

    /**
     * Method under test: {@link AreaCreationServiceImpl#deleteAreaById(Long)}
     */
    @Test
    void testDeleteAreaById() throws RecordNotFoundException {
        // Arrange
        AreaCreationEntity areaCreationEntity = new AreaCreationEntity();
        areaCreationEntity.setAreaName("Area Name");
        areaCreationEntity.setId(123L);
        areaCreationEntity.setRoute("Route");
        areaCreationEntity.setStatus("Status");
        Optional<AreaCreationEntity> ofResult = Optional.of(areaCreationEntity);
        doNothing().when(areaCreationRepository).deleteById((Long) any());
        when(areaCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act
        areaCreationServiceImpl.deleteAreaById(123L);

        // Assert that nothing has changed
        verify(areaCreationRepository).findById((Long) any());
        verify(areaCreationRepository).deleteById((Long) any());
        assertTrue(areaCreationServiceImpl.getAllAreaList().isEmpty());
    }

    /**
     * Method under test: {@link AreaCreationServiceImpl#deleteAreaById(Long)}
     */
    @Test
    void testDeleteAreaById2() throws RecordNotFoundException {
        // Arrange
        AreaCreationEntity areaCreationEntity = new AreaCreationEntity();
        areaCreationEntity.setAreaName("Area Name");
        areaCreationEntity.setId(123L);
        areaCreationEntity.setRoute("Route");
        areaCreationEntity.setStatus("Status");
        Optional<AreaCreationEntity> ofResult = Optional.of(areaCreationEntity);
        when(areaCreationRepository.findById((Long) any())).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> areaCreationServiceImpl.deleteAreaById(123L));
        verify(areaCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AreaCreationServiceImpl#deleteAreaById(Long)}
     */
    @Test
    void testDeleteAreaById3() throws RecordNotFoundException {
        // Arrange
        doNothing().when(areaCreationRepository).deleteById((Long) any());
        when(areaCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> areaCreationServiceImpl.deleteAreaById(123L));
        verify(areaCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AreaCreationServiceImpl#createOrUpdateAreaList(List)}
     */
    @Test
    void testCreateOrUpdateAreaList() {
        // Arrange
        when(areaCreationRepository.saveAll((Iterable<AreaCreationEntity>) any())).thenReturn(new ArrayList<>());

        // Act
        areaCreationServiceImpl.createOrUpdateAreaList(new ArrayList<>());

        // Assert that nothing has changed
        verify(areaCreationRepository).saveAll((Iterable<AreaCreationEntity>) any());
        assertTrue(areaCreationServiceImpl.getAllAreaList().isEmpty());
    }

    /**
     * Method under test: {@link AreaCreationServiceImpl#createOrUpdateAreaList(List)}
     */
    @Test
    void testCreateOrUpdateAreaList2() {
        // Arrange
        when(areaCreationRepository.saveAll((Iterable<AreaCreationEntity>) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> areaCreationServiceImpl.createOrUpdateAreaList(new ArrayList<>()));
        verify(areaCreationRepository).saveAll((Iterable<AreaCreationEntity>) any());
    }
}

