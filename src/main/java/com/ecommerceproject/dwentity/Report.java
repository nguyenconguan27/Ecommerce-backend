package com.ecommerceproject.dwentity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Report {
    private String timeQuery;
    private long totalRevenue;
    private int saledProduct;
    private int registedUser;
}
