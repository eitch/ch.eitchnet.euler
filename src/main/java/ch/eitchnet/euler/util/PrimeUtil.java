package ch.eitchnet.euler.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class PrimeUtil {

	private static final boolean COMPOSITE = true;
	private static final int DEFAULT_SIZE = 100;

	// cache of primes.
	public final List<Integer> primes;
	public int cachedMaxPrime;

	public PrimeUtil() {
		primes = new ArrayList<Integer>();
		// initial seed
		primes.addAll(Arrays.asList(2, 3, 5, 7, 11, 13));
		cachedMaxPrime = primes.get(primes.size() - 1);
	}

	private void validatePositive(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("Expecting a non-zero value");
		}
	}

	private void validateOutOfBound(int n) {
		// resulted in int-overflow.
		if (n <= 0) {
			throw new IllegalArgumentException("The value is too large to calculate");
		}
	}

	/**
	 * Find the nth prime. ie if n == 6, then return 13.
	 * 
	 * @param n
	 *            the nth prime
	 * @return the prime at nth position
	 */
	public synchronized int getNthPrime(int n) {
		validatePositive(n);

		if (n <= primes.size()) {
			return primes.get(n - 1);
		}
		int size = cachedMaxPrime + DEFAULT_SIZE; // adding cachedMaxPrime to DEFAULT_SIZE is a tiny optimization, nothing else.
		while (primes.size() < n) {
			validateOutOfBound(size);
			computePrimesUptoN(size);
			size += DEFAULT_SIZE;
		}
		return primes.get(n - 1);
	}

	/**
	 * Given an input prime, return the next prime. ie, if prime == 13 then return 17, ie 17 is the next prime of 13.
	 * 
	 * @param prime
	 *            the prime number whose next should be found
	 * @return the next prime of the input prime.
	 */
	public synchronized int getNextPrime(int prime) {
		validatePositive(prime);

		int primeIndex = Collections.binarySearch(primes, prime);
		if (primeIndex != -1 && primeIndex != primes.size()) {
			return primes.get(primeIndex + 1);
		}
		int prevSize = primes.size();
		int size = cachedMaxPrime + DEFAULT_SIZE; // adding cachedMaxPrime to DEFAULT_SIZE is a tiny optimization, nothing else.
		while (primes.size() == prevSize) {
			validateOutOfBound(size);
			computePrimesUptoN(size);
			size += DEFAULT_SIZE;
		}
		return primes.get(primeIndex + 1);
	}

	/**
	 * Given an input n, find all primes from 0 to n Returns an unmodifiable list.
	 * 
	 * @param n
	 *            the number upto which the primes should be calculated
	 * @return An unmodifiable list.
	 */
	public synchronized List<Integer> getPrimesUptoN(int n) {
		validatePositive(n);
		validateOutOfBound(n);

		if (n < cachedMaxPrime) {
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; primes.get(i) <= n; i++) {
				list.add(i);
			}
			return Collections.unmodifiableList(list);
		}
		return Collections.unmodifiableList(computePrimesUptoN(n));
	}

	private List<Integer> computePrimesUptoN(int n) {
		// composite is name of the sieve, ie nothing else but the sieve.
		// optimizing the sieve size, but trimming it to "n - cacheprime"
		boolean[] composites = new boolean[n - cachedMaxPrime];
		int root = (int) Math.sqrt(n);

		// loop through all "first prime upto max-cached-primes"

		/*
		 * We need i <= root, and NOT i < root Try cache of {3, 5, 7} and n of 50. you will really why
		 */
		for (int i = 0; i < primes.size() && primes.get(i) <= root; i++) {
			int prime = primes.get(i);

			// get the first odd multiple of this prime, greater than max-prime
			int firstPrimeMultiple = (cachedMaxPrime + prime) - ((cachedMaxPrime + prime) % prime);
			if (firstPrimeMultiple % 2 == 0) {
				/*
				 * since we know that no even number other than 2 can be a prime, we only want to consider odd numbers
				 * while filtering.
				 */
				firstPrimeMultiple += prime;
			}
			filterComposites(composites, prime, firstPrimeMultiple, n);
		}

		// loop through all primes in the range of max-cached-primes upto root.
		for (int prime = cachedMaxPrime + 2; prime < root; prime = prime + 2) {
			if (!composites[prime]) {
				// selecting all the prime numbers.
				filterComposites(composites, prime, prime, n);
			}
		}

		// by doing i + 2, we essentially skip all even numbers
		// also skip cachedMaxPrime, since quite understandably its already cached.
		for (int i = 1; i < composites.length; i = i + 2) {
			if (!composites[i]) {
				primes.add(i + cachedMaxPrime + 1);
			}
		}

		cachedMaxPrime = primes.get(primes.size() - 1);
		return primes;
	}

	private void filterComposites(boolean[] composites, int prime, int firstMultiple, int n) {
		// we want to add prime, twice to the multiple so that we only bother to filter odd-numbers.
		for (int multiple = firstMultiple; multiple < n; multiple += prime + prime) {
			// eg: assume n = 50, cachemax = 13, and multiple = 15, then 15 should be at composite position of 1.
			composites[multiple - cachedMaxPrime - 1] = COMPOSITE;
		}
	}
}