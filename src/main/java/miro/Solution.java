package miro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.*;

class MiroGameManager {
    Map<Integer, Integer> users = new HashMap<>();
    List<Integer> usersIndex = new ArrayList<>();

    public MiroGameManager() {

    }

    public boolean login(int userId) {
        if (users.containsKey(userId)) {
            return false;
        }
        users.put(userId, usersIndex.size());
        usersIndex.add(userId);
        return true;
    }

    public boolean logout(int userId) {
        if (!users.containsKey(userId)) {
            return false;
        }

        int index = (int) users.get(userId);
        int lastIndexUser = usersIndex.get(usersIndex.size() - 1);
        usersIndex.remove(usersIndex.size() - 1);
        usersIndex.add(index, userId);
        users.put(lastIndexUser, index);
        users.remove(userId);
        return true;
    }

    public int getRandomUserId() {
        if (users.isEmpty()) {
            return -1;
        }
        return usersIndex.get(new Random().nextInt(users.size()));
    }
}

public class Solution {

    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDN. Print output to STDOUT */
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        MiroGameManager miroGameManager = new MiroGameManager();

        int numberOfCommands = Integer.parseInt(bufferedReader.readLine().trim());
        for (int i = 0; i < numberOfCommands; i++) {
            String[] rowData = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            String command = rowData[0];

            if (command.equals("LOGIN")) {
                int value = Integer.parseInt(rowData[1]);
                bufferedWriter.write(String.valueOf(miroGameManager.login(value)));
                bufferedWriter.newLine();
            } else if (command.equals("LOGOUT")) {
                int value = Integer.parseInt(rowData[1]);
                bufferedWriter.write(String.valueOf(miroGameManager.logout(value)));
                bufferedWriter.newLine();
            } else if (command.equals("SELECT_RANDOM")) {
                int val = miroGameManager.getRandomUserId();
                if (val == -1) {
                    bufferedWriter.write("-1");
                    bufferedWriter.newLine();
                }
            } else {
                throw new RuntimeException("Unsupported command.");
            }
        }

        bufferedReader.close();
        bufferedWriter.close();
    }

}