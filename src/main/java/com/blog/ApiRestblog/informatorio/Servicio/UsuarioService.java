package com.blog.ApiRestblog.informatorio.Servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.blog.ApiRestblog.informatorio.DataTransferObject.UsuarioDTO;
import com.blog.ApiRestblog.informatorio.Model.UsuarioModel;
import com.blog.ApiRestblog.informatorio.Repository.OtroUsuarioRepository;
import com.blog.ApiRestblog.informatorio.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository; 

    OtroUsuarioRepository otroUsuarioRepository;
    
    public UsuarioDTO guardarUsuario(UsuarioModel usuario) {
        
        return new UsuarioDTO(usuarioRepository.save(usuario));
    }
    
    public ArrayList<UsuarioModel> obtenerTodosUsuarios() {
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

    public Optional<UsuarioModel> obtenerPorId(Long id){
        return usuarioRepository.findById(id);
    }
    
    public UsuarioModel getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    
    public List <UsuarioModel> buscar(String ciudad) throws Exception{
        try{
            List <UsuarioModel> usuarios = usuarioRepository.findByCiudad(ciudad);
            return usuarios;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public boolean eliminarUsuario(Long id){
        try{
            usuarioRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }

	public Optional<UsuarioModel> getUsuario(Long id) {
        return usuarioRepository.findById(id);
	}

	public UsuarioModel getUno(Long usuarioId) {
        
        return otroUsuarioRepository.getOne(usuarioId);
	}

	public UsuarioDTO actualizarUsuario(UsuarioModel usuarioAdaptado) {
        
        return new UsuarioDTO(otroUsuarioRepository.save(usuarioAdaptado));
	}	
}

