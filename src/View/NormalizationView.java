package View;

import java.io.*;
public class NormalizationView {
    private String text;

    public String getText() {
        return text;
    }


    public void loadData(String filePath) {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty())
                    continue;
                if (sb.length() == 0) {
                    line = line.replaceFirst(String.valueOf(line.charAt(0)), String.valueOf(Character.toUpperCase(line.charAt(0))));
                }
                sb.append(nomarlizeSpace(line));
            }
            text = sb.toString().trim();
        } catch (FileNotFoundException e) {
            System.out.println("File Error !!!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeStringToFile(String data, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(data);
            writer.flush();
            System.out.println("Write Successfully");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public String nomarlizeSpace(String input) {
        String[] s = input.split("\\s+");
        StringBuilder sb = new StringBuilder();
        int flag = 0, flag1 = 0;
        for (String value : s)
            for (int k = 0; k < value.toCharArray().length; k++) {
                char c = value.charAt(k);
                if (c == ',' || c == ':') {
                    if (sb.charAt(sb.length() - 1) == ' ')
                        sb.deleteCharAt(sb.length() - 1);
                    sb.append(c);
                    sb.append(' ');
                    continue;
                } else if (c == '.') {
                    if (sb.charAt(sb.length() - 1) == ' ')
                        sb.deleteCharAt(sb.length() - 1);
                    sb.append(c);
                    sb.append(' ');
                    flag = 1;
                    continue;
                } else if (Character.isLetter(c)) {
                    if (flag == 1) {
                        sb.append(Character.toUpperCase(c));
                        flag = 0;
                    } else
                        sb.append(c);
                } else {
                    if (flag1 == 0) {
                        if (sb.charAt(sb.length() - 1) != ' ')
                            sb.append(' ');
                        sb.append(c);
                        flag1 = 1;
                        continue;
                    } else if (sb.charAt(sb.length() - 1) == ' ') {
                        sb.deleteCharAt(sb.length() - 1);
                        sb.append(c);
                        sb.append(' ');
                        continue;
                    } else {
                        sb.append(c);
                        sb.append(' ');
                        continue;
                    }
                }
                if (k == value.toCharArray().length - 1) {
                    sb.append(' ');
                }
            }
        String normalizedText = sb.toString().trim();
        if (!normalizedText.isEmpty() && !normalizedText.endsWith(".")) {
            normalizedText = normalizedText + ".";
        }
        return normalizedText;
    }

    public void display(String data) {
        System.out.println("Your Data: ");
        System.out.print(nomarlizeSpace(data));
        System.out.println();
    }
}
