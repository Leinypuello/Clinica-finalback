package dh.backend.clinica_odontologica.service;

import dh.backend.clinica_odontologica.dto.request.TurnosRequestDto;
import dh.backend.clinica_odontologica.dto.response.TurnoResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface ITurnoService {

    TurnoResponseDto registrar(TurnosRequestDto requestDto);

    TurnoResponseDto buscarPorId(Integer id);

    List<TurnoResponseDto> buscarTodos();
    void actualizarTurno(Integer id,TurnosRequestDto turno);
    void eliminarTurno(Integer id);

    List<TurnoResponseDto> buscarTurnoEntreFechas(LocalDate startDate, LocalDate endDate);
}
