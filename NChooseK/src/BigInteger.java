
import java.util.Arrays;
import java.util.Formatter;

/**
 * The support class that will handle big numbers. It can perform plus, multiply
 * and divide functions.
 *
 * @author hjia
 */
public class BigInteger {

    private static final int BASE_MAX = 1000;//1,000
    private static final int BASE_LENGTH = 3;
    private int[] digits; // place that hold the big integer. 

    public BigInteger(int... digits) { // 0 or more int will be passed
        this.digits = digits.clone();
    }

    public int[] getDigits() {
        return this.digits;
    }

    /**
     * This method can return a BigInteger Object based on the input String s.
     * no Exception would be handled.
     *
     * @param s the input String. should all be numbers.
     * @return the BigInteger object that generated based on s.
     */
    public static BigInteger valueOf(String s) {
        int len = s.length(); // length of the string 
        int digitsLength = len / BASE_LENGTH;
        // length of the array that will be passed into the constructor 
        int[] digits;
        int indexZeroLength = len % BASE_LENGTH;
        // the remaining part if the length of the string is not divisible by 9.
        //the base length  
        if (indexZeroLength != 0) {//if the remaining part exists.
            digitsLength += 1;
            digits = new int[digitsLength];
            digits[0] = Integer.valueOf(s.substring(0, indexZeroLength));
            // for each slot, give it value.
            for (int i = 1; i < digitsLength; i++) {
                digits[i] = Integer.valueOf(s.substring(indexZeroLength
                        + (i - 1) * BASE_LENGTH,
                        indexZeroLength + (i) * BASE_LENGTH));
            }
        } else {
            digits = new int[digitsLength];
            for (int i = 0; i < digitsLength; i++) {
                digits[i] = Integer.valueOf(s.substring(
                        i * BASE_LENGTH, (i + 1) * BASE_LENGTH));
            }
        }
        return new BigInteger(digits);
    }

    public void plus(int[] digits2) {
        int[] digits1 = this.digits.clone();
        //make two arrays into the same size
        int len2 = digits2.length;
        int len1 = digits1.length;
        if (len1 > len2) {
            int[] temp = new int[len1];
            int j = len1 - 1;
            for (int i = len2 - 1; i >= 0; i--) {
                temp[j] = digits2[i];
                j--;
            }
            digits2 = temp;
        } else if (len2 > len1) {
            int[] temp = new int[len2];
            int j = len2 - 1;
            for (int i = len1 - 1; i >= 0; i--) {
                temp[j] = digits1[i];
                j--;
            }
            digits1 = temp;
        }
        //start to calculate.
        int[] result = new int[Math.max(len1, len2) + 1];//result array
        int x = result.length;
        for (int i = Math.max(len1, len2) - 1; i >= 0; i--) {
            x--;
            int temp = digits2[i] + digits1[i] + result[x];
            if (temp > BASE_MAX) {
                result[x - 1] += temp / BASE_MAX;
                result[x] = temp - (temp / BASE_MAX) * BASE_MAX;
            } else if (temp % BASE_MAX == 0) {
                result[x - 1] += temp / BASE_MAX;
                result[x] = 0;
            } else {
                result[x] = temp - (temp / BASE_MAX) * BASE_MAX;
            }

        }
        if (result[0] == 0) {
            result = Arrays.copyOfRange(result, 1, result.length);
        }
        this.digits = result;
    }

    /**
     * This method determine which index should the array value be added on.
     *
     * @param length the length of the result array
     * @param a the array that contains multiplied result of two indexes of two
     * digits arrays.
     * @param endIndex the index of the result array where the last element in
     * array a should be added to.
     * @return the formatted array
     */
    public int[] formatArray(int length, int[] a, int endIndex) {
        int[] result = new int[length];
        int j = a.length - 1;
        for (int i = length - 1 - endIndex;
                i > length - 1 - endIndex - a.length; i--) {
            result[i] = a[j];
            j--;
        }
        return result;
    }

    /**
     * take one int array and multiply it with the local array. then change the
     * local array to the result array.
     *
     * @param digits2
     */
    public void multiply(int[] digits2) {
        int[] digits1 = this.digits.clone();
//make two arrays into the same size
        int len2 = digits2.length;
        int len1 = digits1.length;
        if (len1 > len2) {
            int[] temp = new int[len1];
            int j = len1 - 1;
            for (int i = len2 - 1; i >= 0; i--) {
                temp[j] = digits2[i];
                j--;
            }
            digits2 = temp;
        } else if (len2 > len1) {
            int[] temp = new int[len2];
            int j = len2 - 1;
            for (int i = len1 - 1; i >= 0; i--) {
                temp[j] = digits1[i];
                j--;
            }
            digits1 = temp;
        }
        //start to calculate 
        int[] result = new int[Math.max(len1, len2) * 2];//result array
        int x = result.length;
        int len = digits1.length;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = len - 1; j >= 0; j--) {
                int temp = digits2[i] * digits1[j];
                BigInteger tempBig = BigInteger.valueOf(temp + "");
                int[] tempArray = tempBig.getDigits();
                //(len-1-j)+(len-1-i)) the reverse index of two arrays.
                // that we are currently calculating.
                //e.g. [1,2,3] has index of (2,1,0)
                tempArray = formatArray(x, tempArray,
                        (len - 1 - j) + (len - 1 - i));
                //yeah wth is this... it worked, once i knew how.
                //result=mergeArray(result, tempArray);
                tempBig = new BigInteger(tempArray);
                tempBig.plus(result);
                result = tempBig.getDigits();
            }
        }

        while (result[0] == 0) {
            if (result.length > 1) {
                result = Arrays.copyOfRange(result, 1, result.length);
            }
        }
        this.digits = result;
    }

    /**
     * the big integer will be divided by an Integer.
     *
     * @param divisor
     */
    public void divide(int divisor) {
        int[] digits1 = this.digits.clone();
        int len = digits1.length;
        int[] result = new int[len];//result array
        int remainder = 0; // the remainder that will be carried
        for (int i = 0; i < len; i++) {
            int temp1 = ((remainder * BASE_MAX) + digits1[i]) / divisor;
            // the remainder will never be carried futher than 1 index.
            remainder = ((remainder * BASE_MAX) + digits1[i]) % divisor;
            // each slot in result array will and only will have one 
            // result in it. 
            result[i] = temp1;
        }

        while (result[0] == 0) { // get ride of 0s 
            if (result.length > 1) {
                result = Arrays.copyOfRange(result, 1, result.length);
            }
        }
        this.digits = result;
    }

    /**
     * convert digits array to something look like a number
     *
     * @return formatted string.
     */
    public String toDecimalString() {
        Formatter f = new Formatter();

        f.format("%d", digits[0]); // doesn't matter if it's alreday 0.
        for (int i = 1; i < digits.length; i++) {
            f.format("%03d", digits[i]); //e.g. format 9 as 000000009
        }
        return f.toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Arrays.hashCode(this.digits);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BigInteger other = (BigInteger) obj;
        if (!Arrays.equals(this.digits, other.digits)) {
            return false;
        }
        return true;
    }

    /**
     * return the digits array toString
     *
     * @return digits array toString
     */
    @Override
    public String toString() {
        return Arrays.toString(digits);
    }
}
