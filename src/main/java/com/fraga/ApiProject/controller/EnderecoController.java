package com.fraga.ApiProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fraga.ApiProject.data.vo.EnderecoVO;
import com.fraga.ApiProject.service.EnderecoService;

@RestController
@RequestMapping("/api/endereco/v1")
public class EnderecoController {

	@Autowired
	private EnderecoService service;

	@GetMapping("findAll/{pessoa_id}")
	public ResponseEntity<List<EnderecoVO>> findAll(@PathVariable("pessoa_id") Long pessoa_id) {

		return ResponseEntity.ok(service.findAll(pessoa_id));

	}

	@GetMapping("/{id}")
	public ResponseEntity<EnderecoVO> findById(@PathVariable("id") Long id) {

		return ResponseEntity.ok(service.findById(id));
	}

	@PostMapping("/{pessoa_id}")
	public ResponseEntity<EnderecoVO> create(@PathVariable("pessoa_id") Long pessoa_id, @RequestBody EnderecoVO enderecoVo) {
		System.out.println("post controller ender");
		return ResponseEntity.ok(service.create(pessoa_id, enderecoVo));
	}
	
	
	@PatchMapping("end_principal/{id}/{pessoa_id}")
	public ResponseEntity<EnderecoVO> updateEnderecoPrincipal(@PathVariable("id") Long id, @PathVariable("pessoa_id") Long pessoa_id){
		
		return ResponseEntity.ok(service.updateEnderecoPrincipal(id, pessoa_id));
	}

}
