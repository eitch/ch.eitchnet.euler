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

import ch.eitchnet.euler.util.Util;

/**
 * @author Robert von Burg <eitch@eitchnet.ch>
 */
public class Euler004_LargestPalindromeProduct extends EulerTest {

	private int nrOfDigits;

	/**
	 * @param n
	 */
	public Euler004_LargestPalindromeProduct(int n) {
		this.nrOfDigits = n;
	}

	/**
	 * @return
	 */
	public long[] calculate() {

		if (this.nrOfDigits == 1) {
			logger.error("Bad idea using this algorithm for 1 digits!");
			return new long[] { 3, 3 };
		}

		long nMin = Long.valueOf(Util.normalizeLength("9", this.nrOfDigits, false, '0'));
		long nMax = Long.valueOf(Util.normalizeLength("9", this.nrOfDigits, false, '9'));

		long minValue = nMin * nMin;
		long maxValue = nMax * nMax;
		long nextPossiblePalindrome = closestPalindrome(minValue, true);
		long previousPossiblePalindrome = closestPalindrome(maxValue, false);
		logger.info("Palindrome range for " + this.nrOfDigits + " digits is " + nextPossiblePalindrome + " - "
				+ previousPossiblePalindrome);

		long[] result = new long[2];

		for (long l1 = nMax; l1 > nMin; l1--) {

			for (long l2 = nMax; l2 > nMin; l2--) {
				if (Util.isPalindrom(l1 * l2)) {
					result[0] = l2;
					result[1] = l1;
					break;
				}
			}

			if (result[0] != 0)
				break;
		}

		if (result[0] == 0) {
			logger.info("Did not find a palindrome in range " + minValue + " - " + maxValue);
		}

		return result;
	}

	public static long closestPalindrome(long n, boolean up) {
		if (n < 10)
			return n;

		if (up)
			for (long l = n; l < n * 10; l++) {
				if (Util.isPalindrom(l))
					return l;
			}
		else
			for (long l = n; l > 0; l--) {
				if (Util.isPalindrom(l))
					return l;
			}

		throw new IllegalStateException("No palindrome found " + (up ? "above " : "below ") + n);
	}
}
