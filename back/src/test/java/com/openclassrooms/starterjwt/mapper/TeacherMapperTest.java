package com.openclassrooms.starterjwt.mapper;

import com.openclassrooms.starterjwt.dto.TeacherDto;
import com.openclassrooms.starterjwt.models.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TeacherMapperTest {

    private TeacherMapper teacherMapper;

    @BeforeEach
    public void setUp() {
        teacherMapper = Mappers.getMapper(TeacherMapper.class);
    }

    @Test
    public void testToEntity() {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(1L);
        teacherDto.setFirstName("John");
        teacherDto.setLastName("Doe");

        Teacher teacher = teacherMapper.toEntity(teacherDto);

        assertNotNull(teacher);
        assertEquals(teacherDto.getId(), teacher.getId());
        assertEquals(teacherDto.getFirstName(), teacher.getFirstName());
        assertEquals(teacherDto.getLastName(), teacher.getLastName());
    }

    @Test
    public void testToDto() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setFirstName("John");
        teacher.setLastName("Doe");

        TeacherDto teacherDto = teacherMapper.toDto(teacher);

        assertNotNull(teacherDto);
        assertEquals(teacher.getId(), teacherDto.getId());
        assertEquals(teacher.getFirstName(), teacherDto.getFirstName());
        assertEquals(teacher.getLastName(), teacherDto.getLastName());
    }

    @Test
    public void testToEntityList() {
        TeacherDto teacherDto1 = new TeacherDto();
        teacherDto1.setId(1L);
        teacherDto1.setFirstName("John");
        teacherDto1.setLastName("Doe");

        TeacherDto teacherDto2 = new TeacherDto();
        teacherDto2.setId(2L);
        teacherDto2.setFirstName("Jane");
        teacherDto2.setLastName("Smith");

        List<TeacherDto> dtoList = Arrays.asList(teacherDto1, teacherDto2);
        List<Teacher> teacherList = teacherMapper.toEntity(dtoList);

        assertNotNull(teacherList);
        assertEquals(2, teacherList.size());

        Teacher teacher1 = teacherList.get(0);
        assertEquals(teacherDto1.getId(), teacher1.getId());
        assertEquals(teacherDto1.getFirstName(), teacher1.getFirstName());
        assertEquals(teacherDto1.getLastName(), teacher1.getLastName());

        Teacher teacher2 = teacherList.get(1);
        assertEquals(teacherDto2.getId(), teacher2.getId());
        assertEquals(teacherDto2.getFirstName(), teacher2.getFirstName());
        assertEquals(teacherDto2.getLastName(), teacher2.getLastName());
    }

    @Test
    public void testToDtoList() {
        Teacher teacher1 = new Teacher();
        teacher1.setId(1L);
        teacher1.setFirstName("John");
        teacher1.setLastName("Doe");

        Teacher teacher2 = new Teacher();
        teacher2.setId(2L);
        teacher2.setFirstName("Jane");
        teacher2.setLastName("Smith");

        List<Teacher> teacherList = Arrays.asList(teacher1, teacher2);
        List<TeacherDto> dtoList = teacherMapper.toDto(teacherList);

        assertNotNull(dtoList);
        assertEquals(2, dtoList.size());

        TeacherDto teacherDto1 = dtoList.get(0);
        assertEquals(teacher1.getId(), teacherDto1.getId());
        assertEquals(teacher1.getFirstName(), teacherDto1.getFirstName());
        assertEquals(teacher1.getLastName(), teacherDto1.getLastName());

        TeacherDto teacherDto2 = dtoList.get(1);
        assertEquals(teacher2.getId(), teacherDto2.getId());
        assertEquals(teacher2.getFirstName(), teacherDto2.getFirstName());
        assertEquals(teacher2.getLastName(), teacherDto2.getLastName());
    }
}
