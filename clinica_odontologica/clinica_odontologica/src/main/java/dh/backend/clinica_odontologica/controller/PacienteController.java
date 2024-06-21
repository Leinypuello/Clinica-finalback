package dh.backend.clinica_odontologica.controller;


import dh.backend.clinica_odontologica.entity.Paciente;
import dh.backend.clinica_odontologica.exception.BadRequestException;
import dh.backend.clinica_odontologica.exception.ResourceNotFoundException;
import dh.backend.clinica_odontologica.service.IPacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    public IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/save")
    public ResponseEntity<Paciente>  registrarPaciente(@RequestBody Paciente paciente) throws BadRequestException {

        Paciente pacienteARetornar = pacienteService.registrarPaciente(paciente);
        if (pacienteARetornar == null){
            return new ResponseEntity<Paciente>(pacienteARetornar, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Paciente>(pacienteARetornar, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Paciente>> buscarTodos(){
        List<Paciente> pacientes = pacienteService.buscarTodos();

        if(pacientes.isEmpty()){
            return new ResponseEntity<>(pacientes,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(pacientes,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente>>buscarPacientePorId(@PathVariable Integer id){
        Optional<Paciente> paciente= pacienteService.buscarPorId(id);
        if (paciente == null){
            return new ResponseEntity<>(paciente,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(paciente,HttpStatus.OK);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente){
        pacienteService.actualizarPaciente(paciente);
        return ResponseEntity.ok("Paciente Actualizado...");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarPaciente(@PathVariable Integer id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("paciente eliminado");
    }


}
