package dominio;

import java.io.Serializable;

/**
 * Personaje. Siendo una de las clases mas importantes, es la que une a todos
 * los tipos de personajes En ella se encuentran todos los atributos y metodos
 * apropiados para tener todo en cuenta
 *
 */
public abstract class Personaje implements Peleable, Serializable {
  private static final long serialVersionUID = 1L;
  protected int salud;
  protected int energia;
  protected int defensa;// depende de la destreza
  protected int ataque;// depende de la fuerza
  protected int magia;// depende de la inteligencia
  protected String nombre;// hay que agregarlo a todos los constructores
  protected String nombreRaza;
  protected int saludTope;
  protected int energiaTope;
  protected int fuerza;
  protected int destreza;
  protected int inteligencia;
  protected Casta casta;
  protected int posX;
  protected int posY;
  protected int experiencia;
  protected int nivel;
  protected int idPersonaje;
  protected Alianza clan = null;
  public static int[] tablaDeNiveles;
  protected String[] habilidadesRaza;

  /**
   * getHabilidadesRaza().
   *
   * @return Retorna las habilidades de la raza, en formato de String[]
   */
  public String[] getHabilidadesRaza() {
    return habilidadesRaza;
  }

  /**
   * getHabilidadesCasta().
   *
   * @return Retorna las habilidades de la casta, en formato de String[]
   */
  public String[] getHabilidadesCasta() {
    return casta.getHabilidadesCasta();
  }

  /**
   * cargarTablaNivel().
   *
   */
  public static void cargarTablaNivel() {
    Personaje.tablaDeNiveles = new int[101];
    Personaje.tablaDeNiveles[0] = 0;
    Personaje.tablaDeNiveles[1] = 0;
    for (int i = 2; i < 101; i++) {
      Personaje.tablaDeNiveles[i] = Personaje.tablaDeNiveles[i - 1] + 50;
    }
  }

  /**
   * Personaje(String, Casta, int).
   *
   * @param nombre
   *          nombre del personaje
   * @param casta
   *          casta a la cual pertenece
   * @param id
   *          identificador
   */
  public Personaje(String nombre, Casta casta, int id) {
    this.nombre = nombre;
    this.casta = casta;
    this.idPersonaje = id;
    experiencia = 0;
    nivel = 1;
    fuerza = 10;
    inteligencia = 10;
    destreza = 10;
    if (casta instanceof Guerrero) {
      fuerza += 5;
    }
    if (casta instanceof Hechicero) {
      inteligencia += 5;
    }
    if (casta instanceof Asesino) {
      destreza += 5;
    }

    posX = 0;
    posY = 0;
    saludTope = 100;
    energiaTope = 100;

    ataque = this.calcularPuntosDeAtaque();
    defensa = this.calcularPuntosDeDefensa();
    magia = this.calcularPuntosDeMagia();

  }

  /**
   * Personaje().
   *
   * @param nombre
   *          nombre del personaje
   * @param salud
   *          salud inicial
   * @param energia
   *          energia inicial
   * @param fuerza
   *          fuerza inicial
   * @param destreza
   *          destreza inicial
   * @param inteligencia
   *          inteligencia inicial
   * @param casta
   *          casta a la que pertenece
   * @param experiencia
   *          experiencia inicial
   * @param nivel
   *          nivel inicial
   * @param idPersonaje
   *          identificador del personaje
   */
  public Personaje(String nombre, int salud, int energia, int fuerza, int destreza, int inteligencia, Casta casta, int experiencia, int nivel, int idPersonaje) {

    this.nombre = nombre;
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
  }

  public String getNombreRaza() {
    return nombreRaza;
  }

  public void setNombreRaza(String nombreRaza) {
    this.nombreRaza = nombreRaza;
  }

  @Override
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public int getAtaque() {
    return ataque;
  }

  @Override
  public void setAtaque(int ataque) {
    this.ataque = ataque;
  }

  public int getMagia() {
    return magia;
  }

  public void setMagia(int magia) {
    this.magia = magia;
  }

  public Alianza getClan() {
    return clan;
  }

  public void setClan(Alianza clan) {
    this.clan = clan;
    clan.añadirPersonaje(this);
  }

  @Override
  public int getSalud() {
    return salud;
  }

