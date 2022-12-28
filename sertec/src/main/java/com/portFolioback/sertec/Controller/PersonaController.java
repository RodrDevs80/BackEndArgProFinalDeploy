
package com.portFolioback.sertec.Controller;


import com.portFolioback.sertec.Dto.DtoPersona;
import com.portFolioback.sertec.Entity.Persona;
import com.portFolioback.sertec.Security.Controller.Mensaje;
import com.portFolioback.sertec.Service.ImpPersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
//@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {
    @Autowired ImpPersonaService impPersonaService;
    
   @GetMapping("/lista")
    public ResponseEntity<List<Persona>> List() {
        List<Persona> list = impPersonaService.List();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id){
        if(!impPersonaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Persona persona = impPersonaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoPersona dtoper) {
        if (StringUtils.isBlank(dtoper.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if (impPersonaService.existsByNombre(dtoper.getNombre())) {
            return new ResponseEntity(new Mensaje("Esa persona existe"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = new Persona(dtoper.getNombre(), dtoper.getApellido(),dtoper.getDescripcion(),dtoper.getImg());

        impPersonaService.save(persona);

        return new ResponseEntity(new Mensaje("Se creo una nueva persona correctamente"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoPersona dtoper) {
        if (!impPersonaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if (impPersonaService.existsByNombre(dtoper.getNombre()) && impPersonaService.getByNombre(dtoper.getNombre())
                .get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa persona ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoper.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Persona experiencia = impPersonaService.getOne(id).get();
        
        experiencia.setNombre(dtoper.getNombre());
        experiencia.setApellido(dtoper.getApellido());
        experiencia.setDescripcion(dtoper.getDescripcion());
        experiencia.setImg(dtoper.getImg());

        impPersonaService.save(experiencia);
        return new ResponseEntity(new Mensaje("Se modifico la persona correctamente"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {

        if (!impPersonaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        impPersonaService.delete(id);
        return new ResponseEntity(new Mensaje("Se elimino la experiencia"), HttpStatus.OK);
    }

}
