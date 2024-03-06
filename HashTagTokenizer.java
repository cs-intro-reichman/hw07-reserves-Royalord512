

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readLine();
		}

		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		
		for (int i = 0; i < dictionary.length; i++) {
			if (dictionary[i].equals(word)) { //Why can't I do word == dictionary[i]?
				return true;
			}
		}

		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
 
        int N = hashtag.length();
		String hashtagLower = hashtag.toLowerCase();

        for (int i = 1; i <= N; i++) {
			if (existInDictionary(hashtagLower.substring(0, i), dictionary)) {
				System.out.println(hashtagLower.substring(0, i));
				breakHashTag(hashtagLower.substring(i, N), dictionary);
				break; // Can I do this without break?
				// I thought of doing with an outside variable called counter but didn't understand the error revolving around the static.
			}
        }


    }
}
