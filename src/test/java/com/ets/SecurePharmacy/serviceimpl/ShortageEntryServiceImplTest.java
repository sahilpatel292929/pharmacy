package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.ShortageEntryRepository;
import com.ets.SecurePharmacy.tenant.model.ShortageEntryEntity;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ShortageEntryServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ShortageEntryServiceImplTest {
    @MockBean
    private ShortageEntryRepository shortageEntryRepository;

    @Autowired
    private ShortageEntryServiceImpl shortageEntryServiceImpl;

    /**
     * Method under test: {@link ShortageEntryServiceImpl#getAllShortage()}
     */
    @Test
    void testGetAllShortage() {
        // Arrange
        when(shortageEntryRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(shortageEntryServiceImpl.getAllShortage().isEmpty());
        verify(shortageEntryRepository).findAll();
    }

    /**
     * Method under test: {@link ShortageEntryServiceImpl#getAllShortage()}
     */
    @Test
    void testGetAllShortage2() {
        // Arrange
        ShortageEntryEntity shortageEntryEntity = new ShortageEntryEntity();
        shortageEntryEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        shortageEntryEntity.setId(123L);
        shortageEntryEntity.setShortageDetails(new ArrayList<>());
        shortageEntryEntity.setStatus("Status");
        shortageEntryEntity.setTotalItems(10.0d);
        shortageEntryEntity.setTotalQty(10.0d);

        ArrayList<ShortageEntryEntity> shortageEntryEntityList = new ArrayList<>();
        shortageEntryEntityList.add(shortageEntryEntity);
        when(shortageEntryRepository.findAll()).thenReturn(shortageEntryEntityList);

        // Act
        List<ShortageEntryEntity> actualAllShortage = shortageEntryServiceImpl.getAllShortage();

        // Assert
        assertSame(shortageEntryEntityList, actualAllShortage);
        assertEquals(1, actualAllShortage.size());
        verify(shortageEntryRepository).findAll();
    }

    /**
     * Method under test: {@link ShortageEntryServiceImpl#getAllShortage()}
     */
    @Test
    void testGetAllShortage3() {
        // Arrange
        when(shortageEntryRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> shortageEntryServiceImpl.getAllShortage());
        verify(shortageEntryRepository).findAll();
    }

    /**
     * Method under test: {@link ShortageEntryServiceImpl#getShortageById(Long)}
     */
    @Test
    void testGetShortageById() throws RecordNotFoundException {
        // Arrange
        ShortageEntryEntity shortageEntryEntity = new ShortageEntryEntity();
        shortageEntryEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        shortageEntryEntity.setId(123L);
        shortageEntryEntity.setShortageDetails(new ArrayList<>());
        shortageEntryEntity.setStatus("Status");
        shortageEntryEntity.setTotalItems(10.0d);
        shortageEntryEntity.setTotalQty(10.0d);
        Optional<ShortageEntryEntity> ofResult = Optional.of(shortageEntryEntity);
        when(shortageEntryRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(shortageEntryEntity, shortageEntryServiceImpl.getShortageById(123L));
        verify(shortageEntryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ShortageEntryServiceImpl#getShortageById(Long)}
     */
    @Test
    void testGetShortageById2() throws RecordNotFoundException {
        // Arrange
        when(shortageEntryRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> shortageEntryServiceImpl.getShortageById(123L));
        verify(shortageEntryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ShortageEntryServiceImpl#getShortageById(Long)}
     */
    @Test
    void testGetShortageById3() throws RecordNotFoundException {
        // Arrange
        when(shortageEntryRepository.findById((Long) any())).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> shortageEntryServiceImpl.getShortageById(123L));
        verify(shortageEntryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ShortageEntryServiceImpl#createOrUpdateShortage(ShortageEntryEntity)}
     */
    @Test
    void testCreateOrUpdateShortage() {
        // Arrange
        ShortageEntryEntity shortageEntryEntity = new ShortageEntryEntity();
        shortageEntryEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        shortageEntryEntity.setId(123L);
        shortageEntryEntity.setShortageDetails(new ArrayList<>());
        shortageEntryEntity.setStatus("Status");
        shortageEntryEntity.setTotalItems(10.0d);
        shortageEntryEntity.setTotalQty(10.0d);
        when(shortageEntryRepository.save((ShortageEntryEntity) any())).thenReturn(shortageEntryEntity);

        ShortageEntryEntity shortageEntryEntity1 = new ShortageEntryEntity();
        shortageEntryEntity1.setEntryDate(LocalDate.ofEpochDay(1L));
        shortageEntryEntity1.setId(123L);
        shortageEntryEntity1.setShortageDetails(new ArrayList<>());
        shortageEntryEntity1.setStatus("Status");
        shortageEntryEntity1.setTotalItems(10.0d);
        shortageEntryEntity1.setTotalQty(10.0d);

        // Act and Assert
        assertSame(shortageEntryEntity, shortageEntryServiceImpl.createOrUpdateShortage(shortageEntryEntity1));
        verify(shortageEntryRepository).save((ShortageEntryEntity) any());
    }

    /**
     * Method under test: {@link ShortageEntryServiceImpl#createOrUpdateShortage(ShortageEntryEntity)}
     */
    @Test
    void testCreateOrUpdateShortage2() {
        // Arrange
        when(shortageEntryRepository.save((ShortageEntryEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        ShortageEntryEntity shortageEntryEntity = new ShortageEntryEntity();
        shortageEntryEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        shortageEntryEntity.setId(123L);
        shortageEntryEntity.setShortageDetails(new ArrayList<>());
        shortageEntryEntity.setStatus("Status");
        shortageEntryEntity.setTotalItems(10.0d);
        shortageEntryEntity.setTotalQty(10.0d);

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> shortageEntryServiceImpl.createOrUpdateShortage(shortageEntryEntity));
        verify(shortageEntryRepository).save((ShortageEntryEntity) any());
    }

    /**
     * Method under test: {@link ShortageEntryServiceImpl#getAllShortageEntryWithPagination(int, int)}
     */
    @Test
    void testGetAllShortageEntryWithPagination() {
        // Arrange
        PageImpl<ShortageEntryEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(shortageEntryRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<ShortageEntryEntity> actualAllShortageEntryWithPagination = shortageEntryServiceImpl
                .getAllShortageEntryWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllShortageEntryWithPagination);
        assertTrue(actualAllShortageEntryWithPagination.toList().isEmpty());
        verify(shortageEntryRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link ShortageEntryServiceImpl#getAllShortageEntryWithPagination(int, int)}
     */
    @Test
    void testGetAllShortageEntryWithPagination2() {
        // Arrange
        when(shortageEntryRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> shortageEntryServiceImpl.getAllShortageEntryWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link ShortageEntryServiceImpl#getAllShortageEntryWithPagination(int, int)}
     */
    @Test
    void testGetAllShortageEntryWithPagination3() {
        // Arrange
        when(shortageEntryRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> shortageEntryServiceImpl.getAllShortageEntryWithPagination(2, 3));
        verify(shortageEntryRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link ShortageEntryServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<ShortageEntryEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(shortageEntryRepository.findAllByEntryDateContains((String) any(), (Pageable) any())).thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<ShortageEntryEntity> actualSearch = shortageEntryServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(shortageEntryRepository).findAllByEntryDateContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link ShortageEntryServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(shortageEntryRepository.findAllByEntryDateContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> shortageEntryServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(shortageEntryRepository).findAllByEntryDateContains((String) any(), (Pageable) any());
    }
}

