import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenteeMentorProgram {
    public static void main(String[] args) {
        List<String> mentors = new ArrayList<>();
        List<String> mentees = new ArrayList<>();

        File data = new File("F:/mentors_mentees.csv");

        try (BufferedReader br = new BufferedReader(new FileReader(data))) {
            String line;
            boolean headerSkipped = false;

            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                String[] parts = line.split(",");
                mentees.add(parts[0].trim());
                mentors.add(parts[1].trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a character or substring: ");
        String userInput = scanner.nextLine().trim();


        Set<String> matchingMentors = new HashSet<>();

        for (int i = 0; i < mentees.size(); i++) {
            if (mentees.get(i).contains(userInput)) {
                matchingMentors.add(mentors.get(i));
            }
        }

        System.out.println("Matching mentors:");
        for (String mentor : matchingMentors) {
            System.out.println(mentor);
        }

    }


    // Validate email format
//        if (!isValidEmail(userInput)) {
//            System.out.println("Invalid email format. Exiting program.");
//            return;
//        }

    // Email validation using regex
//    private static boolean isValidEmail(String email) {
//        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
//        Pattern pattern = Pattern.compile(emailRegex);
//        Matcher matcher = pattern.matcher(email);
//        return matcher.matches();
//    }
}
