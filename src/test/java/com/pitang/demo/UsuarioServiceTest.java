package com.pitang.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import com.pitang.demo.model.Carro;
import com.pitang.demo.model.Usuario;
import com.pitang.demo.repository.CarroRepository;
import com.pitang.demo.repository.UsuarioRepository;
import com.pitang.demo.security.JwtGenerator;
import com.pitang.demo.service.impl.UsuarioService;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceTest {
	
	@Mock
	private UsuarioRepository usuarioReposity;
	
	@Mock
	private CarroRepository carroRepository;
	
	@InjectMocks
	private UsuarioService target = new UsuarioService();
	
	@Mock
	private JwtGenerator jwtGenerator;
	
	//////////////// Ini Cadastro
	@Test 
	public void cadastrarTest() {
		final Usuario expected = getUsuarioMock(null);
		when(usuarioReposity.findByEmailIgnoreCase(expected.getEmail()))
		.thenReturn(null);
		when(usuarioReposity.findByLoginIgnoreCase(expected.getLogin()))
		.thenReturn(null);	
		when(usuarioReposity.save(expected)).thenReturn(expected);
		final Usuario actual = (Usuario)target.cadastrar(expected);
		assertEquals(expected, actual);
	}
	@Test(expected = ResponseStatusException.class)
	public void cadastrarTestFailEmail() {
		final Usuario expected = getUsuarioMock(null);
		when(usuarioReposity.findByEmailIgnoreCase(expected.getEmail()))
		.thenReturn(expected);
		target.cadastrar(expected);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void cadastrarTestFailLogin() {
		final Usuario expected = getUsuarioMock(null);
		when(usuarioReposity.findByEmailIgnoreCase(expected.getEmail()))
		.thenReturn(null);
		when(usuarioReposity.findByLoginIgnoreCase(expected.getLogin()))
		.thenReturn(expected);	
		target.cadastrar(expected);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void cadastrarTestFailPlaca() {
		final Usuario expected = getUsuarioMock(null);
		when(usuarioReposity.findByEmailIgnoreCase(expected.getEmail()))
		.thenReturn(null);
		when(usuarioReposity.findByLoginIgnoreCase(expected.getLogin()))
		.thenReturn(null);	
		when(carroRepository.findByLicensePlate(expected.getCars().get(0).getLicensePlate()))
		.thenReturn(getCarroMock());	
		target.cadastrar(expected);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void cadastrarTestFailPlaca1() {
		final Usuario expected = getUsuarioMock(null);
		when(usuarioReposity.findByEmailIgnoreCase(expected.getEmail()))
		.thenReturn(null);
		when(usuarioReposity.findByLoginIgnoreCase(expected.getLogin()))
		.thenReturn(null);	
		expected.getCars().add(getCarroMock());
		target.cadastrar(expected);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void cadastrarNameVazio() {
		final Usuario expected = getUsuarioMock(null);
		expected.setFirstName(null);
		target.cadastrar(expected);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void cadastrarNameCarVazio() {
		final Usuario expected = getUsuarioMock(null);
		expected.getCars().get(0).setModel(null);
		target.cadastrar(expected);
	}
	//////////////////////Fim Cadastro
	
	////////////////////ini Alteracao
	@Test 
	public void alterarTest() {
		final Usuario expected = getUsuarioMock(1);
		when(usuarioReposity.findById(expected.getId()))
		.thenReturn(Optional.ofNullable(expected));
		when(usuarioReposity.findByEmailIgnoreCaseAndIdNot(expected.getEmail(),expected.getId()))
		.thenReturn(null);
		when(usuarioReposity.findByLoginIgnoreCaseAndIdNot(expected.getLogin(),expected.getId()))
		.thenReturn(null);	
		when(usuarioReposity.save(expected)).thenReturn(expected);
		final Usuario actual = (Usuario)target.alterar(expected,expected.getId());
		assertEquals(expected, actual);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void alterarTestFailId() {
		final Usuario expected = getUsuarioMock(null);
		target.alterar(expected,expected.getId());
	}
	
	@Test(expected = ResponseStatusException.class) 
	public void alterarTestFailUsuarioNaoExist() {
		final Usuario expected = getUsuarioMock(1);
		when(usuarioReposity.findById(expected.getId()))
		.thenReturn(Optional.empty());
		target.alterar(expected,expected.getId());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void alterarTestFailPlaca() {
		final Usuario expected = getUsuarioMock(1);
		final Carro carro = getCarroComUserMock();
		carro.getUsuario().setId(3);
		when(usuarioReposity.findById(expected.getId()))
		.thenReturn(Optional.ofNullable(expected));
		when(usuarioReposity.findByEmailIgnoreCaseAndIdNot(expected.getEmail(),expected.getId()))
		.thenReturn(null);
		when(usuarioReposity.findByLoginIgnoreCaseAndIdNot(expected.getLogin(),expected.getId()))
		.thenReturn(null);	
		when(carroRepository.findByLicensePlate(expected.getCars().get(0).getLicensePlate()))
		.thenReturn(carro);	
		target.alterar(expected,expected.getId());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void alterarTestFailPlaca1() {
		final Usuario expected = getUsuarioMock(1);
		when(usuarioReposity.findById(expected.getId()))
		.thenReturn(Optional.ofNullable(expected));
		when(usuarioReposity.findByEmailIgnoreCaseAndIdNot(expected.getEmail(),expected.getId()))
		.thenReturn(null);
		when(usuarioReposity.findByLoginIgnoreCaseAndIdNot(expected.getLogin(),expected.getId()))
		.thenReturn(null);	
		expected.getCars().add(getCarroMock());
		target.alterar(expected,expected.getId());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void alterarTestFailEmail() {
		final Usuario expected = getUsuarioMock(1);
		when(usuarioReposity.findById(expected.getId()))
		.thenReturn(Optional.ofNullable(expected));
		when(usuarioReposity.findByEmailIgnoreCaseAndIdNot(expected.getEmail(),expected.getId()))
		.thenReturn(expected);
		target.alterar(expected,expected.getId());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void alterarTestFailLogin() {
		final Usuario expected = getUsuarioMock(1);
		when(usuarioReposity.findById(expected.getId()))
		.thenReturn(Optional.ofNullable(expected));
		when(usuarioReposity.findByEmailIgnoreCaseAndIdNot(expected.getEmail(),expected.getId()))
		.thenReturn(null);
		when(usuarioReposity.findByLoginIgnoreCaseAndIdNot(expected.getLogin(),expected.getId()))
		.thenReturn(expected);
		target.alterar(expected,expected.getId());
	}
	//////////////////////Fim Alteracao
	
	@Test
	public void listaTodos() {
		final List<Usuario> expecteds = new ArrayList<Usuario>(); 
		expecteds.add(getUsuarioMock(1));
		when(usuarioReposity.findByOrderByContadorDescLoginAsc())
		.thenReturn(expecteds);
		target.usersAll();
	}
	
	@Test
	public void listaPorId() {
		final Usuario expected = getUsuarioMock(1);
		when(usuarioReposity.findByIdOrderByContadorDescLoginAsc(expected.getId()))
		.thenReturn(expected);
		target.usersId(expected.getId());
	}
	
	@Test
	public void Remover() {
		final Usuario expected = getUsuarioMock(1);
		when(usuarioReposity.findById(expected.getId()))
		.thenReturn(Optional.ofNullable(expected));
		target.removerUserId(expected.getId());
	}
	@Test
	public void RemoverCarNull() {
		final Usuario expected = getUsuarioMock(1);
		expected.setCars(new ArrayList<Carro>());
		when(usuarioReposity.findById(expected.getId()))
		.thenReturn(Optional.ofNullable(expected));
		
		target.removerUserId(expected.getId());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void removerFailUserNaoExist() {
		final Usuario expected = getUsuarioMock(1);
		when(usuarioReposity.findById(expected.getId()))
		.thenReturn(Optional.empty());
		target.removerUserId(expected.getId());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void loginLoginNull() {
		target.login(null, "password");
	}
	@Test(expected = ResponseStatusException.class)
	public void loginPassNull() {
		target.login("login", null);
	}
	
	private Usuario getUsuarioMock(Integer id) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuario.setCreatedAt(LocalDate.now());
		usuario.setEmail("Email");
		usuario.setFirstName("FirstName");
		usuario.setBirthday(LocalDate.now());
		usuario.setLastName("lastName");
		usuario.setPassword("password");
		usuario.setLogin("login");
		usuario.setPhone("phone");
		List<Carro> carro = new ArrayList<Carro>();
		carro.add(getCarroMock());
		usuario.setCars(carro);
		return usuario;
	}
	
	private Carro getCarroMock() {
		Carro phone = new Carro();
		phone.setId(1);
		phone.setYear(1);
		phone.setLicensePlate("licensePlate");
		phone.setColor("color");
		phone.setModel("model");
		return phone;
	}
	private Carro getCarroComUserMock() {
		Carro carro = new Carro();
		carro.setId(1);
		carro.setYear(1);
		carro.setLicensePlate("licensePlate");
		carro.setColor("color");
		carro.setModel("model");
		carro.setUsuario(new Usuario());
		return carro;
	}

}
