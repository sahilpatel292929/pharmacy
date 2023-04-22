package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.HsnSacCreationRepository;
import com.ets.SecurePharmacy.tenant.model.HsnSacCreationEntity;
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {HsnSacCreationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class HsnSacCreationServiceImplTest {
    @MockBean
    private HsnSacCreationRepository hsnSacCreationRepository;

    @Autowired
    private HsnSacCreationServiceImpl hsnSacCreationServiceImpl;

    /**
     * Method under test: {@link HsnSacCreationServiceImpl#getAllHSNSACList()}
     */
    @Test
    void testGetAllHSNSACList() {
        // Arrange
        when(hsnSacCreationRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(hsnSacCreationServiceImpl.getAllHSNSACList().isEmpty());
        verify(hsnSacCreationRepository).findAll();
    }

    /**
     * Method under test: {@link HsnSacCreationServiceImpl#getAllHSNSACList()}
     */
    @Test
    void testGetAllHSNSACList2() {
        // Arrange
        HsnSacCreationEntity hsnSacCreationEntity = new HsnSacCreationEntity();
        hsnSacCreationEntity.setDescirption("Descirption");
        hsnSacCreationEntity.setHsnName("Hsn Name");
        hsnSacCreationEntity.setId(123L);
        hsnSacCreationEntity.setStatus("Status");

        ArrayList<HsnSacCreationEntity> hsnSacCreationEntityList = new ArrayList<>();
        hsnSacCreationEntityList.add(hsnSacCreationEntity);
        when(hsnSacCreationRepository.findAll()).thenReturn(hsnSacCreationEntityList);

        // Act
        List<HsnSacCreationEntity> actualAllHSNSACList = hsnSacCreationServiceImpl.getAllHSNSACList();

        // Assert
        assertSame(hsnSacCreationEntityList, actualAllHSNSACList);
        assertEquals(1, actualAllHSNSACList.size());
        verify(hsnSacCreationRepository).findAll();
    }

    /**
     * Method under test: {@link HsnSacCreationServiceImpl#getAllHSNSACList()}
     */
    @Test
    void testGetAllHSNSACList3() {
        // Arrange
        when(hsnSacCreationRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> hsnSacCreationServiceImpl.getAllHSNSACList());
        verify(hsnSacCreationRepository).findAll();
    }

    /**
     * Method under test: {@link HsnSacCreationServiceImpl#getHsnById(Long)}
     */
    @Test
    void testGetHsnById() throws RecordNotFoundException {
        // Arrange
        HsnSacCreationEntity hsnSacCreationEntity = new HsnSacCreationEntity();
        hsnSacCreationEntity.setDescirption("Descirption");
        hsnSacCreationEntity.setHsnName("Hsn Name");
        hsnSacCreationEntity.setId(123L);
        hsnSacCreationEntity.setStatus("Status");
        Optional<HsnSacCreationEntity> ofResult = Optional.of(hsnSacCreationEntity);
        when(hsnSacCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(hsnSacCreationEntity, hsnSacCreationServiceImpl.getHsnById(123L));
        verify(hsnSacCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link HsnSacCreationServiceImpl#getHsnById(Long)}
     */
    @Test
    void testGetHsnById2() throws RecordNotFoundException {
        // Arrange
        when(hsnSacCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> hsnSacCreationServiceImpl.getHsnById(123L));
        verify(hsnSacCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link HsnSacCreationServiceImpl#getHsnById(Long)}
     */
    @Test
    void testGetHsnById3() throws RecordNotFoundException {
        // Arrange
        when(hsnSacCreationRepository.findById((Long) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> hsnSacCreationServiceImpl.getHsnById(123L));
        verify(hsnSacCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link HsnSacCreationServiceImpl#getHsnByName(String)}
     */
    @Test
    void testGetHsnByName() {
        // Arrange
        when(hsnSacCreationRepository.findByHsnName((String) any())).thenReturn(new ArrayList<>());

        // Act and Assert
        assertFalse(hsnSacCreationServiceImpl.getHsnByName("Hsn"));
        verify(hsnSacCreationRepository).findByHsnName((String) any());
    }

    /**
     * Method under test: {@link HsnSacCreationServiceImpl#getHsnByName(String)}
     */
    @Test
    void testGetHsnByName2() {
        // Arrange
        HsnSacCreationEntity hsnSacCreationEntity = new HsnSacCreationEntity();
        hsnSacCreationEntity.setDescirption("Descirption");
        hsnSacCreationEntity.setHsnName("Hsn Name");
        hsnSacCreationEntity.setId(123L);
        hsnSacCreationEntity.setStatus("Status");

        ArrayList<HsnSacCreationEntity> hsnSacCreationEntityList = new ArrayList<>();
        hsnSacCreationEntityList.add(hsnSacCreationEntity);
        when(hsnSacCreationRepository.findByHsnName((String) any())).thenReturn(hsnSacCreationEntityList);

        // Act and Assert
        assertTrue(hsnSacCreationServiceImpl.getHsnByName("Hsn"));
        verify(hsnSacCreationRepository).findByHsnName((String) any());
    }

    /**
     * Method under test: {@link HsnSacCreationServiceImpl#getHsnByName(String)}
     */
    @Test
    void testGetHsnByName3() {
        // Arrange
        when(hsnSacCreationRepository.findByHsnName((String) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> hsnSacCreationServiceImpl.getHsnByName("Hsn"));
        verify(hsnSacCreationRepository).findByHsnName((String) any());
    }

    /**
     * Method under test: {@link HsnSacCreationServiceImpl#createOrUpdateHsn(HsnSacCreationEntity)}
     */
    @Test
    void testCreateOrUpdateHsn() {
        // Arrange
        HsnSacCreationEntity hsnSacCreationEntity = new HsnSacCreationEntity();
        hsnSacCreationEntity.setDescirption("Descirption");
        hsnSacCreationEntity.setHsnName("Hsn Name");
        hsnSacCreationEntity.setId(123L);
        hsnSacCreationEntity.setStatus("Status");
        when(hsnSacCreationRepository.save((HsnSacCreationEntity) any())).thenReturn(hsnSacCreationEntity);
        when(hsnSacCreationRepository.findByHsnName((String) any())).thenReturn(new ArrayList<>());

        HsnSacCreationEntity hsnSacCreationEntity1 = new HsnSacCreationEntity();
        hsnSacCreationEntity1.setDescirption("Descirption");
        hsnSacCreationEntity1.setHsnName("Hsn Name");
        hsnSacCreationEntity1.setId(123L);
        hsnSacCreationEntity1.setStatus("Status");

        // Act and Assert
        assertSame(hsnSacCreationEntity, hsnSacCreationServiceImpl.createOrUpdateHsn(hsnSacCreationEntity1));
        verify(hsnSacCreationRepository).save((HsnSacCreationEntity) any());
        verify(hsnSacCreationRepository).findByHsnName((String) any());
    }

    /**
     * Method under test: {@link HsnSacCreationServiceImpl#createOrUpdateHsn(HsnSacCreationEntity)}
     */
    @Test
    void testCreateOrUpdateHsn2() {
        // Arrange
        HsnSacCreationEntity hsnSacCreationEntity = new HsnSacCreationEntity();
        hsnSacCreationEntity.setDescirption("Descirption");
        hsnSacCreationEntity.setHsnName("Hsn Name");
        hsnSacCreationEntity.setId(123L);
        hsnSacCreationEntity.setStatus("Status");

        HsnSacCreationEntity hsnSacCreationEntity1 = new HsnSacCreationEntity();
        hsnSacCreationEntity1.setDescirption("Descirption");
        hsnSacCreationEntity1.setHsnName("Hsn Name");
        hsnSacCreationEntity1.setId(123L);
        hsnSacCreationEntity1.setStatus("Status");

        ArrayList<HsnSacCreationEntity> hsnSacCreationEntityList = new ArrayList<>();
        hsnSacCreationEntityList.add(hsnSacCreationEntity1);
        when(hsnSacCreationRepository.save((HsnSacCreationEntity) any())).thenReturn(hsnSacCreationEntity);
        when(hsnSacCreationRepository.findByHsnName((String) any())).thenReturn(hsnSacCreationEntityList);

        HsnSacCreationEntity hsnSacCreationEntity2 = new HsnSacCreationEntity();
        hsnSacCreationEntity2.setDescirption("Descirption");
        hsnSacCreationEntity2.setHsnName("Hsn Name");
        hsnSacCreationEntity2.setId(123L);
        hsnSacCreationEntity2.setStatus("Status");

        // Act and Assert
        assertThrows(RecordAlreadyPresentException.class,
                () -> hsnSacCreationServiceImpl.createOrUpdateHsn(hsnSacCreationEntity2));
        verify(hsnSacCreationRepository).findByHsnName((String) any());
    }

    /**
     * Method under test: {@link HsnSacCreationServiceImpl#createOrUpdateHsn(HsnSacCreationEntity)}
     */
    @Test
    void testCreateOrUpdateHsn3() {
        // Arrange
        when(hsnSacCreationRepository.save((HsnSacCreationEntity) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));
        when(hsnSacCreationRepository.findByHsnName((String) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));

        HsnSacCreationEntity hsnSacCreationEntity = new HsnSacCreationEntity();
        hsnSacCreationEntity.setDescirption("Descirption");
        hsnSacCreationEntity.setHsnName("Hsn Name");
        hsnSacCreationEntity.setId(123L);
        hsnSacCreationEntity.setStatus("Status");

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> hsnSacCreationServiceImpl.createOrUpdateHsn(hsnSacCreationEntity));
        verify(hsnSacCreationRepository).findByHsnName((String) any());
    }

    /**
     * Method under test: {@link HsnSacCreationServiceImpl#deleteHsnById(Long)}
     */
    @Test
    void testDeleteHsnById() throws RecordNotFoundException {
        // Arrange
        HsnSacCreationEntity hsnSacCreationEntity = new HsnSacCreationEntity();
        hsnSacCreationEntity.setDescirption("Descirption");
        hsnSacCreationEntity.setHsnName("Hsn Name");
        hsnSacCreationEntity.setId(123L);
        hsnSacCreationEntity.setStatus("Status");
        Optional<HsnSacCreationEntity> ofResult = Optional.of(hsnSacCreationEntity);
        doNothing().when(hsnSacCreationRepository).deleteById((Long) any());
        when(hsnSacCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act
        hsnSacCreationServiceImpl.deleteHsnById(123L);

        // Assert that nothing has changed
        verify(hsnSacCreationRepository).findById((Long) any());
        verify(hsnSacCreationRepository).deleteById((Long) any());
        assertTrue(hsnSacCreationServiceImpl.getAllHSNSACList().isEmpty());
    }

    /**
     * Method under test: {@link HsnSacCreationServiceImpl#deleteHsnById(Long)}
     */
    @Test
    void testDeleteHsnById2() throws RecordNotFoundException {
        // Arrange
        HsnSacCreationEntity hsnSacCreationEntity = new HsnSacCreationEntity();
        hsnSacCreationEntity.setDescirption("Descirption");
        hsnSacCreationEntity.setHsnName("Hsn Name");
        hsnSacCreationEntity.setId(123L);
        hsnSacCreationEntity.setStatus("Status");
        Optional<HsnSacCreationEntity> ofResult = Optional.of(hsnSacCreationEntity);
        doThrow(new UnableToProcessException("An error occurred")).when(hsnSacCreationRepository)
                .deleteById((Long) any());
        when(hsnSacCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> hsnSacCreationServiceImpl.deleteHsnById(123L));
        verify(hsnSacCreationRepository).findById((Long) any());
        verify(hsnSacCreationRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link HsnSacCreationServiceImpl#deleteHsnById(Long)}
     */
    @Test
    void testDeleteHsnById3() throws RecordNotFoundException {
        // Arrange
        doNothing().when(hsnSacCreationRepository).deleteById((Long) any());
        when(hsnSacCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> hsnSacCreationServiceImpl.deleteHsnById(123L));
        verify(hsnSacCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link HsnSacCreationServiceImpl#createOrUpdateHsnList(List)}
     */
    @Test
    void testCreateOrUpdateHsnList() {
        // Arrange
        when(hsnSacCreationRepository.saveAll((Iterable<HsnSacCreationEntity>) any())).thenReturn(new ArrayList<>());
        ArrayList<HsnSacCreationEntity> hsnSacCreationEntityList = new ArrayList<>();

        // Act
        hsnSacCreationServiceImpl.createOrUpdateHsnList(hsnSacCreationEntityList);

        // Assert that nothing has changed
        verify(hsnSacCreationRepository).saveAll((Iterable<HsnSacCreationEntity>) any());
        assertEquals(hsnSacCreationEntityList, hsnSacCreationServiceImpl.getAllHSNSACList());
    }

    /**
     * Method under test: {@link HsnSacCreationServiceImpl#createOrUpdateHsnList(List)}
     */
    @Test
    void testCreateOrUpdateHsnList2() {
        // Arrange
        when(hsnSacCreationRepository.saveAll((Iterable<HsnSacCreationEntity>) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> hsnSacCreationServiceImpl.createOrUpdateHsnList(new ArrayList<>()));
        verify(hsnSacCreationRepository).saveAll((Iterable<HsnSacCreationEntity>) any());
    }

    /**
     * Method under test: {@link HsnSacCreationServiceImpl#getAllHsnSacWithPagination(int, int)}
     */
    @Test
    void testGetAllHsnSacWithPagination() {
        // Arrange
        PageImpl<HsnSacCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(hsnSacCreationRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<HsnSacCreationEntity> actualAllHsnSacWithPagination = hsnSacCreationServiceImpl.getAllHsnSacWithPagination(2,
                3);

        // Assert
        assertSame(pageImpl, actualAllHsnSacWithPagination);
        assertTrue(actualAllHsnSacWithPagination.toList().isEmpty());
        verify(hsnSacCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link HsnSacCreationServiceImpl#getAllHsnSacWithPagination(int, int)}
     */
    @Test
    void testGetAllHsnSacWithPagination2() {
        // Arrange
        when(hsnSacCreationRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> hsnSacCreationServiceImpl.getAllHsnSacWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link HsnSacCreationServiceImpl#getAllHsnSacWithPagination(int, int)}
     */
    @Test
    void testGetAllHsnSacWithPagination3() {
        // Arrange
        when(hsnSacCreationRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> hsnSacCreationServiceImpl.getAllHsnSacWithPagination(2, 3));
        verify(hsnSacCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link HsnSacCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<HsnSacCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(hsnSacCreationRepository.findAllByHsnNameContains((String) any(), (Pageable) any())).thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<HsnSacCreationEntity> actualSearch = hsnSacCreationServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(hsnSacCreationRepository).findAllByHsnNameContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link HsnSacCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(hsnSacCreationRepository.findAllByHsnNameContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> hsnSacCreationServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(hsnSacCreationRepository).findAllByHsnNameContains((String) any(), (Pageable) any());
    }

    /**
     * Method under test: {@link HsnSacCreationServiceImpl#updateHsn(HsnSacCreationEntity)}
     */
    @Test
    void testUpdateHsn() {
        // Arrange
        HsnSacCreationEntity hsnSacCreationEntity = new HsnSacCreationEntity();
        hsnSacCreationEntity.setDescirption("Descirption");
        hsnSacCreationEntity.setHsnName("Hsn Name");
        hsnSacCreationEntity.setId(123L);
        hsnSacCreationEntity.setStatus("Status");

        // Act and Assert
        assertNull(hsnSacCreationServiceImpl.updateHsn(hsnSacCreationEntity));
    }

    /**
     * Method under test: {@link HsnSacCreationServiceImpl#updateHsn(HsnSacCreationEntity)}
     */
    @Test
    void testUpdateHsn2() {
        // Arrange
        HsnSacCreationEntity hsnSacCreationEntity = mock(HsnSacCreationEntity.class);
        doNothing().when(hsnSacCreationEntity).setDescirption((String) any());
        doNothing().when(hsnSacCreationEntity).setHsnName((String) any());
        doNothing().when(hsnSacCreationEntity).setId((Long) any());
        doNothing().when(hsnSacCreationEntity).setStatus((String) any());
        hsnSacCreationEntity.setDescirption("Descirption");
        hsnSacCreationEntity.setHsnName("Hsn Name");
        hsnSacCreationEntity.setId(123L);
        hsnSacCreationEntity.setStatus("Status");

        // Act and Assert
        assertNull(hsnSacCreationServiceImpl.updateHsn(hsnSacCreationEntity));
        verify(hsnSacCreationEntity).setDescirption((String) any());
        verify(hsnSacCreationEntity).setHsnName((String) any());
        verify(hsnSacCreationEntity).setId((Long) any());
        verify(hsnSacCreationEntity).setStatus((String) any());
    }
}

