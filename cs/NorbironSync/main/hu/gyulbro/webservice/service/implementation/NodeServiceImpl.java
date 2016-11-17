package hu.gyulbro.webservice.service.implementation;

import hu.gyulbro.webservice.duplicate.NodeBO;
import hu.gyulbro.webservice.entity.Node;
import hu.gyulbro.webservice.repository.NodeRepository;
import hu.gyulbro.webservice.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class NodeServiceImpl implements NodeService {

    private NodeRepository nodeRepository;

    @Autowired
    public void setNodeRepository(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    @Override
    public List<NodeBO> loadFromDB() {
        List<Node> nodes = nodeRepository.findAll();
        List<NodeBO> nodesToPass = new ArrayList<>();

        for (Node node : nodes) {
            NodeBO nodeBO = new NodeBO(
                    node.getType(),
                    node.getxCoordinate(),
                    node.getyCoordinate(),
                    node.getUserID(),
                    node.getNodeID());
            nodesToPass.add(nodeBO);
        }
        return nodesToPass;
    }

    @Override
    public void saveIntoDB(List<NodeBO> nodes) {
        nodeRepository.deleteAll();

        for (NodeBO node : nodes) {

            Node nodeNT = new Node();
            nodeNT.setNodeID(node.getNodeID());
            nodeNT.setUserID(node.getUserID());
            nodeNT.setType(node.getType());
            nodeNT.setxCoordinate(node.getxCoordinate());
            nodeNT.setyCoordinate(node.getyCoordinate());

            nodeRepository.save(nodeNT);
        }
        nodeRepository.flush();
    }
}
