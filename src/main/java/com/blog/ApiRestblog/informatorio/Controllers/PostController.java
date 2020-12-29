package com.blog.ApiRestblog.informatorio.Controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import com.blog.ApiRestblog.informatorio.DataTransferObject.ComentarioDTO;
import com.blog.ApiRestblog.informatorio.Model.ComentarioModel;
import com.blog.ApiRestblog.informatorio.Model.PostModel;
import com.blog.ApiRestblog.informatorio.Model.UsuarioModel;
import com.blog.ApiRestblog.informatorio.Repository.PostRepository;
import com.blog.ApiRestblog.informatorio.Repository.UsuarioRepository;
import com.blog.ApiRestblog.informatorio.Servicio.ComentarioService;
import com.blog.ApiRestblog.informatorio.Servicio.PostService;

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
@RequestMapping("/api/v1/post")

public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ComentarioService comentarioService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping()
    public PostModel guardarNuevoPost(@RequestBody PostModel post) {
        post.setFechaDeCreacion(LocalDate.now());
        return this.postService.guardarPost(post);
    }
     //POST Crear un COMMENT
     @PostMapping("/{id_post}/comentario")
     public <ComentarioDto> ResponseEntity<?> crearComentario(@PathVariable Long id_post,
             @RequestBody ComentarioDto comentarioDto) {
        
        PostModel post = postRepository.getOne(id_post);

        UsuarioModel usuario = usuarioRepository.findByEmail(((ComentarioDTO) comentarioDto).getAutor());
 
        ComentarioModel comentario = new ComentarioModel();
        comentario.setComentario(((ComentarioModel) comentarioDto).getComentario());
        comentario.setAutor(usuario.getEmail());
        comentario.setFechaDeCreacion(LocalDate.now());
        
        post.agregarComentario(comentario);
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioService.guardarComentario(comentario));
    }

    @GetMapping()
    public ArrayList<PostModel> obtenerPostsArrayList(){
        return postService.obtenerTodosLosPosteos();
    }

    /*
    CONSULTA - OBTENER TODOS LOS POST QUE CONTENGA UNA PALABRA DADA EN EL TÍTULO
    */

    @GetMapping("/titulo")
    public ResponseEntity<?> buscarPorTituloDado(@RequestParam String nombre){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(postService.buscarPorTitulo(nombre));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/estado")
    public ResponseEntity<?> buscarPorEstadoPublicacion(@RequestParam Boolean publicado){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(postService.buscarPorPublicado(publicado));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    /*
    TRAER TODOS LOS POST SIN PUBLICAR
    */

    @PutMapping(path = "/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId,@RequestBody PostModel post){

        PostModel postExistente = postRepository.getOne(postId);
        postExistente.setTitulo(post.getTitulo());
        postExistente.setContenido(post.getContenido());
        postExistente.setDescripcion(post.getDescripcion());
        postExistente.setPublicado(post.isPublicado());
        
        return new ResponseEntity<>(postRepository.save(post), HttpStatus.OK);
    }    

    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.postService.eliminarPost(id);
        if(ok){
            return "Se eliminó el post con id " + id;
        }else {
            return "No pudo eliminar el post con id " + id;
        }
    }
    
}

