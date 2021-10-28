package br.com.joelma.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joelma.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

}
