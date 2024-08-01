package com.openclassrooms.starterjwt;

import com.openclassrooms.starterjwt.controllers.SessionController;
import com.openclassrooms.starterjwt.dto.SessionDto;
import com.openclassrooms.starterjwt.mapper.SessionMapper;
import com.openclassrooms.starterjwt.models.Session;
import com.openclassrooms.starterjwt.services.SessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class SessionControllerTest {

    @Mock
    private SessionMapper sessionMapper;

    @Mock
    private SessionService sessionService;

    @InjectMocks
    private SessionController sessionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        // Arrange
        Session session = new Session();
        SessionDto sessionDto = new SessionDto();
        when(sessionService.getById(anyLong())).thenReturn(session);
        when(sessionMapper.toDto(session)).thenReturn(sessionDto);

        // Act
        ResponseEntity<?> responseEntity = sessionController.findById("1");

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(sessionDto, responseEntity.getBody());
    }

    @Test
    public void testFindById_NotFound() {
        // Arrange
        when(sessionService.getById(anyLong())).thenReturn(null);

        // Act
        ResponseEntity<?> responseEntity = sessionController.findById("1");

        // Assert
        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testFindById_BadRequest() {
        // Act
        ResponseEntity<?> responseEntity = sessionController.findById("abc");

        // Assert
        assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testFindAll() {
        // Arrange
        List<Session> sessions = Collections.singletonList(new Session());
        List<SessionDto> sessionDtos = Collections.singletonList(new SessionDto());
        when(sessionService.findAll()).thenReturn(sessions);
        when(sessionMapper.toDto(sessions)).thenReturn(sessionDtos);

        // Act
        ResponseEntity<?> responseEntity = sessionController.findAll();

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(sessionDtos, responseEntity.getBody());
    }

    @Test
    public void testCreate() {
        // Arrange
        Session session = new Session();
        SessionDto sessionDto = new SessionDto();
        when(sessionService.create(any(Session.class))).thenReturn(session);
        when(sessionMapper.toEntity(any(SessionDto.class))).thenReturn(session);
        when(sessionMapper.toDto(session)).thenReturn(sessionDto);

        // Act
        ResponseEntity<?> responseEntity = sessionController.create(sessionDto);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(sessionDto, responseEntity.getBody());
    }

    @Test
    public void testUpdate() {
        // Arrange
        Session session = new Session();
        SessionDto sessionDto = new SessionDto();
        when(sessionService.update(anyLong(), any(Session.class))).thenReturn(session);
        when(sessionMapper.toEntity(any(SessionDto.class))).thenReturn(session);
        when(sessionMapper.toDto(session)).thenReturn(sessionDto);

        // Act
        ResponseEntity<?> responseEntity = sessionController.update("1", sessionDto);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(sessionDto, responseEntity.getBody());
    }

    @Test
    public void testUpdate_BadRequest() {
        // Act
        ResponseEntity<?> responseEntity = sessionController.update("abc", new SessionDto());

        // Assert
        assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testDelete() {
        // Arrange
        Session session = new Session();
        when(sessionService.getById(anyLong())).thenReturn(session);

        // Act
        ResponseEntity<?> responseEntity = sessionController.save("1");

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        verify(sessionService, times(1)).delete(anyLong());
    }

    @Test
    public void testDelete_NotFound() {
        // Arrange
        when(sessionService.getById(anyLong())).thenReturn(null);

        // Act
        ResponseEntity<?> responseEntity = sessionController.save("1");

        // Assert
        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testDelete_BadRequest() {
        // Act
        ResponseEntity<?> responseEntity = sessionController.save("abc");

        // Assert
        assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testParticipate() {
        // Act
        ResponseEntity<?> responseEntity = sessionController.participate("1", "1");

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        verify(sessionService, times(1)).participate(anyLong(), anyLong());
    }

    @Test
    public void testParticipate_BadRequest() {
        // Act
        ResponseEntity<?> responseEntity = sessionController.participate("abc", "1");

        // Assert
        assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testNoLongerParticipate() {
        // Act
        ResponseEntity<?> responseEntity = sessionController.noLongerParticipate("1", "1");

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        verify(sessionService, times(1)).noLongerParticipate(anyLong(), anyLong());
    }

    @Test
    public void testNoLongerParticipate_BadRequest() {
        // Act
        ResponseEntity<?> responseEntity = sessionController.noLongerParticipate("abc", "1");

        // Assert
        assertEquals(400, responseEntity.getStatusCodeValue());
    }
}
