package dominio;

import java.io.Serializable;

/**
 * La clase Personaje contiene los atributos de cada jugador.
 * Entre ellos se destacan los de ataque y defensa, salud, posicion,
 * nivel y nombre. Ademas posee una casta y una raza, ambas conteniendo
 * una serie de habilidades especiales.
 * Además implementa la interfaz Peleable por lo que debe implementar sus métodos.
 */

public abstract class Personaje extends Character implements Peleable, Serializable {

	protected static final int INCREMENTO_TOPE = 10;
	protected static final int ENERGIA_MINIMA = 10;
	protected static final int CANTIDAD_HABILIDADES = 2;

	private static final int DIMENSION_TABLA = 101;

	private static final int SALUD_INICIAL = 100;
	private static final int ENERGIA_INICIAL = 100;
	private static final int EXPERIENCIA_INICIAL = 0;
	private static final int NIVEL_INICIAL = 1;
	private static final int FUERZA_INICIAL = 10;
	private static final int INTELIGENCIA_INICIAL = 10;
	private static final int DESTREZA_INICIAL = 10;

	private static final double INCREMENTO_PUNTOS = 1.5;

	private static final double SKILLS_MAXIMO = 200;
	private static final double NIVEL_MAXIMO = 100;

	protected int energia;
	protected int ataque;
	protected int magia;
	protected String nombreRaza;
	protected int saludTope;
	protected int energiaTope;

	protected String[] habilidadesRaza;

	private int destreza;
	private int inteligencia;
	private Casta casta;

	private int x;
	private int y;

	private int experiencia;
	private int idPersonaje;

	private Alianza clan = null;
	private static int[] tablaDeNiveles;



	/**
	 * @return habilidades de raza
	 */

	public String[] getHabilidadesRaza() {
		return habilidadesRaza;
	}

	/**
	 * @return habilidades de casta
	 */

	public String[] getHabilidadesCasta() {
		return casta.getHabilidadesCasta();
	}

	/**
	 * Inicialización de la tabla de nieveles del juego.
	 */

	public static void cargarTablaNivel() {
		Personaje.tablaDeNiveles = new int[DIMENSION_TABLA];
		Personaje.tablaDeNiveles[0] = 0;
		Personaje.tablaDeNiveles[1] = 0;
		for (int i = 2; i < DIMENSION_TABLA; i++) {
			Personaje.tablaDeNiveles[i] = Personaje.tablaDeNiveles[i - 1] + (int) (DIMENSION_TABLA / 2);
		}
	}

	/**
	 * Constructor de la clase con valores por defecto.
	 * @param nombre del personaje
	 * @param casta casta
	 * @param id del personaje
	 * @param nombreRaza del personaje
	 * @param habilidad1 del personaje
	 * @param habilidad2 del personaje
	 */

	public Personaje(final String nombre, final Casta casta, final int id,
		final String nombreRaza, final String habilidad1, final String habilidad2) {
		super(nombre);

		this.casta = casta;
		this.idPersonaje = id;
		this.nombreRaza = nombreRaza;

		experiencia = EXPERIENCIA_INICIAL;
		nivel = NIVEL_INICIAL;
		fuerza = FUERZA_INICIAL;
		inteligencia = INTELIGENCIA_INICIAL;
		destreza = DESTREZA_INICIAL;

		casta.incrementarAtributoAlCrear(this);

		x = 0;
		y = 0;
		saludTope = SALUD_INICIAL;
		energiaTope = ENERGIA_INICIAL;

		ataque = this.calcularPuntosDeAtaque();
		defensa = this.calcularPuntosDeDefensa();
		magia = this.calcularPuntosDeMagia();

		this.setHabilidades(habilidad1, habilidad2);
	}

	/**
	 * Constructor de la clase con valores pasados por parámetro.
	 * Los atributos defensa ataque y magia son calculados.
	 * @param nombre del personaje
	 * @param salud del personaje
	 * @param energia del personaje
	 * @param fuerza del personaje
	 * @param destreza del personaje
	 * @param inteligencia del personaje
	 * @param casta del personaje
	 * @param experiencia del personaje
	 * @param nivel del personaje
	 * @param idPersonaje del personaje
	 * @param nombreRaza del personaje
	 * @param habilidad1 del personaje
	 * @param habilidad2 del personaje
	 */

