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

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SieveOfEratosthenes implements Serializable {

	private static final long serialVersionUID = -2366203908363600724L;

	private static final Logger logger = LoggerFactory.getLogger(SieveOfEratosthenes.class);

	private int searchTo;
	private List<Number> numbers;
	private List<Number> possiblePrimes;
	private int numberOfPrimesFound;

	private boolean debug;

	public SieveOfEratosthenes(int searchTo, boolean debug) {
		this.debug = debug;
		this.searchTo = searchTo;

		if (this.debug)
			logger.info("Building numbers database with " + this.searchTo + " entries.");

		this.numbers = new LinkedList<>();
		for (int i = 2; i <= this.searchTo; i++) {
			this.numbers.add(new Number(i));
		}

		if (this.debug)
			logger.info("Building possiblePrimes database with " + this.searchTo + " entries.");

		this.possiblePrimes = new LinkedList<>(this.numbers);
		this.numberOfPrimesFound = 0;
	}

	public List<Integer> doSieveOfEratosthenes() {

		if (this.debug)
			logger.info("Starting...");

		// Ein Index mit dem nächsten Primzahl beim start muss das immer die Zahl 2
		// sein, was als erstes in der Reihe sein soll, deswegen wir hier darauf
		// geprüft:
		int indexOfPrimeNumber = 0;
		Number num = this.possiblePrimes.get(indexOfPrimeNumber);
		if (num.getNumber() != 2) {
			throw new IllegalArgumentException("The first number of a sieve calculation must always be the number "
					+ "2, which is a prime!!");
		}

		// schön, wir haben den ersten Primzahl, also setzen wir die Zahl als
		// Primzahl
		num.setPrime(true);
		this.numberOfPrimesFound++;

		// Hier fängt die Schlaufe des Algorithmus an
		while (true) {

			// setzen wir eine Variable mit dem zuletzt gefunden Primzahl
			int primeNumber = this.possiblePrimes.get(indexOfPrimeNumber).getNumber();

			// um keine Rechenzeit zu verschwenden, prüfen wir, ob der Quadrat des
			// aktuellen Primzahles grösser-gleich der des Bereichs ist, den wir
			// durchsuchen wollen
			if (primeNumber * primeNumber >= this.searchTo) {
				// da dies wahr ist, dann können wir alle noch nicht geprüften Zahlen in
				// der Liste als Primzahl setzen, nach den Regeln des Siebes
				for (Number number : this.possiblePrimes) {
					if (!number.isPrimeStatusSet()) {
						number.setPrime(true);
						this.numberOfPrimesFound++;
					}
				}

				// jetzt brechen wir den Sieb ab
				break;
			}

			// setzen wir eine Variable mit der nächsten Zahl, die keine Primzahl ist
			int nextNotPrime = primeNumber + primeNumber;

			// Hier iterieren wir über die Liste mit allen Zahlen welche noch nicht
			// berechnet wurden. Wenn wir eine Zahl finden, welche keine Primzahl ist,
			// dann wird sie aus performance Gründen aus der List heraus geläscht. So
			// wird die Zahl beim nächsten durchlauf nicht mehr berücksichtigt.
			for (Iterator<Number> iter = this.possiblePrimes.iterator(); iter.hasNext();) {
				// nächste "Number"-Objekt holen
				Number number = iter.next();
				// die eigentliche Zahl in eine Variable setzen
				int n = number.getNumber();
				// Falls der Status der Zahl noch nicht gesetzt wurde, dann...
				if (!number.isPrimeStatusSet()) {
					// ...falls die Zahl die nächste gesuchte Zahl ist, dann setze den
					// Status auf nicht Primzahl, lösche die Zahl aus der Liste und setze
					// die Variable für die nächste Zahl, welche keine Primzahl ist
					if (n == nextNotPrime) {
						number.setPrime(false);
						iter.remove();
						nextNotPrime += primeNumber;
						// Falls die Zahl grösser ist, als der gesuchte Wert, dann haben wir
						// die Zahl schon aus der Liste entfernt und können zum nächsten
						// Zahl erhöhen welche keine Primzahl ist.
					} else if (n > nextNotPrime) {
						nextNotPrime += primeNumber;
					}
				}
			}

			// da wir in der letzten Iteration Zahlen aus der Liste gelöscht haben,
			// können wir nicht davon ausgehen, dass wir den richtigen Index bestimmen
			// können, ohne ihn zu suchen, deswegen suchen wir von dem letzten Index
			// an weiter und nehmen die erste Zahl, wo keinen Status gesetzt wurde.
			// Diese ist dann unsere nächste Primzahl
			int currentIndexOfPrimeNumber = indexOfPrimeNumber;
			for (int i = indexOfPrimeNumber; i < this.possiblePrimes.size(); i++) {
				Number temp = this.possiblePrimes.get(i);
				if (!temp.isPrimeStatusSet()) {
					indexOfPrimeNumber = i;
					synchronized (temp) {
						temp.setPrime(true);
					}
					this.numberOfPrimesFound++;
					break;
				}
			}

			// Falls nach einer Iteration wir keinen neuen Index haben, dann gibt es
			// auch keine Zahlen mehr zu suchen, und können abbrechen
			if (currentIndexOfPrimeNumber == indexOfPrimeNumber) {
				break;
			}
		}

		//logger.info("Finished!");

		return this.possiblePrimes.stream().filter(n -> n.isPrime()).map(n -> Integer.valueOf(n.getNumber()))
				.collect(Collectors.toList());
	}

	public synchronized int getNumberOfPrimesFound() {
		return this.numberOfPrimesFound;
	}

	public List<Number> getNumbers() {
		return this.numbers;
	}

	public final class Number {

		private boolean isPrime;
		private final int number;
		private boolean isPrimeStatusSet;

		public Number(int number) {
			this.number = number;
			this.isPrime = false;
		}

		public boolean isPrime() {
			return this.isPrime;
		}

		public int getNumber() {
			return this.number;
		}

		public void setPrime(boolean prime) {
			this.isPrime = prime;
			this.isPrimeStatusSet = true;
		}

		public boolean isPrimeStatusSet() {
			return this.isPrimeStatusSet;
		}
	}
}