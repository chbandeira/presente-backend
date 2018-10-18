package br.com.chbandeira.utilitario.gson;

import br.com.chbandeira.utilitario.annotation.ExclusionStrategy;

import com.google.gson.FieldAttributes;

/**
 * 
 * @author Charlles
 * @since 18/04/2014
 */
public class GsonExclusionStrategy implements com.google.gson.ExclusionStrategy {

	private final Class<?> typeToSkip;

	public GsonExclusionStrategy() {
		typeToSkip = null;
	}
	
    public GsonExclusionStrategy(Class<?> typeToSkip) {
      this.typeToSkip = typeToSkip;
    }

    public boolean shouldSkipClass(Class<?> clazz) {
      return (clazz == typeToSkip);
    }

    public boolean shouldSkipField(FieldAttributes f) {
      return f.getAnnotation(ExclusionStrategy.class) != null;
    }

}
