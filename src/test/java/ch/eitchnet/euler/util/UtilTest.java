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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Robert von Burg <eitch@eitchnet.ch>
 */
public class UtilTest {

	@Test
	public void shouldSeePalindrom1() {
		assertTrue(Util.isPalindrom(0));
	}

	@Test
	public void shouldSeePalindrom2() {
		assertTrue(Util.isPalindrom(1));
	}

	@Test
	public void shouldSeePalindrom3() {
		assertTrue(Util.isPalindrom(11));
	}

	@Test
	public void shouldSeePalindrom4() {
		assertTrue(Util.isPalindrom(22));
	}

	@Test
	public void shouldSeePalindrom5() {
		assertTrue(Util.isPalindrom(9009));
	}

	@Test
	public void shouldSeePalindrom6() {
		assertTrue(Util.isPalindrom(10201));
	}

	@Test
	public void shouldNotSeePalindrom1() {
		assertFalse(Util.isPalindrom(10202));
	}

	@Test
	public void shouldNotSeePalindrom2() {
		assertFalse(Util.isPalindrom(13202));
	}

	@Test
	public void shouldNotSeePalindrom3() {
		assertFalse(Util.isPalindrom(3202));
	}
}
