package com.example.yuber.services;

import com.example.yuber.controllers.CustomerController;
import com.example.yuber.controllers.CustomerWaitController;
import com.example.yuber.models.OrderSession;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
                // change scene here
                break;
            }
        } while(running);
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}