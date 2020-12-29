package com.blog.ApiRestblog.informatorio.Repository;

import java.util.List;
import com.blog.ApiRestblog.informatorio.Model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostModel, Long> {

    List<PostModel> findByTitulo(String titulo);

    List<PostModel> findByPublicado(Boolean publicado);

}


