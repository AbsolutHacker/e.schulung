package de.esolutions.day7.regex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FindCharacterClass {
//    File ucDatabase;
    String characterClass;
    static final Pattern regexp;
    List<String> selectedLines;

    static {
        regexp = Pattern.compile(
          "([0-9A-F]+);([^;]*);([^;]*);[^;]*;[^;]*;[^;]*;[^;]*;([^;]*);.*"
        );
    }

    public FindCharacterClass(String dbFile, String characterClass) throws IOException {
//        ucDatabase = new File(dbFile);
        this.characterClass = characterClass;
        // select lines
        selectedLines = Files.lines(Paths.get(dbFile))
//          .map( l -> new UnicodeCharacter(l))
          .filter(l -> {
              Matcher m = regexp.matcher(l);
              if (m.matches() && m.group(3).equals(this.characterClass)) {
                  return true;
              }
              return false;
          })
          .collect(Collectors.toList());
    }
    public int countOf() {
        return selectedLines.size();
    }
    public void printAllSelectedLinesFormatted() {
        System.out.println("Unicode | Symbol | Val# | Symbol Name");
        for (String s : selectedLines) {
            Matcher sym = regexp.matcher(s);
            sym.matches();
            StringBuilder sb = new StringBuilder();
            sb.append(sym.group(1)).append(" | ");
//            sb.append((char) Integer.parseInt(sym.group(1), 16));
            sb.append(Character.toChars(Integer.parseInt(sym.group(1), 16)));
            sb.append(" | (").append(sym.group(4));
            sb.append(") | ").append(sym.group(2));
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) throws IOException {
        FindCharacterClass fcc = new FindCharacterClass("unicode/UnicodeData.txt", "Nd");
        System.out.println(fcc.countOf());
        fcc.printAllSelectedLinesFormatted();
    }

    static class UnicodeCharacter {
        int codePoint;
        String descriptiveName;
        String characterClass;

        public int getCodePoint() {
            return codePoint;
        }

        public String getDescriptiveName() {
            return descriptiveName;
        }

        public String getCharacterClass() {
            return characterClass;
        }

        public UnicodeCharacter(int codePoint) {
            this(codePoint, "", "");
        }

        public UnicodeCharacter(int codePoint, String descriptiveName, String characterClass) {
            this.codePoint = codePoint;
            this.descriptiveName = descriptiveName;
            this.characterClass = characterClass;
        }

        public UnicodeCharacter(String dbLine) {

        }
        @Override
        public String toString() {
            char[] wchar = new char[2];
            if (codePoint < 0x10000) {
                return Character.toString((char) codePoint);
            } else {
                codePoint -= 0x10000;
                wchar[0] = (char)(0xD800 + (codePoint >>> 10));
                wchar[1] = (char)(0xDC00 + (codePoint & 0x003FF));
            }
            return new String(wchar);}
    }
}
