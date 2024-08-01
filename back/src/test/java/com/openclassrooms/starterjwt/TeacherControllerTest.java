package com.openclassrooms.starterjwt;

import com.openclassrooms.starterjwt.controllers.TeacherController;
import com.openclassrooms.starterjwt.mapper.TeacherMapper;
import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.services.TeacherService;
import com.openclassrooms.starterjwt.dto.TeacherDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class TeacherControllerTest {

    @Mock
    private TeacherMapper teacherMapper;

    @Mock
    private TeacherService teacherService;

    @InjectMocks
    private TeacherController teacherController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        // Arrange
        Teacher teacher = new Teacher();
        TeacherDto teacherDto = new TeacherDto();
        when(teacherService.findById(anyLong())).thenReturn(teacher);
        when(teacherMapper.toDto(teacher)).thenReturn(teacherDto);

        // Act
        ResponseEntity<?> responseEntity = teacherController.findById("1");

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(teacherDto, responseEntity.getBody());
    }

    @Test
    public void testFindById_NotFound() {
        // Arrange
        when(teacherService.findById(anyLong())).thenReturn(null);

        // Act
        ResponseEntity<?> responseEntity = teacherController.findById("1");

        // Assert
        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testFindById_BadRequest() {
        // Act
        ResponseEntity<?> responseEntity = teacherController.findById("abc");

        // Assert
        assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testFindAll() {
        // Arrange
        List<Teacher> teachers = Collections.singletonList(new Teacher());
        List<TeacherDto> teacherDtos = Collections.singletonList(new TeacherDto());
        when(teacherService.findAll()).thenReturn(teachers);
        when(teacherMapper.toDto(teachers)).thenReturn(teacherDtos);

        // Act
        ResponseEntity<?> responseEntity = teacherController.findAll();

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(teacherDtos, responseEntity.getBody());
    }
}
