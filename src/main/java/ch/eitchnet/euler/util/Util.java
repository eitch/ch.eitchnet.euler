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

/**
 * @author Robert von Burg <eitch@eitchnet.ch>
 */
public class Util {

	/**
	 * Formats the given number of nanoseconds to a time like #h/m/s/ms/us/ns
	 * 
	 * @param nanos
	 *            the number of nanoseconds
	 * 
	 * @return format the given number of nanoseconds to a time like #h/m/s/ms/us/ns
	 */
	public static String formatNanoDuration(final long nanos) {
		if (nanos >= 3600000000000L) {
			return String.format("%.0fh", (nanos / 3600000000000.0D)); //$NON-NLS-1$
		} else if (nanos >= 60000000000L) {
			return String.format("%.0fm", (nanos / 60000000000.0D)); //$NON-NLS-1$
		} else if (nanos >= 1000000000L) {
			return String.format("%.0fs", (nanos / 1000000000.0D)); //$NON-NLS-1$
		} else if (nanos >= 1000000L) {
			return String.format("%.0fms", (nanos / 1000000.0D)); //$NON-NLS-1$
		} else if (nanos >= 1000L) {
			return String.format("%.0fus", (nanos / 1000.0D)); //$NON-NLS-1$
		} else {
			return nanos + "ns"; //$NON-NLS-1$
		}
	}

	public static boolean isPalindrom(long value) {
		if (value < 0)
			throw new IllegalArgumentException("Negative numbers are not palindrome!");
		if (value < 10)
			return true;
		String valueS = Long.valueOf(value).toString();
		int length = valueS.length();
		int middle = length / 2;
		for (int i = 0; i <= middle; i++) {
			if (valueS.charAt(i) != valueS.charAt(length - (i + 1))) {
				return false;
			}
		}

		return true;
	}
}
