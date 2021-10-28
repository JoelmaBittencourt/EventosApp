package br.com.joelma.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joelma.model.Evento;
import br.com.joelma.repository.EventoRepository;





@RestController
@RequestMapping("/evento")
@CrossOrigin("*")
	public class EventoController {

		@Autowired
		private EventoRepository er;
		
		@GetMapping("/todos")
		public ResponseEntity<List<Evento>> pegarTodos() {
			return ResponseEntity.ok(er.findAll());
		}

		@GetMapping("/{id_evento}")
		public ResponseEntity<Evento> pegarEventoPorId(@PathVariable(value = "id_usuario") Long Id) {
			return er.findById(Id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
		}
		@GetMapping("/Nome/{nome}")
		public ResponseEntity<List<Evento>> pegarPorNome(@PathVariable String nome) {
			return ResponseEntity.ok(er.findAllByNomeContainingIgnoreCase(nome));

		}

		@PostMapping("/salvar")
		public ResponseEntity<Object> salvarUsuario(@Valid @RequestBody Evento novoEvento) {
			Optional<Object> objetoCadastrado = service.cadastrarEvento(novoEvento);

			if (objetoCadastrado.isPresent()) {
				return ResponseEntity.status(201).body(objetoCadastrado.get());
			} else {
				return ResponseEntity.status(400).build();
			}

	/*	}

		@PostMapping("/logar")
	    public ResponseEntity<UserLogin> Autentication(@RequestBody Optional<UserLogin> user) {
	        return service.Logar(user).map(resp -> ResponseEntity.ok(resp))
	                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	    }

		@SuppressWarnings("rawtypes")
		@PutMapping("/alterar")
		public ResponseEntity alterarUsuario(@Valid @RequestBody Evento eventoParaSerAlterado) {
			Optional objetoAlterado = service.alterarEvento(eventoParaSerAlterado);

			if (objetoAlterado.isPresent()) {
				return ResponseEntity.status(201).body(objetoAlterado.get());
			} else {
				return ResponseEntity.status(400).build();
			}*/
		}

		@DeleteMapping("/deletar/{id_usuario}")
		public ResponseEntity<Object> deletarUsuario(@PathVariable(value = "id_usuario") Long id) {
			return er.findById(id).map(resp -> {
				er.deleteById(id);
				return ResponseEntity.ok().build();
			}).orElseGet(() -> {
				return ResponseEntity.notFound().build();
			});
		}

	}

