public class Main {

    /*

    string, n1, n2

    numero di occorrenze di

     */
    public static void main(String[] args) {
        String s = "Ciao Marco come va?";
        int n1 = 16, n2 = 26;
        System.out.println(conta(s, n1, n2));
    }

    final static char C = 'c';
    public static int conta(String s, int left, int right){
        if(left < 0 || right < 0) return -1;
        left %= s.length();
        right %= s.length();
        if(right < left) return -1;
        String str = s.substring(left, right);
        int val = 0;
        for(char c : str.toLowerCase().toCharArray()) if(c == C) val++;
        return val;
    }
}