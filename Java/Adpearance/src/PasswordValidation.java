public class PasswordValidation {
    public static Boolean strongPassword(String password) {

        // Checks for at least 12 characters first
        if (password.length() < 12) {
            return false;
        }


        /**
         * breakdown of the regex match:
         *
         * ^                anchors the match the the beginning of the string
         * (?=.*[A-Z])      look ahead that matches uppercase character
         * (?=.*[a-z])      look ahead that matches lowercase character
         * (?=.*\d)         look ahead that matches digit
         * (?!.*(123))      look behind that matches "123"
         *
         */
        return password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?!.*(123)).*");

    }

    public static void main(String[] args) {
        System.out.println("weak, length too short");
        System.out.println(strongPassword("strpass"));              // Weak password, length too short
        System.out.println("----------");
        System.out.println("strong");
        System.out.println(strongPassword("Strong1Password"));      // Strong password
        System.out.println("----------");
        System.out.println("weak, no digit");
        System.out.println(strongPassword("StrongPassword"));       // Weak password, no digit
        System.out.println("----------");
        System.out.println("weak, no uppercase");
        System.out.println(strongPassword("strong1password"));      // Weak password, no uppercase
        System.out.println("----------");
        System.out.println("weak, no lowercase");
        System.out.println(strongPassword("STRONG1PASSWORD"));      // Weak password, no uppercase
        System.out.println("----------");
        System.out.println("weak, contains 123");
        System.out.println(strongPassword("strong123password"));    // Weak password, contains "123"
    }
}

// Password is strong if:
// 1. it is at least 12 characters long
// 2. it contains at least one uppercase letter
// 3. it contains at least one lowercase letter
// 4. it contains at least one digit
// 5. it doesn't contain the substring "123"
