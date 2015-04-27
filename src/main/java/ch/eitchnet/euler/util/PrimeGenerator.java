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

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Robert von Burg <eitch@eitchnet.ch>
 */
public class PrimeGenerator implements Iterator<Long> {

	private static final Logger logger = LoggerFactory.getLogger(PrimeGenerator.class);

	private LinkedList<Long> primeCache;

	private long max;
	private long nextPrime;

	private boolean debug;

	public PrimeGenerator(long max, boolean debug) {
		this.debug = debug;
		if (max < 2) {
			throw new IllegalArgumentException("There are no prime numbers below 2, so max " + max + " is not valid!");
		}

		this.max = max;
		this.nextPrime = 2; // initialize on first prime
		this.primeCache = new LinkedList<>();
		this.primeCache.add(this.nextPrime);
	}

	@Override
	public Long next() {
		if (!hasNext())
			throw new NoSuchElementException("PrimeNumberGenerator has no more elements (max " + this.max
					+ " value reached)!");

		long currentPrime = this.nextPrime;

		while (true) {
			this.nextPrime += 1;
			if (isPrime(this.nextPrime))
				break;
		}

		this.primeCache.add(this.nextPrime);
		return currentPrime;
	}

	private boolean isPrime(long last) {
		if (debug)
			logger.info("Checking " + last);
		for (Long prime : this.primeCache) {

			if (last / 2 < prime) {
				if (debug)
					logger.info(last + " is prime with pre-break as " + last + " / 2 < " + prime);
				return true;
			}

			boolean divisibleByPrime = last % prime == 0;

			if (debug)
				logger.info(last + " % " + prime + " == " + divisibleByPrime);

			if (divisibleByPrime)
				return false;
		}

		if (debug)
			logger.info(last + " is prime!");
		return true;
	}

	@Override
	public boolean hasNext() {
		return this.nextPrime <= this.max;
	}

	public List<Long> calculateAll(boolean logDuration) {

		long start = System.nanoTime();

		while (hasNext())
			next();
		// for hasNext to work, we always are one prime ahead, so remove it before returning
		this.primeCache.removeLast();

		if (logDuration)
			logger.info(Util.formatNanoDuration(System.nanoTime() - start));

		return Collections.unmodifiableList(this.primeCache);
	}
}
