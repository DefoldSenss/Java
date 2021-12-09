package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PR_25_26 {
    public static void main(String[] args) {
        new MosMetroParser();
    }

    public static class MosMetroParser {
        public MosMetroParser() {
            lines();
        }

        private Document getpage() throws IOException {
            return (Jsoup.connect(" https://www.moscowmap.ru/metro.html#lines").get());
        }

        private void lines() {
            int k = 1;
            StringBuilder lines = new StringBuilder("\"lines\" : [ \n ");
            StringBuilder stations = new StringBuilder("\"stations\" : { \n ");
            Document doc;
            try {
                doc = getpage();
            } catch (IOException exit) {
                exit.printStackTrace();
                return;
            }
            for (int i = 1; i <= 15; i++) {
                Elements link = doc.select("[data-line=\"" + i + "\"]");
                link = link.select("span");
                String pConcatenated = "";
                Pattern pattern1 = Pattern.compile("\\d+");
                Matcher matcher;
                for (Element x : link) {
                    matcher = pattern1.matcher(x.text());
                    if (matcher.find())
                        pConcatenated = pConcatenated + x.text();
                    else
                        pConcatenated = pConcatenated + x.text() + " ";
                }
                pattern1 = Pattern.compile("([а-яА-Я]+.[а-яА-Я]+[' '][а-яА-Я]*)");
                Matcher matchcer = pattern1.matcher(pConcatenated);
                if (matchcer.find()) {
                    lines.append("{\n").append("\"number\" : ").append(i).append(",\n\"name\" : \"").append(matchcer.group()).append("\"\n},\n");
                    pConcatenated = matchcer.replaceFirst("");
                }
                pattern1 = Pattern.compile("\\d+.[а-яА-Я]+.[а-яА-Я]*\\s?[а-яА-Я]*");
                matchcer = pattern1.matcher(pConcatenated);

                while (matchcer.find()) {
                    pConcatenated = matchcer.replaceFirst("");
                    String a = matchcer.group();
                    Pattern pattern2 = Pattern.compile("[а-яА-Я]+.[а-яА-Я]*\\s?[а-яА-Я]*");
                    matchcer = pattern2.matcher(a);
                    if (matchcer.find())
                        a = matchcer.group();
                    stations.append("\"").append(k).append("\" : [").append("\n\"").append(a).append("\",\n").append("\"").append(i).append("\",\n],\n");
                    matchcer = pattern1.matcher(pConcatenated);
                    k++;
                }
            }
            for (int i = 1; i <= 2; i++) {
                Elements link = doc.select("[data-line=\"" + "D" + i + "\"]");
                link = link.select("span");
                StringBuilder pConcatenated = new StringBuilder();
                Pattern pattern1 = Pattern.compile("\\d+");
                Matcher matcher;
                for (Element x : link) {
                    matcher = pattern1.matcher(x.text());
                    if (matcher.find())
                        pConcatenated.append(x.text());
                    else
                        pConcatenated.append(x.text()).append(" ");
                }
                pattern1 = Pattern.compile("([М][Ц][Д].[1,2])");
                Matcher matchcer = pattern1.matcher(pConcatenated.toString());
                if (matchcer.find()) {
                    lines.append("{\n").append("\"number\" : ").append("D").append(i).append(",\n\"name\" : \"").append(matchcer.group()).append("\"\n},\n");
                }
                pattern1 = Pattern.compile("\\d+.[а-яА-Я]+.[а-яА-Я]*\\s?[а-яА-Я]*");
                matchcer = pattern1.matcher(pConcatenated.toString());

                while (matchcer.find()) {
                    pConcatenated = new StringBuilder(matchcer.replaceFirst(""));
                    String a = matchcer.group();
                    Pattern pattern2 = Pattern.compile("[а-яА-Я]+.[а-яА-Я]*\\s?[а-яА-Я]*");
                    matchcer = pattern2.matcher(a);
                    if (matchcer.find())
                        a = matchcer.group();
                    stations.append("\"").append(k).append("\" : [").append("\n\"").append(a).append("\",\n").append("\"D").append(i).append("\",\n],\n");
                    matchcer = pattern1.matcher(pConcatenated.toString());
                    k++;
                }
            }
            k = 1;
            Elements link = doc.select("[data-line=\"11A\"]");
            link = link.select("span");
            String pConcatenated = "";
            Pattern pattern1 = Pattern.compile("\\d+");
            Matcher matcher;
            for (Element x : link) {
                matcher = pattern1.matcher(x.text());
                if (matcher.find())
                    pConcatenated = pConcatenated + x.text();
                else
                    pConcatenated = pConcatenated + x.text() + " ";
            }
            pattern1 = Pattern.compile("([а-яА-Я]+.[а-яА-Я]+[' '][а-яА-Я]*)");
            Matcher matchcer = pattern1.matcher(pConcatenated);
            if (matchcer.find()) {
                lines.append("{\n").append("\"number\" : ").append("11A").append(",\n\"name\" : \"").append(matchcer.group()).append("\"\n}\n");
            }
            pattern1 = Pattern.compile("\\d+.[а-яА-Я]+.[а-яА-Я]*\\s?[а-яА-Я]*");
            matchcer = pattern1.matcher(pConcatenated);
            StringBuilder stationsBuilder = new StringBuilder(stations.toString());
            while (matchcer.find()) {
                pConcatenated = matchcer.replaceFirst("");
                String a = matchcer.group();
                Pattern pattern2 = Pattern.compile("[а-яА-Я]+.[а-яА-Я]*\\s?[а-яА-Я]*");
                matchcer = pattern2.matcher(a);
                if (matchcer.find())
                    a = matchcer.group();
                if (k != 3)
                    stationsBuilder.append("\"").append(k).append("\" : [").append("\n\"").append(a).append("\",\n").append("\"").append("11А").append("\",\n],\n");
                else
                    stationsBuilder.append("\"").append(k).append("\" : [").append("\n\"").append(a).append("\",\n").append("\"").append("11А").append("\",\n]\n");
                matchcer = pattern1.matcher(pConcatenated);
                k++;
            }
            stations = new StringBuilder(stationsBuilder.toString());
            lines.append("\n]");
            stations.append("\n}");
            try {
                FileWriter writer = new FileWriter(("file.json"));
                writer.write("{\n");
                writer.write(stations.toString());
                writer.write("\n");
                writer.write(lines.toString());
                writer.write("\n}");
                writer.close();
                read();
            } catch (IOException ignore) {
            }
        }

        public void read() throws IOException {
            Gson gson = new GsonBuilder().create();
            JsonReader reader = new JsonReader(new FileReader("file.json"));
            reader.setLenient(true);
            reader.beginObject();
            reader.nextName();
            reader.beginObject();
            int count = 0;
            int k = 1;
            while (reader.hasNext()) {
                String name = reader.nextName();
                reader.beginArray();
                String f = reader.nextString();
                String l = reader.nextString();
                if (l.equals("D1")) {
                    System.out.println("На ветке " + k + "  " + count + " станций");
                    reader.nextNull();
                    reader.endArray();
                    break;
                }
                if (l.equals(String.valueOf(k)))
                    count++;
                else {
                    if (k != 13) {
                        System.out.println("На ветке " + k + "  " + count + " станций");
                        k++;
                        count = 1;
                    } else {
                        k++;
                        count = 2;
                    }
                }
                reader.nextNull();
                reader.endArray();
            }
            k = 1;
            count = 1;
            while (reader.hasNext()) {
                String name = reader.nextName();
                reader.beginArray();
                reader.nextString();
                String l = reader.nextString();
                if (l.equals("11А")) {
                    System.out.println("На ветке D" + k + "  " + count + " станций");
                    reader.nextNull();
                    reader.endArray();
                    break;
                }
                if (l.equals("D" + String.valueOf(k)))
                    count++;
                else {
                    System.out.println("На ветке D" + k + "  " + count + " станций");
                    k++;
                    count = 1;
                }
                reader.nextNull();
                reader.endArray();
            }
            count = 1;
            while (reader.peek() == JsonToken.NAME) {
                String name = reader.nextName();
                reader.beginArray();
                reader.nextString();
                String l = reader.nextString();
                if (l.equals("11А"))
                    count++;
                reader.nextNull();
                reader.endArray();
            }
            System.out.println("На ветке 11A" + "  " + count + " станций");
            reader.close();
        }
    }

}
