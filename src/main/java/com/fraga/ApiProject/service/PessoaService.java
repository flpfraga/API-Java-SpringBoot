package com.fraga.ApiProject.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fraga.ApiProject.controller.PessoaController;
import com.fraga.ApiProject.converter.DozerConverter;
import com.fraga.ApiProject.data.model.Pessoa;
import com.fraga.ApiProject.data.vo.PessoaVO;
import com.fraga.ApiProject.exception.RequiredObjectIsNullException;
import com.fraga.ApiProject.exception.ResourceNotFoundException;
import com.fraga.ApiProject.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	public List<PessoaVO> findAll() {

		List<PessoaVO> pessoas = DozerConverter.parseList(repository.findAll(), PessoaVO.class);
		pessoas.stream()
				.forEach(p -> p.add(linkTo(methodOn(PessoaController.class).findById(p.getKey())).withSelfRel()));
		return pessoas;
	}

	public PessoaVO findById(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records matches for this ID!"));
		PessoaVO pessoa = DozerConverter.parseObject(entity, PessoaVO.class);

		pessoa.add(linkTo(methodOn(PessoaController.class).findById(pessoa.getKey())).withSelfRel());
		return pessoa;
	}

	public PessoaVO create(PessoaVO pessoaVo) {

		if (pessoaVo == null) {
			throw new RequiredObjectIsNullException("It is not allowed to persist a null object!");
		}

		var entity = DozerConverter.parseObject(pessoaVo, Pessoa.class);

		PessoaVO pessoa = DozerConverter.parseObject(repository.save(entity), PessoaVO.class);

		pessoa.add(linkTo(methodOn(PessoaController.class).findById(pessoa.getKey())).withSelfRel());
		return pessoa;
	}

	public void delete(Long id) {
		var entity = findById(id);

		repository.delete(DozerConverter.parseObject(entity, Pessoa.class));
	}

	public PessoaVO update(PessoaVO pessoaVo, Long id) {

		if (pessoaVo == null) {
			throw new RequiredObjectIsNullException("It is not allowed to persist a null object!");
		}
		var entity = findById(id);
		System.out.println("entity update " + entity);
		entity.setNome(pessoaVo.getNome());
		entity.setNascimento(pessoaVo.getNascimento());
		System.out.println("entity pos update " + entity);

		repository.save(DozerConverter.parseObject(entity, Pessoa.class));

		entity.add(linkTo(methodOn(PessoaController.class).findById(entity.getKey())).withSelfRel());
		return entity;

	}

}
