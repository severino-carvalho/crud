package br.edu.ifrn.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifrn.crud.domains.Profissao;

public interface ProfissaoRepository extends JpaRepository<Profissao, Integer> {

    @Query("select p from Profissao p where p.nome like %:nome%")
    List<Profissao> findByNome(@Param("nome") String nome);

}