	public Personaje(final String nombre, final int salud, final int energia,
		final int fuerza, final int destreza, final int inteligencia,
		final Casta casta, final int experiencia, final int nivel,
		final int idPersonaje, final String nombreRaza, final String habilidad1, final String habilidad2) {

		super(nombre);

		this.salud = salud;
		this.energia = energia;
		this.fuerza = fuerza;
		this.destreza = destreza;
		this.inteligencia = inteligencia;
		this.casta = casta;

		this.experiencia = experiencia;
		this.nivel = nivel;

		this.saludTope = this.salud;
		this.energiaTope = this.energia;

		this.idPersonaje = idPersonaje;
		this.defensa = this.calcularPuntosDeDefensa();
		this.ataque = this.calcularPuntosDeAtaque();
		this.magia = this.calcularPuntosDeMagia();

		this.nombreRaza = nombreRaza;
		this.setHabilidades(habilidad1, habilidad2);
	}

	/**
	 * Asigna las habilidades de raza del personaje
	 * @param habilidad1 del personaje
	 * @param habilidad2 del personaje
	 */

	private void setHabilidades(final String habilidad1, final String habilidad2) {
		habilidadesRaza = new String[CANTIDAD_HABILIDADES];
		habilidadesRaza[0] = habilidad1;
		habilidadesRaza[1] = habilidad2;
	}

	/**
	 * @return nombre de raza
	 */

	public String getNombreRaza() {
		return nombreRaza;
	}

	/**
	 * @param nombreRaza string con el nombre de la raza
	 */

	public void setNombreRaza(final String nombreRaza) {
		this.nombreRaza = nombreRaza;
	}

	/**
	 * @return ataque del personaje
	 */

	public int getAtaque() {
		return ataque;
	}

	/**
	 * @param ataque ataque del personaje
	 */

	public void setAtaque(final int ataque) {
		this.ataque = ataque;
	}

	/**
	 * @return magia del personaje
	 */

	public int getMagia() {
		return magia;
	}

	/**
	 * @param magia del personaje
	 */

	public void setMagia(final int magia) {
		this.magia = magia;
	}

	/**
	 * @return clan - alianza actual del personaje
	 */

	public Alianza getClan() {
		return clan;
	}

	/**
	 * @param clan (alianza)
	 */

	public void setClan(final Alianza clan) {
		this.clan = clan;
		clan.añadirPersonaje(this);
	}

	public int getEnergia() {
		return energia;
	}

	/**
	 * @param energia energia del personaje
	 */

	public void setEnergia(final int energia) {
		this.energia = energia;
	}

	/**
	 * @return destreza
	 */

	public int getDestreza() {
		return destreza;
	}

	/**
	 * @param destreza del personaje
	 */

	public void setDestreza(final int destreza) {
		this.destreza = destreza;
	}

	/**
	 * @return inteligencia
	 */

	public int getInteligencia() {
		return inteligencia;
	}

	/**
	 * @param inteligencia del personaje
	 */

	public void setInteligencia(final int inteligencia) {
		this.inteligencia = inteligencia;
	}

	/**
	 * @return casta
	 */

	public Casta getCasta() {
		return casta;
	}

	/**
	 * @param casta del personaje
	 */

	public void setCasta(final Casta casta) {
		this.casta = casta;
	}

	/**
	 * @return experiencia
	 */

	public int getExperiencia() {
		return experiencia;
	}

	/**
	 * @param experiencia del personaje
	 */

	public void setExperiencia(final int experiencia) {
		this.experiencia = experiencia;
	}

	/**
	 * @return idPersonaje
	 */

	public int getIdPersonaje() {
		return idPersonaje;
	}

	/**
	 * @param idPersonaje id del personaje
	 */

	public void setIdPersonaje(final int idPersonaje) {
		this.idPersonaje = idPersonaje;
	}

	/**
	 * @return salud tope
	 */

	public int getSaludTope() {
		return saludTope;
	}

	/**
	 * @param saludTope salud tope del personaje
	 */

	public void setSaludTope(final int saludTope) {
		this.saludTope = saludTope;
	}

	/**
	 * @return energiaTope
	 */

	public int getEnergiaTope() {
		return energiaTope;
	}

	/**
	 * @param energiaTope energia tope del personaje
	 */

	public void setEnergiaTope(final int energiaTope) {
		this.energiaTope = energiaTope;
	}

	/**
	 * "atacar" obtiene el ataque de este objeto e invoca al método serAtacado
	 * del "atacado" recibido como parametro.
	 * @param atacado atacado
	 * @return daño ocasionado al atacar
	 */

