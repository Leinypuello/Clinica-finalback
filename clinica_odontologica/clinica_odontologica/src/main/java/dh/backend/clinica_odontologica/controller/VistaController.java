package dh.backend.clinica_odontologica.controller;


import dh.backend.clinica_odontologica.entity.Odontologo;
import dh.backend.clinica_odontologica.entity.Paciente;
import dh.backend.clinica_odontologica.service.IOdontologoService;
import dh.backend.clinica_odontologica.service.IPacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/vista")
public class VistaController {

    private IPacienteService pacienteService;
    private IOdontologoService odontologoService;

    public VistaController(IPacienteService pacienteService, IOdontologoService odontologoService) {
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @GetMapping("/buscarPaciente")
    public String buscarPacientePorId(Model model, @RequestParam Integer id){
        Optional<Paciente> paciente = pacienteService.buscarPorId(id);
        model.addAttribute("especialidad", "Paciente");
        model.addAttribute("nombre", paciente.get().getNombre());
        model.addAttribute("apellido", paciente.get().getApellido());
        return "index";
    }

    @GetMapping("/buscarOdontologo")
    public String buscarOdontologoPorId(Model model, @RequestParam Integer id){
        Optional<Odontologo> odontologo = odontologoService.buscarUnOdontologo(id);
        model.addAttribute("especialidad", "odontologo");
        model.addAttribute("nombre", odontologo.get().getNombre());
        model.addAttribute("apellido", odontologo.get().getApellido());
        return "index";
    }
}
