package com.company;

import com.opencsv.CSVReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PR_23_24 {
    public static void main(String[] args) {
        CSVStuff.Bankcard bankcard = new CSVStuff.Bankcard();
        bankcard.Readingfile();
        ImagesStuff images= new ImagesStuff();
        images.parseAll();
    }

    public static class ImagesStuff {
        public void parseAll() {
            try {
                Document doc = Jsoup.connect("https://www.mirea.ru").get();
                int k = 0;
                Elements link = doc.select("img");
                List<String> absHref = link.eachAttr("abs:src");
                for (String src : absHref) {
                    if (k < absHref.size() - 1)
                        downloadImage(src);
                    k++;
                }
                File folder = new File("./images");
                File[] listOfFiles = folder.listFiles();

                if (listOfFiles != null) {
                    for (File file : listOfFiles) {
                        if (file.isFile()) {
                            System.out.println(file.getName());
                        }
                    }
                }
            }catch (IOException ignore){ }
        }
        private static void downloadImage(String strImageURL){
            String strImageName =
                    strImageURL.substring( strImageURL.lastIndexOf("/") + 1 );
            System.out.println("Downloading "+strImageName);
            downloadFile(strImageURL, strImageName);
        }

        public static void downloadFile(String url, String file_name){
            try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(file_name)) {
                byte[] dataBuffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class CSVStuff {
        public static class Transaction {
            String paymentDate;
            double amountplus;
            double amountminus;
            String reference;
            String accountBic;
            String accountName;
            String currency;
            String info;

            public Transaction() {
                this.amountplus = 0;
                this.amountminus = 0;
            }
        }

        public static class Bankcard {
            List<Transaction> trans = new ArrayList<Transaction>();
            private CSVReader csvReader;
            private static final String path = "./movementList.csv";
            List<List<String>> records;

            public void Readingfile() {
                records = new ArrayList<>();
                try (CSVReader csvReader = new CSVReader(new FileReader(path))) {
                    String[] values = null;
                    while ((values = csvReader.readNext()) != null) {
                        records.add(Arrays.asList(values));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                parsing();
            }

            private void parsing() {
                Pattern pattern1 = Pattern.compile("\\W{7}\\s\\W{4}");
                Pattern pattern2 = Pattern.compile("\\d{20}");
                Pattern pattern3 = Pattern.compile("[a-zA-Z]{3}[,]");
                Pattern pattern4 = Pattern.compile("[a-zA-Z]{3}[_]\\w{6}");
                Pattern pattern5 = Pattern.compile("(\\d{6,8}.\\w{3}.[a-zA-Z]{6}.{9,17})|(\\w{8}.\\w{3}.[a-zA-Z]{12}.{9,17})" +
                        "|(\\d{6}\\s{1,4}.\\w{2}.\\w{9}.{9,19})|(\\d{8}.[USA].{22,28})");
                Pattern pattern6 = Pattern.compile("\\d+");
                Pattern pattern7 = Pattern.compile("[0][,].\\d+.\\d+.");

                for (List<String> word : records) {
                    for (int i = 0; i < word.size(); i++) {
                        Transaction transaction = new Transaction();
                        Matcher matcher = pattern1.matcher(word.get(i));
                        if (matcher.find())
                            transaction.accountName = matcher.group();
                        matcher = pattern2.matcher(word.get(i));
                        if (matcher.find())
                            transaction.accountBic = matcher.group();
                        matcher = pattern3.matcher(word.get(i));
                        if (matcher.find()) {
                            String a = matcher.group();
                            String b = a.substring(0, 3);
                            transaction.currency = b;
                        }
                        matcher = pattern4.matcher(word.get(i));
                        if (matcher.find())
                            transaction.reference = matcher.group();
                        matcher = pattern5.matcher(word.get(i));
                        if (matcher.find())
                            transaction.info = matcher.group();
                        matcher = pattern6.matcher(word.get(i));
                        if (i > 0) {
                            if (word.get(i - 1).equals("0"))
                                if (matcher.find())
                                    transaction.amountminus = Double.parseDouble(matcher.group());
                        }
                        if (i < word.size() - 1) {
                            if (word.get(i + 1).equals("0"))
                                if (matcher.find())
                                    transaction.amountplus = Double.parseDouble(matcher.group());
                        }
                        matcher = pattern7.matcher(word.get(i));

                        if (matcher.find()) {
                            String c = matcher.group();
                            System.out.println(c);
                            double d = Double.parseDouble(c.substring(3, c.length() - 4));
                            if (c.charAt(c.length() - 4) == ',') {
                                d = d + ((Double.parseDouble(c.substring(c.length() - 3, c.length() - 1))) / 100);
                            } else {
                                d = d + ((Double.parseDouble(c.substring(c.length() - 2, c.length() - 1))) / 10);
                            }
                            transaction.amountminus = d;
                        }
                        trans.add(transaction);
                    }

                }
                double calc1 = 0, calc = 0;
                for (Transaction t : trans) {
                    calc1 = calc1 + t.amountplus;
                    calc = calc - t.amountminus;
                }

                System.out.println("Доходы:   " + calc1);
                System.out.println("Расходы:   " + calc);
            }
        }
    }
}
