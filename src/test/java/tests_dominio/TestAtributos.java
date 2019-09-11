package tests_dominio;

import org.junit.Test;
import org.junit.Before;

import dominio.*;

import java.util.ArrayList;

import org.junit.Assert;

public class TestAtributos {

	private Humano h;
	
	@Before
	public void initialize() {
		h = new Humano("Nicolas", new Guerrero(), 1, new ArrayList<Item>());
	}

	@Test
	public void testIncrementarFuerza() {
		Assert.assertTrue(h.getAtaque() == 37);
		h.asignarPuntosSkills(10, 0, 0);
		Assert.assertTrue(h.getAtaque() > 37);
	}

	@Test
	public void testIncrementarDestreza() {
		Assert.assertTrue(h.getDefensa() == 20);
		h.asignarPuntosSkills(0, 10, 0);
		Assert.assertTrue(h.getDefensa() > 20);
	}

	@Test
	public void testIncrementarInteligencia() {
		Assert.assertTrue(h.getMagia() == 30);
		h.asignarPuntosSkills(0, 0, 10);
		Assert.assertTrue(h.getMagia() > 30);
	}

	@Test
	public void testIncrementarMasDelMaximo() {
		int max = 201;

		Assert.assertTrue(h.getAtaque() == 37);
		Assert.assertTrue(h.getDefensa() == 20);
		Assert.assertTrue(h.getMagia() == 30);

		h.asignarPuntosSkills(max, max, max);
		Assert.assertTrue(h.getAtaque() == 37);
		Assert.assertTrue(h.getDefensa() == 20);
		Assert.assertTrue(h.getMagia() == 30);
	}
}
