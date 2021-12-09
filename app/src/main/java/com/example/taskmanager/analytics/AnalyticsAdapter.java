package com.example.taskmanager.analytics;

import java.util.Map;

public interface AnalyticsAdapter {

    void report(String name, Map<String, String> data);

}
