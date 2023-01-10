package com.fraga.ApiProject.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.List;

import com.fraga.ApiProject.data.model.Endereco;
import com.fraga.ApiProject.data.vo.EnderecoVO;

public class MockEndereco {

	public Endereco mockEntity() {
		return mockEntity(0);
	}
	
	public EnderecoVO mockVO() {
		return mockVO(0);
	}
	public List<Endereco> mockEntityList() {
        List<Endereco> enderecos = new ArrayList<Endereco>();
        for (int i = 0; i < 14; i++) {
            enderecos.add(mockEntity(i));
        }
        return enderecos;
    }

    public List<EnderecoVO> mockVOList() {
        List<EnderecoVO> enderecos = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            enderecos.add(mockVO(i));
        }
        return enderecos;
    }
    
    public Endereco mockEntity(Integer number) {
        Endereco endereco = new Endereco();
        endereco.setId(number.longValue());
        endereco.setLogradouro("Some logradouro" + number);
        endereco.setCidade("Some cidade" + number);
        endereco.setNumero(number);
        endereco.setPrincipal(false);
        endereco.setPessoa(new MockPessoa().mockEntity(number));
        return endereco;
    }

    public EnderecoVO mockVO(Integer number) {
        EnderecoVO endereco = new EnderecoVO();
        endereco.setKey(number.longValue());
        endereco.setLogradouro("Some logradouro" + number);
        endereco.setCidade("Some cidade" + number);
        endereco.setNumero(number);
        endereco.setPrincipal(false);
        endereco.setPessoa(new MockPessoa().mockEntity(number));
        return endereco;
    }
	
}
