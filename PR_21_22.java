package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class PR_21_22 {
    public static void main(String[] args) {
        ComplexStuff.ComplexNumbersBuilder creating = new ComplexStuff.ComplexNumCustomCreator();
        ComplexStuff.CompNumber number = creating.create();

        ChairStuff.Client client = new ChairStuff.Client();
        client.sit();

        CompanyStuff.Frame frame = new CompanyStuff.Frame();
    }


    public static class ComplexStuff {
        public static class ComplexNumber implements CompNumber {
            private final double re;
            private final double im;

            public ComplexNumber(double re, double im) {
                this.re = re;
                this.im = im;
            }

            public double getRe() {
                return re;
            }

            public double getIm() {
                return im;
            }

            public double getModule() {
                return Math.sqrt(this.re * this.re + this.im * this.im);
            }

            public static ComplexNumber sum(ComplexNumber cn1, ComplexNumber cn2) {
                return new ComplexNumber(cn1.getRe() + cn2.getRe(), cn1.getIm() + cn2.getIm());
            }

            public static ComplexNumber multiply(ComplexNumber cn1, ComplexNumber cn2) {
                return new ComplexNumber(cn1.getRe() * cn2.getRe() - cn1.getIm() * cn2.getIm(), cn1.getRe() * cn2.getIm() + cn1.getIm() * cn2.getRe());
            }

            public static ComplexNumber subtract(ComplexNumber cn1, ComplexNumber cn2) {
                return new ComplexNumber(cn1.getRe() - cn2.getRe(), cn1.getIm() - cn2.getIm());
            }

            public static ComplexNumber divide(ComplexNumber cn1, ComplexNumber cn2) {
                ComplexNumber temp = new ComplexNumber(cn2.getRe(), (-1) * cn2.getIm());
                temp = ComplexNumber.multiply(cn1, temp);
                double denominator = cn2.getRe() * cn2.getRe() + cn2.getIm() * cn2.getIm();
                return new ComplexNumber(temp.getRe() / denominator, temp.getIm() / denominator);
            }

            public String sign() {
                if (im > 0) return " + ";
                else return " - ";
            }

            @Override
            public String toString() {
                String string;
                if (im == 1 || im == -1) {
                    if (re == 0) {
                        string = sign() + "i";
                    } else {
                        string = re + sign() + "i";
                    }
                } else {
                    string = re + sign() + Math.abs(im) + "i";
                }
                return string;
            }
        }

        public interface CompNumber {
            double getRe();

            double getIm();

            double getModule();

            String toString();

            String sign();

            static ComplexNumber divide() {
                return null;
            }

            static ComplexNumber subtract() {
                return null;
            }

            static ComplexNumber multiply() {
                return null;
            }

            static ComplexNumber sum() {
                return null;
            }
        }

        public interface ComplexNumbersBuilder {
            ComplexNumber create();
        }

        public static class ComplexNumCustomCreator implements ComplexNumbersBuilder {
            @Override
            public ComplexNumber create() {
                double re = 3;
                double im = 2;
                return new ComplexNumber(re, im);
            }
        }
    }

    public static class ChairStuff {
        public interface Chair {
            void sit();
        }

        public interface ChairBuilder {
            Chair createChair();
        }

        public static class MagicChair implements Chair {
            public MagicChair() {
                System.out.println("Выбран магический стул");
            }

            @Override
            public void sit() {
                System.out.println("Клиент сел на магический стул");
            }
        }

        public static class MagicChairChairBuilder implements ChairBuilder {

            @Override
            public Chair createChair() {
                return new MagicChair();
            }
        }

        public static class MultifunctionalChair implements Chair {
            public MultifunctionalChair() {
                System.out.println("Выбран мультифункциональный стул");
            }

            @Override
            public void sit() {

                System.out.println("Клиент сел на мультифункциональный стул");
            }
        }

        public static class MultifunctionalChairChairBuilder implements ChairBuilder {
            @Override
            public Chair createChair() {
                return new MultifunctionalChair();
            }
        }

        public static class VictorianChair implements Chair {
            public VictorianChair() {
                System.out.println("Выбран викторианский стул");
            }

            @Override
            public void sit() {
                System.out.println("Клиент сел на викторианский стул");
            }
        }

        public static class VictorianChairChairBuilder implements ChairBuilder {
            @Override
            public Chair createChair() {
                return new VictorianChair();
            }
        }

        public static class Client implements Chair {
            private final Chair chair;

            public Client() {
                System.out.println("Случайный выбор стула:");
                int i = new Random().nextInt(3);
                if (i == 1) {
                    ChairBuilder chairBuilder = new VictorianChairChairBuilder();
                    chair = chairBuilder.createChair();
                } else if (i == 2) {
                    ChairBuilder chairBuilder = new MultifunctionalChairChairBuilder();
                    chair = chairBuilder.createChair();
                } else {
                    ChairBuilder chairBuilder = new MagicChairChairBuilder();
                    chair = chairBuilder.createChair();
                }
            }

            @Override
            public void sit() {
                chair.sit();
            }
        }
    }

    public static class CompanyStuff {
        public static class TextDocument implements IDocument {
            FileWriter fw;

            @Override
            public void save() {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void create() {
                try {
                    fw = new FileWriter("sample1.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void open() {
                try {
                    fw = new FileWriter("sample1.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void close() {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public static class Frame {
            IDocument doc;
            ICreateDocument doc1;

            private int getInput() {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Выберите тип документа");
                System.out.println("1.Текстовый документ");
                return scanner.nextInt();
            }

            private void New() {
                int i = getInput();
                if (i == 1)
                    NewText();
                System.out.println("Документ создан");
            }

            private void Open() {
                int i = getInput();
                if (i == 1)
                    OpenText();
                System.out.println("Документ открыт");

            }

            private void Exit() {
                int i = getInput();
                if (i == 1)
                    ExitText();
                System.out.println("Прекращение работы");
            }

            private void Save() {
                int i = getInput();
                if (i == 1)
                    SaveText();
                System.out.println("Документ сохранен");
            }

            private void NewText() {
                doc1 = new CreateTextDocument();
                doc = doc1.CreateNew();
                doc.create();
            }

            private void OpenText() {
                doc1 = new CreateTextDocument();
                doc = doc1.CreateOpen();
                doc.open();
            }

            private void ExitText() {
                if (doc != null)
                    doc.close();
            }

            private void SaveText() {
                if (doc != null)
                    doc.save();
            }

            public Frame() {
                boolean go = true;
                while (go) {
                    System.out.println("Выберите пункт меню");
                    System.out.println("1.New");
                    System.out.println("2.Open");
                    System.out.println("3.Save");
                    System.out.println("4.Exit");
                    Scanner scanner = new Scanner(System.in);
                    int i = scanner.nextInt();
                    if (i == 4) {
                        Exit();
                        go = false;
                    } else if (i == 1)
                        New();
                    else if (i == 2)
                        Open();
                    else if (i == 3)
                        Save();
                }
            }
        }

        public interface IDocument {

            void save();

            void create();

            void open();

            void close();
        }

        public abstract static class ICreateDocument {
            abstract IDocument CreateNew();

            abstract IDocument CreateOpen();
        }

        public static class CreateTextDocument extends ICreateDocument {
            @Override
            IDocument CreateNew() {
                return new TextDocument();
            }

            @Override
            IDocument CreateOpen() {
                return new TextDocument();
            }
        }
    }
}

