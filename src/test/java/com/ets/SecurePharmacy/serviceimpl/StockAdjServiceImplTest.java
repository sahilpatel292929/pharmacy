package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.ItemStockRepository;
import com.ets.SecurePharmacy.tenant.dao.StockAdjRepository;
import com.ets.SecurePharmacy.tenant.model.StockAdjEntity;
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

@ContextConfiguration(classes = {StockAdjServiceImpl.class})
@ExtendWith(SpringExtension.class)
class StockAdjServiceImplTest {
    @MockBean
    private ItemStockRepository itemStockRepository;

    @MockBean
    private StockAdjRepository stockAdjRepository;

    @Autowired
    private StockAdjServiceImpl stockAdjServiceImpl;

    /**
     * Method under test: {@link StockAdjServiceImpl#getAllStockAgjDetails()}
     */
    @Test
    void testGetAllStockAgjDetails() {
        // Arrange
        when(stockAdjRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(stockAdjServiceImpl.getAllStockAgjDetails().isEmpty());
        verify(stockAdjRepository).findAll();
    }

    /**
     * Method under test: {@link StockAdjServiceImpl#getAllStockAgjDetails()}
     */
    @Test
    void testGetAllStockAgjDetails2() {
        // Arrange
        StockAdjEntity stockAdjEntity = new StockAdjEntity();
        stockAdjEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        stockAdjEntity.setId(123L);
        stockAdjEntity.setStatus("Status");
        stockAdjEntity.setStockAdjDetails(new ArrayList<>());
        stockAdjEntity.setTotalAdj("Total Adj");

        ArrayList<StockAdjEntity> stockAdjEntityList = new ArrayList<>();
        stockAdjEntityList.add(stockAdjEntity);
        when(stockAdjRepository.findAll()).thenReturn(stockAdjEntityList);

        // Act
        List<StockAdjEntity> actualAllStockAgjDetails = stockAdjServiceImpl.getAllStockAgjDetails();

        // Assert
        assertSame(stockAdjEntityList, actualAllStockAgjDetails);
        assertEquals(1, actualAllStockAgjDetails.size());
        verify(stockAdjRepository).findAll();
    }

    /**
     * Method under test: {@link StockAdjServiceImpl#getAllStockAgjDetails()}
     */
    @Test
    void testGetAllStockAgjDetails3() {
        // Arrange
        when(stockAdjRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> stockAdjServiceImpl.getAllStockAgjDetails());
        verify(stockAdjRepository).findAll();
    }

    /**
     * Method under test: {@link StockAdjServiceImpl#getStockAdjById(Long)}
     */
    @Test
    void testGetStockAdjById() throws RecordNotFoundException {
        // Arrange
        StockAdjEntity stockAdjEntity = new StockAdjEntity();
        stockAdjEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        stockAdjEntity.setId(123L);
        stockAdjEntity.setStatus("Status");
        stockAdjEntity.setStockAdjDetails(new ArrayList<>());
        stockAdjEntity.setTotalAdj("Total Adj");
        Optional<StockAdjEntity> ofResult = Optional.of(stockAdjEntity);
        when(stockAdjRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(stockAdjEntity, stockAdjServiceImpl.getStockAdjById(123L));
        verify(stockAdjRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link StockAdjServiceImpl#getStockAdjById(Long)}
     */
    @Test
    void testGetStockAdjById2() throws RecordNotFoundException {
        // Arrange
        when(stockAdjRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> stockAdjServiceImpl.getStockAdjById(123L));
        verify(stockAdjRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link StockAdjServiceImpl#getStockAdjById(Long)}
     */
    @Test
    void testGetStockAdjById3() throws RecordNotFoundException {
        // Arrange
        when(stockAdjRepository.findById((Long) any())).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> stockAdjServiceImpl.getStockAdjById(123L));
        verify(stockAdjRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link StockAdjServiceImpl#createOrUpdateStockAdj(StockAdjEntity)}
     */
    @Test
    void testCreateOrUpdateStockAdj() {
        // Arrange
        StockAdjEntity stockAdjEntity = new StockAdjEntity();
        stockAdjEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        stockAdjEntity.setId(123L);
        stockAdjEntity.setStatus("Status");
        stockAdjEntity.setStockAdjDetails(new ArrayList<>());
        stockAdjEntity.setTotalAdj("Total Adj");
        when(stockAdjRepository.save((StockAdjEntity) any())).thenReturn(stockAdjEntity);

        StockAdjEntity stockAdjEntity1 = new StockAdjEntity();
        stockAdjEntity1.setEntryDate(LocalDate.ofEpochDay(1L));
        stockAdjEntity1.setId(123L);
        stockAdjEntity1.setStatus("Status");
        stockAdjEntity1.setStockAdjDetails(new ArrayList<>());
        stockAdjEntity1.setTotalAdj("Total Adj");

        // Act and Assert
        assertSame(stockAdjEntity, stockAdjServiceImpl.createOrUpdateStockAdj(stockAdjEntity1));
        verify(stockAdjRepository).save((StockAdjEntity) any());
    }

    /**
     * Method under test: {@link StockAdjServiceImpl#createOrUpdateStockAdj(StockAdjEntity)}
     */
    @Test
    void testCreateOrUpdateStockAdj2() {
        // Arrange
        when(stockAdjRepository.save((StockAdjEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        StockAdjEntity stockAdjEntity = new StockAdjEntity();
        stockAdjEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        stockAdjEntity.setId(123L);
        stockAdjEntity.setStatus("Status");
        stockAdjEntity.setStockAdjDetails(new ArrayList<>());
        stockAdjEntity.setTotalAdj("Total Adj");

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> stockAdjServiceImpl.createOrUpdateStockAdj(stockAdjEntity));
        verify(stockAdjRepository).save((StockAdjEntity) any());
    }

    /**
     * Method under test: {@link StockAdjServiceImpl#getAllStockAdjEntityWithPagination(int, int)}
     */
    @Test
    void testGetAllStockAdjEntityWithPagination() {
        // Arrange
        PageImpl<StockAdjEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(stockAdjRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<StockAdjEntity> actualAllStockAdjEntityWithPagination = stockAdjServiceImpl
                .getAllStockAdjEntityWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllStockAdjEntityWithPagination);
        assertTrue(actualAllStockAdjEntityWithPagination.toList().isEmpty());
        verify(stockAdjRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link StockAdjServiceImpl#getAllStockAdjEntityWithPagination(int, int)}
     */
    @Test
    void testGetAllStockAdjEntityWithPagination2() {
        // Arrange
        when(stockAdjRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> stockAdjServiceImpl.getAllStockAdjEntityWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link StockAdjServiceImpl#getAllStockAdjEntityWithPagination(int, int)}
     */
    @Test
    void testGetAllStockAdjEntityWithPagination3() {
        // Arrange
        when(stockAdjRepository.findAll((Pageable) any())).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> stockAdjServiceImpl.getAllStockAdjEntityWithPagination(2, 3));
        verify(stockAdjRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link StockAdjServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<StockAdjEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(stockAdjRepository.findAllByEntryDateContains((String) any(), (Pageable) any())).thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<StockAdjEntity> actualSearch = stockAdjServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(stockAdjRepository).findAllByEntryDateContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link StockAdjServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(stockAdjRepository.findAllByEntryDateContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> stockAdjServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(stockAdjRepository).findAllByEntryDateContains((String) any(), (Pageable) any());
    }
}

