package com.pitang.demo;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import com.pitang.demo.service.impl.CarroService;

@RunWith(MockitoJUnitRunner.class)
public class CarroServiceTest {
	
	@Mock
	private UsuarioRepository usuarioReposity;
	
	@Mock
	private CarroRepository carroRepository;
	
	@InjectMocks
	private CarroService target = new CarroService();
	
	@Mock
	private JwtGenerator jwtGenerator;
	
	private final String EMAIL = "email";
	private final String PASSWORD = "password";
	private final String AUTHORIZATION = "authorization";
	
	//////////////// Ini Cadastro
	@Test 
	public void cadastrarTest() {
		final Carro expected = getCarroComUserMock();
		when(carroRepository.findByLicensePlate(expected.getLicensePlate()))
		.thenReturn(null);
		target.cadastrar(1, expected);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void cadastrarTestFailUsuarioExist(){
		final Carro expected = getCarroComUserMock();
		when(carroRepository.findByLicensePlate(expected.getLicensePlate()))
		.thenReturn(expected);
		target.cadastrar(1, expected);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void cadastrarTestFailColor(){
		final Carro expected = getCarroComUserMock();
		expected.setColor(null);
		target.cadastrar(1, expected);
	}
	@Test(expected = ResponseStatusException.class)
	public void cadastrarTestFailLicensePlate(){
		final Carro expected = getCarroComUserMock();
		expected.setLicensePlate(null);
		target.cadastrar(1, expected);
	}
	@Test(expected = ResponseStatusException.class)
	public void cadastrarTestFailModel(){
		final Carro expected = getCarroComUserMock();
		expected.setModel(null);
		target.cadastrar(1, expected);
	}
	@Test(expected = ResponseStatusException.class)
	public void cadastrarTestFailYear(){
		final Carro expected = getCarroComUserMock();
		expected.setYear(0);
		target.cadastrar(1, expected);
	}

	///////iniciar Alterar
	@Test 
	public void alterarTest() {
		final Carro expected = getCarroComUserMock();
		when(carroRepository.findByIdAndUsuarioId(expected.getId(), 1))
		.thenReturn(expected);
		when(carroRepository.findByLicensePlate(expected.getLicensePlate()))
		.thenReturn(null);
		target.alterar(1,1,expected);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void alterarTestFailCarExist(){
		final Carro expected = getCarroComUserMock();
		when(carroRepository.findByIdAndUsuarioId(expected.getId(), 1))
		.thenReturn(null);
		target.alterar(1,1,expected);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void alterarTestFailIdNull(){
		final Carro expected = getCarroComUserMock();
		target.alterar(null,1,expected);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void alterarTestFailIdmenor(){
		final Carro expected = getCarroComUserMock();
		target.alterar(0,1,expected);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void alterarTestFailColor(){
		final Carro expected = getCarroComUserMock();
		when(carroRepository.findByIdAndUsuarioId(expected.getId(), 1))
		.thenReturn(expected);
		expected.setColor(null);
		target.alterar(1,1,expected);
	}
	@Test(expected = ResponseStatusException.class)
	public void alterarTestFailLicensePlate(){
		final Carro expected = getCarroComUserMock();
		when(carroRepository.findByIdAndUsuarioId(expected.getId(), 1))
		.thenReturn(expected);
		expected.setLicensePlate(null);
		target.alterar(1,1,expected);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void alterarTestFailModel(){
		final Carro expected = getCarroComUserMock();
		when(carroRepository.findByIdAndUsuarioId(expected.getId(), 1))
		.thenReturn(expected);
		expected.setModel(null);
		target.alterar(1,1,expected);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void alterarTestFailYear(){
		final Carro expected = getCarroComUserMock();
		when(carroRepository.findByIdAndUsuarioId(expected.getId(), 1))
		.thenReturn(expected);
		expected.setYear(0);
		target.alterar(1,1,expected);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void alterarFailPlaca() {
		final Carro expected = getCarroComUserMock();
		final Carro expected1 = getCarroComUserMock();
		expected1.setId(3);
		when(carroRepository.findByIdAndUsuarioId(expected.getId(), 1))
		.thenReturn(expected);
		when(carroRepository.findByLicensePlate(expected.getLicensePlate()))
		.thenReturn(expected1);
		target.alterar(1,1,expected);
	}
	
	@Test
	public void alterarFailPlacaNull() {
		final Carro expected = getCarroComUserMock();
		final Carro expected1 = getCarroComUserMock();
		expected1.setId(3);
		when(carroRepository.findByIdAndUsuarioId(expected.getId(), 1))
		.thenReturn(expected);
		when(carroRepository.findByLicensePlate(expected.getLicensePlate()))
		.thenReturn(expected);
		target.alterar(1,1,expected);
	}
	
	///////remover 
	@Test
	public void remover() {
		final Carro expected = getCarroComUserMock();
		when(carroRepository.findByIdAndUsuarioId(expected.getId(), 1))
		.thenReturn(expected);
		target.removerCarroId(1,1);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void removerFail() {
		final Carro expected = getCarroComUserMock();
		when(carroRepository.findByIdAndUsuarioId(expected.getId(), 1))
		.thenReturn(null);
		target.removerCarroId(1,1);
	}
	
	////////Listagem
	
	@Test
	public void todosCars() {
		List<Carro> cars = new ArrayList<Carro>();
		cars.add(getCarroComUserMock());
		when(carroRepository.findAllByUsuarioId(1))
		.thenReturn(cars);
		target.carsAll(1);
	}
	
	@Test
	public void todosCarsId() {
		Carro car = getCarroMock();
		when(carroRepository.findByIdAndUsuarioId(1,1))
		.thenReturn(car);
		target.carsId(1,1);
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
//		List<Carro> carro = new ArrayList<Carro>();
//		carro.add(getCarroMock());
//		usuario.setCars(carro);
		return usuario;
	}
	
	private Carro getCarroMock() {
		Carro carro = new Carro();
		carro.setId(1);
		carro.setYear(1);
		carro.setLicensePlate("licensePlate");
		carro.setColor("color");
		carro.setModel("model");
		return carro;
	}
	private Carro getCarroComUserMock() {
		Carro carro = new Carro();
		carro.setId(1);
		carro.setYear(1);
		carro.setLicensePlate("licensePlate");
		carro.setColor("color");
		carro.setModel("model");
		carro.setUsuario(getUsuarioMock(1));
		return carro;
	}

}
