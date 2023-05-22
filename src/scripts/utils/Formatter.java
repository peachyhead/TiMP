package scripts.utils;

public class Formatter {

    public static String getFormattedTime(long nanoTime){
        var temp = (int)(nanoTime/1000000);
        var millis = temp % 1000;
        temp /= 1000;
        var seconds = temp % 60;
        var minutes = temp / 60;

        return String.format("%02d:%02d:%03d", minutes, seconds, millis);
    }
}
