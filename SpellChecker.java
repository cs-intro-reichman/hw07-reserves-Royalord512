
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		String tail = str.substring(1);
		return tail;
	}

	public static int levenshtein(String word1, String word2) {
		int a = word1.length();
		int b = word2.length();

		if (b == 0)
			return a;

		if (a == 0)
			return b;

		if (Character.toLowerCase(word1.charAt(0)) ==
			Character.toLowerCase(word2.charAt(0)))
			return levenshtein(tail(word1), tail(word2));
		
		return 1 + Math.min( levenshtein(tail(word1), word2),
					Math.min( levenshtein(word1, tail(word2)),
					levenshtein(tail(word1), tail(word2)) ) ); // How can I write this more efficiently?

	}

	public static String[] readDictionary(String fileName) {

		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readLine();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {

		int distance = 0;
		int minDistance = threshold;
		String closest = "";

		for (int i = 0; i < dictionary.length; i++) {
			distance = levenshtein(word, dictionary[i]);
			if (distance <= minDistance) {
				minDistance = distance;
				closest = dictionary[i];
			}
		}

		if (closest == "")
			return word;

		return closest;

	}

}
