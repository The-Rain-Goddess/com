package com.rain.app.service.riot.api;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

abstract public class Dto {

	public String toString(boolean verbose) {
		return verbose ? toStringVerbosely(0) : toString();
	}

	public String toStringVerbosely(Integer depth) {
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");

		// Class Start
		result.append(this.getClass().getName()).append(" {").append(newLine);

		// Fields
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			for (int i = 0; i < depth + 1; i++) {
				result.append("   ");
			}
			try {
				result.append(field.getName()).append(": ");
				if (field.getType().getSuperclass() != null && field.getType().getSuperclass().equals(Dto.class) && field.get(this) != null) {
					// Dto Field
					result.append((String) this.getClass().getMethod("toStringVerbosely", Integer.class).invoke(field.get(this), depth + 1));
				} else {
					// Non-Dto Field
					result.append(field.get(this));
				}
			} catch (InvocationTargetException e) {
				e.getTargetException().printStackTrace();
			} catch (IllegalAccessException | NoSuchMethodException e) {
				e.printStackTrace();
			}
			result.append(newLine);
		}

		// Class End
		for (int i = 0; i < depth; i++) {
			result.append("   ");
		}
		result.append("}");

		return result.toString();
	}
}
