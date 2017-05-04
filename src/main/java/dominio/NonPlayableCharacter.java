package dominio;

/**
 * Clase que posee los atributos y funciones del NonPlayableCharacter.
 *
 */
public class NonPlayableCharacter implements Peleable {

  private int salud;
  private int fuerza;
  private int defensa;
  private String nombre;
  private int nivel;
  private static final int dificultadAleatoria = -1;

  public NonPlayableCharacter(String nombre, int nivel, int dificultadNPC) {
    this.nombre = nombre;
    this.nivel = nivel;
    int dificultad;
    if (dificultadNPC == dificultadAleatoria) {
      dificultad = MyRandom.nextInt(3);
    } else {
      dificultad = dificultadNPC;
    }

    switch (dificultad) {
      case 0:
        this.fuerza = 10 + ((nivel - 1) * 3);// 30%
        this.salud = 30 + ((nivel - 1) * 15);
        this.defensa = 2 + ((nivel - 1) * 1);
        break;
      case 1:
        this.fuerza = 20 + ((nivel - 1) * 6);// 50%
        this.salud = 40 + ((nivel - 1) * 20);
        this.defensa = 5 + ((nivel - 1) * 2);
        break;
      case 2:
        this.fuerza = 30 + ((nivel - 1) * 10);// 50%
        this.salud = 50 + ((nivel - 1) * 25);
        this.defensa = 4 + ((nivel - 1) * 4);
        break;
    }
  }

  @Override
  public int otorgarExp() {
    return this.nivel * 30;
  }

  /**
   * getFuerza().
   *
   * @return Devuelve el numero de la fuerza del personaje solicitado
   */
  public int getFuerza() {
    return fuerza;
  }

  public void setFuerza(int fuerza) {
    this.fuerza = fuerza;
  }

  /*
   * (non-Javadoc)
   *
   * @see dominio.Peleable#getNombre() Devuelve el nombre del personaje
   * solicitado
   */
  @Override
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getNivel() {
    return nivel;
  }

  public void setNivel(int nivel) {
    this.nivel = nivel;
  }

  @Override
  public boolean estaVivo() {
    return salud > 0;
  }

  public int getDefensa() {
    return defensa;
  }

  public void setDefensa(int defensa) {
    this.defensa = defensa;
  }

  @Override
  public int getSalud() {
    return salud;
  }

  public void setSalud(int salud) {
    this.salud = salud;
  }

  @Override
  public int atacar(Peleable atacado) {
    if (MyRandom.nextDouble() <= 0.15) {// los NPC tienen 15% de golpes criticos
      return atacado.serAtacado((int) (this.getAtaque() * 1.5));
    } else {
      return atacado.serAtacado(this.getAtaque());
    }
  }

  @Override
  public int serAtacado(int daño) {
    if (MyRandom.nextDouble() >= 0.15) {
      daño -= this.getDefensa() / 2;
      if (daño > 0) {
        salud -= daño;
        return daño;
      }
      return 0;// no le hace daño ya que la defensa fue mayor
    }
    return 0;// esquivo el golpe
  }

  @Override
  public void despuesDeTurno() {
  }

  public void ganarExperiencia(int exp) {

  }

  @Override
  public int getAtaque() {
    return fuerza;
  }

  @Override
  public void setAtaque(int ataque) {
    this.fuerza = ataque;
  }
}
