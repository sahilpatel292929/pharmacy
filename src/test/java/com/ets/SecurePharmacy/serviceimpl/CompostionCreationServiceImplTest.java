package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.CompostionCreationRepository;
import com.ets.SecurePharmacy.tenant.model.CompostionCreationEntity;
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

@ContextConfiguration(classes = {CompostionCreationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CompostionCreationServiceImplTest {
    @MockBean
    private CompostionCreationRepository compostionCreationRepository;

    @Autowired
    private CompostionCreationServiceImpl compostionCreationServiceImpl;

    /**
     * Method under test: {@link CompostionCreationServiceImpl#getAllCompositionList()}
     */
    @Test
    void testGetAllCompositionList() {
        // Arrange
        when(compostionCreationRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(compostionCreationServiceImpl.getAllCompositionList().isEmpty());
        verify(compostionCreationRepository).findAll();
    }

    /**
     * Method under test: {@link CompostionCreationServiceImpl#getAllCompositionList()}
     */
    @Test
    void testGetAllCompositionList2() {
        // Arrange
        CompostionCreationEntity compostionCreationEntity = new CompostionCreationEntity();
        compostionCreationEntity.setCompName("Comp Name");
        compostionCreationEntity.setId(123L);
        compostionCreationEntity.setStatus("Status");

        ArrayList<CompostionCreationEntity> compostionCreationEntityList = new ArrayList<>();
        compostionCreationEntityList.add(compostionCreationEntity);
        when(compostionCreationRepository.findAll()).thenReturn(compostionCreationEntityList);

        // Act
        List<CompostionCreationEntity> actualAllCompositionList = compostionCreationServiceImpl.getAllCompositionList();

        // Assert
        assertSame(compostionCreationEntityList, actualAllCompositionList);
        assertEquals(1, actualAllCompositionList.size());
        verify(compostionCreationRepository).findAll();
    }

    /**
     * Method under test: {@link CompostionCreationServiceImpl#getAllCompositionList()}
     */
    @Test
    void testGetAllCompositionList3() {
        // Arrange
        when(compostionCreationRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> compostionCreationServiceImpl.getAllCompositionList());
        verify(compostionCreationRepository).findAll();
    }

    /**
     * Method under test: {@link CompostionCreationServiceImpl#getCompositionById(Long)}
     */
    @Test
    void testGetCompositionById() throws RecordNotFoundException {
        // Arrange
        CompostionCreationEntity compostionCreationEntity = new CompostionCreationEntity();
        compostionCreationEntity.setCompName("Comp Name");
        compostionCreationEntity.setId(123L);
        compostionCreationEntity.setStatus("Status");
        Optional<CompostionCreationEntity> ofResult = Optional.of(compostionCreationEntity);
        when(compostionCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(compostionCreationEntity, compostionCreationServiceImpl.getCompositionById(123L));
        verify(compostionCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CompostionCreationServiceImpl#getCompositionById(Long)}
     */
    @Test
    void testGetCompositionById2() throws RecordNotFoundException {
        // Arrange
        when(compostionCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> compostionCreationServiceImpl.getCompositionById(123L));
        verify(compostionCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CompostionCreationServiceImpl#getCompositionById(Long)}
     */
    @Test
    void testGetCompositionById3() throws RecordNotFoundException {
        // Arrange
        when(compostionCreationRepository.findById((Long) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> compostionCreationServiceImpl.getCompositionById(123L));
        verify(compostionCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CompostionCreationServiceImpl#getCompositonByName(String)}
     */
    @Test
    void testGetCompositonByName() {
        // Arrange
        when(compostionCreationRepository.findByCompName((String) any())).thenReturn(new ArrayList<>());

        // Act and Assert
        assertFalse(compostionCreationServiceImpl.getCompositonByName("Com"));
        verify(compostionCreationRepository).findByCompName((String) any());
    }

    /**
     * Method under test: {@link CompostionCreationServiceImpl#getCompositonByName(String)}
     */
    @Test
    void testGetCompositonByName2() {
        // Arrange
        CompostionCreationEntity compostionCreationEntity = new CompostionCreationEntity();
        compostionCreationEntity.setCompName("Comp Name");
        compostionCreationEntity.setId(123L);
        compostionCreationEntity.setStatus("Status");

        ArrayList<CompostionCreationEntity> compostionCreationEntityList = new ArrayList<>();
        compostionCreationEntityList.add(compostionCreationEntity);
        when(compostionCreationRepository.findByCompName((String) any())).thenReturn(compostionCreationEntityList);

        // Act and Assert
        assertTrue(compostionCreationServiceImpl.getCompositonByName("Com"));
        verify(compostionCreationRepository).findByCompName((String) any());
    }

    /**
     * Method under test: {@link CompostionCreationServiceImpl#getCompositonByName(String)}
     */
    @Test
    void testGetCompositonByName3() {
        // Arrange
        when(compostionCreationRepository.findByCompName((String) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> compostionCreationServiceImpl.getCompositonByName("Com"));
        verify(compostionCreationRepository).findByCompName((String) any());
    }

    /**
     * Method under test: {@link CompostionCreationServiceImpl#createOrUpdateCompostion(CompostionCreationEntity)}
     */
    @Test
    void testCreateOrUpdateCompostion() {
        // Arrange
        CompostionCreationEntity compostionCreationEntity = new CompostionCreationEntity();
        compostionCreationEntity.setCompName("Comp Name");
        compostionCreationEntity.setId(123L);
        compostionCreationEntity.setStatus("Status");
        when(compostionCreationRepository.save((CompostionCreationEntity) any())).thenReturn(compostionCreationEntity);
        when(compostionCreationRepository.findByCompName((String) any())).thenReturn(new ArrayList<>());

        CompostionCreationEntity compostionCreationEntity1 = new CompostionCreationEntity();
        compostionCreationEntity1.setCompName("Comp Name");
        compostionCreationEntity1.setId(123L);
        compostionCreationEntity1.setStatus("Status");

        // Act and Assert
        assertSame(compostionCreationEntity,
                compostionCreationServiceImpl.createOrUpdateCompostion(compostionCreationEntity1));
        verify(compostionCreationRepository).save((CompostionCreationEntity) any());
        verify(compostionCreationRepository).findByCompName((String) any());
    }

    /**
     * Method under test: {@link CompostionCreationServiceImpl#createOrUpdateCompostion(CompostionCreationEntity)}
     */
    @Test
    void testCreateOrUpdateCompostion2() {
        // Arrange
        CompostionCreationEntity compostionCreationEntity = new CompostionCreationEntity();
        compostionCreationEntity.setCompName("Comp Name");
        compostionCreationEntity.setId(123L);
        compostionCreationEntity.setStatus("Status");

        CompostionCreationEntity compostionCreationEntity1 = new CompostionCreationEntity();
        compostionCreationEntity1.setCompName("Comp Name");
        compostionCreationEntity1.setId(123L);
        compostionCreationEntity1.setStatus("Status");

        ArrayList<CompostionCreationEntity> compostionCreationEntityList = new ArrayList<>();
        compostionCreationEntityList.add(compostionCreationEntity1);
        when(compostionCreationRepository.save((CompostionCreationEntity) any())).thenReturn(compostionCreationEntity);
        when(compostionCreationRepository.findByCompName((String) any())).thenReturn(compostionCreationEntityList);

        CompostionCreationEntity compostionCreationEntity2 = new CompostionCreationEntity();
        compostionCreationEntity2.setCompName("Comp Name");
        compostionCreationEntity2.setId(123L);
        compostionCreationEntity2.setStatus("Status");

        // Act and Assert
        assertThrows(RecordAlreadyPresentException.class,
                () -> compostionCreationServiceImpl.createOrUpdateCompostion(compostionCreationEntity2));
        verify(compostionCreationRepository).findByCompName((String) any());
    }

    /**
     * Method under test: {@link CompostionCreationServiceImpl#createOrUpdateCompostion(CompostionCreationEntity)}
     */
    @Test
    void testCreateOrUpdateCompostion3() {
        // Arrange
        when(compostionCreationRepository.save((CompostionCreationEntity) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));
        when(compostionCreationRepository.findByCompName((String) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));

        CompostionCreationEntity compostionCreationEntity = new CompostionCreationEntity();
        compostionCreationEntity.setCompName("Comp Name");
        compostionCreationEntity.setId(123L);
        compostionCreationEntity.setStatus("Status");

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> compostionCreationServiceImpl.createOrUpdateCompostion(compostionCreationEntity));
        verify(compostionCreationRepository).findByCompName((String) any());
    }

    /**
     * Method under test: {@link CompostionCreationServiceImpl#createOrUpdateCompostion(List)}
     */
    @Test
    void testCreateOrUpdateCompostion4() {
        // Arrange
        when(compostionCreationRepository.saveAll((Iterable<CompostionCreationEntity>) any()))
                .thenReturn(new ArrayList<>());

        // Act
        compostionCreationServiceImpl.createOrUpdateCompostion(new ArrayList<>());

        // Assert that nothing has changed
        verify(compostionCreationRepository).saveAll((Iterable<CompostionCreationEntity>) any());
        assertTrue(compostionCreationServiceImpl.getAllCompositionList().isEmpty());
    }

    /**
     * Method under test: {@link CompostionCreationServiceImpl#createOrUpdateCompostion(List)}
     */
    @Test
    void testCreateOrUpdateCompostion5() {
        // Arrange
        when(compostionCreationRepository.saveAll((Iterable<CompostionCreationEntity>) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> compostionCreationServiceImpl.createOrUpdateCompostion(new ArrayList<>()));
        verify(compostionCreationRepository).saveAll((Iterable<CompostionCreationEntity>) any());
    }

    /**
     * Method under test: {@link CompostionCreationServiceImpl#getAllCompostionWithPagination(int, int)}
     */
    @Test
    void testGetAllCompostionWithPagination() {
        // Arrange
        PageImpl<CompostionCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(compostionCreationRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<CompostionCreationEntity> actualAllCompostionWithPagination = compostionCreationServiceImpl
                .getAllCompostionWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllCompostionWithPagination);
        assertTrue(actualAllCompostionWithPagination.toList().isEmpty());
        verify(compostionCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link CompostionCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<CompostionCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(compostionCreationRepository.findAllByCompNameContains((String) any(), (Pageable) any()))
                .thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<CompostionCreationEntity> actualSearch = compostionCreationServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(compostionCreationRepository).findAllByCompNameContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link CompostionCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(compostionCreationRepository.findAllByCompNameContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> compostionCreationServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(compostionCreationRepository).findAllByCompNameContains((String) any(), (Pageable) any());
    }

    /**
     * Method under test: {@link CompostionCreationServiceImpl#updateCompostion(CompostionCreationEntity)}
     */
    @Test
    void testUpdateCompostion() {
        // Arrange
        CompostionCreationEntity compostionCreationEntity = new CompostionCreationEntity();
        compostionCreationEntity.setCompName("Comp Name");
        compostionCreationEntity.setId(123L);
        compostionCreationEntity.setStatus("Status");
        when(compostionCreationRepository.save((CompostionCreationEntity) any())).thenReturn(compostionCreationEntity);

        CompostionCreationEntity compostionCreationEntity1 = new CompostionCreationEntity();
        compostionCreationEntity1.setCompName("Comp Name");
        compostionCreationEntity1.setId(123L);
        compostionCreationEntity1.setStatus("Status");

        // Act and Assert
        assertSame(compostionCreationEntity, compostionCreationServiceImpl.updateCompostion(compostionCreationEntity1));
        verify(compostionCreationRepository).save((CompostionCreationEntity) any());
    }

    /**
     * Method under test: {@link CompostionCreationServiceImpl#updateCompostion(CompostionCreationEntity)}
     */
    @Test
    void testUpdateCompostion2() {
        // Arrange
        when(compostionCreationRepository.save((CompostionCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        CompostionCreationEntity compostionCreationEntity = new CompostionCreationEntity();
        compostionCreationEntity.setCompName("Comp Name");
        compostionCreationEntity.setId(123L);
        compostionCreationEntity.setStatus("Status");

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> compostionCreationServiceImpl.updateCompostion(compostionCreationEntity));
        verify(compostionCreationRepository).save((CompostionCreationEntity) any());
    }

    /**
     * Method under test: {@link CompostionCreationServiceImpl#deleteCompostionById(Long)}
     */
    @Test
    void testDeleteCompostionById() throws RecordNotFoundException {
        // Arrange
        CompostionCreationEntity compostionCreationEntity = new CompostionCreationEntity();
        compostionCreationEntity.setCompName("Comp Name");
        compostionCreationEntity.setId(123L);
        compostionCreationEntity.setStatus("Status");
        Optional<CompostionCreationEntity> ofResult = Optional.of(compostionCreationEntity);
        doNothing().when(compostionCreationRepository).deleteById((Long) any());
        when(compostionCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act
        compostionCreationServiceImpl.deleteCompostionById(123L);

        // Assert that nothing has changed
        verify(compostionCreationRepository).findById((Long) any());
        verify(compostionCreationRepository).deleteById((Long) any());
        assertTrue(compostionCreationServiceImpl.getAllCompositionList().isEmpty());
    }

    /**
     * Method under test: {@link CompostionCreationServiceImpl#deleteCompostionById(Long)}
     */
    @Test
    void testDeleteCompostionById2() throws RecordNotFoundException {
        // Arrange
        CompostionCreationEntity compostionCreationEntity = new CompostionCreationEntity();
        compostionCreationEntity.setCompName("Comp Name");
        compostionCreationEntity.setId(123L);
        compostionCreationEntity.setStatus("Status");
        Optional<CompostionCreationEntity> ofResult = Optional.of(compostionCreationEntity);
        doThrow(new UnableToProcessException("An error occurred")).when(compostionCreationRepository)
                .deleteById((Long) any());
        when(compostionCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> compostionCreationServiceImpl.deleteCompostionById(123L));
        verify(compostionCreationRepository).findById((Long) any());
        verify(compostionCreationRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link CompostionCreationServiceImpl#deleteCompostionById(Long)}
     */
    @Test
    void testDeleteCompostionById3() throws RecordNotFoundException {
        // Arrange
        doNothing().when(compostionCreationRepository).deleteById((Long) any());
        when(compostionCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> compostionCreationServiceImpl.deleteCompostionById(123L));
        verify(compostionCreationRepository).findById((Long) any());
    }
}

