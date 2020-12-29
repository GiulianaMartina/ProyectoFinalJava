package com.blog.ApiRestblog.informatorio.Servicio;

import java.util.ArrayList;
import java.util.List;
import com.blog.ApiRestblog.informatorio.Model.ComentarioModel;
import com.blog.ApiRestblog.informatorio.Repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

    @Autowired
    ComentarioRepository comentarioRepository; 

    public ComentarioModel guardarComentario(ComentarioModel comentario){
        return comentarioRepository.save(comentario);
    }

    public ArrayList<ComentarioModel> obtenerTodosLosComentarios() {
        return (ArrayList<ComentarioModel>) comentarioRepository.findAll();
    }

    public List <ComentarioModel> buscarComentariosBajoParametro(Integer cantidad) throws Exception{
        try{
            List <ComentarioModel> comentarios = comentarioRepository.getComentarioPorLimite(cantidad);
            return comentarios;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public boolean eliminarComentario(Long id){
        try{
            comentarioRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
}

