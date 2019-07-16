package de.zunk.vertretungsalarm.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class VertretungsplanTest {

	@Test
	void test() {
		String s = "Linux armv8l";
		assertEquals(true, s.contains("Linux arm"));
	}
}
