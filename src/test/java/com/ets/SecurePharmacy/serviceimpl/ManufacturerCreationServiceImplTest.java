package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.ManufacturerCreationRepository;
import com.ets.SecurePharmacy.tenant.model.ManufacturerCreationEntity;
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

@ContextConfiguration(classes = {ManufacturerCreationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ManufacturerCreationServiceImplTest {
    @MockBean
    private ManufacturerCreationRepository manufacturerCreationRepository;

    @Autowired
    private ManufacturerCreationServiceImpl manufacturerCreationServiceImpl;

    /**
     * Method under test: {@link ManufacturerCreationServiceImpl#getAllManufactureList()}
     */
    @Test
    void testGetAllManufactureList() {
        // Arrange
        when(manufacturerCreationRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(manufacturerCreationServiceImpl.getAllManufactureList().isEmpty());
        verify(manufacturerCreationRepository).findAll();
    }

    /**
     * Method under test: {@link ManufacturerCreationServiceImpl#getAllManufactureList()}
     */
    @Test
    void testGetAllManufactureList2() {
        // Arrange
        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity.setStatus("Status");

        ArrayList<ManufacturerCreationEntity> manufacturerCreationEntityList = new ArrayList<>();
        manufacturerCreationEntityList.add(manufacturerCreationEntity);
        when(manufacturerCreationRepository.findAll()).thenReturn(manufacturerCreationEntityList);

        // Act
        List<ManufacturerCreationEntity> actualAllManufactureList = manufacturerCreationServiceImpl
                .getAllManufactureList();

        // Assert
        assertSame(manufacturerCreationEntityList, actualAllManufactureList);
        assertEquals(1, actualAllManufactureList.size());
        verify(manufacturerCreationRepository).findAll();
    }

    /**
     * Method under test: {@link ManufacturerCreationServiceImpl#getAllManufactureList()}
     */
    @Test
    void testGetAllManufactureList3() {
        // Arrange
        when(manufacturerCreationRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> manufacturerCreationServiceImpl.getAllManufactureList());
        verify(manufacturerCreationRepository).findAll();
    }

    /**
     * Method under test: {@link ManufacturerCreationServiceImpl#getManufactureById(Long)}
     */
    @Test
    void testGetManufactureById() throws RecordNotFoundException {
        // Arrange
        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity.setStatus("Status");
        Optional<ManufacturerCreationEntity> ofResult = Optional.of(manufacturerCreationEntity);
        when(manufacturerCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(manufacturerCreationEntity, manufacturerCreationServiceImpl.getManufactureById(123L));
        verify(manufacturerCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ManufacturerCreationServiceImpl#getManufactureById(Long)}
     */
    @Test
    void testGetManufactureById2() throws RecordNotFoundException {
        // Arrange
        when(manufacturerCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> manufacturerCreationServiceImpl.getManufactureById(123L));
        verify(manufacturerCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ManufacturerCreationServiceImpl#getManufactureById(Long)}
     */
    @Test
    void testGetManufactureById3() throws RecordNotFoundException {
        // Arrange
        when(manufacturerCreationRepository.findById((Long) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> manufacturerCreationServiceImpl.getManufactureById(123L));
        verify(manufacturerCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ManufacturerCreationServiceImpl#getManufactureByName(String)}
     */
    @Test
    void testGetManufactureByName() {
        // Arrange
        when(manufacturerCreationRepository.findByManufacturerName((String) any())).thenReturn(new ArrayList<>());

        // Act and Assert
        assertFalse(manufacturerCreationServiceImpl.getManufactureByName("M Name"));
        verify(manufacturerCreationRepository).findByManufacturerName((String) any());
    }

    /**
     * Method under test: {@link ManufacturerCreationServiceImpl#getManufactureByName(String)}
     */
    @Test
    void testGetManufactureByName2() {
        // Arrange
        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity.setStatus("Status");

        ArrayList<ManufacturerCreationEntity> manufacturerCreationEntityList = new ArrayList<>();
        manufacturerCreationEntityList.add(manufacturerCreationEntity);
        when(manufacturerCreationRepository.findByManufacturerName((String) any()))
                .thenReturn(manufacturerCreationEntityList);

        // Act and Assert
        assertTrue(manufacturerCreationServiceImpl.getManufactureByName("M Name"));
        verify(manufacturerCreationRepository).findByManufacturerName((String) any());
    }

    /**
     * Method under test: {@link ManufacturerCreationServiceImpl#getManufactureByName(String)}
     */
    @Test
    void testGetManufactureByName3() {
        // Arrange
        when(manufacturerCreationRepository.findByManufacturerName((String) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> manufacturerCreationServiceImpl.getManufactureByName("M Name"));
        verify(manufacturerCreationRepository).findByManufacturerName((String) any());
    }

    /**
     * Method under test: {@link ManufacturerCreationServiceImpl#createOrUpdateManufacture(ManufacturerCreationEntity)}
     */
    @Test
    void testCreateOrUpdateManufacture() {
        // Arrange
        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity.setStatus("Status");
        when(manufacturerCreationRepository.save((ManufacturerCreationEntity) any()))
                .thenReturn(manufacturerCreationEntity);
        when(manufacturerCreationRepository.findByManufacturerName((String) any())).thenReturn(new ArrayList<>());

        ManufacturerCreationEntity manufacturerCreationEntity1 = new ManufacturerCreationEntity();
        manufacturerCreationEntity1.setId(123L);
        manufacturerCreationEntity1.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity1.setStatus("Status");

        // Act and Assert
        assertSame(manufacturerCreationEntity,
                manufacturerCreationServiceImpl.createOrUpdateManufacture(manufacturerCreationEntity1));
        verify(manufacturerCreationRepository).save((ManufacturerCreationEntity) any());
        verify(manufacturerCreationRepository).findByManufacturerName((String) any());
    }

    /**
     * Method under test: {@link ManufacturerCreationServiceImpl#createOrUpdateManufacture(ManufacturerCreationEntity)}
     */
    @Test
    void testCreateOrUpdateManufacture2() {
        // Arrange
        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity.setStatus("Status");

        ManufacturerCreationEntity manufacturerCreationEntity1 = new ManufacturerCreationEntity();
        manufacturerCreationEntity1.setId(123L);
        manufacturerCreationEntity1.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity1.setStatus("Status");

        ArrayList<ManufacturerCreationEntity> manufacturerCreationEntityList = new ArrayList<>();
        manufacturerCreationEntityList.add(manufacturerCreationEntity1);
        when(manufacturerCreationRepository.save((ManufacturerCreationEntity) any()))
                .thenReturn(manufacturerCreationEntity);
        when(manufacturerCreationRepository.findByManufacturerName((String) any()))
                .thenReturn(manufacturerCreationEntityList);

        ManufacturerCreationEntity manufacturerCreationEntity2 = new ManufacturerCreationEntity();
        manufacturerCreationEntity2.setId(123L);
        manufacturerCreationEntity2.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity2.setStatus("Status");

        // Act and Assert
        assertThrows(RecordAlreadyPresentException.class,
                () -> manufacturerCreationServiceImpl.createOrUpdateManufacture(manufacturerCreationEntity2));
        verify(manufacturerCreationRepository).findByManufacturerName((String) any());
    }

    /**
     * Method under test: {@link ManufacturerCreationServiceImpl#createOrUpdateManufacture(ManufacturerCreationEntity)}
     */
    @Test
    void testCreateOrUpdateManufacture3() {
        // Arrange
        when(manufacturerCreationRepository.save((ManufacturerCreationEntity) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));
        when(manufacturerCreationRepository.findByManufacturerName((String) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));

        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity.setStatus("Status");

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> manufacturerCreationServiceImpl.createOrUpdateManufacture(manufacturerCreationEntity));
        verify(manufacturerCreationRepository).findByManufacturerName((String) any());
    }

    /**
     * Method under test: {@link ManufacturerCreationServiceImpl#deleteManufactureById(Long)}
     */
    @Test
    void testDeleteManufactureById() throws RecordNotFoundException {
        // Arrange
        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity.setStatus("Status");
        Optional<ManufacturerCreationEntity> ofResult = Optional.of(manufacturerCreationEntity);
        doNothing().when(manufacturerCreationRepository).deleteById((Long) any());
        when(manufacturerCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act
        manufacturerCreationServiceImpl.deleteManufactureById(123L);

        // Assert that nothing has changed
        verify(manufacturerCreationRepository).findById((Long) any());
        verify(manufacturerCreationRepository).deleteById((Long) any());
        assertTrue(manufacturerCreationServiceImpl.getAllManufactureList().isEmpty());
    }

    /**
     * Method under test: {@link ManufacturerCreationServiceImpl#deleteManufactureById(Long)}
     */
    @Test
    void testDeleteManufactureById2() throws RecordNotFoundException {
        // Arrange
        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity.setStatus("Status");
        Optional<ManufacturerCreationEntity> ofResult = Optional.of(manufacturerCreationEntity);
        doThrow(new UnableToProcessException("An error occurred")).when(manufacturerCreationRepository)
                .deleteById((Long) any());
        when(manufacturerCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> manufacturerCreationServiceImpl.deleteManufactureById(123L));
        verify(manufacturerCreationRepository).findById((Long) any());
        verify(manufacturerCreationRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link ManufacturerCreationServiceImpl#deleteManufactureById(Long)}
     */
    @Test
    void testDeleteManufactureById3() throws RecordNotFoundException {
        // Arrange
        doNothing().when(manufacturerCreationRepository).deleteById((Long) any());
        when(manufacturerCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> manufacturerCreationServiceImpl.deleteManufactureById(123L));
        verify(manufacturerCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ManufacturerCreationServiceImpl#createOrUpdateManufactureList(List)}
     */
    @Test
    void testCreateOrUpdateManufactureList() {
        // Arrange
        when(manufacturerCreationRepository.saveAll((Iterable<ManufacturerCreationEntity>) any()))
                .thenReturn(new ArrayList<>());
        ArrayList<ManufacturerCreationEntity> manufacturerCreationEntityList = new ArrayList<>();

        // Act
        manufacturerCreationServiceImpl.createOrUpdateManufactureList(manufacturerCreationEntityList);

        // Assert that nothing has changed
        verify(manufacturerCreationRepository).saveAll((Iterable<ManufacturerCreationEntity>) any());
        assertEquals(manufacturerCreationEntityList, manufacturerCreationServiceImpl.getAllManufactureList());
    }

    /**
     * Method under test: {@link ManufacturerCreationServiceImpl#createOrUpdateManufactureList(List)}
     */
    @Test
    void testCreateOrUpdateManufactureList2() {
        // Arrange
        when(manufacturerCreationRepository.saveAll((Iterable<ManufacturerCreationEntity>) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> manufacturerCreationServiceImpl.createOrUpdateManufactureList(new ArrayList<>()));
        verify(manufacturerCreationRepository).saveAll((Iterable<ManufacturerCreationEntity>) any());
    }

    /**
     * Method under test: {@link ManufacturerCreationServiceImpl#getAllManufacturerWithPagination(int, int)}
     */
    @Test
    void testGetAllManufacturerWithPagination() {
        // Arrange
        PageImpl<ManufacturerCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(manufacturerCreationRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<ManufacturerCreationEntity> actualAllManufacturerWithPagination = manufacturerCreationServiceImpl
                .getAllManufacturerWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllManufacturerWithPagination);
        assertTrue(actualAllManufacturerWithPagination.toList().isEmpty());
        verify(manufacturerCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link ManufacturerCreationServiceImpl#getAllManufacturerWithPagination(int, int)}
     */
    @Test
    void testGetAllManufacturerWithPagination2() {
        // Arrange
        when(manufacturerCreationRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> manufacturerCreationServiceImpl.getAllManufacturerWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link ManufacturerCreationServiceImpl#getAllManufacturerWithPagination(int, int)}
     */
    @Test
    void testGetAllManufacturerWithPagination3() {
        // Arrange
        when(manufacturerCreationRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> manufacturerCreationServiceImpl.getAllManufacturerWithPagination(2, 3));
        verify(manufacturerCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link ManufacturerCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<ManufacturerCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(manufacturerCreationRepository.findAllByManufacturerNameContains((String) any(), (Pageable) any()))
                .thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<ManufacturerCreationEntity> actualSearch = manufacturerCreationServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(manufacturerCreationRepository).findAllByManufacturerNameContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link ManufacturerCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(manufacturerCreationRepository.findAllByManufacturerNameContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> manufacturerCreationServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(manufacturerCreationRepository).findAllByManufacturerNameContains((String) any(), (Pageable) any());
    }

    /**
     * Method under test: {@link ManufacturerCreationServiceImpl#updateManufacture(ManufacturerCreationEntity)}
     */
    @Test
    void testUpdateManufacture() {
        // Arrange
        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity.setStatus("Status");
        when(manufacturerCreationRepository.save((ManufacturerCreationEntity) any()))
                .thenReturn(manufacturerCreationEntity);

        ManufacturerCreationEntity manufacturerCreationEntity1 = new ManufacturerCreationEntity();
        manufacturerCreationEntity1.setId(123L);
        manufacturerCreationEntity1.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity1.setStatus("Status");

        // Act and Assert
        assertSame(manufacturerCreationEntity,
                manufacturerCreationServiceImpl.updateManufacture(manufacturerCreationEntity1));
        verify(manufacturerCreationRepository).save((ManufacturerCreationEntity) any());
    }

    /**
     * Method under test: {@link ManufacturerCreationServiceImpl#updateManufacture(ManufacturerCreationEntity)}
     */
    @Test
    void testUpdateManufacture2() {
        // Arrange
        when(manufacturerCreationRepository.save((ManufacturerCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity.setStatus("Status");

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> manufacturerCreationServiceImpl.updateManufacture(manufacturerCreationEntity));
        verify(manufacturerCreationRepository).save((ManufacturerCreationEntity) any());
    }
}

