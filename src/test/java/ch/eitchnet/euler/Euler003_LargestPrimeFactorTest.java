/*
 * Copyright 2013 Robert von Burg <eitch@eitchnet.ch>
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
public class Euler003_LargestPrimeFactorTest extends EulerTest {

	@Test
	public void test13_195() {
		long n = 13_195L;
		Euler003_LargestPrimeFactor largestPrimeFactor = new Euler003_LargestPrimeFactor(n);
		long result = largestPrimeFactor.calculate();
		logger.info("Largest prime factor for " + 13_195L + " is " + result);
		assertEquals(29L, result);
	}

	@Test
	public void test600_851_475_143() {
		long n = 600_851_475_143L;
		Euler003_LargestPrimeFactor largestPrimeFactor = new Euler003_LargestPrimeFactor(n);
		long result = largestPrimeFactor.calculate();
		logger.info("Largest prime factor for " + 13_195L + " is " + result);
		assertEquals(6_857L, result);
	}
}
