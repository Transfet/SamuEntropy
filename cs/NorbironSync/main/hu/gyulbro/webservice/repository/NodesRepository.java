package hu.gyulbro.webservice.repository;

import hu.gyulbro.webservice.entity.Nodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodesRepository extends JpaRepository<Nodes,String> {
}
