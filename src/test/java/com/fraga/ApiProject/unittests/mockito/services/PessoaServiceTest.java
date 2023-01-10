package com.fraga.ApiProject.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fraga.ApiProject.data.model.Pessoa;
import com.fraga.ApiProject.data.vo.PessoaVO;
import com.fraga.ApiProject.exception.RequiredObjectIsNullException;
import com.fraga.ApiProject.repository.PessoaRepository;
import com.fraga.ApiProject.service.PessoaService;
import com.fraga.ApiProject.unittests.mapper.mocks.MockPessoa;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

	MockPessoa input;

	@InjectMocks
	private PessoaService service;

	@Mock
	PessoaRepository repository;

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockPessoa();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() {
		Pessoa entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.getLinks().toString().contains("</api/pessoa/v1/1>;rel=\"self\""));
		assertEquals("Some Name1", result.getNome());
		assertEquals(LocalDate.now().plusDays(1), result.getNascimento());

	}

	@Test
	void testCreate() {
		Pessoa entity = input.mockEntity(1);
		entity.setId(1L);

		Pessoa persisted = entity;
		persisted.setId(1L);

		PessoaVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.save(entity)).thenReturn(persisted);

		var result = service.create(vo);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.getLinks().toString().contains("</api/pessoa/v1/1>;rel=\"self\""));
		assertEquals("Some Name1", result.getNome());
		assertEquals(LocalDate.now().plusDays(1), result.getNascimento());
	}

	@Test
	void testCreateWithNullPessoa() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});

		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testUpdate() {
		Pessoa entity = input.mockEntity(1);

		Pessoa persisted = entity;
		persisted.setId(1L);

		PessoaVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);

		var result = service.update(vo, 1L);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.getLinks().toString().contains("</api/pessoa/v1/1>;rel=\"self\""));
		assertEquals("Some Name1", result.getNome());
		assertEquals(LocalDate.now().plusDays(1), result.getNascimento());
	}

	@Test
	void testUpdateWithNullPessoa() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.update(null, null);
		});

		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testDelete() {
		Pessoa entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		service.delete(1L);
	}

	@Test
	void testFindAll() {
		List<Pessoa> list = input.mockEntityList();

		when(repository.findAll()).thenReturn(list);

		var pessoas = service.findAll();

		assertNotNull(pessoas);
		assertEquals(14, pessoas.size());

		var pessoaOne = pessoas.get(1);

		assertNotNull(pessoaOne);
		assertNotNull(pessoaOne.getKey());
		assertNotNull(pessoaOne.getLinks());
		assertTrue(pessoaOne.getLinks().toString().contains("</api/pessoa/v1/1>;rel=\"self\""));
		assertEquals("Some Name1", pessoaOne.getNome());
		assertEquals(LocalDate.now().plusDays(1), pessoaOne.getNascimento());

		var pessoaFour = pessoas.get(4);

		assertNotNull(pessoaFour);
		assertNotNull(pessoaFour.getKey());
		assertNotNull(pessoaFour.getLinks());
		
		assertTrue(pessoaFour.getLinks().toString().contains("</api/pessoa/v1/4>;rel=\"self\""));
		assertEquals("Some Name4", pessoaFour.getNome());
		assertEquals(LocalDate.now().plusDays(4), pessoaFour.getNascimento());

		var pessoaSeven = pessoas.get(7);

		assertNotNull(pessoaSeven);
		assertNotNull(pessoaSeven.getKey());
		assertNotNull(pessoaSeven.getLinks());
		
		assertTrue(pessoaSeven.getLinks().toString().contains("</api/pessoa/v1/7>;rel=\"self\""));
		assertEquals("Some Name7", pessoaSeven.getNome());
		assertEquals(LocalDate.now().plusDays(7), pessoaSeven.getNascimento());
	}

}
