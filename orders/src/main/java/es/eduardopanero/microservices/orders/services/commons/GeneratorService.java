package es.eduardopanero.microservices.orders.services.commons;

import java.util.Random;

public class GeneratorService {

	public static byte[] generateSalt(){
		Random rand = new Random();
		byte[] salt = new byte[32];
		rand.nextBytes(salt);
		return salt;
	}

	public static String generateRandomString(){
		Random rand = new Random();
		String randomChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder sb = new StringBuilder();
		while (sb.length() < 50) {
			int index = (int) (rand.nextFloat() * randomChars.length());
			sb.append(randomChars.charAt(index));
		}
		return sb.toString();
	}
}
