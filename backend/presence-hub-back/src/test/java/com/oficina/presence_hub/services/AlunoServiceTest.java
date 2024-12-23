package com.oficina.presence_hub.services;

import com.oficina.presence_hub.dtos.AlunoDTO;
import com.oficina.presence_hub.entities.Aluno;
import com.oficina.presence_hub.entities.Certificado;
import com.oficina.presence_hub.entities.Participacao;
import com.oficina.presence_hub.mappers.AlunoMapper;
import com.oficina.presence_hub.mappers.CertificadoMapper;
import com.oficina.presence_hub.mappers.ParticipacaoMapper;
import com.oficina.presence_hub.repositories.AlunoRepository;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.TestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private AlunoMapper alunoMapper;

    @Mock
    ParticipacaoMapper participacaoMapper;

    @Mock
    CertificadoMapper certificadoMapper;

    @InjectMocks
    private AlunoService alunoService;

    private Aluno aluno;
    private AlunoDTO alunoDTO;

    @BeforeEach
    void setUp() {
        aluno = TestUtils.buildAluno();

        alunoDTO = new AlunoDTO(1L, "Test Aluno", "test@example.com", 20, "10th Grade", "Test School", "1234567890", "Test City", "Test State", "Test Parent", "0987654321", "12345", "Active", null, "No observations", Collections.emptyList(), Collections.emptyList());
    }

    @Test
    void createAlunoTest() {
        when(alunoMapper.toAluno(any(AlunoDTO.class))).thenReturn(aluno);
        when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);
        when(alunoMapper.toAlunoDTO(any(Aluno.class))).thenReturn(alunoDTO);

        AlunoDTO result = alunoService.createAluno(alunoDTO);

        assertNotNull(result);
        assertEquals(alunoDTO.id(), result.id());
        verify(alunoRepository, times(1)).save(any(Aluno.class));
    }

    @Test
    void getAllAlunosTest() {
        when(alunoRepository.findAll()).thenReturn(Collections.singletonList(aluno));
        when(alunoMapper.toAlunoDTO(anyList())).thenReturn(Collections.singletonList(alunoDTO));

        var result = alunoService.getAllAlunos();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(alunoRepository, times(1)).findAll();
    }

    @Test
    void getAlunoByIdTest() {
        when(alunoRepository.findById(anyLong())).thenReturn(Optional.of(aluno));
        when(alunoMapper.toAlunoDTO(any(Aluno.class))).thenReturn(alunoDTO);
        Mockito.lenient().when(participacaoMapper.toParticipacaoDTO(any(Participacao.class))).thenReturn(TestUtils.buildParticipacaoDTO());
        Mockito.lenient().when(certificadoMapper.toCertificadoDTO(any(Certificado.class))).thenReturn(TestUtils.buildCertificadoDTO());

        AlunoDTO result = alunoService.getAlunoById(1L);

        assertNotNull(result);
        assertEquals(alunoDTO.id(), result.id());
        verify(alunoRepository, times(1)).findById(anyLong());
    }

    @Test
    void updateAlunoTest() {
        when(alunoRepository.findById(anyLong())).thenReturn(Optional.of(aluno));
        when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);
        when(alunoMapper.toAlunoDTO(any(Aluno.class))).thenReturn(alunoDTO);

        AlunoDTO result = alunoService.updateAluno(1L, alunoDTO);

        assertNotNull(result);
        assertEquals(alunoDTO.id(), result.id());
        verify(alunoRepository, times(1)).findById(anyLong());
        verify(alunoRepository, times(1)).save(any(Aluno.class));
    }

    @Test
    void deleteAlunoTest() {
        when(alunoRepository.findById(anyLong())).thenReturn(Optional.of(aluno));
        doNothing().when(alunoRepository).delete(any(Aluno.class));

        alunoService.deleteAluno(1L);

        verify(alunoRepository, times(1)).findById(anyLong());
        verify(alunoRepository, times(1)).delete(any(Aluno.class));
    }

    @Test
    void createAlunoExceptionTest() {
        when(alunoMapper.toAluno(any(AlunoDTO.class))).thenThrow(new RuntimeException("Error creating Aluno"));

        assertThrows(RuntimeException.class, () -> alunoService.createAluno(alunoDTO));
    }

    @Test
    void getAlunoByIdExceptionTest() {
        when(alunoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> alunoService.getAlunoById(1L));
    }

    @Test
    void updateAlunoExceptionTest() {
        when(alunoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> alunoService.updateAluno(1L, alunoDTO));
    }

    @Test
    void deleteAlunoExceptionTest() {
        when(alunoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> alunoService.deleteAluno(1L));
    }
}