package healthconnect.repositories;

import healthconnect.models.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findDepartmentByName (String name);
}
