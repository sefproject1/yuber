package com.example.yuber.models;

public class OrderModel {
    private String customerUsername;
    private String sourceAddress;
    private String destinationAddress;
    private String driverUsername;
    private String status;

    public OrderModel() {
    }

    public OrderModel(String customerUsername, String sourceAddress, String destinationAddress, String driverUsername, String status) {
        this.customerUsername = customerUsername;
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
        this.driverUsername = driverUsername;
        this.status = status;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getDriverUsername() {
        return driverUsername;
    }

    public void setDriverUsername(String driverUsername) {
        this.driverUsername = driverUsername;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "customerUsername='" + customerUsername + '\'' +
                ", sourceAddress='" + sourceAddress + '\'' +
                ", destinationAddress='" + destinationAddress + '\'' +
                ", driverUsername='" + driverUsername + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
       if (o instanceof OrderModel) {
            if (((OrderModel) o).sourceAddress.equals(sourceAddress)
            && ((OrderModel) o).destinationAddress.equals(destinationAddress)
            && ((OrderModel) o).customerUsername.equals(customerUsername)) {
                return true;
            }
            return false;
       }
       return false;
    }

}
