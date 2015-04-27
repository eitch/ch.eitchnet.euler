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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

import ch.eitchnet.euler.EulerTest;

/**
 * @author Robert von Burg <eitch@eitchnet.ch>
 */
public class PrimeGeneratorTest extends EulerTest {

	@Test
	public void shouldGeneratePrimesMax10() {

		PrimeGenerator primeGenerator = new PrimeGenerator(10, false);

		Long prime;

		prime = primeGenerator.next();
		assertEquals(2L, prime.longValue());
		prime = primeGenerator.next();
		assertEquals(3L, prime.longValue());
		prime = primeGenerator.next();
		assertEquals(5L, prime.longValue());
		prime = primeGenerator.next();
		assertEquals(7L, prime.longValue());

		try {
			primeGenerator.next();
			fail("Next should throw Exception as end of iteration should be reached!");
		} catch (NoSuchElementException e) {
			// good!
		}

		assertFalse("The next prime is 11, and should not be returned with limit 10!", primeGenerator.hasNext());
	}

	@Test
	public void shouldFindPrimesSmaller10() {
		List<Long> primes = new PrimeGenerator(10, false).calculateAll(true);
		assertEquals(4, primes.size());
	}

	@Test
	public void shouldFindPrimesSmaller1000() {
		List<Long> primes = new PrimeGenerator(1000, false).calculateAll(true);
		assertEquals(168, primes.size());
	}

	@Test
	public void shouldFindPrimesSmaller7920() {
		List<Long> primes = new PrimeGenerator(7920, false).calculateAll(true);
		assertEquals(1000, primes.size());
	}
}
