import java.util.*;

class Process {
    private int id;
    private String name;
    private String status;
    private int priority;
    private boolean isSystemProcess;
    private double cpuUtilization;
    private double memoryUtilization;
    private double ioUtilization;
    private String boundType;

    public Process(int id, String name, int priority, boolean isSystemProcess, double cpuUtilization, double memoryUtilization, double ioUtilization) {
        this.id = id;
        this.name = name;
        this.status = "waiting";
        this.priority = priority;
        this.isSystemProcess = isSystemProcess;
        this.cpuUtilization = cpuUtilization;
        this.memoryUtilization = memoryUtilization;
        this.ioUtilization = ioUtilization;
        this.boundType = cpuUtilization > ioUtilization ? "CPU-bound" : "I/O-bound";
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getPriority() { return priority; }
    public boolean isSystemProcess() { return isSystemProcess; }
    public double getCpuUtilization() { return cpuUtilization; }
    public double getMemoryUtilization() { return memoryUtilization; }
    public double getIoUtilization() { return ioUtilization; }
    public String getBoundType() { return boundType; }
}

class ProcessManager {
    private List<Process> processList;

    public ProcessManager() {
        processList = new ArrayList<>();
    }

    public void addProcess(Process p) {
        processList.add(p);
    }

    public void removeProcess(int id) {
        processList.removeIf(p -> p.getId() == id);
    }

    public void scheduleProcesses() {
        // Example scheduling by priority
        processList.sort(Comparator.comparingInt(Process::getPriority));
        for (Process p : processList) {
            p.setStatus("running");
            System.out.println("Running process ID: " + p.getId() + ", Name: " + p.getName());
            p.setStatus("completed");
        }
    }

    public void displayProcesses() {
        for (Process p : processList) {
            System.out.println("------------------------------------------------------");
            System.out.println("ID: " + p.getId());
            System.out.println("Name: " + p.getName());
            System.out.println("Status: " + p.getStatus());
            System.out.println("Priority: " + p.getPriority());
            System.out.println("System Process: " + p.isSystemProcess());
            System.out.println("CPU Utilization: " + p.getCpuUtilization() + "%");
            System.out.println("Memory Utilization: " + p.getMemoryUtilization() + "MB");
            System.out.println("I/O Utilization: " + p.getIoUtilization() + "MB");
            System.out.println("Bound Type: " + p.getBoundType());
            System.out.println("------------------------------------------------------");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ProcessManager pm = new ProcessManager();
        Process p1 = new Process(1, "Process1", 2, false, 30.5, 100.0, 20.0);
        Process p2 = new Process(2, "Process2", 1, true, 45.0, 150.0, 35.0);
        Process p3 = new Process(3, "Process3", 3, false, 25.0, 200.0, 50.0);

        pm.addProcess(p1);
        pm.addProcess(p2);
        pm.addProcess(p3);

        System.out.println("Processes before scheduling:");
        pm.displayProcesses();

        pm.scheduleProcesses();

        System.out.println("Processes after scheduling:");
        pm.displayProcesses();
    }
}
