package br.edu.ifrn.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifrn.crud.domains.CursoFormacao;

public interface CursoFormacaoRepository extends JpaRepository<CursoFormacao, Integer> {

    @Query("select cf from CursoFormacao cf where cf.nome like %:nome%")
    List<CursoFormacao> findByNome(@Param("nome") String nome);

}
