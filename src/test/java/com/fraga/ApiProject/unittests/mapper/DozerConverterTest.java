package com.fraga.ApiProject.unittests.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fraga.ApiProject.converter.DozerConverter;
import com.fraga.ApiProject.data.model.Pessoa;
import com.fraga.ApiProject.data.vo.PessoaVO;
import com.fraga.ApiProject.unittests.mapper.mocks.MockPessoa;

public class DozerConverterTest {

	MockPessoa inputObject;

	@BeforeEach
	public void setUp() {
		inputObject = new MockPessoa();
	}

	@Test
	public void parseEntityToVoTest() {
		PessoaVO output = DozerConverter.parseObject(inputObject.mockEntity(), PessoaVO.class);
		assertEquals(Long.valueOf(0L), output.getKey());
		assertEquals("Some Name0", output.getNome());
		assertEquals(LocalDate.now(), output.getNascimento());
	}

	@Test
	public void parseVoToEntityTest() {
		Pessoa output = DozerConverter.parseObject(inputObject.mockVO(), Pessoa.class);
		assertEquals(Long.valueOf(0L), output.getId());
		assertEquals("Some Name0", output.getNome());
		assertEquals(LocalDate.now().plusDays(0), output.getNascimento());
	}

	@Test
	public void parseListEntityToListVoTest() {
		List<PessoaVO> outputList = DozerConverter.parseList(inputObject.mockEntityList(), PessoaVO.class);

		PessoaVO outputZero = outputList.get(0);
		assertEquals(Long.valueOf(0L), outputZero.getKey());
		assertEquals("Some Name0", outputZero.getNome());
		assertEquals(LocalDate.now().plusDays(0), outputZero.getNascimento());

		PessoaVO outputSix = outputList.get(6);
		assertEquals(Long.valueOf(6L), outputSix.getKey());
		assertEquals("Some Name6", outputSix.getNome());
		assertEquals(LocalDate.now().plusDays(6), outputSix.getNascimento());

		PessoaVO outputThirtenn = outputList.get(13);
		assertEquals(Long.valueOf(13L), outputThirtenn.getKey());
		assertEquals("Some Name13", outputThirtenn.getNome());
		assertEquals(LocalDate.now().plusDays(13), outputThirtenn.getNascimento());

	}

	@Test
	public void parseListVOToListEntityTest() {
		List<Pessoa> outputList = DozerConverter.parseList(inputObject.mockVOList(), Pessoa.class);

		Pessoa outputZero = outputList.get(0);
		assertEquals(Long.valueOf(0L), outputZero.getId());
		assertEquals("Some Name0", outputZero.getNome());
		assertEquals(LocalDate.now().plusDays(0), outputZero.getNascimento());


		Pessoa outputSix = outputList.get(6);
		assertEquals(Long.valueOf(6L), outputSix.getId());
		assertEquals("Some Name6", outputSix.getNome());
		assertEquals(LocalDate.now().plusDays(6), outputSix.getNascimento());
		
		Pessoa outputThirtenn = outputList.get(13);
		assertEquals(Long.valueOf(13L), outputThirtenn.getId());
		assertEquals("Some Name13", outputThirtenn.getNome());
		assertEquals(LocalDate.now().plusDays(13), outputThirtenn.getNascimento());

	}

}
