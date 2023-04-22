package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.SchedulerCreationRepository;
import com.ets.SecurePharmacy.tenant.model.SchedulerCreationEntity;
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

@ContextConfiguration(classes = {SchedulerCreationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class SchedulerCreationServiceImplTest {
    @MockBean
    private SchedulerCreationRepository schedulerCreationRepository;

    @Autowired
    private SchedulerCreationServiceImpl schedulerCreationServiceImpl;

    /**
     * Method under test: {@link SchedulerCreationServiceImpl#getAllSchedulerList()}
     */
    @Test
    void testGetAllSchedulerList() {
        // Arrange
        when(schedulerCreationRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(schedulerCreationServiceImpl.getAllSchedulerList().isEmpty());
        verify(schedulerCreationRepository).findAll();
    }

    /**
     * Method under test: {@link SchedulerCreationServiceImpl#getAllSchedulerList()}
     */
    @Test
    void testGetAllSchedulerList2() {
        // Arrange
        SchedulerCreationEntity schedulerCreationEntity = new SchedulerCreationEntity();
        schedulerCreationEntity.setId(123L);
        schedulerCreationEntity.setSchedulerName("Scheduler Name");
        schedulerCreationEntity.setStatus("Status");
        schedulerCreationEntity.setWaringMsg("Waring Msg");
        schedulerCreationEntity.setWarning("Warning");

        ArrayList<SchedulerCreationEntity> schedulerCreationEntityList = new ArrayList<>();
        schedulerCreationEntityList.add(schedulerCreationEntity);
        when(schedulerCreationRepository.findAll()).thenReturn(schedulerCreationEntityList);

        // Act
        List<SchedulerCreationEntity> actualAllSchedulerList = schedulerCreationServiceImpl.getAllSchedulerList();

        // Assert
        assertSame(schedulerCreationEntityList, actualAllSchedulerList);
        assertEquals(1, actualAllSchedulerList.size());
        verify(schedulerCreationRepository).findAll();
    }

    /**
     * Method under test: {@link SchedulerCreationServiceImpl#getAllSchedulerList()}
     */
    @Test
    void testGetAllSchedulerList3() {
        // Arrange
        when(schedulerCreationRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> schedulerCreationServiceImpl.getAllSchedulerList());
        verify(schedulerCreationRepository).findAll();
    }

    /**
     * Method under test: {@link SchedulerCreationServiceImpl#getSchedulerById(Long)}
     */
    @Test
    void testGetSchedulerById() throws RecordNotFoundException {
        // Arrange
        SchedulerCreationEntity schedulerCreationEntity = new SchedulerCreationEntity();
        schedulerCreationEntity.setId(123L);
        schedulerCreationEntity.setSchedulerName("Scheduler Name");
        schedulerCreationEntity.setStatus("Status");
        schedulerCreationEntity.setWaringMsg("Waring Msg");
        schedulerCreationEntity.setWarning("Warning");
        Optional<SchedulerCreationEntity> ofResult = Optional.of(schedulerCreationEntity);
        when(schedulerCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(schedulerCreationEntity, schedulerCreationServiceImpl.getSchedulerById(123L));
        verify(schedulerCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SchedulerCreationServiceImpl#getSchedulerById(Long)}
     */
    @Test
    void testGetSchedulerById2() throws RecordNotFoundException {
        // Arrange
        when(schedulerCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> schedulerCreationServiceImpl.getSchedulerById(123L));
        verify(schedulerCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SchedulerCreationServiceImpl#getSchedulerById(Long)}
     */
    @Test
    void testGetSchedulerById3() throws RecordNotFoundException {
        // Arrange
        when(schedulerCreationRepository.findById((Long) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> schedulerCreationServiceImpl.getSchedulerById(123L));
        verify(schedulerCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SchedulerCreationServiceImpl#getSchedulerByName(String)}
     */
    @Test
    void testGetSchedulerByName() {
        // Arrange
        when(schedulerCreationRepository.findBySchedulerName((String) any())).thenReturn(new ArrayList<>());

        // Act and Assert
        assertFalse(schedulerCreationServiceImpl.getSchedulerByName("Sc Name"));
        verify(schedulerCreationRepository).findBySchedulerName((String) any());
    }

    /**
     * Method under test: {@link SchedulerCreationServiceImpl#getSchedulerByName(String)}
     */
    @Test
    void testGetSchedulerByName2() {
        // Arrange
        SchedulerCreationEntity schedulerCreationEntity = new SchedulerCreationEntity();
        schedulerCreationEntity.setId(123L);
        schedulerCreationEntity.setSchedulerName("Scheduler Name");
        schedulerCreationEntity.setStatus("Status");
        schedulerCreationEntity.setWaringMsg("Waring Msg");
        schedulerCreationEntity.setWarning("Warning");

        ArrayList<SchedulerCreationEntity> schedulerCreationEntityList = new ArrayList<>();
        schedulerCreationEntityList.add(schedulerCreationEntity);
        when(schedulerCreationRepository.findBySchedulerName((String) any())).thenReturn(schedulerCreationEntityList);

        // Act and Assert
        assertTrue(schedulerCreationServiceImpl.getSchedulerByName("Sc Name"));
        verify(schedulerCreationRepository).findBySchedulerName((String) any());
    }

    /**
     * Method under test: {@link SchedulerCreationServiceImpl#getSchedulerByName(String)}
     */
    @Test
    void testGetSchedulerByName3() {
        // Arrange
        when(schedulerCreationRepository.findBySchedulerName((String) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> schedulerCreationServiceImpl.getSchedulerByName("Sc Name"));
        verify(schedulerCreationRepository).findBySchedulerName((String) any());
    }

    /**
     * Method under test: {@link SchedulerCreationServiceImpl#createOrUpdateScheduler(SchedulerCreationEntity)}
     */
    @Test
    void testCreateOrUpdateScheduler() {
        // Arrange
        SchedulerCreationEntity schedulerCreationEntity = new SchedulerCreationEntity();
        schedulerCreationEntity.setId(123L);
        schedulerCreationEntity.setSchedulerName("Scheduler Name");
        schedulerCreationEntity.setStatus("Status");
        schedulerCreationEntity.setWaringMsg("Waring Msg");
        schedulerCreationEntity.setWarning("Warning");
        when(schedulerCreationRepository.save((SchedulerCreationEntity) any())).thenReturn(schedulerCreationEntity);
        when(schedulerCreationRepository.findBySchedulerName((String) any())).thenReturn(new ArrayList<>());

        SchedulerCreationEntity schedulerCreationEntity1 = new SchedulerCreationEntity();
        schedulerCreationEntity1.setId(123L);
        schedulerCreationEntity1.setSchedulerName("Scheduler Name");
        schedulerCreationEntity1.setStatus("Status");
        schedulerCreationEntity1.setWaringMsg("Waring Msg");
        schedulerCreationEntity1.setWarning("Warning");

        // Act and Assert
        assertSame(schedulerCreationEntity, schedulerCreationServiceImpl.createOrUpdateScheduler(schedulerCreationEntity1));
        verify(schedulerCreationRepository).save((SchedulerCreationEntity) any());
        verify(schedulerCreationRepository).findBySchedulerName((String) any());
    }

    /**
     * Method under test: {@link SchedulerCreationServiceImpl#createOrUpdateScheduler(SchedulerCreationEntity)}
     */
    @Test
    void testCreateOrUpdateScheduler2() {
        // Arrange
        SchedulerCreationEntity schedulerCreationEntity = new SchedulerCreationEntity();
        schedulerCreationEntity.setId(123L);
        schedulerCreationEntity.setSchedulerName("Scheduler Name");
        schedulerCreationEntity.setStatus("Status");
        schedulerCreationEntity.setWaringMsg("Waring Msg");
        schedulerCreationEntity.setWarning("Warning");

        SchedulerCreationEntity schedulerCreationEntity1 = new SchedulerCreationEntity();
        schedulerCreationEntity1.setId(123L);
        schedulerCreationEntity1.setSchedulerName("Scheduler Name");
        schedulerCreationEntity1.setStatus("Status");
        schedulerCreationEntity1.setWaringMsg("Waring Msg");
        schedulerCreationEntity1.setWarning("Warning");

        ArrayList<SchedulerCreationEntity> schedulerCreationEntityList = new ArrayList<>();
        schedulerCreationEntityList.add(schedulerCreationEntity1);
        when(schedulerCreationRepository.save((SchedulerCreationEntity) any())).thenReturn(schedulerCreationEntity);
        when(schedulerCreationRepository.findBySchedulerName((String) any())).thenReturn(schedulerCreationEntityList);

        SchedulerCreationEntity schedulerCreationEntity2 = new SchedulerCreationEntity();
        schedulerCreationEntity2.setId(123L);
        schedulerCreationEntity2.setSchedulerName("Scheduler Name");
        schedulerCreationEntity2.setStatus("Status");
        schedulerCreationEntity2.setWaringMsg("Waring Msg");
        schedulerCreationEntity2.setWarning("Warning");

        // Act and Assert
        assertThrows(RecordAlreadyPresentException.class,
                () -> schedulerCreationServiceImpl.createOrUpdateScheduler(schedulerCreationEntity2));
        verify(schedulerCreationRepository).findBySchedulerName((String) any());
    }

    /**
     * Method under test: {@link SchedulerCreationServiceImpl#createOrUpdateScheduler(SchedulerCreationEntity)}
     */
    @Test
    void testCreateOrUpdateScheduler3() {
        // Arrange
        when(schedulerCreationRepository.save((SchedulerCreationEntity) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));
        when(schedulerCreationRepository.findBySchedulerName((String) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));

        SchedulerCreationEntity schedulerCreationEntity = new SchedulerCreationEntity();
        schedulerCreationEntity.setId(123L);
        schedulerCreationEntity.setSchedulerName("Scheduler Name");
        schedulerCreationEntity.setStatus("Status");
        schedulerCreationEntity.setWaringMsg("Waring Msg");
        schedulerCreationEntity.setWarning("Warning");

        // Act and Assert
        assertThrows(RecordAlreadyPresentException.class,
                () -> schedulerCreationServiceImpl.createOrUpdateScheduler(schedulerCreationEntity));
        verify(schedulerCreationRepository).findBySchedulerName((String) any());
    }

    /**
     * Method under test: {@link SchedulerCreationServiceImpl#deleteSchedulerById(Long)}
     */
    @Test
    void testDeleteSchedulerById() throws RecordNotFoundException {
        // Arrange
        SchedulerCreationEntity schedulerCreationEntity = new SchedulerCreationEntity();
        schedulerCreationEntity.setId(123L);
        schedulerCreationEntity.setSchedulerName("Scheduler Name");
        schedulerCreationEntity.setStatus("Status");
        schedulerCreationEntity.setWaringMsg("Waring Msg");
        schedulerCreationEntity.setWarning("Warning");
        Optional<SchedulerCreationEntity> ofResult = Optional.of(schedulerCreationEntity);
        doNothing().when(schedulerCreationRepository).deleteById((Long) any());
        when(schedulerCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act
        schedulerCreationServiceImpl.deleteSchedulerById(123L);

        // Assert that nothing has changed
        verify(schedulerCreationRepository).findById((Long) any());
        verify(schedulerCreationRepository).deleteById((Long) any());
        assertTrue(schedulerCreationServiceImpl.getAllSchedulerList().isEmpty());
    }

    /**
     * Method under test: {@link SchedulerCreationServiceImpl#deleteSchedulerById(Long)}
     */
    @Test
    void testDeleteSchedulerById2() throws RecordNotFoundException {
        // Arrange
        SchedulerCreationEntity schedulerCreationEntity = new SchedulerCreationEntity();
        schedulerCreationEntity.setId(123L);
        schedulerCreationEntity.setSchedulerName("Scheduler Name");
        schedulerCreationEntity.setStatus("Status");
        schedulerCreationEntity.setWaringMsg("Waring Msg");
        schedulerCreationEntity.setWarning("Warning");
        Optional<SchedulerCreationEntity> ofResult = Optional.of(schedulerCreationEntity);
        doThrow(new UnableToProcessException("An error occurred")).when(schedulerCreationRepository)
                .deleteById((Long) any());
        when(schedulerCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> schedulerCreationServiceImpl.deleteSchedulerById(123L));
        verify(schedulerCreationRepository).findById((Long) any());
        verify(schedulerCreationRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link SchedulerCreationServiceImpl#deleteSchedulerById(Long)}
     */
    @Test
    void testDeleteSchedulerById3() throws RecordNotFoundException {
        // Arrange
        doNothing().when(schedulerCreationRepository).deleteById((Long) any());
        when(schedulerCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> schedulerCreationServiceImpl.deleteSchedulerById(123L));
        verify(schedulerCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SchedulerCreationServiceImpl#getAllSchedulerWithPagination(int, int)}
     */
    @Test
    void testGetAllSchedulerWithPagination() {
        // Arrange
        PageImpl<SchedulerCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(schedulerCreationRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<SchedulerCreationEntity> actualAllSchedulerWithPagination = schedulerCreationServiceImpl
                .getAllSchedulerWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllSchedulerWithPagination);
        assertTrue(actualAllSchedulerWithPagination.toList().isEmpty());
        verify(schedulerCreationRepository).findAll((Pageable) any());
    }


    /**
     * Method under test: {@link SchedulerCreationServiceImpl#getAllSchedulerWithPagination(int, int)}
     */
    @Test
    void testGetAllSchedulerWithPagination3() {
        // Arrange
        when(schedulerCreationRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> schedulerCreationServiceImpl.getAllSchedulerWithPagination(2, 3));
        verify(schedulerCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link SchedulerCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<SchedulerCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(schedulerCreationRepository.findAllBySchedulerNameContains((String) any(), (Pageable) any()))
                .thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<SchedulerCreationEntity> actualSearch = schedulerCreationServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(schedulerCreationRepository).findAllBySchedulerNameContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link SchedulerCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(schedulerCreationRepository.findAllBySchedulerNameContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> schedulerCreationServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(schedulerCreationRepository).findAllBySchedulerNameContains((String) any(), (Pageable) any());
    }

    /**
     * Method under test: {@link SchedulerCreationServiceImpl#updateScheduler(SchedulerCreationEntity)}
     */
    @Test
    void testUpdateScheduler() {
        // Arrange
        SchedulerCreationEntity schedulerCreationEntity = new SchedulerCreationEntity();
        schedulerCreationEntity.setId(123L);
        schedulerCreationEntity.setSchedulerName("Scheduler Name");
        schedulerCreationEntity.setStatus("Status");
        schedulerCreationEntity.setWaringMsg("Waring Msg");
        schedulerCreationEntity.setWarning("Warning");

        // Act and Assert
        assertNull(schedulerCreationServiceImpl.updateScheduler(schedulerCreationEntity));
    }

    /**
     * Method under test: {@link SchedulerCreationServiceImpl#updateScheduler(SchedulerCreationEntity)}
     */
    @Test
    void testUpdateScheduler2() {
        // Arrange
        SchedulerCreationEntity schedulerCreationEntity = mock(SchedulerCreationEntity.class);
        doNothing().when(schedulerCreationEntity).setId((Long) any());
        doNothing().when(schedulerCreationEntity).setSchedulerName((String) any());
        doNothing().when(schedulerCreationEntity).setStatus((String) any());
        doNothing().when(schedulerCreationEntity).setWaringMsg((String) any());
        doNothing().when(schedulerCreationEntity).setWarning((String) any());
        schedulerCreationEntity.setId(123L);
        schedulerCreationEntity.setSchedulerName("Scheduler Name");
        schedulerCreationEntity.setStatus("Status");
        schedulerCreationEntity.setWaringMsg("Waring Msg");
        schedulerCreationEntity.setWarning("Warning");

        // Act and Assert
        assertNull(schedulerCreationServiceImpl.updateScheduler(schedulerCreationEntity));
        verify(schedulerCreationEntity).setId((Long) any());
        verify(schedulerCreationEntity).setSchedulerName((String) any());
        verify(schedulerCreationEntity).setStatus((String) any());
        verify(schedulerCreationEntity).setWaringMsg((String) any());
        verify(schedulerCreationEntity).setWarning((String) any());
    }
}

