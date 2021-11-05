package br.com.joelma.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joelma.model.Convidado;


public interface ConvidadoRepository extends JpaRepository<Convidado, String> {

	Convidado findByNomeConvidado(String nomeConvidado);

}