	public int atacar(final Peleable atacado) {
		if (salud == 0) {
			return 0;
		}
		if (atacado.getSalud() > 0) {
			if (MyRandom.nextDouble() <= this.casta.getProbabilidadGolpeCritico() + this.destreza / 1000) {
				return atacado.serAtacado(this.golpe_critico());
			} else {
				return atacado.serAtacado(this.ataque);
			}
		}
		return 0;
	}

	/**
	 * @return calculo del valor del golpe critico basado en el
	 * ataque y el daño de la casta.
	 */

	public int golpe_critico() {
		return (int) (this.ataque * this.getCasta().getDañoCritico());
	}

	/**
	 * to do
	 */

	public void despuesDeTurno() { }

	/**
	 * Verifica que el personaje pueda atacar.
	 * @return verdadero o falso si la energia es mayor al maximo o no.
	 */

	public boolean puedeAtacar() {
		return energia > ENERGIA_MINIMA;
	}

	/**
	 * Calculo de los puntos de ataque del personaje.
	 * @return nuevos puntos de ataque
	 */

	public int calcularPuntosDeAtaque() {
		return (int) (this.getFuerza() * INCREMENTO_PUNTOS);
	}

	/**
	 * Calculo de los puntos de defensa.
	 * @return la parte entera del atributo destreza.
	 */

	public int calcularPuntosDeDefensa() {
		return (int) (this.getDestreza());
	}

	/**
	 * Calculo de los puntos de magia.
	 * Función lineal de la inteligencia.
	 * @return nuevos puntos de magia
	 */

	public int calcularPuntosDeMagia() {
		return (int) (this.getInteligencia() * INCREMENTO_PUNTOS);
	}

	/**
	 * Carga la salud del personaje hasta el tope.
	 */

	public void restablecerSalud() {
		this.salud = this.saludTope;
	}

	/**
	 * Carga la energia del personaje hasta el tope.
	 */

	public void restablecerEnergia() {
		this.energia = this.energiaTope;
	}

	/**
	 * Recalcula el ataque, la defensa y la magia del personaje.
	 */

	public void modificarAtributos() {
		this.ataque = this.calcularPuntosDeAtaque();
		this.defensa = this.calcularPuntosDeDefensa();
		this.magia = this.calcularPuntosDeMagia();
	}

	/**
	 * Aplica un daño sobre la salud.
	 * @param daño daño
	 * @return 0 si no es dañado o el valor del daño ocasionado por el ataque.
	 */

	public int serAtacado(int daño) {
		if (MyRandom.nextDouble() >= this.getCasta().getProbabilidadEvitarDaño()) {
			daño -= this.defensa;
			if (daño > 0) {
				if (salud <= daño) {
					daño = salud;
					salud = 0;
				} else {
					salud -= daño;
				}
				return daño;
			}
			return 0;
		}
		return 0;
	}

	/**
	 * Aplica un daño a la salud y devuelve la cantidad del
	 * mismo que se ha inflingido.
	 * @param daño daño
	 * @return daño que se ha inflingido.
	 */

	public int serRobadoSalud(int daño) {
		daño -= this.defensa;
		if (daño <= 0) {
			return 0;
		}
		if ((salud - daño) >= 0) {
			salud -= daño;
		} else {
			daño = salud;
			salud = 0;
		}
		return daño;
	}

	/**
	 * Aplica daño a la energia y devuelve la cantidad del
	 * mismo que se ha inflingido.
	 * @param daño daño
	 * @return daño que se ha inflingido.
	 */

	public int serDesenergizado(int daño) {
		daño -= this.defensa;
		if (daño <= 0) {
			return 0;
		}
		if ((energia - daño) >= 0) {
			energia -= daño;
		} else {
			daño = energia;
			energia = 0;
		}
		return daño;
	}

	/**
	 * Aumenta la salud del personaje.
	 * @param salud salud del personaje
	 */

	public void serCurado(final int salud) {
		if ((this.salud + salud) <= this.saludTope) {
			this.salud += salud;
		} else {
			this.salud = this.saludTope;
		}
	}

	/**
	 * Aumenta la energia del personaje.
	 * @param energia del personaje
	 */

	public void serEnergizado(final int energia) {
		if ((this.energia + energia) <= this.energiaTope) {
			this.energia += energia;
		} else {
			this.energia = this.energiaTope;
		}
	}

	/**
	 * Crea una nueva alianza entre personajes y agregar
	 * al objeto como el primer integrante.
	 * @param nombre_alianza nombre del clan
	 */

	public void crearAlianza(final String nombre_alianza) {
		this.clan = new Alianza(nombre_alianza);
		this.clan.añadirPersonaje(this);
	}

	/**
	 * Elimina al personaje del clan al que pertenece.
	 */

