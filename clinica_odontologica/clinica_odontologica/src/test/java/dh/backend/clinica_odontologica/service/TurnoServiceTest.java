package dh.backend.clinica_odontologica.service;

import dh.backend.clinica_odontologica.dto.request.TurnosRequestDto;
import dh.backend.clinica_odontologica.dto.response.TurnoResponseDto;
import dh.backend.clinica_odontologica.entity.Domicilio;
import dh.backend.clinica_odontologica.entity.Odontologo;
import dh.backend.clinica_odontologica.entity.Paciente;
import dh.backend.clinica_odontologica.entity.Turno;
import dh.backend.clinica_odontologica.repository.IOdontologoRepository;
import dh.backend.clinica_odontologica.repository.IPacienteRepository;
import dh.backend.clinica_odontologica.service.impl.OdontologoService;
import dh.backend.clinica_odontologica.service.impl.PacienteService;
import dh.backend.clinica_odontologica.service.impl.TurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TurnoServiceTest {


    private static Logger LOGGER = LoggerFactory.getLogger(PacienteServiceTest.class);

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    private Turno turno;
    Paciente paciente = new Paciente();
    Odontologo odontologo = new Odontologo();
    TurnosRequestDto turnosRequestDto = new TurnosRequestDto();

    @BeforeEach
    void setUp(){

        paciente.setNombre("Menganito");
        paciente.setApellido("Cosme");
        paciente.setDni("464646");
        paciente.setFechaIngreso(LocalDate.of(2024,01,12));
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Calle falsa");
        domicilio.setNumero(123);
        domicilio.setLocalidad("San Pedro");
        domicilio.setProvincia("Jujuy");
        paciente.setDomicilio(domicilio);
        pacienteService.registrarPaciente(paciente);


        odontologo.setNumeroMatricula("102554");
        odontologo.setNombre("Leiny");
        odontologo.setApellido("Puello");
        odontologoService.agregarOdontologo(odontologo);


        turnosRequestDto.setPacienteId(paciente.getId());
        turnosRequestDto.setOdontologoId(odontologo.getId());
        turnosRequestDto.setFecha("2024-05-30");
    }

    @Test
    @DisplayName("A")
    void testTurnoGuardado(){
        TurnoResponseDto turnoResponseDto = turnoService.registrar(turnosRequestDto);
        assertNotNull(turnoResponseDto);
    }

}
