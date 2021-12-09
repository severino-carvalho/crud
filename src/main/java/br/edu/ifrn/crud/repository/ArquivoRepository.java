package br.edu.ifrn.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifrn.crud.domains.Arquivo;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {

    @Query("select a from Arquivo a where a.nomeArquivo like %:nome%")
    List<Arquivo> findByNome(@Param("nome") String nome);

}
