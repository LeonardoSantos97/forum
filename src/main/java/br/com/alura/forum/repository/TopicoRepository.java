package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.forum.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{

	// Desta forma o Spring Data monta as querys automaticamente
	List<Topico> findByCursoNome(String nomeCurso);
	
	
	/* Caso não queira usar o padrão do Spring Data JPA 
	 * pode criar o método passando a query com o @Query
	 * e passar a assinatura @Param
	 */
	@Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
	List<Topico> carregarPorNomeDoCurso(@Param("nomeCurso")String nomeCurso);

}
