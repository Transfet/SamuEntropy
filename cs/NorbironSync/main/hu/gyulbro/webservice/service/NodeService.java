package hu.gyulbro.webservice.service;

import hu.gyulbro.webservice.duplicate.NodeBO;
import hu.gyulbro.webservice.entity.Node;

import java.util.List;

public interface NodeService {

    List<NodeBO> loadFromDB();

    void saveIntoDB(List<NodeBO> nodes);

}
