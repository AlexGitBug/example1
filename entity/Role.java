package entity;

import java.util.Objects;

public class Role {
    private int id;
    private String rank;

    public Role() {
    }

    public Role(int id, String rank) {
        this.id = id;
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return id == role1.id && Objects.equals(rank, role1.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rank);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", rank='" + rank + '\'' +
                '}';
    }
}

