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
public class Euler001_SumOfMultiplesTest extends EulerTest {

	@Test
	public void shouldCalculateSumOf3and5Max10() {
		Euler001_SumOfMultiples sumOfMultiples = new Euler001_SumOfMultiples(10l, new long[] { 3L, 5L });
		long result = sumOfMultiples.calculate();
		logger.info("Sum of %3 or %5 of max 10 = " + result);
		assertEquals(23, result);
	}

	@Test
	public void shouldCalculateSumOf3and5Max1000() {

		Euler001_SumOfMultiples sumOfMultiples = new Euler001_SumOfMultiples(1000l, new long[] { 3L, 5L });
		long result = sumOfMultiples.calculate();
		logger.info("Sum of %3 or %5 of max 1000 = " + result);
		assertEquals(233168, result);
	}
}
