public class Validinput {
    private final String input;
    public Validinput(String input) {
        this.input = input;
    }
    public boolean validate(int sizegrid) {
        char[] chars = input.toCharArray();
        if (chars.length != 2) {
            return false;
        } else if ((int) chars[0] < 65 || (int) chars[0] > 'A'+sizegrid-1 || (int) chars[1] < 48 || (int) chars[1] > sizegrid+47) {
            return false;
        }
        return true;
    }
}
