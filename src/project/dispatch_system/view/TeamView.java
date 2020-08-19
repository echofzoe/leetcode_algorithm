package project.dispatch_system.view;

import project.dispatch_system.control.service.NameListService;
import project.dispatch_system.control.service.TeamException;
import project.dispatch_system.control.service.TeamService;
import project.dispatch_system.model.staff.Employee;
import project.dispatch_system.model.staff.Programmer;

public class TeamView {
    public static void main(String[] args) {
        TeamView view = new TeamView();
        view.enterMainMenu();
    }

    private NameListService listSvc = new NameListService();
    private TeamService teamSvc = new TeamService();

    public void enterMainMenu() {
        boolean loopFlag = true;
        char menu = 0;

        while (loopFlag) {
            if (menu != '1') {
                listAllEmployees();
            }
            System.out.println("1-团队列表 2-添加团队成员 3-删除团队成员 4-退出   请选择(1-4)：");
            // 读取 1-4 的选项
            menu = TSUtility.readMenuSelection();
            switch (menu) {
                case '1':
                    getTeam();
                    break;
                case '2':
                    addMember();
                    break;
                case '3':
                    deleteMember();
                    break;
                case '4':
                    System.out.println("确认是否退出(Y/N)：");
                    char isExit = TSUtility.readConfirmSelection();
                    if (isExit == 'Y') {
                        loopFlag = false;
                    }
                    break;
            }
        }
    }

    /**
     * 显示所有员工的信息
     */
    private void listAllEmployees() {
        System.out.println("- - - - - - - - - - - - - - - - - - - - 开发团队调度软件 - - - - - - - - - - - - - - - - - - - -");
        Employee[] employees = listSvc.getAllEmployees();
        if (employees == null || employees.length == 0) {
            System.out.println("公司中没有任何员工信息！");
        } else {
            System.out.println("ID \t姓名 \t年龄\t工资\t\t职位\t\t状态\t\t奖金\t\t股票\t\t领用设备");

            for (int i = 0; i < employees.length; i++) {
                System.out.println(employees[i]);
            }
        }
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
    }

    /**
     * 查看团队列表
     */
    private void getTeam() {
        System.out.println("- - - - - - - - - - - - - - - - - - - - - 团队成员列表 - - - - - - - - - - - - - - - - - - - - -");

        Programmer[] team = teamSvc.getTeam();
        if (team == null || team.length == 0) {
            System.out.println();
            System.out.println("当前开发团队中没有成员！");
        } else {
            System.out.println("TID/ID\t姓名\t\t年龄\t工资\t\t职位\t\t奖金\t\t股票");
            for (int i = 0; i < team.length; i++) {
                System.out.println(team[i].getDetailsForTeam());
            }
        }

        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
    }

    /**
     * 添加团队成员
     */
    private void addMember() {
        System.out.println("- - - - - - - - - - - - - - 添加成员 - - - - - - - - - - - - - -");
        System.out.print("请输入要添加的员工ID：");
        int id = TSUtility.readInt();
        try {
            Employee employee = listSvc.getEmployee(id);
            teamSvc.addMember(employee);
            System.out.println("添加成功");
            TSUtility.readReturn();
        } catch (TeamException e) {
            System.out.println("添加失败，原因：" + e.getMessage());
            TSUtility.readReturn();
        }

    }

    /**
     * 删除团队成员
     */
    private void deleteMember() {
        System.out.println("- - - - - - - - - - - - - - 添加成员 - - - - - - - - - - - - - -");
        System.out.print("请输入要删除员工的TID：");
        int memberId = TSUtility.readInt();
        System.out.print("确认是否删除(Y/N)：");
        char isDelete = TSUtility.readConfirmSelection();

        if (isDelete == 'Y') {
            try {
                teamSvc.removeMember(memberId);
                System.out.println("删除成功");
            } catch (TeamException e) {
                System.out.println("删除失败，原因：" + e.getMessage());
            }
            TSUtility.readReturn();
        } else if (isDelete == 'N') {
            return;
        }
    }
}
