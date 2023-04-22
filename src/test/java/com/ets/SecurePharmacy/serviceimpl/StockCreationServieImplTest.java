package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.StockCreationRepository;
import com.ets.SecurePharmacy.tenant.model.BranchCreationEntity;
import com.ets.SecurePharmacy.tenant.model.StockCreationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {StockCreationServieImpl.class})
@ExtendWith(SpringExtension.class)
class StockCreationServieImplTest {
    @MockBean
    private StockCreationRepository stockCreationRepository;

    @Autowired
    private StockCreationServieImpl stockCreationServieImpl;

    /**
     * Method under test: {@link StockCreationServieImpl#getAllStock()}
     */
    @Test
    void testGetAllStock() {
        // Arrange
        when(stockCreationRepository.findAll()).thenReturn((Iterable<StockCreationEntity>) mock(Iterable.class));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> stockCreationServieImpl.getAllStock());
        verify(stockCreationRepository).findAll();
    }

    /**
     * Method under test: {@link StockCreationServieImpl#getAllStock()}
     */
    @Test
    void testGetAllStock2() {
        // Arrange
        when(stockCreationRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> stockCreationServieImpl.getAllStock());
        verify(stockCreationRepository).findAll();
    }

    /**
     * Method under test: {@link StockCreationServieImpl#getStockById(Long)}
     */
    @Test
    void testGetStockById() throws RecordNotFoundException {
        // Arrange
        BranchCreationEntity branchCreationEntity = new BranchCreationEntity();
        branchCreationEntity.setAddreess("42 Main St");
        branchCreationEntity.setBranchName("janedoe/featurebranch");
        branchCreationEntity.setContact_name("Contact name");
        branchCreationEntity.setDlNumber("42");
        branchCreationEntity.setGstNumber("42");
        branchCreationEntity.setId(123L);
        branchCreationEntity.setLocation("Location");
        branchCreationEntity.setMobileNo("Mobile No");
        branchCreationEntity.setPincode(1);
        branchCreationEntity.setStatus("Status");

        StockCreationEntity stockCreationEntity = new StockCreationEntity();
        stockCreationEntity.setBarnchCreation(branchCreationEntity);
        stockCreationEntity.setId(123L);
        stockCreationEntity.setStatus("Status");
        stockCreationEntity.setStockName("Stock Name");
        Optional<StockCreationEntity> ofResult = Optional.of(stockCreationEntity);
        when(stockCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(stockCreationEntity, stockCreationServieImpl.getStockById(123L));
        verify(stockCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link StockCreationServieImpl#getStockById(Long)}
     */
    @Test
    void testGetStockById2() throws RecordNotFoundException {
        // Arrange
        when(stockCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> stockCreationServieImpl.getStockById(123L));
        verify(stockCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link StockCreationServieImpl#getStockById(Long)}
     */
    @Test
    void testGetStockById3() throws RecordNotFoundException {
        // Arrange
        when(stockCreationRepository.findById((Long) any())).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> stockCreationServieImpl.getStockById(123L));
        verify(stockCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link StockCreationServieImpl#getStockName(String)}
     */
    @Test
    void testGetStockName() {
        // Arrange
        when(stockCreationRepository.findByStockName((String) any())).thenReturn(new ArrayList<>());

        // Act and Assert
        assertFalse(stockCreationServieImpl.getStockName("Bname"));
        verify(stockCreationRepository).findByStockName((String) any());
    }

    /**
     * Method under test: {@link StockCreationServieImpl#getStockName(String)}
     */
    @Test
    void testGetStockName2() {
        // Arrange
        BranchCreationEntity branchCreationEntity = new BranchCreationEntity();
        branchCreationEntity.setAddreess("42 Main St");
        branchCreationEntity.setBranchName("janedoe/featurebranch");
        branchCreationEntity.setContact_name("Contact name");
        branchCreationEntity.setDlNumber("42");
        branchCreationEntity.setGstNumber("42");
        branchCreationEntity.setId(123L);
        branchCreationEntity.setLocation("Location");
        branchCreationEntity.setMobileNo("Mobile No");
        branchCreationEntity.setPincode(1);
        branchCreationEntity.setStatus("Status");

        StockCreationEntity stockCreationEntity = new StockCreationEntity();
        stockCreationEntity.setBarnchCreation(branchCreationEntity);
        stockCreationEntity.setId(123L);
        stockCreationEntity.setStatus("Status");
        stockCreationEntity.setStockName("Stock Name");

        ArrayList<StockCreationEntity> stockCreationEntityList = new ArrayList<>();
        stockCreationEntityList.add(stockCreationEntity);
        when(stockCreationRepository.findByStockName((String) any())).thenReturn(stockCreationEntityList);

        // Act and Assert
        assertTrue(stockCreationServieImpl.getStockName("Bname"));
        verify(stockCreationRepository).findByStockName((String) any());
    }

    /**
     * Method under test: {@link StockCreationServieImpl#getStockName(String)}
     */
    @Test
    void testGetStockName3() {
        // Arrange
        when(stockCreationRepository.findByStockName((String) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> stockCreationServieImpl.getStockName("Bname"));
        verify(stockCreationRepository).findByStockName((String) any());
    }

    /**
     * Method under test: {@link StockCreationServieImpl#createOrUpdateStock(StockCreationEntity)}
     */
    @Test
    void testCreateOrUpdateStock() {
        // Arrange
        BranchCreationEntity branchCreationEntity = new BranchCreationEntity();
        branchCreationEntity.setAddreess("42 Main St");
        branchCreationEntity.setBranchName("janedoe/featurebranch");
        branchCreationEntity.setContact_name("Contact name");
        branchCreationEntity.setDlNumber("42");
        branchCreationEntity.setGstNumber("42");
        branchCreationEntity.setId(123L);
        branchCreationEntity.setLocation("Location");
        branchCreationEntity.setMobileNo("Mobile No");
        branchCreationEntity.setPincode(1);
        branchCreationEntity.setStatus("Status");

        StockCreationEntity stockCreationEntity = new StockCreationEntity();
        stockCreationEntity.setBarnchCreation(branchCreationEntity);
        stockCreationEntity.setId(123L);
        stockCreationEntity.setStatus("Status");
        stockCreationEntity.setStockName("Stock Name");
        when(stockCreationRepository.save((StockCreationEntity) any())).thenReturn(stockCreationEntity);

        BranchCreationEntity branchCreationEntity1 = new BranchCreationEntity();
        branchCreationEntity1.setAddreess("42 Main St");
        branchCreationEntity1.setBranchName("janedoe/featurebranch");
        branchCreationEntity1.setContact_name("Contact name");
        branchCreationEntity1.setDlNumber("42");
        branchCreationEntity1.setGstNumber("42");
        branchCreationEntity1.setId(123L);
        branchCreationEntity1.setLocation("Location");
        branchCreationEntity1.setMobileNo("Mobile No");
        branchCreationEntity1.setPincode(1);
        branchCreationEntity1.setStatus("Status");

        StockCreationEntity stockCreationEntity1 = new StockCreationEntity();
        stockCreationEntity1.setBarnchCreation(branchCreationEntity1);
        stockCreationEntity1.setId(123L);
        stockCreationEntity1.setStatus("Status");
        stockCreationEntity1.setStockName("Stock Name");

        // Act and Assert
        assertSame(stockCreationEntity, stockCreationServieImpl.createOrUpdateStock(stockCreationEntity1));
        verify(stockCreationRepository).save((StockCreationEntity) any());
    }

    /**
     * Method under test: {@link StockCreationServieImpl#createOrUpdateStock(StockCreationEntity)}
     */
    @Test
    void testCreateOrUpdateStock2() {
        // Arrange
        when(stockCreationRepository.save((StockCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        BranchCreationEntity branchCreationEntity = new BranchCreationEntity();
        branchCreationEntity.setAddreess("42 Main St");
        branchCreationEntity.setBranchName("janedoe/featurebranch");
        branchCreationEntity.setContact_name("Contact name");
        branchCreationEntity.setDlNumber("42");
        branchCreationEntity.setGstNumber("42");
        branchCreationEntity.setId(123L);
        branchCreationEntity.setLocation("Location");
        branchCreationEntity.setMobileNo("Mobile No");
        branchCreationEntity.setPincode(1);
        branchCreationEntity.setStatus("Status");

        StockCreationEntity stockCreationEntity = new StockCreationEntity();
        stockCreationEntity.setBarnchCreation(branchCreationEntity);
        stockCreationEntity.setId(123L);
        stockCreationEntity.setStatus("Status");
        stockCreationEntity.setStockName("Stock Name");

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> stockCreationServieImpl.createOrUpdateStock(stockCreationEntity));
        verify(stockCreationRepository).save((StockCreationEntity) any());
    }

    /**
     * Method under test: {@link StockCreationServieImpl#deleteStockById(Long)}
     */
    @Test
    void testDeleteStockById() throws RecordNotFoundException {
        // Arrange
        BranchCreationEntity branchCreationEntity = new BranchCreationEntity();
        branchCreationEntity.setAddreess("42 Main St");
        branchCreationEntity.setBranchName("janedoe/featurebranch");
        branchCreationEntity.setContact_name("Contact name");
        branchCreationEntity.setDlNumber("42");
        branchCreationEntity.setGstNumber("42");
        branchCreationEntity.setId(123L);
        branchCreationEntity.setLocation("Location");
        branchCreationEntity.setMobileNo("Mobile No");
        branchCreationEntity.setPincode(1);
        branchCreationEntity.setStatus("Status");

        StockCreationEntity stockCreationEntity = new StockCreationEntity();
        stockCreationEntity.setBarnchCreation(branchCreationEntity);
        stockCreationEntity.setId(123L);
        stockCreationEntity.setStatus("Status");
        stockCreationEntity.setStockName("Stock Name");
        Optional<StockCreationEntity> ofResult = Optional.of(stockCreationEntity);
        doNothing().when(stockCreationRepository).deleteById((Long) any());
        when(stockCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act
        stockCreationServieImpl.deleteStockById(123L);

        // Assert that nothing has changed
        verify(stockCreationRepository).findById((Long) any());
        verify(stockCreationRepository).deleteById((Long) any());
        assertTrue(stockCreationServieImpl.getAllStock().isEmpty());
    }

    /**
     * Method under test: {@link StockCreationServieImpl#deleteStockById(Long)}
     */
    @Test
    void testDeleteStockById2() throws RecordNotFoundException {
        // Arrange
        BranchCreationEntity branchCreationEntity = new BranchCreationEntity();
        branchCreationEntity.setAddreess("42 Main St");
        branchCreationEntity.setBranchName("janedoe/featurebranch");
        branchCreationEntity.setContact_name("Contact name");
        branchCreationEntity.setDlNumber("42");
        branchCreationEntity.setGstNumber("42");
        branchCreationEntity.setId(123L);
        branchCreationEntity.setLocation("Location");
        branchCreationEntity.setMobileNo("Mobile No");
        branchCreationEntity.setPincode(1);
        branchCreationEntity.setStatus("Status");

        StockCreationEntity stockCreationEntity = new StockCreationEntity();
        stockCreationEntity.setBarnchCreation(branchCreationEntity);
        stockCreationEntity.setId(123L);
        stockCreationEntity.setStatus("Status");
        stockCreationEntity.setStockName("Stock Name");
        Optional<StockCreationEntity> ofResult = Optional.of(stockCreationEntity);
        doThrow(new UnableToProcessException("An error occurred")).when(stockCreationRepository).deleteById((Long) any());
        when(stockCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> stockCreationServieImpl.deleteStockById(123L));
        verify(stockCreationRepository).findById((Long) any());
        verify(stockCreationRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link StockCreationServieImpl#deleteStockById(Long)}
     */
    @Test
    void testDeleteStockById3() throws RecordNotFoundException {
        // Arrange
        doNothing().when(stockCreationRepository).deleteById((Long) any());
        when(stockCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> stockCreationServieImpl.deleteStockById(123L));
        verify(stockCreationRepository).findById((Long) any());
    }
}

