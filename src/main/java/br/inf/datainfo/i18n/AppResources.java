package br.inf.datainfo.i18n;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Classe responsável por carregar os resources da aplicação
 * @author Ariel Rai Rodrigues (arielrairodrigues@gmail.com)
 *
 */
public class AppResources {

	private static ResourceBundle bundle;

	/**
	 * Retorna a mensagem para chave especificada
	 * 
	 * @param key
	 *            - chave que será buscada a mensagem
	 * @return uma {@link String} com a mensagem
	 */
	private static ResourceBundle getBundle() {
		synchronized (AppResources.class) {
			if (bundle == null) {
				bundle = ResourceBundle.getBundle("app");
			}
		}
		return bundle;
	}

	/**
	 * Retorna a mensagem 
	 * @param key
	 * @param params
	 * @return
	 */
	public static String getMessage(String key, Object... params) {
		if (params != null && params.length > 0) {
			return MessageFormat.format(getBundle().getString(key), params);
		} else{
			return getBundle().getString(key);
		}
	}
}