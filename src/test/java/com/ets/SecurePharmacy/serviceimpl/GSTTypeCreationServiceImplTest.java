package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.GSTTypeCreationRepository;
import com.ets.SecurePharmacy.tenant.model.GSTTypeCreationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {GSTTypeCreationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class GSTTypeCreationServiceImplTest {
    @MockBean
    private GSTTypeCreationRepository gSTTypeCreationRepository;

    @Autowired
    private GSTTypeCreationServiceImpl gSTTypeCreationServiceImpl;

    /**
     * Method under test: {@link GSTTypeCreationServiceImpl#getAllGSTType()}
     */
    @Test
    void testGetAllGSTType() {
        // Arrange
        ArrayList<GSTTypeCreationEntity> gstTypeCreationEntityList = new ArrayList<>();
        when(gSTTypeCreationRepository.findAll()).thenReturn(gstTypeCreationEntityList);

        // Act
        List<GSTTypeCreationEntity> actualAllGSTType = gSTTypeCreationServiceImpl.getAllGSTType();

        // Assert
        assertSame(gstTypeCreationEntityList, actualAllGSTType);
        assertTrue(actualAllGSTType.isEmpty());
        verify(gSTTypeCreationRepository).findAll();
    }

    /**
     * Method under test: {@link GSTTypeCreationServiceImpl#getAllGSTType()}
     */
    @Test
    void testGetAllGSTType2() {
        // Arrange
        when(gSTTypeCreationRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> gSTTypeCreationServiceImpl.getAllGSTType());
        verify(gSTTypeCreationRepository).findAll();
    }

    /**
     * Method under test: {@link GSTTypeCreationServiceImpl#getAllGSTTypeWithPagination(int, int)}
     */
    @Test
    void testGetAllGSTTypeWithPagination() {
        // Arrange
        PageImpl<GSTTypeCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(gSTTypeCreationRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<GSTTypeCreationEntity> actualAllGSTTypeWithPagination = gSTTypeCreationServiceImpl
                .getAllGSTTypeWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllGSTTypeWithPagination);
        assertTrue(actualAllGSTTypeWithPagination.toList().isEmpty());
        verify(gSTTypeCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link GSTTypeCreationServiceImpl#getAllGSTTypeWithPagination(int, int)}
     */
    @Test
    void testGetAllGSTTypeWithPagination2() {
        // Arrange
        when(gSTTypeCreationRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> gSTTypeCreationServiceImpl.getAllGSTTypeWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link GSTTypeCreationServiceImpl#getAllGSTTypeWithPagination(int, int)}
     */
    @Test
    void testGetAllGSTTypeWithPagination3() {
        // Arrange
        when(gSTTypeCreationRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> gSTTypeCreationServiceImpl.getAllGSTTypeWithPagination(2, 3));
        verify(gSTTypeCreationRepository).findAll((Pageable) any());
    }
}

