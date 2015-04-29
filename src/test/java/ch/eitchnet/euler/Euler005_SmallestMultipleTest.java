/*
 * Copyright 2015 Robert von Burg <eitch@eitchnet.ch>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.eitchnet.euler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Robert von Burg <eitch@eitchnet.ch>
 */
public class Euler005_SmallestMultipleTest extends EulerTest {

	@Test
	public void test1To10() {

		int n = 10;
		long[] numbers = new long[n];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = i + 1;
		}

		Euler005_SmallestMultiple smallestMultiple = new Euler005_SmallestMultiple(numbers);
		long lcm = smallestMultiple.calculate();
		logger.info("LCM of numbers 1 to " + n + " = " + lcm);
		assertEquals(2520, lcm);
	}

	@Test
	public void test1To20() {

		int n = 20;
		long[] numbers = new long[n];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = i + 1;
		}

		Euler005_SmallestMultiple smallestMultiple = new Euler005_SmallestMultiple(numbers);
		long lcm = smallestMultiple.calculate();
		logger.info("LCM of numbers 1 to " + n + " = " + lcm);
		assertEquals(232792560, lcm);
	}
}
