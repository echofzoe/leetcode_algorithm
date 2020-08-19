package project.dispatch_system.model.staff;

import project.dispatch_system.control.service.Status;
import project.dispatch_system.model.equipment.Equipment;

public class Programmer extends Employee {

    private int memberId;   // 开发团队中的 id
    private Status status = Status.FREE;
    private Equipment equipment;   // 设备

    public Programmer() {
        super();
    }

    public Programmer(int id, String name, int age, double salary, Equipment equipment) {
        super(id, name, age, salary);
        this.equipment = equipment;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return super.toString() + "\t程序员\t" + status + "\t\t\t\t\t" + equipment.getDescription();
    }

    public String getDetailsForTeam() {
        return memberId + "/" + getId() + "\t\t" + getName() + "\t" + getAge() + "\t" + getSalary() + "\t程序员";
    }
}
