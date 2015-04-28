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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Robert von Burg <eitch@eitchnet.ch>
 */
public class Euler004_LargestPalindromeProductTest extends EulerTest {

	@Test
	public void test1Digit() {
		int n = 1;
		Euler004_LargestPalindromeProduct palindrome = new Euler004_LargestPalindromeProduct(n);
		long[] result = palindrome.calculate();
		logger.info("Largest palindrom product with " + n + " digits is " + result[0] + "*" + result[1] + "="
				+ (result[0] * result[1]));
		assertArrayEquals(new long[] { 3L, 3L }, result);
		assertEquals(9, result[0] * result[1]);
	}

	@Test
	public void test2Digit() {
		int n = 2;
		Euler004_LargestPalindromeProduct palindrome = new Euler004_LargestPalindromeProduct(n);
		long[] result = palindrome.calculate();
		logger.info("Largest palindrom product with " + n + " digits is " + result[0] + "*" + result[1] + "="
				+ (result[0] * result[1]));
		assertArrayEquals(new long[] { 91L, 99L }, result);
		assertEquals(9009, result[0] * result[1]);
	}

	@Test
	public void test3Digit() {
		int n = 3;
		Euler004_LargestPalindromeProduct palindrome = new Euler004_LargestPalindromeProduct(n);
		long[] result = palindrome.calculate();
		logger.info("Largest palindrom product with " + n + " digits is " + result[0] + "*" + result[1] + "="
				+ (result[0] * result[1]));
		//assertArrayEquals(new long[] { 583L, 995L }, result);
		//assertEquals(580085, result[0] * result[1]);
		//assertArrayEquals(new long[] { 902L, 909L }, result);
		//assertEquals(819918L, result[0] * result[1]);
		assertArrayEquals(new long[] { 913L, 993L }, result);
		assertEquals(906609L, result[0] * result[1]);
	}
}
