package studentmanagement.src;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

public class Main {
    static void AddStudent(String name, double grade, HashMap<String, Double> student){
        student.put(name, grade);
        saveToFile(student);
    }
    static void RemoveStudent(String name, HashMap<String, Double> student){
        student.remove(name);
        saveToFile(student);
    }
    static void ListStudent(HashMap<String, Double> student){
        for(String x : student.keySet()){
            System.out.println("Student: " + x + " Grade: " + student.get(x));
        }
    }
    static void saveToFile(HashMap<String, Double> student) {
        try (FileWriter writer = new FileWriter("students.txt")) {
            for (String name : student.keySet()) {
                writer.write(name + "," + student.get(name) + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving data.");
        }
    }
    static HashMap<String, Double> readFromFile() {
        HashMap<String, Double> student = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    student.put(parts[0], Double.parseDouble(parts[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading data.");
        }
        return student;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        HashMap<String, Double> students = readFromFile();
        while (true) {
            System.out.print("Action (add, remove, list, exit): ");
            String actionInput = input.nextLine();
            switch(actionInput.toLowerCase()){
                case "add":
                    System.out.print("Enter a student: ");
                    String nameAdd = input.nextLine();
                    if(students.containsKey(nameAdd)){
                        System.out.println("Student already exists!");
                    } else {
                        System.out.print("Enter " + nameAdd + "'s grade: ");
                        double grade = input.nextDouble();
                        input.nextLine();
                        AddStudent(nameAdd, grade, students);
                        System.out.println("Student added!");
                    }
                break;
                case "remove":
                    System.out.print("Enter a student: ");
                    String nameRemove = input.nextLine();
                    if(students.containsKey(nameRemove)){
                        System.out.println("Student " + nameRemove + " has been removed!");
                        RemoveStudent(nameRemove, students);
                    } else {
                        System.out.println("Student does not exist!");
                    }
                break;
                case "list":
                    ListStudent(students);
                break;
                case "exit":
                    input.close();
                    System.out.println("Exiting...");
                return;
            }
        }
    }
}