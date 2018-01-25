package spellchecker;

public class Main {

	public static void main(String[] args) throws Exception {
		String a[] = { "1", "2", "3", "4", "5", "6" };
		BasicDictionary now = new BasicDictionary();
		now.importFile("full_dictionary.txt");
		// String key = "vast";
		// now.display(now.root);
		// System.out.println("nice");
		// then.display(then.root);
		String words = "zyzzyvas";
		if (now.find(words) == null) {
			System.out.println("found");
		} else {
			String[] temp = now.find(words);
			System.out.println(temp[0] + " " + temp[1]);
		}

		// String key = "dozen";
		// String result[] = dictionary.find(key);

		// if (result == null) {
		// System.out.println("Word found in dict");
		// } else {
		// System.out.println("Word does not exist in dict");
		// for (String r : result) {
		// System.out.println(r);
		// }
		// }

	}

}
