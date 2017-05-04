package tests_dominio;

import org.junit.Assert;
import org.junit.Test;

import dominio.Asesino;
import dominio.Elfo;
import dominio.Humano;
import dominio.Personaje;

public class TestElfoNuevo {

	@Test
	public void testHabilidadRaza1() {
		Personaje p1 = new Elfo("Ben Affleck",new Asesino(),2);
		Personaje p2 = new Humano("I'm Batman",new Asesino(),2);
		p1.setEnergia(0);
		Assert.assertFalse(p1.habilidadRaza1(p2));
	}
	
	@Test
	public void testHabilidadRaza1S() {
		Personaje p1 = new Elfo("Ben Affleck",new Asesino(),2);
		Personaje p2 = new Humano("I'm Batman",new Asesino(),2);
		p1.setNivel(0);
		p1.setFuerza(0);
		Assert.assertFalse(p1.habilidadRaza1(p2));
	}
	
	@Test
	public void testHabilidadRaza2() {
		Personaje p1 = new Elfo("Ben Affleck",new Asesino(),2);
		Personaje p2 = new Humano("I'm Batman",new Asesino(),2);
		p1.setEnergia(0);
		Assert.assertFalse(p1.habilidadRaza2(p2));
	}
	
	@Test
	public void testHabilidadRaza2S() {
		Personaje p1 = new Elfo("Ben Affleck",new Asesino(),2);
		Personaje p2 = new Humano("I'm Batman",new Asesino(),2);
		p1.setMagia(0);
		Assert.assertFalse(p1.habilidadRaza2(p2));
	}

}
