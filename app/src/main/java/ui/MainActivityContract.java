package ui;

/**
 * Created by manolofernandez on 9/25/17.
 */

public class MainActivityContract {
    public interface View {
        //void showStartActivity();
        void close();
        void showMessage(String message);
    }
}
