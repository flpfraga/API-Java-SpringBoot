package com.fraga.ApiProject.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.fraga.ApiProject.controller.EnderecoController;
import com.fraga.ApiProject.converter.DozerConverter;
import com.fraga.ApiProject.data.model.Endereco;
import com.fraga.ApiProject.data.model.Pessoa;
import com.fraga.ApiProject.data.vo.EnderecoVO;
import com.fraga.ApiProject.exception.RequiredObjectIsNullException;
import com.fraga.ApiProject.exception.ResourceNotFoundException;
import com.fraga.ApiProject.repository.EnderecoRepository;
import com.fraga.ApiProject.repository.PessoaRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public List<EnderecoVO> findAll(Long pessoa_id) {

		Pessoa pessoa = pessoaRepository.findById(pessoa_id)
				.orElseThrow(() -> new ResourceNotFoundException("No person records matches for this ID!"));

		Endereco entity = new Endereco();

		entity.setPessoa(pessoa);

		ExampleMatcher matcher = ExampleMatcher.matchingAny().withStringMatcher(StringMatcher.DEFAULT);
		Example<Endereco> example = Example.of(entity, matcher);

		List<EnderecoVO> enderecos = DozerConverter.parseList(repository.findAll(example), EnderecoVO.class);
		 	
		enderecos.stream()
				.forEach(p -> p.add(linkTo(methodOn(EnderecoController.class).findById(p.getKey())).withSelfRel()));
		return enderecos;
	}

	public EnderecoVO findById(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records matches for this ID!"));

		EnderecoVO endereco = DozerConverter.parseObject(entity, EnderecoVO.class);

		endereco.add(linkTo(methodOn(EnderecoController.class).findById(endereco.getKey())).withSelfRel());
		return endereco;
	}

	public EnderecoVO create(Long pessoa_id, EnderecoVO enderecoVo) {
		System.out.println(enderecoVo);
		if (enderecoVo == null) {
			throw new RequiredObjectIsNullException("It is not allowed to persist a null object!");
		}

		
		Pessoa pessoa = pessoaRepository.findById(pessoa_id)
				.orElseThrow(() -> new ResourceNotFoundException("No person records matches for this ID!"));
		
		
		var entity = DozerConverter.parseObject(enderecoVo, Endereco.class);
		

		entity.setPessoa(pessoa);
		
		entity = repository.save(entity);

		EnderecoVO endereco = DozerConverter.parseObject(entity, EnderecoVO.class);

		endereco.add(linkTo(methodOn(EnderecoController.class).findById(endereco.getKey())).withSelfRel());
		return endereco;
	}

	@Transactional
	public EnderecoVO updateEnderecoPrincipal(Long id, Long pessoa_id) {
		Pessoa pessoa = pessoaRepository.findById(pessoa_id)
				.orElseThrow(() -> new ResourceNotFoundException("No person records matches for this ID!"));

		Endereco entity = new Endereco();

		entity.setPessoa(pessoa);

		ExampleMatcher matcher = ExampleMatcher.matchingAny().withStringMatcher(StringMatcher.CONTAINING);
		Example<Endereco> example = Example.of(entity, matcher);

		List<EnderecoVO> enderecos = DozerConverter.parseList(repository.findAll(example), EnderecoVO.class);

		enderecos.stream().filter(e -> e.getKey() == id).findAny()
				.orElseThrow(() -> new ResourceNotFoundException("No records matches for this ID!"));

		for (EnderecoVO e : enderecos) {
			if (e.getPrincipal()) {
				e.setPrincipal(false);
				repository.save(DozerConverter.parseObject(e, Endereco.class));
			}
			if (e.getKey() == id) {
				e.setPrincipal(true);
				entity = repository.save(DozerConverter.parseObject(e, Endereco.class));

			}
		}
		EnderecoVO endereco = DozerConverter.parseObject(entity, EnderecoVO.class);

		endereco.add(linkTo(methodOn(EnderecoController.class).findById(endereco.getKey())).withSelfRel());
		return endereco;

	}

}
