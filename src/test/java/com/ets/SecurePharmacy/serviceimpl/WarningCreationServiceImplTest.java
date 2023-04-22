package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.WarningCreationRepository;
import com.ets.SecurePharmacy.tenant.model.WarningCreationEntity;
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

@ContextConfiguration(classes = {WarningCreationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class WarningCreationServiceImplTest {
    @MockBean
    private WarningCreationRepository warningCreationRepository;

    @Autowired
    private WarningCreationServiceImpl warningCreationServiceImpl;

    /**
     * Method under test: {@link WarningCreationServiceImpl#getAllWarningList()}
     */
    @Test
    void testGetAllWarningList() {
        // Arrange
        when(warningCreationRepository.findAll()).thenReturn(Collections.emptyList());

        // Act and Assert
        assertTrue(warningCreationServiceImpl.getAllWarningList().isEmpty());
        verify(warningCreationRepository).findAll();
    }

    /**
     * Method under test: {@link WarningCreationServiceImpl#getAllWarningList()}
     */
    @Test
    void testGetAllWarningList2() {
        // Arrange
        when(warningCreationRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> warningCreationServiceImpl.getAllWarningList());
        verify(warningCreationRepository).findAll();
    }
}