  public void setSalud(int salud) {
    this.salud = salud;
  }

  public int getEnergia() {
    return energia;
  }

  public void setEnergia(int energia) {
    this.energia = energia;
  }

  public int getFuerza() {
    return fuerza;
  }

  public void setFuerza(int fuerza) {
    this.fuerza = fuerza;
  }

  public int getDestreza() {
    return destreza;
  }

  public void setDestreza(int destreza) {
    this.destreza = destreza;
  }

  public int getInteligencia() {
    return inteligencia;
  }

  public void setInteligencia(int inteligencia) {
    this.inteligencia = inteligencia;
  }

  public Casta getCasta() {
    return casta;
  }

  public void setCasta(Casta casta) {
    this.casta = casta;
  }

  public int getExperiencia() {
    return experiencia;
  }

  public void setExperiencia(int experiencia) {
    this.experiencia = experiencia;
  }

  public int getNivel() {
    return nivel;
  }

  public void setNivel(int nivel) {
    this.nivel = nivel;
  }

  public int getIdPersonaje() {
    return idPersonaje;
  }

  public void setIdPersonaje(int idPersonaje) {
    this.idPersonaje = idPersonaje;
  }

  public int getDefensa() {
    return defensa;
  }

  public void setDefensa(int defensa) {
    this.defensa = defensa;
  }

  public int getSaludTope() {
    return saludTope;
  }

  public void setSaludTope(int saludTope) {
    this.saludTope = saludTope;
  }

  public int getEnergiaTope() {
    return energiaTope;
  }

  public void setEnergiaTope(int energiaTope) {
    this.energiaTope = energiaTope;
  }

  @Override
  public int atacar(Peleable atacado) {
    if (salud == 0) {
      return 0;
    }
    if (atacado.getSalud() > 0) {
      if (MyRandom.nextDouble() <= (this.casta.getProbabilidadGolpeCritico() + (this.destreza / 1000))) {
        return atacado.serAtacado(this.golpe_critico());
      } else {
        return atacado.serAtacado(this.ataque);
      }
    }
    return 0;
  }

  public int golpe_critico() {
    return (int) (this.ataque * this.getCasta().getDañoCritico());
  }

  @Override
  public void despuesDeTurno() {

  }

  public boolean puedeAtacar() {
    return energia > 10;
  }

  public int calcularPuntosDeAtaque() {
    return (int) (this.getFuerza() * 1.5);
  }

  public int calcularPuntosDeDefensa() {
    return (this.getDestreza());
  }

  public int calcularPuntosDeMagia() {
    return (int) (this.getInteligencia() * 1.5);
  }

  public void restablecerSalud() {
    this.salud = this.saludTope;
  }

  public void restablecerEnergia() {
    this.energia = this.energiaTope;
  }

  /**
   * modificarAtributos().
   *
   */
  public void modificarAtributos() {
    this.ataque = this.calcularPuntosDeAtaque();
    this.defensa = this.calcularPuntosDeDefensa();
    this.magia = this.calcularPuntosDeMagia();
  }

  @Override
  public boolean estaVivo() {
    return salud > 0;
  }

  @Override
  public int serAtacado(int danio) {
    if (MyRandom.nextDouble() >= this.getCasta().getProbabilidadEvitarDaño()) {
      danio -= this.defensa;
      if (danio > 0) {
        if (salud <= danio) {
          danio = salud;
          salud = 0;
        } else {
          salud -= danio;
        }
        return danio;
      }
      return 0;
    }
    return 0;
  }

  /**
   * serRobadoSalud(int danio).
   *
   * @param danio
   *          daño producido
   * @return daño restante?_verificar
   */
  public int serRobadoSalud(int danio) {
    danio -= this.defensa;
    if (danio <= 0) {
      return 0;
    }
    if ((salud - danio) >= 0) {
      salud -= danio;
    } else {
      danio = salud;// le queda menos salud que el daño inflingido
      salud = 0;
    }
    return danio;
  }

  /**
   * serDesenergizado(int daño).
   *
   * @param danio
   *          daño a producir ?
   * @return daño producido?
   */
  public int serDesernegizado(int danio) {
    danio -= this.defensa;
    if (danio <= 0) {
      return 0;
    }
    if ((energia - danio) >= 0) {
      energia -= danio;
    } else {
      danio = energia;// le queda menos energia que el daño inflingido
      energia = 0;
    }
    return danio;
  }

