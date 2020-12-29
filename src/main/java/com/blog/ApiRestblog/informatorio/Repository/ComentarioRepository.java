package com.blog.ApiRestblog.informatorio.Repository;

import java.util.List;
import com.blog.ApiRestblog.informatorio.Model.ComentarioModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ComentarioRepository extends CrudRepository<ComentarioModel,Long>{

    @Query(nativeQuery = true, value = "select * from Comentarios s ORDER BY s.fecha_de_creacion desc LIMIT ?1")
List<ComentarioModel> getComentarioPorLimite(@Param("limit") Integer limit);

}

