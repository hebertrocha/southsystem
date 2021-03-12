package br.com.southsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.southsystem.model.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
	
	@Query(value = "select assunto,\r\n" + 
			"       sum(qtdnao) as qtdnao,\r\n" + 
			"       sum(qtdsim) as qtdsim\r\n" + 
			"from(	   \r\n" + 
			"select p.assunto,\r\n" + 
			"       0 as qtdnao,\r\n" + 
			"       1 as qtdsim\r\n" + 
			"  from pauta p,\r\n" + 
			"       voto v\r\n" + 
			" where p.id = v.pauta_id\r\n" + 
			"   and p.id = :id\r\n" + 
			"   and v.opcao = 1\r\n" + 
			"Union all \r\n" + 
			"select p.assunto,\r\n" + 
			"       1 as qtdnao,\r\n" + 
			"       0 as qtdsim\r\n" + 
			"  from pauta p,\r\n" + 
			"       voto v\r\n" + 
			" where p.id = v.pauta_id\r\n" + 
			"   and p.id = :id\r\n" + 
			"   and v.opcao = 2)\r\n" + 
			"   group by assunto", nativeQuery = true)
	List<Object[]> findByResultadoVotacao(Long id);

}
