import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import debug.Debug;
import debug.Debug.Color;

public class App {
    public static void main(String[] args) throws Exception {
        String text = Files.readString(Path.of("assets/teste-pratica-3-2.txt"));

        String[] patterns = {
                "TATATAAGAAAAAAG",
                "AGACTCTG",
                "GAGAGCGG",
                "TCCTCCCAC",
        };

        for (String pattern : patterns) {
            var bf = bruteForceSearch(text, pattern);
            var kmp = kmpSearch(text, pattern);

            Debug.log(Color.CYAN, "Pattern: " + pattern);
            Debug.log(Color.BLUE_BRIGHT, "Brute Force: " + bf);
            Debug.log(Color.BLUE_BRIGHT, "KMP:         " + kmp);
            Debug.log(Color.BLUE_BRIGHT, "----------------------------------------");
        }
    }

    public static List<Finding> bruteForceSearch(String string, String substring) {
        List<Finding> findings = new ArrayList<>();
        int comparisons = 0;

        for (int i = 0; i < string.length() - substring.length(); i++) {
            int j;
            for (j = 0; j < substring.length(); j++) {
                comparisons++;
                if (string.charAt(i + j) != substring.charAt(j))
                    break;
            }

            if (j == substring.length())
                findings.add(new Finding(i, comparisons));
        }

        return findings;
    }

    public static List<Finding> kmpSearch(String string, String substring) {
        List<Finding> findings = new ArrayList<>();
        int comparisons = 0;

        int[] lps = new int[substring.length()];
        int i = 1;
        int j = 0;
        while (i < substring.length()) {
            comparisons++;
            if (substring.charAt(i) == substring.charAt(j)) {
                lps[i] = j + 1;
                i++;
                j++;
            } else {
                if (j != 0)
                    j = lps[j - 1];
                else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        i = 0;
        j = 0;
        while (i < string.length()) {
            comparisons++;
            if (string.charAt(i) == substring.charAt(j)) {
                i++;
                j++;
            }

            if (j == substring.length()) {
                findings.add(new Finding(i - j, comparisons));
                j = lps[j - 1];
            } else {
                if (i < string.length() && string.charAt(i) != substring.charAt(j)) {
                    if (j != 0)
                        j = lps[j - 1];
                    else
                        i++;
                }
            }
        }

        return findings;
    }
}

record Finding(int index, long comparisons) {

    @Override
    public String toString() {
        return "index " + index + " (" + comparisons + " comparisons)";
    }
}
