package com.blog.ApiRestblog.informatorio.Controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import com.blog.ApiRestblog.informatorio.Model.ComentarioModel;
import com.blog.ApiRestblog.informatorio.Repository.ComentarioRepository;
import com.blog.ApiRestblog.informatorio.Servicio.ComentarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comentario")
public class ComentarioController {

    @Autowired
    ComentarioService comentarioService;

    @Autowired
    ComentarioRepository comentarioRepository;

    @PostMapping()
    public ComentarioModel guardarUsuario(@RequestBody ComentarioModel comentario) {
        comentario.setFechaDeCreacion(LocalDate.now());
        return this.comentarioService.guardarComentario(comentario);
    }

    @GetMapping()
    public ArrayList<ComentarioModel> obtenerComentarios() {
        return comentarioService.obtenerTodosLosComentarios();
    }
    /*
     * Consulta - Ver los comentarios de un POST, con la posibilidad de superar el
     * maximo de comentarios. Opcional a #1. Trae todos los comentarios, a excepcion
     * de que le pase un limite. Si escribo #2, me traera los 10 comentarios mas
     * nuevo recientemente.
     */

    @GetMapping("/cantidad")
    public ResponseEntity<?> buscarPorTituloDado(@RequestParam Integer limit) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(comentarioService.buscarComentariosBajoParametro(limit));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @PutMapping(path = "/{comentarioId}")
    public ResponseEntity<?> updateComentario(@PathVariable Long comentarioId,
            @RequestBody ComentarioModel comentario) {

        ComentarioModel comentarioExistente = ((Optional<ComentarioModel>) comentarioRepository.findById(comentarioId))
                .get();
        ;
        
        comentarioExistente.setComentario(comentario.getComentario());

        
        return new ResponseEntity<>(comentarioRepository.save(comentario), HttpStatus.OK);
    }    

    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.comentarioService.eliminarComentario(id);
        if(ok){
            return "Se eliminó el comentario con id " + id;
        }else {
            return "No pudo eliminar el comentario con id " + id;
        }
    }
}
