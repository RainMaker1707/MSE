package commands;

import java.util.List;

public interface Command {
    public void run();
    public List<String> getArguments();
    public void error(String err);
    public void feedback(String feedback);
}
