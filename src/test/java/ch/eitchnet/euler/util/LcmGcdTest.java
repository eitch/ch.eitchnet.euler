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
package ch.eitchnet.euler.util;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Test;

/**
 * @author Robert von Burg <eitch@eitchnet.ch>
 */
public class LcmGcdTest {

	@Test
	public void gcm1() {

		long n1 = 8;
		long n2 = 12;

		int expected = 4;
		validateGcd(n1, n2, expected);
	}

	@Test
	public void gcm2() {

		long n1 = 54;
		long n2 = 24;

		int expected = 6;
		validateGcd(n1, n2, expected);
	}

	private void validateGcd(long n1, long n2, int expected) {
		long lcm1 = LcmGcd.gcd(n1, n2);
		assertEquals(expected, lcm1);
	}

	@Test
	public void lcm1() {

		long[] n1 = new long[] { 21, 6 };
		BigInteger[] n2 = new BigInteger[] { BigInteger.valueOf(n1[0]), BigInteger.valueOf(n1[1]) };

		int expected = 42;
		validateLcm(n1, n2, expected);
	}

	private void validateLcm(long[] n1, BigInteger[] n2, int expected) {
		long lcm1 = LcmGcd.lcm(n1);
		assertEquals(expected, lcm1);
		BigInteger lcm2 = LcmGcd.lcm(n2);
		assertEquals(BigInteger.valueOf(expected), lcm2);
	}

	@Test
	public void lcm2() {

		long[] n1 = new long[] { 8, 9, 21 };
		BigInteger[] n2 = new BigInteger[] { BigInteger.valueOf(n1[0]), BigInteger.valueOf(n1[1]),
				BigInteger.valueOf(n1[2]) };

		int expected = 504;
		validateLcm(n1, n2, expected);
	}

	// TODO add gcm tests
}
