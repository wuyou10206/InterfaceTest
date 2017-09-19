package test;

import org.apache.log4j.Logger;

public class TestLog4j {
	public static void main(String[] args) {
		Logger logger = Logger.getLogger(TestLog4j.class);
		logger.debug("debug");
		logger.info("info");
		logger.error("error");
	}
}
