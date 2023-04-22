package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.BestBeforeRepository;
import com.ets.SecurePharmacy.tenant.model.BestBeforeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {BestBeforeServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BestBeforeServiceImplTest {
    @MockBean
    private BestBeforeRepository bestBeforeRepository;

    @Autowired
    private BestBeforeServiceImpl bestBeforeServiceImpl;

    /**
     * Method under test: {@link BestBeforeServiceImpl#getAllBestBeforeTypes()}
     */
    @Test
    void testGetAllBestBeforeTypes() {
        // Arrange
        when(bestBeforeRepository.findAll()).thenReturn(Collections.emptyList());

        // Act and Assert
        assertTrue(bestBeforeServiceImpl.getAllBestBeforeTypes().isEmpty());
        verify(bestBeforeRepository).findAll();
    }

    /**
     * Method under test: {@link BestBeforeServiceImpl#getAllBestBeforeTypes()}
     */
    @Test
    void testGetAllBestBeforeTypes2() {
        // Arrange
        when(bestBeforeRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> bestBeforeServiceImpl.getAllBestBeforeTypes());
        verify(bestBeforeRepository).findAll();
    }
}

