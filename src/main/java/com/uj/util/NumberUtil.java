package com.uj.util;

import org.apache.commons.lang3.StringUtils;

/**
 * CreatedBy Upendra Joshi.
 */
public class NumberUtil {
	
	private NumberUtil() {
	}

	public static Double createDouble(String value) {
		if (StringUtils.isBlank(value))
			return null;
		return Double.valueOf(value);
	}

}
