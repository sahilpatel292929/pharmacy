package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.RouteCreationRepository;
import com.ets.SecurePharmacy.tenant.model.RouteCreationEntity;
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

@ContextConfiguration(classes = {RouteCreationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class RouteCreationServiceImplTest {
    @MockBean
    private RouteCreationRepository routeCreationRepository;

    @Autowired
    private RouteCreationServiceImpl routeCreationServiceImpl;

    /**
     * Method under test: {@link RouteCreationServiceImpl#getAllRoutes()}
     */
    @Test
    void testGetAllRoutes() {
        // Arrange
        ArrayList<RouteCreationEntity> routeCreationEntityList = new ArrayList<>();
        when(routeCreationRepository.findAll()).thenReturn(routeCreationEntityList);

        // Act
        List<RouteCreationEntity> actualAllRoutes = routeCreationServiceImpl.getAllRoutes();

        // Assert
        assertSame(routeCreationEntityList, actualAllRoutes);
        assertTrue(actualAllRoutes.isEmpty());
        verify(routeCreationRepository).findAll();
    }

    /**
     * Method under test: {@link RouteCreationServiceImpl#getAllRoutes()}
     */
    @Test
    void testGetAllRoutes2() {
        // Arrange
        when(routeCreationRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> routeCreationServiceImpl.getAllRoutes());
        verify(routeCreationRepository).findAll();
    }

    /**
     * Method under test: {@link RouteCreationServiceImpl#getAllRouteWithPagination(int, int)}
     */
    @Test
    void testGetAllRouteWithPagination() {
        // Arrange
        PageImpl<RouteCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(routeCreationRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<RouteCreationEntity> actualAllRouteWithPagination = routeCreationServiceImpl.getAllRouteWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllRouteWithPagination);
        assertTrue(actualAllRouteWithPagination.toList().isEmpty());
        verify(routeCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link RouteCreationServiceImpl#getAllRouteWithPagination(int, int)}
     */
    @Test
    void testGetAllRouteWithPagination2() {
        // Arrange
        when(routeCreationRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> routeCreationServiceImpl.getAllRouteWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link RouteCreationServiceImpl#getAllRouteWithPagination(int, int)}
     */
    @Test
    void testGetAllRouteWithPagination3() {
        // Arrange
        when(routeCreationRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> routeCreationServiceImpl.getAllRouteWithPagination(2, 3));
        verify(routeCreationRepository).findAll((Pageable) any());
    }
}

