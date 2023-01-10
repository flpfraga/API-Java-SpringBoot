package com.fraga.ApiProject.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

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

import com.fraga.ApiProject.data.model.Endereco;
import com.fraga.ApiProject.data.vo.EnderecoVO;
import com.fraga.ApiProject.exception.RequiredObjectIsNullException;
import com.fraga.ApiProject.exception.ResourceNotFoundException;
import com.fraga.ApiProject.repository.EnderecoRepository;
import com.fraga.ApiProject.service.EnderecoService;
import com.fraga.ApiProject.unittests.mapper.mocks.MockEndereco;
import com.fraga.ApiProject.unittests.mapper.mocks.MockPessoa;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class EnderecoServiceTest {

	MockEndereco input;

	MockPessoa inputPessoa;

	@InjectMocks
	private EnderecoService service;
	
	

	@Mock
	EnderecoRepository repository;
	


	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockEndereco();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() {
		Endereco entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.getLinks().toString().contains("</api/endereco/v1/1>;rel=\"self\""));
		assertEquals("Some logradouro1", result.getLogradouro());
		assertEquals("Some cidade1", result.getCidade());
		assertEquals(1, result.getNumero());
		assertEquals(false, result.getPrincipal());
		assertEquals("Some logradouro1", result.getLogradouro());
		assertEquals(new MockPessoa().mockEntity(1), result.getPessoa());

	}

//	@Test
//	void testCreate() {
//		
//		Endereco entity = input.mockEntity(1);
//		entity.setId(1L);
//
//		Endereco persisted = entity;
//		persisted.setId(1L);
//
//		EnderecoVO vo = input.mockVO(1);
//		vo.setKey(1L);
//		
//		
//		when(repository.save(entity)).thenReturn(persisted);
//
//		var result = service.create(0L, vo);
//
//		assertNotNull(result);
//		assertNotNull(result.getKey());
//		assertNotNull(result.getLinks());
//		assertTrue(result.getLinks().toString().contains("</api/endereco/v1/1>;rel=\"self\""));
//		assertEquals("Some logradouro1", result.getLogradouro());
//		assertEquals("Some cidade1", result.getCidade());
//		assertEquals(1, result.getNumero());
//		assertEquals(false, result.getPrincipal());
//		assertEquals("Some logradouro1", result.getLogradouro());
//		assertEquals(new MockPessoa().mockEntity(1), result.getPessoa());
//	}

	@Test
	void testCreateWithNullEndereco() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(1L, null);
		});

		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	@Test
	void testCreateWithInvalidPessoaId() {
		
		Endereco entity = input.mockEntity(1);
		entity.setId(1L);

		Endereco persisted = entity;
		persisted.setId(1L);

		EnderecoVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		System.out.println("teste " + vo);
		
		Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
			service.create(1L, vo);
		});
		
		String expectedMessage = "No person records matches for this ID!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}
//
//	@Test
//	void testUpdate() {
//		Endereco entity = input.mockEntity(1);
//
//		Endereco persisted = entity;
//		persisted.setId(1L);
//
//		EnderecoVO vo = input.mockVO(1);
//		vo.setKey(1L);
//
//		when(repository.findById(1L)).thenReturn(Optional.of(entity));
//		when(repository.save(entity)).thenReturn(persisted);
//
//		var result = service.update(vo, 1L);
//
//		assertNotNull(result);
//		assertNotNull(result.getKey());
//		assertNotNull(result.getLinks());
//
//		assertTrue(result.getLinks().toString().contains("</api/endereco/v1/1>;rel=\"self\""));
//		assertEquals("Some Name1", result.getNome());
//		assertEquals(LocalDate.now().plusDays(1), result.getNascimento());
//	}
//
//	@Test
//	void testUpdateWithNullEndereco() {
//		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
//			service.update(null, null);
//		});
//
//		String expectedMessage = "It is not allowed to persist a null object!";
//		String actualMessage = exception.getMessage();
//
//		assertTrue(actualMessage.contains(expectedMessage));
//	}
//

//
//	@Test
//	void testFindAll() {
//		List<Endereco> list = input.mockEntityList();
//
//		when(repository.findAll()).thenReturn(list);
//
//		var enderecos = service.findAll();
//
//		assertNotNull(enderecos);
//		assertEquals(14, enderecos.size());
//
//		var enderecoOne = enderecos.get(1);
//
//		assertNotNull(enderecoOne);
//		assertNotNull(enderecoOne.getKey());
//		assertNotNull(enderecoOne.getLinks());
//		assertTrue(enderecoOne.getLinks().toString().contains("</api/endereco/v1/1>;rel=\"self\""));
//		assertEquals("Some Name1", enderecoOne.getNome());
//		assertEquals(LocalDate.now().plusDays(1), enderecoOne.getNascimento());
//
//		var enderecoFour = enderecos.get(4);
//
//		assertNotNull(enderecoFour);
//		assertNotNull(enderecoFour.getKey());
//		assertNotNull(enderecoFour.getLinks());
//
//		assertTrue(enderecoFour.getLinks().toString().contains("</api/endereco/v1/4>;rel=\"self\""));
//		assertEquals("Some Name4", enderecoFour.getNome());
//		assertEquals(LocalDate.now().plusDays(4), enderecoFour.getNascimento());
//
//		var enderecoSeven = enderecos.get(7);
//
//		assertNotNull(enderecoSeven);
//		assertNotNull(enderecoSeven.getKey());
//		assertNotNull(enderecoSeven.getLinks());
//
//		assertTrue(enderecoSeven.getLinks().toString().contains("</api/endereco/v1/7>;rel=\"self\""));
//		assertEquals("Some Name7", enderecoSeven.getNome());
//		assertEquals(LocalDate.now().plusDays(7), enderecoSeven.getNascimento());
//	}

}
