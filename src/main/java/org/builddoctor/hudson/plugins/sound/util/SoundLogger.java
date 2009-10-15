package org.builddoctor.hudson.plugins.sound.util;

import hudson.model.BuildListener;

import java.io.Serializable;

public class SoundLogger implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * Log output to the given logger, using the Sound identifier
     *
     * @param listener The current listener
     * @param message  The message to be outputted
     */
    public static void log(BuildListener listener, final String message) {
        listener.getLogger().println("[Sound] " + message);
    }
}
