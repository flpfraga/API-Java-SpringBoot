package com.fraga.ApiProject.unittests.mapper.mocks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fraga.ApiProject.data.model.Pessoa;
import com.fraga.ApiProject.data.vo.PessoaVO;

public class MockPessoa {

	public Pessoa mockEntity() {
		return mockEntity(0);
	}
	
	public PessoaVO mockVO() {
		return mockVO(0);
	}
	public List<Pessoa> mockEntityList() {
        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        for (int i = 0; i < 14; i++) {
            pessoas.add(mockEntity(i));
        }
        return pessoas;
    }

    public List<PessoaVO> mockVOList() {
        List<PessoaVO> pessoas = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            pessoas.add(mockVO(i));
        }
        return pessoas;
    }
    
    public Pessoa mockEntity(Integer number) {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(number.longValue());
        pessoa.setNome("Some Name" + number);
        pessoa.setNascimento(LocalDate.now().plusDays(number));
        return pessoa;
    }

    public PessoaVO mockVO(Integer number) {
        PessoaVO pessoa = new PessoaVO();
        pessoa.setKey(number.longValue());
        pessoa.setNome("Some Name" + number);
        pessoa.setNascimento(LocalDate.now().plusDays(number));
        return pessoa;
    }
}
