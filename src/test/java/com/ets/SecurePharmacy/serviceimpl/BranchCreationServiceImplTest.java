package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.BranchCreationRepository;
import com.ets.SecurePharmacy.tenant.model.BranchCreationEntity;
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

@ContextConfiguration(classes = {BranchCreationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BranchCreationServiceImplTest {
    @MockBean
    private BranchCreationRepository branchCreationRepository;

    @Autowired
    private BranchCreationServiceImpl branchCreationServiceImpl;

    /**
     * Method under test: {@link BranchCreationServiceImpl#getAllBranchList()}
     */
    @Test
    void testGetAllBranchList() {
        // Arrange
        when(branchCreationRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(branchCreationServiceImpl.getAllBranchList().isEmpty());
        verify(branchCreationRepository).findAll();
    }

    /**
     * Method under test: {@link BranchCreationServiceImpl#getAllBranchList()}
     */
    @Test
    void testGetAllBranchList2() {
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

        ArrayList<BranchCreationEntity> branchCreationEntityList = new ArrayList<>();
        branchCreationEntityList.add(branchCreationEntity);
        when(branchCreationRepository.findAll()).thenReturn(branchCreationEntityList);

        // Act
        List<BranchCreationEntity> actualAllBranchList = branchCreationServiceImpl.getAllBranchList();

        // Assert
        assertSame(branchCreationEntityList, actualAllBranchList);
        assertEquals(1, actualAllBranchList.size());
        verify(branchCreationRepository).findAll();
    }

    /**
     * Method under test: {@link BranchCreationServiceImpl#getAllBranchList()}
     */
    @Test
    void testGetAllBranchList3() {
        // Arrange
        when(branchCreationRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> branchCreationServiceImpl.getAllBranchList());
        verify(branchCreationRepository).findAll();
    }

    /**
     * Method under test: {@link BranchCreationServiceImpl#getBranchById(Long)}
     */
    @Test
    void testGetBranchById() throws RecordNotFoundException {
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
        Optional<BranchCreationEntity> ofResult = Optional.of(branchCreationEntity);
        when(branchCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(branchCreationEntity, branchCreationServiceImpl.getBranchById(123L));
        verify(branchCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link BranchCreationServiceImpl#getBranchById(Long)}
     */
    @Test
    void testGetBranchById2() throws RecordNotFoundException {
        // Arrange
        when(branchCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> branchCreationServiceImpl.getBranchById(123L));
        verify(branchCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link BranchCreationServiceImpl#getBranchById(Long)}
     */
    @Test
    void testGetBranchById3() throws RecordNotFoundException {
        // Arrange
        when(branchCreationRepository.findById((Long) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> branchCreationServiceImpl.getBranchById(123L));
        verify(branchCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link BranchCreationServiceImpl#getBranchByName(String)}
     */
    @Test
    void testGetBranchByName() {
        // Arrange
        when(branchCreationRepository.findByBranchName((String) any())).thenReturn(new ArrayList<>());

        // Act and Assert
        assertFalse(branchCreationServiceImpl.getBranchByName("janedoe/featurebranch"));
        verify(branchCreationRepository).findByBranchName((String) any());
    }

    /**
     * Method under test: {@link BranchCreationServiceImpl#getBranchByName(String)}
     */
    @Test
    void testGetBranchByName2() {
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

        ArrayList<BranchCreationEntity> branchCreationEntityList = new ArrayList<>();
        branchCreationEntityList.add(branchCreationEntity);
        when(branchCreationRepository.findByBranchName((String) any())).thenReturn(branchCreationEntityList);

        // Act and Assert
        assertTrue(branchCreationServiceImpl.getBranchByName("janedoe/featurebranch"));
        verify(branchCreationRepository).findByBranchName((String) any());
    }

    /**
     * Method under test: {@link BranchCreationServiceImpl#getBranchByName(String)}
     */
    @Test
    void testGetBranchByName3() {
        // Arrange
        when(branchCreationRepository.findByBranchName((String) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> branchCreationServiceImpl.getBranchByName("janedoe/featurebranch"));
        verify(branchCreationRepository).findByBranchName((String) any());
    }

    /**
     * Method under test: {@link BranchCreationServiceImpl#createOrUpdateBranch(BranchCreationEntity)}
     */
    @Test
    void testCreateOrUpdateBranch() {
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
        when(branchCreationRepository.save((BranchCreationEntity) any())).thenReturn(branchCreationEntity);
        when(branchCreationRepository.findByBranchName((String) any())).thenReturn(new ArrayList<>());

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

        // Act and Assert
        assertSame(branchCreationEntity, branchCreationServiceImpl.createOrUpdateBranch(branchCreationEntity1));
        verify(branchCreationRepository).save((BranchCreationEntity) any());
        verify(branchCreationRepository).findByBranchName((String) any());
    }

    /**
     * Method under test: {@link BranchCreationServiceImpl#createOrUpdateBranch(BranchCreationEntity)}
     */
    @Test
    void testCreateOrUpdateBranch2() {
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

        ArrayList<BranchCreationEntity> branchCreationEntityList = new ArrayList<>();
        branchCreationEntityList.add(branchCreationEntity1);
        when(branchCreationRepository.save((BranchCreationEntity) any())).thenReturn(branchCreationEntity);
        when(branchCreationRepository.findByBranchName((String) any())).thenReturn(branchCreationEntityList);

        BranchCreationEntity branchCreationEntity2 = new BranchCreationEntity();
        branchCreationEntity2.setAddreess("42 Main St");
        branchCreationEntity2.setBranchName("janedoe/featurebranch");
        branchCreationEntity2.setContact_name("Contact name");
        branchCreationEntity2.setDlNumber("42");
        branchCreationEntity2.setGstNumber("42");
        branchCreationEntity2.setId(123L);
        branchCreationEntity2.setLocation("Location");
        branchCreationEntity2.setMobileNo("Mobile No");
        branchCreationEntity2.setPincode(1);
        branchCreationEntity2.setStatus("Status");

        // Act and Assert
        assertThrows(RecordAlreadyPresentException.class,
                () -> branchCreationServiceImpl.createOrUpdateBranch(branchCreationEntity2));
        verify(branchCreationRepository).findByBranchName((String) any());
    }

    /**
     * Method under test: {@link BranchCreationServiceImpl#createOrUpdateBranch(BranchCreationEntity)}
     */
    @Test
    void testCreateOrUpdateBranch3() {
        // Arrange
        when(branchCreationRepository.save((BranchCreationEntity) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));
        when(branchCreationRepository.findByBranchName((String) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));

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

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> branchCreationServiceImpl.createOrUpdateBranch(branchCreationEntity));
        verify(branchCreationRepository).findByBranchName((String) any());
    }

    /**
     * Method under test: {@link BranchCreationServiceImpl#deleteBranchById(Long)}
     */
    @Test
    void testDeleteBranchById() throws RecordNotFoundException {
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
        Optional<BranchCreationEntity> ofResult = Optional.of(branchCreationEntity);
        doNothing().when(branchCreationRepository).deleteById((Long) any());
        when(branchCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act
        branchCreationServiceImpl.deleteBranchById(123L);

        // Assert that nothing has changed
        verify(branchCreationRepository).findById((Long) any());
        verify(branchCreationRepository).deleteById((Long) any());
        assertTrue(branchCreationServiceImpl.getAllBranchList().isEmpty());
    }

    /**
     * Method under test: {@link BranchCreationServiceImpl#deleteBranchById(Long)}
     */
    @Test
    void testDeleteBranchById2() throws RecordNotFoundException {
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
        Optional<BranchCreationEntity> ofResult = Optional.of(branchCreationEntity);
        doThrow(new UnableToProcessException("An error occurred")).when(branchCreationRepository)
                .deleteById((Long) any());
        when(branchCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> branchCreationServiceImpl.deleteBranchById(123L));
        verify(branchCreationRepository).findById((Long) any());
        verify(branchCreationRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link BranchCreationServiceImpl#deleteBranchById(Long)}
     */
    @Test
    void testDeleteBranchById3() throws RecordNotFoundException {
        // Arrange
        doNothing().when(branchCreationRepository).deleteById((Long) any());
        when(branchCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> branchCreationServiceImpl.deleteBranchById(123L));
        verify(branchCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link BranchCreationServiceImpl#getAllBranchWithPagination(int, int)}
     */
    @Test
    void testGetAllBranchWithPagination() {
        // Arrange
        PageImpl<BranchCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(branchCreationRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<BranchCreationEntity> actualAllBranchWithPagination = branchCreationServiceImpl.getAllBranchWithPagination(2,
                3);

        // Assert
        assertSame(pageImpl, actualAllBranchWithPagination);
        assertTrue(actualAllBranchWithPagination.toList().isEmpty());
        verify(branchCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link BranchCreationServiceImpl#getAllBranchWithPagination(int, int)}
     */
    @Test
    void testGetAllBranchWithPagination2() {
        // Arrange
        when(branchCreationRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> branchCreationServiceImpl.getAllBranchWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link BranchCreationServiceImpl#getAllBranchWithPagination(int, int)}
     */
    @Test
    void testGetAllBranchWithPagination3() {
        // Arrange
        when(branchCreationRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> branchCreationServiceImpl.getAllBranchWithPagination(2, 3));
        verify(branchCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link BranchCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<BranchCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(branchCreationRepository.findAllByBranchContains((String) any(), (Pageable) any())).thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<BranchCreationEntity> actualSearch = branchCreationServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(branchCreationRepository).findAllByBranchContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link BranchCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(branchCreationRepository.findAllByBranchContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> branchCreationServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(branchCreationRepository).findAllByBranchContains((String) any(), (Pageable) any());
    }

    /**
     * Method under test: {@link BranchCreationServiceImpl#updateBranch(BranchCreationEntity)}
     */
    @Test
    void testUpdateBranch() {
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
        when(branchCreationRepository.save((BranchCreationEntity) any())).thenReturn(branchCreationEntity);

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

        // Act and Assert
        assertSame(branchCreationEntity, branchCreationServiceImpl.updateBranch(branchCreationEntity1));
        verify(branchCreationRepository).save((BranchCreationEntity) any());
    }

    /**
     * Method under test: {@link BranchCreationServiceImpl#updateBranch(BranchCreationEntity)}
     */
    @Test
    void testUpdateBranch2() {
        // Arrange
        when(branchCreationRepository.save((BranchCreationEntity) any()))
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

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> branchCreationServiceImpl.updateBranch(branchCreationEntity));
        verify(branchCreationRepository).save((BranchCreationEntity) any());
    }
}

