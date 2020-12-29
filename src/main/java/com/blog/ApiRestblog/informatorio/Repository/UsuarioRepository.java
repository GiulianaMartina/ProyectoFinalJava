package com.blog.ApiRestblog.informatorio.Repository;

import java.util.List;
import com.blog.ApiRestblog.informatorio.Model.UsuarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {
	
	UsuarioModel findByEmail(String nombre);

	List<UsuarioModel> findByCiudad(String ciudad);

}