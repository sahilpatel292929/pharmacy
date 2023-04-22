package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.GSTPercentageRepository;
import com.ets.SecurePharmacy.tenant.model.GSTPercentageEntity;
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
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {GSTPercetageServiceImpl.class})
@ExtendWith(SpringExtension.class)
class GSTPercetageServiceImplTest {
    @MockBean
    private GSTPercentageRepository gSTPercentageRepository;

    @Autowired
    private GSTPercetageServiceImpl gSTPercetageServiceImpl;

    /**
     * Method under test: {@link GSTPercetageServiceImpl#getAllGSTPercentage()}
     */
    @Test
    void testGetAllGSTPercentage() {
        // Arrange
        when(gSTPercentageRepository.findAll()).thenReturn((Iterable<GSTPercentageEntity>) mock(Iterable.class));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> gSTPercetageServiceImpl.getAllGSTPercentage());
        verify(gSTPercentageRepository).findAll();
    }

    /**
     * Method under test: {@link GSTPercetageServiceImpl#getAllGSTPercentage()}
     */
    @Test
    void testGetAllGSTPercentage2() {
        // Arrange
        when(gSTPercentageRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> gSTPercetageServiceImpl.getAllGSTPercentage());
        verify(gSTPercentageRepository).findAll();
    }

    /**
     * Method under test: {@link GSTPercetageServiceImpl#getGSTPercentageById(Long)}
     */
    @Test
    void testGetGSTPercentageById() throws RecordNotFoundException {
        // Arrange
        GSTPercentageEntity gstPercentageEntity = new GSTPercentageEntity();
        gstPercentageEntity.setId(123L);
        gstPercentageEntity.setName("Name");
        gstPercentageEntity.setStatus("Status");
        gstPercentageEntity.setValue("42");
        Optional<GSTPercentageEntity> ofResult = Optional.of(gstPercentageEntity);
        when(gSTPercentageRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(gstPercentageEntity, gSTPercetageServiceImpl.getGSTPercentageById(123L));
        verify(gSTPercentageRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link GSTPercetageServiceImpl#getGSTPercentageById(Long)}
     */
    @Test
    void testGetGSTPercentageById2() throws RecordNotFoundException {
        // Arrange
        when(gSTPercentageRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> gSTPercetageServiceImpl.getGSTPercentageById(123L));
        verify(gSTPercentageRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link GSTPercetageServiceImpl#getGSTPercentageById(Long)}
     */
    @Test
    void testGetGSTPercentageById3() throws RecordNotFoundException {
        // Arrange
        when(gSTPercentageRepository.findById((Long) any())).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> gSTPercetageServiceImpl.getGSTPercentageById(123L));
        verify(gSTPercentageRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link GSTPercetageServiceImpl#createOrUpdateSGSTPercentage(GSTPercentageEntity)}
     */
    @Test
    void testCreateOrUpdateSGSTPercentage() {
        // Arrange
        GSTPercentageEntity gstPercentageEntity = new GSTPercentageEntity();
        gstPercentageEntity.setId(123L);
        gstPercentageEntity.setName("Name");
        gstPercentageEntity.setStatus("Status");
        gstPercentageEntity.setValue("42");
        when(gSTPercentageRepository.save((GSTPercentageEntity) any())).thenReturn(gstPercentageEntity);

        GSTPercentageEntity gstPercentageEntity1 = new GSTPercentageEntity();
        gstPercentageEntity1.setId(123L);
        gstPercentageEntity1.setName("Name");
        gstPercentageEntity1.setStatus("Status");
        gstPercentageEntity1.setValue("42");

        // Act and Assert
        assertSame(gstPercentageEntity, gSTPercetageServiceImpl.createOrUpdateSGSTPercentage(gstPercentageEntity1));
        verify(gSTPercentageRepository).save((GSTPercentageEntity) any());
    }

    /**
     * Method under test: {@link GSTPercetageServiceImpl#createOrUpdateSGSTPercentage(GSTPercentageEntity)}
     */
    @Test
    void testCreateOrUpdateSGSTPercentage2() {
        // Arrange
        when(gSTPercentageRepository.save((GSTPercentageEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        GSTPercentageEntity gstPercentageEntity = new GSTPercentageEntity();
        gstPercentageEntity.setId(123L);
        gstPercentageEntity.setName("Name");
        gstPercentageEntity.setStatus("Status");
        gstPercentageEntity.setValue("42");

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> gSTPercetageServiceImpl.createOrUpdateSGSTPercentage(gstPercentageEntity));
        verify(gSTPercentageRepository).save((GSTPercentageEntity) any());
    }

    /**
     * Method under test: {@link GSTPercetageServiceImpl#createOrUpdateAccountsBulk(List)}
     */
    @Test
    void testCreateOrUpdateAccountsBulk() {
        // Arrange
        when(gSTPercentageRepository.saveAll((Iterable<GSTPercentageEntity>) any()))
                .thenReturn((Iterable<GSTPercentageEntity>) mock(Iterable.class));
        ArrayList<GSTPercentageEntity> gstPercentageEntityList = new ArrayList<>();

        // Act
        gSTPercetageServiceImpl.createOrUpdateAccountsBulk(gstPercentageEntityList);

        // Assert that nothing has changed
        verify(gSTPercentageRepository).saveAll((Iterable<GSTPercentageEntity>) any());
        assertEquals(gstPercentageEntityList, gSTPercetageServiceImpl.getAllGSTPercentage());
    }

    /**
     * Method under test: {@link GSTPercetageServiceImpl#createOrUpdateAccountsBulk(List)}
     */
    @Test
    void testCreateOrUpdateAccountsBulk2() {
        // Arrange
        when(gSTPercentageRepository.saveAll((Iterable<GSTPercentageEntity>) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> gSTPercetageServiceImpl.createOrUpdateAccountsBulk(new ArrayList<>()));
        verify(gSTPercentageRepository).saveAll((Iterable<GSTPercentageEntity>) any());
    }

    /**
     * Method under test: {@link GSTPercetageServiceImpl#deleteGSTPercentageById(Long)}
     */
    @Test
    void testDeleteGSTPercentageById() throws RecordNotFoundException {
        // Arrange
        GSTPercentageEntity gstPercentageEntity = new GSTPercentageEntity();
        gstPercentageEntity.setId(123L);
        gstPercentageEntity.setName("Name");
        gstPercentageEntity.setStatus("Status");
        gstPercentageEntity.setValue("42");
        Optional<GSTPercentageEntity> ofResult = Optional.of(gstPercentageEntity);
        doNothing().when(gSTPercentageRepository).deleteById((Long) any());
        when(gSTPercentageRepository.findById((Long) any())).thenReturn(ofResult);

        // Act
        gSTPercetageServiceImpl.deleteGSTPercentageById(123L);

        // Assert that nothing has changed
        verify(gSTPercentageRepository).findById((Long) any());
        verify(gSTPercentageRepository).deleteById((Long) any());
        assertTrue(gSTPercetageServiceImpl.getAllGSTPercentage().isEmpty());
    }

    /**
     * Method under test: {@link GSTPercetageServiceImpl#deleteGSTPercentageById(Long)}
     */
    @Test
    void testDeleteGSTPercentageById2() throws RecordNotFoundException {
        // Arrange
        GSTPercentageEntity gstPercentageEntity = new GSTPercentageEntity();
        gstPercentageEntity.setId(123L);
        gstPercentageEntity.setName("Name");
        gstPercentageEntity.setStatus("Status");
        gstPercentageEntity.setValue("42");
        Optional<GSTPercentageEntity> ofResult = Optional.of(gstPercentageEntity);
        doThrow(new UnableToProcessException("An error occurred")).when(gSTPercentageRepository).deleteById((Long) any());
        when(gSTPercentageRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> gSTPercetageServiceImpl.deleteGSTPercentageById(123L));
        verify(gSTPercentageRepository).findById((Long) any());
        verify(gSTPercentageRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link GSTPercetageServiceImpl#deleteGSTPercentageById(Long)}
     */
    @Test
    void testDeleteGSTPercentageById3() throws RecordNotFoundException {
        // Arrange
        doNothing().when(gSTPercentageRepository).deleteById((Long) any());
        when(gSTPercentageRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> gSTPercetageServiceImpl.deleteGSTPercentageById(123L));
        verify(gSTPercentageRepository).findById((Long) any());
    }
}

