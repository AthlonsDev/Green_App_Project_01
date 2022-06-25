package com.example.green_app_project_01.models

import java.sql.Timestamp

class Score(val overallScore: Int, timestamp: Long) {
    constructor(): this(0, timestamp = 0)
}