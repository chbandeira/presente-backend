package br.com.chbandeira.utilitario.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author Charlles
 * @since 18/04/2014
 */
public class GsonUtil {

	private static Gson gson;
	
	public static String serializar(Object objeto) {
		gson = getGsonBuilderExclusionStrategy().setExclusionStrategies(new GsonExclusionStrategy()).create();
		return gson.toJson(objeto);
	}
	
	public static <T> T deserializar(String json, Class<T> clazz) {
		return getGson().fromJson(json, clazz);
	}
	
	public static Gson getGson() {
		return new Gson();
	}
	
	public static GsonBuilder getGsonBuilderExclusionStrategy() {
		return new GsonBuilder();
	}

}
