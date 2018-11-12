package main;

public class Exercise1 {

    Exercise1() {
    }

    public int[] pushIntToArray(int[] array, int num, int index) {
        if (index < array.length) {
            int nextElement = array.clone()[index];
            array[index] = num;
            return pushIntToArray(array, nextElement, index + 1);
        } else {
            return array;
        }
    }

    public String weaveStringsTogether(String str1, String str2, StringBuilder rtrString) {
        if (str1.toCharArray().length == 0) {
            return rtrString + str2;
        }
        else if (str2.toCharArray().length == 0) {
            return rtrString + str1;
        }
        rtrString.append(str1.charAt(0));
        rtrString.append(str2.charAt(0));
        str1 = str1.substring(1, str1.length());
        str2 = str2.substring(1,str2.length());
        return weaveStringsTogether(str1, str2, rtrString);
    }

}