package com.oop_pub.clase_interne.Bash;

public interface CommandSubscriber {
    /**
     * Executes the given command. We can call this method from a Publisher to tell the Subscriber that a command was received.
     */
    void executeCommand(Command c);
}
