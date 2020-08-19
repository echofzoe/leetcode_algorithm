package project.dispatch_system.control.service;

import project.dispatch_system.model.equipment.Equipment;
import project.dispatch_system.model.equipment.NoteBook;
import project.dispatch_system.model.equipment.PC;
import project.dispatch_system.model.equipment.Printer;
import project.dispatch_system.model.staff.Architect;
import project.dispatch_system.model.staff.Designer;
import project.dispatch_system.model.staff.Employee;
import project.dispatch_system.model.staff.Programmer;

import static project.dispatch_system.view.Data.*;

/**
 * @author echofzoe
 * @version v1.0
 * @ClassName NameListService
 * @Description 负责将 Data 中的数据封装在 Employee[] 数组中，并提供操作方法
 * @date 2020/5/24 23:37
 */
public class NameListService {

    private Employee[] employees;

    /**
     * 空参构造器 - 初始化所有员工
     */
    public NameListService() {
        employees = new Employee[EMPLOYEES.length];

        for (int i = 0; i < EMPLOYEES.length; i++) {
            // 获取员工类型
            int type = Integer.parseInt(EMPLOYEES[i][0]);

            // 获取 Employee 的 4 个基本信息
            int id = Integer.parseInt(EMPLOYEES[i][1]);
            String name = EMPLOYEES[i][2];
            int age = Integer.parseInt(EMPLOYEES[i][3]);
            double salary = Double.parseDouble(EMPLOYEES[i][4]);
            Equipment equipment;
            double bonus;
            int stock;

            // 初始化 employees 数组
            switch (type) {
                case EMPLOYEE:
                    employees[i] = new Employee(id, name, age, salary);
                    break;
                case PROGRAMMER:
                    equipment = findEquipment(i);
                    employees[i] = new Programmer(id, name, age, salary, equipment);
                    break;
                case DESIGNER:
                    equipment = findEquipment(i);
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    employees[i] = new Designer(id, name, age, salary, equipment, bonus);
                    break;
                case ARCHITECT:
                    equipment = findEquipment(i);
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    stock = Integer.parseInt(EMPLOYEES[i][6]);
                    employees[i] = new Architect(id, name, age, salary, equipment, bonus, stock);
                    break;
            }
        }
    }

    /**
     * 获取指定 index 上的员工的装备
     *
     * @param index
     * @return
     */
    private Equipment findEquipment(int index) {

        int key = Integer.parseInt(EQUIPMENTS[index][0]);

        String modelOrName = EQUIPMENTS[index][1];

        switch (key) {
            case PC:
                String display = EQUIPMENTS[index][2];
                return new PC(modelOrName, display);
            case NOTEBOOK:
                double price = Double.parseDouble(EQUIPMENTS[index][2]);
                return new NoteBook(modelOrName, price);
            case PRINTER:
                String type = EQUIPMENTS[index][2];
                return new Printer(modelOrName, type);
        }
        return null;
    }

    /**
     * 获取当前所有员工
     *
     * @return
     */
    public Employee[] getAllEmployees() {
        return employees;
    }

    public Employee getEmployee(int id) throws TeamException {
        for (int i = 0; i < employees.length; i++) {
            // 如果是比较 Integer 对象，就用 equals，避免数值越界 [-128, 127]
            if (employees[i].getId() == id) {
                return employees[i];
            }
        }
        throw new TeamException("找不到指定的员工！");
    }
}
