package dominio;

/**
 * La clase Guerrero hereda de Casta e implementa los metodos abstractos de su clase
 * padre, habilidad 1, 2 y 3. Es un tipo especifico de Casta.
 * Tambien define sus habilidades que son el ataque doble, aumentar la defensa
 * e ignorar la defensa.
 */

public class Guerrero extends Casta {

	private static final int INCREMENTO_ATAQUE = 2;

	/**
	 * Permite crear un nuevo Guerrero con con valores por parámetro.
	 * @param prob_crit es la probabilidad de golpe critico
	 * @param evasion evasion
	 * @param daño_crit es el daño crítico
	 */

	public Guerrero(final double prob_crit, final double evasion, final double daño_crit) {
		super("Guerrero", prob_crit, evasion, daño_crit);
	}

	/**
	 * Permite crear un nuevo Guerrero con sus habilidades por defecto.
	 */

	public Guerrero() {
		super("Guerrero", "Ataque Doble", "Aumentar Defensa", "Ignorar Defensa");
	}

	/**
	 * Ataque Doble
	 * Sobreescribe la habilidad1 de la clase padre.
	 * @param caster caster
	 * @param atacado atacado
	 * @return verdadero o falso para la utilización del ataque doble
	 * dependiendo del caster y el atacado.
	 */

	public boolean habilidad1(final Personaje caster, final Peleable atacado) {
		if (caster.getEnergia() > ENERGIA_MINIMA) {
			caster.setEnergia(caster.getEnergia() - ENERGIA_MINIMA);
			if (atacado.serAtacado(caster.ataque * INCREMENTO_ATAQUE) > 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Aumentar Defensa
	 * Sobreescribe la habilidad2 de la clase padre.
	 * @param caster caster
	 * @param atacado atacado
	 * @return verdadero o falso para la utilización de aumentar defensa
	 * dependiendo del caster y el atacado.
	 */

	public boolean habilidad2(final Personaje caster, final Peleable atacado) {
		if (caster.getEnergia() > ENERGIA_MINIMA) {
			caster.setEnergia(caster.getEnergia() - ENERGIA_MINIMA);
			caster.setDefensa(caster.getDefensa() + caster.magia);
			return true;
		}
		return false;
	}

	/**
	 * Ignorar Defensa
	 * Sobreescribe la habilidad3 de la clase padre.
	 * @param caster caster
	 * @param atacado atacado
	 * @return verdadero o falso para la utilización de aumentar defensa
	 * dependiendo del caster y el atacado.
	 */

	public boolean habilidad3(final Personaje caster, final Peleable atacado) {
		if (caster.getEnergia() > ENERGIA_MINIMA) {
			caster.setEnergia(caster.getEnergia() - ENERGIA_MINIMA);
			int defensa_original = ((Personaje) atacado).getDefensa();
			atacado.setDefensa(0);
			if (atacado.serAtacado(caster.ataque) > 0) {
				atacado.setDefensa(defensa_original);
				return true;
			}
		}
		return false;
	}

	/**
	 * @param personaje sobre el cual se incrementa la fuerza
	 */

	public void incrementarAtributoAlCrear(final Personaje personaje) {
		personaje.setFuerza(personaje.getFuerza() + INCREMENTO_POR_TIPO);
	}

}
