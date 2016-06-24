package com.lftechnology.tell.tale.util;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * @author Achyut Pokhrel <achyutpokhrel@lftechnology.com>
 * Jun 24, 2016
 * 
 */
public class NumberUtil {
	
	private static final SecureRandom RANDOM = new SecureRandom();
	
	public static String getRandom(){
		return new BigInteger(130, RANDOM).toString(32);
	}
}
