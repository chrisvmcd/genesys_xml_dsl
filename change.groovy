configuration {
    reference {
        folder "id": "AFolder", "DBID": 1234
    }
    create {
        application name: "WD1IV12B_CUSTOMER_SEARCH", {
            options {
                list_pair {
                    str_pair key: "logging.level", value: "DEBUG"
                    str_pair key: "lws.password", value: "password"
                    str_pair key: "lws.username", value: "demo"
                    str_pair key: "polling.interval.seconds", value: "60"
                    str_pair key: "test.mode", value: "false"
                }
            }
        }
    }
}