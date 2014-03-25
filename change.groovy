configuration {
    create {
        application name: "WD1IV12B_CUSTOMER_SEARCH", {
            options "logging.level": "DEBUG",
                    "lws.password": "password",
                    "lws.username": "demo",
                    "polling.interval.seconds": "60",
                    "test.mode": "false"
        }
    }
}