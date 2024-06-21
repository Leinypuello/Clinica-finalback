package dh.backend.clinica_odontologica.service;

import dh.backend.clinica_odontologica.entity.Odontologo;
import dh.backend.clinica_odontologica.entity.Paciente;
import dh.backend.clinica_odontologica.service.impl.OdontologoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OdontologoServiceTest {

    private static Logger LOGGER = LoggerFactory.getLogger(PacienteServiceTest.class);

    @Autowired
    private OdontologoService odontologoService;

    private Odontologo odontologo;

/*
    @BeforeEach
    void setUp(){
        odontologo = new Odontologo();
        odontologo.setNumeroMatricula("102554");
        odontologo.setNombre("Leiny");
        odontologo.setApellido("Puello");


    }*/

    @Test
    void aguardarOdontologo(){
        odontologo = new Odontologo();
        odontologo.setNumeroMatricula("102554");
        odontologo.setNombre("Leiny");
        odontologo.setApellido("Puello");
        Odontologo odontologo1 = odontologoService.agregarOdontologo(odontologo);
        assertNotNull(odontologo1);
    }

    @Test
    void buscarOdontoldoByID(){
        Integer id = 1;
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarUnOdontologo(id);
        Odontologo odontologo1 = odontologoEncontrado.get();
        assertEquals(id,odontologo1.getId());
    }

    @Test
    @DisplayName("Testear busqueda todos los pacientes")
    void ctestBusquedaTodos() {
        List<Odontologo> odontologos = odontologoService.buscarTodosOdontologos();
        assertTrue(odontologos.size()!=0);

    }

}