	public void salirDeAlianza() {
		if (this.clan != null) {
			this.clan.eliminarPersonaje(this);
			this.clan = null;
		}
	}

	/**
	 * Convierte a un personaje recibido por parámetro en el aliado de
	 * este personaje. Crea una nueva alianza si no existe.
	 * @param nuevo_aliado nuevo aliado
	 * @return verdadero o falso si se ha podido establecer la alianza o no.
	 */

	public boolean aliar(final Personaje nuevo_aliado) {
		if (this.clan == null) {
			Alianza a = new Alianza("Alianza 1");
			this.clan = a;
			a.añadirPersonaje(this);
		}

		if (nuevo_aliado.clan == null) {
			nuevo_aliado.clan = this.clan;
			this.clan.añadirPersonaje(nuevo_aliado);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Aumenta fuerza, destreza e inteligencia siempre que el total
	 * sea menor a SKILLS_MAXIMO.
	 * @param fuerza del personaje
	 * @param destreza del personaje
	 * @param inteligencia del personaje
	 */

	public void AsignarPuntosSkills(final int fuerza, final int destreza, final int inteligencia) {
		if (this.fuerza + fuerza <= SKILLS_MAXIMO) {
			this.fuerza += fuerza;
		}
		if (this.destreza + destreza <= SKILLS_MAXIMO) {
			this.destreza += destreza;
		}
		if (this.inteligencia + inteligencia <= SKILLS_MAXIMO) {
			this.inteligencia += inteligencia;
		}
		this.modificarAtributos();
	}

	/**
	 * Aumenta el nivel del personaje y recalcula sus atributos.
	 */

	public void subirNivel() {

		int acumuladorExperiencia = 0;
		if (this.nivel == NIVEL_MAXIMO) {
			return;
		}
		while (this.nivel != NIVEL_MAXIMO
				&& (this.experiencia >= Personaje.tablaDeNiveles[this.nivel + 1] + acumuladorExperiencia)) {
			acumuladorExperiencia += Personaje.tablaDeNiveles[this.nivel + 1];
			this.nivel++;
			this.modificarAtributos();
			this.saludTope += 25;
			this.energiaTope += 20;
		}
		this.experiencia -= acumuladorExperiencia;
	}

	/**
	 * Aumenta la experiencia del personaje y lo sube de nivel
	 * llegado el caso.
	 * @param exp (experiencia)
	 * @return verdadero cuando sube de nivel y falso en caso contrario.
	 */

	public boolean ganarExperiencia(final int exp) {
		this.experiencia += exp;

		if (experiencia >= Personaje.tablaDeNiveles[this.nivel + 1]) {
			subirNivel();
			return true;
		}
		return false;
	}

	/**
	 * Aumenta el nivel del personaje 40 veces.
	 * @return nuevo nivel
	 */

	public int otorgarExp() {
		return this.nivel * 40;
	}

	/**
	 * Sobreescribe el clone de la clase padre
	 * @throws CloneNotSupportedException cuando no se puede clonar
	 * @return un clon del padre
	 */

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * Calcula la distancia con otro personaje.
	 * @param p (otro personaje)
	 * @return distancia entre ambos
	 */

	public double distanciaCon(final Personaje p) {
		return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
	}

	/**
	 * Invoca al metodo habilidad 1 de la casta.
	 * @param atacado atacado
	 * @return lo mismo que habilidad1 de la casta
	 */

	public boolean habilidadCasta1(final Peleable atacado) {
		return this.getCasta().habilidad1(this, atacado);
	}

	/**
	 * Invoca al metodo habilidad 2 de la casta.
	 * @param atacado atacado
	 * @return lo mismo que habilidad2 de la casta
	 */

	public boolean habilidadCasta2(final Peleable atacado) {
		return this.getCasta().habilidad2(this, atacado);
	}

	/**
	 * Invoca al metodo habilidad 3 de la casta.
	 * @param atacado atacado
	 * @return lo mismo que habilidad3 de la casta
	 */

	public boolean habilidadCasta3(final Peleable atacado) {
		return this.getCasta().habilidad3(this, atacado);
	}

	/**
	 * habilidadRaza1 será implementado en las clases que hereden de esta.
	 * @param atacado atacado
	 * @return verdadero o falso
	 */

	public abstract boolean habilidadRaza1(final Peleable atacado);

	/**
	 * habilidadRaza2 será implementado en las clases que hereden de esta.
	 * @param atacado atacado
	 * @return verdadero o falso
	 */

	public abstract boolean habilidadRaza2(final Peleable atacado);

}
