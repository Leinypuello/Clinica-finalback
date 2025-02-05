package dh.backend.clinica_odontologica.service.impl;

import dh.backend.clinica_odontologica.dto.request.TurnosRequestDto;
import dh.backend.clinica_odontologica.dto.response.OdontologoResponseDto;
import dh.backend.clinica_odontologica.dto.response.PacienteResponseDto;
import dh.backend.clinica_odontologica.dto.response.TurnoResponseDto;
import dh.backend.clinica_odontologica.entity.Odontologo;
import dh.backend.clinica_odontologica.entity.Paciente;
import dh.backend.clinica_odontologica.entity.Turno;
import dh.backend.clinica_odontologica.repository.IOdontologoRepository;
import dh.backend.clinica_odontologica.repository.IPacienteRepository;
import dh.backend.clinica_odontologica.repository.ITurnoRepository;
import dh.backend.clinica_odontologica.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    private IOdontologoRepository odontologoRepository;

    private IPacienteRepository pacienteRepository;

    private ITurnoRepository turnoRepository;

    private ModelMapper modelMapper;


    public TurnoService(IOdontologoRepository odontologoRepository, IPacienteRepository pacienteRepository, ITurnoRepository turnoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.pacienteRepository = pacienteRepository;
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TurnoResponseDto registrar(TurnosRequestDto turnosRequestDto) {
        Optional<Paciente> paciente = pacienteRepository.findById(turnosRequestDto.getPacienteId());
        Optional<Odontologo> odontologo = odontologoRepository.findById(turnosRequestDto.getOdontologoId());
        Turno turnoARegistrar = new Turno();
        Turno turnoGuardado = null;
        TurnoResponseDto turnoADevolver = null;
        if (paciente.isPresent() && odontologo.isPresent()) {
            turnoARegistrar.setOdontologo(odontologo.get());
            turnoARegistrar.setPaciente(paciente.get());
            turnoARegistrar.setFecha(LocalDate.parse(turnosRequestDto.getFecha()));
            turnoGuardado = turnoRepository.save(turnoARegistrar);

            turnoADevolver = mapToResponseDto(turnoGuardado);
        }
        return turnoADevolver;
    }

    @Override
    public TurnoResponseDto buscarPorId(Integer id) {
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        if (turnoOptional.isPresent()) {
            Turno turnoEncontrado = turnoOptional.get();
            TurnoResponseDto turnoADevolver = mapToResponseDto(turnoEncontrado);
            return turnoADevolver;
        }
        return null;
    }

    @Override
    public List<TurnoResponseDto> buscarTodos() {
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoResponseDto> turnosADevolver = new ArrayList<>();
        TurnoResponseDto turnoAuxiliar = null;
        for (Turno turno : turnos) {
            turnoAuxiliar = mapToResponseDto(turno);
            turnosADevolver.add(turnoAuxiliar);
        }
        return turnosADevolver;
    }

    @Override
    public void actualizarTurno(Integer id, TurnosRequestDto turnosRequestDto) {
        Optional<Paciente> paciente = pacienteRepository.findById(turnosRequestDto.getPacienteId());
        Optional<Odontologo> odontologo = odontologoRepository.findById(turnosRequestDto.getOdontologoId());
        Optional<Turno> turno = turnoRepository.findById(id);
        Turno turnoAModificar = new Turno();
        if (paciente.isPresent() && odontologo.isPresent() && turno.isPresent()) {
            turnoAModificar.setId(id);
            turnoAModificar.setOdontologo(odontologo.get());
            turnoAModificar.setPaciente(paciente.get());
            turnoAModificar.setFecha(LocalDate.parse(turnosRequestDto.getFecha()));
            turnoRepository.save(turnoAModificar);

        }
    }
    @Override
    public void eliminarTurno (Integer id){
        turnoRepository.deleteById(id);
    }

    @Override
    public List<TurnoResponseDto> buscarTurnoEntreFechas(LocalDate startDate, LocalDate endDate) {
        List<Turno> listadoTurnos = turnoRepository.buscarTurnoEntreFechas(startDate, endDate);
        List<TurnoResponseDto> listadoARetornar = new ArrayList<>();
        TurnoResponseDto turnoAuxiliar = null;
        for (Turno turno: listadoTurnos){
            turnoAuxiliar = mapToResponseDto(turno);
            listadoARetornar.add(turnoAuxiliar);
        }
        return listadoARetornar;
    }


    private TurnoResponseDto mapToResponseDto(Turno turno){
        TurnoResponseDto turnoResponseDto = modelMapper.map(turno, TurnoResponseDto.class);
        turnoResponseDto.setOdontologo(modelMapper.map(turno.getOdontologo(), OdontologoResponseDto.class));
        turnoResponseDto.setPaciente(modelMapper.map(turno.getPaciente(), PacienteResponseDto.class));
        return  turnoResponseDto;
    }
}
