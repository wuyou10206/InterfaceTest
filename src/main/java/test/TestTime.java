package test;

public class TestTime {
	public static void main(String[] args) {
		long time = 3*60*60*1000+5*60*1000+15*1000+13;
		System.out.println(time);
		System.out.println(time/60/60/1000);
		System.out.println(time%3600000/60000);
		System.out.println(time%60000/1000);
		System.out.println(time%1000);
	}
}
