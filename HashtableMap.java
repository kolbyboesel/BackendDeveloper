// --== CS400 Project One File Header ==--
// Name: Kolby Boesel
// CSL Username: kolby
// Email: kboesel@wisc.edu
// Lecture #: 003
// Notes to Grader: 

import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * This class creates a hashtable using a linkedList
 * 
 * @author kolbyboesel
 *
 * @param <KeyType>   An object that will be a key in the hashtable
 * @param <ValueType> An object that will be a value in the hashtable
 */
public class HashtableMap<KeyType, ValueType> {

	@SuppressWarnings("rawtypes")
	protected LinkedList[] hashtable; // The protected hashtable used throughout this class
	private int size; // A int size, that keeps track of the amount of key, value pairs in the
	// hashtable
	private int capacity; // A int capacity that keeps track of the total capacity of the hashtable

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList keySet() {
		ArrayList keys = new ArrayList();
		for (int i = 0; i < capacity; i++) {
			if (hashtable[i] != null) {
				Object tempObject = hashtable[i].clone();
				String currentKey = tempObject.toString();
				keys.add(currentKey);
			}
		}
		return keys;

	}

	/**
	 * This is a constructor to create a hashtable when a capacity is specified
	 * 
	 * @param capacity The capacity of the hashtable
	 */
	public HashtableMap(int capacity) {
		hashtable = new LinkedList[capacity];
		size = 0;
		this.capacity = capacity;
	}

	/**
	 * This is a constructor to create a hashtable when a capacity is not specified
	 */
	public HashtableMap() {
		hashtable = new LinkedList[15];
		size = 0;
		capacity = 15;
	}

	/**
	 * This is a helper method for the put method that rehashes the hashtable
	 */
	@SuppressWarnings("rawtypes")
	public void rehash() {
		capacity = 2 * capacity;
		LinkedList[] hashtableTemp = hashtable;
		hashtable = new LinkedList[capacity];
		for (int i = 0; i < capacity / 2; i++) {
			hashtable[i] = hashtableTemp[i];
		}

	}

	/**
	 * This method is a helper method that finds the hashKey of a given object
	 * 
	 * @param key
	 * @return hashIndex of the object from a given key
	 */
	public int hashKey(Object key) {
		int hashCode = key.hashCode();
		int hashIndex = hashCode % capacity;

		return hashIndex;
	}

	/**
	 * Removes a key and its value from the map.
	 * 
	 * @param key the key for the (key, value) pair to remove
	 * @return the value for the (key, value) pair that was removed, or null if the
	 *         map did not contain a mapping for key
	 */
	@SuppressWarnings("unchecked")
	public Object remove(Object key) {
		if (containsKey(key) == false) {
			return null;
		} else {
			Object value = getValue(hashtable[hashKey(key)]);
			hashtable[hashKey(key)] = null;
			size--;
			return value;
		}
	}

	/**
	 * Checks if a key is stored in the map.
	 * 
	 * @param key the key to check for
	 * @return true if the key is stored (mapped to a value) by the map and false
	 *         otherwise
	 */
	@SuppressWarnings("rawtypes")
	public boolean containsKey(Object key) {
		int index = hashKey(key);
		LinkedList tempList = hashtable[index];
		if (tempList != null && getKey(tempList) == key) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Returns the number of (key, value) pairs stored in the map.
	 * 
	 * @return the number of (key, value) pairs stored in the map
	 */
	public int size() {
		return size;
	}

	/**
	 * Removes all (key, value) pairs from the map.
	 */
	public void clear() {
		for (int i = 0; i < capacity; i++) {
			hashtable[i] = null;
		}
		size = 0;

	}

	/**
	 * Inserts a new (key, value) pair into the map if the map does not contain a
	 * value mapped to key yet.
	 * 
	 * @param key   the key of the (key, value) pair to store
	 * @param value the value that the key will map to
	 * @return true if the (key, value) pair was inserted into the map, false if a
	 *         mapping for key already exists and the new (key, value) pair could
	 *         not be inserted
	 */
	@SuppressWarnings("rawtypes")
	public boolean put(Object key, Object value) {

		int hashKey = hashKey(key);
		if (containsKey(key) == true) {
			return false;
		}

		if (hashtable[hashKey] == null) {
			LinkedList tempList = createPair(key, value);
			hashtable[hashKey] = tempList;
			size++;
			checkHash();
			return true;
		}

		else if (hashtable[hashKey] != null) {
			LinkedList tempList = createPair(key, value);
			while ((hashtable[hashKey] != null) || hashKey == capacity) {
				hashKey++;
			}

			if (hashKey == capacity) {
				hashKey = 0;
				while ((hashtable[hashKey] != null) || hashKey == capacity) {
					hashKey++;
				}
			}

			hashtable[hashKey] = tempList;
			size++;
			checkHash();
			return true;
		}

		else {
			return false;
		}

	}

	/**
	 * This is a helper method for put that checks if the hashtable needs to be
	 * rehashed
	 */
	public void checkHash() {
		double newSize = size;
		double newCapacity = capacity;
		double currentLoad = newSize / newCapacity;
		if (currentLoad >= 0.7) {
			rehash();
		}
	}

	/**
	 * This is a helper method for put that creates a key value pair to insert into
	 * the hashtable
	 * 
	 * @param thisKey The key to be inserted to the hashtable
	 * @param thisVal The value to be inserted to the hashtable
	 * @return The linkedlist with a key value pair
	 */
	@SuppressWarnings("rawtypes")
	public LinkedList createPair(Object thisKey, Object thisVal) {
		LinkedList<Object> keyValuePair = new LinkedList<Object>();
		keyValuePair.add(0, thisKey);
		keyValuePair.add(1, thisVal);

		return keyValuePair;

	}

	/**
	 * This method returns the value of a given key
	 * 
	 * @param keyValuePair The linked list containing a key value pair
	 * @return the Value of the given key
	 */

	@SuppressWarnings("rawtypes")
	public Object getValue(LinkedList keyValuePair) {
		Object value;

		value = keyValuePair.get(1);
		return value;
	}

	/**
	 * This method returns the key of a given key, value pair
	 * 
	 * @param keyValuePair The linked list containing a key value pair
	 * @return the key of the given key, value pair
	 */
	@SuppressWarnings("rawtypes")
	public Object getKey(LinkedList keyValuePair) {
		Object value;

		value = keyValuePair.get(0);
		return value;
	}

	/**
	 * Returns the value mapped to a key if the map contains such a mapping.
	 * 
	 * @param key the key for which to look up the value
	 * @return the value mapped to the key
	 * @throws NoSuchElementException if the map does not contain a mapping for the
	 *                                key
	 */
	@SuppressWarnings({ "rawtypes" })
	public Object get(Object key) throws NoSuchElementException {
		LinkedList tempList = hashtable[hashKey(key)];
		Object value = null;

		checkHash();

		if (hashtable[hashKey(key)] != null) {

			for (int k = 0; k < capacity - 1; k++) {
				if (getKey(tempList) != key && hashtable[k + 1] != null) {

					tempList = hashtable[k + 1];

				} else if (getKey(tempList) == key) {
					value = getValue(tempList);
					return value;
				}
			}
		}

		throw new NoSuchElementException();

	}

}