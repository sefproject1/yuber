package com.example.yuber.services;

import com.example.yuber.models.OrderSession;

public class AcceptanceRunnable implements Runnable {

    private boolean running;

    public AcceptanceRunnable() {
        running = true;
    }

    public void run() {
        do {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (OrderService.checkIfAccepted(OrderSession.getOrder())) {
                break;
            }
        } while(running);
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}