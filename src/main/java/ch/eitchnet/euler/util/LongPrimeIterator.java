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

import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Robert von Burg <eitch@eitchnet.ch>
 */
public class LongPrimeIterator implements Iterator<Long> {

	private static final Logger logger = LoggerFactory.getLogger(LongPrimeIterator.class);

	private static final BigInteger MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
	private BigInteger bi = BigInteger.valueOf(1L);
	private BigInteger max;

	public LongPrimeIterator() {
		this.max = MAX_LONG;
	}

	public LongPrimeIterator(long max) {
		if (max < 2) {
			throw new IllegalArgumentException("There are no prime numbers below 2, so max " + max + " is not valid!");
		}

		this.max = BigInteger.valueOf(max);
	}

	@Override
	public boolean hasNext() {
		return this.bi.nextProbablePrime().compareTo(max) < 0;
	}

	@Override
	public Long next() {
		if (!hasNext())
			throw new NoSuchElementException(getClass().getSimpleName() + " has no more elements (max " + this.max
					+ " value reached)!");

		this.bi = this.bi.nextProbablePrime();
		return bi.longValue();
	}

	/**
	 * @param b
	 * @return
	 */
	public List<Long> calculateAll(boolean logDuration) {

		long start = System.nanoTime();

		List<Long> all = new LinkedList<>();
		while (hasNext())
			all.add(next());

		if (logDuration)
			logger.info(Util.formatNanoDuration(System.nanoTime() - start));

		return all;
	}
}
