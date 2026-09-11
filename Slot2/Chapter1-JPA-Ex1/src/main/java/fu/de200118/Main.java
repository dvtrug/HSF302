package fu.de200118;

import fu.de200118.dao.EmployeeDAO;
import fu.de200118.pojo.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HSF302_Chapter1");
        try {
            EntityManager em = emf.createEntityManager();
            Employee emp = new Employee("Nguyen Van A", "nguyenvana@gmail.com");
            EmployeeDAO empDAO = new EmployeeDAO(emf);
            System.out.println("Trước khi save: ID = " + emp.getId()); // null

            // Thực thi lưu xuống DB
            empDAO.save(emp);

            // Sau khi commit: Trạng thái Managed -> DB tự sinh ID
            System.out.println("Sau khi save: ID = " + emp.getId());
            System.out.println("Thông tin nhân viên: " + emp);

            if (emp.getId() != 0) {
                System.out.println("--> THÀNH CÔNG: e.getId() khác null, ID đã được DB sinh!");
            } else {
                System.out.println("--> THẤT BẠI: e.getId() vẫn là null!");
            }
        } finally {
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }
}
