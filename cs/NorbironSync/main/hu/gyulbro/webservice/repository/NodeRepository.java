package hu.gyulbro.webservice.repository;

import hu.gyulbro.webservice.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends JpaRepository<Node,String> {
}
