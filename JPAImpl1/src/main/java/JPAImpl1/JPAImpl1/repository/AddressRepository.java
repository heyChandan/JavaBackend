package JPAImpl1.JPAImpl1.repository;

import JPAImpl1.JPAImpl1.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
