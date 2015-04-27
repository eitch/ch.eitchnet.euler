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

import ch.eitchnet.euler.util.PrimeGenerator;

/**
 * @author Robert von Burg <eitch@eitchnet.ch>
 */
public class Euler003_LargestPrimeFactor extends Euler {

	private long n;

	public Euler003_LargestPrimeFactor(long n) {
		this.n = n;
	}

	public long calculate() {

		PrimeGenerator pg = new PrimeGenerator(this.n / 2, false);
		long value = this.n;
		long prime = pg.next();
		while (pg.hasNext()) {
			if (value % prime == 0) {
				value /= prime;
				if (value == 1)
					break;
			}
			prime = pg.next();
		}

		return prime;
	}
}
