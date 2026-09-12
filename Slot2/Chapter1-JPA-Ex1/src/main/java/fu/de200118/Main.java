package fu.de200118;

import fu.de200118.dao.EmployeeDAO;
import fu.de200118.pojo.Employee;
import fu.de200118.pojo.Gender;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EmployeeDAO dao = new EmployeeDAO();

        System.out.println("========== DEMO TODO 0.8: CRUD FLOW ==========");
        //===== Create =====
        Employee emp = new Employee("Nguyen Van A", "d@fpt.edu.vn",
                new BigDecimal("15000000"), Gender.MALE, LocalDate.of(2022, 3, 1));

        dao.save(emp);
        System.out.println("Da tao: " + emp);

        //===== READ =====
        Employee found = dao.findById(emp.getId());
        System.out.println("Doc lai: " + found);

        // ===== UPDATE =====
        found.setSalary(new BigDecimal("17000000"));
        Employee updated = dao.update(found);
        System.out.println("Sau update: " + updated);

        // Doc lai de kiem chung
        Employee reChecked = dao.findById(emp.getId());
        System.out.println("Kiem tra lai sau update: " + reChecked);

        // ===== DELETE =====
        dao.delete(emp.getId());
        Employee afterDelete = dao.findById(emp.getId());
        System.out.println("Sau khi xoa, tim lai: " + afterDelete); // ky vong: null

        // ===== TODO 0.9: kiem chung unique constraint tren email =====
        Employee dup1 = new Employee("User 1", "trung@fpt.edu.vn",
                new BigDecimal("10000000"), Gender.FEMALE, LocalDate.now());
        Employee dup2 = new Employee("User 2", "trung@fpt.edu.vn", // trung email
                new BigDecimal("11000000"), Gender.MALE, LocalDate.now());

        dao.save(dup1);
        try {
            dao.save(dup2); // ky vong: nem exception vi vi pham UNIQUE
            System.out.println("LOI: khong thay exception nhu ky vong!");
        } catch (RuntimeException ex) {
            System.out.println("Da bat duoc loi trung email nhu ky vong: "
                    + ex.getClass().getSimpleName());
        }
    }


}
