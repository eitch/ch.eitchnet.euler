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

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ch.eitchnet.euler.EulerTest;

public class PrimeUtilTest extends EulerTest {

	@Test
	public void test1() {
		PrimeUtil primeUtil = new PrimeUtil();

		List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47);
		assertEquals(primes, primeUtil.getPrimesUptoN(50));
	}

	@Test
	public void test2() {

		PrimeUtil primeUtil = new PrimeUtil();

		List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67,
				71, 73, 79, 83, 89, 97);
		assertEquals(primes, primeUtil.getPrimesUptoN(100));
	}

	@Test
	public void test3() {

		PrimeUtil primeUtil = new PrimeUtil();
		primeUtil.getPrimesUptoN(100);

		assertEquals(2, primeUtil.getNthPrime(1));
		assertEquals(3, primeUtil.getNextPrime(2));

		assertEquals(13, primeUtil.getNthPrime(6));
		assertEquals(17, primeUtil.getNextPrime(13));

		assertEquals(281, primeUtil.getNthPrime(60));
		assertEquals(283, primeUtil.getNextPrime(281));
	}

	@Test
	public void test4() {

		PrimeUtil primeUtil = new PrimeUtil();
		logger.info("There are " + primeUtil.getPrimesUptoN(1000).size() + " primes with max value of 1000");
	}
}
