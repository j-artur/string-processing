package debug;

import java.util.Scanner;

public class Debug implements AutoCloseable {
  public enum Color {
    BLACK("\033[0;30m"),
    RED("\033[0;31m"),
    GREEN("\033[0;32m"),
    YELLOW("\033[0;33m"),
    BLUE("\033[0;34m"),
    PURPLE("\033[0;35m"),
    CYAN("\033[0;36m"),
    WHITE("\033[0;37m"),
    RESET("\033[0m"),
    BLACK_BRIGHT("\033[0;90m"),
    RED_BRIGHT("\033[0;91m"),
    GREEN_BRIGHT("\033[0;92m"),
    YELLOW_BRIGHT("\033[0;93m"),
    BLUE_BRIGHT("\033[0;94m"),
    PURPLE_BRIGHT("\033[0;95m"),
    CYAN_BRIGHT("\033[0;96m"),
    WHITE_BRIGHT("\033[0;97m");

    String string;

    Color(String str) {
      string = str;
    }

    @Override
    public String toString() {
      return string;
    }
  }

  private static Scanner scanner = new Scanner(System.in);

  public static String input(Color color, String prompt, Color inputColor) {
    System.out.print(color + prompt + inputColor);
    String input = scanner.nextLine();
    System.out.print(Color.RESET);
    return input;
  }

  public static String input(String prompt, Color inputColor) {
    System.out.print(Color.RESET + prompt + inputColor);
    String input = scanner.nextLine();
    System.out.print(Color.RESET);
    return input;
  }

  public static void log(String string) {
    System.out.println(Color.RESET + string + Color.RESET);
  }

  public static void log(Color color, String string) {
    System.out.println(color + string + Color.RESET);
  }

  public static String Char(char ch) {
    if (ch == '\0')
      return "'\\0'";
    else if (ch == '\b')
      return "'\\b'";
    else if (ch == '\t')
      return "'\\t'";
    else if (ch == '\n')
      return "'\\n'";
    else if (ch == '\f')
      return "'\\f'";
    else if (ch == '\r')
      return "'\\r'";
    else if (ch == '\"')
      return "'\\\"'";
    else if (ch == '\'')
      return "'\\''";
    else if (ch == '\\')
      return "'\\\\'";
    else if (ch >= ' ' && ch <= '~')
      return "'" + ch + "'";
    else
      return String.format("'\\u%04x'", (int) ch);
  }

  @Override
  public void close() {
    scanner.close();
  }
}
