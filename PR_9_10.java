package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PR_9_10 {
    public static void main(String[] args) {
        Company company = new Company();
        company.hireAll();
        company.setIncome();
        company.printInfo();
        System.out.println("Top salaries:");
        List<Employee> top = company.getTopSalaryStaff(15);
        for (int i = 0; i < 15; ++i)
            System.out.println(top.get(i).position.calcSalary(top.get(i).position.getBaseSalary()));
        System.out.println();
        System.out.println("Least salaries:");
        top = company.getLowestSalaryStaff(30);
        for (int i = 0; i < 30; ++i)
            System.out.println(top.get(i).position.calcSalary(top.get(i).position.getBaseSalary()));

        company.fireHalf();

        System.out.println();
        company.setIncome();
        company.printInfo();
    }

    public interface EmployeePosition {
        String getJobTitle();

        void setIncome(int i);

        double calcSalary(double baseSalary);

        double getBaseSalary();
    }

    public static class Employee {
        private String surname, name;
        private EmployeePosition position;
    }

    public static class Manager implements EmployeePosition {
        private int income;

        public int getIncome() {
            return income;
        }

        public static double getBaseSalaryStatic() {
            return 60000;
        }

        public static String getJobName() {
            return "Manager";
        }

        @Override
        public void setIncome(int i) {
            income = i;
        }

        @Override
        public String getJobTitle() {
            return getJobName();
        }

        @Override
        public double calcSalary(double baseSalary) {
            return baseSalary + income * 0.05f;
        }

        @Override
        public double getBaseSalary() {
            return getBaseSalaryStatic();
        }
    }

    public static class TopManager implements EmployeePosition {
        private int income;

        public static String getJobName() {
            return "TopManager";
        }

        @Override
        public void setIncome(int i) {
            income = i;
        }

        @Override
        public String getJobTitle() {
            return getJobName();
        }

        @Override
        public double calcSalary(double baseSalary) {
            if (income > 10000000)
                return baseSalary + baseSalary * 1.5f;
            else
                return baseSalary;
        }

        public static double getBaseSalaryStatic() {
            return 228000;
        }

        @Override
        public double getBaseSalary() {
            return getBaseSalaryStatic();
        }
    }

    public static class Operator implements EmployeePosition {
        @Override
        public void setIncome(int i) {
        }

        public static String getJobName() {
            return "Operator";
        }

        @Override
        public String getJobTitle() {
            return getJobName();
        }

        @Override
        public double calcSalary(double baseSalary) {
            return baseSalary;
        }

        public static double getBaseSalaryStatic() {
            return 12000;
        }

        @Override
        public double getBaseSalary() {
            return getBaseSalaryStatic();
        }
    }

    public static class Company {
        ArrayList<Employee> employeesList = new ArrayList<>();

        public void setIncome() {
            Random random = new Random();
            for (Employee e : employeesList)
                if (e.position.getJobTitle().equals(Manager.getJobName()))
                    e.position.setIncome(random.nextInt(25000) + 115000);
        }

        public int getTotalCompanyIncome() {
            int sum = 0;
            for (Employee e : employeesList)
                if (e.position.getJobTitle().equals(Manager.getJobName()))
                    sum += ((Manager) e.position).getIncome();
            return sum;
        }

        public int getTotalLoss() {
            int sum = 0;
            for (Employee e : employeesList)
                sum += getIncome(e);
            return sum;
        }

        int getIncome(Employee e) {
            String job = e.position.getJobTitle();
            if (job.equals(Manager.getJobName()))
                return (int) e.position.calcSalary(Manager.getBaseSalaryStatic());
            else if (job.equals(TopManager.getJobName()))
                return (int) e.position.calcSalary(TopManager.getBaseSalaryStatic());
            else if (job.equals(Operator.getJobName()))
                return (int) e.position.calcSalary(Operator.getBaseSalaryStatic());
            else
                return 0;
        }

        void hire(int type) {
            Employee person = new Employee();
            switch (type) {
                case 0:
                    person.position = new Operator();
                    break;
                case 1:
                    person.position = new Manager();
                    break;
                case 2:
                    person.position = new TopManager();
                    break;
            }
            employeesList.add(person);
        }

        public void hireAll() {
            for (int i = 0; i < 180; ++i)
                hire(0);
            for (int i = 0; i < 80; ++i)
                hire(1);
            for (int i = 0; i < 10; ++i)
                hire(2);
        }

        void fire(Employee e) {
            employeesList.remove(e);
        }

        int getCountOf(String jobName) {
            int sum = 0;
            for (Employee employee : employeesList)
                if (employee.position.getJobTitle().equals(jobName))
                    ++sum;
            return sum;
        }

        public void fireHalf() {
            String[] jobs = {Operator.getJobName(), Manager.getJobName(), TopManager.getJobName()};
            ArrayList<Employee> toDel = new ArrayList<>();
            for (String job : jobs) {
                int employees = getCountOf(job) / 2;
                int found = 0;
                int i = 0;
                while (found < employees && i < employeesList.size()) {
                    if (employeesList.get(i).position.getJobTitle().equals(job)) {
                        toDel.add(employeesList.get(i));
                        ++found;
                    }
                    ++i;
                }
            }
            for (Employee e : toDel)
                fire(e);
        }

        List<Employee> getTopSalaryStaff(int count) {
            for (int i = employeesList.size() - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    if (employeesList.get(j).position.calcSalary(employeesList.get(j).position.getBaseSalary()) <
                            employeesList.get(j + 1).position.calcSalary(employeesList.get(j + 1).position.getBaseSalary())) {
                        Employee tmp = employeesList.get(j);
                        employeesList.set(j, employeesList.get(j + 1));
                        employeesList.set(j + 1, tmp);
                    }
                }
            }

            ArrayList<Employee> res = new ArrayList<>();
            for (int i = 0; i < count; ++i)
                res.add(employeesList.get(i));
            return res;
        }

        List<Employee> getLowestSalaryStaff(int count) {
            for (int i = employeesList.size() - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    if (employeesList.get(j).position.calcSalary(employeesList.get(j).position.getBaseSalary()) >
                            employeesList.get(j + 1).position.calcSalary(employeesList.get(j + 1).position.getBaseSalary())) {
                        Employee tmp = employeesList.get(j);
                        employeesList.set(j, employeesList.get(j + 1));
                        employeesList.set(j + 1, tmp);
                    }
                }
            }

            ArrayList<Employee> res = new ArrayList<>();
            for (int i = 0; i < count; ++i)
                res.add(employeesList.get(i));
            return res;
        }

        public void printInfo() {
            System.out.println("Total income = " + getTotalCompanyIncome());
            System.out.println("Total loss = " + getTotalLoss());
            System.out.println("Revenue = " + (getTotalCompanyIncome() - getTotalLoss()));
        }

    }
}
