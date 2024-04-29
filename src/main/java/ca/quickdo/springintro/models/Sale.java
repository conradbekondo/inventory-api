package ca.quickdo.springintro.models;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Data
@Jacksonized
@Builder
@Entity
@Table(name = "Sale")

public class Sale {
    @ElementCollection
    private List<Item> items;
    private long timestamp;
    private String SalesId;

    public Sale(String timestamp, List<Item>items){
        this.timestamp= Long.parseLong(timestamp);
        this.items=items;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = Long.parseLong(timestamp);
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}





