package waitnotify;

public class IncreaceCount {
    private  int count = 0;

    synchronized public void addCount() {
        this.count++;
    }

    synchronized public int getCount() {
        return count;
    }
}
