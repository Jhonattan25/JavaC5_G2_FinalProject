package com.devsenior;

import com.devsenior.repository.DataRepository;
import com.devsenior.service.AnalyticsService;
import com.devsenior.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {

      DataRepository dataRepository = new DataRepository();
      AnalyticsService analyticsService = new AnalyticsService(dataRepository);
      ConsoleUI consoleUI = new ConsoleUI(analyticsService);

      consoleUI.start();
    }
}