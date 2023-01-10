package com.fraga.ApiProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fraga.ApiProject.data.vo.PessoaVO;
import com.fraga.ApiProject.service.PessoaService;

@RestController
@RequestMapping("/api/pessoa/v1")
public class PessoaController {

	@Autowired
	private PessoaService service;

	@GetMapping
	public ResponseEntity<List<PessoaVO>> findAll() {

		return ResponseEntity.ok(service.findAll());

	}

	@GetMapping("/{id}")
	public ResponseEntity<PessoaVO> findById(@PathVariable("id") Long id) {

		return ResponseEntity.ok(service.findById(id));
	}

	@PostMapping
	public ResponseEntity<PessoaVO> create(@RequestBody PessoaVO pessoaVo) {
		
		return ResponseEntity.ok(service.create(pessoaVo));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PessoaVO> update(@PathVariable("id") Long id, @RequestBody PessoaVO pessoaVo) {
		
		return ResponseEntity.ok(service.update(pessoaVo,id));
	}
	

}
