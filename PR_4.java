package com.company;

public class PR_4 {
    public static class Author {

        private final String name;
        private String email;
        private final char gender;

        public Author(String name, String email, char gender) {
            this.name = name;
            this.email = email;
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        /**
         * Захотел программист узнать сколько существует гендеров.
         * Смотри класс Author, реализацию getGender... А там возвращаемый тип boolean
         */
        public char getGender() {
            return gender;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void ToString() {
            System.out.print(name + "(" + gender + ")" + " at " + email);
        }
    }

}
