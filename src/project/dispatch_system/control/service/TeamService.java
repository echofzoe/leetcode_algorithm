package project.dispatch_system.control.service;

import project.dispatch_system.model.staff.Architect;
import project.dispatch_system.model.staff.Designer;
import project.dispatch_system.model.staff.Employee;
import project.dispatch_system.model.staff.Programmer;

/**
 * @author echofzoe
 * @version v1.0
 * @ClassName TeamService
 * @Description 关于开发团队成员的管理、添加、删除等
 * @date 2020/5/25 20:31
 */
public class TeamService {
    private static int counter = 1;   // 给 memberId 赋值用
    private final int MAX_MEMBER = 5;   // 表示开发团队最大成员数
    private Programmer[] team = new Programmer[MAX_MEMBER];   // 保存开发团队成员
    private int total;   // 记录开发团队中实际的人数

    /**
     * 获取开发团队中的所有成员
     *
     * @return
     */
    public Programmer[] getTeam() {
        Programmer[] team = new Programmer[total];

        for (int i = 0; i < team.length; i++) {
            team[i] = this.team[i];
        }
        return team;
    }

    /**
     * 将指定的员工添加到开发团队中
     *
     * @param e
     */
    public void addMember(Employee e) throws TeamException {
        if (total >= MAX_MEMBER) {
            throw new TeamException("成员已满，无法添加");
        }
        if (!(e instanceof Programmer)) {
            throw new TeamException("该成员不是开发人员，无法添加");
        }
        if (isExist(e)) {
            throw new TeamException("该员工已在本开发团队中");
        }

        Programmer p = (Programmer) e;
        if ("BUSY".equalsIgnoreCase(p.getStatus().getNAME())) {
            throw new TeamException("该员工已是某团队成员");
        } else if ("VACATION".equalsIgnoreCase(p.getStatus().getNAME())) {
            throw new TeamException("该员工正在休假，无法添加");
        }

        // 初始化团队中已有架构师、设计师、程序员的人数
        int numsOfArchitect = 0, numsOfDesigner = 0, numsOfProgrammer = 0;
        for (int i = 0; i < total; i++) {
            if (team[i] instanceof Architect) {
                numsOfArchitect++;
            } else if (team[i] instanceof Designer) {
                numsOfDesigner++;
            } else if (team[i] instanceof Programmer) {
                numsOfProgrammer++;
            }
        }

        if (p instanceof Architect) {
            if (numsOfArchitect >= 1) {
                throw new TeamException("团队中至多只能有一名架构师");
            }
        } else if (p instanceof Designer) {
            if (numsOfDesigner >= 2) {
                throw new TeamException("团队中至多只能有两名设计师");
            }
        } else if (p instanceof Programmer) {
            if (numsOfProgrammer >= 3) {
                throw new TeamException("团队中至多只能有三名程序员");
            }
        }

        // 将 p（或e）添加到现有的 team 中
        team[total++] = p;
        // p 的属性赋值
        p.setStatus(Status.BUSY);
        p.setMemberId(counter++);
    }

    private boolean isExist(Employee e) {
        for (int i = 0; i < total; i++) {
            if (team[i].getId() == e.getId()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 删除团队成员
     *
     * @param memberId
     */
    public void removeMember(int memberId) throws TeamException {
        int i = 0;
        // 找
        for (; i < total; i++) {
            if (team[i].getMemberId() == memberId) {
                team[i].setStatus(Status.FREE);
                break;
            }
        }

        // 没找到
        if (i == total) {
            throw new TeamException("找不到指定的员工，删除失败");
        }
        // 找到了，删
        else {
            for (int j = i; j < total - 1; j++) {
                team[j] = team[j + 1];
            }
            team[--total] = null;
        }
    }
}
