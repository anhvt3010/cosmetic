package com.anhvt.cosmetic.DTO;

import com.anhvt.cosmetic.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private long id;
    private User user;
    private String order_date;
    private String delivery_date;
    private String note;
    private Status status;
    public enum Status {
        PENDING((byte) 0),
        PROCESSING((byte) 1),
        SHIPPING((byte) 2),
        SUCCESS((byte) 4);


        private byte value;

        Status(byte value) {
            this.value = value;
        }

        public static Status fromValue(byte value) {
            for (Status status : Status.values()) {
                if (status.value  == value) {
                    return status;
                }
            }
            throw new IllegalArgumentException("Not found OrderStatus with value: " + value);
        }

        public String toString() {
            switch (this) {
                case PENDING:
                    return "PENDING";
                case PROCESSING:
                    return "PROCESSING";
                case SHIPPING:
                    return "SHIPPING";
                case SUCCESS:
                    return "SUCCESS";
                default:
                    throw new IllegalArgumentException("ERROR");
            }
        }
    }


}
