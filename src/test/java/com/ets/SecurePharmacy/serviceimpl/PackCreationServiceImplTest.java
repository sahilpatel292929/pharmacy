package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.PackCreationRepository;
import com.ets.SecurePharmacy.tenant.model.PackCreationEntity;
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

@ContextConfiguration(classes = {PackCreationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PackCreationServiceImplTest {
    @MockBean
    private PackCreationRepository packCreationRepository;

    @Autowired
    private PackCreationServiceImpl packCreationServiceImpl;

    /**
     * Method under test: {@link PackCreationServiceImpl#getAllPackList()}
     */
    @Test
    void testGetAllPackList() {
        // Arrange
        when(packCreationRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(packCreationServiceImpl.getAllPackList().isEmpty());
        verify(packCreationRepository).findAll();
    }

    /**
     * Method under test: {@link PackCreationServiceImpl#getAllPackList()}
     */
    @Test
    void testGetAllPackList2() {
        // Arrange
        PackCreationEntity packCreationEntity = new PackCreationEntity();
        packCreationEntity.setId(123L);
        packCreationEntity.setPackName("Pack Name");
        packCreationEntity.setQty(1);
        packCreationEntity.setStatus("Status");

        ArrayList<PackCreationEntity> packCreationEntityList = new ArrayList<>();
        packCreationEntityList.add(packCreationEntity);
        when(packCreationRepository.findAll()).thenReturn(packCreationEntityList);

        // Act
        List<PackCreationEntity> actualAllPackList = packCreationServiceImpl.getAllPackList();

        // Assert
        assertSame(packCreationEntityList, actualAllPackList);
        assertEquals(1, actualAllPackList.size());
        verify(packCreationRepository).findAll();
    }

    /**
     * Method under test: {@link PackCreationServiceImpl#getAllPackList()}
     */
    @Test
    void testGetAllPackList3() {
        // Arrange
        when(packCreationRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> packCreationServiceImpl.getAllPackList());
        verify(packCreationRepository).findAll();
    }

    /**
     * Method under test: {@link PackCreationServiceImpl#getPakById(Long)}
     */
    @Test
    void testGetPakById() throws RecordNotFoundException {
        // Arrange
        PackCreationEntity packCreationEntity = new PackCreationEntity();
        packCreationEntity.setId(123L);
        packCreationEntity.setPackName("Pack Name");
        packCreationEntity.setQty(1);
        packCreationEntity.setStatus("Status");
        Optional<PackCreationEntity> ofResult = Optional.of(packCreationEntity);
        when(packCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(packCreationEntity, packCreationServiceImpl.getPakById(123L));
        verify(packCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PackCreationServiceImpl#getPakById(Long)}
     */
    @Test
    void testGetPakById2() throws RecordNotFoundException {
        // Arrange
        when(packCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> packCreationServiceImpl.getPakById(123L));
        verify(packCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PackCreationServiceImpl#getPakById(Long)}
     */
    @Test
    void testGetPakById3() throws RecordNotFoundException {
        // Arrange
        when(packCreationRepository.findById((Long) any())).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> packCreationServiceImpl.getPakById(123L));
        verify(packCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PackCreationServiceImpl#getPackByName(String)}
     */
    @Test
    void testGetPackByName() {
        // Arrange
        when(packCreationRepository.findByPackName((String) any())).thenReturn(new ArrayList<>());

        // Act and Assert
        assertFalse(packCreationServiceImpl.getPackByName("Packs"));
        verify(packCreationRepository).findByPackName((String) any());
    }

    /**
     * Method under test: {@link PackCreationServiceImpl#getPackByName(String)}
     */
    @Test
    void testGetPackByName2() {
        // Arrange
        PackCreationEntity packCreationEntity = new PackCreationEntity();
        packCreationEntity.setId(123L);
        packCreationEntity.setPackName("Pack Name");
        packCreationEntity.setQty(1);
        packCreationEntity.setStatus("Status");

        ArrayList<PackCreationEntity> packCreationEntityList = new ArrayList<>();
        packCreationEntityList.add(packCreationEntity);
        when(packCreationRepository.findByPackName((String) any())).thenReturn(packCreationEntityList);

        // Act and Assert
        assertTrue(packCreationServiceImpl.getPackByName("Packs"));
        verify(packCreationRepository).findByPackName((String) any());
    }

    /**
     * Method under test: {@link PackCreationServiceImpl#getPackByName(String)}
     */
    @Test
    void testGetPackByName3() {
        // Arrange
        when(packCreationRepository.findByPackName((String) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> packCreationServiceImpl.getPackByName("Packs"));
        verify(packCreationRepository).findByPackName((String) any());
    }

    /**
     * Method under test: {@link PackCreationServiceImpl#createOrUpdatePack(PackCreationEntity)}
     */
    @Test
    void testCreateOrUpdatePack() {
        // Arrange
        PackCreationEntity packCreationEntity = new PackCreationEntity();
        packCreationEntity.setId(123L);
        packCreationEntity.setPackName("Pack Name");
        packCreationEntity.setQty(1);
        packCreationEntity.setStatus("Status");
        when(packCreationRepository.save((PackCreationEntity) any())).thenReturn(packCreationEntity);
        when(packCreationRepository.findByPackName((String) any())).thenReturn(new ArrayList<>());

        PackCreationEntity packCreationEntity1 = new PackCreationEntity();
        packCreationEntity1.setId(123L);
        packCreationEntity1.setPackName("Pack Name");
        packCreationEntity1.setQty(1);
        packCreationEntity1.setStatus("Status");

        // Act and Assert
        assertSame(packCreationEntity, packCreationServiceImpl.createOrUpdatePack(packCreationEntity1));
        verify(packCreationRepository).save((PackCreationEntity) any());
        verify(packCreationRepository).findByPackName((String) any());
    }

    /**
     * Method under test: {@link PackCreationServiceImpl#createOrUpdatePack(PackCreationEntity)}
     */
    @Test
    void testCreateOrUpdatePack2() {
        // Arrange
        PackCreationEntity packCreationEntity = new PackCreationEntity();
        packCreationEntity.setId(123L);
        packCreationEntity.setPackName("Pack Name");
        packCreationEntity.setQty(1);
        packCreationEntity.setStatus("Status");

        PackCreationEntity packCreationEntity1 = new PackCreationEntity();
        packCreationEntity1.setId(123L);
        packCreationEntity1.setPackName("Pack Name");
        packCreationEntity1.setQty(1);
        packCreationEntity1.setStatus("Status");

        ArrayList<PackCreationEntity> packCreationEntityList = new ArrayList<>();
        packCreationEntityList.add(packCreationEntity1);
        when(packCreationRepository.save((PackCreationEntity) any())).thenReturn(packCreationEntity);
        when(packCreationRepository.findByPackName((String) any())).thenReturn(packCreationEntityList);

        PackCreationEntity packCreationEntity2 = new PackCreationEntity();
        packCreationEntity2.setId(123L);
        packCreationEntity2.setPackName("Pack Name");
        packCreationEntity2.setQty(1);
        packCreationEntity2.setStatus("Status");

        // Act and Assert
        assertThrows(RecordAlreadyPresentException.class,
                () -> packCreationServiceImpl.createOrUpdatePack(packCreationEntity2));
        verify(packCreationRepository).findByPackName((String) any());
    }

    /**
     * Method under test: {@link PackCreationServiceImpl#createOrUpdatePack(PackCreationEntity)}
     */
    @Test
    void testCreateOrUpdatePack3() {
        // Arrange
        when(packCreationRepository.save((PackCreationEntity) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));
        when(packCreationRepository.findByPackName((String) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));

        PackCreationEntity packCreationEntity = new PackCreationEntity();
        packCreationEntity.setId(123L);
        packCreationEntity.setPackName("Pack Name");
        packCreationEntity.setQty(1);
        packCreationEntity.setStatus("Status");

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> packCreationServiceImpl.createOrUpdatePack(packCreationEntity));
        verify(packCreationRepository).findByPackName((String) any());
    }

    /**
     * Method under test: {@link PackCreationServiceImpl#createOrUpdatePack(List)}
     */
    @Test
    void testCreateOrUpdatePack4() {
        // Arrange
        when(packCreationRepository.saveAll((Iterable<PackCreationEntity>) any())).thenReturn(new ArrayList<>());

        // Act
        packCreationServiceImpl.createOrUpdatePack(new ArrayList<>());

        // Assert that nothing has changed
        verify(packCreationRepository).saveAll((Iterable<PackCreationEntity>) any());
        assertTrue(packCreationServiceImpl.getAllPackList().isEmpty());
    }

    /**
     * Method under test: {@link PackCreationServiceImpl#createOrUpdatePack(List)}
     */
    @Test
    void testCreateOrUpdatePack5() {
        // Arrange
        when(packCreationRepository.saveAll((Iterable<PackCreationEntity>) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> packCreationServiceImpl.createOrUpdatePack(new ArrayList<>()));
        verify(packCreationRepository).saveAll((Iterable<PackCreationEntity>) any());
    }

    /**
     * Method under test: {@link PackCreationServiceImpl#getAllPackWithPagination(int, int)}
     */
    @Test
    void testGetAllPackWithPagination() {
        // Arrange
        PageImpl<PackCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(packCreationRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<PackCreationEntity> actualAllPackWithPagination = packCreationServiceImpl.getAllPackWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllPackWithPagination);
        assertTrue(actualAllPackWithPagination.toList().isEmpty());
        verify(packCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link PackCreationServiceImpl#getAllPackWithPagination(int, int)}
     */
    @Test
    void testGetAllPackWithPagination2() {
        // Arrange
        when(packCreationRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> packCreationServiceImpl.getAllPackWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link PackCreationServiceImpl#getAllPackWithPagination(int, int)}
     */
    @Test
    void testGetAllPackWithPagination3() {
        // Arrange
        when(packCreationRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> packCreationServiceImpl.getAllPackWithPagination(2, 3));
        verify(packCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link PackCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<PackCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(packCreationRepository.findAllByPackNameContains((String) any(), (Pageable) any())).thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<PackCreationEntity> actualSearch = packCreationServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(packCreationRepository).findAllByPackNameContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link PackCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(packCreationRepository.findAllByPackNameContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> packCreationServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(packCreationRepository).findAllByPackNameContains((String) any(), (Pageable) any());
    }

    /**
     * Method under test: {@link PackCreationServiceImpl#updatePack(PackCreationEntity)}
     */
    @Test
    void testUpdatePack() {
        // Arrange
        PackCreationEntity packCreationEntity = new PackCreationEntity();
        packCreationEntity.setId(123L);
        packCreationEntity.setPackName("Pack Name");
        packCreationEntity.setQty(1);
        packCreationEntity.setStatus("Status");
        when(packCreationRepository.save((PackCreationEntity) any())).thenReturn(packCreationEntity);

        PackCreationEntity packCreationEntity1 = new PackCreationEntity();
        packCreationEntity1.setId(123L);
        packCreationEntity1.setPackName("Pack Name");
        packCreationEntity1.setQty(1);
        packCreationEntity1.setStatus("Status");

        // Act and Assert
        assertSame(packCreationEntity, packCreationServiceImpl.updatePack(packCreationEntity1));
        verify(packCreationRepository).save((PackCreationEntity) any());
    }

    /**
     * Method under test: {@link PackCreationServiceImpl#updatePack(PackCreationEntity)}
     */
    @Test
    void testUpdatePack2() {
        // Arrange
        when(packCreationRepository.save((PackCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        PackCreationEntity packCreationEntity = new PackCreationEntity();
        packCreationEntity.setId(123L);
        packCreationEntity.setPackName("Pack Name");
        packCreationEntity.setQty(1);
        packCreationEntity.setStatus("Status");

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> packCreationServiceImpl.updatePack(packCreationEntity));
        verify(packCreationRepository).save((PackCreationEntity) any());
    }

    /**
     * Method under test: {@link PackCreationServiceImpl#deletePackById(Long)}
     */
    @Test
    void testDeletePackById() throws RecordNotFoundException {
        // Arrange
        PackCreationEntity packCreationEntity = new PackCreationEntity();
        packCreationEntity.setId(123L);
        packCreationEntity.setPackName("Pack Name");
        packCreationEntity.setQty(1);
        packCreationEntity.setStatus("Status");
        Optional<PackCreationEntity> ofResult = Optional.of(packCreationEntity);
        doNothing().when(packCreationRepository).deleteById((Long) any());
        when(packCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act
        packCreationServiceImpl.deletePackById(123L);

        // Assert that nothing has changed
        verify(packCreationRepository).findById((Long) any());
        verify(packCreationRepository).deleteById((Long) any());
        assertTrue(packCreationServiceImpl.getAllPackList().isEmpty());
    }

    /**
     * Method under test: {@link PackCreationServiceImpl#deletePackById(Long)}
     */
    @Test
    void testDeletePackById2() throws RecordNotFoundException {
        // Arrange
        PackCreationEntity packCreationEntity = new PackCreationEntity();
        packCreationEntity.setId(123L);
        packCreationEntity.setPackName("Pack Name");
        packCreationEntity.setQty(1);
        packCreationEntity.setStatus("Status");
        Optional<PackCreationEntity> ofResult = Optional.of(packCreationEntity);
        doThrow(new UnableToProcessException("An error occurred")).when(packCreationRepository).deleteById((Long) any());
        when(packCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> packCreationServiceImpl.deletePackById(123L));
        verify(packCreationRepository).findById((Long) any());
        verify(packCreationRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link PackCreationServiceImpl#deletePackById(Long)}
     */
    @Test
    void testDeletePackById3() throws RecordNotFoundException {
        // Arrange
        doNothing().when(packCreationRepository).deleteById((Long) any());
        when(packCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> packCreationServiceImpl.deletePackById(123L));
        verify(packCreationRepository).findById((Long) any());
    }
}

