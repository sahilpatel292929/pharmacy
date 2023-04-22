package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.StoreTypeCreationRepository;
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

@ContextConfiguration(classes = {StoreTypeCreationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class StoreTypeCreationServiceImplTest {
    @MockBean
    private StoreTypeCreationRepository storeTypeCreationRepository;

    @Autowired
    private StoreTypeCreationServiceImpl storeTypeCreationServiceImpl;

    /**
     * Method under test: {@link StoreTypeCreationServiceImpl#getAllStoreTypeList()}
     */
    @Test
    void testGetAllStoreTypeList() {
        // Arrange
        when(storeTypeCreationRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(storeTypeCreationServiceImpl.getAllStoreTypeList().isEmpty());
        verify(storeTypeCreationRepository).findAll();
    }

    /**
     * Method under test: {@link StoreTypeCreationServiceImpl#getAllStoreTypeList()}
     */
    @Test
    void testGetAllStoreTypeList2() {
        // Arrange
        StoreTypeCreationEntity storeTypeCreationEntity = new StoreTypeCreationEntity();
        storeTypeCreationEntity.setId(123L);
        storeTypeCreationEntity.setStatus("Status");
        storeTypeCreationEntity.setStoreTypeName("Store Type Name");

        ArrayList<StoreTypeCreationEntity> storeTypeCreationEntityList = new ArrayList<>();
        storeTypeCreationEntityList.add(storeTypeCreationEntity);
        when(storeTypeCreationRepository.findAll()).thenReturn(storeTypeCreationEntityList);

        // Act
        List<StoreTypeCreationEntity> actualAllStoreTypeList = storeTypeCreationServiceImpl.getAllStoreTypeList();

        // Assert
        assertSame(storeTypeCreationEntityList, actualAllStoreTypeList);
        assertEquals(1, actualAllStoreTypeList.size());
        verify(storeTypeCreationRepository).findAll();
    }

    /**
     * Method under test: {@link StoreTypeCreationServiceImpl#getAllStoreTypeList()}
     */
    @Test
    void testGetAllStoreTypeList3() {
        // Arrange
        when(storeTypeCreationRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> storeTypeCreationServiceImpl.getAllStoreTypeList());
        verify(storeTypeCreationRepository).findAll();
    }

    /**
     * Method under test: {@link StoreTypeCreationServiceImpl#getStoreById(Long)}
     */
    @Test
    void testGetStoreById() throws RecordNotFoundException {
        // Arrange
        StoreTypeCreationEntity storeTypeCreationEntity = new StoreTypeCreationEntity();
        storeTypeCreationEntity.setId(123L);
        storeTypeCreationEntity.setStatus("Status");
        storeTypeCreationEntity.setStoreTypeName("Store Type Name");
        Optional<StoreTypeCreationEntity> ofResult = Optional.of(storeTypeCreationEntity);
        when(storeTypeCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(storeTypeCreationEntity, storeTypeCreationServiceImpl.getStoreById(123L));
        verify(storeTypeCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link StoreTypeCreationServiceImpl#getStoreById(Long)}
     */
    @Test
    void testGetStoreById2() throws RecordNotFoundException {
        // Arrange
        when(storeTypeCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> storeTypeCreationServiceImpl.getStoreById(123L));
        verify(storeTypeCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link StoreTypeCreationServiceImpl#getStoreById(Long)}
     */
    @Test
    void testGetStoreById3() throws RecordNotFoundException {
        // Arrange
        when(storeTypeCreationRepository.findById((Long) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> storeTypeCreationServiceImpl.getStoreById(123L));
        verify(storeTypeCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link StoreTypeCreationServiceImpl#getStoreByName(String)}
     */
    @Test
    void testGetStoreByName() {
        // Arrange
        when(storeTypeCreationRepository.findByStoreTypeName((String) any())).thenReturn(new ArrayList<>());

        // Act and Assert
        assertFalse(storeTypeCreationServiceImpl.getStoreByName("Store"));
        verify(storeTypeCreationRepository).findByStoreTypeName((String) any());
    }

    /**
     * Method under test: {@link StoreTypeCreationServiceImpl#getStoreByName(String)}
     */
    @Test
    void testGetStoreByName2() {
        // Arrange
        StoreTypeCreationEntity storeTypeCreationEntity = new StoreTypeCreationEntity();
        storeTypeCreationEntity.setId(123L);
        storeTypeCreationEntity.setStatus("Status");
        storeTypeCreationEntity.setStoreTypeName("Store Type Name");

        ArrayList<StoreTypeCreationEntity> storeTypeCreationEntityList = new ArrayList<>();
        storeTypeCreationEntityList.add(storeTypeCreationEntity);
        when(storeTypeCreationRepository.findByStoreTypeName((String) any())).thenReturn(storeTypeCreationEntityList);

        // Act and Assert
        assertTrue(storeTypeCreationServiceImpl.getStoreByName("Store"));
        verify(storeTypeCreationRepository).findByStoreTypeName((String) any());
    }

    /**
     * Method under test: {@link StoreTypeCreationServiceImpl#getStoreByName(String)}
     */
    @Test
    void testGetStoreByName3() {
        // Arrange
        when(storeTypeCreationRepository.findByStoreTypeName((String) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> storeTypeCreationServiceImpl.getStoreByName("Store"));
        verify(storeTypeCreationRepository).findByStoreTypeName((String) any());
    }

    /**
     * Method under test: {@link StoreTypeCreationServiceImpl#createOrUpdateStoreType(StoreTypeCreationEntity)}
     */
    @Test
    void testCreateOrUpdateStoreType() {
        // Arrange
        StoreTypeCreationEntity storeTypeCreationEntity = new StoreTypeCreationEntity();
        storeTypeCreationEntity.setId(123L);
        storeTypeCreationEntity.setStatus("Status");
        storeTypeCreationEntity.setStoreTypeName("Store Type Name");
        when(storeTypeCreationRepository.save((StoreTypeCreationEntity) any())).thenReturn(storeTypeCreationEntity);

        StoreTypeCreationEntity storeTypeCreationEntity1 = new StoreTypeCreationEntity();
        storeTypeCreationEntity1.setId(123L);
        storeTypeCreationEntity1.setStatus("Status");
        storeTypeCreationEntity1.setStoreTypeName("Store Type Name");

        // Act and Assert
        assertSame(storeTypeCreationEntity, storeTypeCreationServiceImpl.createOrUpdateStoreType(storeTypeCreationEntity1));
        verify(storeTypeCreationRepository).save((StoreTypeCreationEntity) any());
    }

    /**
     * Method under test: {@link StoreTypeCreationServiceImpl#createOrUpdateStoreType(StoreTypeCreationEntity)}
     */
    @Test
    void testCreateOrUpdateStoreType2() {
        // Arrange
        when(storeTypeCreationRepository.save((StoreTypeCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        StoreTypeCreationEntity storeTypeCreationEntity = new StoreTypeCreationEntity();
        storeTypeCreationEntity.setId(123L);
        storeTypeCreationEntity.setStatus("Status");
        storeTypeCreationEntity.setStoreTypeName("Store Type Name");

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> storeTypeCreationServiceImpl.createOrUpdateStoreType(storeTypeCreationEntity));
        verify(storeTypeCreationRepository).save((StoreTypeCreationEntity) any());
    }

    /**
     * Method under test: {@link StoreTypeCreationServiceImpl#deleteStoreById(Long)}
     */
    @Test
    void testDeleteStoreById() throws RecordNotFoundException {
        // Arrange
        StoreTypeCreationEntity storeTypeCreationEntity = new StoreTypeCreationEntity();
        storeTypeCreationEntity.setId(123L);
        storeTypeCreationEntity.setStatus("Status");
        storeTypeCreationEntity.setStoreTypeName("Store Type Name");
        Optional<StoreTypeCreationEntity> ofResult = Optional.of(storeTypeCreationEntity);
        doNothing().when(storeTypeCreationRepository).deleteById((Long) any());
        when(storeTypeCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act
        storeTypeCreationServiceImpl.deleteStoreById(123L);

        // Assert that nothing has changed
        verify(storeTypeCreationRepository).findById((Long) any());
        verify(storeTypeCreationRepository).deleteById((Long) any());
        assertTrue(storeTypeCreationServiceImpl.getAllStoreTypeList().isEmpty());
    }

    /**
     * Method under test: {@link StoreTypeCreationServiceImpl#deleteStoreById(Long)}
     */
    @Test
    void testDeleteStoreById2() throws RecordNotFoundException {
        // Arrange
        StoreTypeCreationEntity storeTypeCreationEntity = new StoreTypeCreationEntity();
        storeTypeCreationEntity.setId(123L);
        storeTypeCreationEntity.setStatus("Status");
        storeTypeCreationEntity.setStoreTypeName("Store Type Name");
        Optional<StoreTypeCreationEntity> ofResult = Optional.of(storeTypeCreationEntity);
        doThrow(new UnableToProcessException("An error occurred")).when(storeTypeCreationRepository)
                .deleteById((Long) any());
        when(storeTypeCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> storeTypeCreationServiceImpl.deleteStoreById(123L));
        verify(storeTypeCreationRepository).findById((Long) any());
        verify(storeTypeCreationRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link StoreTypeCreationServiceImpl#deleteStoreById(Long)}
     */
    @Test
    void testDeleteStoreById3() throws RecordNotFoundException {
        // Arrange
        doNothing().when(storeTypeCreationRepository).deleteById((Long) any());
        when(storeTypeCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> storeTypeCreationServiceImpl.deleteStoreById(123L));
        verify(storeTypeCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link StoreTypeCreationServiceImpl#getAllStoreTypeWithPagination(int, int)}
     */
    @Test
    void testGetAllStoreTypeWithPagination() {
        // Arrange
        PageImpl<StoreTypeCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(storeTypeCreationRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<StoreTypeCreationEntity> actualAllStoreTypeWithPagination = storeTypeCreationServiceImpl
                .getAllStoreTypeWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllStoreTypeWithPagination);
        assertTrue(actualAllStoreTypeWithPagination.toList().isEmpty());
        verify(storeTypeCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link StoreTypeCreationServiceImpl#getAllStoreTypeWithPagination(int, int)}
     */
    @Test
    void testGetAllStoreTypeWithPagination2() {
        // Arrange
        when(storeTypeCreationRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> storeTypeCreationServiceImpl.getAllStoreTypeWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link StoreTypeCreationServiceImpl#getAllStoreTypeWithPagination(int, int)}
     */
    @Test
    void testGetAllStoreTypeWithPagination3() {
        // Arrange
        when(storeTypeCreationRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> storeTypeCreationServiceImpl.getAllStoreTypeWithPagination(2, 3));
        verify(storeTypeCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link StoreTypeCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<StoreTypeCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(storeTypeCreationRepository.findAllByStoreTypeNameContains((String) any(), (Pageable) any()))
                .thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<StoreTypeCreationEntity> actualSearch = storeTypeCreationServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(storeTypeCreationRepository).findAllByStoreTypeNameContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link StoreTypeCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(storeTypeCreationRepository.findAllByStoreTypeNameContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> storeTypeCreationServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(storeTypeCreationRepository).findAllByStoreTypeNameContains((String) any(), (Pageable) any());
    }

    /**
     * Method under test: {@link StoreTypeCreationServiceImpl#updateStoreType(StoreTypeCreationEntity)}
     */
    @Test
    void testUpdateStoreType() {
        // Arrange
        StoreTypeCreationEntity storeTypeCreationEntity = new StoreTypeCreationEntity();
        storeTypeCreationEntity.setId(123L);
        storeTypeCreationEntity.setStatus("Status");
        storeTypeCreationEntity.setStoreTypeName("Store Type Name");
        when(storeTypeCreationRepository.save((StoreTypeCreationEntity) any())).thenReturn(storeTypeCreationEntity);

        StoreTypeCreationEntity storeTypeCreationEntity1 = new StoreTypeCreationEntity();
        storeTypeCreationEntity1.setId(123L);
        storeTypeCreationEntity1.setStatus("Status");
        storeTypeCreationEntity1.setStoreTypeName("Store Type Name");

        // Act and Assert
        assertSame(storeTypeCreationEntity, storeTypeCreationServiceImpl.updateStoreType(storeTypeCreationEntity1));
        verify(storeTypeCreationRepository).save((StoreTypeCreationEntity) any());
    }

    /**
     * Method under test: {@link StoreTypeCreationServiceImpl#updateStoreType(StoreTypeCreationEntity)}
     */
    @Test
    void testUpdateStoreType2() {
        // Arrange
        when(storeTypeCreationRepository.save((StoreTypeCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        StoreTypeCreationEntity storeTypeCreationEntity = new StoreTypeCreationEntity();
        storeTypeCreationEntity.setId(123L);
        storeTypeCreationEntity.setStatus("Status");
        storeTypeCreationEntity.setStoreTypeName("Store Type Name");

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> storeTypeCreationServiceImpl.updateStoreType(storeTypeCreationEntity));
        verify(storeTypeCreationRepository).save((StoreTypeCreationEntity) any());
    }
}

