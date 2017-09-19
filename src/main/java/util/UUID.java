package util;

public class UUID {
	public static String uuid() {
		String uuid = java.util.UUID.randomUUID().toString();
		return uuid.replaceAll("-", "");
	}
	public static void main(String[] args) {
		System.out.println(uuid());
	}
	
}
