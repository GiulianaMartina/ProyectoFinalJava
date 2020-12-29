package com.blog.ApiRestblog.informatorio.Repository;

import com.blog.ApiRestblog.informatorio.Model.UsuarioModel;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtroUsuarioRepository extends JpaRepository<UsuarioModel, Long>{

    List<UsuarioModel> findByFechaDeCreacionAfter(LocalDate fechaDeCreacion);

}
