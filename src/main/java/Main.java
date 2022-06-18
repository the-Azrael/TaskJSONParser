import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
    private static List<Employee> list = new ArrayList<>();

    private static Employee listFromJSON(String jsonString) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(jsonString, Employee.class);
    }

    private static List<Employee> readString(String fileName) {
        List<Employee> list = new ArrayList<>();
        try {
            FileReader fr = new FileReader(fileName);
            JSONParser parser = new JSONParser();
            JSONArray objects = (JSONArray) parser.parse(fr);
            for (Object obj : objects) {
                Employee employee = listFromJSON(obj.toString());
                list.add(employee);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static void showEmployees(List<Employee> lst) {
        for (Employee emp : lst) {
            System.out.println(emp.toString());
        }
    }

    public static void main(String[] args) {
        showEmployees(readString("data.json"));
    }
}
