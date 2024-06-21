package dh.backend.clinica_odontologica.controller;

import dh.backend.clinica_odontologica.entity.Odontologo;
import dh.backend.clinica_odontologica.exception.BadRequestException;
import dh.backend.clinica_odontologica.exception.ResourceNotFoundException;
import dh.backend.clinica_odontologica.service.IOdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {

    private IOdontologoService odontologoService;

    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Odontologo>> listar(){
        List<Odontologo> odontologos = odontologoService.buscarTodosOdontologos();
        if (odontologos != null){
            return new ResponseEntity<>(odontologos,HttpStatus.OK);
        }
        return new ResponseEntity<>(odontologos,HttpStatus.OK);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarById(@PathVariable("id") Integer id){
        Optional<Odontologo> odontologo = odontologoService.buscarUnOdontologo(id);
        if (odontologo!= null){
            return new ResponseEntity<>(odontologo,HttpStatus.OK);
        }
        return new ResponseEntity<>(odontologo,HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/save")
    public ResponseEntity<Odontologo> save(@RequestBody Odontologo odontologo) throws BadRequestException {
        Odontologo nuevoOdontologo = odontologoService.agregarOdontologo(odontologo);
        if (nuevoOdontologo != null){
            return new ResponseEntity<>(odontologo, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(odontologo,HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/actualizar")
    public ResponseEntity<Odontologo> update(@RequestBody Odontologo odontologo){
        odontologoService.actualizar(odontologo);
        return new ResponseEntity<>(odontologo,HttpStatus.OK);
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Optional<Odontologo>> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Optional<Odontologo> buscarOdontologo = odontologoService.buscarUnOdontologo(id);
        if (buscarOdontologo!=null){
            odontologoService.eliminar(id);
           return new  ResponseEntity<>(buscarOdontologo,HttpStatus.OK);
        }

        return new ResponseEntity<>(buscarOdontologo,HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/apellido/{apellido}")
    public ResponseEntity<List<Odontologo>> buscarPorApellido(@PathVariable String apellido){
        List<Odontologo> listaOdontologos =odontologoService.buscarPorApellido(apellido);
        if(listaOdontologos.size()>0){
            return ResponseEntity.ok(listaOdontologos);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Odontologo>> buscarTodos(@PathVariable String nombre){
        return ResponseEntity.ok(odontologoService.buscarPorNombre(nombre));
    }
}
