
// TODO: SAVE DATA TO FILE

package studentmanagement.src;
import java.util.HashMap;
import java.util.Scanner;
public class Main {
    static void AddStudent(String name, int grade, HashMap<String, Integer> student){
        student.put(name, grade);
    }
    static void RemoveStudent(String name, HashMap<String, Integer> student){
        student.remove(name);
    }
    static void ListStudent(HashMap<String, Integer> student){
        for(String x : student.keySet()){
            System.out.println("Student: " + x + " Grade: " + student.get(x));
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        HashMap<String, Integer> students = new HashMap<String, Integer>();
        //while true, so that the program continues running
        while (true) {
            System.out.print("Action (add, remove, list, exit): ");
            String actionInput = input.nextLine();
            switch(actionInput.toLowerCase()){
                //  ADD ---------------------------
                case "add":
                    System.out.print("Enter a student: ");
                    String nameAdd = input.nextLine();

                    if(students.containsKey(nameAdd)){
                        System.out.println("Student already exists!");
                    } else {
                        System.out.print("Enter " + nameAdd + "'s grade: ");
                        int grade = input.nextInt();
                        input.nextLine(); // Consume the newline character, if we dont do this the action message will be sent twice
                        AddStudent(nameAdd, grade, students);
                    }
                    break;
                //  REMOVE ---------------------------
                case "remove":
                    System.out.print("Enter a student: ");
                    String nameRemove = input.nextLine();

                    if(students.containsKey(nameRemove)){
                        System.out.println("Student" + nameRemove + "has been removed!");
                        RemoveStudent(nameRemove, students);
                    } else {
                        System.out.println("Student does not exist!");
                    }
                break;
                //  LIST ---------------------------
                case "list":
                    ListStudent(students);
                break;
                //  EXIST ---------------------------
                case "exit":
                    input.close();
                    System.out.println("Exiting...");
                return;
            }
        }
    }
}
