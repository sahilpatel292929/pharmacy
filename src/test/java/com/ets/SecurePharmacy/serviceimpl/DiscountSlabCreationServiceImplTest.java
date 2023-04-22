package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.DiscountSlabCreationRepository;
import com.ets.SecurePharmacy.tenant.model.DiscountSlabCreationEntity;
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

@ContextConfiguration(classes = {DiscountSlabCreationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class DiscountSlabCreationServiceImplTest {
    @MockBean
    private DiscountSlabCreationRepository discountSlabCreationRepository;

    @Autowired
    private DiscountSlabCreationServiceImpl discountSlabCreationServiceImpl;

    /**
     * Method under test: {@link DiscountSlabCreationServiceImpl#getAllDisountSlabDetails()}
     */
    @Test
    void testGetAllDisountSlabDetails() {
        // Arrange
        when(discountSlabCreationRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(discountSlabCreationServiceImpl.getAllDisountSlabDetails().isEmpty());
        verify(discountSlabCreationRepository).findAll();
    }

    /**
     * Method under test: {@link DiscountSlabCreationServiceImpl#getAllDisountSlabDetails()}
     */
    @Test
    void testGetAllDisountSlabDetails2() {
        // Arrange
        DiscountSlabCreationEntity discountSlabCreationEntity = new DiscountSlabCreationEntity();
        discountSlabCreationEntity.setDiscount("3");
        discountSlabCreationEntity.setDiscountSlabName("3");
        discountSlabCreationEntity.setFrom_amt(1);
        discountSlabCreationEntity.setId(123L);
        discountSlabCreationEntity.setStatus("Status");
        discountSlabCreationEntity.setTo_amt(1);

        ArrayList<DiscountSlabCreationEntity> discountSlabCreationEntityList = new ArrayList<>();
        discountSlabCreationEntityList.add(discountSlabCreationEntity);
        when(discountSlabCreationRepository.findAll()).thenReturn(discountSlabCreationEntityList);

        // Act
        List<DiscountSlabCreationEntity> actualAllDisountSlabDetails = discountSlabCreationServiceImpl
                .getAllDisountSlabDetails();

        // Assert
        assertSame(discountSlabCreationEntityList, actualAllDisountSlabDetails);
        assertEquals(1, actualAllDisountSlabDetails.size());
        verify(discountSlabCreationRepository).findAll();
    }

    /**
     * Method under test: {@link DiscountSlabCreationServiceImpl#getAllDisountSlabDetails()}
     */
    @Test
    void testGetAllDisountSlabDetails3() {
        // Arrange
        when(discountSlabCreationRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> discountSlabCreationServiceImpl.getAllDisountSlabDetails());
        verify(discountSlabCreationRepository).findAll();
    }

    /**
     * Method under test: {@link DiscountSlabCreationServiceImpl#getDiscountSalbById(Long)}
     */
    @Test
    void testGetDiscountSalbById() throws RecordNotFoundException {
        // Arrange
        DiscountSlabCreationEntity discountSlabCreationEntity = new DiscountSlabCreationEntity();
        discountSlabCreationEntity.setDiscount("3");
        discountSlabCreationEntity.setDiscountSlabName("3");
        discountSlabCreationEntity.setFrom_amt(1);
        discountSlabCreationEntity.setId(123L);
        discountSlabCreationEntity.setStatus("Status");
        discountSlabCreationEntity.setTo_amt(1);
        Optional<DiscountSlabCreationEntity> ofResult = Optional.of(discountSlabCreationEntity);
        when(discountSlabCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(discountSlabCreationEntity, discountSlabCreationServiceImpl.getDiscountSalbById(123L));
        verify(discountSlabCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link DiscountSlabCreationServiceImpl#getDiscountSalbById(Long)}
     */
    @Test
    void testGetDiscountSalbById2() throws RecordNotFoundException {
        // Arrange
        when(discountSlabCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> discountSlabCreationServiceImpl.getDiscountSalbById(123L));
        verify(discountSlabCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link DiscountSlabCreationServiceImpl#getDiscountSalbById(Long)}
     */
    @Test
    void testGetDiscountSalbById3() throws RecordNotFoundException {
        // Arrange
        when(discountSlabCreationRepository.findById((Long) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> discountSlabCreationServiceImpl.getDiscountSalbById(123L));
        verify(discountSlabCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link DiscountSlabCreationServiceImpl#getDiscountSlabName(String)}
     */
    @Test
    void testGetDiscountSlabName() {
        // Arrange
        when(discountSlabCreationRepository.findByDiscountSlabName((String) any())).thenReturn(new ArrayList<>());

        // Act and Assert
        assertFalse(discountSlabCreationServiceImpl.getDiscountSlabName("Dname"));
        verify(discountSlabCreationRepository).findByDiscountSlabName((String) any());
    }

    /**
     * Method under test: {@link DiscountSlabCreationServiceImpl#getDiscountSlabName(String)}
     */
    @Test
    void testGetDiscountSlabName2() {
        // Arrange
        DiscountSlabCreationEntity discountSlabCreationEntity = new DiscountSlabCreationEntity();
        discountSlabCreationEntity.setDiscount("3");
        discountSlabCreationEntity.setDiscountSlabName("3");
        discountSlabCreationEntity.setFrom_amt(1);
        discountSlabCreationEntity.setId(123L);
        discountSlabCreationEntity.setStatus("Status");
        discountSlabCreationEntity.setTo_amt(1);

        ArrayList<DiscountSlabCreationEntity> discountSlabCreationEntityList = new ArrayList<>();
        discountSlabCreationEntityList.add(discountSlabCreationEntity);
        when(discountSlabCreationRepository.findByDiscountSlabName((String) any()))
                .thenReturn(discountSlabCreationEntityList);

        // Act and Assert
        assertTrue(discountSlabCreationServiceImpl.getDiscountSlabName("Dname"));
        verify(discountSlabCreationRepository).findByDiscountSlabName((String) any());
    }

    /**
     * Method under test: {@link DiscountSlabCreationServiceImpl#getDiscountSlabName(String)}
     */
    @Test
    void testGetDiscountSlabName3() {
        // Arrange
        when(discountSlabCreationRepository.findByDiscountSlabName((String) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> discountSlabCreationServiceImpl.getDiscountSlabName("Dname"));
        verify(discountSlabCreationRepository).findByDiscountSlabName((String) any());
    }

    /**
     * Method under test: {@link DiscountSlabCreationServiceImpl#createOrUpdateDiscountSlab(DiscountSlabCreationEntity)}
     */
    @Test
    void testCreateOrUpdateDiscountSlab() {
        // Arrange
        DiscountSlabCreationEntity discountSlabCreationEntity = new DiscountSlabCreationEntity();
        discountSlabCreationEntity.setDiscount("3");
        discountSlabCreationEntity.setDiscountSlabName("3");
        discountSlabCreationEntity.setFrom_amt(1);
        discountSlabCreationEntity.setId(123L);
        discountSlabCreationEntity.setStatus("Status");
        discountSlabCreationEntity.setTo_amt(1);
        when(discountSlabCreationRepository.save((DiscountSlabCreationEntity) any()))
                .thenReturn(discountSlabCreationEntity);
        when(discountSlabCreationRepository.findByDiscountSlabName((String) any())).thenReturn(new ArrayList<>());

        DiscountSlabCreationEntity discountSlabCreationEntity1 = new DiscountSlabCreationEntity();
        discountSlabCreationEntity1.setDiscount("3");
        discountSlabCreationEntity1.setDiscountSlabName("3");
        discountSlabCreationEntity1.setFrom_amt(1);
        discountSlabCreationEntity1.setId(123L);
        discountSlabCreationEntity1.setStatus("Status");
        discountSlabCreationEntity1.setTo_amt(1);

        // Act and Assert
        assertSame(discountSlabCreationEntity,
                discountSlabCreationServiceImpl.createOrUpdateDiscountSlab(discountSlabCreationEntity1));
        verify(discountSlabCreationRepository).save((DiscountSlabCreationEntity) any());
        verify(discountSlabCreationRepository).findByDiscountSlabName((String) any());
    }

    /**
     * Method under test: {@link DiscountSlabCreationServiceImpl#createOrUpdateDiscountSlab(DiscountSlabCreationEntity)}
     */
    @Test
    void testCreateOrUpdateDiscountSlab2() {
        // Arrange
        DiscountSlabCreationEntity discountSlabCreationEntity = new DiscountSlabCreationEntity();
        discountSlabCreationEntity.setDiscount("3");
        discountSlabCreationEntity.setDiscountSlabName("3");
        discountSlabCreationEntity.setFrom_amt(1);
        discountSlabCreationEntity.setId(123L);
        discountSlabCreationEntity.setStatus("Status");
        discountSlabCreationEntity.setTo_amt(1);

        DiscountSlabCreationEntity discountSlabCreationEntity1 = new DiscountSlabCreationEntity();
        discountSlabCreationEntity1.setDiscount("3");
        discountSlabCreationEntity1.setDiscountSlabName("3");
        discountSlabCreationEntity1.setFrom_amt(1);
        discountSlabCreationEntity1.setId(123L);
        discountSlabCreationEntity1.setStatus("Status");
        discountSlabCreationEntity1.setTo_amt(1);

        ArrayList<DiscountSlabCreationEntity> discountSlabCreationEntityList = new ArrayList<>();
        discountSlabCreationEntityList.add(discountSlabCreationEntity1);
        when(discountSlabCreationRepository.save((DiscountSlabCreationEntity) any()))
                .thenReturn(discountSlabCreationEntity);
        when(discountSlabCreationRepository.findByDiscountSlabName((String) any()))
                .thenReturn(discountSlabCreationEntityList);

        DiscountSlabCreationEntity discountSlabCreationEntity2 = new DiscountSlabCreationEntity();
        discountSlabCreationEntity2.setDiscount("3");
        discountSlabCreationEntity2.setDiscountSlabName("3");
        discountSlabCreationEntity2.setFrom_amt(1);
        discountSlabCreationEntity2.setId(123L);
        discountSlabCreationEntity2.setStatus("Status");
        discountSlabCreationEntity2.setTo_amt(1);

        // Act and Assert
        assertThrows(RecordAlreadyPresentException.class,
                () -> discountSlabCreationServiceImpl.createOrUpdateDiscountSlab(discountSlabCreationEntity2));
        verify(discountSlabCreationRepository).findByDiscountSlabName((String) any());
    }

    /**
     * Method under test: {@link DiscountSlabCreationServiceImpl#createOrUpdateDiscountSlab(DiscountSlabCreationEntity)}
     */
    @Test
    void testCreateOrUpdateDiscountSlab3() {
        // Arrange
        when(discountSlabCreationRepository.save((DiscountSlabCreationEntity) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));
        when(discountSlabCreationRepository.findByDiscountSlabName((String) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));

        DiscountSlabCreationEntity discountSlabCreationEntity = new DiscountSlabCreationEntity();
        discountSlabCreationEntity.setDiscount("3");
        discountSlabCreationEntity.setDiscountSlabName("3");
        discountSlabCreationEntity.setFrom_amt(1);
        discountSlabCreationEntity.setId(123L);
        discountSlabCreationEntity.setStatus("Status");
        discountSlabCreationEntity.setTo_amt(1);

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> discountSlabCreationServiceImpl.createOrUpdateDiscountSlab(discountSlabCreationEntity));
        verify(discountSlabCreationRepository).findByDiscountSlabName((String) any());
    }

    /**
     * Method under test: {@link DiscountSlabCreationServiceImpl#deleteDiscountSlabById(Long)}
     */
    @Test
    void testDeleteDiscountSlabById() throws RecordNotFoundException {
        // Arrange
        DiscountSlabCreationEntity discountSlabCreationEntity = new DiscountSlabCreationEntity();
        discountSlabCreationEntity.setDiscount("3");
        discountSlabCreationEntity.setDiscountSlabName("3");
        discountSlabCreationEntity.setFrom_amt(1);
        discountSlabCreationEntity.setId(123L);
        discountSlabCreationEntity.setStatus("Status");
        discountSlabCreationEntity.setTo_amt(1);
        Optional<DiscountSlabCreationEntity> ofResult = Optional.of(discountSlabCreationEntity);
        doNothing().when(discountSlabCreationRepository).deleteById((Long) any());
        when(discountSlabCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act
        discountSlabCreationServiceImpl.deleteDiscountSlabById(123L);

        // Assert that nothing has changed
        verify(discountSlabCreationRepository).findById((Long) any());
        verify(discountSlabCreationRepository).deleteById((Long) any());
        assertTrue(discountSlabCreationServiceImpl.getAllDisountSlabDetails().isEmpty());
    }

    /**
     * Method under test: {@link DiscountSlabCreationServiceImpl#deleteDiscountSlabById(Long)}
     */
    @Test
    void testDeleteDiscountSlabById2() throws RecordNotFoundException {
        // Arrange
        DiscountSlabCreationEntity discountSlabCreationEntity = new DiscountSlabCreationEntity();
        discountSlabCreationEntity.setDiscount("3");
        discountSlabCreationEntity.setDiscountSlabName("3");
        discountSlabCreationEntity.setFrom_amt(1);
        discountSlabCreationEntity.setId(123L);
        discountSlabCreationEntity.setStatus("Status");
        discountSlabCreationEntity.setTo_amt(1);
        Optional<DiscountSlabCreationEntity> ofResult = Optional.of(discountSlabCreationEntity);
        doThrow(new UnableToProcessException("An error occurred")).when(discountSlabCreationRepository)
                .deleteById((Long) any());
        when(discountSlabCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> discountSlabCreationServiceImpl.deleteDiscountSlabById(123L));
        verify(discountSlabCreationRepository).findById((Long) any());
        verify(discountSlabCreationRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link DiscountSlabCreationServiceImpl#deleteDiscountSlabById(Long)}
     */
    @Test
    void testDeleteDiscountSlabById3() throws RecordNotFoundException {
        // Arrange
        doNothing().when(discountSlabCreationRepository).deleteById((Long) any());
        when(discountSlabCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> discountSlabCreationServiceImpl.deleteDiscountSlabById(123L));
        verify(discountSlabCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link DiscountSlabCreationServiceImpl#createOrUpdateDiscountSlabList(List)}
     */
    @Test
    void testCreateOrUpdateDiscountSlabList() {
        // Arrange
        when(discountSlabCreationRepository.saveAll((Iterable<DiscountSlabCreationEntity>) any()))
                .thenReturn(new ArrayList<>());
        ArrayList<DiscountSlabCreationEntity> discountSlabCreationEntityList = new ArrayList<>();

        // Act
        discountSlabCreationServiceImpl.createOrUpdateDiscountSlabList(discountSlabCreationEntityList);

        // Assert that nothing has changed
        verify(discountSlabCreationRepository).saveAll((Iterable<DiscountSlabCreationEntity>) any());
        assertEquals(discountSlabCreationEntityList, discountSlabCreationServiceImpl.getAllDisountSlabDetails());
    }

    /**
     * Method under test: {@link DiscountSlabCreationServiceImpl#createOrUpdateDiscountSlabList(List)}
     */
    @Test
    void testCreateOrUpdateDiscountSlabList2() {
        // Arrange
        when(discountSlabCreationRepository.saveAll((Iterable<DiscountSlabCreationEntity>) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> discountSlabCreationServiceImpl.createOrUpdateDiscountSlabList(new ArrayList<>()));
        verify(discountSlabCreationRepository).saveAll((Iterable<DiscountSlabCreationEntity>) any());
    }

    /**
     * Method under test: {@link DiscountSlabCreationServiceImpl#getAllDiscountSlabWithPagination(int, int)}
     */
    @Test
    void testGetAllDiscountSlabWithPagination() {
        // Arrange
        PageImpl<DiscountSlabCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(discountSlabCreationRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<DiscountSlabCreationEntity> actualAllDiscountSlabWithPagination = discountSlabCreationServiceImpl
                .getAllDiscountSlabWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllDiscountSlabWithPagination);
        assertTrue(actualAllDiscountSlabWithPagination.toList().isEmpty());
        verify(discountSlabCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link DiscountSlabCreationServiceImpl#getAllDiscountSlabWithPagination(int, int)}
     */
    @Test
    void testGetAllDiscountSlabWithPagination2() {
        // Arrange
        when(discountSlabCreationRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> discountSlabCreationServiceImpl.getAllDiscountSlabWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link DiscountSlabCreationServiceImpl#getAllDiscountSlabWithPagination(int, int)}
     */
    @Test
    void testGetAllDiscountSlabWithPagination3() {
        // Arrange
        when(discountSlabCreationRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> discountSlabCreationServiceImpl.getAllDiscountSlabWithPagination(2, 3));
        verify(discountSlabCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link DiscountSlabCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<DiscountSlabCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(discountSlabCreationRepository.findAllByDiscountSlabNameContains((String) any(), (Pageable) any()))
                .thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<DiscountSlabCreationEntity> actualSearch = discountSlabCreationServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(discountSlabCreationRepository).findAllByDiscountSlabNameContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link DiscountSlabCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(discountSlabCreationRepository.findAllByDiscountSlabNameContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> discountSlabCreationServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(discountSlabCreationRepository).findAllByDiscountSlabNameContains((String) any(), (Pageable) any());
    }

    /**
     * Method under test: {@link DiscountSlabCreationServiceImpl#updateDiscountSlab(DiscountSlabCreationEntity)}
     */
    @Test
    void testUpdateDiscountSlab() {
        // Arrange
        DiscountSlabCreationEntity discountSlabCreationEntity = new DiscountSlabCreationEntity();
        discountSlabCreationEntity.setDiscount("3");
        discountSlabCreationEntity.setDiscountSlabName("3");
        discountSlabCreationEntity.setFrom_amt(1);
        discountSlabCreationEntity.setId(123L);
        discountSlabCreationEntity.setStatus("Status");
        discountSlabCreationEntity.setTo_amt(1);
        when(discountSlabCreationRepository.save((DiscountSlabCreationEntity) any()))
                .thenReturn(discountSlabCreationEntity);

        DiscountSlabCreationEntity discountSlabCreationEntity1 = new DiscountSlabCreationEntity();
        discountSlabCreationEntity1.setDiscount("3");
        discountSlabCreationEntity1.setDiscountSlabName("3");
        discountSlabCreationEntity1.setFrom_amt(1);
        discountSlabCreationEntity1.setId(123L);
        discountSlabCreationEntity1.setStatus("Status");
        discountSlabCreationEntity1.setTo_amt(1);

        // Act and Assert
        assertSame(discountSlabCreationEntity,
                discountSlabCreationServiceImpl.updateDiscountSlab(discountSlabCreationEntity1));
        verify(discountSlabCreationRepository).save((DiscountSlabCreationEntity) any());
    }

    /**
     * Method under test: {@link DiscountSlabCreationServiceImpl#updateDiscountSlab(DiscountSlabCreationEntity)}
     */
    @Test
    void testUpdateDiscountSlab2() {
        // Arrange
        when(discountSlabCreationRepository.save((DiscountSlabCreationEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        DiscountSlabCreationEntity discountSlabCreationEntity = new DiscountSlabCreationEntity();
        discountSlabCreationEntity.setDiscount("3");
        discountSlabCreationEntity.setDiscountSlabName("3");
        discountSlabCreationEntity.setFrom_amt(1);
        discountSlabCreationEntity.setId(123L);
        discountSlabCreationEntity.setStatus("Status");
        discountSlabCreationEntity.setTo_amt(1);

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> discountSlabCreationServiceImpl.updateDiscountSlab(discountSlabCreationEntity));
        verify(discountSlabCreationRepository).save((DiscountSlabCreationEntity) any());
    }
}

