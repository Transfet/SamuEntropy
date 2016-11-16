package hu.gyulbro.webservice.entity;


import javax.persistence.*;

@Entity
@Table(name = "nodes")
public class Nodes {

    private String type;
    private String xCoordinate;
    private String yCoordinate;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName=Users.ID_COLUMN_NAME)
    private Users user;

    @Id
    @Column(name = "NODE_ID")
    private String nodeID;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(String xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public String getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(String yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }
}
