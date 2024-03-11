package src;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataWriter extends DataConstants {

    private static ArrayList<User> students = new ArrayList<User>();
    private static ArrayList<User> faculty = new ArrayList<User>();;
    private static ArrayList<User> advisors = new ArrayList<User>();;

    private static ArrayList<Course> courses = new ArrayList<Course>();

    private ArrayList<Major> majors = new ArrayList<Major>();

    public static void parseUserList() {
        for (int i = 0; i < UserList.getInstance().getUsers().size(); i++) {
            if (UserList.getInstance().getUsers().get(i).getType() == 3) {
                faculty.add(UserList.getInstance().getUsers().get(i));
            } else if (UserList.getInstance().getUsers().get(i).getType() == 2) {
                advisors.add(UserList.getInstance().getUsers().get(i));
            } else {
                students.add(UserList.getInstance().getUsers().get(i));
            }
        }
    }

    public static void writeCourses() {

    }

    public static void writeMajors() {

    }

    public static void saveUsers() {
        UserList userList = UserList.getInstance();
        ArrayList<User> users = userList.getUsers();
        JSONArray userJSON = new JSONArray();

        for (User user : users) {
            userJSON.add(getUserJSON(user));
        }

        try (FileWriter file = new FileWriter("data/userwritetest.json")) {

            file.write(userJSON.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static JSONObject getUserJSON(User user) {
        JSONObject userJSON = new JSONObject();
        userJSON.put(USER_FIRST_NAME, user.getFirstName());
        userJSON.put(USER_LAST_NAME, user.getLastName());
        userJSON.put(USER_ID, user.getUserID().toString());
        userJSON.put(USER_EMAIL, user.getEmail());
        userJSON.put(USER_USERNAME, user.getUsername());
        userJSON.put(USER_PASSWORD, user.getPassword());
        userJSON.put(USER_TYPE, user.getType());
        return userJSON;
    }
}