  /**
   * serCurado(int Salud).
   *
   * @param salud
   *          salud final
   */
  public void serCurado(int salud) {
    if ((this.salud + salud) <= this.saludTope) {
      this.salud += salud;
    } else {
      this.salud = this.saludTope;
    }
  }

  /**
   * serEnergizado(int energia).
   *
   * @param energia
   *          energia recibida
   */
  public void serEnergizado(int energia) {
    if ((this.energia + energia) <= this.energiaTope) {
      this.energia += energia;
    } else {
      this.energia = this.energiaTope;
    }
  }

  /**
   * crearAlianza(string nombre_alianza).
   *
   * @param nombreAlianza
   *          nombre de la alianza
   */
  public void crearAlianza(String nombreAlianza) {
    this.clan = new Alianza(nombreAlianza);
    this.clan.añadirPersonaje(this);
  }

  /**
   * salirDeAlianza().
   *
   */
  public void salirDeAlianza() {
    if (this.clan != null) {
      this.clan.eliminarPersonaje(this);
      this.clan = null;
    }
  }

  /**
   * aliar(Personaje).
   *
   * @param nuevoAliado
   *          personaje con el cual se alia
   * @return true or false si pudo aliarse
   */
  public boolean aliar(Personaje nuevoAliado) {
    if (this.clan == null) {
      Alianza a = new Alianza("Alianza 1");
      this.clan = a;
      a.añadirPersonaje(this);
    }

    if (nuevoAliado.clan == null) {
      nuevoAliado.clan = this.clan;
      this.clan.añadirPersonaje(nuevoAliado);
      return true;
    } else {
      return false;
    }
  }

  /**
   * AsignarPuntosSkills(int, int, int).
   *
   * @param fuerza
   *          de skills
   * @param destreza
   *          de skills
   * @param inteligencia
   *          de skills
   */
  public void asignarPuntosSkills(int fuerza, int destreza, int inteligencia) {
    if ((this.fuerza + fuerza) <= 200) {
      this.fuerza += fuerza;
    }
    if ((this.destreza + destreza) <= 200) {
      this.destreza += destreza;
    }
    if ((this.inteligencia + inteligencia) <= 200) {
      this.inteligencia += inteligencia;
    }
    this.modificarAtributos();
  }

  /**
   * subirNivel().
   *
   */
  public void subirNivel() {

    int acumuladorExperiencia = 0;
    if (this.nivel == 100) {
      return;
    }
    while ((this.nivel != 100) && (this.experiencia >= (Personaje.tablaDeNiveles[this.nivel + 1] + acumuladorExperiencia))) {
      acumuladorExperiencia += Personaje.tablaDeNiveles[this.nivel + 1];
      this.nivel++;
      this.modificarAtributos();
      this.saludTope += 25;
      this.energiaTope += 20;
    }
    this.experiencia -= acumuladorExperiencia;
  }

  /**
   * ganarExperiencia(int exp).
   *
   * @param exp
   *          experiencia ganada
   * @return true or false si se pudo aplicar la experiencia
   */
  public boolean ganarExperiencia(int exp) {
    this.experiencia += exp;

    if (experiencia >= Personaje.tablaDeNiveles[this.nivel + 1]) {
      subirNivel();
      return true;
    }
    return false;
  }

  @Override
  public int otorgarExp() {
    return this.nivel * 40;
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  public double distanciaCon(Personaje p) {
    return Math.sqrt(Math.pow(this.posX - p.posX, 2) + Math.pow(this.posY - p.posY, 2));
  }

  public boolean habilidadCasta1(Peleable atacado) {
    return this.getCasta().habilidad1(this, atacado);
  }

  public boolean habilidadCasta2(Peleable atacado) {
    return this.getCasta().habilidad2(this, atacado);
  }

  public boolean habilidadCasta3(Peleable atacado) {
    return this.getCasta().habilidad3(this, atacado);
  }

  public abstract boolean habilidadRaza1(Peleable atacado);

  public abstract boolean habilidadRaza2(Peleable atacado);
}
