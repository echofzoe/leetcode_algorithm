package project.dispatch_system.control.service;

public class Status {

    private final String NAME;

    private Status(String name) {
        this.NAME = name;
    }

    public static final Status FREE = new Status("FREE");
    public static final Status BUSY = new Status("BUSY");
    public static final Status VACATION = new Status("VACATION");

    public String getNAME() {
        return NAME;
    }

    @Override
    public String toString() {
        return this.NAME;
    }
}
