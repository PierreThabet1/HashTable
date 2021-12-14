package hashtable;

/**
 * This class implements the HashTable from the scratch with almost all of its
 * methods.
 * 
 * @author Pierre
 *
 */
public class HashTable { // Begin of HashTable class.

	/**
	 * This returns the hashsize.
	 */
	private static int hashSize = 0;
	private ListNode[] table;

	public static void main(String[] args) { // Begin of main.


		HashTable table = new HashTable(2); 
		String key,value;

		while (true) {

			System.out.println(" Choose from the menu! ");
			System.out.println(" 1- test put(key, value) ");
			System.out.println(" 2- test get(key) ");
			System.out.println(" 3- test containsKey(key) ");
			System.out.println(" 4- test remove(key) ");
			System.out.println(" 5- Exit ");
			System.out.println(" Please enter your command: ");

			switch (TextIO.getlnInt()) {

			case 1 -> {

				System.out.println(" Key = ");
				key = TextIO.getln();

				System.out.println(" Value = ");
				value = TextIO.getln();

				table.put(key, value);
				break;

			}

			case 2 -> {

				System.out.println(" Key = ");
				key = TextIO.getln();

				System.out.println(" Value is " + table.get(key));
				break;

			}

			case 3 -> {

				System.out.println(" Key = ");
				key = TextIO.getln();

				System.out.println(" containsKey(" + key + ") is " + 
						table.containsKey(key));
				break;

			}

			case 4 -> {

				System.out.println(" Key = ");
				key = TextIO.getln();

				table.remove(key);
				break;

			}

			case 5 -> {

				return;

			}

			default -> {

				System.out.println(" Invalid command! ");
				break;


			}

			}

		}

	} // End of main.

	/**
	 * This class is for creating the ListNode with key, value and next properties.
	 * 
	 * @author Pierre
	 *
	 */
	private static class ListNode { // Begin of ListNode class.

		String key;
		String value;
		ListNode next;

	} // End of ListNode class.


	public HashTable() { // Begin of 1st constructor.

		table = new ListNode[12];

	} // End of 1st constructor.

	public HashTable(int size) { // Begin of 2nd constructor.

		table = new ListNode[size];

	} // End of 2nd constructor.

	/**
	 * This method takes a key as parameter and returns the value.
	 * If no value is found, then null is returned.
	 * 
	 * @param String key.
	 * @return String.
	 */
	public String get(String key) { // Begin of get method.

		int arrInd = hash(key);

		ListNode list = table[arrInd];

		while(list != null) {

			if(list.key.equals(key)) {

				return list.value;

			}

			list = list.next;

		}

		return null;

	} // End of get method.

	/**
	 * This method puts the given key and value together in the hashtable.
	 * If the key already exists and new value was given, the old value
	 * will be replaced by the new given value, and the key will still
	 * stay as before.
	 * 
	 * @param String key.
	 * @param String value.
	 */
	public void put(String key, String value) { // Begin of put method.

		int arrInd = hash(key);

		ListNode list = table[arrInd];

		while(list != null) {

			if(list.key.equals(key)) {

				break;

			}

			list = list.next;

		}

		if (list != null) {

			list.value = value;

		}

		else {

			ListNode newNode = new ListNode();
			newNode.key = key;
			newNode.value = value;
			newNode.next = table[arrInd];
			table[arrInd] = newNode;
			hashSize++;

		}


	} // End of put method.

	/**
	 * This method remove the key from the hashtable.
	 * 
	 * @param String key.
	 */
	public void remove(String key) { // Begin of remove method.

		int arrInd = hash(key);

		if (table[arrInd] == null) {

			return;

		}

		if ( table[arrInd].key.equals(key)) {

			table[arrInd] = table[arrInd].next;
			hashSize--;
			return;

		}

		ListNode prev = table[arrInd];
		ListNode current = prev.next;

		while (current != null && !current.key.equals(key)) {

			current = current.next;
			prev = current;

		}

		if (current != null) {

			prev.next = current.next;
			hashSize--;

		}

	} // End of remove method.

	/**
	 * This method returns true, if the key is found in the hashtable,
	 * and false otherwise.
	 * 
	 * @param String key.
	 * @return boolean.
	 */
	public boolean containsKey(String key) { // Begin of containsKey method.

		int arrInd = hash(key);

		ListNode list = table[arrInd];

		while(list != null) {

			if (list.key.equals(key)) {

				return true;

			}

			list = list.next;

		}

		return false;

	} // End of containsKey method.

	/**
	 * This method returns the size of the hashtable.
	 * 
	 * @return int.
	 */
	public int size() { // Begin of size method.

		return hashSize;

	} // End of size method.

	/**
	 * This method hashes the given key, sothat it not exceed the size of 
	 * the table.
	 * The returned int is the hashcode in the hashtable, where the key will
	 * be stored.
	 * 
	 * @param String key.
	 * @return int.
	 */
	private int hash(String key) { // Begin of hash method.

		return (Math.abs(key.hashCode() % table.length));

	} // End of hash method.

} // End of HashTable class.
