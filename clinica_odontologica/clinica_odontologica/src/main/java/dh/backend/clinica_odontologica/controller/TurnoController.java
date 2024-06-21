package dh.backend.clinica_odontologica.controller;

import dh.backend.clinica_odontologica.dto.request.TurnosRequestDto;
import dh.backend.clinica_odontologica.dto.response.TurnoResponseDto;
import dh.backend.clinica_odontologica.exception.BadRequestException;
import dh.backend.clinica_odontologica.exception.ResourceNotFoundException;
import dh.backend.clinica_odontologica.service.ITurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {

    private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TurnoResponseDto>> listarTurnos(){
        return  ResponseEntity.ok(turnoService.buscarTodos());
    }

    @PostMapping("/save")
    public ResponseEntity<TurnoResponseDto> save(@RequestBody TurnosRequestDto turno) throws BadRequestException {
        TurnoResponseDto nuevoTurno = turnoService.registrar(turno);
        if (nuevoTurno == null){
            return new ResponseEntity<>(nuevoTurno,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(nuevoTurno,HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id ,@RequestBody TurnosRequestDto turno){
        turnoService.actualizarTurno(id,turno);
        return ResponseEntity.ok("Turno modificado...");
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<TurnoResponseDto> listarById(@PathVariable Integer id){
        TurnoResponseDto turnoResponseDto = turnoService.buscarPorId(id);
        return new  ResponseEntity<>(turnoResponseDto,HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<TurnoResponseDto> delete(@PathVariable Integer id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @GetMapping("/fechas")
    public ResponseEntity<List<TurnoResponseDto>> buscarEntreFechas(@RequestParam String inicio, @RequestParam String fin){
        LocalDate fechaInicio = LocalDate.parse(inicio, formatter);
        LocalDate fechaFinal = LocalDate.parse(fin, formatter);

        return ResponseEntity.ok(turnoService.buscarTurnoEntreFechas(fechaInicio, fechaFinal));
    }

}
