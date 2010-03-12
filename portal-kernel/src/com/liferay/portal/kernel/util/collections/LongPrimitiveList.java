/*
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.kernel.util.collections;

/**
 * <a href="LongPrimitiveList.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class LongPrimitiveList {

	public LongPrimitiveList() {
		_elements = new long[10];
	}

	public LongPrimitiveList(int capacity) {
		_elements = new long[capacity];
	}

	public void addAll(long[] values) {
		checkCapacity(_numElements + values.length);

		System.arraycopy(values, 0, _elements, _numElements, values.length);

		_numElements += values.length;
	}

	public void add(long value) {
		checkCapacity(_numElements + 1);
		
		_elements[_numElements++] = value;
	}

	public long[] getArray() {
		trim();

		return _elements;
	}

	public int size() {
		return _numElements;
	}

	private void trim() {
		int oldSize = _elements.length;

		if (_numElements < oldSize) {

			long[] previousElements = _elements;

			_elements = new long[_numElements];

			System.arraycopy(previousElements, 0, _elements, 0, _numElements);
		}
	}

	private void checkCapacity(int minSize) {
		int oldSize = _elements.length;

		if (minSize > oldSize) {

			long[] previousElements = _elements;

			int newCapacity = (oldSize * 3) / 2 + 1;

			if (newCapacity < minSize) {
				newCapacity = minSize;
			}
			_elements = new long[newCapacity];

			System.arraycopy(previousElements, 0, _elements, 0, _numElements);
		}
	}

	private long[] _elements;
	private int _numElements;
}
