package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.GroupCreationRepository;
import com.ets.SecurePharmacy.tenant.model.GroupCreationEntity;
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {GroupCreationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class GroupCreationServiceImplTest {
    @MockBean
    private GroupCreationRepository groupCreationRepository;

    @Autowired
    private GroupCreationServiceImpl groupCreationServiceImpl;

    /**
     * Method under test: {@link GroupCreationServiceImpl#getAllGroupList()}
     */
    @Test
    void testGetAllGroupList() {
        // Arrange
        when(groupCreationRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(groupCreationServiceImpl.getAllGroupList().isEmpty());
        verify(groupCreationRepository).findAll();
    }

    /**
     * Method under test: {@link GroupCreationServiceImpl#getAllGroupList()}
     */
    @Test
    void testGetAllGroupList2() {
        // Arrange
        GroupCreationEntity groupCreationEntity = new GroupCreationEntity();
        groupCreationEntity.setGroupName("Group Name");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("Status");

        ArrayList<GroupCreationEntity> groupCreationEntityList = new ArrayList<>();
        groupCreationEntityList.add(groupCreationEntity);
        when(groupCreationRepository.findAll()).thenReturn(groupCreationEntityList);

        // Act
        List<GroupCreationEntity> actualAllGroupList = groupCreationServiceImpl.getAllGroupList();

        // Assert
        assertSame(groupCreationEntityList, actualAllGroupList);
        assertEquals(1, actualAllGroupList.size());
        verify(groupCreationRepository).findAll();
    }

    /**
     * Method under test: {@link GroupCreationServiceImpl#getAllGroupList()}
     */
    @Test
    void testGetAllGroupList3() {
        // Arrange
        when(groupCreationRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> groupCreationServiceImpl.getAllGroupList());
        verify(groupCreationRepository).findAll();
    }

    /**
     * Method under test: {@link GroupCreationServiceImpl#getGroupById(Long)}
     */
    @Test
    void testGetGroupById() throws RecordNotFoundException {
        // Arrange
        GroupCreationEntity groupCreationEntity = new GroupCreationEntity();
        groupCreationEntity.setGroupName("Group Name");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("Status");
        Optional<GroupCreationEntity> ofResult = Optional.of(groupCreationEntity);
        when(groupCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(groupCreationEntity, groupCreationServiceImpl.getGroupById(123L));
        verify(groupCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link GroupCreationServiceImpl#getGroupById(Long)}
     */
    @Test
    void testGetGroupById2() throws RecordNotFoundException {
        // Arrange
        when(groupCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> groupCreationServiceImpl.getGroupById(123L));
        verify(groupCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link GroupCreationServiceImpl#getGroupById(Long)}
     */
    @Test
    void testGetGroupById3() throws RecordNotFoundException {
        // Arrange
        when(groupCreationRepository.findById((Long) any())).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> groupCreationServiceImpl.getGroupById(123L));
        verify(groupCreationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link GroupCreationServiceImpl#getGroupByName(String)}
     */
    @Test
    void testGetGroupByName() {
        // Arrange
        when(groupCreationRepository.findByGroupName((String) any())).thenReturn(new ArrayList<>());

        // Act and Assert
        assertFalse(groupCreationServiceImpl.getGroupByName("Area"));
        verify(groupCreationRepository).findByGroupName((String) any());
    }

    /**
     * Method under test: {@link GroupCreationServiceImpl#getGroupByName(String)}
     */
    @Test
    void testGetGroupByName2() {
        // Arrange
        GroupCreationEntity groupCreationEntity = new GroupCreationEntity();
        groupCreationEntity.setGroupName("Group Name");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("Status");

        ArrayList<GroupCreationEntity> groupCreationEntityList = new ArrayList<>();
        groupCreationEntityList.add(groupCreationEntity);
        when(groupCreationRepository.findByGroupName((String) any())).thenReturn(groupCreationEntityList);

        // Act and Assert
        assertTrue(groupCreationServiceImpl.getGroupByName("Area"));
        verify(groupCreationRepository).findByGroupName((String) any());
    }

    /**
     * Method under test: {@link GroupCreationServiceImpl#getGroupByName(String)}
     */
    @Test
    void testGetGroupByName3() {
        // Arrange
        when(groupCreationRepository.findByGroupName((String) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> groupCreationServiceImpl.getGroupByName("Area"));
        verify(groupCreationRepository).findByGroupName((String) any());
    }

    /**
     * Method under test: {@link GroupCreationServiceImpl#createOrUpdateGroup(GroupCreationEntity)}
     */
    @Test
    void testCreateOrUpdateGroup() {
        // Arrange
        GroupCreationEntity groupCreationEntity = new GroupCreationEntity();
        groupCreationEntity.setGroupName("Group Name");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("Status");
        when(groupCreationRepository.save((GroupCreationEntity) any())).thenReturn(groupCreationEntity);
        when(groupCreationRepository.findByGroupName((String) any())).thenReturn(new ArrayList<>());

        GroupCreationEntity groupCreationEntity1 = new GroupCreationEntity();
        groupCreationEntity1.setGroupName("Group Name");
        groupCreationEntity1.setId(123L);
        groupCreationEntity1.setStatus("Status");

        // Act and Assert
        assertSame(groupCreationEntity, groupCreationServiceImpl.createOrUpdateGroup(groupCreationEntity1));
        verify(groupCreationRepository).save((GroupCreationEntity) any());
        verify(groupCreationRepository).findByGroupName((String) any());
    }

    /**
     * Method under test: {@link GroupCreationServiceImpl#createOrUpdateGroup(GroupCreationEntity)}
     */
    @Test
    void testCreateOrUpdateGroup2() {
        // Arrange
        GroupCreationEntity groupCreationEntity = new GroupCreationEntity();
        groupCreationEntity.setGroupName("Group Name");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("Status");

        GroupCreationEntity groupCreationEntity1 = new GroupCreationEntity();
        groupCreationEntity1.setGroupName("Group Name");
        groupCreationEntity1.setId(123L);
        groupCreationEntity1.setStatus("Status");

        ArrayList<GroupCreationEntity> groupCreationEntityList = new ArrayList<>();
        groupCreationEntityList.add(groupCreationEntity1);
        when(groupCreationRepository.save((GroupCreationEntity) any())).thenReturn(groupCreationEntity);
        when(groupCreationRepository.findByGroupName((String) any())).thenReturn(groupCreationEntityList);

        GroupCreationEntity groupCreationEntity2 = new GroupCreationEntity();
        groupCreationEntity2.setGroupName("Group Name");
        groupCreationEntity2.setId(123L);
        groupCreationEntity2.setStatus("Status");

        // Act and Assert
        assertThrows(RecordAlreadyPresentException.class,
                () -> groupCreationServiceImpl.createOrUpdateGroup(groupCreationEntity2));
        verify(groupCreationRepository).findByGroupName((String) any());
    }

    /**
     * Method under test: {@link GroupCreationServiceImpl#createOrUpdateGroup(GroupCreationEntity)}
     */
    @Test
    void testCreateOrUpdateGroup3() {
        // Arrange
        when(groupCreationRepository.save((GroupCreationEntity) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));
        when(groupCreationRepository.findByGroupName((String) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));

        GroupCreationEntity groupCreationEntity = new GroupCreationEntity();
        groupCreationEntity.setGroupName("Group Name");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("Status");

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> groupCreationServiceImpl.createOrUpdateGroup(groupCreationEntity));
        verify(groupCreationRepository).findByGroupName((String) any());
    }

    /**
     * Method under test: {@link GroupCreationServiceImpl#createOrUpdateGroup(List)}
     */
    @Test
    void testCreateOrUpdateGroup4() {
        // Arrange
        when(groupCreationRepository.saveAll((Iterable<GroupCreationEntity>) any())).thenReturn(new ArrayList<>());
        ArrayList<GroupCreationEntity> groupCreationEntityList = new ArrayList<>();

        // Act
        groupCreationServiceImpl.createOrUpdateGroup(groupCreationEntityList);

        // Assert that nothing has changed
        verify(groupCreationRepository).saveAll((Iterable<GroupCreationEntity>) any());
        assertEquals(groupCreationEntityList, groupCreationServiceImpl.getAllGroupList());
    }

    /**
     * Method under test: {@link GroupCreationServiceImpl#createOrUpdateGroup(List)}
     */
    @Test
    void testCreateOrUpdateGroup5() {
        // Arrange
        when(groupCreationRepository.saveAll((Iterable<GroupCreationEntity>) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> groupCreationServiceImpl.createOrUpdateGroup(new ArrayList<>()));
        verify(groupCreationRepository).saveAll((Iterable<GroupCreationEntity>) any());
    }

    /**
     * Method under test: {@link GroupCreationServiceImpl#getAllGroupWithPagination(int, int)}
     */
    @Test
    void testGetAllGroupWithPagination() {
        // Arrange
        PageImpl<GroupCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(groupCreationRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<GroupCreationEntity> actualAllGroupWithPagination = groupCreationServiceImpl.getAllGroupWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllGroupWithPagination);
        assertTrue(actualAllGroupWithPagination.toList().isEmpty());
        verify(groupCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link GroupCreationServiceImpl#getAllGroupWithPagination(int, int)}
     */
    @Test
    void testGetAllGroupWithPagination2() {
        // Arrange
        when(groupCreationRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> groupCreationServiceImpl.getAllGroupWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link GroupCreationServiceImpl#getAllGroupWithPagination(int, int)}
     */
    @Test
    void testGetAllGroupWithPagination3() {
        // Arrange
        when(groupCreationRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> groupCreationServiceImpl.getAllGroupWithPagination(2, 3));
        verify(groupCreationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link GroupCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<GroupCreationEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(groupCreationRepository.findAllByGroupNameContains((String) any(), (Pageable) any())).thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<GroupCreationEntity> actualSearch = groupCreationServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(groupCreationRepository).findAllByGroupNameContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link GroupCreationServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(groupCreationRepository.findAllByGroupNameContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> groupCreationServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(groupCreationRepository).findAllByGroupNameContains((String) any(), (Pageable) any());
    }

    /**
     * Method under test: {@link GroupCreationServiceImpl#updateGroup(GroupCreationEntity)}
     */
    @Test
    void testUpdateGroup() {
        // Arrange
        GroupCreationEntity groupCreationEntity = new GroupCreationEntity();
        groupCreationEntity.setGroupName("Group Name");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("Status");

        // Act and Assert
        assertNull(groupCreationServiceImpl.updateGroup(groupCreationEntity));
    }

    /**
     * Method under test: {@link GroupCreationServiceImpl#updateGroup(GroupCreationEntity)}
     */
    @Test
    void testUpdateGroup2() {
        // Arrange
        GroupCreationEntity groupCreationEntity = mock(GroupCreationEntity.class);
        doNothing().when(groupCreationEntity).setGroupName((String) any());
        doNothing().when(groupCreationEntity).setId((Long) any());
        doNothing().when(groupCreationEntity).setStatus((String) any());
        groupCreationEntity.setGroupName("Group Name");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("Status");

        // Act and Assert
        assertNull(groupCreationServiceImpl.updateGroup(groupCreationEntity));
        verify(groupCreationEntity).setGroupName((String) any());
        verify(groupCreationEntity).setId((Long) any());
        verify(groupCreationEntity).setStatus((String) any());
    }

    /**
     * Method under test: {@link GroupCreationServiceImpl#deleteGroupById(Long)}
     */
    @Test
    void testDeleteGroupById() throws RecordNotFoundException {
        // Arrange
        GroupCreationEntity groupCreationEntity = new GroupCreationEntity();
        groupCreationEntity.setGroupName("Group Name");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("Status");
        Optional<GroupCreationEntity> ofResult = Optional.of(groupCreationEntity);
        doNothing().when(groupCreationRepository).deleteById((Long) any());
        when(groupCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act
        groupCreationServiceImpl.deleteGroupById(123L);

        // Assert that nothing has changed
        verify(groupCreationRepository).findById((Long) any());
        verify(groupCreationRepository).deleteById((Long) any());
        assertTrue(groupCreationServiceImpl.getAllGroupList().isEmpty());
    }

    /**
     * Method under test: {@link GroupCreationServiceImpl#deleteGroupById(Long)}
     */
    @Test
    void testDeleteGroupById2() throws RecordNotFoundException {
        // Arrange
        GroupCreationEntity groupCreationEntity = new GroupCreationEntity();
        groupCreationEntity.setGroupName("Group Name");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("Status");
        Optional<GroupCreationEntity> ofResult = Optional.of(groupCreationEntity);
        doThrow(new UnableToProcessException("An error occurred")).when(groupCreationRepository).deleteById((Long) any());
        when(groupCreationRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> groupCreationServiceImpl.deleteGroupById(123L));
        verify(groupCreationRepository).findById((Long) any());
        verify(groupCreationRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link GroupCreationServiceImpl#deleteGroupById(Long)}
     */
    @Test
    void testDeleteGroupById3() throws RecordNotFoundException {
        // Arrange
        doNothing().when(groupCreationRepository).deleteById((Long) any());
        when(groupCreationRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> groupCreationServiceImpl.deleteGroupById(123L));
        verify(groupCreationRepository).findById((Long) any());
    }
}

